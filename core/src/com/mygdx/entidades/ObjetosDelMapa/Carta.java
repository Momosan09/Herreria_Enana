package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.eventos.EventoInteraccionObj;
import com.mygdx.eventos.Listeners;
import com.mygdx.historia.CartasManager;
import com.mygdx.hud.CartaHUD;
import com.mygdx.hud.YunqueHUD;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;

public class Carta extends ObjetoDelMapa implements EventoInteraccionObj{
	
	public Carta(float x, float y, World world, String rutaTextura, Jugador jugador) {
		super(x, y, world, rutaTextura, jugador);
		 Listeners.agregarListener(this);
	}


	@Override
	public void interaccionObj() {
		if(getJugadorEnRango()) {
		Listeners.recibirCarta(CartasManager.determinarCarta());
		}
	}
	
}
