package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Jugador;
import com.mygdx.game.Principal;
import com.mygdx.hud.HUD;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Juego implements Screen{
	
	private Jugador jugador;
	private Texture jugadorTextura;
	private OrthographicCamera camaraJuego, camaraHud;
	
	private HUD hud;


	public Juego(final Principal game) {

	}

	@Override
	public void show() {
		
		//camaras
		camaraJuego = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraJuego.setToOrtho(false);
		camaraJuego.zoom = .7f;
		
		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraHud.setToOrtho(false); 
		camaraHud.zoom = .1f;
		
		//HUD
		hud = new HUD();
		
	    //render
		Render.tiledMapRenderer = new OrthogonalTiledMapRenderer(Recursos.MAPA);
		
		//jugador
		jugador = new Jugador(camaraJuego);
		

	}

	@Override
	public void render(float delta) {
	    Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	    //Renderiza el Juego
	    camaraJuego.update();
	    Render.batch.setProjectionMatrix(camaraJuego.combined);//Aca estaba el problema de que el HUD no se renderizaba por encima del mapa, los setProjectionMatrix de cada camara tienen que estar en ciclos .begin() y .end() distintos
	    Render.batch.begin();

	    Render.tiledMapRenderer.setView(camaraJuego);
	    Render.tiledMapRenderer.render();

	    jugador.draw(Render.batch);

	    Render.batch.end();

	    //Renderiza el HUD
	    camaraHud.update();
	    Render.batch.setProjectionMatrix(camaraHud.combined);//Una vez que renderiza el juego, se inicia el batch para la camara del HUD y lo dibuja
	    Render.batch.begin();

	    hud.draw(Render.batch);

	    Render.batch.end();
	}


	@Override
	public void resize(int width, int height) {
		camaraJuego.viewportWidth = width;
		camaraJuego.viewportHeight = height;
		camaraHud.viewportWidth = width;
		camaraHud.viewportHeight = height;
		camaraJuego.update();
		camaraHud.update();		
	    System.out.println("X =" +Gdx.graphics.getWidth() + "\n Y =" + Gdx.graphics.getHeight());
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
