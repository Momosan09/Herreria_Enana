package com.mygdx.utiles;

import java.util.ArrayList;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.mygdx.pantallas.Juego;
import com.mygdx.utiles.recursos.Recursos;

public class HelpMapa {

	
	private TiledMap tiledMap;
	private Juego juego;
	private Vector2 jugadorSpawn;
	private Rectangle spawnMinerales;
	private ArrayList<Vector2> posicionObjetosInteratuablesTaller;
	//El numero de las capas es el orden en el que estan en tiled, de abajo a arriba
	private int[] capasDeFondo = {0,1,2,4,5,6,7,8,9,10}, capasDeFrente= {3};//Relativo a donde el personaje deberia estar ubicado //si agrego mas capas acordarse de modificar esto
	float unitScale = 1 / 1f;


	
	public HelpMapa(Juego juego) {
		this.juego = juego;
		jugadorSpawn = new Vector2();
		spawnMinerales = new Rectangle();
		posicionObjetosInteratuablesTaller = new ArrayList<Vector2>();
	}
	
	public OrthogonalTiledMapRenderer Inicializar() {
		tiledMap = new TmxMapLoader().load(Recursos.MAPA);
		conseguirObjetosDelMapa(tiledMap.getLayers().get("spawnMinerales").getObjects());
		conseguirObjetosDelMapa(tiledMap.getLayers().get("colisiones").getObjects());
//		conseguirObjetosInteractuablesDeLaCapa((TiledMapTileLayer) tiledMap.getLayers().get("objetosTaller"));
		conseguirObjetosDeLaCapa((TiledMapTileLayer) tiledMap.getLayers().get("taller"));
		conseguirObjetosDeLaCapa((TiledMapTileLayer) tiledMap.getLayers().get("columnas"));
		conseguirObjetosDeLaCapa((TiledMapTileLayer) tiledMap.getLayers().get("pisoInterior"));
//		conseguirObjetosDeLaCapa((TiledMapTileLayer) tiledMap.getLayers().get("objetosTaller"));
		conseguirObjetosDeLaCapa((TiledMapTileLayer) tiledMap.getLayers().get("estructurasFondo"));
		conseguirObjetosDeLaCapa((TiledMapTileLayer) tiledMap.getLayers().get("habitacion"));
		conseguirObjetosDeLaCapa((TiledMapTileLayer) tiledMap.getLayers().get("habitacionDetalles"));
		conseguirObjetosDeLaCapa((TiledMapTileLayer) tiledMap.getLayers().get("sitioPiedra"));
		return new OrthogonalTiledMapRenderer(tiledMap, unitScale);
		}
	
	
	public void conseguirObjetosDelMapa(MapObjects mapObjects) {
	    for (MapObject mapObject : mapObjects) {
//	    	System.out.println(HelpDebug.debub(getClass())+"entro");
	        if (mapObject instanceof PolygonMapObject) {
	        		crearCuerposDeColision((PolygonMapObject) mapObject);	        		
	        	
	            
	        }else {
	        	
	        if(mapObject instanceof RectangleMapObject) {
	        	
	        
	        	
	        	if(mapObject.getName().equals("puntoAparicion")) {//aca todas las excepciones
	        		Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
	        		jugadorSpawn.x = rectangle.getX();
	        		jugadorSpawn.y = rectangle.getY();
	        	}
	        	
	        	if(mapObject.getName().equals("spawnArea")) {
	        		Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
	        		spawnMinerales.x = rectangle.getX();
	        		spawnMinerales.y = rectangle.getY();
	        		spawnMinerales.width = rectangle.height;//por alguna razon que no me gusta, te da la altura y el ancho al revez, por eso los puse asi
	        		spawnMinerales.height = rectangle.width;
	        	}
	        }
	        }
	        

	    }
	}
	
//	private void conseguirObjetosInteractuablesDeLaCapa(TiledMapTileLayer mapLayer) {
//	    for (int i = 0; i < mapLayer.getWidth(); i++) {
//	        for (int j = 0; j < mapLayer.getHeight(); j++) {
//	            Cell celda = mapLayer.getCell(i, j);
//	            
//	            if (celda != null && celda.getTile().getObjects().getCount() == 1) {
//					float worldY = j * mapLayer.getTileWidth();
//		            float worldX = i * mapLayer.getTileHeight();
//		            float rotacion = celda.getRotation();
//
//		            if(celda.getTile().getObjects().get(0).getName().equals("horno")) {
//		            	System.out.println("siii");
//		            }
//		            crearCuerposDeColision((PolygonMapObject) celda.getTile().getObjects().get(0), worldX, worldY, rotacion);
//		           
//
//	            }
//	        }
//	    }
//	}

