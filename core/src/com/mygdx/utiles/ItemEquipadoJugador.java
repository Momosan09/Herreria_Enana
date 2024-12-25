package com.mygdx.utiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.enums.Items;
import com.mygdx.enums.PartesDelCuerpo;

/**
 * Esto es todo lo que puede colgarle al jugador
 *
 */
public class ItemEquipadoJugador {

	private String ruta;
	private Texture textura;
	private Sprite sprite;
	private Vector2 posicion;
	private Items tipo;
	private PartesDelCuerpo pc;
	private boolean mostrar = true;
	
	/**
	 * 
	 * @param ruta
	 * @param posicion es la posicion que va a tomar relativa al mundo (se mueve con el jugador)
	 * @param pc es la posicion relativa al jugador (se mantiene estatico en la parte del cuerpo)
	 */
	public ItemEquipadoJugador(Items tipo, Vector2 posicion, PartesDelCuerpo pc) {
		this.tipo = tipo;
		this.posicion = posicion;
		this.pc = pc;
		this.sprite = new Sprite(tipo.getTextura());
	}
	
	public ItemEquipadoJugador() {
		this.sprite = new Sprite();
		this.posicion = new Vector2();
		this.pc = null;
		this.tipo = Items.VACIO;
	}
	
	public void dibujar() {
		if(mostrar) {
			sprite.draw(Render.batch);			
		}else {
			
		}
	}
	
	public void actualizarPosicion(Vector2 posicion) {
		this.posicion = posicion;
	}
	
	public void dibujarYPosicion(Vector2 posicion) {
		if(mostrar && (sprite.getTexture() != null || sprite == null)) {
		this.posicion = posicion;
		sprite.setPosition(posicion.x, posicion.y);
		sprite.draw(Render.batch);		
		}

	}
	
	public void mostrar() {
		if(!mostrar) {
			mostrar = true;			
		}
	}
	
	public void ocultar() {
		if(mostrar) {
			mostrar = false;
		}
	}
	
	public void cambiarSprite(Items tipo) {
		this.tipo = tipo;
		this.sprite.set(new Sprite(tipo.getTextura()));

	}
	
	public void setParteDelCuerpo(PartesDelCuerpo pc) {
		this.pc = pc;
	}
	
	public void borrarSprite() {
		sprite.setTexture(null);
	}
	
	public Items getTipo() {
		return tipo;
	}
	
	public void setTipo(Items tipo) {
		this.tipo = tipo;
	}
}
