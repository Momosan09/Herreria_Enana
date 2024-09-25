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
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.NPCManager;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.entidades.ObjetosDelMapa.AltoHorno;
import com.mygdx.entidades.ObjetosDelMapa.CajaEntregas;
import com.mygdx.entidades.ObjetosDelMapa.Carta;
import com.mygdx.entidades.ObjetosDelMapa.Mesa;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.MineralesManager;
import com.mygdx.entidades.ObjetosDelMapa.ObjetosTallerManager;
import com.mygdx.entidades.ObjetosDelMapa.SoporteArmadura;
import com.mygdx.entidades.ObjetosDelMapa.Yunque;
import com.mygdx.entidades.ObjetosDelMapa.Minable.CarbonMena;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.HierroMena;
import com.mygdx.entidades.ObjetosDelMapa.Minable.PiedraMena;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroPuro;
import com.mygdx.entidades.animales.Rana;
import com.mygdx.entidades.npcs.Carpintero;
import com.mygdx.entidades.npcs.VendedorAmbulante;
import com.mygdx.entidades.npcs.VendedorDeTienda;
import com.mygdx.entidades.npcs.Viejo;
import com.mygdx.entidades.npcs.dialogos.CharlaManager;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.entidades.npcs.dialogos.Npc_Dialogos_Rey;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.enums.Items;
import com.mygdx.game.Principal;
import com.mygdx.historia.TipoMision;
import com.mygdx.hud.CajaEntregasHUD;
import com.mygdx.hud.CartaHUD;
import com.mygdx.hud.Combinacion;
import com.mygdx.hud.Dialogo;
import com.mygdx.hud.DialogoDeCompra;
import com.mygdx.hud.Fundicion;
import com.mygdx.hud.HUD;
import com.mygdx.hud.InventarioHUD;
import com.mygdx.hud.MesaHUD;
import com.mygdx.hud.PausaHUD;
import com.mygdx.hud.SoporteArmaduraHUD;
import com.mygdx.hud.UI;
import com.mygdx.hud.YunqueHUD;
import com.mygdx.io.Entradas;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.HelpMapa;
import com.mygdx.utiles.Iluminacion;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.Tiempo;
import com.mygdx.historia.CartasManager;
import com.mygdx.historia.MisionesDelJuego;
import com.mygdx.historia.MisionesManager;

import box2dLight.RayHandler;
import box2dLight.PointLight;

public class Juego implements Screen{
	
	//Box2d
	private World world;
	private Box2DDebugRenderer box2Debug;
	private HelpMapa helpMapa;
	
	//Box2dLight
	private Iluminacion iluminacion;
	 
	
	//Mapa
	private TiledMap tiledMap;
	private OrganizadorSpritesIndiceZ organizador;
	
	//Entidades
	private Jugador jugador;
	private Carta carta;
	private Npc viejo, vendedorAmbulate, vendedorTienda, carpintero, rey;
	private Texture jugadorTextura;
	private Mineral piedra, hierro, hierro1, piedra2, carbon;
	private AltoHorno altoHorno;
	private SoporteArmadura soporteArmadura;
	private Yunque yunque;
	private Mesa mesa;
	private CajaEntregas cajaEntregas;
	
	
	//Managers
	private NPCManager npcManager;
	private MineralesManager mineralesManager;
	private MisionesManager misionesManager;
	private ObjetosTallerManager objetosDelTallerManager;

	//Camaras
	private OrthographicCamera camaraJugador, camaraHud;

	//Scene2d.ui
	private UI ui;
	
	//Entradas 
	private Entradas entradas;
	
	//Charlas
	public CharlaManager charlaManager;
	
	//Screens
	private final Principal game;

	public Juego(final Principal game) {
		this.game = game;
		Gdx.input.setInputProcessor(Recursos.muxJuego);
	}

