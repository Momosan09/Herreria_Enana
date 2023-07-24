package com.mygdx.pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Principal;
import com.mygdx.io.Entradas;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.Texto;

public class PantallaMenu implements Screen {

	OrthographicCamera camara;
	final Principal game;
	private Viewport vwp;
	Entradas entradas = new Entradas();

	private Texto[] textos;
	private Music musicaMenu;

	private int cont = 2;

	public PantallaMenu(final Principal game) {
		this.game = game;
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		vwp = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camara);

		Gdx.input.setInputProcessor(entradas);

		// Inicializar la instancia de SpriteBatch en Render con la del juego
		Render.batch = game.batch;

	}

	@Override
	public void show() {
		textos = new Texto[5];
		textos[0] = new Texto(Recursos.FUENTE_TEMPORAL, 42, Color.WHITE, false);
		textos[1] = new Texto(Recursos.FUENTE_TEMPORAL, 32, Color.WHITE, false);
		textos[2] = new Texto(Recursos.FUENTE_TEMPORAL, 32, Color.WHITE, false);
		textos[3] = new Texto(Recursos.FUENTE_TEMPORAL, 32, Color.WHITE, false);
		textos[4] = new Texto(Recursos.FUENTE_TEMPORAL, 30, Color.SALMON, false);

		musicaMenu = Gdx.audio.newMusic(Gdx.files.internal(Recursos.MUSICA_MENU));

		textos[0].setTexto("Herreria Enana");
		textos[0].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[0].getAncho() / 2), Gdx.graphics.getHeight());

		textos[1].setTexto("Menu Principal");
		textos[1].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[1].getAncho() / 2),
				(Gdx.graphics.getHeight()) - (textos[1].getAlto() * 2));

		textos[2].setTexto("Jugar");
		textos[2].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[2].getAncho() / 2), 250);

		textos[3].setTexto("Configuracion");
		textos[3].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[3].getAncho() / 2), 200);

		textos[4].setTexto("El videojuego de herreria por combinacion");
		textos[4].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[4].getAncho() / 2), textos[4].getAlto() * 2);

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		musicaMenu.setLooping(true);
		musicaMenu.play();

		camara.update();
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();

		for (int i = 0; i < textos.length; i++) {
			textos[i].dibujar();
		}
		Render.batch.end();
		seleccionarOpcion();
		// entradas.seleccionarOpcion(textos,2,3);

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
		game.font.dispose();
		entradas.efectoSonidoTeclas.dispose();
		musicaMenu.pause();
		musicaMenu.dispose();

	}

	private void seleccionarOpcion() {

		int seleccion = entradas.seleccionarOpcion(textos, 2, 3);

		if (seleccion == 2) {
			game.setScreen(new Juego(game));
			dispose();
		} else if (seleccion == 3) {
			game.setScreen(new Configs(game));
			dispose();

		}
		if (seleccion == -1) {

		}
	}

}
