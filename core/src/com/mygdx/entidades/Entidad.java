package com.mygdx.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.utiles.Render;

public abstract class Entidad {

	protected Vector2 posicion;//La necesito en las hijas
	protected Texture textura;//La necesito en las hijas
	private Sprite sprite;
	protected boolean jugadorEnRango = false, apretoE = false;
	private boolean jugadorTienePico = false;//Deberia ir en mineral pero no se me ocurre como hacerlo
	private boolean comprable = false;
	private String nombre;
	protected int colisionAncho=32, colisionAlto=32;//Por si tengo entidades mas grandes?
	private Rectangle colision;
	private Body body;

	private int distanciaInteraccion = 64;
	
	public Entidad(float x, float y, World world, String rutaTextura) {
		this.posicion = new Vector2(x,y);
		this.textura = new Texture(rutaTextura);
		colision = new Rectangle(posicion.x,posicion.y,colisionAncho,colisionAlto);
		sprite = new Sprite(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);
		crearCuerpo(world);
	}
	
	public Entidad(float x, float y, boolean comprable, World world ,String rutaTextura) {
		this.posicion = new Vector2(x,y);
		this.textura = new Texture(rutaTextura);
		colision = new Rectangle(x,y,textura.getWidth(),textura.getHeight());
		this.comprable = comprable;
		sprite = new Sprite(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);
		crearCuerpo(world);
	}
	
	public Entidad(float x, float y, boolean comprable,String rutaTextura) {
		this.posicion = new Vector2(x,y);
		this.textura = new Texture(rutaTextura);
		colision = new Rectangle(x,y,textura.getWidth(),textura.getHeight());
		this.comprable = comprable;
		sprite = new Sprite(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);

	}
	
	
	private void crearCuerpo(World world) {
		// Crear el cuerpo del jugador
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posicion.x+16, posicion.y+16);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32/2, 32/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
	}
	
	public void draw() {
		sprite.draw(Render.batch);
	}
	
	
	public void detectarJugador(Jugador jugador) {
		buscarItemEnJugador(jugador);//Cuando el jugador esta dentro del rango de la entidad, esta busca en los items del jugador. Esto me puede servir para objetos de mision y el minado
		if(((jugador.getPosicion().x - this.posicion.x) < distanciaInteraccion && (jugador.getPosicion().x - this.posicion.x) > -distanciaInteraccion) && ((jugador.getPosicion().y - this.posicion.y) < distanciaInteraccion && (jugador.getPosicion().y - this.posicion.y) > -distanciaInteraccion)){
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
	
}
