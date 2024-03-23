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
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.historia.Mision;
import com.mygdx.historia.TipoMision;

public class Jugador {

	public Vector2 posicion; //la hice publica para poder setearle valor en el hiloCliente
	private Body body;
	private float velocidad = 100f;
	public OrthographicCamera camara;
	public boolean puedeMoverse = false;
	private int tamañoPersonaje = MundoConfig.tamanoTile;
	private Texture texturaItem;
	private Sprite spriteItem;
	public int[] dinero;
	public boolean estaChocando = false;
	public String spritesheet;
	public Rectangle areaJugador;
	
	//Inventarios
	private ArrayList<Items> items = new ArrayList<>();//Por ahora el jugador va a poder tener varios items, pero talvez mas adelante hago que solo pueda tener uno a la vez
	private ArrayList<Mineral> mineralesInv = new ArrayList<>();  
	private ArrayList<Integer> indicesDeEliminacion = new ArrayList<>();//Se guardan temporalmente los indices de los minerales a eliminar, despues este array se limpia
	private ArrayList<Mision> tareas = new ArrayList<>();
	
	public Direcciones direccionActual = Direcciones.QUIETO;
	public Direcciones direccionDelChoque = null;
	private Animator animacionQuieto, animacionAbajo, animacionArriba, animacionDerecha, animacionIzquierda;
	private Animator animacionActual;
	
	
	public Jugador(OrthographicCamera camara, World world, Vector2 spawnPosicion) {
		posicion = new Vector2(); // posicion inicial
		this.camara = camara;
		
		// Crear el cuerpo del jugador
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(spawnPosicion.x+16,spawnPosicion.y);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(6,2);
        
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
		
		
		texturaItem = new Texture(Recursos.PICO_DER);
		spriteItem = new Sprite(texturaItem);
		this.spritesheet = Recursos.JUGADOR1_SPRITESHEET;
		crearAnimaciones();
		
		dinero = new int[3];
		
		areaJugador = new Rectangle(posicion.x, posicion.y, 32, 32);
		
		
		//ESTO ES TEMPORAL, DESPUES EL JUGADOR NO VA A EMPEZAR CON LAS HERRAMIENTAS
		items.add(Items.PICO);
		items.add(Items.MAZA);
		items.add(Items.CINCEL);
	}

	private void dibujarItemActual() {
		if(items.contains(Items.PICO)) {
			if(direccionActual == Direcciones.IZQUIERDA) {
				//spriteItem.flip(true, false);
			}
//			spriteItem.draw(Render.batch);
			spriteItem.setPosition(posicion.x-16, posicion.y-16);
		}
	}
	
	public void draw(SpriteBatch batch) {
		// sprite.draw(batch);
		update();
		dibujarItemActual();
		areaJugador.set(posicion.x, posicion.y, 32, 32);
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
            posicion.y = body.getPosition().y+14;//ajusta la colision a los pies del jugador, la camara se centra en la posicion por tanto el sprite del jugador quedaria mas arriba, pero me parece que queda mejor asi
            
            if (movimientoX != 0 || movimientoY != 0) {
                //body.setLinearVelocity(movimientoX, movimientoY);
                alternarSprites(direccionActual).render();
            } else {
                body.setLinearVelocity(0, 0);
                alternarSprites(Direcciones.QUIETO).render();;
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
			
            camara.position.set(posicion.x, posicion.y, 0);
            camara.update();
		}
	}

	public Vector2 getPosicion() {
		return posicion;
	}

	public Animator alternarSprites(Direcciones direccion) {
		switch (direccion) {
		case ABAJO:
			return animacionAbajo;
		case ARRIBA:
			return animacionArriba;
		case IZQUIERDA:
			return animacionIzquierda;
		case DERECHA:
			return animacionDerecha;
		case QUIETO:
			return animacionQuieto;
		}
		return null;
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
	
	
	public void devolverMineralesEnElInventario(TipoMinerales mineral, EstadosMinerales estado){
		String[][] minerales = new String[TipoMinerales.values().length][EstadosMinerales.values().length];
		for(int i = 0; i<mineralesInv.size();i++) {
			
			switch (mineralesInv.get(i).tipo) {
			case PIEDRA:
				minerales[0][0] = TipoMinerales.PIEDRA.toString().toLowerCase();
				break;
				

			case CARBON:
				minerales[1][0] = TipoMinerales.CARBON.toString().toLowerCase();
				switch (mineralesInv.get(i).estado) {
				case MENA:
					minerales[1][0] = EstadosMinerales.MENA.toString().toLowerCase();
					break;
				case PURO:
					minerales[1][1] = EstadosMinerales.PURO.toString().toLowerCase();
					break;
				default:
					break;
				}
				
				
			case HIERRO:
				minerales[2][0] = TipoMinerales.HIERRO.toString().toLowerCase();
				switch (mineralesInv.get(i).estado) {
				case MENA:
					minerales[2][0] = EstadosMinerales.MENA.toString().toLowerCase();
					break;
				case PURO:
					minerales[2][1] = EstadosMinerales.PURO.toString().toLowerCase();
					break;
					
				case LINGOTE:
					minerales[2][2] = EstadosMinerales.LINGOTE.toString().toLowerCase();
					break;
				default:
					
					break;
				}
				
			default:
				break;
			}
			}

	}
	

	
	public int buscarCantidadDeMineralesPorTipoYEstado(TipoMinerales mineral, EstadosMinerales estado){
		int cont = 0;
		for(int i = 0; i<mineralesInv.size();i++) {
			if(mineralesInv.get(i).tipo == mineral && mineralesInv.get(i).estado == estado) {
				cont++;
			}
		}
		return cont;
	}
	
	public void eliminarPorNombreDadoCantidadYEstado(TipoMinerales mineral, int cantidad, EstadosMinerales estado) {
		System.out.println(HelpDebug.debub(getClass())+"El tamano es " + mineralesInv.size() + "++++++++++++");
		if(mineralesInv.size() > 0) {
			
			int cont = 0;
			for(int i = 0; i<mineralesInv.size();i++) {
				if(mineralesInv.get(i).tipo == mineral && cont < cantidad && mineralesInv.get(i).estado == estado) {
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
