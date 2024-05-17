package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.hud.CajaEntregasHUD;
import com.mygdx.utiles.MundoConfig;

public class CajaEntregas extends ObjetoDelMapa{

	private CajaEntregasHUD hud;
	
	public CajaEntregas(float x, float y, World world, String rutaTextura, CajaEntregasHUD hud) {
		 super(x, y, world, rutaTextura);
	        this.hud = hud;
	}

	public void mostrarHUD(Jugador jugador) {
		if (getJugadorEnRango() && MundoConfig.apretoE) {
			hud.mostrar();
		} else if (hud != null) {
			hud.ocultar();
		}else if(!getJugadorEnRango()){
			MundoConfig.apretoE = false;
		}
	}
	
	   public CajaEntregasHUD getHUD() {
	    	return hud;
	    }
		
 
	
}
