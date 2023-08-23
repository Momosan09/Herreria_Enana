package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.utiles.Recursos;


/*
 	https://libgdxinfo.wordpress.com/basic_image/
 */
public class HUD{

	private Viewport vwp;
	private Texture Dinero_Tex;
	private Stage stage;
	private Image dinero;
	private Label dineroLbl;
	
	public HUD() {

	    cargarImagenes();
	    vwp = new ScreenViewport();
		create();
		
	}
	
	public void create () {
		
		stage = new Stage(vwp);
		Gdx.input.setInputProcessor(stage);
		
		dinero = new Image(Dinero_Tex);

		int escala = 3;
        dinero.setScale(escala);
        dinero.setPosition(20, Gdx.graphics.getHeight() - ((dinero.getHeight()*escala)*1.2f));

        stage.addActor(dinero);

	}
	
	public void dispose() {
		stage.dispose();
	}
	
	public void draw(SpriteBatch batch) {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	private void cargarImagenes() {
		Dinero_Tex = new Texture(Recursos.DINERO_HUD);
	}
	
	
}
