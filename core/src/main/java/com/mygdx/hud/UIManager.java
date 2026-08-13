package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.npcs.dialogos.Mensaje;
import com.mygdx.eventos.EventoRecibirCarta;
import com.mygdx.eventos.Listeners;
import com.mygdx.hud.actoresEspeciales.SolapaGeneralesHUD;
import com.mygdx.pantallas.Juego;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.recursos.Recursos;

public class UIManager implements EventoRecibirCarta{
	
	private ScreenViewport screenViewport;
	private LibroHUD libroHUD;
	private HUDPrincipal hud;
	private Dialogo dialogo;
	private VentaHUD venta;
	private PausaHUD pausa;
	private InventarioHUD inventario;
	private Combinacion combinacion;
	private Mensaje mensajeAnadido;
	private FundicionOmega fundicion;
	private ModificadoresHUD modificadores;
	private DiarioHUD diario;
	private GeneralesHUD ficheroDeGeneralesGuerra;
	
	private CartaHUD carta;
	
	private Jugador jugador;
	private Juego juego;
	
	private boolean mostrarLibro;
	
	boolean variableDeControl = false;
	
	public UIManager(Jugador jugador, Juego juego) {
		
		this.jugador = jugador;
		this.juego = juego;
		
		screenViewport = new ScreenViewport();
		hud = new HUDPrincipal(jugador, juego);
		dialogo = new Dialogo();
		venta = new VentaHUD(jugador);
	    pausa = new PausaHUD(juego);
	    inventario = new InventarioHUD(jugador);
	    combinacion = new Combinacion(jugador);
	    libroHUD = new LibroHUD();
	    fundicion = new FundicionOmega(jugador);
	    //carta = new CartaHUD(Npc_Dialogos_Rey.CARTA_0);
	    diario = new DiarioHUD(jugador);
	    modificadores = new ModificadoresHUD(jugador);
	    ficheroDeGeneralesGuerra = new GeneralesHUD(jugador);
	    
		
	    mensajeAnadido = new Mensaje();
		
		
	    //FIXME mover de aca cuando tenga las guerras bien hechas
	    ficheroDeGeneralesGuerra.agregarSolapa("General_1", new SolapaGeneralesHUD(Recursos.npc.enanos.portraits.VENDEDOR_AMBULANTE_PORTRAIT, "General_1"));
	    ficheroDeGeneralesGuerra.agregarSolapa("General_2", new SolapaGeneralesHUD(Recursos.npc.enanos.portraits.VENDEDOR_AMBULANTE_PORTRAIT, "General_2"));
	    ficheroDeGeneralesGuerra.agregarSolapa("General_3", new SolapaGeneralesHUD(Recursos.npc.enanos.portraits.VENDEDOR_AMBULANTE_PORTRAIT, "General_3"));


	    
		Listeners.agregarListener(this);
	}
	
