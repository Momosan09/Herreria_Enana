package com.mygdx.hud;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.recursos.Recursos;
import com.mygdx.historia.Mision;
import com.mygdx.historia.misiones.MisionRecFab;

public class DiarioHUD extends HUD{


	private Table tablaTareas;
	private Skin skin;
	private Button cerrarBoton;
	private Label titulo;
	private ScrollPane scrollPane;
	private ArrayList<Table> tareas = new ArrayList<>();
	private ArrayList<Mision> misiones = new ArrayList<>();
	private Jugador jugador;

	
	
	private Label.LabelStyle labelStyle, labelStyleCompletada, labelStylePendiente, labelStyleFallada,labelStyleOro, labelStylePlata, labelStyleCobre;
	
	public DiarioHUD(Jugador jugador) {
		this.jugador = jugador;
		misiones = new ArrayList<>(jugador.getMisiones().values());
		construir();
	}
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		labelStyleCompletada = EstiloFuente.generarFuente(30, Colores.VERDE, false);
		labelStylePendiente = EstiloFuente.generarFuente(30, Colores.AU , false);
		labelStyleFallada = EstiloFuente.generarFuente(30, Colores.ROJO , false);
		labelStyleOro = EstiloFuente.generarFuente(30, Colores.AU, false);
		labelStylePlata = EstiloFuente.generarFuente(30, Colores.AG, false);
		labelStyleCobre = EstiloFuente.generarFuente(30, Colores.CU, false);
	}

	@Override
	public void crearActores() {
		skin = new Skin(Gdx.files.internal(Recursos.hud.SKIN));
		
		contenedor = new Table();
		contenedor.setDebug(true);
		scrollPane  = new ScrollPane(contenedor);

		tabla = new Table();
		tabla.setFillParent(true);
		
		tablaTareas = new Table();
		
		cerrarBoton = new Button(skin);
		cerrarBoton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				ocultar();
			}
		});
		
		titulo = new Label(Recursos.bundle.get("diarioHUD.titulo"),labelStyle);
		

		
	}

	@Override
	public void poblarStage() {
		
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.CARTA_TEXTURA)));//cambiar por otra tetura
		
		contenedor.add(titulo).top();
		contenedor.row();
		contenedor.add(tablaTareas).expand();
		
		for (Table table : tareas) {
			tablaTareas.row();
			tablaTareas.add(table).expand();
		}

		tabla.add(scrollPane).center();
		tabla.add(cerrarBoton).top();
		
		stage.addActor(tabla);
		
	}



	@Override
	public void mostrar() {
		if(!visible) {			
		  stage.getRoot().setTouchable(Touchable.enabled);
		agregarMisiones();
		actualizarContenedor();
		visible = true;
		}
		
	}

	@Override
	public void ocultar() {
		if(visible == true) {
			visible = false;
		    stage.getRoot().setTouchable(Touchable.disabled);
			MundoConfig.estadoJuego = EstadosDelJuego.JUEGO;
			stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
		}
	}

	public void agregarMisiones() {
		tareas.clear();
		misiones = new ArrayList<>(jugador.getMisiones().values());
		for(int i=0; i<jugador.getMisiones().size();i++) {
			
		Table tabla = new Table();
//		tabla.setDebug(true);
		
		if(!misiones.isEmpty()) {
			
			
			

		Label tipoMision = new Label(misiones.get(i).getTipo().toString() + ":", labelStyle);
		Label objetoMision = null;
		Label dadorMision = new Label(misiones.get(i).getEntidad(), labelStyle);
		Label descripcion = new Label(misiones.get(i).getDescripcion(), labelStyle);
		Label diasRestantes = new Label("Dias restantes: "+misiones.get(i).getDiasRestantes(), labelStyle);
		Label recompensaOro = new Label(" "+Recursos.bundle.get("moneda.oro")+ ": "+String.valueOf(misiones.get(i).getOro()),labelStyleOro);
		Label recompensaPlata = new Label(" "+Recursos.bundle.get("moneda.plata") +": "+ String.valueOf(misiones.get(i).getPlata()),labelStylePlata);
		Label recompensaCobre = new Label(" "+Recursos.bundle.get("moneda.cobre") +": " +String.valueOf(misiones.get(i).getCobre()),labelStyleCobre);
		
		if(misiones.get(i) instanceof MisionRecFab) {
			MisionRecFab m = (MisionRecFab) misiones.get(i);
			objetoMision = new Label(m.getObjeto() + " " + m.getCantidadConseguida() +"/"+ m.getCantidadObjetivo(), labelStyle);				
		}
		
		
		tabla.add(tipoMision);
		//el estilo depende del tipo de la mision
		if(objetoMision != null) {
			tabla.add(objetoMision);			
		}
		tabla.add().expand();
		tabla.row();
		tabla.add(dadorMision);
		tabla.row();
		tabla.add(descripcion);
		if(misiones.get(i).getDiasRestantes() >= 0) {
			tabla.row();
			tabla.add(diasRestantes);			
		}
		tabla.row();
		tabla.add(recompensaOro, recompensaPlata, recompensaCobre);
		tabla.row();
		
		
		tareas.add(tabla);
		}
		}

	}
	
	private void actualizarContenedor() {
	    tablaTareas.clear();

	    for (int i = 0; i < tareas.size(); i++) {
	        Table tarea = tareas.get(i);

	        // Agregar la tarea actual
	        tablaTareas.add(tarea).expand().row();

	        // Agregar el estado de la misión
	        Label estadoMision;
	        switch (misiones.get(i).getEstado()) {
			case PENDIENTE:
				estadoMision = new Label(Recursos.bundle.get("misiones.estado.pendiente"), labelStylePendiente);
				break;
			case COMPLETADA:
				estadoMision = new Label(Recursos.bundle.get("misiones.estado.completada"), labelStyleCompletada);
				break;
			case FALLADA:
				estadoMision = new Label(Recursos.bundle.get("misiones.estado.fallada"), labelStyleFallada);
				break;
				
				default:
					estadoMision = new Label("Algo salio mal en la creacion de esta label ver DiarioHUD.java", labelStyle);
					break;
			}
	        tablaTareas.add(estadoMision).expand().row();
	        
	    }

	    // Invalidar y volver a dibujar la tabla
//	    tablaTareas.invalidateHierarchy();
//	    tablaTareas.layout();
//	    
	}

	@Override
	public void dispose() {
		Recursos.muxJuego.removeProcessor(stage);//tengo que sacar el stage del inputprocesor porque el mux es estatico, entonces cuando entro y salgo del juego, el mux agrega el nuevo stage pero sigue guardando el anterior
		stage.dispose();
		skin.dispose();
	}

}
