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
import com.mygdx.utiles.DibujarFiguras;
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
	private Horno horno;
	
	
	//Managers
	private NPCManager npcManager;
	private MineralesManager mineralesManager;

	//Camaras
	private OrthographicCamera camaraJugador1, camaraJugador2, camaraHud;

	//Scene2d.ui
	private HUD hud;
	private Dialogo dialogo;
	private CartaHUD cartaHUD;
	private PausaHUD pausaHud;
	private Combinacion combinacionJugador1, combinacionJugador2;
	private InventarioHUD inventarioHUD1, inventarioHUD2;
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
	private boolean red = false;
	HiloCliente hc;
	public int idJugador = 1;
	public boolean frenarGameLoop = false;
	
	//Colisiones
	private ColisionesManager colisionesManager;

	public Juego(final Principal game, boolean red) {
		this.game = game;
		UtilesRed.game = this;
		this.red = red;
		
	}

	@Override
	public void show() {
		mux = new InputMultiplexer();//El input multiplexer es una especie de gestor de inputProcessors
		
		//camaras
		
		camaraJugador1 = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraJugador1.setToOrtho(false);
		camaraJugador1.zoom = .6f;

		
		
		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraHud.setToOrtho(false); 
		camaraHud.zoom = .6f;
		
	    //render
		Render.tiledMapRenderer = new OrthogonalTiledMapRenderer(Recursos.MAPA);
		
		//jugador
		if(red) {
			hc = UtilesRed.hc;
			camaraJugador2 = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			camaraJugador2.setToOrtho(false);
			camaraJugador2.zoom = .6f;
			jugador_1 = new Jugador(camaraJugador1,hc, Recursos.JUGADOR1_SPRITESHEET);
			jugador_2 = new Jugador(camaraJugador2,hc,Recursos.JUGADOR2_SPRITESHEET);

			UtilesRed.hc.setGame(this);//Le paso el juego porque sino el juego que le entra por constructor (al estatico) vale nulo
			UtilesRed.hc = hc;
		}else {
			jugador_1 = new Jugador(camaraJugador1);

		}
		
	
				
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
	   
	    inventarioHUD1 = new InventarioHUD(jugador_1);
	    inventarioHUD2 = new InventarioHUD(jugador_2);
	    
	    if(idJugador==0) {
			hud = new HUD(jugador_2);
			fundicionHUD = new Fundicion(jugador_2);
			combinacionJugador2 = new Combinacion(jugador_2);
	    	mux.addProcessor(combinacionJugador2.getStage());
	    	mux.addProcessor(combinacionJugador2.getDragAndDrop().getStage());//Esto es para las imagenes arratrables que tiene el stage del dragAndDrop de esta clase, si quiero poner otro dragAndDrop tengo q ue agregarlo asi
	    }else if(idJugador==1) {
			hud = new HUD(jugador_1);
			fundicionHUD = new Fundicion(jugador_1);
			
			combinacionJugador1 = new Combinacion(jugador_1);
	    	mux.addProcessor(combinacionJugador1.getStage());
	    	mux.addProcessor(combinacionJugador1.getDragAndDrop().getStage());
	    }
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
	public void render(float delta) {

		if(!frenarGameLoop) {
			
		Render.batch.begin();
		Render.tiledMapRenderer.render();
		Render.batch.end();

	    //Renderiza el Juego
		Render.batch.begin();
//		DibujarFiguras.dibujarRectanguloLleno(colisionPrueba.x, colisionPrueba.y, colisionPrueba.width, colisionPrueba.height, Color.CYAN);
			
		if(idJugador==1 || !red) {//Para determinar que camara es la que se usa en el batch
			camaraJugador1.update();
			Render.batch.setProjectionMatrix(camaraJugador1.combined);
			Render.tiledMapRenderer.setView(camaraJugador1);
//			System.out.println(HelpDebug.debub(getClass())+"Esta es la camara del cliente 0");
		}else if(idJugador==0) {
			Render.batch.setProjectionMatrix(camaraJugador2.combined);
			Render.tiledMapRenderer.setView(camaraJugador2);
			camaraJugador2.update();
//			System.out.println(HelpDebug.debub(getClass())+"Esta es la camara del cliente 1");
		}else if(idJugador == -1) {
//			System.out.println(HelpDebug.debub(getClass())+"La camara es -1");
		}
		
	

		jugador_1.draw(Render.batch);
		

		//Render.batch.setProjectionMatrix(camaraJugador2.combined);
		if(red) {
			jugador_2.draw(Render.batch);			
		}
		colisionesManager.checkearColisiones();

		
		Render.batch.end();
		Render.batch.begin();
		
		npcManager.renderizar(Render.batch);
		npcManager.detectarJugador(jugador_1); 
		
		mineralesManager.renderizar();

		if(idJugador == 0) {
			npcManager.detectarJugador(jugador_2); 
			mineralesManager.detectarJugador(jugador_2);
			mineralesManager.minar(jugador_2);
			mineralesManager.comprar(jugador_2);
			horno.detectarJugador(jugador_2);
			mineralesManager.limpiarMinerales(colisionesManager,true);
		}else {
			mineralesManager.detectarJugador(jugador_1);
			mineralesManager.minar(jugador_1);
			mineralesManager.comprar(jugador_1);
			horno.detectarJugador(jugador_1);
		}
		
		horno.draw();
		mineralesManager.limpiarMinerales(colisionesManager,false);


		Render.batch.end();

		Render.batch.begin();//HUD´s
		
		

		if(cartaHUD.getCerrar()) {//si ya leyo la carta...
			cartaHUD.cerrar();
			jugador_1.puedeMoverse=true;
			/*
			if(red && idJugador ==0) {
				jugador_2.puedeMoverse = true;
			}
			*/
			//npcManager.mostrarDialogo(Render.batch,0);//Aca tengo que modificar, pq todos los npcs me muestran el primer mensaje
			vendedor.charla(1);
			viejo.charla(0);
			//vendedor.getData().getMensaje(0);
	    
			//Renderiza el HUD
			camaraHud.update();
			Render.batch.setProjectionMatrix(camaraHud.combined);//Una vez que renderiza el juego, se inicia el batch para la camara del HUD y lo dibuja
	    

			//Renderiza ocultables
			if(red && idJugador == 0) {
				hud.render();
				pausaHud.render(jugador_2);
				dialogoDeCompra.render(jugador_2);
				inventarioHUD2.render(jugador_2);
				horno.mostarHUD(jugador_2);
				fundicionHUD.render();
				combinacionJugador2.render();
			}else {
				hud.render();
				pausaHud.render(jugador_1);
				dialogoDeCompra.render(jugador_1);
				inventarioHUD1.render(jugador_1);
				horno.mostarHUD(jugador_1);
				fundicionHUD.render();
				combinacionJugador1.render();
				
			}

			
			
		    if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT) && red && idJugador == 0) {//Esto despues lo tengo que cambiar
		    		combinacionJugador2.mostrar();
		    }
		    
		    if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT) && red && idJugador == 1) {
		    	combinacionJugador1.mostrar();
		    }
		    
		    if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT) && !red) {
		    	combinacionJugador1.mostrar();
		    }
		    
		    
		    if(Gdx.input.isKeyJustPressed(Keys.TAB)) {
		    	toggleInventario = !toggleInventario;
		    	
		    	if(toggleInventario) {
		    		if(idJugador==0) {
		    			inventarioHUD2.mostrar();
		    		}else {
		    			inventarioHUD1.mostrar();		    			
		    		}
		    	}else {
		    		if(idJugador==0) {
		    			inventarioHUD2.ocultar();
		    		}else {
		    			inventarioHUD1.ocultar();
		    		}
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
		    		if(red && idJugador ==0) {
		    			jugador_2.puedeMoverse = false;
		    		}
		    	
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

		



		if(Gdx.input.isKeyPressed(Keys.E)) {
			if(idJugador == 1 ||!red) {
				jugador_1.getItems().add(Items.PICO);
//				System.out.println("Otorgado: Pico");				
			}else {
				jugador_2.getItems().add(Items.PICO);
//				System.out.println("Otorgado: Pico al jugador 2");
			}
		}
		Render.batch.end();
	    //System.out.println(HelpDebug.debub(this.getClass()) + "Hola");
		jugador_1.puedeMoverse = true;
		if(red && idJugador ==0) {
			jugador_2.puedeMoverse = false;
		}
		}else {
			this.game.setScreen(new PantallaMenu(game, true));
		}

	}


	@Override
	public void resize(int width, int height) {


		if(idJugador == 0) {
			camaraJugador2.viewportWidth = width;
			camaraJugador2.viewportHeight = height;
			camaraJugador2.update();	
		    combinacionJugador2.reEscalar(width, height);
			inventarioHUD2.reEscalar(width, height);
		}else {
			camaraJugador1.viewportWidth = width;
			camaraJugador1.viewportHeight = height;
			camaraJugador1.update();	
		    combinacionJugador1.reEscalar(width, height);
			inventarioHUD1.reEscalar(width, height);
		}
		

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
		viejo = new Viejo(32*10,32*15,Recursos.VIEJO, NpcData.VIEJO);
		vendedor = new Vendedor(32*20,32*5,Recursos.VENDEDOR, NpcData.VENDEDOR);
		//rey = new Rey(0,0,Recursos.VENDEDOR, NpcData.REY);
	}
	
	private void npcManagerConfig() {
		npcManager = new NPCManager();
		npcManager.agregarEntidad(viejo);
		npcManager.agregarEntidad(vendedor);
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
		if(idJugador==0) {
			colisionesManager = new ColisionesManager(jugador_2);
		}else {
			colisionesManager = new ColisionesManager(jugador_1);
		}
		colisionesManager.agregarArrayDeColisiones(mineralesManager.getColisiones());
		colisionesManager.agregarArrayDeColisiones(npcManager.getColisiones());
		colisionesManager.agregarColision(horno.getColision());

	}

	public Jugador getJugador1() {
		return jugador_1;
	}
	
	public Jugador getJugador2() {
		return jugador_2;
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
	
	public boolean isRed() {
		return red;
	}
	
	public Horno getHorno() {
		return horno;
	}
}
