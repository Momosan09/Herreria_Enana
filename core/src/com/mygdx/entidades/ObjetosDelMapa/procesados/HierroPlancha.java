package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.utiles.Recursos;

public class HierroPlancha extends Mineral{

	public HierroPlancha() {
		//Los dos enum componen la ruta de la textura TipoMinerales.HIERRO.ruta + EstadoMinerales.PURO.ruta
		super(TipoMinerales.HIERRO, EstadosMinerales.PLANCHA);
		
	}

}
