package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public class Carbon extends Mineral{

	public Carbon(int x, int y, World world, boolean comprable, String rutaTextura) {
		super(x, y, world, comprable, rutaTextura, TipoMinerales.CARBON, 11, 11);
	}

}
