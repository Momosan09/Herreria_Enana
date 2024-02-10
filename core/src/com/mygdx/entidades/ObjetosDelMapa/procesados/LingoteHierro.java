package com.mygdx.entidades.ObjetosDelMapa.procesados;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.enums.EstadosMinerales;

public class LingoteHierro extends Mineral{

	public LingoteHierro(float x, float y, boolean comprable, String rutaTextura) {
		super(x, y, comprable, rutaTextura, TipoMinerales.HIERRO, EstadosMinerales.LINGOTE);
		
	}

}
