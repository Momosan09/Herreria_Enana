package com.mygdx.hud;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.pantallas.Juego;

public class UI {
	
	private ScreenViewport screenViewport;
	private LibroHUD libroHUD;
	private HUD hud;
	private Jugador jugador;
	private Juego juego;
	
	private boolean mostrarLibro;
	
	public UI(Jugador jugador, Juego juego) {
		
		screenViewport = new ScreenViewport();
		hud = new HUD(jugador, juego);
		
		libroHUD = new LibroHUD(screenViewport);
		
	}
	
	public void render() {
		hud.render();
		if(mostrarLibro) {
			libroHUD.render();			
		}
	}

	public HUD getHUD () {
		return hud;
	}
	
	public void dispose() {
		hud.dispose();
	}
	
	public void reEscalar(int width, int height) {
		screenViewport.update(width, height);
	}
	
	public void mostrarLibro() {
		mostrarLibro = true;
	}
	
	public void ocultarLibro() {
		mostrarLibro = false;
	}
}
