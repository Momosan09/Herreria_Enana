package com.mygdx.utiles;

import java.util.ArrayList;

import com.mygdx.entidades.Jugador;



public abstract class OrganizadorSpritesIndiceZ {
	
	public static ArrayList<SpriteOrdenableIndiceZ> sprites = new ArrayList<SpriteOrdenableIndiceZ>();

	
	public void agregarSprite(SpriteOrdenableIndiceZ spriteOrdenable) {
		sprites.add(spriteOrdenable);
	}
	
	public void eliminarSprite(SpriteOrdenableIndiceZ spriteOrdenable) {
		sprites.remove(spriteOrdenable);
	}
	
	public static void comparar(SpriteOrdenableIndiceZ elDinamico) {
		for(int i = 0; i < sprites.size(); i++) {
			if(elDinamico.getY() < sprites.get(i).getY()) {
				sprites.get(i).setIndiceZ(0);//0 = en el fondo
			}else if(elDinamico.getY() > sprites.get(i).getY()) {
				sprites.get(i).setIndiceZ(1); //1 = de frente
			}
		}
	}
	
	public static void dibujarYComparar(SpriteOrdenableIndiceZ elDinamico, Jugador jugador) {
		comparar(elDinamico);
		//Revisa los
		for(int i = 0; i < sprites.size(); i++){
			
		if(sprites.get(i).getIndiceZ() == 0) {
			sprites.get(i).draw(Render.batch);
		}
		}
		
		//elDinamico.draw(Render.batch);
		jugador.draw(Render.batch);
		
		
		for(int i = 0; i < sprites.size(); i++){
		if(sprites.get(i).getIndiceZ() == 1) {
			sprites.get(i).draw(Render.batch);
		}
		}
		
	}

}
