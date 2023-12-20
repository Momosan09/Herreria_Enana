package com.mygdx.entidades;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.enums.Direcciones;
import com.mygdx.enums.Items;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.historia.Mision;
import com.mygdx.historia.TipoMision;

public class Jugador {

	public Vector2 posicion; //la hice publica para poder setearle valor en el hiloCliente
	private Body body;
	private float velocidad = 100f;
	public OrthographicCamera camara;
	public boolean puedeMoverse = true;
	private int tamañoPersonaje = 32;
	private Texture texturaItem;
	private Sprite spriteItem;
	public int[] dinero;
	public boolean estaChocando = false;
	public String spritesheet;
	
	//Inventarios
	private ArrayList<Items> items = new ArrayList<>();//Por ahora el jugador va a poder tener varios items, pero talvez mas adelante hago que solo pueda tener uno a la vez
	private ArrayList<Mineral> mineralesInv = new ArrayList<>();  
	private ArrayList<Integer> indicesDeEliminacion = new ArrayList<>();//Se guardan temporalmente los indices de los minerales a eliminar, despues este array se limpia
	private ArrayList<Mision> tareas = new ArrayList<>();
	
	public Direcciones direccionActual = Direcciones.QUIETO;
	public Direcciones direccionDelChoque = null;
	private Animator animacionQuieto, animacionAbajo, animacionArriba, animacionDerecha, animacionIzquierda;
	
	
	public Jugador(OrthographicCamera camara, World world, Vector2 spawnPosicion) {
		posicion = new Vector2(); // posicion inicial
		this.camara = camara;
		
		// Crear el cuerpo del jugador
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawnPosicion.x+16,spawnPosicion.y+16);


        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(14,16);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
		
		
		texturaItem = new Texture(Recursos.PICO_DER);
		spriteItem = new Sprite(texturaItem);
		this.spritesheet = Recursos.JUGADOR1_SPRITESHEET;
		crearAnimaciones();
		
		dinero = new int[3];
		
		
	}

	private void dibujarItemActual() {
		if(items.contains(Items.PICO)) {
			if(direccionActual == Direcciones.IZQUIERDA) {
				//spriteItem.flip(true, false);
			}
			spriteItem.draw(Render.batch);
			spriteItem.setPosition(posicion.x-16, posicion.y-16);
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

        if (puedeMoverse) {
            if (Gdx.input.isKeyPressed(Keys.W) && direccionDelChoque != Direcciones.ARRIBA) {
                movimientoY += velocidad;
                direccionActual = Direcciones.ARRIBA;
                body.setLinearVelocity(movimientoX, movimientoY);
            } else if (Gdx.input.isKeyPressed(Keys.S) && direccionDelChoque != Direcciones.ABAJO) {
                movimientoY -= velocidad;
                direccionActual = Direcciones.ABAJO;
                body.setLinearVelocity(movimientoX, movimientoY);
            }

            if (Gdx.input.isKeyPressed(Keys.A) && direccionDelChoque != Direcciones.IZQUIERDA) {
                movimientoX -= velocidad;
                direccionActual = Direcciones.IZQUIERDA;
                body.setLinearVelocity(movimientoX, movimientoY);
            } else if (Gdx.input.isKeyPressed(Keys.D) && direccionDelChoque != Direcciones.DERECHA) {
                movimientoX += velocidad;
                direccionActual = Direcciones.DERECHA;
                body.setLinearVelocity(movimientoX, movimientoY);
            }
            
            posicion.x = body.getPosition().x;
            posicion.y = body.getPosition().y;
            
            if (movimientoX != 0 || movimientoY != 0) {
                //body.setLinearVelocity(movimientoX, movimientoY);
                alternarSprites(direccionActual);
            } else {
                body.setLinearVelocity(0, 0);
                alternarSprites(Direcciones.QUIETO);
                resetearAnimaciones(animacionArriba, animacionAbajo, animacionIzquierda, animacionDerecha);
            }

        if (movimientoX != 0 && movimientoY != 0) {
            movimientoX *= 0.7071f;
            movimientoY *= 0.7071f;
            body.setLinearVelocity(movimientoX, movimientoY);
            //Esta es la velocidad que tiene cuando se mueva diagonalmente, ya que, sino su velocidad diagonal seria mayor que la horizontal o vertical
       }

        movimientoCamara();
        }
        
	}
	
	
	public void movimientoCamara() {
		if(camara != null) {
			
            camara.position.set(body.getPosition().x, body.getPosition().y, 0);
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
		animacionAbajo = new Animator(spritesheet, posicion, 0);
		animacionArriba = new Animator(spritesheet, posicion, 1);
		animacionIzquierda = new Animator(spritesheet, posicion, 2);
		animacionDerecha = new Animator(spritesheet, posicion, 3);
		animacionQuieto = new Animator(spritesheet, posicion, 4);

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
//			System.out.println("E");
			return true;
		}
		return false;
	}
	
	public boolean isTabPressed() {
		if(Gdx.input.isKeyPressed(Keys.TAB)) {
//			System.out.println("TAB");
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
	
	public int buscarCantidadDeMineralesPorNombre(String nombre){
		int cont = 0;
		for(int i = 0; i<mineralesInv.size();i++) {
			if(mineralesInv.get(i).nombre.equals(nombre)) {
				cont++;
			}
		}
		return cont;
	}
	
	public void eliminarPorNombreDadoYCantidad(String nombre, int cantidad) {
		System.out.println(HelpDebug.debub(getClass())+"El tamano es " + mineralesInv.size() + "++++++++++++");
		if(mineralesInv.size() > 0) {
			
			int cont = 0;
			for(int i = 0; i<mineralesInv.size();i++) {
				if(mineralesInv.get(i).nombre.equals(nombre) && cont < cantidad) {
					indicesDeEliminacion.add(i);
					cont++;
				}
			}
	    
	    
	    for (int j = 0; j < indicesDeEliminacion.size(); j++) {	    	
	        mineralesInv.remove(indicesDeEliminacion.get(j).intValue());
	    }
	    //indicesDeEliminacion.clear();
		}
	}

	public OrthographicCamera getCamara() {
		return camara;
	}

	public void agregarMision(Entidad requisor, TipoMision tipo, String objeto, int cantidad, int oro, int plata, int cobre) {
		tareas.add(new Mision(requisor, tipo, objeto, cantidad, oro, plata, cobre));

	}
	
	public ArrayList<Mision> getTareas() {
		return tareas;
		
	}
	
}
