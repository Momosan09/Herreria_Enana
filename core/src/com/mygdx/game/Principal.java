package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.pantallas.Juego;
import com.mygdx.pantallas.PantallaMenu;
import com.mygdx.utiles.Render;

public class Principal extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		Render.batch = batch; // nose si tendria que borrar el batch de arriba
		font = new BitmapFont();
		
	    this.setScreen(new PantallaMenu(this));
	    // Configurar la cámara
	    //jugador.camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}



	@Override
	public void render() {
	    ScreenUtils.clear(0, 0, 0, 1);
	    super.render();

	}


	
	@Override
	public void dispose () {
		super.dispose();
		Render.batch.dispose();
		font.dispose();
	}
}
