package com.mygdx.utiles;

import java.util.ArrayList;
import java.util.HashMap;

import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.NpcData;


/**
 * Guarda los npcs creados para acceder a ellos en cualquier momento
 * se usa en OrganizadorSpritesIndiceZ.java y Mision.java
 * @author  Momosan09
 *
 */
public abstract class Npcs {
	public static HashMap<NpcData,Npc> NPCS = new HashMap<NpcData, Npc>();//Esto es por las animaciones que tienen los npc
}
