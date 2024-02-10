package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.enums.EstadosMinerales;
import com.mygdx.utiles.Recursos;

public class PiedraMena extends Mineral{

	public PiedraMena(float x, float y, World world, boolean comprable) {
		super(x, y, world,comprable, Recursos.PIEDRA_PIEDRA, TipoMinerales.PIEDRA, EstadosMinerales.MENA, 16,12);
	}

}
