package com.mygdx.utiles;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.SierraCircular;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.entidades.ObjetosDelMapa.procesados.CarbonPuro;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroDisco;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroPlancha;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroPuro;
import com.mygdx.enums.Items;
import com.mygdx.enums.TipoCombinacion;
import com.mygdx.historia.MisionesDelJuego;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;

public class MyDragAndDrop {
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Label.LabelStyle labelStyle;
	private Label actorLabelNombre;
	private ArrayList<Image> inventario, herramientas;
	private Jugador jugador;
	private DragAndDrop dragAndDrop;
	
	public MyDragAndDrop(Jugador jugador){
		dragAndDrop = new DragAndDrop();
		this.jugador = jugador;
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		
		labelStyle = EstiloFuente.generarFuente(32, Colores.BLANCO, false);
		
		actorLabelNombre = new Label("", labelStyle);
		actorLabelNombre.setVisible(true);

		
		inventario = new ArrayList<Image>();
		herramientas = new ArrayList<Image>();

        if (jugador.getMinerales().size() > 0) {
            for (Mineral mineral : jugador.getMinerales()) {
                inventario.add(new Image(new Sprite(mineral.getTextura())));
            }
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
			 final Mineral mineral = jugador.getMinerales().get(i);
			    dragAndDrop.addSource(new Source(inventario.get(i)) {//addSource permite que la imagen sea arrastrable, por eso necesito que cada imagen del inventario sea source
				
				@Null
				public Payload dragStart (InputEvent event, float x, float y, int pointer) {
					Payload payload = new Payload();
					payload.setObject(mineral);

					payload.setDragActor(getActor());

					Label validLabel = new Label("Valido!", labelStyle);//crea la label que se muestra cuando es valido
					validLabel.setColor(0, 1, 0, 1);
					payload.setValidDragActor(validLabel);

					Label invalidLabel = new Label("Invalido", labelStyle);//crea la label que se muestra cuando no es valido
					invalidLabel.setColor(1, 0, 0, 1);
					payload.setInvalidDragActor(invalidLabel);

					return payload;
				}
			});
		}
		
		
		for (int i = 0; i < jugador.getItems().size(); i++) {
            final Item herramienta = jugador.getItems().get(i);
            dragAndDrop.addSource(new Source(herramientas.get(i)) {
                @Null
                public Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    System.out.println(HelpDebug.debub(getClass())+"detecta herramienta");
                    Payload payload = new Payload();
                    payload.setObject(herramienta);

                    payload.setDragActor(getActor());

                    Label validLabel = new Label("Valido!", labelStyle);
                    validLabel.setColor(0, 1, 0, 1);
                    payload.setValidDragActor(validLabel);

                    Label invalidLabel = new Label("Invalido", labelStyle);
                    invalidLabel.setColor(1, 0, 0, 1);
                    payload.setInvalidDragActor(invalidLabel);

                    return payload;
                }
            });
            }
		

		
		for (int i = 0; i < inventario.size(); i++) {
			final Mineral mineralSource;
			 mineralSource = jugador.getMinerales().get(i);
				dragAndDrop.addTarget(new Target(inventario.get(i)) {

					public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
						getActor().setColor(Color.GREEN);

						return true;
					}

					public void reset(Source source, Payload payload) {
						getActor().setColor(Color.WHITE);
					}

					public void drop(Source source, Payload payload, float x, float y, int pointer) {

						if (payload.getObject() instanceof Mineral) {// Aca para cosas que involucren dos minerales
							Mineral mineralTarget = (Mineral) payload.getObject();

							if (esCombinacionValida(mineralSource, mineralTarget)) {

							} else {

							}

						}

						if (payload.getObject() instanceof Item) {// Aca para cosas que involucren una herramienta y un
																	// mineral
							Item herramienta = (Item) payload.getObject();

							if (esCombinacionValida(herramienta, mineralSource)) {
								// Esto es medio generico, pero bueno cuando necesite mas especifico lo cambio
								inventario.remove(mineralSource);
								jugador.getMinerales().remove(mineralSource);

							}


						}

						refrescar();

			        }
					
					
				
			    });
			
		}
		
		for(int i = 0; i < herramientas.size(); i++) {
			final Item itemSource;
			itemSource = jugador.getItems().get(i);
			dragAndDrop.addTarget(new Target(herramientas.get(i)) {

				@Override
				public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
					getActor().setColor(Color.GREEN);

					return true;
				}

				@Override
				public void reset(Source source, Payload payload) {
					getActor().setColor(Color.WHITE);
				}

				public void drop(Source source, Payload payload, float x, float y, int pointer) {
					if(payload.getObject() instanceof Item) {
						Item itemTarget = (Item) payload.getObject();
					if (esCombinacionValida(itemTarget, itemSource)) {//ITEM CON ITEM
						herramientas.remove(itemSource);
						jugador.getItems().remove(itemSource);

					}
					
					}

					refrescar();
		        }
				
			});
		}
		

				

		
		
		
