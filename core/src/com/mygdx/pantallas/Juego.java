package com.mygdx.pantallas;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Jugador;
import com.mygdx.game.Principal;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Juego implements Screen{
	
	Jugador jugador;
	Texture jugadorTextura;
	

	public Juego(final Principal game) {
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		jugadorTextura = new Texture(Recursos.JUGADOR_TEXTURA);
		jugador = new Jugador(jugadorTextura);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Render.batch.begin();
		jugador.draw(Render.batch);
		Render.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
