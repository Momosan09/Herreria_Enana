package com.mygdx.utiles;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Recursos {
	
	
	//Menu
	public static final String FUENTE_TEMPORAL = "Fonts/Cardinal.ttf";
	public static final String MUSICA_MENU = "Audio/saplingGarden.mp3";
	public static final String EFECTO_TECLA_MENU = "Audio/sonidoEnMenu.wav";
	public static final String FONDO_MENU = "imagen/menu/imagenfondo.png";
	
	//Jugador
	public static final String JUGADOR_TEXTURA = "Jugador/quieto_0.png";
	
	//Mapas
	public static final TiledMap MAPA = new TmxMapLoader().load("mapas/mapa.tmx");
	
}
