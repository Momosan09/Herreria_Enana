package com.mygdx.entidades;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.hud.Dialogo;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.Render;
import java.util.ArrayList;
import java.util.List;

public abstract class Npc extends Entidad implements NpcInterface{
	
	protected String nombre;
	private Dialogo dialogo;
	private List<String> dialogos;
	private Animator animacion;

	public Npc(float x, float y, String ruta, String nombre){
		super(x,y,ruta);
		this.nombre = nombre;
		this.dialogos = new ArrayList<String>();
		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
	}
	
	public boolean interaccion() {
		while(jugadorEnRango && apretoE) {
			return true;
		}
		return false;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void crearCajaDialogo() {
		dialogo = new Dialogo(this);
	}
	
	public void agregarDialogo(String dialogo) {
	        dialogos.add(dialogo);
	}
	   
	public String getDialogos(int index) {//Devuelve el String que esta en el indice pasado
		return dialogos.get(index);
	}
	
	public void dibujarCajaDialogo(SpriteBatch batch) {
		dialogo.draw(batch);
	}
	
	 public Dialogo getDialogo() {
		 return dialogo;
	 }
	 
	 public void ejecutarAnimacion() {
		 animacion.render();
	 }
}
