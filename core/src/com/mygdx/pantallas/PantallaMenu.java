package com.mygdx.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Principal;

public class PantallaMenu implements Screen{

	OrthographicCamera camara;
	
	final Principal game;
	
	public PantallaMenu(final Principal game) { //No termine de entender bien el parametro que le paso al constructor https://libgdx.com/wiki/start/simple-game-extended
		this.game = game;
		this.resize(1900, 1000);
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camara.update();
		game.batch.setProjectionMatrix(camara.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Herreria Enana", 2, 100);
		game.font.draw(game.batch, "Menu", 0, 0);
		game.font.draw(game.batch, "Toca cualquier tecla para continuar", 100, 150);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new Juego(game));
			dispose();
		}
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
