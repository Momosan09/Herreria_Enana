package com.mygdx.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.enums.Items;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;
import com.mygdx.utiles.Render;

public abstract class Entidad {

	protected Vector2 posicion;//La necesito en las hijas
	protected Texture textura;//La necesito en las hijas

	protected boolean jugadorEnRango = false;
	protected Body body;
	protected Circle areaDeInteraccion;
	protected Sprite sprite;
	protected int indiceZ;
	private boolean comprable = false;
	private int distanciaInteraccion = 20;

	
	public Entidad(float x, float y, World world, String rutaTextura) {
		x=x*MundoConfig.tamanoTile;
		y=(MundoConfig.altoMundo - y) * MundoConfig.tamanoTile;
		this.posicion = new Vector2(x,y);
		this.textura = new Texture(rutaTextura);
		
		areaDeInteraccion = new Circle(posicion.x+MundoConfig.tamanoTile/2 , posicion.y+MundoConfig.tamanoTile/2,
				MundoConfig.tamanoTile/1.3f);
	}
	
	public Entidad(float x, float y, boolean comprable, World world ,String rutaTextura) {
		x=x*MundoConfig.tamanoTile;
		y=(MundoConfig.altoMundo - y) * MundoConfig.tamanoTile;
		this.posicion = new Vector2(x,y);
		this.textura = new Texture(rutaTextura);
		this.comprable = comprable;
//		sprite = new SpriteOrdenableIndiceZ(this.textura);
//		sprite.setPosition(this.posicion.x, this.posicion.y);
	}
	
	public Entidad(boolean comprable, String rutaTextura) {
		this.textura = new Texture(rutaTextura);
		this.comprable = comprable;
//		sprite = new SpriteOrdenableIndiceZ(this.textura);
//		sprite.setPosition(this.posicion.x, this.posicion.y);

	}
	
	
	public Entidad(float x, float y, String rutaTextura) {
		this.posicion = new Vector2(x,y);
		this.textura = new Texture(rutaTextura);
		this.sprite = new Sprite(textura);
		areaDeInteraccion = new Circle(posicion.x+MundoConfig.tamanoTile/2 , posicion.y+MundoConfig.tamanoTile/2,
				MundoConfig.tamanoTile/1.3f);
	}
	public Entidad(String rutaTextura) {
		this.textura = new Texture(rutaTextura);
		this.sprite = new Sprite(textura);
	}

	protected void crearCuerpo(World world) {// cuerpos basicos por defecto
		// Crear el cuerpo del jugador
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posicion.x, posicion.y);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32/2, 32/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
	}
	
	protected void crearCuerpo(World world, float ancho, float alto) { //esta me permite hacer cuerpos con distintos tamaños
		// Crear el cuerpo del jugador
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posicion.x, posicion.y-alto);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(ancho, alto);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
	}
	
	public void draw() {
		sprite.draw(Render.batch);
//		dibujarAreaInteraccion();
	}
	
	
	public void dibujarAreasInteraccion() {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(Render.batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(areaDeInteraccion.x, areaDeInteraccion.y, areaDeInteraccion.radius);
		shapeRenderer.end();
	}
	
	public void dibujarAreasInteraccion(Circle c, String color) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(Render.batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.valueOf(color));
		shapeRenderer.circle(c.x+c.radius/4, c.y+c.radius/4, c.radius);
		shapeRenderer.end();
	}
	
	public void dibujarAreasInteraccion(Rectangle r, String color) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(Render.batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.valueOf(color));
		shapeRenderer.rect(r.x, r.y, r.width, r.height);
		shapeRenderer.end();
	}
	
	
	public void detectarJugador(Jugador jugador) {
		if(areaDeInteraccion.overlaps(jugador.areaJugador)) {		
		//		if(((jugador.getPosicion().x - this.posicion.x) < distanciaInteraccion && (jugador.getPosicion().x - this.posicion.x) > -distanciaInteraccion) && ((jugador.getPosicion().y - this.posicion.y) < distanciaInteraccion && (jugador.getPosicion().y - this.posicion.y) > -distanciaInteraccion)){	
			jugadorEnRango = true;
		}else {
			jugadorEnRango = false;
			
		}
		
		indiceZ = (posicion.y > jugador.getPosicion().y-16 ? 0 : 1);//para el auto sorting de sprites
		
	}
	
	public boolean getJugadorEnRango() {
		return jugadorEnRango;
	}
	
	public Vector2 getPosicion() {
		return posicion;
	}
	
	public int getDistanciaInteraccion() {
		return distanciaInteraccion;
	}
	
	
	public Texture getTextura() {
		return textura;
	}
	
	public boolean isComprable() {
		return comprable;
	}
	
	public Body getBody() {
		return body;
	}
	

	public int getIndiceZ() {
		return indiceZ;
	}
	
	public void dispose() {
		textura.dispose();
	}
	
//	public SpriteOrdenableIndiceZ getSprite() {
//		return sprite;
//	}
	
}
