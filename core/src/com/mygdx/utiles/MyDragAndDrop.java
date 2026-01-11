package com.mygdx.utiles;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.combinaciones.CargadorRecetas;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.combinaciones.Medios;
import com.mygdx.combinaciones.MotorCrafteo;
import com.mygdx.combinaciones.Receta;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.utiles.sonidos.ListaSonidos;
import com.mygdx.utiles.sonidos.SonidosManager;

public class MyDragAndDrop {
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Label.LabelStyle labelStyle;
	private Label actorLabelNombre;
	private Table herramientasTabla, mineralesTabla, tabla, contenedor;
	private Jugador jugador;
	private DragAndDrop dragAndDrop;
	private MotorCrafteo motorCraf;
	private Medios medio = Medios.YUNQUE;
	private ArrayList<Image> inventario, herramientas;
	private ArrayList<IngredientesId> ingredientes;
	
	public MyDragAndDrop(Jugador jugador){
		dragAndDrop = new DragAndDrop();
		this.jugador = jugador;
		this.motorCraf = new MotorCrafteo(jugador);

		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);

		
		herramientasTabla = new Table();
		mineralesTabla = new Table();
		tabla = new Table();
		tabla.setFillParent(true);
		contenedor = new Table();
		
		labelStyle = EstiloFuente.generarFuente(32, Colores.BLANCO, false);
		
		actorLabelNombre = new Label("", labelStyle);
		actorLabelNombre.setVisible(true);

		
		inventario = new ArrayList<Image>();
		herramientas = new ArrayList<Image>();


