package com.mygdx.combinaciones;

import com.mygdx.entidades.Jugador;

public class MotorCrafteo {

	private InventarioCrafteo inventario;

	
	public MotorCrafteo(Jugador j) {
		setInventario(new InventarioJugador(j));
	}
	
    /**
     * Verifica si se puede fabricar una receta
     */
    public static boolean puedeFabricar(Receta receta, InventarioCrafteo inventario, Medios medioActual) {
    	if(receta!= null) { //El metodo buscar de Cargador de recetas devuelve una receta nula si no encontro, y por lo tanto, si no encontro no puede fabricar
        
    		// Medio correcto
        if (receta.medio() != medioActual) {
            return false;
        }



        if (receta.herramienta() != null) {
            if (!inventario.tieneItem(receta.herramienta())) {
                return false;
            }
        }
        // Entradas
        for (IngredienteReceta ing : receta.entradas()) {
            if (inventario.getCantidad(ing.ingrediente()) < ing.cantidad()) {
                return false;
            }
        }

        return true;
        
    	}else {
    		return false;
    	}
    }

    /**
     * Fabrica la receta (ASUME que ya fue validada)
     */
    public static void fabricar(Receta receta, InventarioCrafteo inventario) {

        // Consumir entradas
        for (IngredienteReceta ing : receta.entradas()) {
            inventario.consumir(ing.ingrediente(),ing.cantidad());
        }

        // Agregar salidas
        for (IngredienteReceta out : receta.salidas()) {
            inventario.agregar(out.ingrediente(),out.cantidad());
        }
    }

	public InventarioCrafteo getInventario() {
		return inventario;
	}

	public void setInventario(InventarioCrafteo inventario) {
		this.inventario = inventario;
	}
}
