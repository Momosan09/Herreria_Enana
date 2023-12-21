package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public class Piedra extends Mineral{

	public Piedra(float x, float y, World world, boolean comprable, String rutaTextura) {
		super(x, y, world,comprable, rutaTextura, TipoMinerales.PIEDRA, 16,12);
	}

}
