package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.Vendedor;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.hud.CartaHUD;

public abstract class MundoConfig {
	
	public static int anchoMundo;
	public static int altoMundo;
	public static int tamanoTile = 32;
	public static float offsetY = 0f * tamanoTile; //por alguna razon hay una especie de offset respecto a la posicion del mapa y las coordenadas de libgdx. De momento lo soluciono asi
	
	public static boolean mostrarHUD = false;
	public static boolean habilitadoHUDS = false;
	
	public static EstadosDelJuego estadoJuego = null;
	
	public static Npc locutor;
	public static Vendedor vendedor;
	
	//Tiempo
	public static boolean pausarTiempo = false; // El tiempo del mundo, los minutos y las horas no cambian, la luz tampoco. Eso no significa que se frena el juego
	public static int diasTranscurridos; // Dias transcurridos en el juego, sin formatear ni nada
	public static int diaDelMundo; //Dia de la semana, da vuelta en 7
	public static String dia;
	public static long horaDelMundo;
	public static long minutoDelMundo;
	
	//Carta a mostrar
	public static CartaHUD cartaAMostrar;
}
