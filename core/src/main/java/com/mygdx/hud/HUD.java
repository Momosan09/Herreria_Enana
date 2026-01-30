package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.recursos.Recursos;

public abstract class HUD {

	
	protected Stage stage;
	protected ScreenViewport screenViewport;
	protected Table tabla, contenedor;
	protected Button cerrarBtn;
	protected Skin skin;
	
	protected Label.LabelStyle labelStyle;
	protected boolean visible = false;
	
	protected Jugador jugador;
	
	public HUD() {
	}
	
	public HUD(Jugador j) {
		this.jugador = j;
	}
	
    protected final void construir() {
		skin = new Skin(Gdx.files.internal(Recursos.hud.SKIN));
        screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);

        crearFuentes();
        crearActoresBase();
        crearActores();     // hook
        poblarStage();      // hook
    }

	protected void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
	}
	
	protected void crearFuentes(int tamañoLetra) {
		labelStyle = EstiloFuente.generarFuente(tamañoLetra, Colores.BLANCO, false);
	}
	
    private void crearActoresBase() {
        tabla = new Table();
        tabla.setFillParent(true);

        contenedor = new Table();
        
        cerrarBtn = new Button(skin);
        cerrarBtn.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ocultar();
			}
		});
    }
	
	protected void crearActores() {	
		
	}
	
	protected void poblarStage() {

	}
	
	protected void dibujar() {
		if(visible) {
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
		}
	}
	
	protected void reEscalar(int width, int heigth) {
		screenViewport.update(width, heigth, true);//actualizamos cuando la ventana se reescala
	}
	
	/**
	 * Este metodo queda libre, se ejecuta una sola vez al mostrar. Esta para editar en cada caso
	 */
	protected void inicializar() {
		
	}
	
	protected void mostrar() {
		if(visible == false) {
			inicializar();
			   stage.getRoot().setTouchable(Touchable.enabled);
			visible = true;
		}
	}
	
	protected void ocultar() {
		if(visible == true) {
			visible = false;
			MundoConfig.estadoJuego = EstadosDelJuego.JUEGO;
			   stage.getRoot().setTouchable(Touchable.disabled);
			stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
		}
	}
	

	
	protected Stage getStage() {
		return stage;
	}
	
	protected boolean getVisible() {
		return visible;
	}
	
	protected void dispose() {
		stage.dispose();
	}
	
	
}
