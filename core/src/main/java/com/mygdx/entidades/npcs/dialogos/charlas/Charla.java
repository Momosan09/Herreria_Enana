package com.mygdx.entidades.npcs.dialogos.charlas;

import java.util.List;

import com.mygdx.entidades.Npc;
import com.mygdx.utiles.EstadoMundo;


public record Charla(String id, String monologo, List<Condicion> condiciones, List<Respuesta> respuestas) {

    public boolean puedeMostrarse(EstadoMundo mundo, Npc npc) {
        return condiciones.stream()
                .allMatch(c -> c.seCumple(mundo, npc));
    }
	

    
}