/*	Aca estan las no permitidas
		dragAndDrop.addTarget(new Target(inventario.get(1)) {
			public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
				getActor().setColor(Color.RED);
				return false;
			}

			public void reset (Source source, Payload payload) {
				getActor().setColor(Color.WHITE);
			}

			public void drop (Source source, Payload payload, float x, float y, int pointer) {
			}
		});*/
		
		mostrarLabelOnEnter();
	}

	

	
	private boolean esCombinacionValida(Mineral mineralFuente, Mineral mineralObjetivo) {
		if(mineralFuente.tipo == mineralObjetivo.tipo) {
			System.out.println("Exito");
			return true;
		}else {
			return false;
		}
	}
	
	private boolean esCombinacionValida(Item herramientaFuente, Item herramientaObjetivo) {
		if(herramientaFuente.getTipo() == Items.LIMA_PLANA && herramientaObjetivo.getTipo() == Items.DISCO_HIERRO) {
			jugador.getItems().remove(herramientaObjetivo);
			jugador.getItems().add(new SierraCircular(Items.SIERRA_CIRCULAR));
			jugador.conseguirMisionPorId(MisionesDelJuego.CARP_00).setObjetoFabricado();;
			jugador.conseguirMisionPorId(MisionesDelJuego.CARP_00).setCantidadConseguida(1);
			return true;
		}else {
			return false;
		}
	}
	
	private boolean esCombinacionValida(Item herramienta, Mineral mineral) {
		if(herramienta.getTipo() == Items.CINCEL && mineral.tipo == TipoMinerales.HIERRO && mineral.estado == EstadosMinerales.MENA) {
			jugador.getMinerales().add(new HierroPuro(false));
			herramienta.restarUsos();
			return true;
		}else if(herramienta.getTipo() == Items.CINCEL && mineral.tipo == TipoMinerales.CARBON && mineral.estado == EstadosMinerales.MENA){
			jugador.getMinerales().add(new CarbonPuro(false));
			return true;
		}else if(herramienta.getTipo() == Items.MAZA && mineral.tipo == TipoMinerales.HIERRO && mineral.estado == EstadosMinerales.LINGOTE){
			jugador.getMinerales().add(new HierroPlancha(false));
			return true;
		}else if(herramienta.getTipo() == Items.ESQUEMA_SIERRA_CIRCULAR && mineral.tipo == TipoMinerales.HIERRO && mineral.estado == EstadosMinerales.PLANCHA){
			jugador.getItems().add(new HierroDisco(Items.DISCO_HIERRO));
			jugador.getMinerales().remove(mineral);
			jugador.getItems().remove(herramienta);
			return false;
		}else {
			return false;
		}
			
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
	
	public void refrescar() {
		stage.clear();
		inventario.clear();
		herramientas.clear();
		for (int i=0; i<jugador.getMinerales().size();i++) {
			inventario.add(new Image(new Sprite(jugador.getMinerales().get(i).getTextura())));//Agrega los minearales del inventario del personaje, aca puede haber un error cuando quiera combinar mas cosas que solo minerales
			stage.addActor(inventario.get(i));
		}
		
        for (int i = 0; i < jugador.getItems().size(); i++) {
            herramientas.add(new Image(new Sprite(jugador.getItems().get(i).getTextura())));
            stage.addActor(herramientas.get(i));
        }
		System.out.println(HelpDebug.debub(getClass()) + stage.getActors().size);
		create();
	    stage.addActor(actorLabelNombre);
	}


	private void mostrarLabelOnEnter() {
		//Label nombre 
		 for (int i = 0; i < inventario.size(); i++) {
		        final Mineral mineral = jugador.getMinerales().get(i);
		        final Image image = inventario.get(i);
		        image.addListener(new InputListener() {
		            @Override
		            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		                // Muestra la etiqueta con el nombre del actor cuando el cursor entra en la imagen
		                actorLabelNombre.setText(mineral.getNombre() + " " + mineral.getEstado());
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
