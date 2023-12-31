package com.mygdx.enums;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.utiles.Recursos;

public enum Items {
	//Aca van todos los items que el jugador puede tener y que sean necesarios para realizar alguna accion
	//Ejemplo: PICO, es necesario para minar
	//Ejemplo: AMULETO1, es necesario para x motivo
	
	
	VACIO(new Texture(Recursos.VACIO)),PICO(new Texture(Recursos.PICO_DER));
	
	private Texture textura;

	Items(Texture textura){
		this.textura = textura;
	}

	public Texture getTextura() {
		return textura;
	}
}
