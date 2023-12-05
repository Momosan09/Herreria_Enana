package com.mygdx.entidades;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
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
	private Texture retrato;
	private NpcData data;


	public Npc(float x, float y, World world, String ruta, NpcData data){
		super(x, y, world, ruta);
		crearCuerpo(world);
		this.data = data;
		this.nombre = this.data.getNombre();
		this.dialogos = data.getDialogos();
		this.retrato = data.getTextura();

		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
	}
	
	public Npc(float x, float y, World world, String ruta, NpcData data, int ancho, int alto){//para los npc con colisiones mas grandes o mas chicas
		super(x, y, world, ruta);
		crearCuerpo(world, ancho, alto);
		this.data = data;
		this.nombre = this.data.getNombre();
		this.dialogos = data.getDialogos();
		this.retrato = data.getTextura();

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
	
	public Texture getRetratoTextura() {
		return retrato;
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
		cajaDialogo.render();
	}
	
	 public Dialogo getCajaDialogo() {
		 return cajaDialogo;
	 }
	 
	 public void charla(int index) {
		 if(interaccion()) {
			 cajaDialogo.selectMensaje(index);
			 cajaDialogo.render();
		 }
	 }
	 
	 public void ejecutarAnimacion() {
		 animacion.render();
	 }
	 

}