	@Override
	public void show() {	
		//Box2d
		helpMapa = new HelpMapa(this);
		this.world = new World(new Vector2(0,0), false);


		this.box2Debug = new Box2DDebugRenderer();
		Render.tiledMapRenderer = helpMapa.Inicializar();
		MundoConfig.anchoMundo = helpMapa.getCantTilesAncho();
		MundoConfig.altoMundo = helpMapa.getCantTilesAlto();

		
		//camaras
		camaraJugador = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		camaraJugador.setToOrtho(false);
		camaraJugador.zoom = .4f;

		
		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraHud.setToOrtho(false); 
		camaraHud.zoom = .4f;
		

		jugador = new Jugador(camaraJugador, world, helpMapa.getJugadorSpawn());


		ui = new UI(jugador,this);//Ui tiene que ir antes que iluminacion por el orden en el que se cargan los listeners TODO arreglar eso
		Render.rayHandler = new RayHandler(world);
		iluminacion = new Iluminacion(world, camaraJugador);
				
    	
		//Npc
		crearNPCs();
		npcManagerConfig();
		charlaManagerConfig();
		
		//objetos del mapa
		piedra = new PiedraMena(20,16, world,false);//Eem los minerales voy a tener que hacer algun tipo de manager que los spawnee de manera aleatoria en alguna zona permitida
		hierro = new HierroMena(20,20, world,false);
		hierro1 = new HierroMena(7,5, world,true);
		piedra2 = new PiedraMena(18,18, world,false);
		carbon = new CarbonMena(23, 32, world, false);
		
		crearObjetosDelTaller();	
				
		mineralesManagerConfig();
		misionesMangerConfig();
		objetosDleTallerManagerConfig();
		
		//InputMultiplexer
			
	   // Recursos.muxJuego.addProcessor(altoHorno.getHUD().getStage());
//	    Recursos.muxJuego.addProcessor(ui.getHUD().getStage());
//	    Recursos.muxJuego.addProcessor(ui.getHUD().getDiarioHUD().getStage());

	    /*
		jugador.agregarMision(viejo, TipoMision.RECOLECTAR, TipoMinerales.HIERRO.toString(), 1, 1,50,300);
		jugador.agregarMision(viejo, TipoMision.RECOLECTAR, TipoMinerales.PIEDRA.toString(), 2,0,10,50);
		*/
	    
		jugador.getMinerales().add(hierro);
		jugador.getMinerales().add(hierro1);
		
		entradas = new Entradas(jugador);
		carta = new Carta(36, 12, world, Recursos.CARTA, jugador);
		
		MundoConfig.estadoJuego = EstadosDelJuego.JUEGO;
	}

