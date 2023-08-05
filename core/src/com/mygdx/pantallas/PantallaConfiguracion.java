package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Principal;
import com.mygdx.io.Entradas;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.Texto;

public class PantallaConfiguracion implements Screen{
	
	Entradas entradas = new Entradas();
	final Principal game;
	Texto textos[];
	OrthographicCamera camara;
	private Viewport vwp;
	
	public PantallaConfiguracion(final Principal game) {
		this.game = game;
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		vwp = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camara);
		
		Gdx.input.setInputProcessor(entradas);
		
		Render.batch = game.batch;
	}
	
	@Override
	public void show() {

		textos = new Texto[3];
		textos[0] = new Texto(Recursos.FUENTE_TEMPORAL, 40, Color.WHITE, false);
		
		textos[0].setTexto("Configuracion");
		textos[0].setPosicion((Gdx.graphics.getWidth()/2) - (textos[0].getAncho()/2), Gdx.graphics.getHeight());
		
		textos[1] = new Texto(Recursos.FUENTE_TEMPORAL, 24, Color.WHITE, false);
		textos[1].setTexto("Volver a menu principal");
		textos[1].setPosicion(10, Gdx.graphics.getHeight());
		
		textos[2] = new Texto(Recursos.FUENTE_TEMPORAL, 24, Color.WHITE,false);
		textos[2].setTexto("Pantalla Completa");
		textos[2].setPosicion(20, (Config.alto/2) + textos[2].getAlto()*6);
		
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
		seleccionarOpcion();
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
	
	public void seleccionarOpcion() {
		int seleccion = entradas.seleccionarOpcion(textos, 1, 2);
		
		if(seleccion == 1) {
			game.setScreen(new PantallaMenu(game));
			dispose();
		}
		/*if(seleccion == 2) {
			String aux;
			aux = (Config.pantallaCompleta)? " Si": " No";
			textos[2].setTexto("Pantalla completa" + aux);
		}*/
	}

}
