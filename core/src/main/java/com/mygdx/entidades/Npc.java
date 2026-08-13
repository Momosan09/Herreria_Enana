package com.mygdx.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.npcs.VendedorData;
import com.mygdx.entidades.npcs.dialogos.charlas.Charla;
import com.mygdx.entidades.npcs.dialogos.charlas.DialogoLoader;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.eventos.Listeners;
import com.mygdx.utiles.Animator;
import com.mygdx.utiles.EstadoMundo;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.OrganizadorSpritesIndiceZ;

public abstract class Npc extends Entidad implements NpcInterface, Interactuable{
	
	protected NpcData data;
	
	protected String nombre;
	private Animator animacion;
	private Texture retrato;
	private ArrayList<IngredientesId> inventario;
	
	public boolean mostrarDialogo = true;
	private boolean cerroDialogo = false;

	private EstadoMundo estadoM;
	
	//charlas
	private Map<String, Charla> charlas = new LinkedHashMap<>();//(Nodos)
	private Charla charlaActual;//(Nodo actual)
	private String charlaInicialId = "1";
	
	public Npc(float x, float y, World world, String ruta, NpcData data, EstadoMundo estadoM, String rutaCharlas){
		super(x, y, world, ruta);
		crearCuerpo(world,8,8);

		
		this.nombre = this.data.getNombre();
		this.retrato = data.getTextura();

		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
		cargarCharlas(rutaCharlas);
		charlaActual = charlas.get("1");//setea la charla actual (inicial) de todos los npcs con la charla con id 1 de el .json correspondiente de cada npc

		areaInteraccion = new Circle(posicion, radioInteraccion);
		areaInteraccion.setPosition(posicion.x, posicion.y);
		Listeners.agregarListener(this);
	}
	
	public Npc(float x, float y, World world, String ruta, NpcData data, VendedorData itemsData, EstadoMundo estadoM, String rutaCharlas){
		super(x, y, world, ruta);
		crearCuerpo(world,8,8);
		
		this.data = data;
		this.nombre = this.data.getNombre();
		this.retrato = data.getTextura();
		this.inventario = itemsData.getInventario();

		OrganizadorSpritesIndiceZ.NPCS.add(this);
		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
		cargarCharlas(rutaCharlas);
		charlaActual = charlas.get("1");//setea la charla actual (inicial) de todos los npcs con la charla con id 1 de el .json correspondiente de cada npc

		areaInteraccion = new Circle(posicion, radioInteraccion);
		areaInteraccion.setPosition(posicion.x, posicion.y);
	}
	
	public Npc(float x, float y, World world, String ruta, NpcData data, int ancho, int alto, EstadoMundo estadoM, String rutaCharlas){//para los npc con colisiones mas grandes o mas chicas
		super(x, y, world, ruta);
		crearCuerpo(world, ancho, alto);
		
		this.data = data;
		this.nombre = this.data.getNombre();
		this.retrato = data.getTextura();

		OrganizadorSpritesIndiceZ.NPCS.add(this);
		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
		cargarCharlas(rutaCharlas);
		charlaActual = charlas.get("1");//setea la charla actual (inicial) de todos los npcs con la charla con id 1 de el .json correspondiente de cada npc

		areaInteraccion = new Circle(posicion, radioInteraccion);
		areaInteraccion.setPosition(posicion.x, posicion.y);
	}
	
	public Npc(float x, float y, World world, String ruta, int ancho, int alto, EstadoMundo estadoM, String rutaCharlas){//para los npc con colisiones mas grandes o mas chicas
		super(x, y, world, ruta);
		crearCuerpo(world, ancho, alto);
		
		this.nombre = this.data.getNombre();
		this.retrato = data.getTextura();

		OrganizadorSpritesIndiceZ.NPCS.add(this);
		animacion = new Animator(ruta, posicion, 0);
		animacion.create();
		cargarCharlas(rutaCharlas);
		charlaActual = charlas.get("1");//setea la charla actual (inicial) de todos los npcs con la charla con id 1 de el .json correspondiente de cada npc

		areaInteraccion = new Circle(posicion, radioInteraccion);
		areaInteraccion.setPosition(posicion.x, posicion.y);
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
	
	 
	 public void ejecutarAnimacion() {
		 animacion.render();
	 }
	 
	 
	 public void ocultarDialogo() {
		 mostrarDialogo = true;
	 }
	 
	    @Override
	    public Circle getAreaInteraccion() {
	        return areaInteraccion;
	    }

	    
//	    @Override
//	    public void interactuar(Jugador jugador) {
//	    	System.out.println(HelpDebug.debub(getClass())+"Jugador interactuo con npc: " + nombre);
//			MundoConfig.estadoJuego = EstadosDelJuego.DIALOGO;
//			MundoConfig.locutor = this;	
//	        MundoConfig.dialogoManager.iniciar(MundoConfig.locutor);
//	    }

		public void agregarCharla(Charla charla) {
		    charlas.put(charla.id(), charla);
		}

		public void iniciarCharla(EstadoMundo mundo) {
	        if (charlaActual.puedeMostrarse(mundo, this)) {
	            //mostrarCharla();
	            return;
	        
	    }

	    //System.out.println(HelpDebug.debub(getClass())+"No hay charla disponible.");
	}

		private void mostrarCharla() {
			
		    System.out.println(charlaActual.monologo());

		    int i = 1;
		    for (var respuesta : charlaActual.respuestas()) {
		        System.out.println(i + ": " + respuesta.texto());
		        i++;
		    }
		    
		}
		

		public void setCharlaActual(String idSiguienteCharla) {

		    Charla siguiente = charlas.get(idSiguienteCharla);

		    if (siguiente != null) {
		        charlaActual = siguiente;
		        mostrarCharla();
		    }
		}

		public void cargarCharlas(String rutaJson) {

		    List<Charla> lista = DialogoLoader.cargar(rutaJson);

		    for (int i = 0; i<lista.size();i++) {
		        agregarCharla(lista.get(i));
		    }
		    
		    charlaActual = lista.get(0);
		    
		    setCharlaActual(lista.get(0).id());
		}

		public Charla getCharlaActual() {
			return charlaActual;
		}
		public void setCharlaInicial(String id) {
		    this.charlaInicialId = id;
		}
		
	 
}
