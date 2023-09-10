package com.mygdx.utiles;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Recursos {
	
	
	//Menu
	public static final String FUENTE_TEMPORAL = "Fonts/Cardinal.ttf";
	public static final String MUSICA_MENU = "Audio/saplingGarden.mp3";
	public static final String EFECTO_TECLA_MENU = "Audio/sonidoEnMenu.wav";
	public static final String FONDO_MENU = "Imagen/menu/imagenfondo.png";
	
	//Jugador
	public static final String JUGADOR_TEXTURA = "Jugador/quieto_0.png";
	public static final String JUGADOR_SPRITESHEET = "Jugador/spriteSheet.png";
	
	//Mapas
	public static final TiledMap MAPA = new TmxMapLoader().load("mapas/mapa.tmx");
	
	//NPC
	public static final String VIEJO = "objetosDelMundo/npc/viejo/viejoAnimacion.png";
	public static final String VENDEDOR = "objetosDelMundo/npc/vendedor/vendedorAnimacion.png";
	
	//Objetos del mapa
	public static final String YUNQUE = "objetosDelMundo/inanimados/interactuables/yunque.png";
	
	
	//HUD
	public static final String DINERO_HUD = "HUD/Dinero.png";
	public static final String RELOJ_HUD = "HUD/hora.png";
	
}
