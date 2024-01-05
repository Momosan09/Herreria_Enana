package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.NPCManager;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.entidades.ObjetosDelMapa.Horno;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.MineralesManager;
import com.mygdx.entidades.ObjetosDelMapa.Minable.Hierro;
import com.mygdx.entidades.ObjetosDelMapa.Minable.Piedra;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.entidades.npcs.VendedorAmbulante;
import com.mygdx.entidades.npcs.VendedorDeTienda;
import com.mygdx.entidades.npcs.Viejo;
import com.mygdx.entidades.npcs.dialogos.CharlaManager;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.entidades.npcs.dialogos.Npc_Dialogos_Rey;
import com.mygdx.enums.Items;
import com.mygdx.game.Principal;
import com.mygdx.historia.TipoMision;
import com.mygdx.hud.CartaHUD;
import com.mygdx.hud.Combinacion;
import com.mygdx.hud.Dialogo;
import com.mygdx.hud.DialogoDeCompra;
import com.mygdx.hud.Fundicion;
import com.mygdx.hud.HUD;
import com.mygdx.hud.InventarioHUD;
import com.mygdx.hud.PausaHUD;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.HelpMapa;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.historia.MisionesManager;

import box2dLight.RayHandler;
import box2dLight.PointLight;

public class Juego implements Screen{
	
	//Box2d
	private World world;
	private Box2DDebugRenderer box2Debug;
	private HelpMapa helpMapa;
	
	//Box2dLight
	private RayHandler rayHandler;
	PointLight pl, pl2;
	
	//Mapa
	private TiledMap tiledMap;
	
	
	//Entidades
	private Jugador jugador;
	private ObjetoDelMapa carta;
	private Npc viejo, vendedorAmbulate, vendedorTienda, rey;
	private Texture jugadorTextura;
	private Mineral piedra, hierro, hierro1, piedra2;
	private Horno horno;
	
	//Tiempo
	private int diaDelMundo = 3;
	private float horaDelMundo = 0;
	private float minutoDelMundo = 0;
	
	//Managers
	private NPCManager npcManager;
	private MineralesManager mineralesManager;
	private MisionesManager misionesManager;

	//Camaras
	private OrthographicCamera camaraJugador, camaraHud;

	//Scene2d.ui
	private HUD hud;
	private CartaHUD cartaHUD;
	private PausaHUD pausaHud;
	private Combinacion combinacionJugador;
	private InventarioHUD inventarioHUD;
	private DialogoDeCompra dialogoDeCompra;
	private Fundicion fundicionHUD;
	
	//Charlas
	public CharlaManager charlaManager;
	
	//Toggles (referido a HUDs), los uso cuando ese hud no se cierra con boton
	private boolean toggleInventario = false;
	private boolean togglePausa = false;
	private boolean toggleBarraItems1 = false;
	
	//Screens
	private final Principal game;

	public Juego(final Principal game) {
		this.game = game;

	}

