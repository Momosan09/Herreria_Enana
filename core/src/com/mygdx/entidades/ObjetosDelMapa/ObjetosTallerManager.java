package com.mygdx.entidades.ObjetosDelMapa;

import java.util.ArrayList;

import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;

public class ObjetosTallerManager{

	private ArrayList<Entidad> objetos;
	
	public ObjetosTallerManager() {
		objetos = new ArrayList<>();
	}
	
	public void agregarObjeto(Entidad entidad) {
		objetos.add(entidad);
	}
	
    public void detectarJugador(Jugador jugador) {
        for (Entidad entidad: objetos) {
        	entidad.detectarJugador(jugador);
        }
    }
    
    public void dibujar() {
        for (Entidad entidad: objetos) {
        	entidad.draw();
        }
    }

	
}
