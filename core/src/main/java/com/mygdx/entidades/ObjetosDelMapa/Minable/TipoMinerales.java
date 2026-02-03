package com.mygdx.entidades.ObjetosDelMapa.Minable;

/**
 * Este enum sirve para definir las rutas de los minerales en conjunto con EstadosMinerales
 * @author  Momosan09
 *
 */
public enum TipoMinerales {
	
	/*Sobre la arcilla y los moldes.
	 * Arcilla se lo toma como un mineral en TipoMinerales.
	 * En EstadosMinerales se usa "Molde" para poder diferenciar que es por molde pero puede servir para el mineral una vez procesado.
	 * O sea, ahora hago TipoMinerales.ARCILLA EstadosMinerales.MOLDE_CABEZA_MAZA para que sea el molde. Pero tambien puedo hacer TipoMinerales.HIERRO EstadosMinerales.MOLDE_CABEZA_MAZA, en este caso no significa que sea un molde de hierro, sino el item ya fabricado.
	 * 
	 * */
	PIEDRA("objetosDelMundo/inanimados/minerales/piedra/piedra"),
	HIERRO("objetosDelMundo/inanimados/minerales/hierro/hierro"),
	CARBON("objetosDelMundo/inanimados/minerales/carbon/carbon"),
	ORO("objetosDelMundo/inanimados/minerales/oro/oro"),
	ARCILLA("objetosDelMundo/inanimados/minerales/arcilla/arcilla");
	
	public String ruta;
	
	private TipoMinerales(String ruta) {
	this.ruta = ruta;
	}
}
