package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.utiles.Recursos;


/*
 	https://libgdxinfo.wordpress.com/basic_image/
 */
public class HUD{

	private Texture Dinero_Tex;
	private Stage stage;
	private Table hud;
	private Table dineroTable;
	private Sprite dineroImgSpr;
	private Label dineroLbl;
	private Label[] monedas;
	private Label centroLbl;
	private Label derechaLbl;
	private Label barraAbajoLbl;
	private Label.LabelStyle labelStyle;
	
    public HUD() {
    	cargarTexturas();
		generarFuente();
    	crearActores();
    	crearTablas();
    }
	
	public void crearTablas () {

		stage = new Stage();
		//Gdx.input.setInputProcessor(stage);
		hud = new Table();
		hud.setFillParent(true);
		hud.debug();
		
		dineroTable = new Table();
		dineroTable.setFillParent(false);
		dineroTable.debug();
		


		dineroTable.setBackground(new SpriteDrawable(dineroImgSpr));
		dineroTable.add(dineroLbl).row();;
		dineroTable.row();
		dineroTable.add(monedas[0], monedas[1], monedas[2]);
		
		hud.add(dineroTable);
		hud.add(centroLbl).expand();

        stage.addActor(hud);


	}
	
	public void dispose() {
		stage.dispose();
	}
	
	public void draw(SpriteBatch batch) {
		stage.act(Gdx.graphics.getDeltaTime());

		stage.draw();
	}
	
	private void cargarTexturas() {
		Dinero_Tex = new Texture(Recursos.DINERO_HUD);
		dineroImgSpr = new Sprite(Dinero_Tex);
	}
	
	private void crearActores() {
		dineroLbl = new Label("Dinero", labelStyle);
		monedas = new Label[3];
		monedas[0] = new Label("Au: ", labelStyle);
		monedas[1] = new Label("Ag: ", labelStyle);
		monedas[2] = new Label("Cu: ", labelStyle);
		
		centroLbl = new Label("Centro", labelStyle);
		derechaLbl = new Label("Hora", labelStyle);
		barraAbajoLbl = new Label("Barra De items", labelStyle);
	}
	
	private void generarFuente() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Recursos.FUENTE_TEMPORAL));
	    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	    parameter.size = 30;
	    parameter.borderWidth = 1;
	    parameter.color = Color.YELLOW;
	    parameter.shadowOffsetX = 3;
	    parameter.shadowOffsetY = 3;

	    BitmapFont font24 = generator.generateFont(parameter); // tamaño de la fuente 24 pixeles
	    generator.dispose();
	 
	    labelStyle = new Label.LabelStyle();
	    labelStyle.font = font24;
	}

	//adios ;( ...
}
