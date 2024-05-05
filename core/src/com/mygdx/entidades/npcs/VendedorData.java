package com.mygdx.entidades.npcs;

import java.util.ArrayList;

import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;

public enum VendedorData {

	
	CARPINTERO(Items.PICO, Items.ESQUEMA_SIERRA_CIRCULAR, Items.DISCO_HIERRO, Items.CINCEL,Items.MAZA);
	
	private ArrayList<Items> inventario = new ArrayList();
	
	VendedorData(Items ...items ) {
		for(int i=0;i<items.length;i++) {
			inventario.add(items[i]);
		}
		
	}
	
	public ArrayList<Items> getInventario(){
		return inventario;
	}
}
