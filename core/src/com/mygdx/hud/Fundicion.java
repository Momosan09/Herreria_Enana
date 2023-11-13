package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;

public class Fundicion implements Ocultable, HeadUpDisplay{

	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor, tabla;
	private Label titulo;
	private Button botonArriba, botonAbajo;
	private Label.LabelStyle labelStyle;
	private Skin skinArriba, skinAbajo;
	private Jugador jugador;
	private int cantidad;
	 public boolean visible=false;
	
	 
	 public Fundicion(Jugador jugador) {
		 	this.jugador = jugador;
	    	screenViewport = new ScreenViewport();
	        stage = new Stage(screenViewport);
	        
	        crearFuentes();
	        crearActores();
	        poblarStage();
	 }
	


	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
			
	}
	
	@Override
	public void crearActores() {
		skinArriba = new Skin(Gdx.files.internal(Recursos.SKIN_BOTON_ARRIBA));
		skinAbajo = new Skin(Gdx.files.internal(Recursos.SKIN_BOTON_ABAJO));
		
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(true);
		
		contenedor = new Table();
		contenedor.setDebug(true);
		
		titulo = new Label("Horno de funducion", labelStyle);
		
		botonArriba = new Button(skinArriba);
		botonAbajo = new Button(skinAbajo);
		
		botonArriba.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if(cantidad >=1) {
					botonAbajo.setDisabled(false);
				}
				if(jugador.getMinerales().size() <= cantidad) {//aca voy a tener que buscar por mineral en especifico (ahora me toma tanto las piedras como el hierro como un todo)
					System.out.println(HelpDebug.debub(getClass())+"sumar");	
					cantidad++;
					System.out.println(cantidad);
				}else {
					botonArriba.setDisabled(true);
				}
			}
		});
		
		
		botonAbajo.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(cantidad >=1) {
					System.out.println(HelpDebug.debub(getClass())+"restar");
					
					cantidad--;
					System.out.println(cantidad);
				}else {
					botonAbajo.setDisabled(true);
				}
			}
		});
		
	}

	@Override
	public void poblarStage() {
		
		contenedor.add(titulo);
		contenedor.row();
		contenedor.add();
		contenedor.add(botonArriba);
		contenedor.row();
		contenedor.add();
		contenedor.add(botonAbajo);
		
		
		tabla.add(contenedor);
		stage.addActor(tabla);
		
		
	}

	@Override
	public void reEscalar(int width, int heigth) {
    	screenViewport.update(width, heigth, true);
		
	}
	
	public void render(Jugador jugador) {
		if(visible) {
			//DibujarFiguras.dibujarRectanguloLleno(contenedor.getX(), contenedor.getY(), contenedor.getWidth(), contenedor.getHeight(), new Color(0,0,0,.7f));
	    	stage.act(Gdx.graphics.getDeltaTime());
	    	stage.draw();
		}
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrar() {
		visible = true;
		
	}

	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
	}
	
	public Stage getStage() {
		return stage;
	}





	
}
