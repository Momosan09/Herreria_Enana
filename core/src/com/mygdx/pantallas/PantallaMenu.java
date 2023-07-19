package com.mygdx.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Principal;

public class PantallaMenu implements Screen{

	OrthographicCamera camara;
	
	final Principal game;
	
	
	private Viewport vwp;

	public PantallaMenu(final Principal game) { //No termine de entender bien el parametro que le paso al constructor https://libgdx.com/wiki/start/simple-game-extended
		this.game = game;
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		vwp = new ExtendViewport(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, camara);
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
		

		game.font.draw(game.batch, "Herreria Enana", 120, 200);
		game.font.draw(game.batch, "Menu", 150, 150);
		game.font.draw(game.batch, "Toca cualquier tecla para continuar", 50, 50);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new Juego(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		vwp.update(width, height);

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
