package com.mygdx.hud.actoresEspeciales;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.entidades.ObjetoDelMapa;

public class ImagenApilable extends Image{

	
	private ObjetoDelMapa tipo; //esto es lo que lleva adentro, es decir que si el una imagen apilable de hierro puro, esto va a ser del tipo HierroPuro
	private int cantidad;//tendria que hacer un arrayList y que lleve datos verdaderos o na?
	//private float peso; //Para un futuro, en el que quiera agregar un sistema de peso de carga
	private Label cantidadLb;
	private Label.LabelStyle estilo;
	
	public ImagenApilable(ObjetoDelMapa tipo) {
		super();
		
	}
}
