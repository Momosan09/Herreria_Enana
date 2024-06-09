package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.hud.Fundicion;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;

public class AltoHorno extends ObjetoDelMapa{

    private Fundicion hud;
	
	public AltoHorno(float x, float y, World world, String rutaTextura, Fundicion hud, Jugador jugador) {
		 super(x, y, world, rutaTextura, jugador);
	        this.hud = hud;
	       	}
	
	public void mostrarHUD() {
		if (getJugadorEnRango()) {
			MundoConfig.estadoJuego = EstadosDelJuego.FUNDICION;
		} else if (!getJugadorEnRango()){
			jugador.borrarInteraccion();
			//MundoConfig.apretoE = false;
		}
    
	}
	
}