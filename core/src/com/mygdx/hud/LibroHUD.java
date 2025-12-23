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
import com.mygdx.utiles.recursos.Recursos;

public class LibroHUD extends HUD{

	private Table columnaBotonesIzq, columnaIzq, columnaDer;
	
	public LibroHUD() {
		super();	
		construir();
	}
	

	@Override
	public void crearActores() {
		stage = new Stage();
		tabla = new Table();
		contenedor = new Table();
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.LIBRO_HUD)));
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

}
