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
import com.mygdx.entidades.ObjetosDelMapa.AltoHorno;
import com.mygdx.entidades.ObjetosDelMapa.CajaEntregas;
import com.mygdx.entidades.ObjetosDelMapa.Horno;
import com.mygdx.entidades.ObjetosDelMapa.Mesa;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.MineralesManager;
import com.mygdx.entidades.ObjetosDelMapa.ObjetosTallerManager;
import com.mygdx.entidades.ObjetosDelMapa.SoporteArmadura;
import com.mygdx.entidades.ObjetosDelMapa.Yunque;
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
import com.mygdx.hud.YunqueHUD;
import com.mygdx.utiles.MundoConfig;
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
	private AltoHorno altoHorno;
	private SoporteArmadura soporteArmadura;
	private Yunque yunque;
	private Mesa mesa;
	private CajaEntregas cajaEntregas;
	
	
	//Tiempo
	private int diaDelMundo = 3;
	private float horaDelMundo = 10;
	private float minutoDelMundo = 0;
	
	//Managers
	private NPCManager npcManager;
	private MineralesManager mineralesManager;
	private MisionesManager misionesManager;
	private ObjetosTallerManager objetosDelTallerManager;

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
	private MesaHUD mesaHUD;
	private SoporteArmaduraHUD soporteArmaduraHUD;
	private CajaEntregasHUD cajaEntregasHUD;
	private YunqueHUD yunqueHUD;
	
	
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
		Gdx.input.setInputProcessor(Recursos.muxJuego);
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
		MundoConfig.anchoMundo = helpMapa.getCantTilesAncho();
		MundoConfig.altoMundo = helpMapa.getCantTilesAlto();

		
		//camaras
		camaraJugador = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		camaraJugador.setToOrtho(false);
		camaraJugador.zoom = .6f;
		rayHandler.setCombinedMatrix(camaraJugador);
		
		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraHud.setToOrtho(false); 
		camaraHud.zoom = .6f;
		

		jugador = new Jugador(camaraJugador, world, helpMapa.getJugadorSpawn());

		combinacionJugador = new Combinacion(jugador);
		
    	Recursos.muxJuego.addProcessor(combinacionJugador.getStage());
    	Recursos.muxJuego.addProcessor(combinacionJugador.getDragAndDrop().getStage());
				
		//HUD

		cartaHUD = new CartaHUD(Npc_Dialogos_Rey.CARTA_0);//ee parece que cartaHUD tiene que ir primero, sino no anda la combinacion (nose pq)

	    dialogoDeCompra = new DialogoDeCompra();
	    pausaHud = new PausaHUD(this);
	    
	    Recursos.muxJuego.addProcessor(cartaHUD.getStage());
	    Recursos.muxJuego.addProcessor(pausaHud.getStage());
	    //muxJuego.addProcessor(dialogoDeCompra.getStage());
	   
	    inventarioHUD = new InventarioHUD(jugador);
	    
		hud = new HUD(jugador, this);
		mesaHUD = new MesaHUD(jugador);
		yunqueHUD = new YunqueHUD(jugador);
		fundicionHUD = new Fundicion(jugador);
		cajaEntregasHUD = new CajaEntregasHUD(jugador);
		soporteArmaduraHUD = new SoporteArmaduraHUD(jugador);
    	
		//Npc
		crearNPCs();
		npcManagerConfig();
		charlaManagerConfig();
		
		//objetos del mapa
		piedra = new Piedra(20,16, world,false,Recursos.PIEDRA);//Eem los minerales voy a tener que hacer algun tipo de manager que los spawnee de manera aleatoria en alguna zona permitida
		hierro = new Hierro(20,20, world,false, Recursos.HIERRO);
		hierro1 = new Hierro(7,5, world,true, Recursos.HIERRO);
		piedra2 = new Piedra(18,18, world,false, Recursos.PIEDRA);
		
		crearObjetosDelTaller();	
				
		mineralesManagerConfig();
		misionesMangerConfig();
		objetosDleTallerManagerConfig();
		
		//InputMultiplexer
			
	    Recursos.muxJuego.addProcessor(altoHorno.getHUD().getStage());
	    Recursos.muxJuego.addProcessor(hud.getStage());
	    Recursos.muxJuego.addProcessor(hud.getDiarioHUD().getStage());

		jugador.agregarMision(viejo, TipoMision.RECOLECTAR, TipoMinerales.HIERRO.toString(), 1, 1,50,300);
		jugador.agregarMision(viejo, TipoMision.RECOLECTAR, TipoMinerales.PIEDRA.toString(), 2,0,10,50);
		
		jugador.getMinerales().add(hierro);
		jugador.getMinerales().add(hierro1);
		
	}

	@Override
	public void render(float delta){
		//DEBUG Y COSAS TEMPORALES (despues no van a estar mas)
		if(Gdx.input.isKeyPressed(Keys.P)) {//para debug
			camaraJugador.zoom = 5;
		}else {
			camaraJugador.zoom = .6f;
		}
		
		//UNA SOLA VEZ
		if(cartaHUD.seCerro()) {
			cartaHUD.cerrar();
			jugador.puedeMoverse = true;
			MundoConfig.mostrarHUD = true;
			MundoConfig.habilitadoHUDS = true;
		}
		
		//CONDICIONES POR TECLAS
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE) && MundoConfig.habilitadoHUDS) {
			togglePausa = !togglePausa;
			if (togglePausa) {
				pausaHud.mostrar();
				jugador.puedeMoverse = false;
				MundoConfig.mostrarHUD = false;
				MundoConfig.pausarTiempo = true;
			} else {
				pausaHud.ocultar();
				jugador.puedeMoverse = true;
				MundoConfig.mostrarHUD = true;
				MundoConfig.pausarTiempo = false;
			}
		}
		
	    if(Gdx.input.isKeyJustPressed(Keys.TAB) && MundoConfig.habilitadoHUDS) {
	    	toggleInventario = !toggleInventario;
	    	if(toggleInventario) {
	    	inventarioHUD.mostrar();	
	    	}else {
	    	inventarioHUD.ocultar();
	    	}
	    }
		
		
		if (Gdx.input.isKeyJustPressed(Keys.NUM_1) && MundoConfig.habilitadoHUDS) {
			toggleBarraItems1 = !toggleBarraItems1;
			if (toggleBarraItems1) {
				jugador.getItems().add(Items.PICO);
			} else {
				jugador.getItems().clear();
			}
		}
		
	    if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT) && MundoConfig.habilitadoHUDS) {
	    	combinacionJugador.mostrar();
	    }
		
		//GAMELOOP
		horaDelMundo();
		Render.batch.begin();
		rayHandler.update();
		world.step(1/60f, 6, 2);
		Render.tiledMapRenderer.setView(camaraJugador);
		Render.tiledMapRenderer.render(helpMapa.getCapasDeFondo());
		box2Debug.render(world, camaraJugador.combined);
		rayHandler.setCombinedMatrix(camaraJugador.combined,0,0,1,1);	
		Render.batch.end();

		
		Render.batch.begin();
		camaraJugador.update();
		Render.batch.setProjectionMatrix(camaraJugador.combined);
		Render.batch.end();

		//Managers
		Render.batch.begin();
		npcManager.renderizar();
		mineralesManager.renderizar();
		objetosDelTallerManager.renderizar();
		
		npcManager.detectarJugador(jugador);
		mineralesManager.detectarJugador(jugador);
		objetosDelTallerManager.detectarJugador(jugador);
		
		mineralesManager.minar(jugador);
		mineralesManager.limpiarMinerales(world);
		mineralesManager.comprar(jugador);

		jugador.draw(Render.batch);
		
		Render.batch.end();
		
		
		Render.tiledMapRenderer.render(helpMapa.getCapasDeFrente());// Estas son las capas que esconden al jugador
		rayHandler.render();

		Render.batch.begin();// HUD´s
		
		if (cartaHUD.getCerrar()) {// si ya leyo la carta...
			npcManager.mostrarDialogo();// DEJALO ACA
			charlaManager.checkearCharlas(vendedorTienda, vendedorAmbulate, viejo);
			// Renderiza el HUD
			camaraHud.update();
			Render.batch.setProjectionMatrix(camaraHud.combined);// Una vez que renderiza el juego, se inicia el batch
																	// para la camara del HUD y lo dibuja

			// Renderiza ocultables
			hud.render();
			pausaHud.render();
			dialogoDeCompra.render(jugador);
			inventarioHUD.render(jugador);
			combinacionJugador.render();

			renderizarHUDSTaller();
			mostrarHUDSTaller();

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
			
		misionesManager.checkearMisiones();//Se fija si las condiciones se cumplen

		}else {
			cartaHUD.render();
		}
		Render.batch.end();
		
	}


	@Override
	public void resize(int width, int height) {

		camaraJugador.viewportWidth = width;
		camaraJugador.viewportHeight = height;
		camaraJugador.update();	
		combinacionJugador.reEscalar(width, height);
		inventarioHUD.reEscalar(width, height);

		reEscalarHUDSTaller(width, height);
	    
		hud.reEscalar(width, height);
	    cartaHUD.reEscalar(width, height);
	    pausaHud.reEscalar(width, height);

	    npcManager.reEscalarDialogos(width, height);
	    dialogoDeCompra.reEscalar(width, height);
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
//		rey = new Rey(0,0,Recursos.VENDEDOR, NpcData.REY);
	}
	
	public void crearObjetosDelTaller() {
		mesa = new Mesa(39, 17, world, Recursos.MESA, mesaHUD);
		yunque = new Yunque(34, 15, world, Recursos.YUNQUE, yunqueHUD, jugador);
		altoHorno = new AltoHorno(34, 12, world, Recursos.ALTO_HORNO, fundicionHUD); //Estas coordenadas las saco de Tiled
		cajaEntregas = new CajaEntregas(39, 17.5f, world, Recursos.CAJA_ENTREGAS, cajaEntregasHUD);
		soporteArmadura = new SoporteArmadura(32, 19, world, Recursos.SOPORTE_ARMADURAS, soporteArmaduraHUD);
		
	}
	
	private void objetosDleTallerManagerConfig() {
		objetosDelTallerManager = new ObjetosTallerManager();
		objetosDelTallerManager.agregarObjeto(mesa);
		objetosDelTallerManager.agregarObjeto(yunque);
		objetosDelTallerManager.agregarObjeto(altoHorno);
		objetosDelTallerManager.agregarObjeto(cajaEntregas);
		objetosDelTallerManager.agregarObjeto(soporteArmadura);
		
	}
	
	private void mostrarHUDSTaller() {
		mesa.mostrarHUD(jugador);
		yunque.mostarHUD(jugador);
		altoHorno.mostrarHUD(jugador);
		cajaEntregas.mostrarHUD(jugador);
		soporteArmadura.mostrarHUD(jugador);
	}
	
	private void renderizarHUDSTaller() {
		mesaHUD.render();
		yunqueHUD.render();
		fundicionHUD.render();
		cajaEntregasHUD.render();
		soporteArmaduraHUD.render();
	}
	
	private void reEscalarHUDSTaller(int width, int height) {
		mesaHUD.reEscalar(width, height);
		yunqueHUD.reEscalar(width, height);
		fundicionHUD.reEscalar(width, height);
		cajaEntregasHUD.reEscalar(width, height);
		soporteArmaduraHUD.reEscalar(width, height);
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
		mineralesManager.agregarMineral(hierro);
		mineralesManager.agregarMineral(hierro1);
		mineralesManager.agregarMineral(piedra2);
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

	public Horno getHorno() {
		return horno;
	}

	public World getWorld() {
		return world;
	}

	private void iluminacion() {

		rayHandler.setBlurNum(3);
		rayHandler.setShadows(true);
		pl = new PointLight(rayHandler, 128, new Color(Color.valueOf("#ea8e0e")), 200, 300, 300);
		pl.setStaticLight(false);
		pl.setSoft(true);
		rayHandler.setCulling(false); // Esto es lo que me hace que no se vean las luces que inicien fuera de los
										// bordes de la pantalla
	}
	 
	 private void horaDelMundo() { 
		 if(!MundoConfig.pausarTiempo) {//Para cuando apretas escape o lo que sea
		 
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
		 }
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
	 
		@Override
		public void dispose() {
			Render.tiledMapRenderer.dispose();
			rayHandler.dispose();
			pausaHud.dispose();
			cartaHUD.dispose();
			hud.dispose();
			Recursos.muxJuego.clear();
		}

}
