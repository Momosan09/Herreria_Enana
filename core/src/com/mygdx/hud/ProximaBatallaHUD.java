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
import com.mygdx.utiles.Recursos;

public class ProximaBatallaHUD implements HeadUpDisplay, Ocultable{

	private ScreenViewport screenViewport;
	private Stage stage;
	private Skin skin;
	private Table tabla, contenedor;
	private Button cerrarBoton;
	private Label.LabelStyle labelStyle;
	
	private boolean visible = false;
	
	
	public ProximaBatallaHUD() {
		crearFuentes();
		crearActores();
		poblarStage();
	}
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(22, Colores.BLANCO, false);
		
	}

	@Override
	public void crearActores() {
		skin = new Skin(Gdx.files.internal(Recursos.SKIN));
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		
		tabla = new Table();
		tabla.setFillParent(true);
		contenedor = new Table();
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.CARTA_TEXTURA)) );
		
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

	@Override
	public void reEscalar(int width, int heigth) {
		screenViewport.update(width, heigth, true);
		
		
	}

	@Override
	public void render() {
        if (visible) {
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
		
	}

	@Override
	public void mostrar() {
		visible = true;
		
	}

	@Override
	public void ocultar() {
		visible = false;
        stage.unfocusAll();
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void dispose() {
		Recursos.mux.removeProcessor(stage);//tengo que sacar el stage del inputprocesor porque el mux es estatico, entonces cuando entro y salgo del juego, el mux agrega el nuevo stage pero sigue guardando el anterior
		stage.dispose();
		skin.dispose();
	}

}
