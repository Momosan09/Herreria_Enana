package com.mygdx.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.hud.actoresEspeciales.ProximaBatallaTabla;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;

public class LibroHUD implements HeadUpDisplay, Ocultable{

	private ScreenViewport screenViewport;
	private Stage stage;
	private Table tabla, contenedor, columnaBotonesIzq, columnaIzq, columnaDer;
	private boolean visible = true;
	
	private Label.LabelStyle labelStyle;
	
	public LibroHUD(ScreenViewport screenViewPort) {
		this.screenViewport = screenViewPort;
		crearFuentes();
		crearActores();
		poblarStage();
		
	}
	
	
	@Override
	public void render() {
		if(visible) {
			stage.draw();
			stage.act();
		}
		
	}

	@Override
	public void crearActores() {
		stage = new Stage();
		tabla = new Table();
		contenedor = new Table();
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.LIBRO_HUD)));
		contenedor.setDebug(true);
		tabla.setFillParent(true);
		tabla.setDebug(true);	
		
		columnaIzq = new Table();
		columnaDer = new Table();
		columnaBotonesIzq = new Table();
		
	}

	@Override
	public void poblarStage() {
		
		contenedor.setWidth(800);
		contenedor.add(columnaBotonesIzq);
		contenedor.add(columnaIzq).expand();
		contenedor.add(columnaDer).expand();
		
		columnaIzq.add(new ProximaBatallaTabla("Los cuatro picos", "Ratas gigantes", 800, "Montañoso"));
		columnaIzq.row();
		columnaIzq.add(new ProximaBatallaTabla("Perdida de fe", "Orcos", 700, "planicie"));
		
		columnaDer.add(new ProximaBatallaTabla("Perdicion de los desamparados", "Ghouls", 300, "Caverna"));
		columnaDer.row();
		columnaDer.add(new ProximaBatallaTabla("El rugido estruendoso", "Dragon", 1, "Playa"));
		tabla.add(contenedor);
		stage.addActor(tabla);
		
	}

	@Override
	public void reEscalar(int width, int heigth) {
    	screenViewport.update(width, heigth, true);
		
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

	public Stage getStage() {
		return stage;
	}

	@Override
	public boolean getVisible() {
		return visible;
	}


	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
			
	}

}
