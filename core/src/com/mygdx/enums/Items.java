package com.mygdx.enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.utiles.Recursos;

public enum Items {
	//Aca van todos los items que el jugador puede tener y que sean necesarios para realizar alguna accion
	//Ejemplo: PICO, es necesario para minar
	//Ejemplo: AMULETO1, es necesario para x motivo
	
	
	VACIO(new Texture(Recursos.VACIO),0,-1),
	PICO(new Texture(Recursos.PICO_DER),100,-1),
	CINCEL(new Texture(Recursos.CINCEL),90,-1),
	MAZA(new Texture(Recursos.MAZA),120,-1),
	LIMA_PLANA(new Texture(Recursos.LIMA),30,-1),
	
	
	//Esquemas
	ESQUEMA_SIERRA_CIRCULAR(new Texture(Recursos.ESQUEMA_TEXT)),
	
	//MISION
	DISCO_HIERRO(new Texture(Recursos.DISCO_HIERRO)),
	SIERRA_CIRCULAR(new Texture(Recursos.SIERRA_CIRCULAR));

	private Texture textura;
	private int valor;
	private int usos;

	Items(Texture textura, int valor, int usos){
		this.textura = textura;
		this.valor = valor;
		this.usos = usos;
	}
	
	Items(Texture textura){
		this.textura = textura;
		this.valor = -1;
		this.usos = -1;
	}

	public Texture getTextura() {
		return textura;
	}
	
	public String getNombre() {
		String nombre = this.toString();
		return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
	}
	
	public int getValor() {
		return valor;
	}
	
	public int getUsos() {
		return usos;
	}
}
