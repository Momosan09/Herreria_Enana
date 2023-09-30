package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.MyDragAndDrop;
import com.mygdx.utiles.Recursos;

import com.mygdx.io.Entradas;

public class Combinacion implements HeadUpDisplay{

	private Stage stage;
	private Table contenedor;
	private Label titulo;

    private DibujarFiguras fondo;
    private EstiloFuente estiloFuente;
	private Label.LabelStyle labelStyle;
    private float ancho=800, alto=700;
    Entradas entradas = new Entradas();
    
    MyDragAndDrop dragNDrop;

    public Combinacion() {
    	
    	fondo = new DibujarFiguras();
        stage = new Stage();
        
        dragNDrop = new MyDragAndDrop(stage);
        dragNDrop.create();
        crearFuentes();
        crearActores();
        poblarStage();
        

        
    }
    
    public void render() {
    	fondo.dibujarRectanguloLleno((Gdx.graphics.getWidth()/2)-(ancho/2), (Gdx.graphics.getHeight()/2)-(alto/2) , ancho, alto, new Color(0,0,0,.7f));
    	 stage.draw();
    	 dragNDrop.render();
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
