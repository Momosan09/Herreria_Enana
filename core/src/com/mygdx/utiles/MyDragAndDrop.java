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

public class MyDragAndDrop {
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Label.LabelStyle labelStyle;
	private ArrayList<Image> inventario;
	private Jugador jugador;
	private DragAndDrop dragAndDrop;
	
	public MyDragAndDrop(Jugador jugador){
		dragAndDrop = new DragAndDrop();
		this.jugador = jugador;
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		
		labelStyle = EstiloFuente.generarFuente(32, Colores.BLANCO, false);
		
		inventario = new ArrayList<Image>();
		
		if(jugador.getMinerales().size() >0) {	
		for (Mineral image : jugador.getMinerales()) {
			inventario.add(new Image(new Sprite(image.getTextura())));//Agrega los minearales del inventario del personaje, aca puede haber un error cuando quiera combinar mas cosas que solo minerales
		}
		}
	}

	public void create () {
		stage.setDebugAll(true);
		System.out.println(HelpDebug.debub(getClass())+ "creado");
		for (Image image : inventario) {
			dragAndDrop.addSource(new Source(image) {//addSource permite que la imagen sea arrastrable, por eso necesito que cada imagen del inventario sea source
				@Null
				public Payload dragStart (InputEvent event, float x, float y, int pointer) {
					System.out.println("detecta");
					Payload payload = new Payload();
					payload.setObject(HelpDebug.debub(getClass())+"Some payload!");

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
		
		
		for (Image image : inventario) {//aca tengo que ver como discriminar las combinaciones permitidas y no permitidas
			dragAndDrop.addTarget(new Target(image) {
				public boolean drag (Source source, Payload payload, float x, float y, int pointer) {
					getActor().setColor(Color.GREEN);
					
					return true;
				}

				public void reset (Source source, Payload payload) {
					getActor().setColor(Color.WHITE);
				}

				public void drop (Source source, Payload payload, float x, float y, int pointer) {
					System.out.println("Accepted: " + payload.getObject() + " " + x + ", " + y);
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
		
		for (int i=0; i<jugador.getMinerales().size();i++) {
//			System.out.println("jeda"+jugador.getMinerales().size() + "i" + i);
			inventario.add(new Image(new Sprite(jugador.getMinerales().get(i).getTextura())));//Agrega los minearales del inventario del personaje, aca puede haber un error cuando quiera combinar mas cosas que solo minerales
			stage.addActor(inventario.get(i));
		}
	}

}
