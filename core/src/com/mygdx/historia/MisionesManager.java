package com.mygdx.historia;

import java.util.ArrayList;

import com.mygdx.entidades.Jugador;

public class MisionesManager {
	
	private ArrayList<Mision> misiones = new ArrayList<>();
	private Jugador jugador;
	
	public MisionesManager(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public void agregarMision() {
		misiones.clear();
		misiones = jugador.getMisiones();
	}
	
	public void checkearMisiones() {
		for (Mision mision : misiones) {
			mision.comprobarCondicion();
			mision.darRecompensa(jugador);
		}
	}
	
}
