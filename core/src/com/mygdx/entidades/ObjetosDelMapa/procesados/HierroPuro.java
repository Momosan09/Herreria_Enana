package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.enums.EstadosMinerales;
import com.mygdx.utiles.Recursos;

public class HierroPuro extends Mineral{

	public HierroPuro(boolean comprable) {
		super(comprable, Recursos.HIERRO_PURO, TipoMinerales.HIERRO, EstadosMinerales.PURO);
		
	}

}
