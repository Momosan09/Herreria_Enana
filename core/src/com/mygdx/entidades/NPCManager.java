package com.mygdx.entidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class NPCManager {
    private List<Npc> npcs;

    public NPCManager() {
    	npcs = new ArrayList<Npc>();
    }

    public void agregarEntidad(Npc entidad) {
    	npcs.add(entidad);
    }

    public void eliminarEntidad(Npc entidad) {
    	npcs.remove(entidad);
    }

    public void detectarJugador(Jugador jugador) {
        for (Npc entidad : npcs) {
            entidad.detectarJugador(jugador);
        }
    }

    public void renderizar(SpriteBatch batch) {
        for (Npc entidad : npcs) {
            entidad.ejecutarAnimacion();
        }
    }
    
    public void crearDialogos() {
    	for (Npc npcIndex: npcs) {
    		npcIndex.crearCajaDialogo();
    		System.out.println("Cargados los dialogos de " + npcIndex.getNombre() );

    		
		}
    }
    
    public void mostrarDialogo(SpriteBatch batch, int index) {
        for (Npc entidad : npcs) {
        	if(entidad.interaccion()) {
        		batch.begin();
        		entidad.getDialogo().selectMensaje(index);
        		entidad.dibujarCajaDialogo(batch);
        		batch.end();
        	}
        	
        }
    }
    
    public void hablar(Npc npc, int index) {
    	npc.getDialogos(index);
    }

    public List<Npc> getEntidades() {
        return npcs;
    }

}

