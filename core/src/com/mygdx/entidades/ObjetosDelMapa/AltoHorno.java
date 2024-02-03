package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.hud.Fundicion;

public class AltoHorno extends Entidad{

    private Fundicion hud;
	
	public AltoHorno(float x, float y, World world, String rutaTextura, Fundicion hud) {
		 super(x, y, world, rutaTextura);
//	        crearCuerpo(world,this.textura.getWidth(),this.textura.getHeight());
	        this.hud = hud;
	}
	
    public void mostarHUD(Jugador jugador) {
        if (getJugadorEnRango() && apretoE) {
            hud.mostrar();
            hud.tieneHierro(jugador);
        } else if (hud != null) {
            hud.ocultar();
        }
    }
    
    public Fundicion getHUD() {
    	return hud;
    }
	
}
