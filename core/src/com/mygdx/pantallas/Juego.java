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
import com.mygdx.hud.HUD;
import com.mygdx.hud.InventarioHUD;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Juego implements Screen{
	
	private Jugador jugador;
	private Npc viejo, vendedor;
	private ObjetoDelMapa carta;
	private Mineral piedra, hierro, hierro1, piedra2;
	private Texture jugadorTextura;
	private OrthographicCamera camaraJuego, camaraHud;
	private Dialogo dialogo;
	private NPCManager npcManager;
	private MineralesManager mineralesManager;
	private CartaHUD cartaHUD;
	private InventarioHUD inventarioHUD;
	private DialogoDeCompra dialogoDeCompra;
	private HUD hud;
	
	private Combinacion combinacion;

	private InputMultiplexer mux;
	
	private boolean toggleInventario = false;


	public Juego(final Principal game) {

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
		camaraHud.zoom = .01f;
		
	    //render
		Render.tiledMapRenderer = new OrthogonalTiledMapRenderer(Recursos.MAPA);
		
		//jugador
		jugador = new Jugador(camaraJuego);
				
		//Npc
		crearNPCs();
		npcManagerConfig();

		//objetos del mapa
		piedra = new Piedra(600,600,false,Recursos.PIEDRA);
		piedra2 = new Piedra(600,500,false, Recursos.PIEDRA);
		hierro = new Hierro(632,632,false, Recursos.HIERRO);
		hierro1 = new Hierro(700,32*5,true, Recursos.HIERRO);
		
		
		mineralesManager = new MineralesManager();
		
		mineralesManagerConfig();
		//yunque = new Yunque(532,532,Recursos.YUNQUE);
		
		//HUD
		hud = new HUD(jugador);
		cartaHUD = new CartaHUD(Npc_Dialogos_Rey.CARTA_0);//ee parece que cartaHUD tiene que ir primero, sino no anda la combinacion (nose pq)
	    combinacion = new Combinacion();
	    inventarioHUD = new InventarioHUD();
	    dialogoDeCompra = new DialogoDeCompra();
	    
	    mux.addProcessor(cartaHUD.getStage());
	    mux.addProcessor(combinacion.getStage());//Esto es para los botones de la propia clase
	    mux.addProcessor(dialogoDeCompra.getStage());
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

		jugador.draw(Render.batch);
	    
		npcManager.renderizar(Render.batch);
		npcManager.detectarJugador(jugador); 
		
		mineralesManager.renderizar();
		mineralesManager.detectarJugador(jugador);
		mineralesManager.minar(jugador);
		mineralesManager.comprar(jugador);


		Render.batch.end();

		Render.batch.begin();//HUD´s
		
		

		if(cartaHUD.getCerrar()) {//si ya leyo la carta
			cartaHUD.cerrar();
			jugador.puedeMoverse=true;
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
			inventarioHUD.render(jugador);
			dialogoDeCompra.render(jugador);

		    if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)) {//Esto despues lo tengo que cambiar
		    	combinacion.mostrar();//Abrir Combinacion
		    }
		    
		    
		    if(Gdx.input.isKeyJustPressed(Keys.TAB)) {
		    	toggleInventario = !toggleInventario;
		    	
		    	if(toggleInventario) {
		    		inventarioHUD.mostrar();
		    	}else {
		    		inventarioHUD.ocultar();
		    	}
		    }
		    
		    if(mineralesManager.devolverComprable()) {
		    	dialogoDeCompra.mostrar();

		    }
	    	if(dialogoDeCompra.cerrar) {
	    		mineralesManager.detenerCompra();
	    	}
		    
		    
		    
		}else {
			cartaHUD.render();
		}
		
		//bloquear movimiento del jugador
		if(combinacion.visible) {
			jugador.puedeMoverse = false;
			hud.ocultar();
		}else {
			jugador.puedeMoverse=true;
			hud.mostrar();
		}


		if(Gdx.input.isKeyPressed(Keys.E)) {
			jugador.getItems().add(Items.PICO);
			System.out.println("Otorgado: Pico");
		}
		Render.batch.end();
	    //System.out.println(HelpDebug.debub(this.getClass()) + "Hola");

	}


	@Override
	public void resize(int width, int height) {
		camaraJuego.viewportWidth = width;
		camaraJuego.viewportHeight = height;
		camaraJuego.update();	
		
	    System.out.println(HelpDebug.debub(getClass())+"X =" +Gdx.graphics.getWidth() + " Y =" + Gdx.graphics.getHeight());
	    hud.reEscalar(width, height);
	    cartaHUD.reEscalar(width, height);
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
		dispose();
		
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



}