	private void conseguirObjetosDeLaCapa(TiledMapTileLayer mapLayer) {
	    for (int i = 0; i < mapLayer.getWidth(); i++) {
	        for (int j = 0; j < mapLayer.getHeight(); j++) {
	            Cell celda = mapLayer.getCell(i, j);
	            
	            if (celda != null && celda.getTile().getObjects().getCount() == 1) {
					float worldY = j * mapLayer.getTileWidth();
		            float worldX = i * mapLayer.getTileHeight();
		            float rotacion = celda.getRotation();

		            crearCuerposDeColision((PolygonMapObject) celda.getTile().getObjects().get(0), worldX, worldY, rotacion);
		           

	            }
	        }
	    }
	}


	private void crearCuerposDeColision(PolygonMapObject polygonMapObject) {
	    BodyDef bodyDef = new BodyDef();
	    bodyDef.type = BodyDef.BodyType.StaticBody;
	    Body body = juego.getWorld().createBody(bodyDef);
	    Shape shape = createPolygonShape(polygonMapObject);
	    body.createFixture(shape, 1000);
	    shape.dispose();
	}
	
	private void crearCuerposDeColision(PolygonMapObject polygonMapObject, float x, float y, float rotacion) {
	    BodyDef bodyDef = new BodyDef();
	    bodyDef.type = BodyDef.BodyType.StaticBody;
	    bodyDef.angle = rotacion;
	    bodyDef.position.set(x,y);
	    Body body = juego.getWorld().createBody(bodyDef);
	    Shape shape = createPolygonShape(polygonMapObject);
	    body.createFixture(shape, 1000);
	    shape.dispose();
	}

	private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
	    float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
	    Vector2[] worldVertices = new Vector2[vertices.length / 2];

	    for (int i = 0; i < vertices.length / 2; i++) {
	        Vector2 actual = new Vector2(vertices[i * 2], vertices[i * 2 + 1]);
	        worldVertices[i] = actual;
	    }

	    PolygonShape shape = new PolygonShape();
	    shape.set(worldVertices);

	    return shape;
	}

	public Vector2 getJugadorSpawn() {
		return jugadorSpawn;
	}
	
	public Rectangle getSitioDeMinado() {
		return spawnMinerales;
	}
	
	public int[] getCapasDeFondo() {
		return capasDeFondo;
	}
	
	public int[] getCapasDeFrente() {
		return capasDeFrente;
	}
	
	public int getCantTilesAlto() {
		TiledMapTileLayer mapLayer = (TiledMapTileLayer) tiledMap.getLayers().get("base");
		int worldY = 0;
		        for (int j = 0; j < mapLayer.getHeight(); j++) {
						worldY++;
		    }
		
		return worldY-1;
	}
	
	public int getCantTilesAncho() {
		TiledMapTileLayer mapLayer = (TiledMapTileLayer) tiledMap.getLayers().get("base");
		int worldX = 0;
		        for (int j = 0; j < mapLayer.getWidth(); j++) {
						worldX++;
		    }
		
		return worldX-1;
		
	}
	
}
