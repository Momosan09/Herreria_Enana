package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;

public class HierroMineral extends Mineral{

	public HierroMineral(float x, float y, boolean comprable, String rutaTextura) {
		super(x, y, comprable, rutaTextura, TipoMinerales.HIERRO);
		
	}

}
