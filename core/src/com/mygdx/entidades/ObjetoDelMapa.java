package com.mygdx.entidades;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;
import com.mygdx.utiles.SpriteOrdenableIndiceZ;

public abstract class ObjetoDelMapa extends Entidad{

	
	public ObjetoDelMapa(float x, float y, World world, String rutaTextura) {
		super(x, y, world,rutaTextura);
		sprite = new Sprite(this.textura);
		sprite.setPosition(this.posicion.x, this.posicion.y);
		OrganizadorSpritesIndiceZ.objetosDelMapa.add(this);
		//crearCuerpo(world, sprite.getWidth()-5, 3);
	}
	
	
	public ObjetoDelMapa(float x, float y, boolean comprable, String rutaTextura) {
		super(x, y, comprable,rutaTextura);
		}
	
	public boolean interaccion() {
		while(jugadorEnRango && apretoE) {
			return true;
		}
		return false;
	}

	public void mostrarMensaje() {
		if(interaccion()) {
			System.out.println(HelpDebug.debub(getClass())+"yea");
		}
	}
	
	
}