	public void render() {
		hud.dibujar();
		pausa.dibujar();
		inventario.dibujar();
		dialogo.dibujar();
		venta.dibujar();
		combinacion.dibujar();
		fundicion.dibujar();
		modificadores.dibujar();
		ficheroDeGeneralesGuerra.dibujar();
		diario.dibujar();
		
		
		
		/*
		mensajeAnadido.mostrarMensajeTemporal(jugador.getItemMensaje(), 2);
		if(jugador.mostrarMensaje) {
		}*/
		
		
		
//		System.out.println(HelpDebug.debub(getClass())+ "Estado actual = " + MundoConfig.estadoJuego);
		switch (MundoConfig.estadoJuego) {
		case JUEGO:

			MundoConfig.pausarTiempo = false;
			hud.mostrar();
			jugador.puedeMoverse = true;
			inventario.ocultar();
			dialogo.ocultar();
		    activarSolo(hud.getStage());
			//dialogo.limpiarDatos();//Esto ayuda a que no queden datos del npc anterior en la caja de dialogo cuando se hable con uno nuevo
			break;


			
		case DIALOGO:
			Gdx.input.setInputProcessor(dialogo.getStage());
			dialogo.mostrar();
			jugador.puedeMoverse = true;
			ocultar(pausa,inventario, diario);
			break;
			
		case PAUSA:
			 activarSolo(pausa.getStage());

			jugador.puedeMoverse = false;
			MundoConfig.mostrarHUD = false;
			MundoConfig.pausarTiempo = true;
			hud.ocultar();
			break;
			
		case INVENTARIO:
			inventario.mostrar();
			activarSolo(inventario.getStage());
//			ocultar(diario);
			break;
		case COMBINACION:
			combinacion.mostrar();
			 activarSolo(combinacion.getStage(), combinacion.getDragAndDrop());
			break;
			
		case FUNDICION:
			
			jugador.puedeMoverse = false;
			fundicion.mostrar();
			
			 activarSolo(fundicion.getStage());
			break;
			
		case DIARIO:
			jugador.puedeMoverse = true;
			diario.mostrar();
			activarSolo(diario.getStage());
			
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
			if(MundoConfig.cartaAMostrar != null) {	
			if(!MundoConfig.cartaAMostrar.getCerrar()) {
				MundoConfig.cartaAMostrar.dibujar();
				hud.ocultar();
				activarSolo(MundoConfig.cartaAMostrar.getStage());
				jugador.puedeMoverse = false;
				MundoConfig.pausarTiempo = true;
				
				if(MundoConfig.cartaAMostrar.getMision() != null) { // si la carta da mision
					if(!jugador.getInventarios().tareas.getMisiones().containsValue(MundoConfig.cartaAMostrar.getMision())) {//si el jugador no tiene asiganda esa mision
						jugador.getInventarios().tareas.agregarMision(MundoConfig.cartaAMostrar.getMision());
					}
					
				}
			}else {
				Recursos.muxJuego.removeProcessor(MundoConfig.cartaAMostrar.getStage());
			}
			}
			break;
		case INICIO:

			if(!MundoConfig.cartaAMostrar.getCerrar()) {
				Recursos.muxJuego.addProcessor(MundoConfig.cartaAMostrar.getStage());
				MundoConfig.cartaAMostrar.dibujar();
				MundoConfig.pausarTiempo = true;
				jugador.puedeMoverse = false;
			}else {
				Recursos.muxJuego.removeProcessor(MundoConfig.cartaAMostrar.getStage());
				MundoConfig.cartaAMostrar = null;				
			}
			break;
		case VENTA:
			if(venta.getVendedor() != MundoConfig.vendedor) {
				venta.setVendedor(MundoConfig.vendedor);				
			}
			jugador.puedeMoverse = false;
			
			venta.mostrar();
			inventario.ocultar();
			dialogo.ocultar();
			break;
		case INVENTARIO_BATALLAS:
			break;
			
		case MODIFICADORES:
			jugador.puedeMoverse = false;
			modificadores.mostrar();
			activarSolo(modificadores.getStage());
			break;
			
		case FICHERO_DE_GUERRA:
			jugador.puedeMoverse = false;

			if(!variableDeControl) {
				if(!jugador.getInventarios().armas.isEmpty()) {					
			    ficheroDeGeneralesGuerra.getSolapaActiva().poblarTablaArma("Martillo de zeus", jugador.getInventarios().armas.get(0).getTextura(), "yabadabdo");
			    variableDeControl = true;
				}
			}
			ficheroDeGeneralesGuerra.mostrar();
			activarSolo(ficheroDeGeneralesGuerra.getStage());
			break;
		default:
			break;
		}
		
		if(mostrarLibro) {
			libroHUD.dibujar();			
		}
	}
	
	private void ocultar(HUD ...huds) {
		for(int i = 0;i < huds.length;i++) {
			huds[i].ocultar();
		}
	}


