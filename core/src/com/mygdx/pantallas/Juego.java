package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.entidades.ColisionesManager;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.NPCManager;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.MineralesManager;
import com.mygdx.entidades.ObjetosDelMapa.Minable.Hierro;
import com.mygdx.entidades.ObjetosDelMapa.Minable.Piedra;
import com.mygdx.entidades.npcs.Vendedor;
import com.mygdx.entidades.npcs.Viejo;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.game.Principal;
import com.mygdx.utiles.ConsolaDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

import com.mygdx.red.Servidor;

public class Juego implements Screen{
	
	//Entidades
	private Jugador jugador_1, jugador_2;
	private Npc viejo, vendedor;
	private Mineral piedra, hierro, hierro1, piedra2;
	
	//Managers
	private NPCManager npcManager;
	private MineralesManager mineralesManager;

	//Camaras
	private OrthographicCamera camaraJuego;

	
	//Toggles (referido a HUDs), los uso cuando ese hud no se cierra con boton
	private boolean toggleConsola = true;
	
	//Screens
	private final Principal game;
	
	public ColisionesManager colisionesManager1,colisionesManager2;
	private Rectangle colisionDelHorno;
	private Sprite  hornoSprite;
	
	private ConsolaDebug consola;
	public static Servidor servidor;

	public Juego(final Principal game) {
		this.game = game;
		consola = new ConsolaDebug();
		camaraJuego = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
	}

	@Override
	public void show() {
		
	    //render
		Render.tiledMapRenderer = new OrthogonalTiledMapRenderer(Recursos.MAPA);
		
		//jugador
		jugador_1 = new Jugador();
		jugador_2= new Jugador(64);		
		servidor = new Servidor(this, consola);
		//Npc
		crearNPCs();
		npcManagerConfig();

		//objetos del mapa
		piedra = new Piedra(32*20,32*16,false,Recursos.PIEDRA);
		piedra2 = new Piedra(32*18,32*18,false, Recursos.PIEDRA);
		hierro = new Hierro(32*20,32*20,false, Recursos.HIERRO);
		hierro1 = new Hierro(32*7,32*5,true, Recursos.HIERRO);
		
		colisionDelHorno = new Rectangle(32*22,32*10,32,32);//En el servidor solo hace falta la colision
		hornoSprite = new Sprite(new Texture(Recursos.HORNO));
		hornoSprite.setPosition(colisionDelHorno.x, colisionDelHorno.y);
		mineralesManagerConfig();
		
		colisionesManagerConfig();
		
		

	}

	@Override
	public void render(float delta) {
		
		if(Gdx.input.isKeyJustPressed(Keys.TAB)) {
			toggleConsola = !toggleConsola;
		}

		colisionesManager1.checkearColisiones();
		colisionesManager2.checkearColisiones();

		
	    //Renderiza el Juego
		
		if(toggleConsola) {		
		consola.render();
		Gdx.graphics.setWindowedMode(450, 800);
		resize(450, 800);
		}else {
			Gdx.graphics.setWindowedMode(1280, 720);
			resize(1280, 720);
		camaraJuego.position.x = jugador_1.posicion.x;
		camaraJuego.position.y = jugador_1.posicion.y;
		camaraJuego.update();
		Render.batch.begin();
		Render.tiledMapRenderer.setView(camaraJuego);
		Render.tiledMapRenderer.render();
		Render.batch.setProjectionMatrix(camaraJuego.combined);//Aca estaba el problema de que el HUD no se renderizaba por encima del mapa, los setProjectionMatrix de cada camara tienen que estar en ciclos .begin() y .end() distintos
		Render.batch.end();
		
		Render.batch.begin();
		mineralesManager.renderizar();
		npcManager.renderizar(Render.batch);
		hornoSprite.draw(Render.batch);


		jugador_1.draw(Render.batch);
		jugador_2.draw(Render.batch);
		Render.batch.end();	
		}
	}


	@Override
	public void resize(int width, int height) {
		consola.reEscalar(width, height);
		camaraJuego.viewportWidth = width;
		camaraJuego.viewportHeight = height;
		camaraJuego.update();	
	}

	@Override
	public void pause() {
	
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		servidor.cerrarHilo();
		Render.tiledMapRenderer.dispose();
		Recursos.MAPA.dispose();
		
	}
	
	public void crearNPCs() {
		viejo = new Viejo(32*10,32*15,Recursos.VIEJO, NpcData.VIEJO);
		vendedor = new Vendedor(32*20,32*5,Recursos.VENDEDOR, NpcData.VENDEDOR);
	}
	
	public void npcManagerConfig() {
		npcManager = new NPCManager();
		npcManager.agregarEntidad(viejo);
		npcManager.agregarEntidad(vendedor);
	}
	
	public void mineralesManagerConfig() {
		mineralesManager = new MineralesManager();
		mineralesManager.agregarMineral(piedra);
		mineralesManager.agregarMineral(piedra2);
		mineralesManager.agregarMineral(hierro);
		mineralesManager.agregarMineral(hierro1);
	}
	
	private void colisionesManagerConfig() {
		System.out.println("se llama");
		colisionesManager1 = new ColisionesManager(jugador_1);
		colisionesManager1.agregarArrayDeColisiones(mineralesManager.getColisiones());
		colisionesManager1.agregarArrayDeColisiones(npcManager.getColisiones());
		colisionesManager1.agregarColision(jugador_2.colision);
		colisionesManager1.agregarColision(colisionDelHorno);
		colisionesManager2 = new ColisionesManager(jugador_2);
		colisionesManager2.agregarArrayDeColisiones(mineralesManager.getColisiones());
		colisionesManager2.agregarArrayDeColisiones(npcManager.getColisiones());
		colisionesManager2.agregarColision(jugador_1.colision);
		colisionesManager2.agregarColision(colisionDelHorno);
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

}
