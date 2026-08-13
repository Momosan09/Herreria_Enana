package com.mygdx.entidades.npcs.dialogos.charlas;

import java.util.List;

import com.mygdx.entidades.Npc;
import com.mygdx.utiles.EstadoMundo;


public record Charla(String id, String monologo, List<Condicion> condiciones, List<Respuesta> respuestas) {

	public boolean puedeMostrarse(EstadoMundo mundo, Npc npc) {

		//System.out.println("\n\t === DEBUG === \n\t \n NPC: "+npc.getNombre()+" CONDICIONES DE LA CHARLA ID: "+id+" PARA MOSTRARSE \n");
	    for (Condicion c : condiciones) {
	        boolean cumple = c.seCumple(mundo, npc);

	        //System.out.println("\t- " + c.getClass().getSimpleName() + " -> " + cumple);

	        if(!cumple)
	            return false;
	    }

	    return true;
	}
}

