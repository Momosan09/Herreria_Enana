package com.mygdx.entidades.npcs.dialogos;

import java.util.ArrayList;

import com.mygdx.entidades.Npc;

public class CharlaManager {

	//Aca van todas las charlas
	public ArrayList<Charla> vendedorTiendaCharlas;
	
	
	public CharlaManager(Npc ...npcs) {
		npcVendedorTienda(npcs[0]);
		npcViejo(npcs[1]);
	}
	
	public void npcVendedorTienda(Npc vendedorTienda) {
		vendedorTienda.charlas.add(new Charla(-1,vendedorTienda.getDialogos(), vendedorTienda.getRespuestas()));
	}
	
	public void npcViejo(Npc viejo) {
		viejo.charlas.add(new Charla(-1, viejo.getDialogos(), viejo.getRespuestas()));
	}
	
}
