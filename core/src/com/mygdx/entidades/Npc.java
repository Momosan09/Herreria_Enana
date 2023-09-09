package com.mygdx.entidades;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Npc extends Entidad{

	public Npc(float x, float y, String ruta){
		super(x,y,ruta);
	}
	
	public void say() {
		System.out.println("elo");
	}
	
	public void interaccion(Jugador jugador) {
		if(jugadorEnRango) System.out.println("soy el viejito");
	}
}
