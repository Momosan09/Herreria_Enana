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
import com.mygdx.entidades.ObjetosDelMapa.procesados.CarbonPuro;
import com.mygdx.entidades.ObjetosDelMapa.procesados.LingoteHierro;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.recursos.Recursos;
import com.mygdx.utiles.recursos.SonidosRecursos;
import com.mygdx.utiles.sonidos.ListaSonidos;
import com.mygdx.utiles.sonidos.SonidosManager;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;


public class FundicionOmega extends HUD{

	private Table inventario, fragua;
	private Label calorLbl, msgLbl;
	
	private Label.LabelStyle labelAdvertencia;
	
	private Jugador j;
	
	private DragAndDrop dragAndDrop;
	
	private Image entrada, salida, molde, combustibleImg;
	private Texture texturaEntradaVacia;
	private Mineral mineralEntrada;
	private Mineral mineralMolde;
	private Mineral mineralSalida;
	private Mineral combustible;
	
	private long calor = 0;//calor de la fragua

	private boolean fundiendo = false;

	
	public FundicionOmega(Jugador jugador) {
		this.j = jugador;
    	screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
        
        dragAndDrop = new DragAndDrop();

        construir();
	}
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		labelAdvertencia = EstiloFuente.generarFuente(30, Colores.AU, false);
		
	}

	@Override
	public void crearActores() {
		texturaEntradaVacia = new Texture(Recursos.minerales.CARBON_POLVO);
		entrada = new Image(texturaEntradaVacia);
		molde = new Image(texturaEntradaVacia);
		salida = new Image(texturaEntradaVacia);
		combustibleImg = new Image(texturaEntradaVacia);
		
	    salida.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(mineralSalida != null) {					
					j.agregarMineral(mineralSalida);
					salida.setDrawable(new TextureRegionDrawable(texturaEntradaVacia));
					actualizarTablaInventario();
					mineralSalida = null;
				}
			}
		});
		
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(true);
		
		inventario = new Table();

		fragua = new Table();

		
		contenedor = new Table();
		contenedor.setDebug(true);
		
		calorLbl = new Label("Calor = " + calor,labelStyle);
		msgLbl = new Label(Recursos.bundle.get("hud.fundicion.advertencia.calorInsuf"), labelAdvertencia);
		msgLbl.setVisible(false);
		crearTargets();
		
	}

	@Override
	public void poblarStage() {
		
		//inventario.setBackground(new TextureRegionDrawable(new Texture(Recursos.CARTA_TEXTURA)));
		//fragua.setBackground(new TextureRegionDrawable(new Texture(Recursos.CAJA_ENTREGAS)));
		
		fragua.add(combustibleImg).size(64,64).pad(5);
		fragua.add(entrada).size(64, 64).pad(5);
		fragua.row();
		fragua.add();
		fragua.add(molde).size(64, 64).pad(5);
		fragua.row();
		fragua.add();
		fragua.add(salida).size(64, 64).pad(5);
		
		fragua.setSize(500, 500);
		
		inventario.setSize(100, 500);
		
		contenedor.add(fragua);
		contenedor.add(inventario);
		contenedor.row();
		contenedor.add(calorLbl);
		contenedor.row();
		contenedor.add(msgLbl);
		
		tabla.add(contenedor);
		tabla.add(cerrarBtn);
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
	public boolean getVisible() {
		return visible;
	}
	
	private void crearTargets() {
		
		Target targetCombustible = new Target(combustibleImg) {

		    @Override
		    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
		        if (!(payload.getObject() instanceof CarbonPuro)) return false;//TODO aca tengo que usar una interface que sea "combustible" para que me permita usar distintos combustibles con distintos aportes de calor

		        Mineral m = (Mineral) payload.getObject();

		        
		        return combustible == null;
		    }

		    @Override
		    public void drop(Source source, Payload payload, float x, float y, int pointer) {

		        Mineral mineral = (Mineral) payload.getObject();

		        combustible = mineral;

		        combustibleImg.setDrawable(new TextureRegionDrawable(mineral.getTextura()));
		        consumirMineral(mineral, source.getActor());
		        reproducirSonidoSoltar();
		        actualizarMensajeCalor();
		        calentar();
		    }
		};

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
		        actualizarMensajeCalor();
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
		        actualizarMensajeCalor();
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

		dragAndDrop.addTarget(targetCombustible);
	    dragAndDrop.addTarget(targetEntrada);
	    dragAndDrop.addTarget(targetMolde);
//	    dragAndDrop.addTarget(targetSalida);
	}

	private void calentar() { //TODO aca tambien cambiarlo por la interface "combustible"
		Timer.schedule(new Timer.Task() {
	        @Override
	        public void run() {
	        	consumirCombustible();
	        	
	    		if(entrada != null && molde != null) {
	    			iniciarFundicion();
	    		}
	        }
	    }, 1f); // 1 segundo
		

		
	}
	
	private void consumirCombustible() {
    	combustibleImg.setDrawable(new TextureRegionDrawable(texturaEntradaVacia));
		calor += combustible.calorDeFusion;
		combustible = null;
		calorLbl.setText("Calor = " + calor);
		actualizarMensajeCalor();
	}
	
	private void iniciarFundicion() {
		if(mineralEntrada != null && calor >= mineralEntrada.calorDeFusion) {
	    if (fundiendo) return;
	    if (mineralEntrada == null || mineralMolde == null) return;

	    fundiendo = true;
	    System.out.println(HelpDebug.debub(getClass())+"Fundiendo...");

	    Timer.schedule(new Timer.Task() {
	        @Override
	        public void run() {
	            terminarFundicion();
	        }
	    }, 3f); // 3 segundos
		}
	}
	
	private void terminarFundicion() {
		if(fundiendo) {
			
	    fundiendo = false;
	    
	    System.out.println(HelpDebug.debub(getClass())+"Fundición terminada");

	    // Resultado (ejemplo)
	    Texture resultado = new Texture(Recursos.minerales.LINGOTE_HIERRO);
	    mineralSalida = new Mineral(TipoMinerales.HIERRO, EstadosMinerales.LINGOTE);
	    salida.setDrawable(new TextureRegionDrawable(resultado));

	    calor -= mineralEntrada.calorDeFusion;

	    mineralEntrada = null;
	    mineralMolde = null;

	    entrada.setDrawable(new TextureRegionDrawable(texturaEntradaVacia));
	    molde.setDrawable(new TextureRegionDrawable(texturaEntradaVacia));
		calorLbl.setText("Calor = " + calor);
		} 
	}

	private void reproducirSonidoSoltar() {
	    SonidosManager.reproducirSonido(ListaSonidos.MISION_RECIBIDA);
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
	
	private void actualizarMensajeCalor() {

	    // No hay nada para fundir
	    if (mineralEntrada == null || mineralMolde == null) {
	        msgLbl.setVisible(false);
	        return;
	    }

	    // Ya esta fundiendo
	    if (fundiendo) {
	        msgLbl.setVisible(false);
	        return;
	    }

	    // Hay intento de fundicion pero no alcanza el calor
	    if (calor < mineralEntrada.calorDeFusion) {
	        msgLbl.setVisible(true);
	    } else {
	        msgLbl.setVisible(false);
	    }
	}

}
