package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.Principal;
import com.mygdx.hud.HUD;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.recursos.Recursos;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PantallaPrimera extends HUD implements Screen{
	private final Principal game;
	private Sound himno = Gdx.audio.newSound(Gdx.files.internal(Recursos.HIMNO));
	private Image bandera;
	private Label label;
	
	private Table contenedor, tabla;
	
	public PantallaPrimera(Principal g) {
		this.game = g;

		Render.batch = game.batch;
	
	}
	
	@Override
	public void show() {
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		
		crearFuentes(60);
		crearActores();
		poblarStage();
		himno.play();
		

		stage.addAction(
			Actions.sequence(
				Actions.delay(3f),//Espera 3s
				Actions.fadeOut(2.5f), //Desvanece por 2.5s
				Actions.delay(.1f),//espera .1s
				Actions.run(() -> {
					game.setScreen(new PantallaMenu(game));//cambia panta
					dispose();
				})
			)
		);
	}
		
	

	@Override
	public void render(float delta) {

		stage.draw();
		stage.act();
	}

	@Override
	public void resize(int width, int height) {
		screenViewport.update(width, height, true);		
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() {

	}
	
	@Override
	public void crearActores() {
		
		bandera = new Image(new Texture(Recursos.BANDERA));
		
		label = new Label("Industria Argentina", labelStyle);
		
		tabla = new Table();
		contenedor = new Table();
		contenedor.setFillParent(true);
	}
	
	@Override
	public void poblarStage() {
		tabla.add(bandera).padTop(5);
		tabla.row();
		tabla.add(label);
		contenedor.add(tabla);
		stage.addActor(contenedor);
	}

}
