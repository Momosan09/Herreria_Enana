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

	private Texture dinero_Tex;
	private Texture reloj_Tex;
	private Image reloj;
	private Stage stage;
	private Table hud;
	private Table dineroTable;
	private Sprite dineroImgSpr;
	private Label dineroLbl;
	private Label[][] monedas;
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

		dineroTable.setBackground(new SpriteDrawable(dineroImgSpr));//para poner sprite de fondo
		dineroTable.add(dineroLbl);
		dineroTable.row();
		dineroTable.add(monedas[0][0], monedas[0][1], monedas[1][0], monedas[1][1], monedas[2][0], monedas[2][1]);
		
		hud.add(dineroTable).top();
		hud.add(centroLbl).expand();
		
		hud.add(reloj);
		hud.add(derechaLbl);

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
		dinero_Tex = new Texture(Recursos.DINERO_HUD);
		dineroImgSpr = new Sprite(dinero_Tex);
		
		reloj_Tex = new Texture(Recursos.RELOJ_HUD);
		reloj = new Image(reloj_Tex);
	}
	
	private void crearActores() {
		dineroLbl = new Label("Dinero ", labelStyle);
		monedas = new Label[3][2];
		monedas[0][0] = new Label("Au: ", labelStyle);
		monedas[0][1] = new Label("2", labelStyle);
		monedas[1][0] = new Label("Ag: ", labelStyle);
		monedas[1][1] = new Label("45", labelStyle);
		monedas[2][0] = new Label("Cu: ", labelStyle);
		monedas[2][1] = new Label("60", labelStyle);
		

		
		centroLbl = new Label("Centro", labelStyle);
		derechaLbl = new Label("Hora", labelStyle);
		barraAbajoLbl = new Label("Barra De items", labelStyle);
	}
	
	private void generarFuente() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Recursos.FUENTE_TEMPORAL));
	    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	    parameter.size = 30;
	    parameter.borderWidth = 1;
	    parameter.shadowOffsetX = 3;
	    parameter.shadowOffsetY = 3;

	    BitmapFont font24 = generator.generateFont(parameter); // tamaño de la fuente 24 pixeles
	    generator.dispose();
	 
	    labelStyle = new Label.LabelStyle();
	    labelStyle.font = font24;
	}

	//adios ;( ...
}
