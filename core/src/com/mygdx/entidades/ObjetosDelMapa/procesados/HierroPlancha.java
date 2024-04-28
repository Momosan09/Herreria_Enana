package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.utiles.Recursos;

public class HierroPlancha extends Mineral{

	public HierroPlancha(boolean comprable) {
		super(comprable, Recursos.PLANCHA_HIERRO, TipoMinerales.HIERRO, EstadosMinerales.PLANCHA);
		
	}

}
