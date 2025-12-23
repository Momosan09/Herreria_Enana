package com.mygdx.hud;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.MyDragAndDrop;
import com.mygdx.utiles.recursos.Recursos;
import com.mygdx.entidades.Jugador;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.enums.TipoCombinacion;

public class Combinacion extends HUD{


	private Label labelInv, titulo;
	private Button cerrarBoton;
	private Skin skin;

    private MyDragAndDrop dragNDrop;
    private Jugador jugador;
    private ArrayList<Image> combinables;
    
    private int pad = 20;
    private boolean visible=false;
    
    public Combinacion(Jugador jugador) {
    	this.jugador = jugador;
    	combinables = new ArrayList<Image>();
        
        dragNDrop = new MyDragAndDrop(this.jugador);
        dragNDrop.create();

		construir();

        
    }
    @Override
    public void dibujar() {
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
		skin = new Skin(Gdx.files.internal(Recursos.hud.SKIN));
        
    	contenedor = new Table();
    	tabla = new Table();
    	tabla.setFillParent(true);
    	contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.YUNQUE_TEXTURA)));
    	contenedor.debug();
    	
    	cerrarBoton = new Button(skin);
    	cerrarBoton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				ocultar();
				MundoConfig.estadoJuego = EstadosDelJuego.JUEGO;
			}
		});
    	
    	titulo = new Label(Recursos.bundle.get("combinacion.titulo"), labelStyle);
    	labelInv = new Label(Recursos.bundle.get("combinacion.inventario"), labelStyle);
    	//traerinventario();
    }
	
	public void traerInventario() {
		for (int i = 0; i<jugador.obtenerTodosLosMinerales().size();i++) {
			 combinables.add(new Image(jugador.obtenerTodosLosMinerales().get(i).getTextura()));
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
		tabla.add(contenedor);
    	stage.addActor(tabla);
    	
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
	
	public Stage getDragAndDrop() {
		return dragNDrop.getStage();
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
		if(!visible) {	
			dragNDrop.refrescar();
			visible = true;
		}

	}

	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
		dragNDrop.ocultar();
	}

	

}
