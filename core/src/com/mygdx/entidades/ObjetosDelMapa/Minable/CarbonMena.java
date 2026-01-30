package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.utiles.recursos.Recursos;

public class CarbonMena extends Mineral{

	public CarbonMena(float x, float y, World world, boolean comprable) {
		super(x, y, world, comprable, 10, 8, IngredientesId.CARBON_MENA);
	}

}
