package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.recursos.Recursos;

public class ProximaBatallaHUD extends HUD{


	private Skin skin;
	private Button cerrarBoton;
	
	private boolean visible = false;
	
	
	public ProximaBatallaHUD() {
		construir();
	}
	
	@Override
	public void crearActores() {
		skin = new Skin(Gdx.files.internal(Recursos.hud.SKIN));
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		
		tabla = new Table();
		tabla.setFillParent(true);
		contenedor = new Table();
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.CARTA_TEXTURA)) );
		
		cerrarBoton = new Button(skin);
		cerrarBoton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				ocultar();
			}
		});
		
		
	}

	@Override
	public void poblarStage() {
		
		contenedor.add(new Label(Recursos.bundle.get("siguienteBatalla.nombreBatalla"), labelStyle));//Para el nombre de batalla nose si tendre que hacer un enum o "Plural Forms" de I18nBundle https://libgdx.com/wiki/internationalization-and-localization#plural-forms
		contenedor.row();
		contenedor.add(new Label(Recursos.bundle.get("siguienteBatalla.enemigos"), labelStyle));
		contenedor.add(new Label(Recursos.bundle.get("siguienteBatalla.tipoEnemigos"), labelStyle));
		contenedor.row();
		contenedor.add(new Label(Recursos.bundle.get("siguienteBatalla.terreno"), labelStyle));
		contenedor.add(new Label(Recursos.bundle.get("siguienteBatalla.tipoTerreno"), labelStyle));
		
		tabla.add(contenedor).center();
		tabla.add(cerrarBoton).top();
		stage.addActor(tabla);
		
	}

	public void dispose() {
		Recursos.muxJuego.removeProcessor(stage);//tengo que sacar el stage del inputprocesor porque el mux es estatico, entonces cuando entro y salgo del juego, el mux agrega el nuevo stage pero sigue guardando el anterior
		stage.dispose();
		skin.dispose();
	}

}
