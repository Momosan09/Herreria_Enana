package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.entidades.Npc;
import com.mygdx.enums.EstadosDelJuego;

public abstract class Config {

	//persistente
	public static Preferences prefs = Gdx.app.getPreferences("HerreriaEnanaPreferencias");
	public static float volumenMusica = 100;
	
	public static float ancho =  1280;
	public static float alto = 768;
	
	public static boolean pantallaCompleta = Gdx.graphics.isFullscreen();
	
	public static String[] resolucionesString= {"1280 x 720", "1920 x 1080"};
}
