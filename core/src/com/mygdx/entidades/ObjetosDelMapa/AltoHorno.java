package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.hud.Fundicion;
import com.mygdx.utiles.MundoConfig;

public class AltoHorno extends ObjetoDelMapa{

    private Fundicion hud;
	
	public AltoHorno(float x, float y, World world, String rutaTextura, Fundicion hud) {
		 super(x, y, world, rutaTextura);
	        this.hud = hud;
	       	}
	
	public void mostrarHUD(Jugador jugador) {
		if (getJugadorEnRango() && MundoConfig.apretoE) {
			hud.mostrar();
			hud.tieneHierro(jugador);
		} else if (hud != null) {
			hud.ocultar();
		}else if(!getJugadorEnRango()){
			MundoConfig.apretoE = false;
		}
	}
    
    public Fundicion getHUD() {
    	return hud;
    }
	
}
