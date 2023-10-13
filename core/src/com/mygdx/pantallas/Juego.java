package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.mygdx.entidades.ObjetosDelMapa.Yunque;
import com.mygdx.entidades.npcs.Rey;
import com.mygdx.entidades.npcs.Vendedor;
import com.mygdx.entidades.npcs.Viejo;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.entidades.npcs.dialogos.Npc_Dialogos_Rey;
import com.mygdx.game.Principal;
import com.mygdx.hud.CartaHUD;
import com.mygdx.hud.Combinacion;
import com.mygdx.hud.Dialogo;
import com.mygdx.hud.HUD;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Juego implements Screen{
	
	private Jugador jugador;
	private Npc viejo, vendedor;
	private ObjetoDelMapa carta;
	private Texture jugadorTextura;
	private OrthographicCamera camaraJuego, camaraHud;
	private Dialogo dialogo;
	private NPCManager npcManager;
	private CartaHUD cartaHUD;
	
	private Combinacion combinacion;
	
	private HUD hud;


	public Juego(final Principal game) {

	}

	@Override
	public void show() {
		
		//camaras
		camaraJuego = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraJuego.setToOrtho(false);
		camaraJuego.zoom = .7f;
		
		
		camaraHud = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camaraHud.setToOrtho(false); 
		camaraHud.zoom = .1f;
		
	    //render
		Render.tiledMapRenderer = new OrthogonalTiledMapRenderer(Recursos.MAPA);
		
		//jugador
		jugador = new Jugador(camaraJuego);
				
		//Npc
		crearNPCs();
		managerConfig();

		//objetos del mapa
		//yunque = new Yunque(532,532,Recursos.YUNQUE);
		
		//HUD
		hud = new HUD();
	    combinacion = new Combinacion();
		cartaHUD = new CartaHUD(Npc_Dialogos_Rey.CARTA_0);

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
	      
		Render.batch.end();
	    
		if(cartaHUD.cerrar) {
		jugador.puedeMoverse=true;
	    npcManager.mostrarDialogo(Render.batch,0);//Aca tengo que modificar, pq todos los npcs me muestran el primer mensaje
	    vendedor.charla(1);
	    viejo.charla(0);
	    //vendedor.getData().getMensaje(0);
	    
	    //Renderiza el HUD
	    camaraHud.update();
	    Render.batch.setProjectionMatrix(camaraHud.combined);//Una vez que renderiza el juego, se inicia el batch para la camara del HUD y lo dibuja
	    
	    Render.batch.begin();//HUD´s
	    hud.render();
	    Render.batch.end();
		}else {
			cartaHUD.render();
			
		}

	    

	    //combinacion.render();
	    //System.out.println(HelpDebug.debub(this.getClass()) + "Hola");

	}


	@Override
	public void resize(int width, int height) {
		camaraJuego.viewportWidth = width;
		camaraJuego.viewportHeight = height;
		camaraJuego.update();	
		
	    System.out.println(HelpDebug.debub(getClass())+"X =" +Gdx.graphics.getWidth() + " Y =" + Gdx.graphics.getHeight());
	    hud.reEscalar(width, height);
	    combinacion.reEscalar(width, height);
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
	
	public void managerConfig() {
		npcManager = new NPCManager();
		npcManager.agregarEntidad(viejo);
		npcManager.agregarEntidad(vendedor);
	    npcManager.crearDialogos();
	}



}
