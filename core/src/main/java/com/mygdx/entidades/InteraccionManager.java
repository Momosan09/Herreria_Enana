package com.mygdx.entidades;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InteraccionManager {

    private List<Interactuable> interactuables = new ArrayList<>();

    public void registrar(Interactuable i) {
        interactuables.add(i);
    }

    public void resolver(Jugador jugador) {

        for (Interactuable i : interactuables) {
        	
        	if(i.getAreaInteraccion() != null) {
        		if (i.getAreaInteraccion().overlaps(jugador.getAreaInteraccion())) {
        			if (Gdx.input.isKeyJustPressed(Keys.E)) {
        				i.interactuar(jugador);
        				return; // solo uno por vez
                }
            }
        	}
        }
    }
}