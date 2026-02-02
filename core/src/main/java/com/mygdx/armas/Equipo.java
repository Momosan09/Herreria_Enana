package com.mygdx.armas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

/**
 * 
 */
public abstract class Equipo {
	
	protected Texture textura;
	protected Sprite sprite;
	
	protected float verde;
	protected float rojo;
	protected float azul;
	
	protected Mineral metalBase;
	protected int modificador;//cambiar
	
	public Equipo(Texture textura, Mineral metalBase) {
		this.textura = textura;
		this.metalBase = metalBase;
	}
	
}
