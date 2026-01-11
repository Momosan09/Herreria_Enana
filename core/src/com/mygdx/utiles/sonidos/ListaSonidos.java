package com.mygdx.utiles.sonidos;

import com.mygdx.utiles.recursos.Recursos;

public enum ListaSonidos {

	MINAR(Recursos.sonidos.MINAR),
	VENTA(Recursos.sonidos.VENTA),
	FALLA(Recursos.sonidos.FALLA),
	GOLPE_MARTILLO_METAL(Recursos.sonidos.GOLPE_MARTILLO_AL_METAL),
	SONIDO_ESQUEMA(Recursos.sonidos.SONIDO_ESQUEMA),
	SIERRA_METAL(Recursos.sonidos.SIERRA_METAL),
	MISION_RECIBIDA(Recursos.sonidos.MISION_RECIBIDA);
	
	public String ruta;
	ListaSonidos(String ruta){
		this.ruta = ruta;
	}
}
