package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.enums.EstadosMinerales;
import com.mygdx.utiles.Recursos;

public class HierroMena extends Mineral{

	public HierroMena(int x, int y, World world, boolean comprable) {
		super(x, y, world, comprable, Recursos.MENA_HIERRO, TipoMinerales.HIERRO, EstadosMinerales.MENA, 11, 7);
	}

}
