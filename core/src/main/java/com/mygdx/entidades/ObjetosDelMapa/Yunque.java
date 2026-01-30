package com.mygdx.entidades.ObjetosDelMapa;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.enums.TipoCombinacion;
import com.mygdx.eventos.EventoInteraccionObj;
import com.mygdx.eventos.Listeners;
import com.mygdx.hud.YunqueHUD;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.MyDragAndDrop;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.recursos.Recursos;

public class Yunque extends ObjetoDelMapa implements EventoInteraccionObj{

	private boolean entro = false;
	
	public Yunque(float x, float y, World world, String rutaTextura, Jugador jugador) {
		super(x, y, world, rutaTextura, jugador);
		 Listeners.agregarListener(this);
	}
	
	public void mostarHUD() {
		MundoConfig.estadoJuego = EstadosDelJuego.COMBINACION;
	}
	



	@Override
	public void interaccionObj() {
		if (getJugadorEnRango()) {
		mostarHUD();
		}
	}

}
