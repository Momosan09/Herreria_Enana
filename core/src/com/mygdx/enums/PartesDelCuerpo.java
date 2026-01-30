package com.mygdx.enums;

import com.badlogic.gdx.math.Vector2;

/**
 * Este enum guarda las posiciones donde masomenos puede ir cada accesorio
 *
 *
 */
public enum PartesDelCuerpo {

	CABEZA(16,64), HOMBROS(0,0), PECHO(0,0), HOMBRO_IZQUIERDO(0,0), HOMBRO_DERECHO(0,0), MANO_DERECHA(16,32), MANO_IZQUIERDA(16,32), CINTURA(0,0), PIES(0,0);

	Vector2 posicion;
	
	PartesDelCuerpo(int x, int y){
		posicion = new Vector2(x,y);

	}


}
