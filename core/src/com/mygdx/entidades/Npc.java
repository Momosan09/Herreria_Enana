package com.mygdx.entidades;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.hud.Dialogo;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.Render;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Npc extends Entidad implements NpcInterface{
	
	protected String nombre;
	private Dialogo cajaDialogo;
	private List<String> dialogos;
	private Animator animacion;
	private NpcData data;

	public Npc(float x, float y, String ruta, NpcData data){
		super(x,y,ruta);
		this.data = data;
		this.nombre = this.data.getNombre();
		this.dialogos = data.getDialogos();


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
	
	@Override
	public NpcData getData() {
		return data;
	}
	
	public void crearCajaDialogo() {
		cajaDialogo = new Dialogo(this);
	}
	
	/*
	public void agregarDialogo() {
		for(int i = 0; i<data.getDialogos().length;i++) {
			this.dialogos.add(data.getDialogos().toString());
		}
	}*/
	   
	public String getDialogos(int index) {//Devuelve el String que esta en el indice pasado
		return dialogos.get(index);
	}
	
	public void dibujarCajaDialogo(SpriteBatch batch) {
		cajaDialogo.draw(batch);
	}
	
	 public Dialogo getCajaDialogo() {
		 return cajaDialogo;
	 }
	 
	 public void charla(int index, SpriteBatch batch) {
		 cajaDialogo.selectMensaje(index);
		 cajaDialogo.draw(batch);
	 }
	 
	 public void ejecutarAnimacion() {
		 animacion.render();
	 }
}
