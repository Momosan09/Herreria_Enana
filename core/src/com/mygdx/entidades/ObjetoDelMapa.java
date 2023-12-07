package com.mygdx.entidades;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.utiles.HelpDebug;

public abstract class ObjetoDelMapa extends Entidad{

	public ObjetoDelMapa(float x, float y, World world, String rutaTextura) {
		super(x, y, world,rutaTextura);
		crearCuerpo(world);
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
