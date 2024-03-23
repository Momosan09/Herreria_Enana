package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.utiles.Recursos;

public class CarbonPuro extends Mineral{

	public CarbonPuro(boolean comprable) {
		super(comprable, Recursos.CARBON_PURO, TipoMinerales.CARBON, EstadosMinerales.PURO);
		
	}
}
