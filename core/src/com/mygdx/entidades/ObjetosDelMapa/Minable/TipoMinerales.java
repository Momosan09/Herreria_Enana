package com.mygdx.entidades.ObjetosDelMapa.Minable;

/**
 * Este enum sirve para definir las rutas de los minerales en conjunto con EstadosMinerales
 * @author  Momosan09
 *
 */
public enum TipoMinerales {

	PIEDRA("objetosDelMundo/inanimados/minerales/piedra/piedra"), HIERRO("objetosDelMundo/inanimados/minerales/hierro/hierro"), CARBON("objetosDelMundo/inanimados/minerales/carbon/carbon");
	
	public String ruta;
	
	private TipoMinerales(String ruta) {
	this.ruta = ruta;
	}
}
