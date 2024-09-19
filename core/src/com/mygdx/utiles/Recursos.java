package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.I18NBundle;

public abstract class Recursos {
	
	public static I18NBundle bundle = I18NBundle.createBundle(Gdx.files.internal("locale/locale"));
	
	public static InputMultiplexer muxJuego = new InputMultiplexer();//El input multiplexer es una especie de gestor de inputProcessors
	public static InputMultiplexer muxMenu = new InputMultiplexer();
	
	//Box2d
	public static final float PPM = 32.0f;//Pixeles por metro
	
	//Menu
	public static final String FUENTE_TEMPORAL = "Fonts/Cardinal.ttf";
	public static final String MUSICA_MENU = "Audio/saplingGarden.mp3";
	public static final String EFECTO_TECLA_MENU = "Audio/sonidoEnMenu.wav";
	public static final String FONDO_MENU = "Imagen/menu/imagenfondo.png";
	public static final String MUSICA_SI = "Imagen/menu/notaMusical.png";
	public static final String MUSICA_NO = "Imagen/menu/notaMusicalNo.png";
	public static final String SKIN_NOTA_MUSICAL = "Imagen/menu/skinImageButtonNotaMusical.json";
	
	//Jugador
	public static final String JUGADOR_TEXTURA = "Jugador/quieto_0.png";
	public static final String JUGADOR1_SPRITESHEET = "Jugador/jugador_1_SpriteSheet.png";
	public static final String JUGADOR2_SPRITESHEET = "Jugador/jugador_2_SpriteSheet.png";
	
	//Items
	public static final String PICO_DER = "Items/picoDer.png";
	public static final String PICO_IZ = "Items/picoIz.png";
	public static final String CINCEL = "Items/cincel.png";
	public static final String MAZA = "Items/maza.png";
	public static final String LIMA = "Items/lima.png";
	public static final String SIERRA = "Items/sierra.png";
	public static final String ESQUEMA_TEXT = "Items/esquema.png";
	
	//Mapas
	public static final String MAPA = "mapas/mapa.tmx";
	
	//NPC
	public static final String VIEJO = "objetosDelMundo/npc/viejo/viejoAnimacion.png";
	public static final String VENDEDOR_AMBULANTE = "objetosDelMundo/npc/vendedor/vendedorAnimacion.png";
	public static final String VENDEDOR_TIENDA = "objetosDelMundo/npc/tiendaNpc/tiendaNpcAnimacion.png";
	public static final String CARPINTERO = "objetosDelMundo/npc/carpintero/carpinteroAnimacion.png";
	
	
	//ANIMALES
	public static final String RANA = "objetosDelMundo/npc/rana/laRanaSpriteSheet.png";
	
	//NPC PORTRAITS
	public static final String VENDEDOR_AMBULANTE_PORTRAIT = "objetosDelMundo/npc/vendedor/vendedorPortrait.png";
	public static final String VENDEDOR_TIENDA_PORTRAIT = "objetosDelMundo/npc/tiendaNpc/tiendaNpcPortrait.png";
	
	//Objetos del mapa
//	public static final String YUNQUE = "objetosDelMundo/inanimados/interactuables/yunque.png";
	public static final String PIEDRA_PIEDRA = "objetosDelMundo/inanimados/interactuables/piedra.png";
	public static final String MENA_HIERRO = "objetosDelMundo/inanimados/interactuables/hierroMena.png";
	public static final String MENA_CARBON = "objetosDelMundo/inanimados/interactuables/carbonMena.png";
	public static final String LINGOTE_HIERRO = "objetosDelMundo/inanimados/hierroLingote.png";
	public static final String PLANCHA_HIERRO = "objetosDelMundo/inanimados/hierroPlancha.png";
	public static final String HIERRO_PURO = "objetosDelMundo/inanimados/hierroPuro.png";
	public static final String CARBON_PURO = "objetosDelMundo/inanimados/carbonPuro.png";
			
	public static final String VACIO = "objetosDelMundo/inanimados/vacio.png";
	public static final String HORNO = "objetosDelMundo/inanimados/interactuables/horno.png";
	public static final String ALTO_HORNO = "objetosDelMundo/inanimados/interactuables/altoHorno.png";
	public static final String CAJA_ENTREGAS = "objetosDelMundo/inanimados/interactuables/cajaEntregas.png";
	public static final String MESA = "objetosDelMundo/inanimados/interactuables/mesa.png";
	public static final String SOPORTE_ARMADURAS = "objetosDelMundo/inanimados/interactuables/soporteArmadura.png";
	public static final String YUNQUE = "objetosDelMundo/inanimados/interactuables/yunque2.png";
	
		//ITEMS Y DE MISION
	public static final String DISCO_HIERRO = "objetosDelMundo/inanimados/hierroDisco.png";
	public static final String SIERRA_CIRCULAR= "objetosDelMundo/inanimados/sierraCircular.png";
	
	//HUD
	public static final String DINERO_HUD = "HUD/Dinero.png";
	public static final String MARCO_RELOJ = "HUD/reloj.png";
	public static final String DIBUJO_RELOJ = "HUD/tiempo.png";
	public static final String CUADRO_HUD = "HUD/cuadro.png";
	public static final String SKIN = "HUD/skin/skin.json";
	public static final String SKIN_BOTON_ARRIBA = "HUD/skin/botonArriba.json";
	public static final String SKIN_BOTON_ABAJO = "HUD/skin/botonAbajo.json";
	public static final String SKIN_TOOLTIP = "HUD/skin/tooltip.json";
	public static final String DIALOGO_HUD = "HUD/9Patch/dialogo.9.png";
	public static final String VENTA_HUD = "HUD/9Patch/venta.9.png";
	public static final String PAUSA_HUD = "HUD/9Patch/pausa.9.png";
	
	
	//HUD fondos
	public static final String CARTA_TEXTURA = "Imagen/juego/carta/carta.png";
	public static final String MESA_TEXTURA = "HUD/mesaHUD.png";
	public static final String HORNO_TEXTURA = "Imagen/juego/horno/horno.png";
	public static final String YUNQUE_TEXTURA = "HUD/yunque2HUD.png";
	public static final String CAJA_ENTREGAS_TEXTURA = "HUD/cajaEntregasHUD.png";
	public static final String LIBRO_HUD = "HUD/libroHUD.png";
	public static final String CASILLERO_VENTA_HUD = "HUD/casilleroVenta.png";
}
