package com.mygdx.entidades;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.utiles.HelpDebug;

public abstract class ObjetoDelMapa extends Entidad{

	public ObjetoDelMapa(float x, float y, String rutaTextura) {
		super(x, y, rutaTextura);
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
