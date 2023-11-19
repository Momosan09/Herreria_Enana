package com.mygdx.entidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.utiles.HelpDebug;

public class NPCManager {
    private ArrayList<Npc> npcs;
	private ArrayList<Rectangle> colisiones;

    public NPCManager() {
    	npcs = new ArrayList<Npc>();
    	colisiones = new ArrayList<Rectangle>();
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
    		System.out.println(HelpDebug.debub(getClass())+"Cargados los dialogos de " + npcIndex.getNombre() );

    		
		}
    }
    
    public void mostrarDialogo(SpriteBatch batch, int index) {
        for (Npc entidad : npcs) {
        	if(entidad.interaccion()) {
        		batch.begin();
        		entidad.getCajaDialogo().selectMensaje(index);
        		entidad.dibujarCajaDialogo(batch);
        		batch.end();
        	}
        	
        }
    }
    
    public void hablar(Npc npc, int index) {
    	npc.getDialogos(index);
    }

    public ArrayList<Npc> getEntidades() {
        return npcs;
    }
    
    public void reEscalarDialogos(int width, int height) {
    	for (Npc npc : npcs) {
    		npc.getCajaDialogo().reEscalar(width, height);			
		}
    }
    
    
    public ArrayList<Rectangle> getColisiones() {
    	return colisiones;
    }

}

