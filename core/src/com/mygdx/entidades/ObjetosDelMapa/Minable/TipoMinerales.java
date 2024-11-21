package com.mygdx.entidades.ObjetosDelMapa.Minable;

public enum TipoMinerales {

	PIEDRA("objetosDelMundo/inanimados/minerales/piedra/piedra"), HIERRO("objetosDelMundo/inanimados/minerales/hierro/hierro"), CARBON("objetosDelMundo/inanimados/minerales/carbon/carbon");
	
	public String ruta;
	
	private TipoMinerales(String ruta) {
	this.ruta = ruta;
	}
}
