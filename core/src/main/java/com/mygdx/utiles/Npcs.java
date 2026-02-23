package com.mygdx.utiles;

import java.util.ArrayList;

import com.mygdx.entidades.Npc;


/**
 * Guarda los npcs creados para acceder a ellos en cualquier momento
 * se usa en OrganizadorSpritesIndiceZ.java y Mision.java
 * @author  Momosan09
 *
 */
public abstract class Npcs {
	public static ArrayList<Npc> NPCS = new ArrayList<Npc>();//Esto es por las animaciones que tienen los npc
}
