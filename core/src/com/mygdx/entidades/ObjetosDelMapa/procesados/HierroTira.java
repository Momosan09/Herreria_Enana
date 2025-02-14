package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;

public class HierroTira extends Mineral{

	public HierroTira() {
		super(TipoMinerales.HIERRO, EstadosMinerales.TIRA);
	}

}
