package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Entidad;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.NPCManager;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.ObjetoDelMapa;
import com.mygdx.entidades.ObjetosDelMapa.Yunque;
import com.mygdx.entidades.npcs.Vendedor;
import com.mygdx.entidades.npcs.Viejo;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.game.Principal;
import com.mygdx.hud.Dialogo;
import com.mygdx.hud.HUD;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

public class Juego implements Screen{
	
	private Jugador jugador;
	private Npc viejo, vendedor;
	private ObjetoDelMapa yunque;
	private Texture jugadorTextura;
	private OrthographicCamera camaraJuego, camaraHud;
	private Dialogo dialogo;
	private NPCManager npcManager;
	
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
		yunque = new Yunque(532,532,Recursos.YUNQUE);
		
		//HUD
		hud = new HUD();
		



	}

	@Override
	public void render(float delta) {
	    Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
	    
	    npcManager.mostrarDialogo(Render.batch,0);
	    

	    //Renderiza el HUD
	    camaraHud.update();
	    //Render.batch.setProjectionMatrix(camaraHud.combined);//Una vez que renderiza el juego, se inicia el batch para la camara del HUD y lo dibuja
	    Render.batch.begin();

	    hud.draw(Render.batch);

	    Render.batch.end();
	}


	@Override
	public void resize(int width, int height) {
		camaraJuego.viewportWidth = width;
		camaraJuego.viewportHeight = height;
		camaraJuego.update();	
		
	    System.out.println("X =" +Gdx.graphics.getWidth() + "\n Y =" + Gdx.graphics.getHeight());
	    hud.reescalar(width, height);
	}

	@Override
	public void pause() {

		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
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
		//archivo de traduccion
		I18NBundle bundle = I18NBundle.createBundle(Gdx.files.internal("locale/locale"));
		
		viejo = new Viejo(32*10,32*15,Recursos.VIEJO, "Viejito");
		viejo.agregarDialogo(bundle.get("viejo_Dialogo_1"));
		viejo.agregarDialogo(bundle.get("viejo_Dialogo_2"));
		vendedor = new Vendedor(32*20,32*5,Recursos.VENDEDOR, NpcData.VENDEDOR.getNombre());
		vendedor.agregarDialogo(NpcData.VENDEDOR.getDialogos());
		//vendedor.agregarDialogo(bundle.get("vendedor_Dialogo_2"));
		
		
	}
	
	public void managerConfig() {
		npcManager = new NPCManager();
		npcManager.agregarEntidad(viejo);
		npcManager.agregarEntidad(vendedor);
	    npcManager.crearDialogos();
	}
}
