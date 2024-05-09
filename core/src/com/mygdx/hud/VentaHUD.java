package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Vendedor;
import com.mygdx.hud.actoresEspeciales.CuadraditoItem;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;

public class VentaHUD implements HeadUpDisplay, Ocultable {

	private Vendedor vendedor;
	private Jugador jugador;
	private ScreenViewport screenViewport;
	private Stage stage;
	private Skin skin;
	private Button cerrarBoton;
	private Table contenedor, tabla;
	private Label.LabelStyle labelStyle;
	private boolean visible = true;

	public VentaHUD(Vendedor vendedor) {
		this.vendedor = vendedor;
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);

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

	}

	@Override
	public boolean getVisible() {
		return visible;
	}

	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);

	}

	@Override
	public void crearActores() {
		skin = new Skin(Gdx.files.internal(Recursos.SKIN));
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(false);

		contenedor = new Table();
		  // contenedor.defaults().size(100, 100); //Establece el tamaño deseado para cada casillero

		cerrarBoton = new Button(skin);
		cerrarBoton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				ocultar();
			}
		});
		
		
		
		   }

	@Override
	public void poblarStage() {
//		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.HORNO_TEXTURA)));
		int indiceFilas= 0;
		for (int i = 0; i < vendedor.getInventario().size(); i++) {
			contenedor.add(new CuadraditoItem(vendedor.getInventario().get(i))).pad(10);
			indiceFilas++;
			if(indiceFilas == 4) {
				contenedor.row();
				indiceFilas = 0;
			}
		}
		cerrarBoton.setDisabled(true);//temporal 

		tabla.add(contenedor);
		tabla.add(cerrarBoton).top();
		stage.addActor(tabla);
	}

	@Override
	public void reEscalar(int width, int heigth) {
		screenViewport.update(width, heigth, true);

	}

	@Override
	public void render() {
		if (visible) {
			stage.draw();
			stage.act();
		}

	}
	
	public void venta() {
		
	}
	
	public Stage getStage() {
		return stage;
	}

	public void dispose() {
		Recursos.muxJuego.removeProcessor(stage);//tengo que sacar el stage del inputprocesor porque el mux es estatico, entonces cuando entro y salgo del juego, el mux agrega el nuevo stage pero sigue guardando el anterior
		stage.dispose();
		skin.dispose();
	}

}