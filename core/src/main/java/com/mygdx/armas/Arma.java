package com.mygdx.armas;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public abstract class Arma extends Equipo{

	public Arma(Texture textura, Mineral metalBase) {
		super(textura, metalBase);
	}
}
