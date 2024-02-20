package com.mygdx.utiles;

import java.util.ArrayList;



public class OrganizadorSpritesIndiceZ {
	
	private ArrayList<SpriteOrdenableIndiceZ> sprites = new ArrayList<SpriteOrdenableIndiceZ>();

	
	public void agregarSprite(SpriteOrdenableIndiceZ spriteOrdenable) {
		sprites.add(spriteOrdenable);
	}
	
	public void eliminarSprite(SpriteOrdenableIndiceZ spriteOrdenable) {
		sprites.remove(spriteOrdenable);
	}
	
	public void comparar(SpriteOrdenableIndiceZ elDinamico) {
		for(int i = 0; i < sprites.size(); i++) {
			if(elDinamico.getY() < sprites.get(i).getY()) {
				sprites.get(i).setIndiceZ(0);//0 = en el fondo
			}else if(elDinamico.getY() > sprites.get(i).getY()) {
				sprites.get(i).setIndiceZ(1); //1 = de frente
			}
		}
	}
	
	public void dibujarYComparar(SpriteOrdenableIndiceZ elDinamico) {
		comparar(elDinamico);
		//Revisa los
		for(int i = 0; i < sprites.size(); i++){
			
		if(sprites.get(i).getIndiceZ() == 0) {
			sprites.get(i).draw(Render.batch);
		}
		
		elDinamico.draw(Render.batch);
		
		if(sprites.get(i).getIndiceZ() == 1) {
			sprites.get(i).draw(Render.batch);
		}
		
		}
	}

}
