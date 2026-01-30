package com.mygdx.hud.actoresEspeciales;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;

public class ProximaBatallaTabla extends Table{

	private Label nombreBatallaLbl, tipoEnemigoLbl, cantEnemigoLbl, tipoTerrenoLbl;
	private Table contenedor;
	private Label.LabelStyle labelStyle;
	
	public ProximaBatallaTabla(String nombreBatalla, String tipoEnemigo, int cantEnemigos,  String tipoTerreno) {
		crearFuentes();
		
		crearActores(nombreBatalla, tipoEnemigo, cantEnemigos, tipoTerreno);
		
		contenedor.add(nombreBatallaLbl);
		contenedor.row();
		contenedor.add(tipoEnemigoLbl);
		contenedor.row();
		contenedor.add(cantEnemigoLbl);
		contenedor.row();
		contenedor.add(tipoTerrenoLbl);
		add(contenedor);
		
	}
	
	private void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);			
		
	}
	
	private void crearActores(String nombreBatalla, String tipoEnemigo, int cantEnemigos, String tipoTerreno) {
		contenedor = new Table();
		nombreBatallaLbl = new Label(nombreBatalla, labelStyle);
		tipoEnemigoLbl = new Label("Tipo enemigo " + tipoEnemigo, labelStyle);
		cantEnemigoLbl = new Label("Cantidad aproximada = " + cantEnemigos, labelStyle);
		tipoTerrenoLbl = new Label("Tipo terreno: " + tipoTerreno, labelStyle);
	}
	
	
}
