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
import com.mygdx.entidades.Npc;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;

public class Dialogo {

	Npc locutor;
	
	private Stage stage;
	private Table cajaDeDialogo;
	private Label nombre, mensaje;
	private Image retrato;
	
	private EstiloFuente estiloFuente;
	private Label.LabelStyle labelStyle;
	private int mensajeAMostrar, padding = 20;
	
	private ShapeRenderer render;
	
	public Dialogo(Npc locutor) {
		this.locutor = locutor;
		System.out.println("mostando dialgo");
		render = new ShapeRenderer();
		poblarStage();
		
		
	}
	
	public void draw(SpriteBatch batch) {
		update();
		fondoDeTabla();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		

	}
	
	public void dispose() {
		stage.dispose();
	}
	
	public void update() {
		mensaje.setText(locutor.getDialogos(mensajeAMostrar));
	}
	
	public void poblarStage() {
		crearFuentes();
		crearActores();
		stage = new Stage();
		cajaDeDialogo = new Table();
		cajaDeDialogo.setFillParent(true);
		cajaDeDialogo.setDebug(true);

		cajaDeDialogo.add(nombre).expandX().left();
		cajaDeDialogo.row();
		cajaDeDialogo.add(mensaje).size(Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/3).left();
		cajaDeDialogo.add(retrato).size(retrato.getWidth()*2, Gdx.graphics.getHeight()/3);
		cajaDeDialogo.bottom().setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/3);
		cajaDeDialogo.pad(padding);
		stage.addActor(cajaDeDialogo);
		

		
	}
	
	public void fondoDeTabla() {
		Gdx.gl.glEnable(GL30.GL_BLEND);		//Esto para que funcione el canal alpha de setColor() // esto te lo robe a vos facu 
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.rect(0, padding, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/3);
		render.setColor(new Color(0,0,0,0.5f));
		render.end();
	}
	
	public void crearActores() {
		
		nombre = new Label(locutor.getNombre(), labelStyle);
		mensaje = new Label(locutor.getDialogos(mensajeAMostrar), labelStyle);
		retrato = new Image(locutor.getRetratoTextura());
		
	}
	
	public void crearFuentes() {
		estiloFuente = new EstiloFuente();
		labelStyle = estiloFuente.generarFuente(22, Colores.BLANCO, false);
	}
	
	public void selectMensaje(int index) {
		mensajeAMostrar = index;
	}
}
