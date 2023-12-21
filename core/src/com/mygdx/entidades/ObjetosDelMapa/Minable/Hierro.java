package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public class Hierro extends Mineral{



	public Hierro(int x, int y, World world, boolean comprable, String rutaTextura) {
		super(x, y, world, comprable, rutaTextura, TipoMinerales.HIERRO ,11,11);
	}

}
