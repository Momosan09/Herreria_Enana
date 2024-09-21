package com.mygdx.hud;

import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.npcs.dialogos.Mensaje;
import com.mygdx.entidades.npcs.dialogos.Npc_Dialogos_Rey;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.eventos.EventoRecibirCarta;
import com.mygdx.eventos.Listeners;
import com.mygdx.pantallas.Juego;

public class UI implements EventoRecibirCarta{
	
	private ScreenViewport screenViewport;
	private LibroHUD libroHUD;
	private HUD hud;
	private Dialogo dialogo;
	private VentaHUD venta;
	private PausaHUD pausa;
	private InventarioHUD inventario;
	private Combinacion combinacion;
	private Mensaje mensajeAnadido;
	private Fundicion fundicion;
	private DiarioHUD diario;
	
	private CartaHUD carta;
	
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
	    combinacion = new Combinacion(jugador);
	    libroHUD = new LibroHUD(screenViewport);
	    fundicion = new Fundicion(jugador);
	    //carta = new CartaHUD(Npc_Dialogos_Rey.CARTA_0);
	    diario = new DiarioHUD(jugador);
	    
		
	    mensajeAnadido = new Mensaje();
		
		Recursos.muxJuego.addProcessor(hud.getStage());
		Recursos.muxJuego.addProcessor(hud.getDiarioHUD().getStage());
		Recursos.muxJuego.addProcessor(hud.getProximaBatallaHUD().getStage());
		Recursos.muxJuego.addProcessor(hud.getResultadosBatallasHUD().getStage());
		Recursos.muxJuego.addProcessor(pausa.getStage());
    	Recursos.muxJuego.addProcessor(combinacion.getStage());
    	Recursos.muxJuego.addProcessor(combinacion.getDragAndDrop());
		Recursos.muxJuego.addProcessor(venta.getStage());
		Recursos.muxJuego.addProcessor(fundicion.getStage());
		Recursos.muxJuego.addProcessor(libroHUD.getStage());
		Recursos.muxJuego.addProcessor(diario.getStage());

		Listeners.agregarListener(this);
	}
	
	public void render() {
		hud.render();
		pausa.render();
		inventario.render(jugador);
		dialogo.render();
		venta.render();
		combinacion.render();
		fundicion.render();
		diario.render();
		
		
		/*
		mensajeAnadido.mostrarMensajeTemporal(jugador.getItemMensaje(), 2);
		if(jugador.mostrarMensaje) {
		}*/
		
		Gdx.input.setInputProcessor(Recursos.muxJuego);
		
//		System.out.println(HelpDebug.debub(getClass())+ "Estado actual = " + MundoConfig.estadoJuego);
		switch (MundoConfig.estadoJuego) {
		case JUEGO:
			MundoConfig.pausarTiempo = false;
			hud.mostrar();
			jugador.puedeMoverse = true;
			ocultar(pausa,inventario,dialogo,venta, combinacion, diario);
			dialogo.limpiarDatos();//Esto ayuda a que no queden datos del npc anterior en la caja de dialogo cuando se hable con uno nuevo
			break;


			
		case DIALOGO:

			if(dialogo.getLocutor() != MundoConfig.locutor) {//se llama solo una vez
			dialogo.setLocutor(MundoConfig.locutor);				
			}
			dialogo.update();
			dialogo.mostrar();
			jugador.puedeMoverse = false;
			ocultar(pausa,inventario, diario);
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
			ocultar(diario);
			break;
		case COMBINACION:
			combinacion.mostrar();
			ocultar(inventario, hud, diario);
			break;
			
		case FUNDICION:
			Gdx.input.setInputProcessor(fundicion.getStage());
			jugador.puedeMoverse = false;
			fundicion.mostrar();
			fundicion.tieneHierro(jugador);
			ocultar(inventario,hud,pausa, diario);
			break;
			
		case DIARIO:
			jugador.puedeMoverse = true;
			diario.mostrar();
			ocultar(fundicion, inventario);
			
			break;
		case ESCENA:
			break;
		case FIN:
			break;
		case IDLE:
			break;
//		case INICIO:
//			if(!carta.getCerrar()) {				
//			carta.render();
//			ocultar(hud,inventario,combinacion);
//			jugador.puedeMoverse = false;
//			MundoConfig.pausarTiempo = true;
//			}
//			break;
		case CARTA:
			if(!carta.getCerrar()) {
				carta.render();
				ocultar(hud,inventario,combinacion);
				jugador.puedeMoverse = false;
				MundoConfig.pausarTiempo = true;
			}
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
		combinacion.reEscalar(width, height);
		fundicion.reEscalar(width, height);
		diario.reEscalar(width, height);
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
		diario.dispose();
	}


	@Override
	public void recibirCarta(CartaHUD carta) {
		MundoConfig.estadoJuego = EstadosDelJuego.CARTA;
		Recursos.muxJuego.addProcessor(carta.getStage());
		this.carta = carta;
		
	}





}
