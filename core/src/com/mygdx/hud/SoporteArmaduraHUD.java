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

public class SoporteArmaduraHUD extends HUD{


	private Label titulo;
	
	 public SoporteArmaduraHUD(Jugador jugador) {
		 super(jugador);
		 construir();
	 }
	

	@Override
	public void crearActores() {
		
		titulo = new Label("Soporte Armadura", labelStyle);
		
	}

	@Override
	public void poblarStage() {
		// TODO Auto-generated method stub
		
	}

}
