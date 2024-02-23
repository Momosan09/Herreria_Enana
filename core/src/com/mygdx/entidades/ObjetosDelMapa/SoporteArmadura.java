package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.hud.Fundicion;
import com.mygdx.hud.SoporteArmaduraHUD;

public class SoporteArmadura extends ObjetoDelMapa{
	
	private SoporteArmaduraHUD hud;

	public SoporteArmadura(float x, float y, World world, String rutaTextura, SoporteArmaduraHUD hud) {
		 super(x, y, world, rutaTextura);
	        this.hud = hud;
	}

    public void mostrarHUD(Jugador jugador) {
        if (getJugadorEnRango() && apretoE) {
            hud.mostrar();
        } else if (hud != null) {
            hud.ocultar();
        }
    }
	
	   public SoporteArmaduraHUD getHUD() {
	    	return hud;
	    }
		
    
	
}
