package com.mygdx.entidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.utiles.HelpDebug;

public class NPCManager {
    private List<Npc> npcs;
	private ArrayList<Rectangle> colisiones;

    public NPCManager() {
    	npcs = new ArrayList<Npc>();
    	colisiones = new ArrayList<Rectangle>();
    }

    public void agregarEntidad(Npc entidad) {
    	npcs.add(entidad);
    }

    public void eliminarEntidad(Npc entidad, World world) {
    	npcs.remove(entidad);
    	world.destroyBody(entidad.getBody());
    }

    public void detectarJugador(Jugador jugador) {
        for (Npc entidad : npcs) {
            entidad.detectarJugador(jugador);
           // entidad.interaccion();
        }
    }
    

    public void renderizar() {
        for (Npc entidad : npcs) {
            entidad.ejecutarAnimacion();
        }
    }
    

    public List<Npc> getEntidades() {
        return npcs;
    }
    
    
    public ArrayList<Rectangle> getColisiones() {
    	return colisiones;
    }

}

