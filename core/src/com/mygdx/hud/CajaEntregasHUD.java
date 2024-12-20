package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;

public class CajaEntregasHUD implements HeadUpDisplay, Ocultable{

	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor, tabla;
	private Label titulo;
	private Label.LabelStyle labelStyle;
	private Jugador jugador;
	private boolean visible = false;
	
	 public CajaEntregasHUD(Jugador jugador) {
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
		
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(true);
		
		contenedor = new Table();
		contenedor.setDebug(true);
		titulo = new Label("Caja Entregas", labelStyle);
		
	}

	@Override
	public void poblarStage() {
		contenedor.add(titulo);
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.CAJA_ENTREGAS_TEXTURA)));
		tabla.add(contenedor);
		stage.addActor(tabla);
		
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
	    	System.out.println(HelpDebug.debub(getClass())+"La caja de entregas");
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