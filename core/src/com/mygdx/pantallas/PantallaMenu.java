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
import com.mygdx.utiles.Config;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.Texto;

public class PantallaMenu implements Screen {

	OrthographicCamera camara;
	final Principal game;
	private Viewport vwp;

	private Texto titulo, textoDelMenu, tocaCualquierLetra;
	private Music musicaMenu;
	private Sound efectoSonidoTeclas;
	private boolean isSoundPlaying = false;
	private boolean isSoundPlayed = false;
	
	public PantallaMenu(final Principal game) {
		this.game = game;
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		vwp = new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camara);

		// Inicializar la instancia de SpriteBatch en Render con la del juego
		Render.batch = game.batch;

	}

	@Override
	public void show() {
		titulo = new Texto(Recursos.FUENTE_TEMPORAL, 40, Color.WHITE, false);
		textoDelMenu = new Texto(Recursos.FUENTE_TEMPORAL, 32, Color.WHITE, false);
		tocaCualquierLetra = new Texto(Recursos.FUENTE_TEMPORAL, 30, Color.YELLOW, false);

		musicaMenu = Gdx.audio.newMusic(Gdx.files.internal(Recursos.MUSICA_MENU_TEMPORAL));
		efectoSonidoTeclas = Gdx.audio.newSound(Gdx.files.internal(Recursos.EFECTO_TECLA_MENU));

		titulo.setTexto("Herreria Enana");
		titulo.setPosicion((Gdx.graphics.getWidth() / 2) - (titulo.getAncho() / 2),
				(Gdx.graphics.getHeight()) - (titulo.getAlto() * 2)); // arreglado, por alguna razon estaba dividiendo
																		// por 2 los parametros que le paso a la camara
																		// y al viewport y por eso pasaba cualquier cosa

		textoDelMenu.setTexto("Menu Principal");
		textoDelMenu.setPosicion((Gdx.graphics.getWidth() / 2) - (textoDelMenu.getAncho() / 2),
				titulo.getPosicionY() - (textoDelMenu.getAlto() * 2));

		tocaCualquierLetra.setTexto("Presione la tecla Enter para continuar");
		tocaCualquierLetra.setPosicion((Gdx.graphics.getWidth() / 2) - (tocaCualquierLetra.getAncho() / 2),
				tocaCualquierLetra.getAlto() * 2);

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		musicaMenu.setLooping(true);
		musicaMenu.play();

		camara.update();
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();

		titulo.dibujar(); // Dibujar el texto utilizando la misma instancia de SpriteBatch
		textoDelMenu.dibujar();
		tocaCualquierLetra.dibujar();
		Render.batch.end();


	    // Reproducir el efecto de sonido si se ha presionado alguna tecla
	    reproducirEfectoSonido();

	    // Verificar si se ha presionado la tecla Enter para cambiar la pantalla
	    if (Gdx.input.isKeyPressed(Keys.ENTER)) {
	        if(!isSoundPlayed) {
	        	reproducirEfectoSonido(); // Reproducir el efecto de sonido antes de cambiar la pantalla	     
	        	isSoundPlayed = true;
	        }else {
	        	game.setScreen(new Juego(game));
	        	dispose();	        	
	        }//NO ANDA
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
		game.font.dispose();
		musicaMenu.pause();
		musicaMenu.dispose();
		efectoSonidoTeclas.dispose();

	}
	
	private void reproducirEfectoSonido() {
        if (Gdx.input.isKeyPressed(-1)) { // "-1" = cualquier tecla  - cambiar por las teclas que defina despues
            if (!isSoundPlaying) {
                efectoSonidoTeclas.play();
                isSoundPlaying = true;
            }
        } else {
            isSoundPlaying = false;
        }
	}

}
