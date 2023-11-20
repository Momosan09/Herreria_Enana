package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.pantallas.Juego;
import com.mygdx.utiles.Render;
import com.mygdx.red.UtilesRed;

public class Principal extends Game {
	public SpriteBatch batch;
	public BitmapFont font;


	
	@Override
	public void create() {
		batch = new SpriteBatch();
		Render.batch = batch;
		font = new BitmapFont();

		this.setScreen(new Juego(this));


	}

	@Override
	public void render() {
	    ScreenUtils.clear(0, 0, 0, 1);
	    super.render();
	}


	
	@Override
	public void dispose () {
		UtilesRed.hs.fin();
		super.dispose();
		Render.batch.dispose();
		font.dispose();
	}
}
