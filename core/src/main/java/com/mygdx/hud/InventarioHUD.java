package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Mineral;
import com.mygdx.entidades.ObjetosDelMapa.Items.Item;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.enums.Items;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.recursos.Recursos;

import java.util.HashMap;
import java.util.Map;

public class InventarioHUD extends HUD{


	private Table tablaMinerales, tablaArtefactos, tablaBarraItems;
	private Label encabezadoMinerales, encabezadoArtefactos, cantidadMineral;
	private Label[] nombreMineral;
	private ObjetoDelInventarioApilable hierroMena;
	
	private Skin skin = new Skin(Gdx.files.internal(Recursos.hud.SKIN_TOOLTIP));
	private Label.LabelStyle labelStyleCantidades;
	private int[] cantidadDeCadaMineral = new int[2];	
	  private boolean mineralesCargados = false;
	 
	 private HashMap<String, Integer> mineralesApilados = new HashMap<>();
	
	public InventarioHUD(Jugador jugador) {
    	super();
        this.jugador = jugador;
		construir();

	}
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		labelStyleCantidades  = EstiloFuente.generarFuente(20, Colores.BLANCO, false);
		
	}

	@Override
	public void crearActores() {
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(true);
		
		contenedor = new Table();
		contenedor.setDebug(true);
		
		tablaMinerales = new Table();
		tablaArtefactos = new Table();
		tablaBarraItems = new Table();
		
		encabezadoMinerales = new Label(Recursos.bundle.get("inventario.minerales"), labelStyle);
		encabezadoArtefactos = new Label(Recursos.bundle.get("inventario.artefactos"), labelStyle);
		
		nombreMineral = new Label[2];
		nombreMineral[0] = new Label("Piedra", labelStyle);
		nombreMineral[1] = new Label("Hierro", labelStyle);
		nombreMineral[0].setVisible(false);
		nombreMineral[1].setVisible(false);

		//hierroMena = new ObjetoDelInventarioApilable("Hierro Mena", Recursos.MENA_HIERRO);
	}

	@Override
	public void poblarStage() {
		
		tablaMinerales.add(encabezadoMinerales);
		tablaMinerales.row();
		
		tablaArtefactos.add(encabezadoArtefactos);
		tablaArtefactos.row();
		
		contenedor.add(tablaMinerales).expand().top().left();
		contenedor.row();
		contenedor.add(tablaArtefactos).expand().top().left();
		contenedor.row();
		contenedor.add(tablaBarraItems);
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.CARTA_TEXTURA)));//cambiar por otra tetura
		tabla.add(contenedor);
		
		stage.addActor(tabla);
		stage.addActor(nombreMineral[0]);

		
	}

	@Override
	public void dibujar() {

		if(visible) {
			if(!mineralesCargados) {
		    	llenarInventario();//Cada vez que se muestre el inventario llena las tablas
				mineralesCargados = true;				
			}
			//DibujarFiguras.dibujarRectanguloLleno(contenedor.getX(), contenedor.getY(), contenedor.getWidth(), contenedor.getHeight(), new Color(0,0,0,.7f));
	    	stage.act(Gdx.graphics.getDeltaTime());
	    	stage.draw();
		}else {
			mineralesCargados = false;
		}
//	    	infoMineral();
		}
		
	
	
	public void llenarInventario() {
		llenarMinerales();
		llenarArtefactos();
	}
	
	public void llenarMinerales() {
		tablaMinerales.clear();
		tablaMinerales.add(encabezadoMinerales).row();
		tablaMinerales.setDebug(true);
		//Este if me permite saber si el la tabla no esta actualizada y si no lo esta, actualizarla
		if(tablaMinerales.getChildren().size-1 != jugador.obtenerIngredientesParaCrafteo().size()) {//Le resto 1 porque la Label es un children tambien
	    for (IngredientesId mineral : jugador.obtenerIngredientesParaCrafteo()) {
	    	//System.out.println(HelpDebug.debub(getClass())+"Hay mineral");
	        // Crea una imagen para el mineral y la agrega a la tabla
	        Image mineralImage = new Image(mineral.getTextura());
//	        mineralImage.addListener(new TextTooltip("el pene", skin));

	        tablaMinerales.add(mineralImage).size(64, 64).pad(5);
	        
	    }
	    }
		
		/*
		for(int i = 0; i < jugador.getMinerales().size(); i++) {
	
			
			switch (jugador.getMinerales().get(i).tipo) {
			case PIEDRA:
				switch (jugador.getMinerales().get(i).estado) {
				case MENA:
		            mineralesApilados.put(TipoMinerales.PIEDRA.toString() + "_" + EstadosMinerales.MENA.toString(), mineralesApilados.getOrDefault(TipoMinerales.PIEDRA.toString() + "_" + EstadosMinerales.MENA.toString(), 0) + 1);
					break;

				default:
					break;
				}

				break;
			
			case CARBON:
				
				break;
			
			case HIERRO:
				switch (jugador.getMinerales().get(i).estado) {
				case MENA:
					 mineralesApilados.put(TipoMinerales.HIERRO.toString() + "_" + EstadosMinerales.MENA.toString(), mineralesApilados.getOrDefault(TipoMinerales.HIERRO.toString() + "_" + EstadosMinerales.MENA.toString(), 0) + 1);
					 hierroMena.aumentarCantidad(mineralesApilados.get(TipoMinerales.HIERRO.toString() + "_" + EstadosMinerales.MENA.toString()));
					 break;
				case PURO:
					
					break;
					
				case LINGOTE:
					
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}

		}
		System.out.println();
		

		tablaMinerales.add(hierroMena.tabla).size(96,96);
	*/

		}
	
	 private void actualizarMineralesApilados() {
	        // Limpiar el mapa antes de actualizar
	        mineralesApilados.clear();
	        
	        // Recorrer la lista de minerales del inventario
	        for (Mineral mineral : jugador.obtenerTodosLosMinerales()) {
	            // Obtener la combinación de tipo y estado del mineral
	            String clave = mineral.tipo.toString() + "_" + mineral.getEstado().toString();
	            
	            // Actualizar la cantidad correspondiente en el mapa
	            mineralesApilados.put(clave, mineralesApilados.getOrDefault(clave, 0) + 1);
	        }
	    }
	
	public void llenarArtefactos() {
		tablaArtefactos.clear();
		tablaArtefactos.add(encabezadoArtefactos).row();
		//Este if me permite saber si el la tabla no esta actualizada y si no lo esta, actualizarla
		if(tablaArtefactos.getChildren().size-1 != jugador.getItems().size()) {//Le resto 1 porque la Label es un children tambien
	    for (Item artefacto : jugador.getItems()) {
	    	//System.out.println(HelpDebug.debub(getClass())+"Hay mineral");
	        // Crea una imagen para el mineral y la agrega a la tabla
	        Image artefactoImage = new Image(artefacto.getTextura());

	        tablaArtefactos.add(artefactoImage).size(64, 64).pad(5);
	    }
		}
	}
	
	public void infoMineral() {
		for(int i = 0; i<tablaMinerales.getChildren().size; i++) {
			if(Gdx.input.getX() >= tablaMinerales.getChild(i).getX() && Gdx.input.getY() >= tablaMinerales.getChild(i).getY()) {
				
				nombreMineral[0].setText(tablaMinerales.getChildren().get(i).getName()+ " no anda");
				nombreMineral[0].setVisible(true);
				nombreMineral[0].setPosition(Gdx.input.getX(), Gdx.graphics.getHeight()- Gdx.input.getY());
				System.out.println(HelpDebug.debub(getClass())+"Mostrando");
			}
			
		}
		
	}


}