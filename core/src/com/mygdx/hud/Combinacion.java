package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.MyDragAndDrop;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.io.Entradas;

public class Combinacion implements HeadUpDisplay{

	private Stage stage;
	private Table contenedor;
	private Label titulo;

    private DibujarFiguras fondo, dondeApuntar, cuadradoRojo;
    private EstiloFuente estiloFuente;
	private Label.LabelStyle labelStyle;
    private float ancho=800, alto=700;
    Entradas entradas = new Entradas();
    
    MyDragAndDrop dp;

    public Combinacion() {
    	
    	fondo = new DibujarFiguras();
    	cuadradoRojo = new DibujarFiguras();
    	dondeApuntar = new DibujarFiguras();
        stage = new Stage();
        
        dp = new MyDragAndDrop(stage);
        dp.create();
        crearFuentes();
        crearActores();
        poblarStage();
        

        
    }
    
    public void render() {
    	fondo.dibujarRectanguloLleno((Gdx.graphics.getWidth()/2)-(ancho/2), (Gdx.graphics.getHeight()/2)-(alto/2) , ancho, alto, new Color(0,0,0,.7f));
    	 stage.draw();
    	 dondeApuntar.dibujarRectanguloLleno(490, 490, 10, 10, new Color(0,1,0,1));
    	 dp.render();
    }
    
	@Override
    public void crearActores() {
    	contenedor = new Table();
    	contenedor.setPosition((Gdx.graphics.getWidth()/2)-(ancho/2), (Gdx.graphics.getHeight()/2)-(alto/2));
    	contenedor.setWidth(ancho);
    	contenedor.setHeight(alto);
    	//contenedor.setFillParent(true);
    	contenedor.debug();
    	
    	titulo = new Label(Recursos.bundle.get("combinacion.titulo"), labelStyle);
    	
    }
	
	@Override
    public void poblarStage() {
		contenedor.add(titulo).top();
		contenedor.row();
		contenedor.add().expand();
		
    	stage.addActor(contenedor);
    }

	@Override
	public void crearFuentes() {
		estiloFuente = new EstiloFuente();
		labelStyle = estiloFuente.generarFuente(32, Colores.BLANCO, false);
		
	}
	
}