	@Override
	public void show() {	
		//Box2d
		helpMapa = new HelpMapa(this);
		this.world = new World(new Vector2(0,0), false);
		rayHandler = new RayHandler(world);
		iluminacion();
		
		
		this.box2Debug = new Box2DDebugRenderer();
		Render.tiledMapRenderer = helpMapa.Inicializar();

		
		//camaras
		camaraJugador = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraJugador.setToOrtho(false);
		camaraJugador.zoom = .6f;
		rayHandler.setCombinedMatrix(camaraJugador);
		
		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraHud.setToOrtho(false); 
		camaraHud.zoom = .6f;
		

		jugador = new Jugador(camaraJugador, world, helpMapa.getJugadorSpawn());

		combinacionJugador = new Combinacion(jugador);
		
    	Recursos.mux.addProcessor(combinacionJugador.getStage());
    	Recursos.mux.addProcessor(combinacionJugador.getDragAndDrop().getStage());
				
		//Npc
		crearNPCs();
		npcManagerConfig();
		charlaManagerConfig();
		
		//objetos del mapa
		piedra = new Piedra(32*20,32*16, world,false,Recursos.PIEDRA);
		piedra2 = new Piedra(32*18,32*18, world,false, Recursos.PIEDRA);
		hierro = new Hierro(32*20,32*20, world,false, Recursos.HIERRO);
		hierro1 = new Hierro(32*7,32*5, world,true, Recursos.HIERRO);
		
				
		mineralesManagerConfig();
		misionesMangerConfig();


		
		//HUD

		cartaHUD = new CartaHUD(Npc_Dialogos_Rey.CARTA_0);//ee parece que cartaHUD tiene que ir primero, sino no anda la combinacion (nose pq)

	    dialogoDeCompra = new DialogoDeCompra();
	    pausaHud = new PausaHUD(game);
	    
	    Recursos.mux.addProcessor(cartaHUD.getStage());
	    Recursos.mux.addProcessor(pausaHud.getStage());
	    //mux.addProcessor(dialogoDeCompra.getStage());
	   
	    inventarioHUD = new InventarioHUD(jugador);
	    
		hud = new HUD(jugador, this);
		fundicionHUD = new Fundicion(jugador);
			
	    horno = new Horno(32*22,32*10, world, Recursos.HORNO, fundicionHUD);
	    Recursos.mux.addProcessor(horno.getHUD().getStage());
	    Recursos.mux.addProcessor(hud.getStage());
	    Recursos.mux.addProcessor(hud.getResultadosBatallasHUD().getStage());
	    Recursos.mux.addProcessor(hud.getProximaBatallaHUD().getStage());
	    Recursos.mux.addProcessor(hud.getDiarioHUD().getStage());

	    
		Gdx.input.setInputProcessor(Recursos.mux);
		
		jugador.agregarMision(viejo, TipoMision.RECOLECTAR, TipoMinerales.HIERRO.toString(), 1, 1,50,300);
		jugador.agregarMision(viejo, TipoMision.RECOLECTAR, TipoMinerales.PIEDRA.toString(), 2,0,10,50);



	}

	@Override
	public void render(float delta){
		horaDelMundo();
		Render.batch.begin();
		
		rayHandler.update();
		world.step(1/60f, 6, 2);
		Render.tiledMapRenderer.setView(camaraJugador);
		Render.tiledMapRenderer.render(helpMapa.getCapasDeFondo());
		box2Debug.render(world, camaraJugador.combined);
		rayHandler.setCombinedMatrix(camaraJugador.combined,0,0,1,1);



		
		Render.batch.end();

	    //Renderiza el Juego
		Render.batch.begin();
			
		camaraJugador.update();
		Render.batch.setProjectionMatrix(camaraJugador.combined);
		
		Render.batch.end();
		
		Render.batch.begin();
		
		npcManager.renderizar(Render.batch);
		npcManager.detectarJugador(jugador);

		
		mineralesManager.renderizar();
		mineralesManager.detectarJugador(jugador);
		mineralesManager.minar(jugador);
		mineralesManager.limpiarMinerales(world);
		mineralesManager.comprar(jugador);
		horno.detectarJugador(jugador);
		horno.draw();

		jugador.draw(Render.batch);
		
		Render.batch.end();
		
		Render.tiledMapRenderer.render(helpMapa.getCapasDeFrente());//Estas son las capas que esconden al jugador
		rayHandler.render();
		
		Render.batch.begin();//HUD´s
		
		

		
		cartaHUD.render();
		if(cartaHUD.getCerrar()) {//si ya leyo la carta...
			cartaHUD.cerrar();
			jugador.puedeMoverse=true;
	    
			npcManager.mostrarDialogo();//DEJALO ACA
			charlaManager.checkearCharlas(vendedorTienda, vendedorAmbulate, viejo);
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
		    
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			toggleBarraItems1 = !toggleBarraItems1;
			if(toggleBarraItems1) {
				jugador.getItems().add(Items.PICO);				
			}else {
				jugador.getItems().clear();
			}
		}
		Render.batch.end();
	    //System.out.println(HelpDebug.debub(this.getClass()) + "Hola");
		misionesManager.checkearMisiones();

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
		rayHandler.dispose();
	}
	
