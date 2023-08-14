package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.enums.Direcciones;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.Recursos;

public class Jugador {

	private Vector2 posicion;
	private float velocidad = 100f;

	public OrthographicCamera camara;

	private int tamañoPersonaje = 32;
	
	Animator animacionQuieto, animacionAbajo, animacionArriba, animacionDerecha, animacionIzquierda;

	public Jugador(OrthographicCamera camara) {

		posicion = new Vector2(Gdx.graphics.getWidth() / 2 - (tamañoPersonaje/ 2),Gdx.graphics.getHeight() / 2 - (tamañoPersonaje/ 2)); // posicion inicial
		this.camara = camara;

		crearAnimaciones();

	}

	public void draw(SpriteBatch batch) {
		// sprite.draw(batch);
		update();

	}

	public void update() {
		movimiento(Gdx.graphics.getDeltaTime());

	}

	public void movimiento(float deltaTime) { // tendria que usar la clase Entradas para esto?
		boolean estaEnMovimiento=false;	//para saber si esta quieto

		if(Gdx.input.isKeyPressed(Keys.W) != Gdx.input.isKeyPressed(Keys.S) != Gdx.input.isKeyPressed(Keys.A) != Gdx.input.isKeyPressed(Keys.D)) {
			estaEnMovimiento=false;

		
		if (Gdx.input.isKeyPressed(Keys.W) != Gdx.input.isKeyPressed(Keys.S)) { //para evitar que se mueva si toca al mismo tiempo las teclas opuestas
			if(Gdx.input.isKeyPressed(Keys.W)) {
				estaEnMovimiento=true;
				posicion.y += velocidad * deltaTime;
				alternarSprites(Direcciones.ARRIBA);				
			}else {
				animacionArriba.reset();//resetea la animacion para que la proxima vez que se toce la tecla, la animacion empieze de cero
			}
			if (Gdx.input.isKeyPressed(Keys.S)) {
				estaEnMovimiento=true;
				posicion.y -= velocidad * deltaTime;
				alternarSprites(Direcciones.ABAJO);
			}else {
				animacionAbajo.reset();
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.A) != Gdx.input.isKeyPressed(Keys.D)) {
			if (Gdx.input.isKeyPressed(Keys.A)) {
				estaEnMovimiento=true;
				posicion.x -= velocidad * deltaTime;
				alternarSprites(Direcciones.IZQUIERDA);
			}else {
				animacionIzquierda.reset();
			}
			if (Gdx.input.isKeyPressed(Keys.D)) {
				estaEnMovimiento=true;
				posicion.x += velocidad * deltaTime;
				alternarSprites(Direcciones.DERECHA);
			}else {
				animacionDerecha.reset();
			}
		}
		
		if(!estaEnMovimiento) {
			alternarSprites(Direcciones.QUIETO);
			resetearAnimaciones(animacionArriba, animacionAbajo, animacionDerecha, animacionIzquierda);
		}else {
			animacionQuieto.reset();
		}

		movimientoCamara();
	}

	public void movimientoCamara() {
		camara.position.set(posicion.x + tamañoPersonaje / 2, posicion.y + tamañoPersonaje / 2, 0);
		camara.update();
	}

	public Vector2 getPosicion() {
		return posicion;
	}

	public void alternarSprites(Direcciones direccion) {
		switch (direccion) {
		case ABAJO:
			animacionAbajo.render();
			break;
		case ARRIBA:
			animacionArriba.render();
			break;
		case IZQUIERDA:
			animacionIzquierda.render();
			break;
		case DERECHA:
			animacionDerecha.render();
			break;
		case QUIETO:
			animacionQuieto.render();
			break;
		}
	}

	private void crearAnimaciones() {
		animacionAbajo = new Animator(Recursos.JUGADOR_SPRITESHEET, posicion, 0);
		animacionArriba = new Animator(Recursos.JUGADOR_SPRITESHEET, posicion, 1);
		animacionIzquierda = new Animator(Recursos.JUGADOR_SPRITESHEET, posicion, 2);
		animacionDerecha = new Animator(Recursos.JUGADOR_SPRITESHEET, posicion, 3);
		animacionQuieto = new Animator(Recursos.JUGADOR_SPRITESHEET, posicion, 4);

		animacionAbajo.create();
		animacionArriba.create();
		animacionIzquierda.create();
		animacionDerecha.create();
		animacionQuieto.create();
	}

	private void resetearAnimaciones(Animator ... animaciones) {	//varargs, ya que nose cuantas animaciones voy a usar
	    for (Animator animacion : animaciones) { // for each: tipo del elemento, nombre del elemento, coleccion a recorrer;
	        animacion.reset();
	    }

	}

}
