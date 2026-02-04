package com.mygdx.entidades.jugador;

import java.util.ArrayList;
import java.util.EnumMap;

import com.mygdx.combinaciones.CreadorDeMinerales;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.combinaciones.InventarioCrafteo;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;

public class IngredientesInventarioManager implements InventarioCrafteo{

    private EnumMap<IngredientesId, Integer> inventario = new EnumMap<>(IngredientesId.class); //Los items "abstractos" son los que sirven para las combinaciones, contar cantidades y no existen en el mapa
    private ArrayList<Item> items;//ESTO ES SOLO UN PARCHE PARA EL METODO tieneItem(IngredientesId id) NO USAR PARA OTRA COSA DENTRO DE ESTA CLASE
    
    
    public IngredientesInventarioManager(ArrayList<Item> items) {
        for (IngredientesId id : IngredientesId.values()) {
            inventario.put(id, 0);
        }
        this.items = items;
    }
    
	@Override
    public int getCantidad(IngredientesId ingrediente) {
        return inventario.getOrDefault(ingrediente, 0);
    }

    @Override
    public void agregar(IngredientesId ingrediente, int cantidad) {
        inventario.put(ingrediente, getCantidad(ingrediente) + cantidad);
    }
    
    public void agregar(IngredientesId ingrediente) {
        inventario.put(ingrediente, getCantidad(ingrediente) + 1);
    }

    public void agregar(Mineral mineral) {
    	IngredientesId ingrediente = mineral.getIngredienteId();
        inventario.put(ingrediente, getCantidad(ingrediente) + 1);
    }

    
    @Override
    public void consumir(IngredientesId ingrediente, int cantidad) {
        int actual = getCantidad(ingrediente);
        if (actual < cantidad)
            throw new IllegalStateException("No hay suficiente " + ingrediente);

        inventario.put(ingrediente, actual - cantidad);
    }
    
    public void consumir(Mineral mineral) {
    	IngredientesId ingrediente = mineral.getIngredienteId();
        int actual = getCantidad(ingrediente);
        if (actual < 1) {
        	throw new IllegalStateException("No hay suficiente " + ingrediente);        	
        }

        inventario.put(ingrediente, actual - 1);
    }
    
    public void consumir(Mineral mineral, int cantidad) {
    	IngredientesId ingrediente = mineral.getIngredienteId();
        int actual = getCantidad(ingrediente);
        if (actual < cantidad) {
        	throw new IllegalStateException("No hay suficiente " + ingrediente);        	
        }

        inventario.put(ingrediente, actual - cantidad);
    }
    
    @Override
    public ArrayList<Mineral> getMinerales(){
    	return obtenerTodosLosMinerales();
    }
	
    public ArrayList<IngredientesId> obtenerIngredientesParaCrafteo() {

        ArrayList<IngredientesId> lista = new ArrayList<>();

        for (IngredientesId id : inventario.keySet()) {
            if (inventario.get(id) > 0 && id.esIngredienteCrafteable()) {
                lista.add(id);
            }
        }
        
        ArrayList<Mineral> minerales = obtenerTodosLosMinerales();
        for(int i = 0; i<minerales.size();i++) {
        	lista.add(minerales.get(i).getIngredienteId());
        }

        return lista;
    }

    
    public ArrayList<Mineral> obtenerTodosLosMinerales() {

        ArrayList<Mineral> resultado = new ArrayList<>();

        for (IngredientesId id : inventario.keySet()) {
        	if(id.tipoI == null) { //Solo es mineral si "tipoI" es nulo
        		
            int cantidad = inventario.get(id);

            if (cantidad <= 0) continue;

            for (int i = 0; i < cantidad; i++) {
                resultado.add(CreadorDeMinerales.crear(id));
            }
        	}
        }

        return resultado;
    }
    
    public void imprimirInventario() {
        System.out.println("=== INVENTARIO ===");
        inventario.forEach((k, v) -> {
            if (v > 0)
                System.out.println(k + " x" + v);
        });
        
    }
    
    public boolean tieneItem(IngredientesId id) {
        // primero: herramientas físicas
        for (Item item : items) {//FIXME pendinte ya
            if (item.getIngredienteId() == id) {
                return true;
            }
        }

        // segundo: ingredientes contables (por si alguna receta usa eso)
        return getCantidad(id) > 0;
    }

}
