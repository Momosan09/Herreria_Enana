package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.hud.Fundicion;
import com.mygdx.hud.SoporteArmaduraHUD;
import com.mygdx.utiles.MundoConfig;

public class SoporteArmadura extends ObjetoDelMapa{
	
	private SoporteArmaduraHUD hud;

	public SoporteArmadura(float x, float y, World world, String rutaTextura, SoporteArmaduraHUD hud, Jugador jugador) {
		 super(x, y, world, rutaTextura, jugador);
	        this.hud = hud;
	}

	public void mostrarHUD() {
		if (getJugadorEnRango() && MundoConfig.apretoE) {
			hud.mostrar();
		} else if (hud != null) {
			hud.ocultar();
		}else if(!getJugadorEnRango()){
			MundoConfig.apretoE = false;
		}
	}
	
	   public SoporteArmaduraHUD getHUD() {
	    	return hud;
	    }
		
    
	
}
