package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.utiles.Recursos;

public class CarbonMena extends Mineral{

	public CarbonMena(float x, float y, World world, boolean comprable) {
		super(x, y, world, comprable, TipoMinerales.CARBON, EstadosMinerales.MENA, 10, 8);
	}

}
