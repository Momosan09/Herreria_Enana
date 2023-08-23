package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;

/*
 	https://libgdxinfo.wordpress.com/basic_image/
 */
public class HUD {

	private Viewport vwp;
	private Texture HUD_0_Tex;
	private Stage stage;
	private Image image1;
	
	public HUD() {

	    cargarImagenes();
		create();
		
	}
	
	public void create () {
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		image1 = new Image(HUD_0_Tex);
		image1.setPosition(Config.ancho/2, Config.alto/2);	
		stage.addActor(image1);

	}
	
	public void dispose() {
		stage.dispose();
	}
	
	public void draw(SpriteBatch batch) {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	private void cargarImagenes() {
		HUD_0_Tex = new Texture(Recursos.HUD_0);
	}
	
}
