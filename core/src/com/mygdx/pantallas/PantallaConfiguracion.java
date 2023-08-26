package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Principal;
import com.mygdx.io.Entradas;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Render;

public class PantallaConfiguracion implements Screen{
	

	final Principal game;
	private Stage stage;
	private Table interfaz;
	private Label interfazTextos[];
	private Label.LabelStyle estiloLabel, tituloEstilo;
	private EstiloFuente estiloFuente;
	Entradas entradas = new Entradas();
	OrthographicCamera camara;
	
	public PantallaConfiguracion(final Principal game) {
		this.game = game;
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		Gdx.input.setInputProcessor(entradas);
		
		Render.batch = game.batch;
	}
	
	@Override
	public void show() {
		crearInterfaz();

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camara.update();
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();
		stage.draw();
		seleccionarOpcion();
		
		Render.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
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
	
	private void crearInterfaz() {
		crearEstilos();
		stage = new Stage();
		interfazTextos = new Label[3];
		
		interfaz = new Table();
		interfaz.setFillParent(true);
		interfaz.debug();
		
		interfazTextos[0] = new Label("Volver al menu principal", estiloLabel);
		interfazTextos[1] = new Label("Configuraciones", tituloEstilo);
		interfazTextos[2] = new Label("Pantalla completa: " + (Config.pantallaCompleta?"Si":"No") , estiloLabel);
		
		interfaz.add(interfazTextos[0]);
		interfaz.add(interfazTextos[1]).expand();
		interfaz.row();
		interfaz.add(interfazTextos[2]);
		
		
		stage.addActor(interfaz);
	}
	
	private void crearEstilos() {
		estiloFuente = new EstiloFuente();
		estiloLabel = estiloFuente.generarFuente(24, Colores.BLANCO, false);
		tituloEstilo = estiloFuente.generarFuente(40, Colores.BLANCO, false);
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

}
