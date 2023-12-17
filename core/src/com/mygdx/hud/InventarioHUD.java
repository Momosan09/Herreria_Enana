package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;

public class InventarioHUD implements HeadUpDisplay, Ocultable{

	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor, tabla, tablaMinerales, tablaArtefactos, tablaBarraItems;
	private Label encabezadoMinerales, encabezadoArtefactos, cantidadMineral;
	private Label[] nombreMineral;
	private Jugador jugador;
	
	private Label.LabelStyle labelStyle, labelStyleCantidades;
	
	 private boolean visible=false;
	
	public InventarioHUD(Jugador jugador) {
    	screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
        this.jugador = jugador;
        crearFuentes();
        crearActores();
        poblarStage();
	}
	
	
	@Override
	public void mostrar() {
		visible = true;
		
	}

	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
	}

	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		labelStyleCantidades  = EstiloFuente.generarFuente(20, Colores.BLANCO, false);
		
	}

	@Override
	public void crearActores() {
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(true);
		
		contenedor = new Table();
		contenedor.setDebug(true);
		
		tablaMinerales = new Table();
		tablaArtefactos = new Table();
		tablaBarraItems = new Table();
		
		encabezadoMinerales = new Label(Recursos.bundle.get("inventario.minerales"), labelStyle);
		encabezadoArtefactos = new Label(Recursos.bundle.get("inventario.artefactos"), labelStyle);
		
		nombreMineral = new Label[2];
		nombreMineral[0] = new Label("Piedra", labelStyle);
		nombreMineral[1] = new Label("Hierro", labelStyle);
		nombreMineral[0].setVisible(false);
		nombreMineral[1].setVisible(false);
		
	}

	@Override
	public void poblarStage() {
		
		tablaMinerales.add(encabezadoMinerales);
		tablaMinerales.row();
		
		tablaArtefactos.add(encabezadoArtefactos);
		tablaArtefactos.row();
		
		contenedor.add(tablaMinerales).expand().top().left();
		contenedor.row();
		contenedor.add(tablaArtefactos).expand().top().left();
		contenedor.row();
		contenedor.add(tablaBarraItems);
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.CARTA_TEXTURA)));//cambiar por otra tetura
		tabla.add(contenedor);
		
		stage.addActor(tabla);
		stage.addActor(nombreMineral[0]);
		
	}

    @Override
    public void reEscalar(int width, int heigth) {
    	screenViewport.update(width, heigth, true);
    }

	public void render(Jugador jugador) {
		if(visible) {
			//DibujarFiguras.dibujarRectanguloLleno(contenedor.getX(), contenedor.getY(), contenedor.getWidth(), contenedor.getHeight(), new Color(0,0,0,.7f));
	    	stage.act(Gdx.graphics.getDeltaTime());
	    	stage.draw();
	    	llenarMinerales(jugador);//Cada vez que se muestre el inventario llena las tablas
	    	//infoMineral();
		}
		
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void llenarInventario(Jugador jugador) {
		llenarMinerales(jugador);
	}
	
	public void llenarMinerales(Jugador jugador) {
		tablaMinerales.clear();
		tablaMinerales.add(encabezadoMinerales).row();
		//Este if me permite saber si el la tabla no esta actualizada y si no lo esta, actualizarla
		if(tablaMinerales.getChildren().size-1 != jugador.getMinerales().size()) {//Le resto 1 porque la Label es un children tambien
	    for (Mineral mineral : jugador.getMinerales()) {
	    	//System.out.println(HelpDebug.debub(getClass())+"Hay mineral");
	        // Crea una imagen para el mineral y la agrega a la tabla
	        Image mineralImage = new Image(mineral.getTextura());
	        tablaMinerales.add(mineralImage).size(64, 64).pad(5);
	    }
		}

	}
	
	public void infoMineral() {
		for(int i = 0; i<tablaMinerales.getChildren().size; i++) {
			if(Gdx.input.getX() >= tablaMinerales.getChild(i).getX() && Gdx.input.getY() >= tablaMinerales.getChild(i).getY()) {
				nombreMineral[0].setVisible(true);
				nombreMineral[0].setPosition(Gdx.input.getX(), Gdx.input.getY()+300);
				System.out.println(HelpDebug.debub(getClass())+"Mostrando");
			}
			
		}
		
	}


	@Override
	public void render() {//No usar
	}


	@Override
	public boolean getVisible() {
		return visible;
	}

}