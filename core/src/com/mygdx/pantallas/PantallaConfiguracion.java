package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Principal;
import com.mygdx.hud.HeadUpDisplay;
import com.mygdx.io.EntradaMenu;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class PantallaConfiguracion implements Screen, HeadUpDisplay{
	

	final Principal game;
	private ScreenViewport screenViewport;
	private Stage stage;
	private Skin skin;
	private Table interfaz, pantalla;
	private Label pantallaTextos[];
	private SelectBox pantallaResolucionesSelectBox;
	private ImageButton botonVolver;
	private Label interfazTextos[];
	private Label.LabelStyle estiloLabel, tituloEstilo;
	EntradaMenu entradas = new EntradaMenu();
	OrthographicCamera camara;
	
	public PantallaConfiguracion(final Principal game) {
		this.game = game;
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		stage.setDebugAll(true);
		Gdx.input.setInputProcessor(stage);
		//Recursos.muxMenu.addProcessor(stage);
		
	}
	
	@Override
	public void show() {
		crearFuentes();
		crearActores();
		poblarStage();
	}

	@Override
	public void render(float delta) {
    	stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		//seleccionarOpcion();
		
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
	public void poblarStage() {
		crearFuentes();
		interfaz.add(botonVolver);
		interfaz.add(interfazTextos[1]).expand();
		interfaz.row();
		
		//tabla de la pantalla
		pantalla.add(pantallaTextos[0]);
		pantalla.add(pantallaResolucionesSelectBox).size(300,20);
		interfaz.add(pantalla);
		//interfaz.add(interfazTextos[2]);
		
		
		stage.addActor(interfaz);
	}
	
	
	private void seleccionarOpcion() {
		int seleccion = entradas.seleccionarOpcion(interfazTextos, 0, 2);
		
		if(seleccion == 2) {
			if(!Gdx.graphics.isFullscreen()) {
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			}
		}
	}

	@Override
	public void crearFuentes() {
		estiloLabel = EstiloFuente.generarFuente(24, Colores.BLANCO, false);
		tituloEstilo = EstiloFuente.generarFuente(40, Colores.BLANCO, false);
		skin = new Skin(Gdx.files.internal(Recursos.SELECT_BOX_SKIN));
		
	}

	@Override
	public void crearActores() {
		
		botonVolver = new ImageButton(skin);
		botonVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
    			game.setScreen(new PantallaMenu(game));
    			dispose();
            }
        });
		
		//pantalla Configs
		pantalla = new Table();
		pantallaTextos = new Label[4];
		pantallaTextos[0] = new Label(Recursos.bundle.get("pantallaConfiguracion.resolucion"), estiloLabel);
		pantallaResolucionesSelectBox = new SelectBox(skin);

		pantallaResolucionesSelectBox.setItems(Config.resolucionesString);
		
		interfazTextos = new Label[3];
		
		interfaz = new Table();
		interfaz.setFillParent(true);
//		interfaz.debug();
		
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
	

	@Override
	public void dispose() {
		Recursos.muxMenu.removeProcessor(stage);
		
	}
	

}
