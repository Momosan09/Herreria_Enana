package com.mygdx.hud;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Render;

public class ObjetoDelInventarioApilable{

	private String nombre;
	private Image imagen;
	private int cantidad;
	private Label etiquetaDeCantidad;
	private Label.LabelStyle labelStyle;
	
	public Table tabla;
	
	
	public ObjetoDelInventarioApilable(String nombre, String tex) {
		this.nombre = nombre;
		this.imagen = new Image(new Texture(tex));
		this.cantidad = 0;
		
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		
		   etiquetaDeCantidad = new Label("x1", labelStyle);

		    // Crea una nueva tabla para contener la imagen y la etiqueta
		    tabla = new Table();
		    
		    
		    // Apila la imagen y la etiqueta, con la etiqueta encima de la imagen
		    tabla.stack(imagen, etiquetaDeCantidad).expand().fill();
	}
	
	public void aumentarCantidad() {
		cantidad+=1;
		setearCantidad();
	}
	
	public void aumentarCantidad(int cantidad) {
		this.cantidad+=cantidad;
		setearCantidad();
	}
	
	public void disminuirCantidad() {
		cantidad-=1;
		setearCantidad();
	}
	
	public void disminuirCantidad(int cantidad) {
		this.cantidad-=cantidad;
		setearCantidad();
	}
	
	public void setearCantidad() {
		etiquetaDeCantidad.setText("x"+String.valueOf(cantidad));
	}
	
	public Image getImagen() {
		return imagen;
	}
	
	public Label getLabel() {
		return etiquetaDeCantidad;
	}

}
