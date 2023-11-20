package com.mygdx.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.utiles.Animator;
import java.util.List;

public abstract class Npc extends Entidad implements NpcInterface{
	
	protected String nombre;
	private List<String> dialogos;
	private Animator animacion;
	private Texture retrato;
	private NpcData data;

	public Npc(float x, float y, String ruta, NpcData data){
		super(x, y, ruta);
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
	
	   
	public String getDialogos(int index) {//Devuelve el String que esta en el indice pasado
		return dialogos.get(index);
	}
	
	 
	 public void ejecutarAnimacion() {
		 animacion.render();
	 }
}
