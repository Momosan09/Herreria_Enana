package com.mygdx.utiles;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.entidades.ObjetosDelMapa.procesados.CarbonPuro;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroPuro;
import com.mygdx.enums.EstadosMinerales;
import com.mygdx.enums.Items;
import com.mygdx.enums.TipoCombinacion;

public class MyDragAndDrop {
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Label.LabelStyle labelStyle;
	private ArrayList<Image> inventario, herramientas;
	private Jugador jugador;
	private DragAndDrop dragAndDrop;
	
	public MyDragAndDrop(Jugador jugador){
		dragAndDrop = new DragAndDrop();
		this.jugador = jugador;
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		
		labelStyle = EstiloFuente.generarFuente(32, Colores.BLANCO, false);
		
		inventario = new ArrayList<Image>();
		herramientas = new ArrayList<Image>();

        if (jugador.getMinerales().size() > 0) {
            for (Mineral mineral : jugador.getMinerales()) {
                inventario.add(new Image(new Sprite(mineral.getTextura())));
            }
        }

        if (jugador.getItems().size() > 0) {
            for (Items herramienta : jugador.getItems()) {
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
					System.out.println("detecta");
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
            final Items herramienta = jugador.getItems().get(i);
            dragAndDrop.addSource(new Source(herramientas.get(i)) {
                @Null
                public Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    System.out.println("detecta herramienta");
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
		    final Mineral mineralSource = jugador.getMinerales().get(i);
		    dragAndDrop.addTarget(new Target(inventario.get(i)) {
				public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
					getActor().setColor(Color.GREEN);
					
					return true;
				}

				public void reset (Source source, Payload payload) {
					getActor().setColor(Color.WHITE);
				}

				public void drop (Source source, Payload payload, float x, float y, int pointer) {
					
					if (payload.getObject() instanceof Mineral) {//Aca para cosas que involucren dos minerales
		                Mineral mineralTarget = (Mineral) payload.getObject();

		                if (esCombinacionValida(mineralSource, mineralTarget)) {
		                    // Hacer algo si la combinación es válida
		                    System.out.println("Combinacion valida: " + mineralSource + " sobre " + mineralTarget);
		                } else {
		                    // Hacer algo si la combinación no es valida
		                    System.out.println("Combinacion no valida: " + mineralSource + " sobre " + mineralTarget);
		                }
		                System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
		                
		            } else if (payload.getObject() instanceof Items) {//Aca para cosas que involucren una herramienta y un mineral
		                Items herramienta = (Items) payload.getObject();

		                if (esCombinacionValida(herramienta, mineralSource)) {
		                	//Esto es medio generico, pero bueno cuando necesite mas especifico lo cambio
		                	inventario.remove(mineralSource);
		                	jugador.getMinerales().remove(mineralSource);
		                	
		                	
		                    System.out.println("Combinacion valida: " + mineralSource + " sobre " + herramienta);
		                } else {
		                    System.out.println("Combinacion no valida: " + mineralSource + " sobre " + herramienta);
		                }
		                System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
		            }
					refrescar();
		        }
				
				private boolean esCombinacionValida(Mineral mineralFuente, Mineral mineralObjetivo) {
					if(mineralFuente.tipo == mineralObjetivo.tipo) {
						System.out.println("Exito");
						return true;
					}else {
						return false;
					}
				}
				
				private boolean esCombinacionValida(Items herramineta, Mineral mineral) {
					if(herramineta == Items.CINCEL && mineral.tipo == TipoMinerales.HIERRO && mineral.estado == EstadosMinerales.MENA) {
						jugador.getMinerales().add(new HierroPuro(false));
						return true;
					}else if(herramineta == Items.CINCEL && mineral.tipo == TipoMinerales.CARBON && mineral.estado == EstadosMinerales.MENA){
						jugador.getMinerales().add(new CarbonPuro(false));
						return true;
					}else {
						return false;
					}
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
	}

}
