package com.mygdx.utiles;

import java.util.Arrays;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.mygdx.pantallas.Juego;

public class HelpMapa {

	
	private TiledMap tiledMap;
	private Juego juego;
	private Vector2 jugadorSpawn;
	
	
	public HelpMapa(Juego juego) {
		this.juego = juego;
		jugadorSpawn = new Vector2();
	}
	
	public OrthogonalTiledMapRenderer Inicializar() {
		tiledMap = new TmxMapLoader().load(Recursos.MAPA);
		conseguirObjetosDelMapa(tiledMap.getLayers().get("colisiones").getObjects());
		return new OrthogonalTiledMapRenderer(tiledMap);
		}
	
	
	public void conseguirObjetosDelMapa(MapObjects mapObjects) {
	    for (MapObject mapObject : mapObjects) {
	        if (mapObject instanceof PolygonMapObject) {
	            crearCuerposDeColision((PolygonMapObject) mapObject);
	        }
	        if(mapObject instanceof RectangleMapObject) {
	        	Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
	        	String rectangleName = mapObject.getName();
	        	
	        	if(rectangleName.equals("puntoAparicion")) {
	        		jugadorSpawn.x = rectangle.getX();
	        		jugadorSpawn.y = rectangle.getY();
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
	
}
