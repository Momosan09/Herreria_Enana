package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.utiles.recursos.Recursos;

public class HierroMena extends Mineral{

	public HierroMena(float x, float y, World world, boolean comprable) {
		//Los dos enum componen la ruta de la textura TipoMinerales.HIERRO.ruta + EstadoMinerales.PURO.ruta
		super(x, y, world, comprable, 11, 7, IngredientesId.HIERRO_MENA);
	}

}
