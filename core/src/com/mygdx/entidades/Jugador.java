package com.mygdx.entidades;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.audio.AudioManager;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.Cincel;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.entidades.ObjetosDelMapa.Items.LimaPlana;
import com.mygdx.entidades.ObjetosDelMapa.Items.Maza;
import com.mygdx.entidades.ObjetosDelMapa.Items.Pico;
import com.mygdx.entidades.ObjetosDelMapa.Items.Sierra;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.enums.Direcciones;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.enums.Items;
import com.mygdx.enums.PartesDelCuerpo;
import com.mygdx.enums.Respuestas;
import com.mygdx.eventos.Listeners;
import com.mygdx.historia.Mision;
import com.mygdx.historia.MisionesDelJuego;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.ItemEquipadoJugador;
import com.mygdx.utiles.Monedero;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Jugador {

	private float velocidad = 100f;
	public boolean puedeMoverse = false;
	public Monedero monedero;
	public boolean estaChocando = false;
	public Vector2 posicion; //la hice publica para poder setearle valor en el hiloCliente
	private Body body;
	public OrthographicCamera camara;
	public String spritesheet;
	public Circle areaJugador;
	private ItemEquipadoJugador itemEnMano;
	
	//Inventarios
	private ArrayList<Item> items = new ArrayList<>();//Por ahora el jugador va a poder tener varios items, pero talvez mas adelante hago que solo pueda tener uno a la vez
	private EnumMap<TipoMinerales, EnumMap<EstadosMinerales, ArrayList<Mineral>>> mineralesInventario =
		    new EnumMap<>(TipoMinerales.class);
	
	private HashMap<String,Mision> tareas = new HashMap<String,Mision>();
	
	public Direcciones direccionActual = Direcciones.QUIETO;
	public Direcciones direccionDelChoque = null;
	private Animator animacionQuieto, animacionAbajo, animacionArriba, animacionDerecha, animacionIzquierda, animacionMinar;
	
	
	public Respuestas respuesta1 = Respuestas.NOVALOR, respuesta2 = Respuestas.NOVALOR;
	public boolean mostrarMensaje = true;
	
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
		
		
		this.spritesheet = Recursos.JUGADOR1_SPRITESHEET;
		crearAnimaciones();
		
		monedero = new Monedero(1,100,1000);
		
		areaJugador = new Circle(posicion.x, posicion.y, 32);
		
		
		//ESTO ES TEMPORAL, DESPUES EL JUGADOR NO VA A EMPEZAR CON LAS HERRAMIENTAS
		items.add(new Pico());
		items.add(new Maza());
		items.add(new Cincel());
		items.add(new LimaPlana());
		items.add(new Sierra());
		

		
		//Inicializa el inventario de minerales
		for (TipoMinerales tipo : TipoMinerales.values()) {
		    EnumMap<EstadosMinerales, ArrayList<Mineral>> estadoMap = new EnumMap<>(EstadosMinerales.class); // crea un enumMap por cada TipoMineral
		    for (EstadosMinerales estado : EstadosMinerales.values()) {
		        estadoMap.put(estado, new ArrayList<Mineral>());
		    }
		    mineralesInventario.put(tipo, estadoMap);
		}

	
		itemEnMano = new ItemEquipadoJugador();
		
//		System.out.println("Eel amigo" + obtenerMineral(TipoMinerales.HIERRO, EstadosMinerales.MENA));
	}


	
	public void draw(SpriteBatch batch) {
		// sprite.draw(batch);
		update();
		itemEnMano.dibujarYPosicion(this.posicion);
		//dibujarItemActual();
		areaJugador.set(posicion.x, posicion.y, 16);
	}

	private void update() {
		movimiento(Gdx.graphics.getDeltaTime());
		eliminarItemRoto();
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
                quieto();
            }

        if (movimientoX != 0 && movimientoY != 0) {
            movimientoX *= 0.7071f;
            movimientoY *= 0.7071f;
            body.setLinearVelocity(movimientoX, movimientoY);
            //Esta es la velocidad que tiene cuando se mueva diagonalmente, ya que, sino su velocidad diagonal seria mayor que la horizontal o vertical
       }

        movimientoCamara();
        }else {
        	quieto();
        }
        
	}
	
	private void quieto() {
     	 body.setLinearVelocity(0, 0);
         alternarSprites(Direcciones.QUIETO).render();;
         resetearAnimaciones(animacionArriba, animacionAbajo, animacionIzquierda, animacionDerecha);
	}
	
	private void movimientoCamara() {
		if(camara != null) {
			
            camara.position.set(posicion.x, posicion.y, 0);
            camara.update();
		}
	}

	public Vector2 getPosicion() {
		return posicion;
	}
	
	public void dibujarAreaInteraccion() {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(Render.batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(areaJugador.x, areaJugador.y, areaJugador.radius);
		shapeRenderer.end();
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
		animacionAbajo = new Animator(spritesheet, posicion, 0, 6);
		animacionArriba = new Animator(spritesheet, posicion, 1, 6);
		animacionIzquierda = new Animator(spritesheet, posicion, 2, 6);
		animacionDerecha = new Animator(spritesheet, posicion, 3, 6);
		animacionQuieto = new Animator(spritesheet, posicion, 4, 6);
		animacionMinar = new Animator(spritesheet, posicion, 5, 6);

		animacionAbajo.create();
		animacionArriba.create();
		animacionIzquierda.create();
		animacionDerecha.create();
		animacionQuieto.create();
		animacionMinar.create();
	}

	private void resetearAnimaciones(Animator ... animaciones) {	//varargs, ya que nose cuantas animaciones voy a usar
	    for (Animator animacion : animaciones) { // for each: tipo del elemento, nombre del elemento, coleccion a recorrer;
	        animacion.reset();
	    }

	}
	
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public void agregarItem(Item item) {
		items.add(item);
		mostrarMensaje = true;
	}
	
	public void eliminarItem(Item item) {
		items.remove(item);
	}
	
	public void eliminarItem(Items item) {
		items.remove(new Item(item));
	}
	
	public String getItemMensaje() {
		return "Añadido: " + items.get(items.size()-1).getNombre();
	}
	
	public Item getItem(Items item) {
		if(!items.isEmpty()) {			
		for(int i = 0; i<items.size();i++) {
			if(item == items.get(i).getTipo()) {
				return items.get(i);
			}
		}
		}
		
		return null;
	}
	
	
	public Item getItem(int i) {
		if(!items.isEmpty()) {			
			return items.get(i);
		}
		
		return null;
	}
	
	public Items getItemEnMano() {
		return itemEnMano.getTipo();
	}
	
	public void eliminarItemRoto() {
		if(MundoConfig.estadoJuego == EstadosDelJuego.INVENTARIO ) {
			
		for(int i=0;i<items.size();i++) {
			if(items.get(i).getUsos() == 0) {
				System.out.println("Eliminado " + items.get(i).getNombre());
				items.remove(i);
			}
		}
		}
	}
	
	public ArrayList<Mineral> obtenerTodosLosMinerales() {
	    ArrayList<Mineral> todosLosMinerales = new ArrayList<>();

	    // Iterar sobre cada tipo de mineral (TipoMinerales)
	    for (EnumMap<EstadosMinerales, ArrayList<Mineral>> estadoMap : mineralesInventario.values()) {
	        // Iterar sobre cada estado de mineral (EstadosMinerales)
	        for (ArrayList<Mineral> minerales : estadoMap.values()) {
	            // Añadir todos los minerales a la lista
	            todosLosMinerales.addAll(minerales);
	        }
	    }
	    
	    return todosLosMinerales;
	}

	
	
	public ArrayList<Mineral> obtenerMineral(TipoMinerales tipo, EstadosMinerales estado) {
	    return mineralesInventario.get(tipo).get(estado);
	}
	
	public ArrayList<Mineral> obtenerMineral(Mineral mineral) {
	    return mineralesInventario.get(mineral.tipo).get(mineral.estado);
	}

	public int contarMinerales(TipoMinerales tipo, EstadosMinerales estado) {
	    return obtenerMineral(tipo, estado).size();
	}
	
	public int contarMinerales(Mineral mineral) {
	    return obtenerMineral(mineral.tipo, mineral.estado).size();
	}

	public int contarTotalDeMinerales() {
	    int total = 0;
	    for (EnumMap<EstadosMinerales, ArrayList<Mineral>> estadoMap : mineralesInventario.values()) {
	        for (ArrayList<Mineral> listaMinerales : estadoMap.values()) {
	            total += listaMinerales.size();
	        }
	    }
	    return total;
	}

	
	public void eliminarMineral(Mineral mineral, int cantidad) {
	    ArrayList<Mineral> minerales = obtenerMineral(mineral.tipo, mineral.estado);
	    for (int i = 0; i < cantidad && !minerales.isEmpty(); i++) {
	        minerales.remove(0); // Elimina el primero de la lista
	    }
	}
	
	public void eliminarMineral(TipoMinerales tipo, EstadosMinerales estado, int cantidad) {
	    ArrayList<Mineral> minerales = obtenerMineral(tipo, estado);
	    for (int i = 0; i < cantidad && !minerales.isEmpty(); i++) {
	        minerales.remove(0); // Elimina el primero de la lista
	    }
	}
	
	
	public void agregarMineral(Mineral mineral) {
	    TipoMinerales tipo = mineral.getTipoMineral();
	    EstadosMinerales estado = mineral.getEstadoMineral();
	    if (mineralesInventario.containsKey(tipo)) {
	        EnumMap<EstadosMinerales, ArrayList<Mineral>> estadoMap = mineralesInventario.get(tipo);
	        if (estadoMap.containsKey(estado)) {
	            estadoMap.get(estado).add(mineral);
	        }
	    }
	}
	
	public void agregarMineral(Mineral mineral, int cantidad) {
	    TipoMinerales tipo = mineral.getTipoMineral();
	    EstadosMinerales estado = mineral.getEstadoMineral();
	    if (mineralesInventario.containsKey(tipo)) {
	        EnumMap<EstadosMinerales, ArrayList<Mineral>> estadoMap = mineralesInventario.get(tipo);
	        if (estadoMap.containsKey(estado)) {
	        	for(int i = 0;i<cantidad;i++) {
	        		estadoMap.get(estado).add(mineral);	        		
	        	}
	        }
	    }
	}

	/*
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
*/
	public OrthographicCamera getCamara() {
		return camara;
	}

	public void agregarMision(MisionesDelJuego misionD) {
		Mision mision = new Mision(misionD);
		tareas.put(mision.getId(), mision);
		Listeners.misionAgregada(mision);
		AudioManager.reproducirSonidoMisionRecibida();
	}
	
	public void agregarMision(Mision mision) {
		tareas.put(mision.getId(), mision);
		Listeners.misionAgregada(mision);
		AudioManager.reproducirSonidoMisionRecibida();
	}
	
	public HashMap<String, Mision> getMisiones() {
		return tareas;
		
	}
	
	public boolean buscarMisionPorId(String id) {
		if (tareas.containsKey(id)) {
			return true;
		}else {
			return false;
		}
	}
	
	public Mision conseguirMisionPorId(MisionesDelJuego mision) {
			if(!tareas.isEmpty()) {
				return tareas.get(mision.getId());
			}
		return null;
	}
	

	
	public void resetearRespuestas() {
		respuesta1 = Respuestas.NOVALOR;
		respuesta2 = Respuestas.NOVALOR;
	}

	/**
	 * Para equipar en la mano
	 * @param numero
	 */
	public void equipar(int numero) {
		itemEnMano.mostrar();
		switch (numero) {
		case 1:
			itemEnMano.setParteDelCuerpo(PartesDelCuerpo.MANO_DERECHA);
			itemEnMano.cambiarSprite(Items.PICO);
			break;

		case 2:
			itemEnMano.cambiarSprite(Items.MAZA);
			break;
		default:
			itemEnMano.borrarSprite();
			break;
		}
	}
	
	//DINERO
	
	public void desequipar() {
		itemEnMano.setTipo(Items.VACIO);
		itemEnMano.borrarSprite();
		itemEnMano.ocultar();
	}
	

	
}
