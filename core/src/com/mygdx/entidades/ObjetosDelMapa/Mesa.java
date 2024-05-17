package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.hud.MesaHUD;
import com.mygdx.utiles.MundoConfig;

public class Mesa extends ObjetoDelMapa {

	private MesaHUD hud;

	public Mesa(float x, float y, World world, String rutaTextura, MesaHUD hud) {
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

	public MesaHUD getHUD() {
		return hud;
	}

}