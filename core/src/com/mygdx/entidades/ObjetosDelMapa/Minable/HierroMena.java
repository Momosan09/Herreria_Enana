package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.utiles.Recursos;

public class HierroMena extends Mineral{

	public HierroMena(float x, float y, World world, boolean comprable) {
		super(x, y, world, comprable, Recursos.MENA_HIERRO, TipoMinerales.HIERRO, EstadosMinerales.MENA, 11, 7);
	}

}
