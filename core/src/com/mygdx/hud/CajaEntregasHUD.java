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

public class CajaEntregasHUD extends HUD{

	private Label titulo;

	
	 public CajaEntregasHUD(Jugador jugador) {
		 this.jugador= jugador;
		 construir();
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
	public void dibujar() {
		if(visible) {
	    	stage.act(Gdx.graphics.getDeltaTime());
	    	stage.draw();
	    	System.out.println(HelpDebug.debub(getClass())+"La caja de entregas");
		}
	}

}