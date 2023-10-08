package com.mygdx.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utiles.Render;

public abstract class Entidad {

	protected Vector2 posicion;
	Texture textura;
	Sprite sprite;
	protected boolean jugadorEnRango = false, apretoE = false;

	private int distanciaInteraccion = 64;
	
	public Entidad(float x, float y, String rutaTextura) {
		this.posicion = new Vector2(x,y);

		this.textura = new Texture(rutaTextura);
		sprite = new Sprite(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);

	}
	
	public void draw() {
		sprite.draw(Render.batch);
	}
	
	
	public void detectarJugador(Jugador jugador) {
		if(((jugador.getPosicion().x - this.posicion.x) < distanciaInteraccion && (jugador.getPosicion().x - this.posicion.x) > -distanciaInteraccion) && ((jugador.getPosicion().y - this.posicion.y) < distanciaInteraccion && (jugador.getPosicion().y - this.posicion.y) > -distanciaInteraccion)){
			jugadorEnRango = true;
			if(jugador.isEPressed()) {
				apretoE = true;
			}
		}else {
			jugadorEnRango = false;
			apretoE = false;
		}
	}
	
	public boolean getJugadorEnRango() {
		return jugadorEnRango;
	}
	
	public Vector2 getPosicion() {
		return posicion;
	}
	
	public int getDistanciaInteraccion() {
		return distanciaInteraccion;
	}
	
}
