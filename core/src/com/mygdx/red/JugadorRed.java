package com.mygdx.red;

import java.net.InetAddress;

import com.mygdx.entidades.Jugador;

public class JugadorRed {

	private Jugador jugador;
	public InetAddress ip;
	public int puerto;
	
	
	public JugadorRed(Jugador jugador, InetAddress ip, int puerto) {
		this.jugador = jugador;
		this.ip = ip;
		this.puerto = puerto;
	}
	
	public Jugador getEntidadJugador() {
		return jugador;
	}
}
