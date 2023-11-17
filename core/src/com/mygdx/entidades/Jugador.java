package com.mygdx.entidades;

import java.util.ArrayList;

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
import com.mygdx.enums.Items;
import com.mygdx.hud.HUD;
import com.mygdx.red.HiloCliente;
import com.mygdx.red.UtilesRed;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public class Jugador {

	public Vector2 posicion; //la hice publica para poder setearle valor en el hiloCliente
	private float velocidad = 120f;
	public OrthographicCamera camara;
	public boolean puedeMoverse = true;
	private int tamañoPersonaje = 32;
	private Texture texturaItem;
	private Sprite spriteItem;
	public int dinero=10;
	private boolean red = false;
	public int nroJugador;
	private HiloCliente hc;
	
	private ArrayList<Items> items = new ArrayList<>();//Por ahora el jugador va a poder tener varios items, pero talvez mas adelante hago que solo pueda tener uno a la vez
	private ArrayList<Mineral> mineralesInv = new ArrayList<>();  
    
	private Direcciones direccionActual = Direcciones.QUIETO;
	Animator animacionQuieto, animacionAbajo, animacionArriba, animacionDerecha, animacionIzquierda;
	
	public Jugador(OrthographicCamera camara, HiloCliente hc) {
		posicion = new Vector2(Gdx.graphics.getWidth() / 2 - (tamañoPersonaje/ 2),Gdx.graphics.getHeight() / 2 - (tamañoPersonaje/ 2)); // posicion inicial
		this.camara = camara;
		crearAnimaciones();
		texturaItem = new Texture(Recursos.PICO_DER);
		spriteItem = new Sprite(texturaItem);
		this.hc = hc;
		this.red = true;
	}
	
	public Jugador(OrthographicCamera camara) {
		posicion = new Vector2(Gdx.graphics.getWidth() / 2 - (tamañoPersonaje/ 2),Gdx.graphics.getHeight() / 2 - (tamañoPersonaje/ 2)); // posicion inicial
		this.camara = camara;
		crearAnimaciones();
		texturaItem = new Texture(Recursos.PICO_DER);
		spriteItem = new Sprite(texturaItem);
		this.red = false;
	}

	private void dibujarItemActual() {
		if(items.contains(Items.PICO)) {
			if(direccionActual == Direcciones.IZQUIERDA) {
				//spriteItem.flip(true, false);
			}
			spriteItem.draw(Render.batch);
			spriteItem.setPosition(posicion.x, posicion.y);
		}
	}
	
	public void draw(SpriteBatch batch) {
		// sprite.draw(batch);
		update();
		dibujarItemActual();
	}

	private void update() {
		movimiento(Gdx.graphics.getDeltaTime());
	}

	private void movimiento(float deltaTime) {
        float movimientoX = 0;
        float movimientoY = 0;

        if(puedeMoverse && !red) {
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
        movimientoCamara();
        }
        
        if(red) {
        	if(Gdx.input.isKeyPressed(Keys.W) != Gdx.input.isKeyPressed(Keys.S)) {
        		if (Gdx.input.isKeyPressed(Keys.W)) {
        			direccionActual = Direcciones.ARRIBA;
//        			System.out.println("++++++Soy el cliente "+ hc.getIdCliente() + "yendo para arriba");
        			//System.out.println(HelpDebug.debub(getClass())+"direccion arriba");
        			UtilesRed.hc.enviarMensaje("direccion#arriba");
        		} else if (Gdx.input.isKeyPressed(Keys.S)) {
        			direccionActual = Direcciones.ABAJO;
        			UtilesRed.hc.enviarMensaje("direccion#abajo");
        		}
        	}else {
        		resetearAnimaciones(animacionQuieto);
        	}
        
        
        
        if(Gdx.input.isKeyPressed(Keys.A) != Gdx.input.isKeyPressed(Keys.D)) {
        	if (Gdx.input.isKeyPressed(Keys.A)) {
    			direccionActual = Direcciones.IZQUIERDA;
    			UtilesRed.hc.enviarMensaje("direccion#izquierda");
        	} else if (Gdx.input.isKeyPressed(Keys.D)) {
    			direccionActual = Direcciones.DERECHA;
    			UtilesRed.hc.enviarMensaje("direccion#derecha");
        	}
        }else {
        	resetearAnimaciones(animacionQuieto);
        }
    
        }

        if (movimientoX != 0 || movimientoY != 0) {
//        	System.out.println(HelpDebug.debub(getClass())+"animanod");
            alternarSprites(direccionActual);
        } else {
//        	System.out.println("animado quieto");
            alternarSprites(Direcciones.QUIETO);
            resetearAnimaciones(animacionArriba, animacionAbajo, animacionIzquierda, animacionDerecha);
        }
        //movimientoCamara();
        
	}
	
	

	public void movimientoPorRed(float x, float y) {
		if(red) {
	        posicion.x = x;
	        posicion.y = y;
		}
	}
	public void movimientoCamara() {
		if(camara != null) {
			
		camara.position.set(posicion.x + tamañoPersonaje / 2, posicion.y + tamañoPersonaje / 2, 0);
		camara.update();
		}
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
	
	public boolean isEPressed() {
		if(Gdx.input.isKeyPressed(Keys.E)) {
			System.out.println("E");
			return true;
		}
		return false;
	}
	
	public boolean isTabPressed() {
		if(Gdx.input.isKeyPressed(Keys.TAB)) {
			System.out.println("E");
			return true;
		}
		return false;
	}
	
	public ArrayList<Items> getItems(){
		return items;
	}
	
	public ArrayList<Mineral> getMinerales(){
		return mineralesInv;
	}
	
	public boolean isRed() {
		return red;
	}

}
