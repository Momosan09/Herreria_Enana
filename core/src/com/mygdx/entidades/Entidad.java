package com.mygdx.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.enums.Items;
import com.mygdx.utiles.Render;

public abstract class Entidad {

	protected Vector2 posicion;//La necesito en las hijas
	protected Texture textura;//La necesito en las hijas
	private Sprite sprite;
	protected boolean jugadorEnRango = false, apretoE = false;
	private boolean jugadorTienePico = false;//Deberia ir en mineral pero no se me ocurre como hacerlo
	private boolean comprable = false;

	protected int colisionAncho=32, colisionAlto=32;//Por si tengo entidades mas grandes?
	private Rectangle colision;

	private int distanciaInteraccion = 64;
	
	public Entidad(float x, float y,String rutaTextura) {
		this.posicion = new Vector2(x,y);
		colision = new Rectangle(posicion.x,posicion.y,colisionAncho,colisionAlto);
		this.textura = new Texture(rutaTextura);
		sprite = new Sprite(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);

	}
	
	public Entidad(float x, float y, boolean comprable,String rutaTextura) {
		this.posicion = new Vector2(x,y);
		colision = new Rectangle(posicion.x,posicion.y,colisionAncho,colisionAlto);
		this.textura = new Texture(rutaTextura);
		this.comprable = comprable;
		sprite = new Sprite(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);
	}
	
	public void draw() {
		sprite.draw(Render.batch);
	}
	
	
	public void detectarJugador(Jugador jugador) {
		buscarItemEnJugador(jugador);//Cuando el jugador esta dentro del rango de la entidad, esta busca en los items del jugador. Esto me puede servir para objetos de mision y el minado
		if(((jugador.getPosicion().x - this.posicion.x) < distanciaInteraccion && (jugador.getPosicion().x - this.posicion.x) > -distanciaInteraccion) && ((jugador.getPosicion().y - this.posicion.y) < distanciaInteraccion && (jugador.getPosicion().y - this.posicion.y) > -distanciaInteraccion)){
			jugadorEnRango = true;
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
	
	public void buscarItemEnJugador(Jugador jugador) {//Setea los items que el jugador tiene o no tiene
		if(jugador.getItems().contains(Items.PICO)) {
			jugadorTienePico = true;
		}else {
			jugadorTienePico = false;
		}
	}
	
	public boolean buscarPorItemEnJugador(Items item) {//Para las clases hijas, sirve para saber si el jugador tiene un item en especifico
		switch (item) {
		case PICO:
			return jugadorTienePico;

		default:
			return false;
		}
	}
	
	public Texture getTextura() {
		return textura;
	}
	
	public boolean isComprable() {
		return comprable;
	}
	
	 public Rectangle getColision() {
		 return colision;
	 }
	
}
