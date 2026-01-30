package com.mygdx.entidades.ObjetosDelMapa;

import java.util.ArrayList;

import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;

public class ObjetosTallerManager{

	private ArrayList<ObjetoDelMapa> objetos;
	
	public ObjetosTallerManager() {
		objetos = new ArrayList<>();
	}
	
	public void agregarObjeto(ObjetoDelMapa entidad) {
		objetos.add(entidad);
	}
	
    public void detectarJugador(Jugador jugador) {
        for (ObjetoDelMapa entidad: objetos) {
        	entidad.detectarJugador(jugador);
        }
    }
    
    public void renderizar() {
        for (ObjetoDelMapa entidad: objetos) {
        	entidad.draw();
        }
    }

	
}
