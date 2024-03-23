package com.mygdx.enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.utiles.Recursos;

public enum Items {
	//Aca van todos los items que el jugador puede tener y que sean necesarios para realizar alguna accion
	//Ejemplo: PICO, es necesario para minar
	//Ejemplo: AMULETO1, es necesario para x motivo
	
	
	VACIO(new Texture(Recursos.VACIO)),
	PICO(new Texture(Recursos.PICO_DER)),
	CINCEL(new Texture(Recursos.CINCEL)),
	MAZA(new Texture(Recursos.MAZA));
	
	private Texture textura;

	Items(Texture textura){
		this.textura = textura;
	}

	public Texture getTextura() {
		return textura;
	}
	
	public String getNombre() {
		String nombre = this.toString();
		return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
	}
}
