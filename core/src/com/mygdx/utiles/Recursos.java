package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.I18NBundle;

public abstract class Recursos {
	
	public static I18NBundle bundle = I18NBundle.createBundle(Gdx.files.internal("locale/locale"));
	
	//Box2d
	public static final float PPM = 32.0f;//Pixeles por metro
	
	//Menu
	public static final String FUENTE_TEMPORAL = "Fonts/Cardinal.ttf";
	public static final String MUSICA_MENU = "Audio/saplingGarden.mp3";
	public static final String EFECTO_TECLA_MENU = "Audio/sonidoEnMenu.wav";
	public static final String FONDO_MENU = "Imagen/menu/imagenfondo.png";
	
	//Jugador
	public static final String JUGADOR_TEXTURA = "Jugador/quieto_0.png";
	public static final String JUGADOR1_SPRITESHEET = "Jugador/jugador_1_SpriteSheet.png";
	public static final String JUGADOR2_SPRITESHEET = "Jugador/jugador_2_SpriteSheet.png";
	
	//Items
	public static final String PICO_DER = "Items/picoDer.png";
	public static final String PICO_IZ = "Items/picoIz.png";
	
	//Mapas
	public static final String MAPA = "mapas/mapa.tmx";
	
	//NPC
	public static final String VIEJO = "objetosDelMundo/npc/viejo/viejoAnimacion.png";
	public static final String VENDEDOR_AMBULANTE = "objetosDelMundo/npc/vendedor/vendedorAnimacion.png";
	public static final String VENDEDOR_TIENDA = "objetosDelMundo/npc/tiendaNpc/tiendaNpcAnimacion.png";
	
	//NPC PORTRAITS
	public static final String VENDEDOR_AMBULANTE_PORTRAIT = "objetosDelMundo/npc/vendedor/vendedorPortrait.png";
	public static final String VENDEDOR_TIENDA_PORTRAIT = "objetosDelMundo/npc/tiendaNpc/tiendaNpcPortrait.png";
	
	//Objetos del mapa
	public static final String YUNQUE = "objetosDelMundo/inanimados/interactuables/yunque.png";
	public static final String PIEDRA = "objetosDelMundo/inanimados/interactuables/piedra.png";
	public static final String HIERRO = "objetosDelMundo/inanimados/interactuables/hierro.png";
	public static final String VACIO = "objetosDelMundo/inanimados/vacio.png";
	public static final String HORNO = "objetosDelMundo/inanimados/interactuables/horno.png";
	public static final String LINGOTE_HIERRO = "objetosDelMundo/inanimados/hierroLingote.png";
	
	//HUD
	public static final String DINERO_HUD = "HUD/Dinero.png";
	public static final String RELOJ_HUD = "HUD/hora.png";
	public static final String CUADRO_HUD = "HUD/cuadro.png";
	public static final String SKIN = "HUD/skin/skin.json";
	public static final String SKIN_BOTON_ARRIBA = "HUD/skin/botonArriba.json";
	public static final String SKIN_BOTON_ABAJO = "HUD/skin/botonAbajo.json";
	
	//HUD fondos
	public static final String CARTA_TEXTURA = "Imagen/juego/carta/carta.png";
	public static final String HORNO_TEXTURA = "Imagen/juego/horno/horno.png";
	
}
