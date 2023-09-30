package com.mygdx.utiles;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Null;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.io.Entradas;

import com.badlogic.gdx.utils.Null;

public class MyDragAndDrop {
	Stage stage;
    private EstiloFuente estiloFuente;
	private Label.LabelStyle labelStyle;
	private ArrayList<Image> inventario;
	
	
	public MyDragAndDrop(Stage stage){
		this.stage = stage;
		estiloFuente = new EstiloFuente();
		labelStyle = estiloFuente.generarFuente(32, Colores.BLANCO, false);
		
		Sprite spr = new Sprite(new Texture(Recursos.YUNQUE));
		
		Image sourceImage = new Image(spr);
		
		Sprite spr2 = new Sprite(new Texture(Recursos.RELOJ_HUD));
		
		Image sourceImage2 = new Image(spr2);
		
		inventario = new ArrayList();
		
		inventario.add(sourceImage);
		inventario.add(sourceImage2);
		inventario.get(0).setPosition(500, 500);
		inventario.get(1).setPosition(600, 600);
	}

	public void create () {
		stage.setDebugAll(true);
		Gdx.input.setInputProcessor(stage);

		
		for (Image image : inventario) {
			stage.addActor(image);
		}


		DragAndDrop dragAndDrop = new DragAndDrop();
		
		for (Image image : inventario) {
			dragAndDrop.addSource(new Source(image) {//addSource permite que la imagen sea arrastrable, por eso necesito que cada imagen del inventario sea source
				@Null
				public Payload dragStart (InputEvent event, float x, float y, int pointer) {
					Payload payload = new Payload();
					payload.setObject("Some payload!");

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
		stage.getViewport().update(width, height, true);
	}

	public void dispose () {
		stage.dispose();
	}
}