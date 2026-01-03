package com.mygdx.utiles.sonidos;

import com.mygdx.utiles.recursos.Recursos;

public enum ListaSonidos {

	MINAR(Recursos.sonidos.MINAR),
	MISION_RECIBIDA(Recursos.sonidos.MISION_RECIBIDA);
	
	public String ruta;
	ListaSonidos(String ruta){
		this.ruta = ruta;
	}
}
