package com.mygdx.entidades.ObjetosDelMapa.Minable;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.utiles.Recursos;

public class PiedraMena extends Mineral{

	public PiedraMena(float x, float y, World world, boolean comprable) {
		//Los dos enum componen la ruta de la textura TipoMinerales.HIERRO.ruta + EstadoMinerales.PURO.ruta
		super(x, y, world,comprable, TipoMinerales.PIEDRA, EstadosMinerales.MENA, 16,10);
	}

}
