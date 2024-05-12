package com.mygdx.hud;

import com.mygdx.utiles.Config;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.pantallas.Juego;

public class UI {
	
	private ScreenViewport screenViewport;
	private LibroHUD libroHUD;
	private HUD hud;
	private Dialogo dialogo;
	private PausaHUD pausa;
	private InventarioHUD inventario;
	private Combinacion combinacionJugador;
	
	private Jugador jugador;
	private Juego juego;
	
	private boolean mostrarLibro;
	
	public UI(Jugador jugador, Juego juego) {
		
		this.jugador = jugador;
		this.juego = juego;
		
		screenViewport = new ScreenViewport();
		hud = new HUD(jugador, juego);
		dialogo = new Dialogo(jugador);
	    pausa = new PausaHUD(juego);
	    inventario = new InventarioHUD(jugador);
		combinacionJugador = new Combinacion(jugador);
	    libroHUD = new LibroHUD(screenViewport);
		
		
		
	    Recursos.muxJuego.addProcessor(pausa.getStage());
    	Recursos.muxJuego.addProcessor(combinacionJugador.getStage());
    	Recursos.muxJuego.addProcessor(combinacionJugador.getDragAndDrop().getStage());
		
	}
	
	public void render() {
		hud.render();
		pausa.render();
		inventario.render(jugador);
		dialogo.render();
		combinacionJugador.render();
		
		switch (MundoConfig.estadoJuego) {
		case JUEGO:
			hud.mostrar();
			jugador.puedeMoverse = true;
			ocultar(pausa,inventario,dialogo);
			break;

		case DIALOGO:
			System.out.println(HelpDebug.debub(getClass())+"mostrando correctamente");
			if(dialogo.getLocutor() != MundoConfig.locutor) {//se llama solo una vez
			dialogo.setLocutor(MundoConfig.locutor);				
			}
			dialogo.update();

			dialogo.mostrar();
			jugador.puedeMoverse = false;
			ocultar(pausa,inventario);
			break;
			
		case PAUSA:
			pausa.mostrar();
			jugador.puedeMoverse = false;
			MundoConfig.mostrarHUD = false;
			MundoConfig.pausarTiempo = true;
			ocultar(hud);
			break;
			
		case INVENTARIO:
			inventario.mostrar();
			break;
		case COMBINACION:
			combinacionJugador.mostrar();
			ocultar(inventario);
			break;
		case ESCENA:
			break;
		case FIN:
			break;
		case IDLE:
			break;
		case INICIO:
			break;
		case INTERCAMBIO:
			break;
		case INVENTARIO_BATALLAS:
			break;
		default:
			break;
		}
		
		if(mostrarLibro) {
			libroHUD.render();			
		}
	}
	
	private void ocultar(Ocultable ...huds) {
		for(int i = 0;i < huds.length;i++) {
			huds[i].ocultar();
		}
	}

	public HUD getHUD () {
		return hud;
	}
	
	
	public void reEscalar(int width, int height) {
		screenViewport.update(width, height);
		hud.reEscalar(width, height);
		dialogo.reEscalar(width, height);
	    pausa.reEscalar(width, height);
		inventario.reEscalar(width, height);
		combinacionJugador.reEscalar(width, height);
	}
	
	public void mostrarLibro() {
		mostrarLibro = true;
	}
	
	public void ocultarLibro() {
		mostrarLibro = false;
	}
	
	
	public void dispose() {
		hud.dispose();
		pausa.dispose();
	}

}
