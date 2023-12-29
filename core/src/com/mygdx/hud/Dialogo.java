package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Npc;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;

public class Dialogo implements HeadUpDisplay{

	private Npc locutor;
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Table cajaDeDialogo;
	private Label nombre, mensaje, respuestas[];
	private Image retrato;
	
	private Label.LabelStyle labelStyle;
	private int mensajeAMostrar, padding = 20;
	private boolean tieneRespuesta = false;
	
	
	public Dialogo(Npc locutor) {
		this.locutor = locutor;
		respuestas = new Label[2];

		//System.out.println("mostrando dialgo");
		poblarStage();
		
		
	}
	
	@Override
	public void render() {
		update();
		DibujarFiguras.dibujarRectanguloLleno(0, padding, cajaDeDialogo.getWidth(), cajaDeDialogo.getRowHeight(1), new Color(0,0,0,.5f));
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}
	
	public void dispose() {
		stage.dispose();
	}
	
	public void update() {
		mensaje.setText(locutor.getBloque(0, 0, 0));
		respuestas[0].setText(locutor.getBloque(0, 0, 1));
		respuestas[1].setText(locutor.getBloque(0, 0, 2));
	}
	
	@Override
	public void crearActores() {
		nombre = new Label(locutor.getNombre(), labelStyle);
		mensaje = new Label("asd", labelStyle);
		
		respuestas[0] = new Label("das", labelStyle);
		respuestas[1] = new Label("fsaf", labelStyle);
	
		retrato = new Image(locutor.getRetratoTextura());
		
	}
	
	@Override
	public void poblarStage() {
		crearFuentes();
		crearActores();
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		cajaDeDialogo = new Table();
		cajaDeDialogo.setFillParent(true);
		cajaDeDialogo.setDebug(true);
		
		cajaDeDialogo.add(nombre).expandX().left();
		cajaDeDialogo.row();
		cajaDeDialogo.add(mensaje).size(Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/3).left();
		cajaDeDialogo.add(retrato).size(retrato.getWidth()*2, Gdx.graphics.getHeight()/3);
		cajaDeDialogo.bottom().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/3);
		cajaDeDialogo.pad(padding);

		cajaDeDialogo.row();
		cajaDeDialogo.add(respuestas[0]);
		cajaDeDialogo.row();
		cajaDeDialogo.add(respuestas[1]);
		stage.addActor(cajaDeDialogo);
		
		
	}	
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(22, Colores.BLANCO, false);
	}
	
	public void selectMensaje(int index) {
		mensajeAMostrar = index;
	}

	@Override
	public void reEscalar(int width, int heigth) {
		screenViewport.update(width, heigth,true);
		DibujarFiguras.dibujarRectanguloLleno(0, padding, cajaDeDialogo.getWidth(), cajaDeDialogo.getRowHeight(1), new Color(0,0,0,.5f));
		
	}


}
