package com.mygdx.entidades;

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
import com.mygdx.hud.HUD;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Jugador {

	private Vector2 posicion;
	private float velocidad = 120f;
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

	private void update() {
		movimiento(Gdx.graphics.getDeltaTime());


	}

	private void movimiento(float deltaTime) {
        float movimientoX = 0;
        float movimientoY = 0;

        if(Gdx.input.isKeyPressed(Keys.W) != Gdx.input.isKeyPressed(Keys.S)) {
        if (Gdx.input.isKeyPressed(Keys.W)) {
            movimientoY += velocidad;
            direccionActual = Direcciones.ARRIBA;
        } else if (Gdx.input.isKeyPressed(Keys.S)) {
            movimientoY -= velocidad;
            direccionActual = Direcciones.ABAJO;
        }
        }else {
        	resetearAnimaciones(animacionQuieto);
        }

        if(Gdx.input.isKeyPressed(Keys.A) != Gdx.input.isKeyPressed(Keys.D)) {
        if (Gdx.input.isKeyPressed(Keys.A)) {
            movimientoX -= velocidad;
            direccionActual = Direcciones.IZQUIERDA;
        } else if (Gdx.input.isKeyPressed(Keys.D)) {
            movimientoX += velocidad;
            direccionActual = Direcciones.DERECHA;
        }
        }else {
        	resetearAnimaciones(animacionQuieto);
        }

        if (movimientoX != 0 && movimientoY != 0) {
            movimientoX *= 0.7071f;
            movimientoY *= 0.7071f;
            //Esta es la velocidad que tiene cuando se mueva diagonalmente, ya que, sino su velocidad diagonal seria mayor que la horizontal o vertical
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

	private void movimientoCamara() {
		camara.position.set(posicion.x + tamañoPersonaje / 2, posicion.y + tamañoPersonaje / 2, 0);
		camara.update();
	}

	public Vector2 getPosicion() {
		return posicion;
	}

	private void alternarSprites(Direcciones direccion) {
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
	
	public boolean isEPressed() {
		if(Gdx.input.isKeyPressed(Keys.E)) {
			System.out.println("E");
			return true;
		}
		return false;
	}

}
