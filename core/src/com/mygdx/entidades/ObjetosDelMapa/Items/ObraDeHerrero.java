package com.mygdx.entidades.ObjetosDelMapa.Items;

import com.mygdx.combinaciones.EstadiosDeCombinacion;

public abstract class ObraDeHerrero {
	
	protected EstadiosDeCombinacion estadio = EstadiosDeCombinacion.OBRA_DE_HERRERO;
	protected float calidad;
	
	public ObraDeHerrero(float calidad) {
		this.calidad = calidad;
	}
	
}
