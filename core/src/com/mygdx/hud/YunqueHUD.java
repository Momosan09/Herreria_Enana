package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;

public class YunqueHUD implements Ocultable, HeadUpDisplay{
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor, tabla;
	private Label titulo;
	private Label.LabelStyle labelStyle;
	private Jugador jugador;
	private boolean visible = false;
	
	 public YunqueHUD(Jugador jugador) {
		 	this.jugador= jugador;
	    	screenViewport = new ScreenViewport();
	        stage = new Stage(screenViewport);
	        
	        crearFuentes();
	        crearActores();
	        poblarStage();
	 }
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
			
	}

	@Override
	public void crearActores() {
		
		titulo = new Label("Yunque", labelStyle);
		
	}

	@Override
	public void poblarStage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reEscalar(int width, int heigth) {
    	screenViewport.update(width, heigth, true);
		
	}
	
	@Override
	public void render() {
		if(visible) {
	    	stage.act(Gdx.graphics.getDeltaTime());
	    	stage.draw();
	    	System.out.println(HelpDebug.debub(getClass())+"el yunque");
		}
	}

	@Override
	public void mostrar() {
		visible = true;
		
	}

	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
	}
	
	public Stage getStage() {
		return stage;
	}

	@Override
	public boolean getVisible() {
		return visible;
	}

}
