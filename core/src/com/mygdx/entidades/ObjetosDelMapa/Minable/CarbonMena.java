package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.utiles.Recursos;

public class CarbonMena extends Mineral{

	public CarbonMena(int x, int y, World world, boolean comprable) {
		super(x, y, world, comprable, Recursos.MENA_CARBON, TipoMinerales.CARBON, EstadosMinerales.MENA, 10, 8);
	}

}
