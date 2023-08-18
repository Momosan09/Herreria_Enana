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
    
	private Direcciones direccionActual = Direcciones.QUIETO;
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

	public void movimiento(float deltaTime) {
        float movimientoX = 0;
        float movimientoY = 0;

        if (Gdx.input.isKeyPressed(Keys.W)) {
            movimientoY += velocidad;
            direccionActual = Direcciones.ARRIBA;
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            movimientoY -= velocidad;
            direccionActual = Direcciones.ABAJO;
        }

        if (Gdx.input.isKeyPressed(Keys.A)) {
            movimientoX -= velocidad;
            direccionActual = Direcciones.IZQUIERDA;
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            movimientoX += velocidad;
            direccionActual = Direcciones.DERECHA;
        }

        if (movimientoX != 0 && movimientoY != 0) {
            movimientoX *= 0.7071f;
            movimientoY *= 0.7071f;
        }

        posicion.x += movimientoX * deltaTime;
        posicion.y += movimientoY * deltaTime;

        // Actualizar animaciones y cámaras
        if (movimientoX != 0 || movimientoY != 0) {
            alternarSprites(direccionActual);
        } else {
            alternarSprites(Direcciones.QUIETO);
            resetearAnimaciones(animacionArriba, animacionAbajo, animacionIzquierda, animacionDerecha);
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
