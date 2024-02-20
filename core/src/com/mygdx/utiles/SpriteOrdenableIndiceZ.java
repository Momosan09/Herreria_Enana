package com.mygdx.utiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteOrdenableIndiceZ extends Sprite{ //Agrega un indice "z" para hacer el "sprite sorting"
	
	private int indiceZ; // 0 = se dibuja en el fondo 
						 // 1 = se dibuja de frente
	
	public SpriteOrdenableIndiceZ(Texture textura) {
		super(textura);
	}
	
	
	public int getIndiceZ() {
		return indiceZ;
	}
	
	public void setIndiceZ(int z) {
		indiceZ = z;
	}
	

}
