package com.mygdx.entidades.ObjetosDelMapa.Minable;

/**
 * Este enum sirve para definir las rutas de los minerales en conjunto con TipoMinerales
 * @author  Momosan09
 *
 */

public enum EstadosMinerales {

	/*Sobre la arcilla y los moldes.
	 * Arcilla se lo toma como un mineral en TipoMinerales.
	 * En EstadosMinerales se usa "Molde" para poder diferenciar que es por molde pero puede servir para el mineral una vez procesado.
	 * O sea, ahora hago TipoMinerales.ARCILLA EstadosMinerales.MOLDE_CABEZA_MAZA para que sea el molde. Pero tambien puedo hacer TipoMinerales.HIERRO EstadosMinerales.MOLDE_CABEZA_MAZA, en este caso no significa que sea un molde de hierro, sino el item ya fabricado.
	 * 
	 * */
	
	MENA("Mena.png"),
	PURO("Puro.png"),
	LINGOTE("Lingote.png"),
	PLANCHA("Plancha.png"),
	TIRA("Tira.png"),
	DISCO("Disco.png"),
	TIRA_PILA("TiraPila.png"),
	MOLDE_CABEZA_MAZA("CabezaMaza.png");//OXIDADO?
	
	
	public String ruta;
	
	EstadosMinerales(String ruta){
		this.ruta = ruta;
	}
	
}