		 ingredientes = jugador.obtenerIngredientesParaCrafteo();
		for (IngredientesId id : ingredientes) {
		    inventario.add(new Image(id.getTextura()));
		}

		

		
        if (jugador.getItems().size() > 0) {
            for (Item herramienta : jugador.getItems()) {
                herramientas.add(new Image(new Sprite(herramienta.getTextura())));
                
            }
        }
		
	}
	
	
	

	public void create () {
		stage.setDebugAll(true);
		System.out.println(HelpDebug.debub(getClass())+ "creado");
		for (int i = 0; i < inventario.size(); i++) {
		    final IngredientesId ingrediente = ingredientes.get(i);

		    dragAndDrop.addSource(new Source(inventario.get(i)) {
		        @Override
		        public Payload dragStart(InputEvent event, float x, float y, int pointer) {
		            Payload payload = new Payload();
		            payload.setObject(ingrediente);
		            payload.setDragActor(getActor());
		            stage.addActor(getActor());
		            return payload;
		        }
		    });
		}

		
		
		for (int i = 0; i < jugador.getItems().size(); i++) {
            final IngredientesId herramienta = jugador.getItems().get(i).getIngredienteId();
            dragAndDrop.addSource(new Source(herramientas.get(i)) {
                @Null
                public Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    System.out.println(HelpDebug.debub(getClass())+"detecta herramienta");
                    Payload payload = new Payload();
                    payload.setObject(herramienta);

                    payload.setDragActor(getActor());
                    
                    stage.addActor(getActor());//esta linea arregla el tema del offset

                    Label validLabel = new Label("Valido!", labelStyle);
                    validLabel.setColor(0, 1, 0, 1);
                    payload.setValidDragActor(validLabel);

                    Label invalidLabel = new Label("Invalido", labelStyle);
                    invalidLabel.setColor(1, 0, 0, 1);
                    payload.setInvalidDragActor(invalidLabel);
					x+=16;
					y+=16;
                    return payload;
                }
            });
            }
		

		
		for (int i = 0; i < inventario.size(); i++) {
			final IngredientesId mineralSource;

			 mineralSource = jugador.obtenerIngredientesParaCrafteo().get(i);
				dragAndDrop.addTarget(new Target(inventario.get(i)) {

					public boolean drag(Source source, Payload payload, float x, float y, int pointer) {

					    if (!(payload.getObject() instanceof IngredientesId)) return false;

					    IngredientesId origen = (IngredientesId) payload.getObject();
					    Receta receta = CargadorRecetas.buscar(origen, mineralSource, medio);

					    if (MotorCrafteo.puedeFabricar(receta, motorCraf.getInventario(), medio)) {
					        getActor().setColor(Color.GREEN);
					    } else {
					        getActor().setColor(Color.RED);
					    }

					    return true;
					}


					public void reset(Source source, Payload payload) {
						getActor().setColor(Color.WHITE);
					}

					public void drop(Source source, Payload payload, float x, float y, int pointer) {

					    if (!(payload.getObject() instanceof IngredientesId)) return;

					    IngredientesId origen = (IngredientesId) payload.getObject();

					    Receta receta = CargadorRecetas.buscar(origen, mineralSource, medio);
					    
					    if (receta != null && MotorCrafteo.puedeFabricar(receta, motorCraf.getInventario(), medio)) {
					        MotorCrafteo.fabricar(receta, motorCraf.getInventario());
					        refrescar();
					    }else {
					    	SonidosManager.reproducirSonido(ListaSonidos.FALLA);
					    }
					}

						
				
			    });
			
		}
		
		for(int i = 0; i < herramientas.size(); i++) {
			final Item itemSource;
			itemSource = jugador.getItems().get(i);
			dragAndDrop.addTarget(new Target(herramientas.get(i)) {

				@Override
				public boolean drag(Source source, Payload payload, float x, float y, int pointer) {

					if(payload.getObject() instanceof IngredientesId) {
						IngredientesId itemTarget = (IngredientesId) payload.getObject();
						Receta receta = CargadorRecetas.buscar(itemTarget, itemSource.getIngredienteId(), medio);
						
						if(MotorCrafteo.puedeFabricar(receta, motorCraf.getInventario(), medio)) {
							getActor().setColor(Color.GREEN);
						}else {
							getActor().setColor(Color.RED);
						}
					
					}

					return true;
				}

				@Override
				public void reset(Source source, Payload payload) {
					getActor().setColor(Color.WHITE);
				}

				public void drop(Source source, Payload payload, float x, float y, int pointer) {
					if (payload.getObject() instanceof IngredientesId) {

						IngredientesId herramienta = (IngredientesId) payload.getObject();

					    Receta receta = CargadorRecetas.buscar(herramienta, itemSource.getIngredienteId(), medio);

					    if (receta != null &&
					        MotorCrafteo.puedeFabricar(receta,motorCraf.getInventario(), medio)) {

					        MotorCrafteo.fabricar(receta, motorCraf.getInventario());

					        //jugador.avanzarMision(MisionesDelJuego.CARP_00);

					        refrescar();
					    }
					}


					refrescar();
		        }
				
			});
		}	
			

		mostrarLabelOnEnter();
	}

	public void render () {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize (int width, int height) {
		screenViewport.update(width, height, true);
	}

	public void dispose () {
		stage.dispose();
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public ArrayList<Image> getChilds(){
		return inventario;
	}
	
	public void ocultar() {
		stage.clear();
		stage.unfocusAll();
		
	}
	
	public void refrescar() {
		stage.clear();
		inventario.clear();
		herramientas.clear();
		herramientasTabla.clear();
		mineralesTabla.clear();
		contenedor.clear();
		tabla.clear();

		System.out.println(HelpDebug.debub(getClass()) + stage.getActors().size);
		
	    dragAndDrop.clear();
		
		//Agrega las imagenes de los minerales en el inventario del jugador
		 ingredientes = jugador.obtenerIngredientesParaCrafteo();
		for (IngredientesId id : ingredientes) {
		    inventario.add(new Image(id.getTextura()));
		}

		
        for (int i = 0; i < jugador.getItems().size(); i++) {
            herramientas.add(new Image(new Sprite(jugador.getItems().get(i).getTextura())));
            herramientasTabla.add(herramientas.get(i));
            if(i % 2 == 0 && i != 0) herramientasTabla.row();
        }

			//los agrega a la tabla
			for(int i = 0; i<inventario.size();i++) {
				mineralesTabla.add(inventario.get(i));
			}
	



	    // vuelve a crear drag & drop (una sola vez)
	    create();

		contenedor.add(herramientasTabla);
		contenedor.add(mineralesTabla);
	    tabla.add(contenedor);
	    stage.addActor(tabla);
	    stage.addActor(actorLabelNombre);
	}


	private void mostrarLabelOnEnter() {
		//Label nombre 
		 for (int i = 0; i < inventario.size(); i++) {
		        final IngredientesId ingrediente = jugador.obtenerIngredientesParaCrafteo().get(i);
		        final Image image = inventario.get(i);
		        image.addListener(new InputListener() {
		            @Override
		            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		                // Muestra la etiqueta con el nombre del actor cuando el cursor entra en la imagen
		                actorLabelNombre.setText(ingrediente.toString());
		                actorLabelNombre.setPosition(event.getStageX(), event.getStageY());
		                actorLabelNombre.setVisible(true);
		            }

		            @Override
		            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		                // Oculta la etiqueta cuando el cursor sale de la imagen
		                actorLabelNombre.setVisible(false);
		            }
		        });
		    }
		 
		 
		 
		 for (int i = 0; i < jugador.getItems().size(); i++) {

	            final Item herramienta = jugador.getItems().get(i);
		        final Image image = herramientas.get(i);
		        image.addListener(new InputListener() {
		            @Override
		            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		                // Muestra la etiqueta con el nombre del actor cuando el cursor entra en la imagen
		                actorLabelNombre.setText(herramienta.getNombre());
		                actorLabelNombre.setPosition(event.getStageX(), event.getStageY());
		                actorLabelNombre.setVisible(true);
		            }

		            @Override
		            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		                // Oculta la etiqueta cuando el cursor sale de la imagen
		                actorLabelNombre.setVisible(false);
		            }
		        });
		    }
	}




}
