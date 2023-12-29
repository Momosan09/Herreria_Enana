package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.entidades.Npc;

public class CharlaManager {

	//Aca van todas las charlas
	public ArrayList<Charla> vendedorTiendaCharlas;
	
	
	public CharlaManager(Npc ...npcs) {
		npcVendedorTienda(npcs[0]);
	}
	
	public void npcVendedorTienda(Npc vendedorTienda) {
		vendedorTienda.charlas.add(new Charla(vendedorTienda, -1,vendedorTienda.getDialogos(), vendedorTienda.getRespuestas()));
	}
	
}
