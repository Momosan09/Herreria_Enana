package com.mygdx.entidades;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.enums.Direcciones;
import com.mygdx.enums.Items;
import com.mygdx.hud.HUD;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;

public class Jugador {

	public Vector2 posicion;
	private float velocidad = 100f;
	public OrthographicCamera camara;
	public boolean puedeMoverse = false;
	private int tamañoPersonaje = 32;
	private Texture texturaItem;
	private Sprite spriteItem;
	public int dinero=10;
	public float movimientoX, movimientoY;
	public Rectangle colision;

	
	public Sprite jugadorImg;
	 
	private ArrayList<Items> items = new ArrayList<>();//Por ahora el jugador va a poder tener varios items, pero talvez mas adelante hago que solo pueda tener uno a la vez
	private ArrayList<Mineral> mineralesInv = new ArrayList<>();  
    
	public Direcciones direccionActual = Direcciones.QUIETO;
	public Direcciones direccionDelChoque = null;
	Animator animacionQuieto, animacionAbajo, animacionArriba, animacionDerecha, animacionIzquierda;
	
	
	public Jugador() {
		posicion = new Vector2(Gdx.graphics.getWidth() / 2 - (tamañoPersonaje/ 2),Gdx.graphics.getHeight() / 2 - (tamañoPersonaje/ 2)); // posicion inicial
		colision = new Rectangle(posicion.x, posicion.y, tamañoPersonaje, tamañoPersonaje);
		jugadorImg = new Sprite(new Texture(Recursos.YUNQUE));
		
		//crearAnimaciones();
		//texturaItem = new Texture(Recursos.PICO_DER);
		//spriteItem = new Sprite(texturaItem);
	}
	
	public Jugador(int offset) {
		posicion = new Vector2(Gdx.graphics.getWidth() / 2 - (tamañoPersonaje/ 2),Gdx.graphics.getHeight() / 2 - (tamañoPersonaje/ 2)); // posicion inicial
		colision = new Rectangle(posicion.x, posicion.y, tamañoPersonaje, tamañoPersonaje);
		jugadorImg = new Sprite(new Texture(Recursos.YUNQUE));
		
		//crearAnimaciones();
		//texturaItem = new Texture(Recursos.PICO_DER);
		//spriteItem = new Sprite(texturaItem);
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
		jugadorImg.draw(batch);
		update();
		dibujarItemActual();
	}

	private void update() {
		//movimiento();
	}

	public void movimiento(Direcciones direccion) {
		float deltaTime = Gdx.graphics.getDeltaTime();
        movimientoX = 0;
        movimientoY = 0;

        this.direccionActual = direccion;

        	
        System.out.println("la direccion acutal " + direccionActual);
        System.out.println("choque en " + direccionDelChoque);
        
        switch(direccionActual){
        	case ARRIBA:
        		if(direccionDelChoque != Direcciones.ARRIBA) {
        			movimientoY += velocidad;        			
        		}
        		break;
        	case ABAJO:
        		if(direccionDelChoque != Direcciones.ABAJO) {        			
        		movimientoY -= velocidad;
        		}
        		break;
        	case IZQUIERDA:
        		if(direccionDelChoque != Direcciones.IZQUIERDA) {  
        		movimientoX -= velocidad;
        		}
        		break;
        	case DERECHA:
        		if(direccionDelChoque != Direcciones.DERECHA) {  
        		movimientoX += velocidad;
        		}
        		break;
        	case QUIETO:
        		if(direccionDelChoque != Direcciones.QUIETO) {        			
        		movimientoX = 0;
        		movimientoY = 0;
        		}
        }

        if (movimientoX != 0 && movimientoY != 0) {
            movimientoX *= 0.7071f;
            movimientoY *= 0.7071f;
            //Esta es la velocidad que tiene cuando se mueva diagonalmente, ya que, sino su velocidad diagonal seria mayor que la horizontal o vertical
        }

        posicion.x += movimientoX * deltaTime;
        posicion.y += movimientoY * deltaTime;
        
        jugadorImg.setPosition(posicion.x, posicion.y);
        actualizarColision(posicion.x,posicion.y);
        
        // Actualizar animaciones y cámaras
        if (movimientoX != 0 || movimientoY != 0) {
           //alternarSprites(direccionActual);
        } else {
            //alternarSprites(Direcciones.QUIETO);
            //resetearAnimaciones(animacionArriba, animacionAbajo, animacionIzquierda, animacionDerecha);
        }

        //movimientoCamara();
        //}
    }


	public Vector2 getPosicion() {
		return posicion;
	}


	public void actualizarColision(float x,float y) {
		colision.x = x;
		colision.y = y;
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

}
