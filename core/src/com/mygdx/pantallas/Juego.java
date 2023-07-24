package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Jugador;
import com.mygdx.game.Principal;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Juego implements Screen{
	
	Jugador jugador;
	Texture jugadorTextura;
	public OrthographicCamera camara;
	public  OrthogonalTiledMapRenderer renderer;

	public Juego(final Principal game) {

	}

	@Override
	public void show() {
	    camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    camara.setToOrtho(false);
	 
	    
		renderer = new OrthogonalTiledMapRenderer(Recursos.MAPA);
		
		jugadorTextura = new Texture(Recursos.JUGADOR_TEXTURA);
		jugador = new Jugador(jugadorTextura, camara);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();
		renderer.setView(camara);
		renderer.render();
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
