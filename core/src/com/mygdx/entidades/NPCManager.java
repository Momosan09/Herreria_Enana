package com.mygdx.entidades;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

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
      
    public void hablar(Npc npc, int index) {
    	npc.getDialogos(index);
    }

    public ArrayList<Npc> getEntidades() {
        return npcs;
    }
     
    public ArrayList<Rectangle> getColisiones() {
    	return colisiones;
    }

}

