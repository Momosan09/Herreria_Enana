package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Principal;
import com.mygdx.hud.HeadUpDisplay;
import com.mygdx.io.Entradas;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class PantallaConfiguracion implements Screen, HeadUpDisplay{
	

	final Principal game;
	private ScreenViewport screenViewport;
	private Stage stage;
	private Table interfaz;
	private Label interfazTextos[];
	private Label.LabelStyle estiloLabel, tituloEstilo;
	private EstiloFuente estiloFuente;
	Entradas entradas = new Entradas();
	OrthographicCamera camara;
	
	public PantallaConfiguracion(final Principal game) {
		this.game = game;
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		
		Gdx.input.setInputProcessor(entradas);
		
	}
	
	@Override
	public void show() {
		crearFuentes();
		crearActores();
		poblarStage();
	}

	@Override
	public void render(float delta) {
		stage.draw();
		seleccionarOpcion();
		
	}

	@Override
	public void resize(int width, int height) {
		screenViewport.update(width, height, true);
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
	
	@Override
	public void poblarStage() {
		crearFuentes();

		interfaz.add(interfazTextos[0]);
		interfaz.add(interfazTextos[1]).expand();
		interfaz.row();
		interfaz.add(interfazTextos[2]);
		
		
		stage.addActor(interfaz);
	}
	
	
	private void seleccionarOpcion() {
		int seleccion = entradas.seleccionarOpcion(interfazTextos, 0, 2);
		
		if(seleccion == 0) {
			game.setScreen(new PantallaMenu(game));
			dispose();
		}
		if(seleccion == 2) {
			if(!Gdx.graphics.isFullscreen()) {
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			}
		}
	}

	@Override
	public void crearFuentes() {
		estiloFuente = new EstiloFuente();
		estiloLabel = estiloFuente.generarFuente(24, Colores.BLANCO, false);
		tituloEstilo = estiloFuente.generarFuente(40, Colores.BLANCO, false);
		
	}

	@Override
	public void crearActores() {
		interfazTextos = new Label[3];
		
		interfaz = new Table();
		interfaz.setFillParent(true);
		interfaz.debug();
		
		interfazTextos[0] = new Label(Recursos.bundle.get("pantallaConfiguracion.volverMenuPrincipal"), estiloLabel);
		interfazTextos[1] = new Label(Recursos.bundle.get("pantallaConfiguracion.titulo"), tituloEstilo);
		interfazTextos[2] = new Label(Recursos.bundle.get("pantallaConfiguracion.pantallaCompleta")+" "+ (Config.pantallaCompleta?Recursos.bundle.get("si"):Recursos.bundle.get("no")) , estiloLabel);
		
	}


	@Override
	public void reEscalar(int width, int heigth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
