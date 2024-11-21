package com.mygdx.entidades.ObjetosDelMapa.Minable;

public enum EstadosMinerales {

	
	MENA("Mena.png"), PURO("Puro.png"), LINGOTE("Lingote.png"), PLANCHA("Plancha.png");//OXIDADO?
	
	
	public String ruta;
	
	EstadosMinerales(String ruta){
		this.ruta = ruta;
	}
	
}
