package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.Texto;

public class Configs implements Screen{
	
	Texto textos[];
	OrthographicCamera camara;
	private Viewport vwp;
	
	@Override
	public void show() {
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		vwp = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camara);
		
		textos = new Texto[1];
		textos[0] = new Texto(Recursos.FUENTE_TEMPORAL, 40, Color.WHITE, false);
		
		textos[0].setTexto("Configuracion");
		textos[0].setPosicion((Gdx.graphics.getWidth()/2) - (textos[0].getAncho()/2), Gdx.graphics.getHeight());
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camara.update();
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();
		
		for(int i = 0; i<textos.length; i++) {
			textos[i].dibujar();
		}
		Render.batch.end();
		
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
