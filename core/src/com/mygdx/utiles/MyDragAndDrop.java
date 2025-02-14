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
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.SierraCircular;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.entidades.ObjetosDelMapa.procesados.CarbonPuro;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroDisco;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroPlancha;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroPuro;
import com.mygdx.entidades.ObjetosDelMapa.procesados.HierroTira;
import com.mygdx.enums.Items;
import com.mygdx.enums.TipoCombinacion;
import com.mygdx.historia.MisionesDelJuego;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;

public class MyDragAndDrop {
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Label.LabelStyle labelStyle;
	private Label actorLabelNombre;
	private Table herramientasTabla, mineralesTabla, tabla, contenedor;
	private ArrayList<Image> inventario, herramientas;
	private Jugador jugador;
	private DragAndDrop dragAndDrop;
	
	public MyDragAndDrop(Jugador jugador){
		dragAndDrop = new DragAndDrop();
		this.jugador = jugador;
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

        if (jugador.contarTotalDeMinerales() > 0) {
    		for (int i = 0; i<jugador.contarTotalDeMinerales();i++) {  				
    			for(int j = 0; j < TipoMinerales.values().length;j++) {
    				for(int k = 0; k < EstadosMinerales.values().length;k++) {
    					int cantidad = jugador.contarMinerales(TipoMinerales.values()[j], EstadosMinerales.values()[k]);
    					if(cantidad > 0) { // antes de crear la imagen se tiene que fijar que el jugador tenga por lo menos 1 de este mineral    						
    					for(int l = 0;l< cantidad;l++) {
    						inventario.add(new Image(new Sprite(new Texture(TipoMinerales.values()[j].ruta + EstadosMinerales.values()[k].ruta))));//crea las imagenes en funcion de la cantidad de cada tipo y estado de mineral que el jugador tenga en el inventario 										
    					}
    					}
    				}
    				
    			}
    			
    			
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
			 final Mineral mineral = jugador.obtenerTodosLosMinerales().get(i);
			    dragAndDrop.addSource(new Source(inventario.get(i)) {//addSource permite que la imagen sea arrastrable, por eso necesito que cada imagen del inventario sea source
				
				@Null
				public Payload dragStart (InputEvent event, float x, float y, int pointer) {
					Payload payload = new Payload();
					payload.setObject(mineral);

					payload.setDragActor(getActor());
					stage.addActor(getActor());//esta linea arregla el tema del offset

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
                    stage.addActor(getActor());//esta linea arregla el tema del offset

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
			 mineralSource = jugador.obtenerTodosLosMinerales().get(i);
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
																	//ITEM MINERAL
							Item herramienta = (Item) payload.getObject();

							if (esCombinacionValida(herramienta, mineralSource)) {
								// Esto es medio generico, pero bueno cuando necesite mas especifico lo cambio
								inventario.remove(mineralSource);
								jugador.eliminarMineral(mineralSource, 1);
								System.out.println("eliminado");

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
	
	private boolean esCombinacionValida(Item itemFuente, Item itemObjetivo) { //Items con items
		Items fuente = itemFuente.getTipo();
		Items objeti = itemObjetivo.getTipo();
		if(fuente == Items.LIMA_PLANA && objeti == Items.DISCO_HIERRO) {
			jugador.getItems().remove(itemObjetivo);
			jugador.getItems().add(new SierraCircular());
			jugador.conseguirMisionPorId(MisionesDelJuego.CARP_00).setObjetoFabricado();
			jugador.conseguirMisionPorId(MisionesDelJuego.CARP_00).setCantidadConseguida(1);
			return true;
		}else if(fuente == Items.MANGO_MADERA_0 && objeti == Items.HOJA_ESPADA_HIERRO_0){
			jugador.eliminarItem(itemFuente);
			jugador.eliminarItem(itemObjetivo);
			jugador.conseguirMisionPorId(MisionesDelJuego.RC1_FESP).setCantidadConseguida(1);
			jugador.getItems().add(new Item(Items.ESPADA_HIERRO_0));
			return true;
		}else {
			
		}
		return false;
	}
	
	private boolean esCombinacionValida(Item herramienta, Mineral mineral) { //herramienta con mineral
		Items herrTip = herramienta.getTipo();
		if(herrTip == Items.CINCEL && mineral.tipo == TipoMinerales.HIERRO && mineral.estado == EstadosMinerales.MENA) {
			jugador.agregarMineral(new HierroPuro());
			herramienta.restarUsos();
			return true;
		}else if(herrTip == Items.CINCEL && mineral.tipo == TipoMinerales.CARBON && mineral.estado == EstadosMinerales.MENA){
			jugador.agregarMineral((new CarbonPuro()));
			return true;
		}else if(herrTip == Items.MAZA && mineral.tipo == TipoMinerales.HIERRO && mineral.estado == EstadosMinerales.LINGOTE){
			jugador.agregarMineral((new HierroPlancha()));
			return true;
		}else if(herrTip == Items.ESQUEMA_SIERRA_CIRCULAR && mineral.tipo == TipoMinerales.HIERRO && mineral.estado == EstadosMinerales.PLANCHA){
			jugador.getItems().add(new HierroDisco());
			jugador.eliminarMineral(mineral,1);
			//System.out.println("Eliminado correctamente ");
			jugador.getItems().remove(herramienta);
			return false;
		}else if(herrTip == Items.SIERRA && mineral.tipo == TipoMinerales.HIERRO && mineral.estado == EstadosMinerales.PLANCHA){
			jugador.agregarMineral(new HierroTira(),2);
			jugador.eliminarMineral(mineral, 1);
			return false;
		}else if(herrTip == Items.ESQUEMA_HOJA_ESPADA && mineral.tipo == TipoMinerales.HIERRO && mineral.estado == EstadosMinerales.TIRA) {
			jugador.getItems().add(new Item(Items.HOJA_ESPADA_HIERRO_0));
			jugador.eliminarMineral(mineral,1);
		}else {
			return false;
		}
		return false;
			
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

		for (int i = 0; i < jugador.getItems().size(); i++) {
			herramientas.add(new Image(new Sprite(jugador.getItems().get(i).getTextura())));
			herramientasTabla.add(herramientas.get(i));				
			if(i%2 == 0 && i != 0) {
				herramientasTabla.row();
			}
		}

			for(int j = 0; j < TipoMinerales.values().length;j++) {
				//System.out.println("tipo minerales "+ TipoMinerales.values().length);
				for(int k = 0; k < EstadosMinerales.values().length;k++) {
					//System.out.println("estado minerales "+ EstadosMinerales.values().length);
					int cantidad = jugador.contarMinerales(TipoMinerales.values()[j], EstadosMinerales.values()[k]);
					if(cantidad > 0) { // antes de crear la imagen se tiene que fijar que el jugador tenga por lo menos 1 de este mineral    						
					for(int l = 0;l< cantidad;l++) {
						inventario.add(new Image(new Sprite(new Texture(TipoMinerales.values()[j].ruta + EstadosMinerales.values()[k].ruta))));//crea las imagenes en funcion de la cantidad de cada tipo y estado de mineral que el jugador tenga en el inventario 										
					}
					}
					}
			

		}

			//los agrega a la tabla
			for(int i = 0; i<inventario.size();i++) {
				mineralesTabla.add(inventario.get(i));
			}
	
		System.out.println(HelpDebug.debub(getClass()) + stage.getActors().size);
		
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
		        final Mineral mineral = jugador.obtenerTodosLosMinerales().get(i);
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
