package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.recursos.Recursos;

public class DialogoDeCompra extends HUD{
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor, tabla;
	private Label titulo;
	private Button cerrarBoton;
	private TextButton aceptarBotton;
	private Skin skin;
	public boolean cerrar=false;
	

	public DialogoDeCompra() {
		super();
		 construir();
	}
	
	@Override
	public void crearActores() {
		skin = new Skin(Gdx.files.internal(Recursos.hud.SKIN));
		
		tabla = new Table();
		tabla.setFillParent(true);
		
		contenedor = new Table();
		contenedor.setDebug(true);
		
		titulo = new Label("Ingrese cantidad a comprar", labelStyle);
		
		aceptarBotton = new TextButton("Comprar!", skin);
		aceptarBotton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println(HelpDebug.debub(getClass())+"Compra");
			}
		});
		
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
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.CARTA_TEXTURA)));//cambiar por otra tetura
		contenedor.add(cerrarBoton).top().right();
		contenedor.row();
		contenedor.add(titulo).top();
		contenedor.row();
		contenedor.add(aceptarBotton);
		
		tabla.add(contenedor);
		stage.addActor(tabla);
		
		
		
	}

}
