package com.mygdx.utiles.recursos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.I18NBundle;
import com.mygdx.utiles.recursos.npc.NpcsRecursos;

public abstract class Recursos {
	
	public static SonidosRecursos sonidos = new SonidosRecursos();
	public static HudsRecursos hud = new HudsRecursos();
	public static ItemsYdeMision itemsYmision = new ItemsYdeMision();
	public static Props objMapa = new Props();
	public static Minerales minerales = new Minerales();
	public static NpcsRecursos npc = new NpcsRecursos();
	public static MenuRecursos menu = new MenuRecursos();
	public static ConfiguracionesRecursos config = new ConfiguracionesRecursos();
	public static JugadorRecursos jugador = new JugadorRecursos();
	public static Particulas particulas = new Particulas();
	
	
	public static I18NBundle bundle = I18NBundle.createBundle(Gdx.files.internal("locale/locale"));
	
	public static InputMultiplexer muxJuego = new InputMultiplexer();//El input multiplexer es una especie de gestor de inputProcessors
	public static InputMultiplexer muxMenu = new InputMultiplexer();
	
	//Box2d
	public static final float PPM = 32.0f;//Pixeles por metro
	
	
		//de forja
		//TODO no se si definirlos como item o como mineral
	public static final String HOJA_HIERRO_0 = "objetosDelMundo/inanimados/Items/Espadas/Hojas/Hierro/HojaHierro0.png";
	public static final String ESPADA_HIERRO_0 = "objetosDelMundo/inanimados/Items/Espadas/espadaHierro0.png";
	
	

	//Mapas
	public static final String MAPA = "mapas/mapa.tmx";
	




		

	

	
	
}
