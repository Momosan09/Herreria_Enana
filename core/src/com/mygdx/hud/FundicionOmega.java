package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.entidades.ObjetosDelMapa.procesados.LingoteHierro;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.recursos.Recursos;
import com.mygdx.utiles.recursos.Sonidos;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;


public class FundicionOmega extends HUD{

	private Table inventario, fragua;
	
	private Jugador j;
	
	private DragAndDrop dragAndDrop;
	
	private Image entrada, salida, molde;
	private Texture texturaEntradaVacia;
	private Mineral mineralEntrada;
	private Mineral mineralMolde;
	private Mineral mineralSalida;

	private boolean fundiendo = false;
	private Sound sonidoSoltar;

	
	public FundicionOmega(Jugador jugador) {
		this.j = jugador;
    	screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
        
        dragAndDrop = new DragAndDrop();
        
        sonidoSoltar = Gdx.audio.newSound(Gdx.files.internal(Recursos.sonidos.SONIDO_MISION_RECIBIDA));

        construir();
	}
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		
	}

	@Override
	public void crearActores() {
		texturaEntradaVacia = new Texture(Recursos.minerales.CARBON_POLVO);
		entrada = new Image(texturaEntradaVacia);
		molde = new Image(texturaEntradaVacia);
		salida = new Image(texturaEntradaVacia);
		
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(true);
		
		inventario = new Table();

		fragua = new Table();

		
		contenedor = new Table();
		contenedor.setDebug(true);
		
	}

	@Override
	public void poblarStage() {
		
		//inventario.setBackground(new TextureRegionDrawable(new Texture(Recursos.CARTA_TEXTURA)));
		//fragua.setBackground(new TextureRegionDrawable(new Texture(Recursos.CAJA_ENTREGAS)));
		
		fragua.add(entrada).size(64, 64).pad(5);
		fragua.row();
		fragua.add(molde).size(64, 64).pad(5);
		fragua.row();
		fragua.add(salida).size(64, 64).pad(5);
		
		fragua.setSize(500, 500);
		
		inventario.setSize(100, 500);
		
		contenedor.add(fragua);
		contenedor.add(inventario);
		
		tabla.add(contenedor);
		stage.addActor(tabla);
		
	}

	@Override
	public void reEscalar(int width, int heigth) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mostrar() {

	    if (!visible) {
	    	actualizarTablaInventario();
	       
	        }

	        visible = true;
	    }
	


	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();
		
	}

	@Override
	public boolean getVisible() {
		return visible;
	}
	
	private void crearTargets() {

		Target targetEntrada = new Target(entrada) {

		    @Override
		    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
		        if (!(payload.getObject() instanceof Mineral)) return false;

		        Mineral m = (Mineral) payload.getObject();

		        
		        return mineralEntrada == null;
		    }

		    @Override
		    public void drop(Source source, Payload payload, float x, float y, int pointer) {

		        Mineral mineral = (Mineral) payload.getObject();

		        mineralEntrada = mineral;

		        entrada.setDrawable(new TextureRegionDrawable(mineral.getTextura()));
		        consumirMineral(mineral, source.getActor());
		        reproducirSonidoSoltar();

		        iniciarFundicion();
		    }
		};


		Target targetMolde = new Target(molde) {

		    @Override
		    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
		        if (!(payload.getObject() instanceof Mineral)) return false;

		        Mineral m = (Mineral) payload.getObject();

		        // Ejemplo: solo hierro como molde
		        return m.getTipoMineral() == TipoMinerales.HIERRO && mineralMolde == null;
		    }

		    @Override
		    public void drop(Source source, Payload payload, float x, float y, int pointer) {

		        Mineral mineral = (Mineral) payload.getObject();

		        mineralMolde = mineral;

		        molde.setDrawable(new TextureRegionDrawable(mineral.getTextura()));
		        consumirMineral(mineral, source.getActor());
		        reproducirSonidoSoltar();

		        iniciarFundicion();
		    }
		};

		/*Esto es por si quiero poner cosas en la salida, entonces poner un casco de hierro en la salida, y al poner oro en la entrada lo baña en oro*/
//	    Target targetSalida = new Target(salida) {
//	        @Override
//	        public boolean drag(Source source, Payload payload,
//	                            float x, float y, int pointer) {
//	            return payload.getObject() instanceof Mineral;
//	        }
//
//	        @Override
//	        public void drop(Source source, Payload payload,
//	                         float x, float y, int pointer) {
//
//	            Mineral mineral = (Mineral) payload.getObject();
//	            salida.setDrawable(new TextureRegionDrawable(mineral.getTextura()));
//	        }
//	    };

	    dragAndDrop.addTarget(targetEntrada);
	    dragAndDrop.addTarget(targetMolde);
//	    dragAndDrop.addTarget(targetSalida);
	}

	private void iniciarFundicion() {

	    if (fundiendo) return;
	    if (mineralEntrada == null || mineralMolde == null) return;

	    fundiendo = true;
	    System.out.println("Fundiendo...");

	    Timer.schedule(new Timer.Task() {
	        @Override
	        public void run() {
	            terminarFundicion();
	        }
	    }, 3f); // 3 segundos
	}
	
	private void terminarFundicion() {
	    fundiendo = false;

	    System.out.println("Fundición terminada");

	    // Resultado (ejemplo)
	    Texture resultado = new Texture(Recursos.minerales.LINGOTE_HIERRO);
	    mineralSalida = new Mineral(TipoMinerales.HIERRO, EstadosMinerales.LINGOTE);
	    salida.setDrawable(new TextureRegionDrawable(resultado));
	    salida.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
					j.agregarMineral(mineralSalida);
					salida.setDrawable(new TextureRegionDrawable(texturaEntradaVacia));
					actualizarTablaInventario();
				}
		});

	    mineralEntrada = null;
	    mineralMolde = null;

	    entrada.setDrawable(new TextureRegionDrawable(texturaEntradaVacia));
	    molde.setDrawable(new TextureRegionDrawable(texturaEntradaVacia));
	}

	private void reproducirSonidoSoltar() {
	    sonidoSoltar.play(0.8f);
	}

	private void consumirMineral(Mineral mineral, Actor actor) {
	    j.eliminarMineral(mineral, 1);
	    actor.remove();
	}

	public Stage getStage() {
		return stage;
	}
	
	private void actualizarTablaInventario() {
		 inventario.clearChildren(); // evita duplicados

	        for (final Mineral mineral : j.obtenerTodosLosMinerales()) {

	            final Image mineralImage = new Image(mineral.getTextura());

	            // Drag source
	            dragAndDrop.addSource(new DragAndDrop.Source(mineralImage) {
	                @Override
	                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {

	                    DragAndDrop.Payload payload = new DragAndDrop.Payload();

	                    Image dragImage = new Image(mineral.getTextura());
	                    payload.setDragActor(dragImage);
	                    payload.setObject(mineral);

	                    return payload;
	                }
	            });

	            inventario.add(mineralImage).size(64, 64).pad(5);
	            inventario.row();
	        }

	}
}
