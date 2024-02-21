package com.mygdx.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.enums.Items;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.SpriteOrdenableIndiceZ;

public abstract class Entidad {

	protected Vector2 posicion;//La necesito en las hijas
	protected Texture textura;//La necesito en las hijas
	private SpriteOrdenableIndiceZ sprite;
	protected boolean jugadorEnRango = false, apretoE = false;
	private boolean jugadorTienePico = false;//Deberia ir en mineral pero no se me ocurre como hacerlo
	private boolean comprable = false;
	private String nombre;
	protected int colisionAncho= MundoConfig.tamanoTile, colisionAlto=colisionAncho;//Por si tengo entidades mas grandes?
	protected Body body;
	protected Rectangle areaDeInteraccion;

	private int distanciaInteraccion = 20;
	
	public Entidad(float x, float y, World world, String rutaTextura) {
		x=x*MundoConfig.tamanoTile;
		y=(MundoConfig.altoMundo - y) * MundoConfig.tamanoTile;
		this.posicion = new Vector2(x,y);
		this.textura = new Texture(rutaTextura);
		sprite = new SpriteOrdenableIndiceZ(this.textura);
		OrganizadorSpritesIndiceZ.sprites.add(sprite);
		sprite.setPosition(this.posicion.x, this.posicion.y);
		
		areaDeInteraccion = new Rectangle(posicion.x - distanciaInteraccion/2, posicion.y - distanciaInteraccion/2,
                sprite.getWidth()+distanciaInteraccion, (sprite.getHeight()) + distanciaInteraccion);
		


	}
	
	public Entidad(float x, float y, boolean comprable, World world ,String rutaTextura) {
		x=x*MundoConfig.tamanoTile;
		y=(MundoConfig.altoMundo - y) * MundoConfig.tamanoTile;
		this.posicion = new Vector2(x,y);
		this.textura = new Texture(rutaTextura);
		this.comprable = comprable;
		sprite = new SpriteOrdenableIndiceZ(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);
	}
	
	public Entidad(float x, float y, boolean comprable, String rutaTextura) {
		x=x*MundoConfig.tamanoTile;
		y=(MundoConfig.altoMundo - y) * MundoConfig.tamanoTile;
		this.posicion = new Vector2(x,y);
		this.textura = new Texture(rutaTextura);
		this.comprable = comprable;
		sprite = new SpriteOrdenableIndiceZ(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);

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
        bodyDef.position.set(posicion.x+(ancho/2), posicion.y+(alto/2));

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(ancho/2, 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
	}
	
	public void draw() {
		sprite.draw(Render.batch);
//		dibujarAreaInteraccion();
	}
	
	public void dibujarAreaInteraccion() {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(Render.batch.getProjectionMatrix());
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(areaDeInteraccion.x, areaDeInteraccion.y, areaDeInteraccion.width, areaDeInteraccion.height);
		shapeRenderer.end();
	}
	
	
	public void detectarJugador(Jugador jugador) {
		buscarItemEnJugador(jugador);//Cuando el jugador esta dentro del rango de la entidad, esta busca en los items del jugador. Esto me puede servir para objetos de mision y el minado

		if(areaDeInteraccion.overlaps(jugador.areaJugador)) {		
		//		if(((jugador.getPosicion().x - this.posicion.x) < distanciaInteraccion && (jugador.getPosicion().x - this.posicion.x) > -distanciaInteraccion) && ((jugador.getPosicion().y - this.posicion.y) < distanciaInteraccion && (jugador.getPosicion().y - this.posicion.y) > -distanciaInteraccion)){	
			jugadorEnRango = true;
			if(jugador.isEPressed()) {
				apretoE = true;
			}
		}else {
			jugadorEnRango = false;
			apretoE = false;
		}
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
	
	public void buscarItemEnJugador(Jugador jugador) {//Setea los items que el jugador tiene o no tiene
		if(jugador.getItems().contains(Items.PICO)) {
			jugadorTienePico = true;
		}else {
			jugadorTienePico = false;
		}
	}
	
	public boolean buscarPorItemEnJugador(Items item) {//Para las clases hijas, sirve para saber si el jugador tiene un item en especifico
		switch (item) {
		case PICO:
			return jugadorTienePico;

		default:
			return false;
		}
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
	
	public String getNombre() {
		return nombre;
	}

	public SpriteOrdenableIndiceZ getSprite() {
		return sprite;
	}
	
}
