package com.mygdx.hud;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.historiales.ResultadosBatallas;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;

public class ResultadosBatallasHUD extends HUD{//Una cosa es cerrar y otra ocultar, si es Cerrable, ese hud no se va volver a ver porque se vacio la stage. Si es ocultable solamente renderiza o no


	private Label.LabelStyle labelStyle, labelStylePerdio, labelStyleGano;
	private ArrayList<String> historial;
	private ArrayList<Table> tablas;
	private ArrayList<Label> labels;
	private Skin skin;
	private Button cerrarBoton;
	public boolean cerrar = false;
	
	public ResultadosBatallasHUD() {
		historial = ResultadosBatallas.getHistorial();
		tablas = new ArrayList<Table>();
		labels = new ArrayList<Label>();
		construir();
	}


	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(22, Colores.BLANCO, false);
		labelStylePerdio = EstiloFuente.generarFuente(22, Colores.ROJO, false);
		labelStyleGano = EstiloFuente.generarFuente(22, Colores.VERDE, false);
		
	}

	@Override
	public void crearActores() {
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		skin = new Skin(Gdx.files.internal(Recursos.SKIN));
	
		tabla = new Table();
		tabla.setFillParent(true);
		
		contenedor = new Table();
//		contenedor.setDebug(true);
	
		historial.add(Recursos.bundle.get("resultadosBatalla.ganada"));//Se supone que va a venir ya con los datos
		historial.add(Recursos.bundle.get("resultadosBatalla.ganada"));
		historial.add(Recursos.bundle.get("resultadosBatalla.perdida"));
		historial.add(Recursos.bundle.get("resultadosBatalla.ganada"));
		
		for (int i = 0; i<historial.size();i++) {
			tablas.add(new Table());
			labels.add(new Label(historial.get(i), (historial.get(i).contains(Recursos.bundle.get("resultadosBatalla.ganada"))?labelStyleGano:labelStylePerdio)));//color verde si es ganada, color rojo si es perdida
			
//			if(i==historial.size()-1)System.out.println(HelpDebug.debub(getClass())+"Creadas= "+(i+1));
			
		}
		
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

		for (int i = 0; i<labels.size();i++) {
			tablas.get(i).add(new Label("Encabezado", labelStyle));
			tablas.get(i).row();
			tablas.get(i).add(labels.get(i)).fill();
			contenedor.add(tablas.get(i));
			contenedor.row();
		}
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.CARTA_TEXTURA)) );

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
