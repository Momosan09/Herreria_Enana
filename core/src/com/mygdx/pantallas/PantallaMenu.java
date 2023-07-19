package com.mygdx.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Principal;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.Texto;

public class PantallaMenu implements Screen {

    OrthographicCamera camara;
    final Principal game;
    private Viewport vwp;
    Texto t;

    public PantallaMenu(final Principal game) {
        this.game = game;
        camara = new OrthographicCamera();
        camara.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        vwp = new ExtendViewport(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, camara);
        
        // Inicializar la instancia de SpriteBatch en Render con la del juego
        Render.batch = game.batch;
    }

    @Override
    public void show() {
        t = new Texto(Recursos.FUENTE_TEMPORAL, 40, Color.WHITE, false);
        t.setTexto("Herreria Enana");
        t.setPosicion((Gdx.graphics.getWidth()/2) - t.getAncho(), 200); //arreglar la puta madre
 
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camara.update();
        Render.batch.setProjectionMatrix(camara.combined);
        Render.batch.begin();

        t.dibujar(); // Dibujar el texto utilizando la misma instancia de SpriteBatch

        Render.batch.end();

        if (Gdx.input.isKeyPressed(-1)) { // "-1" = cualquier tecla
            game.setScreen(new Juego(game));
            dispose();
        }
    }

	@Override
	public void resize(int width, int height) {
		vwp.update(width, height);
		System.out.println(Gdx.graphics.getWidth());


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
		game.font.dispose();
		
	}

}
