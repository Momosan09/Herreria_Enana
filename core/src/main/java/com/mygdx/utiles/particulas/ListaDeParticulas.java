package com.mygdx.utiles.particulas;

import com.mygdx.utiles.recursos.Recursos;

public enum ListaDeParticulas {

	MINADO_PIEDRA(Recursos.particulas.MINADO_PIEDRA),
	FUEGO(Recursos.particulas.FUEGO);

	public String ruta;
	
	ListaDeParticulas(String ruta){
		this.ruta = ruta;
	}

}
