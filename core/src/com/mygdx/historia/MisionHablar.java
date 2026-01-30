package com.mygdx.historia;

import com.mygdx.entidades.Npc;

public class MisionHablar extends Mision{

	private Npc npcObjetivo;
	private MisionesDelJuego datosMision;
	
	public MisionHablar(MisionesDelJuego datosMision) {
		super(datosMision);
		this.npcObjetivo = datosMision.getNpcObjetivo();
		this.datosMision = datosMision;
	}

	
	public void comprobarCondicion() {
		//if(npcObjetivo.getNombreCharlaActual().equals(datosMision.get))
	}
}
