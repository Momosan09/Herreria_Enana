package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.utiles.Recursos;

public class HierroPuro extends Mineral{

	public HierroPuro() {
		super(Recursos.HIERRO_PURO, TipoMinerales.HIERRO, EstadosMinerales.PURO);
		
	}

}
