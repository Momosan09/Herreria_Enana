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
import com.mygdx.utiles.recursos.Recursos;

public class MesaHUD extends HUD{

	private Label titulo;
	
	 public MesaHUD(Jugador jugador) {
		 super(jugador);
		 construir();
	 }
	

	@Override
	public void crearActores() {
		
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(true);
		
		contenedor = new Table();
		contenedor.setDebug(true);
		titulo = new Label("Mesa", labelStyle);
		
	}

	@Override
	public void poblarStage() {
		contenedor.add(titulo);
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.MESA_TEXTURA)));
		tabla.add(contenedor);
		stage.addActor(tabla);
	}

}
