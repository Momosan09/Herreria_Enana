package com.mygdx.hud;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;

import com.mygdx.eventos.EventoHudConexion;

public class ConexionHUD implements HeadUpDisplay{
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor, tabla;
	private Label titulo, conexionExitosaLbl, esperandoJugadores;
	
	public boolean conexionExitosa;
	
	private Label.LabelStyle labelStyle,labelStyleError;
	
	
	public ConexionHUD() {
    	screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
        crearFuentes();
        crearActores();
        poblarStage();
	}
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		labelStyleError = EstiloFuente.generarFuente(30, Colores.ROJO, false);
	}

	@Override
	public void crearActores() {
		tabla = new Table();
		tabla.setFillParent(true);
		
		contenedor = new Table();
		
		titulo = new Label(Recursos.bundle.get("conexion.hud.titulo"), labelStyle);
		conexionExitosaLbl = new Label(Recursos.bundle.get("conexion.hud.conexionExitosa"), labelStyle);
		esperandoJugadores = new Label(Recursos.bundle.get("conexion.hud.esperandoJugadores"),labelStyle);
		
	
	}

	@Override
	public void poblarStage() {
		
		tabla.add(contenedor);
		
		contenedor.add(titulo);
		contenedor.row();
		contenedor.add(conexionExitosaLbl);
		contenedor.row();
		contenedor.add(esperandoJugadores);
		stage.addActor(tabla);
		
	}

	@Override
	public void reEscalar(int width, int heigth) {
		screenViewport.update(width, heigth, true);
		
	}

	@Override
	public void render() {
		if(!conexionExitosa) {
			conexionExitosaLbl.setVisible(false);;
			esperandoJugadores.setVisible(false);
		}else {
			conexionExitosaLbl.setVisible(true);;
			esperandoJugadores.setVisible(true);
		}
		stage.draw();
		
	}
	

	public void agregarMensaje(String msg, boolean error) {
		contenedor.add(new Label(msg, (error?labelStyleError:labelStyle)));
		contenedor.row();
		
	}
	
	public boolean getConexionExitosa() {
		return conexionExitosa;
	}

}
