package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Jugador {

	private Sprite sprite;
	private Vector2 posicion;
	private float velocidad = 100f;
	

	private Texture[] texturas;
	public OrthographicCamera camara;
	
	public Jugador(Texture tex, OrthographicCamera camara){
		
		sprite = new Sprite(tex);
		posicion = new Vector2(Gdx.graphics.getWidth()/2 - (sprite.getWidth()/2), Gdx.graphics.getHeight()/2 - (sprite.getHeight()/2));	//posicion inicial
		sprite.setPosition(posicion.x, posicion.y);

		texturas = new Texture[20];
		cargarTexturas();
		
		this.camara = camara;

	}
	
	public void draw(SpriteBatch batch) {
	    sprite.draw(batch);

	    update();
	}



	
	public void update() {
		movimiento(Gdx.graphics.getDeltaTime());
	}
	
	public void movimiento(float deltaTime) { //tendria que usar la clase Entradas para esto?
		if(Gdx.input.isKeyPressed(Keys.W)) {
			posicion.y += velocidad*deltaTime;
			alternarSprites("arriba", deltaTime);
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
			posicion.y -= velocidad*deltaTime;
			alternarSprites("abajo", deltaTime);
		}
		if(Gdx.input.isKeyPressed(Keys.A)) {
			posicion.x -= velocidad*deltaTime;
			alternarSprites("izq", deltaTime);
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			posicion.x += velocidad*deltaTime;
			alternarSprites("der", deltaTime);
		}
		//alternarSprites("quieto", deltaTime);
		sprite.setPosition(posicion.x, posicion.y);//actualizar posicion del sprite
		movimientoCamara();
	}
	
	public void movimientoCamara() {
	    camara.position.set(posicion.x + sprite.getWidth() / 2, posicion.y + sprite.getHeight() / 2, 0);
	    camara.update();

	}
	
	
	public void cargarTexturas() {
		String aux = "Jugador/";
		for(int i = 0; i < texturas.length;i++) {
			if(i >= 0 && i <= 3) {
				int j = 0;
				texturas[i] = new Texture(aux + "quieto_"+j+".png");
				j++;
			}
			if(i > 3 && i <= 7) {
				int j = 0;
				texturas[i] = new Texture(aux + "arriba_"+j+".png");
				j++;
			}
			if(i>7 && i<= 11) {
				int j = 0;
				texturas[i] = new Texture(aux + "abajo_"+j+".png");
				j++;
			}
			if(i>11 && i<= 15) {
				int j = 0;
				texturas[i] = new Texture(aux + "izquierda_"+j+".png");
				j++;
			}
			if(i>15 && i<= 19) {
				int j = 0;
				texturas[i] = new Texture(aux + "derecha_"+j+".png");	
				j++;
			}
		}

	}
	public void alternarSprites(String direccion, float deltaTime) {
		float tiempo = 0f;
		switch (direccion) {
		case "quieto":
			sprite.setTexture(texturas[0]);
			/*
			sprite.setTexture(texturas[1]);
			sprite.setTexture(texturas[2]);
			sprite.setTexture(texturas[3]);
			*/
			break;

		case "arriba":
			sprite.setTexture(texturas[4]);
			/*
			sprite.setTexture(texturas[5]);
			sprite.setTexture(texturas[6]);
			sprite.setTexture(texturas[7]);
			*/
			break;
		case "abajo":
			sprite.setTexture(texturas[8]);
			/*
			sprite.setTexture(texturas[9]);
			sprite.setTexture(texturas[10]);
			sprite.setTexture(texturas[11]);
			*/
			break;
			
		case "izq":
			sprite.setTexture(texturas[12]);
			/*
			sprite.setTexture(texturas[13]);
			sprite.setTexture(texturas[14]);
			sprite.setTexture(texturas[15]);
			*/
			break;
		
		case "der":
			sprite.setTexture(texturas[16]);
			/*
			sprite.setTexture(texturas[17]);
			sprite.setTexture(texturas[18]);
			sprite.setTexture(texturas[19]);
			*/
			break;
		}
	}
}
