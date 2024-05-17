package com.mygdx.hud;

import com.mygdx.utiles.Config;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.npcs.dialogos.Mensaje;
import com.mygdx.pantallas.Juego;

public class UI {
	
	private ScreenViewport screenViewport;
	private LibroHUD libroHUD;
	private HUD hud;
	private Dialogo dialogo;
	private VentaHUD venta;
	private PausaHUD pausa;
	private InventarioHUD inventario;
	private Combinacion combinacionJugador;
	private Mensaje mensajeAnadido;
	
	private Jugador jugador;
	private Juego juego;
	
	private boolean mostrarLibro;
	
	public UI(Jugador jugador, Juego juego) {
		
		this.jugador = jugador;
		this.juego = juego;
		
		screenViewport = new ScreenViewport();
		hud = new HUD(jugador, juego);
		dialogo = new Dialogo(jugador);
		venta = new VentaHUD();
	    pausa = new PausaHUD(juego);
	    inventario = new InventarioHUD(jugador);
		combinacionJugador = new Combinacion(jugador);
	    libroHUD = new LibroHUD(screenViewport);
		
	    mensajeAnadido = new Mensaje();
		
		
	    Recursos.muxJuego.addProcessor(pausa.getStage());
    	Recursos.muxJuego.addProcessor(combinacionJugador.getStage());
    	Recursos.muxJuego.addProcessor(combinacionJugador.getDragAndDrop().getStage());
		Recursos.muxJuego.addProcessor(venta.getStage());


	}
	
	public void render() {
		hud.render();
		pausa.render();
		inventario.render(jugador);
		dialogo.render();
		venta.render();
		combinacionJugador.render();
		
		
		/*
		mensajeAnadido.mostrarMensajeTemporal(jugador.getItemMensaje(), 2);
		if(jugador.mostrarMensaje) {
		}*/
		
		
		
//		System.out.println(HelpDebug.debub(getClass())+ "Estado actual = " + MundoConfig.estadoJuego);
		switch (MundoConfig.estadoJuego) {
		case JUEGO:
			hud.mostrar();
			jugador.puedeMoverse = true;
			ocultar(pausa,inventario,dialogo,venta);
			dialogo.limpiarDatos();//Esto ayuda a que no queden datos del npc anterior en la caja de dialogo cuando se hable con uno nuevo
			break;


			
		case DIALOGO:
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
		case VENTA:
			if(venta.getVendedor() != MundoConfig.vendedor) {
				venta.setVendedor(MundoConfig.vendedor);				
			}
			jugador.puedeMoverse = false;
			venta.mostrar();
			ocultar(inventario, dialogo);
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
		venta.reEscalar(width, height);
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
		venta.dispose();
		dialogo.dispose();
	}

}
