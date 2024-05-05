package com.mygdx.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.entidades.npcs.VendedorData;
import com.mygdx.entidades.npcs.dialogos.Charla;
import com.mygdx.entidades.npcs.dialogos.NpcData;
import com.mygdx.enums.Items;
import com.mygdx.hud.Dialogo;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;

import java.util.ArrayList;

public abstract class Npc extends Entidad implements NpcInterface{
	
	protected String nombre;
	private Dialogo cajaDialogo;
	private ArrayList<String[]> paqueteDeCharlas;//Tiene los datos de las charlas. Es muy importante el que tenga en el npc_dialogos_***
	private Animator animacion;
	private Texture retrato;
	private NpcData data;
	private ArrayList<Items> inventario;
	private String nombreCharlaActual;//nombre de la charla que se va a usar
	
	public ArrayList<Charla> charlas;
	public boolean respuesta1 = false;
	public boolean respuesta2 = false;
	public boolean mostrarDialogo = true;

	public Npc(float x, float y, World world, String ruta, NpcData data){
		super(x, y, world, ruta);
		crearCuerpo(world,8,8);
		charlas = new ArrayList<Charla>();
		paqueteDeCharlas = new ArrayList<String[]>();
		
		this.data = data;
		this.nombre = this.data.getNombre();
		this.paqueteDeCharlas = data.getBloquesDeCharla();
		this.retrato = data.getTextura();

		OrganizadorSpritesIndiceZ.NPCS.add(this);
		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
	}
	
	public Npc(float x, float y, World world, String ruta, NpcData data, VendedorData itemsData){
		super(x, y, world, ruta);
		crearCuerpo(world,8,8);
		charlas = new ArrayList<Charla>();
		paqueteDeCharlas = new ArrayList<String[]>();
		
		this.data = data;
		this.nombre = this.data.getNombre();
		this.paqueteDeCharlas = data.getBloquesDeCharla();
		this.retrato = data.getTextura();
		this.inventario = itemsData.getInventario();

		OrganizadorSpritesIndiceZ.NPCS.add(this);
		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
	}
	
	public Npc(float x, float y, World world, String ruta, NpcData data, int ancho, int alto){//para los npc con colisiones mas grandes o mas chicas
		super(x, y, world, ruta);
		crearCuerpo(world, ancho, alto);
		charlas = new ArrayList<Charla>();
		paqueteDeCharlas = new ArrayList<String[]>();
		
		this.data = data;
		this.nombre = this.data.getNombre();
		this.paqueteDeCharlas = data.getBloquesDeCharla();
		this.retrato = data.getTextura();

		OrganizadorSpritesIndiceZ.NPCS.add(this);
		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
	}
	
	public Npc(float x, float y, World world, String ruta, int ancho, int alto){//para los npc con colisiones mas grandes o mas chicas
		super(x, y, world, ruta);
		crearCuerpo(world, ancho, alto);
		
		this.nombre = this.data.getNombre();
		this.paqueteDeCharlas = data.getBloquesDeCharla();
		this.retrato = data.getTextura();

		OrganizadorSpritesIndiceZ.NPCS.add(this);
		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
	}
	
	
	
	public boolean interaccion() {
		while(jugadorEnRango && apretoE) {
			if(mostrarDialogo) {
				cajaDialogo.mostrar=true;				
			}
			return true;
		}
		cajaDialogo.mostrar=false;
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
	
	public String getMensajeNroDePaqueteNro(int mensajeNro, int paqueteNro) {
		return paqueteDeCharlas.get(paqueteNro)[mensajeNro];
	}
	
	public String[] getPaqueteDeDialogosNro(int paqueteNro) {
		return paqueteDeCharlas.get(paqueteNro);
	}
	
	public void dibujarCajaDialogo() {
		if(cajaDialogo.mostrar) {			
		cajaDialogo.render();
		}
	}
	
	 public Dialogo getCajaDialogo() {
		 return cajaDialogo;
	 }

	 public void charla() {
		 if(interaccion()) {
//			 cajaDialogo.selectMensaje(index);
			 cajaDialogo.render();
		 }
	 }
	 
	 public void ejecutarAnimacion() {
		 animacion.render();
	 }
	 
	 public String getNombreCharlaActual() {
		 return nombreCharlaActual;
	 }
	 
	 public Charla getCharlaActual() {
		 for(int i = 0; i<charlas.size(); i++) {
			 if(charlas.get(i).nombreCharla.equals(nombreCharlaActual)) {
				 return charlas.get(i);
			 }
		 }
		return null;
	 }
	 
	 public void setCharlaActual(String nombreCharla) {
		 respuesta1 = false;
		 respuesta2 = false;
		 nombreCharlaActual = nombreCharla;
	 }
	 
	 
	 public void resetearRespuestas() {
		 respuesta1 = false;
		 respuesta2 = false;
	 }
	 
	 public void ocultarDialogo() {
		 mostrarDialogo = true;
	 }

}