	public HUDPrincipal getHUD () {
		return hud;
	}
	
	
	public void reEscalar(int width, int height) {
		screenViewport.update(width, height);
		hud.reEscalar(width, height);
		//dialogo.reEscalar(width, height);
		venta.reEscalar(width, height);
	    pausa.reEscalar(width, height);
		inventario.reEscalar(width, height);
		combinacion.reEscalar(width, height);
		fundicion.reEscalar(width, height);
		modificadores.reEscalar(width, height);
		ficheroDeGeneralesGuerra.reEscalar(width, height);
		diario.reEscalar(width, height);
	}
	
	public void mostrarLibro() {
		mostrarLibro = true;
	}
	
	public void ocultarLibro() {
		mostrarLibro = false;
	}
	
	private void activarSolo(InputProcessor processor) {
		//No puedo usar "clear" porque me saca los processors del teclado que estan en Juego.java
		Recursos.muxJuego.removeProcessor(hud.getStage());
		Recursos.muxJuego.removeProcessor(hud.getDiarioHUD().getStage());
		Recursos.muxJuego.removeProcessor(hud.getProximaBatallaHUD().getStage());
		Recursos.muxJuego.removeProcessor(hud.getResultadosBatallasHUD().getStage());
		Recursos.muxJuego.removeProcessor(pausa.getStage());
    	Recursos.muxJuego.removeProcessor(combinacion.getStage());
    	Recursos.muxJuego.removeProcessor(combinacion.getDragAndDrop());
		Recursos.muxJuego.removeProcessor(venta.getStage());
		Recursos.muxJuego.removeProcessor(fundicion.getStage());
		Recursos.muxJuego.removeProcessor(libroHUD.getStage());
		Recursos.muxJuego.removeProcessor(ficheroDeGeneralesGuerra.getStage());
		Recursos.muxJuego.removeProcessor(modificadores.getStage());
		Recursos.muxJuego.removeProcessor(diario.getStage());
		
	    Recursos.muxJuego.addProcessor(processor);
	    Gdx.input.setInputProcessor(Recursos.muxJuego);
	}
	
	/**
	 * Este metodo es porque combinacion tiene dos procesors
	 * @param processor
	 * @param procesorDelDragAndDrop
	 */
	private void activarSolo(InputProcessor processor, InputProcessor procesorDelDragAndDrop) {
		//No puedo usar "clear" porque me saca los processors del teclado que estan en Juego.java
		Recursos.muxJuego.removeProcessor(hud.getStage());
		Recursos.muxJuego.removeProcessor(hud.getDiarioHUD().getStage());
		Recursos.muxJuego.removeProcessor(hud.getProximaBatallaHUD().getStage());
		Recursos.muxJuego.removeProcessor(hud.getResultadosBatallasHUD().getStage());
		Recursos.muxJuego.removeProcessor(pausa.getStage());
    	Recursos.muxJuego.removeProcessor(combinacion.getStage());
    	Recursos.muxJuego.removeProcessor(combinacion.getDragAndDrop());
		Recursos.muxJuego.removeProcessor(venta.getStage());
		Recursos.muxJuego.removeProcessor(fundicion.getStage());
		Recursos.muxJuego.removeProcessor(libroHUD.getStage());
		Recursos.muxJuego.removeProcessor(modificadores.getStage());
		Recursos.muxJuego.removeProcessor(ficheroDeGeneralesGuerra.getStage());
		Recursos.muxJuego.removeProcessor(diario.getStage());
		
	    Recursos.muxJuego.addProcessor(processor);
	    Recursos.muxJuego.addProcessor(procesorDelDragAndDrop);
	    Gdx.input.setInputProcessor(Recursos.muxJuego);
	}

	
	public void dispose() {
		hud.dispose();
		pausa.dispose();
		venta.dispose();
//		dialogo.dispose();
		diario.dispose();
	}


	@Override
	public void recibirCarta(CartaHUD carta) {
		Recursos.muxJuego.addProcessor(carta.getStage());
		this.carta = carta;
		
	}


	public Stage getDialogoStage() {
		return dialogo.getStage();
	}

}
