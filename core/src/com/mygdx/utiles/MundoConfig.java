package com.mygdx.utiles;

public abstract class MundoConfig {

	public static int anchoMundo;
	public static int altoMundo;
	public static int tamanoTile = 32;
	public static float offsetY = 0f * tamanoTile; //por alguna razon hay una especie de offset respecto a la posicion del mapa y las coordenadas de libgdx. De momento lo soluciono asi
	public static boolean mostrarHUD = false;
}