	public void crearNPCs() {
		viejo = new Viejo(32*10,32*12, world,Recursos.VIEJO, NpcData.VIEJO);
		vendedorAmbulate = new VendedorAmbulante(32*20,32*5, world,Recursos.VENDEDOR_AMBULANTE, NpcData.VENDEDOR_AMBULANTE);
		vendedorTienda = new VendedorDeTienda(32*12, 32*15.5f, world,Recursos.VENDEDOR_TIENDA, NpcData.VENDEDOR_TIENDA);
//		rey = new Rey(0,0,Recursos.VENDEDOR, NpcData.REY);
	}
	
	private void npcManagerConfig() {
		npcManager = new NPCManager();
		npcManager.agregarEntidad(viejo);
		npcManager.agregarEntidad(vendedorAmbulate);
		npcManager.agregarEntidad(vendedorTienda);
	    npcManager.crearDialogos();

	}
	private void charlaManagerConfig() {
		charlaManager = new CharlaManager(vendedorTienda, vendedorAmbulate, viejo);
	}
	
	private void mineralesManagerConfig() {
		mineralesManager = new MineralesManager();
		mineralesManager.agregarMineral(piedra);
		mineralesManager.agregarMineral(piedra2);
		mineralesManager.agregarMineral(hierro);
		mineralesManager.agregarMineral(hierro1);
	}
	
	private void misionesMangerConfig() {
		misionesManager = new MisionesManager(jugador);
		misionesManager.agregarMision();
	}
	
	

	public Jugador getJugador1() {
		return jugador;
	}
	
	public MineralesManager getMineralesManager() {
		return mineralesManager;
	}

	public void salirDelJuego() {
		game.setScreen(new PantallaMenu(game));
	}
	
	public Horno getHorno() {
		return horno;
	}
	 public World getWorld() {
		 return world;
	 }
	 
	 private void iluminacion() {
		
			rayHandler.setBlurNum(3);
			rayHandler.setShadows(true);
			pl = new PointLight(rayHandler, 128, new Color(Color.valueOf("#ea8e0e")), 200,300,300);
			pl.setStaticLight(false);
			pl.setSoft(true);
			rayHandler.setCulling(false);  // Esto es lo que me hace que no se vean las luces que inicien fuera de los bordes de la pantalla
	
	 }
	 
	 private void horaDelMundo() { 
		 minutoDelMundo+=0.5f;
		 if(minutoDelMundo>= 60) {
			 horaDelMundo++; 
			 minutoDelMundo=0;
		 }
		 if(horaDelMundo >24) {
			 diaDelMundo++;
			 horaDelMundo=0;
		 }
		 if(diaDelMundo>7) {
			 diaDelMundo=1;
		 }
		 
		 
		 if(horaDelMundo>=0 && horaDelMundo < 4.5f) {
			 rayHandler.setAmbientLight(.2f);
		 }else if( horaDelMundo < 12) {
			 rayHandler.setAmbientLight(.7f);
		 }else if(horaDelMundo < 15) {
			 rayHandler.setAmbientLight(1f);
		 }else if(horaDelMundo < 17) {
			 rayHandler.setAmbientLight(.6f);
		 }else if(horaDelMundo < 20) {
			 rayHandler.setAmbientLight(.4f);
		 }else if (horaDelMundo <= 24) {
			 rayHandler.setAmbientLight(.2f);
		 }
			 
			 
//		 System.out.println(HelpDebug.debub(getClass())+horaDelMundo);
	 }
	 
	 public int getDia() {
		 return diaDelMundo;
	 }
	 public float getHora() {
		 return horaDelMundo;
	 }
	 public float getMinuto() {
		 return minutoDelMundo;
	 }
	 

}
