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
import com.mygdx.utiles.Recursos;

public class DialogoDeCompra implements HeadUpDisplay, Ocultable{
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor, tabla;
	private Label titulo;
	private Button cerrarBoton;
	private TextButton aceptarBotton;
	private Skin skin;
	private boolean visible=false;
	public boolean cerrar=false;
	
	private Label.LabelStyle labelStyle;
	
	
	public DialogoDeCompra() {
    	screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
        
        crearFuentes();
        crearActores();
        poblarStage();
	}
	
	@Override
	public void mostrar() {
		visible = true;
		
	}

	public boolean isVisible() {
		return visible;
	}
	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
	}

	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		
	}

	@Override
	public void crearActores() {
		skin = new Skin(Gdx.files.internal(Recursos.SKIN));
		
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
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.CARTA_TEXTURA)));//cambiar por otra tetura
		contenedor.add(cerrarBoton).top().right();
		contenedor.row();
		contenedor.add(titulo).top();
		contenedor.row();
		contenedor.add(aceptarBotton);
		
		tabla.add(contenedor);
		stage.addActor(tabla);
		
		
		
	}

    @Override
    public void reEscalar(int width, int heigth) {
    	screenViewport.update(width, heigth, true);
    }

    
	public void render(Jugador jugador) {
		if(visible) {
	    	stage.act(Gdx.graphics.getDeltaTime());
	    	stage.draw();

		}
		
	}
	
	@Override
	public void render() {
		
	}
	
	public Stage getStage() {
		return stage;
	}

	@Override
	public boolean getVisible() {
		return visible;
	}
}