	@Override
	public void render(float delta){
		entradas.estadosDelJuego();
		Tiempo.contarSegundosEnEstadoJuego();//Cuenta el tiempo que EstadoJuego != PAUSA
		 
		//System.out.println(HelpDebug.debub(getClass()) + MundoConfig.apretoE);

		//System.out.println(MundoConfig.estadoJuego);
		//hacer cosas dependiendo de los estados del juego
		/*
		switch (MundoConfig.estadoJuego) {
		case INVENTARIO:
			inventarioHUD.mostrar();
			break;

		case PAUSA:

			break;
		case JUEGO:

			inventarioHUD.ocultar();
			ui.ocultarLibro();
			jugador.puedeMoverse = true;
			MundoConfig.mostrarHUD = true;
			MundoConfig.pausarTiempo = false;
			break;
			
		case INVENTARIO_BATALLAS:	
			ui.mostrarLibro();
			break;
		}*/
		
		//DEBUG Y COSAS TEMPORALES (despues no van a estar mas)
		if(Gdx.input.isKeyPressed(Keys.P)) {//para debug
			camaraJugador.zoom = 5;
		}else {
			camaraJugador.zoom = .4f;
		}
	    
	  
		
//		if (Gdx.input.isKeyJustPressed(Keys.NUM_1) && MundoConfig.habilitadoHUDS) {
//			toggleBarraItems1 = !toggleBarraItems1;
//			if (toggleBarraItems1) {
//				jugador.getItems().add(Items.PICO);
//			} else {
//				jugador.getItems().clear();
//			}
//		}
		
		
		//GAMELOOP
		Render.batch.begin();
		//iluminacion.render(camaraJugador);
		world.step(1/60f, 6, 2);
		Render.tiledMapRenderer.setView(camaraJugador);
		Render.tiledMapRenderer.render(helpMapa.getCapasDeFondo());
		box2Debug.render(world, camaraJugador.combined);
		Render.batch.end();

		
		Render.batch.begin();
		camaraJugador.update();
		Render.batch.setProjectionMatrix(camaraJugador.combined);
		Render.batch.end();

		//Managers
		Render.batch.begin();
		//npcManager.renderizar();
		//mineralesManager.renderizar();
		objetosDelTallerManager.renderizar();
		
	
		carta.draw();
		carta.detectarJugador(jugador);
		
		
		npcManager.detectarJugador(jugador);
		mineralesManager.detectarJugador(jugador);
		objetosDelTallerManager.detectarJugador(jugador);
		
		mineralesManager.minar(jugador);
		mineralesManager.limpiarMinerales(world);
		mineralesManager.comprar(jugador);

		//jugador.draw(Render.batch);
		
		organizador.dibujarYComparar(jugador);
		
		Render.batch.end();
		
		
		Render.tiledMapRenderer.render(helpMapa.getCapasDeFrente());// Estas son las capas que esconden al jugador
		iluminacion.render(camaraJugador);

		Render.batch.begin();// HUD´s
		ui.render();
		
			charlaManager.checkearCharlas(vendedorTienda, vendedorAmbulate, viejo, carpintero);
			// Renderiza el HUD
			camaraHud.update();
			Render.batch.setProjectionMatrix(camaraHud.combined);// Una vez que renderiza el juego, se inicia el batch
																	// para la camara del HUD y lo dibuja


	
		misionesManager.checkearMisiones();//Se fija si las condiciones se cumplen
		Render.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camaraJugador.viewportWidth = width;
		camaraJugador.viewportHeight = height;
		camaraJugador.update();	
		
		ui.reEscalar(width, height);
	    
		//hud.reEscalar(width, height);
	    System.out.println(HelpDebug.debub(getClass())+"X =" +Gdx.graphics.getWidth() + " Y =" + Gdx.graphics.getHeight());

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

	
	public void crearNPCs() {
		viejo = new Viejo(19,34, world,Recursos.VIEJO, NpcData.VIEJO);
		vendedorTienda = new VendedorDeTienda(12,33.5f, world,Recursos.VENDEDOR_TIENDA, NpcData.VENDEDOR_TIENDA);
		vendedorAmbulate = new VendedorAmbulante(22,40, world,Recursos.VENDEDOR_AMBULANTE, NpcData.VENDEDOR_AMBULANTE);
		carpintero = new Carpintero(6,5, world, Recursos.CARPINTERO, NpcData.CARPINTERO);
//		rey = new Rey(0,0,Recursos.VENDEDOR, NpcData.REY);
	}
	
	public void crearObjetosDelTaller() {
		mesa = new Mesa(39, 15, world, Recursos.MESA, jugador);
		yunque = new Yunque(34, 13, world, Recursos.YUNQUE, jugador);
		altoHorno = new AltoHorno(34, 10, world, Recursos.ALTO_HORNO, jugador); //Estas coordenadas las saco de Tiled
		cajaEntregas = new CajaEntregas(39, 15.5f, world, Recursos.CAJA_ENTREGAS, jugador);
		soporteArmadura = new SoporteArmadura(32, 18, world, Recursos.SOPORTE_ARMADURAS, jugador);
		
	}
	
	private void objetosDleTallerManagerConfig() {
		objetosDelTallerManager = new ObjetosTallerManager();
		objetosDelTallerManager.agregarObjeto(mesa);
		objetosDelTallerManager.agregarObjeto(yunque);
		objetosDelTallerManager.agregarObjeto(altoHorno);
		objetosDelTallerManager.agregarObjeto(cajaEntregas);
		objetosDelTallerManager.agregarObjeto(soporteArmadura);
		
		
	}
	

	
	private void npcManagerConfig() {
		npcManager = new NPCManager();
		npcManager.agregarEntidad(viejo);
		npcManager.agregarEntidad(vendedorAmbulate);
		npcManager.agregarEntidad(vendedorTienda);
		npcManager.agregarEntidad(carpintero);
	}
	private void charlaManagerConfig() {
		charlaManager = new CharlaManager(jugador, vendedorTienda, vendedorAmbulate, viejo, carpintero);
	}
	
	private void mineralesManagerConfig() {
		mineralesManager = new MineralesManager();
		mineralesManager.agregarMineral(piedra);
		mineralesManager.agregarMineral(hierro);
		mineralesManager.agregarMineral(hierro1);
		mineralesManager.agregarMineral(piedra2);
		mineralesManager.agregarMineral(carbon);
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
		Recursos.muxJuego.clear();// Pero aca voy a tener un prblema si uso el mismo muxJuego para las otras partes del
								// juego que no sean de la pantalla juego (pantallaMenu, etc) tengo que tener
								// cuidado
		game.setScreen(new PantallaMenu(game));
	}


	public World getWorld() {
		return world;
	}

	 
	 
		@Override
		public void dispose() {
			Render.tiledMapRenderer.dispose();
			ui.dispose();
			Recursos.muxJuego.clear();
		}

}
