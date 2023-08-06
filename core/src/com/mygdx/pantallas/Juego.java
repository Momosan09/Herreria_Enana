package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Jugador;
import com.mygdx.game.Principal;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Juego implements Screen{
	
	private Jugador jugador;
	private Texture jugadorTextura;
	private OrthographicCamera camara;
	private Animator animator;


	public Juego(final Principal game) {

	}

	@Override
	public void show() {
		//camara
	    camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    camara.setToOrtho(false);
	 
	    //render
		Render.tiledMapRenderer = new OrthogonalTiledMapRenderer(Recursos.MAPA);
		
		//jugador
		jugadorTextura = new Texture(Recursos.JUGADOR_TEXTURA);
		jugador = new Jugador(jugadorTextura, camara);
		
	    animator = new Animator(Recursos.JUGADOR_SPRITESHEET, jugador.getPosicion()); // Aquí puedes pasar la posición del jugador
	    animator.create();
		
		

	}

	@Override
	public void render(float delta) {
		
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();
		
		//mapa
		Render.tiledMapRenderer.setView(camara);
		Render.tiledMapRenderer.render();
		
		animator.render();
		
		jugador.draw(Render.batch);
		Render.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camara.viewportWidth = width;
		camara.viewportHeight = height;
		camara.update();
		
	}

	@Override
	public void pause() {

		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void dispose() {
		Render.tiledMapRenderer.dispose();
		Recursos.MAPA.dispose();
		
	}
}
