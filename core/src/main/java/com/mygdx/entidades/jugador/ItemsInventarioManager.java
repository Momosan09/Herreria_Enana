package com.mygdx.entidades.jugador;

import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;
import com.mygdx.utiles.InventarioList;

public class ItemsInventarioManager {

	private InventarioList<Item> items = new InventarioList<>();//LOS ITEMS "FISICOS" son los cuales tienen interaccion, se pueden equipar o interactuan con el mundo
	
	public InventarioList<Item> getItems(){
		return items;
	}
	
	public void agregarItem(Item item){
		items.add(item);
	}
	
	public void agregarItem(Item item, boolean mostrarMensaje) {
		items.add(item);
		if(mostrarMensaje) {
			
		}

	}
	

	public void eliminarItem(IngredientesId item) {
		items.remove(new Item(item));
	}
	
	public String getItemMensaje() {
		return "Añadido: " + items.get(items.size()-1).getNombre();
	}
	
	public Item getItem(Items item) {
		if(!items.isEmpty()) {			
		for(int i = 0; i<items.size();i++) {
			if(item == items.get(i).getTipo()) {
				return items.get(i);
			}
		}
		}
		
		return null;
	}
	
//	
	public Item getItem(int i) {
		if(!items.isEmpty()) {			
			return items.get(i);
		}
		
		return null;
	}
	
    public void eliminarItemRoto() {
        items.removeIf(i -> i.getUsos() == 0);
    }
}
