package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.utiles.MundoConfig;

public class Yunque extends ObjetoDelMapa{

	private boolean entro = false;
	
	public Yunque(float x, float y, World world, String rutaTextura, Jugador jugador) {
		super(x, y, world, rutaTextura, jugador);
	}
	
	public void mostrarHUD() {
		MundoConfig.estadoJuego = EstadosDelJuego.COMBINACION;
	}


	@Override
	public void interactuar(Jugador jugador) {
		mostrarHUD();
		
	}

	@Override
	public Circle getAreaInteraccion() {
		return areaInteraccion;
	}

	@Override
	public void interactuar(Npc npc) {	
	}


}
