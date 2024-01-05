package com.mygdx.hud;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MyDragAndDrop;
import com.mygdx.utiles.Recursos;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.io.Entradas;

public class Combinacion implements HeadUpDisplay, Ocultable{

	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor;
	private Label labelInv, titulo;
	private Button cerrarBoton;
	private Skin skin;
	private Label.LabelStyle labelStyle;

    private MyDragAndDrop dragNDrop;
    private Jugador jugador;
    private ArrayList<Image> combinables;
    
    private int pad = 20;
    private boolean visible=false;
    
    public Combinacion(Jugador jugador) {
    	this.jugador = jugador;
    	combinables = new ArrayList<Image>();
    	screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
        
        dragNDrop = new MyDragAndDrop(this.jugador);
        //dragNDrop.create();
        crearFuentes();
        crearActores();
        poblarStage();

        
    }
    
    public void render() {
    	if(visible) {
    	stage.act(Gdx.graphics.getDeltaTime());
    	stage.draw();
    	dragNDrop.render();
    	}
    }
    
    @Override
    public void reEscalar(int width, int heigth) {
    	screenViewport.update(width, heigth, true);
    	dragNDrop.resize(width, heigth);
    }
    
	@Override
    public void crearActores() {
		skin = new Skin(Gdx.files.internal(Recursos.SKIN));
        
    	contenedor = new Table();
    	contenedor.setFillParent(true);
//    	contenedor.debug();
    	
    	cerrarBoton = new Button(skin);
    	cerrarBoton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				ocultar();
			}
		});
    	
    	titulo = new Label(Recursos.bundle.get("combinacion.titulo"), labelStyle);
    	labelInv = new Label(Recursos.bundle.get("combinacion.inventario"), labelStyle);
    	//traerinventario();
    }
	
	public void traerinventario() {
		for (int i = 0; i<jugador.getMinerales().size();i++) {
			 combinables.add(new Image(jugador.getMinerales().get(i).getTextura()));
			 combinables.get(i).setPosition(32, 32);
		}
	}
	
	@Override
    public void poblarStage() {
		contenedor.pad(pad);
		contenedor.add(labelInv).top();
		contenedor.add(titulo).top();
		contenedor.add(cerrarBoton);
		contenedor.row();
		contenedor.add();
		contenedor.add().grow();
		contenedor.add();
		contenedor.add();
		//tabla.add(contenedor).center();
    	stage.addActor(contenedor);
    	
    }

	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(32, Colores.BLANCO, false);
		
	}

/*
	public void cerrar() {
	    contenedor.clear(); // Limpia todos los actores del contenedor
	    stage.unfocusAll(); // Desenfoca el stage para que no procese eventos
	    stage.clear(); // Limpia el stage completamente
		
	}
	*/
	public Stage getStage() {
		return stage;
	}
	
	public MyDragAndDrop getDragAndDrop() {
		return dragNDrop;
	}
/*
	public boolean getCerrar() {
		return cerrar;
	}
	
	public void setCerrar(boolean value) {
		cerrar=value;
	}
*/
	@Override
	public void mostrar() {
		visible = true;
		dragNDrop.refrescar();
		dragNDrop.create();

		
	}

	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
	}

	@Override
	public boolean getVisible() {
		return visible;
	}
}
