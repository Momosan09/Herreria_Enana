package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Table.Debug;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.entidades.ColisionesManager;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.NPCManager;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.entidades.ObjetosDelMapa.Horno;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.MineralesManager;
import com.mygdx.entidades.ObjetosDelMapa.Yunque;
import com.mygdx.entidades.ObjetosDelMapa.Minable.Hierro;
import com.mygdx.entidades.ObjetosDelMapa.Minable.Piedra;
import com.mygdx.entidades.npcs.Rey;
import com.mygdx.entidades.npcs.VendedorAmbulante;
import com.mygdx.entidades.npcs.VendedorDeTienda;
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
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Juego implements Screen{
	
	//Entidades
	private Jugador jugador;
	private ObjetoDelMapa carta;
	private Npc viejo, vendedorAmbulate, vendedorTienda;
	private Texture jugadorTextura;
	private Mineral piedra, hierro, hierro1, piedra2;
	private Horno horno;
	
	
	//Managers
	private NPCManager npcManager;
	private MineralesManager mineralesManager;

	//Camaras
	private OrthographicCamera camaraJugador, camaraHud;

	//Scene2d.ui
	private HUD hud;
	private Dialogo dialogo;
	private CartaHUD cartaHUD;
	private PausaHUD pausaHud;
	private Combinacion combinacionJugador;
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
	
	//Colisiones
	private ColisionesManager colisionesManager;

	public Juego(final Principal game) {
		this.game = game;
	}

	@Override
	public void show() {
		mux = new InputMultiplexer();//El input multiplexer es una especie de gestor de inputProcessors
		
		//camaras
		
		camaraJugador = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraJugador.setToOrtho(false);
		camaraJugador.zoom = .6f;

		
		
		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraHud.setToOrtho(false); 
		camaraHud.zoom = .6f;
		
	    //render
		Render.tiledMapRenderer = new OrthogonalTiledMapRenderer(Recursos.MAPA);
		

		jugador = new Jugador(camaraJugador);

		
		combinacionJugador = new Combinacion(jugador);
		
    	mux.addProcessor(combinacionJugador.getStage());
    	mux.addProcessor(combinacionJugador.getDragAndDrop().getStage());
		
	
				
		//Npc
		crearNPCs();
		npcManagerConfig();
		
		//objetos del mapa
		piedra = new Piedra(32*20,32*16,false,Recursos.PIEDRA);
		piedra2 = new Piedra(32*18,32*18,false, Recursos.PIEDRA);
		hierro = new Hierro(32*20,32*20,false, Recursos.HIERRO);
		hierro1 = new Hierro(32*7,32*5,true, Recursos.HIERRO);
		
				
		mineralesManagerConfig();
		//yunque = new Yunque(532,532,Recursos.YUNQUE);
		

		
		//HUD

		cartaHUD = new CartaHUD(Npc_Dialogos_Rey.CARTA_0);//ee parece que cartaHUD tiene que ir primero, sino no anda la combinacion (nose pq)

	    
	    dialogoDeCompra = new DialogoDeCompra();
	    pausaHud = new PausaHUD(game);

	    
	    mux.addProcessor(cartaHUD.getStage());
	    mux.addProcessor(pausaHud.getStage());
	    //mux.addProcessor(dialogoDeCompra.getStage());
	   
	    inventarioHUD = new InventarioHUD(jugador);
	    
		hud = new HUD(jugador);
		fundicionHUD = new Fundicion(jugador);
			
	    horno = new Horno(32*22,32*10, Recursos.HORNO, fundicionHUD);
	    mux.addProcessor(horno.getHUD().getStage());
	    mux.addProcessor(hud.getStage());
	    mux.addProcessor(hud.getResultadosBatallasHUD().getStage());
	    mux.addProcessor(hud.getProximaBatallaHUD().getStage());
	    
		Gdx.input.setInputProcessor(mux);
		
		//Colisiones
		colisionesManagerConfig();

	}

	@Override
	public void render(float delta){
			
		Render.batch.begin();
		Render.tiledMapRenderer.render();
		Render.batch.end();

	    //Renderiza el Juego
		Render.batch.begin();
//		DibujarFiguras.dibujarRectanguloLleno(colisionPrueba.x, colisionPrueba.y, colisionPrueba.width, colisionPrueba.height, Color.CYAN);
			
		camaraJugador.update();
		Render.batch.setProjectionMatrix(camaraJugador.combined);
		Render.tiledMapRenderer.setView(camaraJugador);

		jugador.draw(Render.batch);

		colisionesManager.checkearColisiones();

		
		Render.batch.end();
		Render.batch.begin();
		
		npcManager.renderizar(Render.batch);
		npcManager.detectarJugador(jugador); 
		
		mineralesManager.renderizar();
		mineralesManager.detectarJugador(jugador);
		mineralesManager.minar(jugador);
		mineralesManager.comprar(jugador);
		horno.detectarJugador(jugador);
		horno.draw();
		
		mineralesManager.limpiarMinerales(colisionesManager,false);


		Render.batch.end();

		Render.batch.begin();//HUD´s
		
		cartaHUD.render();

		if(cartaHUD.getCerrar()) {//si ya leyo la carta...
			cartaHUD.cerrar();
			jugador.puedeMoverse=true;

			//npcManager.mostrarDialogo(Render.batch,0);//Aca tengo que modificar, pq todos los npcs me muestran el primer mensaje
			vendedorAmbulate.charla(1);
			viejo.charla(0);
			vendedorTienda.charla(0);
			//vendedor.getData().getMensaje(0);
	    
			//Renderiza el HUD
			camaraHud.update();
			Render.batch.setProjectionMatrix(camaraHud.combined);//Una vez que renderiza el juego, se inicia el batch para la camara del HUD y lo dibuja
	    
			//Renderiza ocultables
			hud.render();
			pausaHud.render(jugador);
			dialogoDeCompra.render(jugador);
			inventarioHUD.render(jugador);
			horno.mostarHUD(jugador);
			fundicionHUD.render();
			combinacionJugador.render();
		    
		    if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)) {
		    	combinacionJugador.mostrar();
		    }
		    
		    
		    if(Gdx.input.isKeyJustPressed(Keys.TAB)) {
		    	toggleInventario = !toggleInventario;
		    	if(toggleInventario) {
		    		
		    	inventarioHUD.mostrar();	
		    	}else {
		    		inventarioHUD.ocultar();
		    	}
		    }
		    }
		    
		    if (mineralesManager.comprar(jugador)) {
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
		    		jugador.puedeMoverse = false;
		    		pausaHud.mostrar();
		    		hud.ocultar();
		    		}else {
		    		pausaHud.ocultar();
		    		hud.mostrar();
		    	}
		    }
		    
		if(Gdx.input.isKeyPressed(Keys.E)) {
			jugador.getItems().add(Items.PICO);
		}
		Render.batch.end();
	    //System.out.println(HelpDebug.debub(this.getClass()) + "Hola");

	}


	@Override
	public void resize(int width, int height) {

		camaraJugador.viewportWidth = width;
		camaraJugador.viewportHeight = height;
		camaraJugador.update();	
		combinacionJugador.reEscalar(width, height);
		inventarioHUD.reEscalar(width, height);

		fundicionHUD.reEscalar(width, height);
	    System.out.println(HelpDebug.debub(getClass())+"X =" +Gdx.graphics.getWidth() + " Y =" + Gdx.graphics.getHeight());
	    hud.reEscalar(width, height);
	    cartaHUD.reEscalar(width, height);
	    pausaHud.reEscalar(width, height);

	    npcManager.reEscalarDialogos(width, height);
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
		viejo = new Viejo(32*10,32*12,Recursos.VIEJO, NpcData.VIEJO);
		vendedorAmbulate = new VendedorAmbulante(32*20,32*5,Recursos.VENDEDOR_AMBULANTE, NpcData.VENDEDOR_AMBULANTE);
		vendedorTienda = new VendedorDeTienda(32*12, 32*15, Recursos.VENDEDOR_TIENDA, NpcData.VENDEDOR_TIENDA);
		
		//rey = new Rey(0,0,Recursos.VENDEDOR, NpcData.REY);
	}
	
	private void npcManagerConfig() {
		npcManager = new NPCManager();
		npcManager.agregarEntidad(viejo);
		npcManager.agregarEntidad(vendedorAmbulate);
		npcManager.agregarEntidad(vendedorTienda);
	    npcManager.crearDialogos();
	}
	
	private void mineralesManagerConfig() {
		mineralesManager = new MineralesManager();
		mineralesManager.agregarMineral(piedra);
		mineralesManager.agregarMineral(piedra2);
		mineralesManager.agregarMineral(hierro);
		mineralesManager.agregarMineral(hierro1);
	}
	
	private void colisionesManagerConfig() {

		colisionesManager = new ColisionesManager(jugador);
		colisionesManager.agregarArrayDeColisiones(mineralesManager.getColisiones());
		colisionesManager.agregarArrayDeColisiones(npcManager.getColisiones());
		colisionesManager.agregarColision(horno.getColision());

	}

	public Jugador getJugador1() {
		return jugador;
	}
	
	public MineralesManager getMineralesManager() {
		return mineralesManager;
	}

	public ColisionesManager getColisionesManager() {
		return colisionesManager;
	}

	public void salirDelJuego() {
		game.setScreen(new PantallaMenu(game));
	}
	
	public Horno getHorno() {
		return horno;
	}
}
