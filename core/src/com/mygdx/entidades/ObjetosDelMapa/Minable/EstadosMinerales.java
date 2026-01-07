package com.mygdx.entidades.ObjetosDelMapa.Minable;

/**
 * Este enum sirve para definir las rutas de los minerales en conjunto con TipoMinerales
 * @author  Momosan09
 *
 */

public enum EstadosMinerales {

	
	MENA("Mena.png"), PURO("Puro.png"), LINGOTE("Lingote.png"), PLANCHA("Plancha.png"), TIRA("Tira.png"), DISCO("Disco.png");//OXIDADO?
	
	
	public String ruta;
	
	EstadosMinerales(String ruta){
		this.ruta = ruta;
	}
	
}
