package com.mygdx.utiles;

import java.util.ArrayList;

import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.ObjetoDelMapa;



public abstract class OrganizadorSpritesIndiceZ {
	
	public static ArrayList<ObjetoDelMapa> objetosDelMapa = new ArrayList<ObjetoDelMapa>();
	public static ArrayList<Npc> NPCS = new ArrayList<Npc>();//Esto es por las animaciones que tienen los npc
	

	
	public static void dibujarYComparar(Jugador jugador) {
		//Revisa los
		for(int i = 0; i < objetosDelMapa.size(); i++){
			
		if(objetosDelMapa.get(i).getIndiceZ() == 0) {
			objetosDelMapa.get(i).draw();
		}
		}
		
		for(int i = 0; i < NPCS.size(); i++){
			if(NPCS.get(i).getIndiceZ() == 0) {
				NPCS.get(i).ejecutarAnimacion();
			}
		}
		
		
		//elDinamico.draw(Render.batch);
		jugador.draw(Render.batch);
		
		
		for(int i = 0; i < NPCS.size(); i++){
			if(NPCS.get(i).getIndiceZ() == 1) {
				NPCS.get(i).ejecutarAnimacion();
			}
		}
		
		for(int i = 0; i < objetosDelMapa.size(); i++){
		if(objetosDelMapa.get(i).getIndiceZ() == 1) {
			objetosDelMapa.get(i).draw();
		}
		}
		
	}

}
