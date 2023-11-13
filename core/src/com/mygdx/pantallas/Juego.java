package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Table.Debug;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.NPCManager;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.MineralesManager;
import com.mygdx.entidades.ObjetosDelMapa.Yunque;
import com.mygdx.entidades.ObjetosDelMapa.Minable.Hierro;
import com.mygdx.entidades.ObjetosDelMapa.Minable.Piedra;
import com.mygdx.entidades.npcs.Rey;
import com.mygdx.entidades.npcs.Vendedor;
import com.mygdx.entidades.npcs.Viejo;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.entidades.npcs.dialogos.Npc_Dialogos_Rey;
import com.mygdx.enums.Items;
import com.mygdx.game.Principal;
import com.mygdx.hud.CartaHUD;
import com.mygdx.hud.Combinacion;
import com.mygdx.hud.Dialogo;
import com.mygdx.hud.DialogoDeCompra;
import com.mygdx.hud.Fundicion;
import com.mygdx.hud.HUD;
import com.mygdx.hud.InventarioHUD;
import com.mygdx.hud.PausaHUD;
import com.mygdx.red.HiloCliente;
import com.mygdx.red.UtilesRed;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Juego implements Screen{
	
	//Entidades
	private Jugador jugador_1, jugador_2;
	private ObjetoDelMapa carta;
	private Npc viejo, vendedor;
	private Texture jugadorTextura;
	private Mineral piedra, hierro, hierro1, piedra2;
	
	//Managers
	private NPCManager npcManager;
	private MineralesManager mineralesManager;

	//Camaras
	private OrthographicCamera camaraJuego, camaraHud;

	//Scene2d.ui
	private HUD hud;
	private Dialogo dialogo;
	private CartaHUD cartaHUD;
	private PausaHUD pausaHud;
	private Combinacion combinacion;
	private InventarioHUD inventarioHUD;
	private DialogoDeCompra dialogoDeCompra;
	private Fundicion fundicionHUD;
	
	//Input
	private InputMultiplexer mux;
	
	//Toggles (referido a HUDs), los uso cuando ese hud no se cierra con boton
	private boolean toggleInventario = false;
	private boolean togglePausa = false;
	
	//Screens
	private final Principal game;

	//red
	private boolean red = true;
	HiloCliente hc;

	public Juego(final Principal game) {
		this.game = game;
		UtilesRed.game = this;
	}

	@Override
	public void show() {
		mux = new InputMultiplexer();//El input multiplexer es una especie de gestor de inputProcessors
		
		//camaras
		
		camaraJuego = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraJuego.setToOrtho(false);
		camaraJuego.zoom = .6f;
		
		
		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraHud.setToOrtho(false); 
		camaraHud.zoom = .6f;
		
	    //render
		Render.tiledMapRenderer = new OrthogonalTiledMapRenderer(Recursos.MAPA);
		
		//jugador
		hc = new HiloCliente(this);
		jugador_1 = new Jugador(camaraJuego,hc);
		jugador_2 = new Jugador(camaraJuego,hc);
		if(red) {

			UtilesRed.hc.setGame(this);//Le paso el juego porque sino el juego que le entra por constructor (al estatico) vale nulo
			UtilesRed.hc = hc;
		}
				
		//Npc
		crearNPCs();
		npcManagerConfig();

		//objetos del mapa
		piedra = new Piedra(32*19,32*15,false,Recursos.PIEDRA);
		piedra2 = new Piedra(32*18,32*18,false, Recursos.PIEDRA);
		hierro = new Hierro(32*20,32*20,false, Recursos.HIERRO);
		hierro1 = new Hierro(32*7,32*5,true, Recursos.HIERRO);
		
		
		mineralesManager = new MineralesManager();
		
		mineralesManagerConfig();
		//yunque = new Yunque(532,532,Recursos.YUNQUE);
		
		//HUD
		hud = new HUD(jugador_1);
		cartaHUD = new CartaHUD(Npc_Dialogos_Rey.CARTA_0);//ee parece que cartaHUD tiene que ir primero, sino no anda la combinacion (nose pq)
	    combinacion = new Combinacion(jugador_1);
	    inventarioHUD = new InventarioHUD();
	    dialogoDeCompra = new DialogoDeCompra();
	    fundicionHUD = new Fundicion(jugador_1);
	    pausaHud = new PausaHUD(game);
	    
	    mux.addProcessor(cartaHUD.getStage());
	    mux.addProcessor(pausaHud.getStage());
	    mux.addProcessor(combinacion.getStage());//Esto es para los botones de la propia clase
	    mux.addProcessor(fundicionHUD.getStage());
	    //mux.addProcessor(dialogoDeCompra.getStage());
	    mux.addProcessor(combinacion.getDragAndDrop().getStage());//Esto es para las imagenes arratrables que tiene el stage del dragAndDrop de esta clase, si quiero poner otro dragAndDrop tengo q ue agregarlo asi
	    mux.addProcessor(hud.getStage());
	    mux.addProcessor(hud.getResultadosBatallasHUD().getStage());
	    mux.addProcessor(hud.getProximaBatallaHUD().getStage());
		Gdx.input.setInputProcessor(mux);
		
		

	}

	@Override
	public void render(float delta) {


	    //Renderiza el Juego
		camaraJuego.update();
		Render.batch.setProjectionMatrix(camaraJuego.combined);//Aca estaba el problema de que el HUD no se renderizaba por encima del mapa, los setProjectionMatrix de cada camara tienen que estar en ciclos .begin() y .end() distintos
		Render.batch.begin();

		Render.tiledMapRenderer.setView(camaraJuego);
		Render.tiledMapRenderer.render();

		jugador_1.draw(Render.batch);
		if(red) {
			jugador_2.draw(Render.batch);
		}
	    
		npcManager.renderizar(Render.batch);
		npcManager.detectarJugador(jugador_1); 
		
		mineralesManager.renderizar();
		mineralesManager.detectarJugador(jugador_1);
		mineralesManager.minar(jugador_1);
		mineralesManager.comprar(jugador_1);


		Render.batch.end();

		Render.batch.begin();//HUD´s
		
		

		if(cartaHUD.getCerrar()) {//si ya leyo la carta...
			cartaHUD.cerrar();
			jugador_1.puedeMoverse=true;
			//npcManager.mostrarDialogo(Render.batch,0);//Aca tengo que modificar, pq todos los npcs me muestran el primer mensaje
			vendedor.charla(1);
			viejo.charla(0);
			//vendedor.getData().getMensaje(0);
	    
			//Renderiza el HUD
			camaraHud.update();
			Render.batch.setProjectionMatrix(camaraHud.combined);//Una vez que renderiza el juego, se inicia el batch para la camara del HUD y lo dibuja
	    

			//Renderiza ocultables
			hud.render();
			combinacion.render();
			pausaHud.render(jugador_1);
			inventarioHUD.render(jugador_1);
			dialogoDeCompra.render(jugador_1);
			fundicionHUD.render(jugador_1);

		    if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)) {//Esto despues lo tengo que cambiar
		    	combinacion.mostrar();//Abrir Combinacion
		    }
		    fundicionHUD.mostrar();
		    
		    if(Gdx.input.isKeyJustPressed(Keys.TAB)) {
		    	toggleInventario = !toggleInventario;
		    	
		    	if(toggleInventario) {
		    		inventarioHUD.mostrar();
		    	}else {
		    		inventarioHUD.ocultar();
		    	}
		    }
		    if (mineralesManager.comprar(jugador_1)) {
	            // Comprueba si el diálogo de compra debe abrirse o cerrarse
	            if (!dialogoDeCompra.isVisible()) {
	                // Abre el diálogo de compra
	                dialogoDeCompra.mostrar();
	            } else {
	                // Cierra el diálogo de compra
	                dialogoDeCompra.ocultar();
	            }
	        }
		    
		    if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
		    	togglePausa = !togglePausa;
		    	if(togglePausa) {
		    		jugador_1.puedeMoverse = false;
		    	
		    		//System.out.println(HelpDebug.debub(getClass())+"Pausa");
		    		pausaHud.mostrar();
		    		//hud.ocultar();
		    	}else {
		    		pausaHud.ocultar();
		    		//hud.mostrar();
		    		
		    	}
		    }
		    
		}else {
			cartaHUD.render();
		}
		
		//bloquear movimiento del jugador
		if(combinacion.visible) {
			jugador_1.puedeMoverse = false;
			hud.ocultar();
			if(inventarioHUD.visible) {//oculta el inventario si este esta mostrandose
				inventarioHUD.ocultar();
				toggleInventario = !toggleInventario; //cambio el valor del toggle asi no se traba
			}
		}else {
			hud.mostrar();
		}


		if(Gdx.input.isKeyPressed(Keys.E)) {
			jugador_1.getItems().add(Items.PICO);
			System.out.println("Otorgado: Pico");
		}
		Render.batch.end();
	    //System.out.println(HelpDebug.debub(this.getClass()) + "Hola");
		jugador_1.puedeMoverse = true;

	}


	@Override
	public void resize(int width, int height) {
		camaraJuego.viewportWidth = width;
		camaraJuego.viewportHeight = height;
		camaraJuego.update();	
		
	    System.out.println(HelpDebug.debub(getClass())+"X =" +Gdx.graphics.getWidth() + " Y =" + Gdx.graphics.getHeight());
	    hud.reEscalar(width, height);
	    cartaHUD.reEscalar(width, height);
	    pausaHud.reEscalar(width, height);
	    combinacion.reEscalar(width, height);
	    npcManager.reEscalarDialogos(width, height);
	    inventarioHUD.reEscalar(width, height);
	    dialogoDeCompra.reEscalar(width, height);
	}

	@Override
	public void pause() {
	
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		//dispose();
		
	}

	@Override
	public void dispose() {

		Render.tiledMapRenderer.dispose();
		Recursos.MAPA.dispose();
		
	}
	
	public void crearNPCs() {
		viejo = new Viejo(32*10,32*15,Recursos.VIEJO, NpcData.VIEJO);
		vendedor = new Vendedor(32*20,32*5,Recursos.VENDEDOR, NpcData.VENDEDOR);
		//rey = new Rey(0,0,Recursos.VENDEDOR, NpcData.REY);
	}
	
	public void npcManagerConfig() {
		npcManager = new NPCManager();
		npcManager.agregarEntidad(viejo);
		npcManager.agregarEntidad(vendedor);
	    npcManager.crearDialogos();
	}
	
	public void mineralesManagerConfig() {
		mineralesManager = new MineralesManager();
		mineralesManager.agregarMineral(piedra);
		mineralesManager.agregarMineral(piedra2);
		mineralesManager.agregarMineral(hierro);
		mineralesManager.agregarMineral(hierro1);
	}

	public Jugador getJugador1() {
		return jugador_1;
	}
	
	public Jugador getJugador2() {
		return jugador_2;
	}
	


}
