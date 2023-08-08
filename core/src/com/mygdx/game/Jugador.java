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

	private Sprite sprite;
	private Vector2 posicion;
	private float velocidad = 100f;
	private Direcciones direccion;

	public OrthographicCamera camara;

	Animator animacionQuieto, animacionAbajo, animacionArriba, animacionDerecha, animacionIzquierda;

	public Jugador(Texture tex, OrthographicCamera camara) {

		sprite = new Sprite(tex);
		posicion = new Vector2(Gdx.graphics.getWidth() / 2 - (sprite.getWidth() / 2),
				Gdx.graphics.getHeight() / 2 - (sprite.getHeight() / 2)); // posicion inicial
		sprite.setPosition(posicion.x, posicion.y);

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
		if (Gdx.input.isKeyPressed(Keys.W)) {
			posicion.y += velocidad * deltaTime;
			alternarSprites(Direcciones.ARRIBA);
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			posicion.y -= velocidad * deltaTime;
			alternarSprites(Direcciones.ABAJO);
		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			posicion.x -= velocidad * deltaTime;
			alternarSprites(Direcciones.IZQUIERDA);
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			posicion.x += velocidad * deltaTime;
			alternarSprites(Direcciones.DERECHA);
		}

		//sprite.setPosition(posicion.x, posicion.y);// actualizar posicion del sprite
		movimientoCamara();
	}

	public void movimientoCamara() {
		camara.position.set(posicion.x + sprite.getWidth() / 2, posicion.y + sprite.getHeight() / 2, 0);
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

	private void resetearAnimaciones() {
		animacionAbajo.reset();
		animacionArriba.reset();
		animacionIzquierda.reset();
		animacionDerecha.reset();
		animacionQuieto.reset();
	}

}
