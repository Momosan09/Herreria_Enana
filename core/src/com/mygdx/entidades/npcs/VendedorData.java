package com.mygdx.entidades.npcs;

import java.util.ArrayList;

import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;

public enum VendedorData {

	
	CARPINTERO(IngredientesId.MANGO_MADERA_0, IngredientesId.ESQUEMA_HOJA_ESPADA),
	TIENDA(IngredientesId.CINCEL), 
	AMBULANTE(IngredientesId.SIERRA_CIRCULAR);
	
	private ArrayList<IngredientesId> inventario = new ArrayList();
	
	VendedorData(IngredientesId ...items ) {
		for(int i=0;i<items.length;i++) {
			inventario.add(items[i]);
		}
		
	}
	
	public ArrayList<IngredientesId> getInventario(){
		return inventario;
	}
}
