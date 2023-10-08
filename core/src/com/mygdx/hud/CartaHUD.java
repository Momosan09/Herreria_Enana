package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.entidades.npcs.dialogos.DialogosNPC;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.EstiloFuente;

public class CartaHUD implements HeadUpDisplay{

	private Stage stage;
	private Table contenedor;
	private Label cuerpoCarta;
	private EstiloFuente estiloFuente;
	private Label.LabelStyle labelStyle;
	private DialogosNPC datosCartaNpc;
	
	
	public CartaHUD(DialogosNPC datosCartaNpc) {
		this.datosCartaNpc = datosCartaNpc;
		crearFuentes();
		crearActores();
		poblarStage();
	}
	
	@Override
	public void crearFuentes() {
		estiloFuente = new EstiloFuente();
		labelStyle = estiloFuente.generarFuente(20, Colores.BLANCO, false);
		
	}

	@Override
	public void crearActores() {
		stage = new Stage();
		
		contenedor = new Table();
		contenedor.debug();
		contenedor.setFillParent(false);
		contenedor.setSize(Config.ancho/2, Config.alto/2);
		contenedor.setPosition(200, 400);
		
		cuerpoCarta = new Label(datosCartaNpc.getMensaje(0), labelStyle);
		
	}

	@Override
	public void poblarStage() {
		contenedor.add(cuerpoCarta).grow();
		
		stage.addActor(contenedor);
		
	}

	@Override
	public void reEscalar(int width, int heigth) {
		// TODO Auto-generated method stub
	}

	public void render() {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();	
	}

}
