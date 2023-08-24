package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.utiles.Recursos;


/*
 	https://libgdxinfo.wordpress.com/basic_image/
 */
public class HUD{

	private Texture Dinero_Tex;
	private Stage stage;
	private Image dinero;
	private Label dineroLbl;
	private Label.LabelStyle labelStyle;
	
    public HUD() {
    	cargarTexturas();

        create();
    }
	
	public void create () {
		generarFuente();

		stage = new Stage();
		//Gdx.input.setInputProcessor(stage);

		
		dinero = new Image(Dinero_Tex);//96x32

		
        dinero.setPosition(20, Gdx.graphics.getHeight()-dinero.getHeight()*4); // Posición fija en la parte superior izquierda
        dineroLbl = new Label("Dinero", labelStyle);
        dineroLbl.setPosition(dinero.getX(), dinero.getY()+dineroLbl.getHeight()-2);

        
        stage.addActor(dinero);
        stage.addActor(dineroLbl);

	}
	
	public void dispose() {
		stage.dispose();
	}
	
	public void draw(SpriteBatch batch) {
		stage.act(Gdx.graphics.getDeltaTime());
		System.out.println("stage viewport ventana =" + (stage.getViewport().getCamera().viewportWidth = Gdx.graphics.getWidth()));
		
reescalar(dinero);
		stage.draw();
	}
	
	private void cargarTexturas() {
		Dinero_Tex = new Texture(Recursos.DINERO_HUD);
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
	private void reescalar(Image imagen) {
		//medidas de ejemplo
        float relacionDeAspecto = imagen.getWidth()/imagen.getHeight();// 96/32 = 3
        float ancho = stage.getViewport().getCamera().viewportWidth = Gdx.graphics.getWidth();//reajustar el tamaño del viewport de la stage con el tamaño de la ventana    
        stage.getViewport().getCamera().viewportHeight = Gdx.graphics.getHeight();//reajustar el tamaño del viewport de la stage con el tamaño de la ventana 
        float anchoHUD = ancho*0.16f;// 1280 * 0.16 = 204.8px
        
        float altoHUD = anchoHUD / relacionDeAspecto;// 204.8/3 = 68.266px.
        imagen.setSize(anchoHUD, altoHUD);//204.8 x 68.266
        //imagen.setPosition(ancho-ancho, 200); // Posición fija en la parte superior izquierda

	}
	
}
