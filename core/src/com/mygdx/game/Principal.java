package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Principal extends ApplicationAdapter {
	SpriteBatch batch;
	Texture jugadorT;
	
	Jugador jugador;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		jugadorT = new Texture("Jugador/quieto_0.png");
		jugador = new Jugador(jugadorT);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		jugador.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
