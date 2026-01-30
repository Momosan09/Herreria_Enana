package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.eventos.EventoInteraccionObj;
import com.mygdx.eventos.Listeners;
import com.mygdx.utiles.MundoConfig;

public class SoporteArmadura extends ObjetoDelMapa implements EventoInteraccionObj{
	

	public SoporteArmadura(float x, float y, World world, String rutaTextura, Jugador jugador) {
		 super(x, y, world, rutaTextura, jugador);
		 Listeners.agregarListener(this);
	}

	public void mostrarHUD() {
		MundoConfig.estadoJuego = EstadosDelJuego.SOPORTE_ARMADURA;
	}
	

	@Override
	public void interaccionObj() {
		if (getJugadorEnRango()) {
		mostrarHUD();
		}
	}
		
    
	
}
