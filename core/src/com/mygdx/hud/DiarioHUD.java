package com.mygdx.hud;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;
import com.mygdx.historia.Mision;

public class DiarioHUD implements HeadUpDisplay, Ocultable{

	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor, tabla, tablaTareas;
	private Skin skin;
	private Button cerrarBoton;
	private Label titulo;
	private ArrayList<Table> tareas = new ArrayList<>();
	private ArrayList<Mision> misiones = new ArrayList<>();
	private Jugador jugador;
	
	private boolean visible;
	
	
	private Label.LabelStyle labelStyle, labelStyleCompletada, labelStylePendiente;
	
	public DiarioHUD(Jugador jugador) {
		this.jugador = jugador;
		misiones = jugador.getTareas();
    	screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
		crearFuentes();
		crearActores();
		poblarStage();
	}
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		labelStyleCompletada = EstiloFuente.generarFuente(30, Colores.VERDE, false);
		labelStylePendiente = EstiloFuente.generarFuente(30, Colores.AU, false);
	}

	@Override
	public void crearActores() {
		skin = new Skin(Gdx.files.internal(Recursos.SKIN));
		
		contenedor = new Table();
		contenedor.setDebug(true);

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
		
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.CARTA_TEXTURA)));//cambiar por otra tetura
		
		contenedor.add(titulo).top();
		contenedor.row();
		contenedor.add(tablaTareas).expand();
		
		for (Table table : tareas) {
			tablaTareas.row();
			tablaTareas.add(table).expand();
		}

		tabla.add(contenedor).center();
		tabla.add(cerrarBoton).top();
		
		stage.addActor(tabla);
		
	}

	@Override
	public void reEscalar(int width, int heigth) {
		screenViewport.update(width, heigth, true);
		
	}

	@Override
	public void render() {
		if(visible) {
			stage.act(Gdx.graphics.getDeltaTime());
			stage.draw();
		}

		
	}

	@Override
	public void mostrar() {
		actualizarContenedor();
		visible = true;
		
	}

	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
		
	}

	@Override
	public boolean getVisible() {
		return visible;
	}
	
	public void agregarMisiones() {
		tareas.clear();
	
		for(int i=0; i<jugador.getTareas().size();i++) {
			
		Table tabla = new Table();
//		tabla.setDebug(true);
		
		Label tipoMision = new Label(misiones.get(i).getTipoMision().toString() + ":", labelStyle);
		Label objetoMision = new Label(misiones.get(i).getObjeto() + "x" + misiones.get(i).getCantidadObjetivo(), labelStyle);
		Label dadorMision = new Label(misiones.get(i).getEntidad().getNombre(), labelStyle);
		
		tabla.add(tipoMision);
		tabla.add(objetoMision);
		tabla.add().expand();
		tabla.row();
		tabla.add(dadorMision);
		tabla.row();
		
		tareas.add(tabla);

		}

	}
	
	private void actualizarContenedor() {
	    tablaTareas.clear();

	    for (int i = 0; i < tareas.size(); i++) {
	        Table tarea = tareas.get(i);

	        // Agregar la tarea actual
	        tablaTareas.add(tarea).expand().row();

	        // Agregar el estado de la misión
	        Label estadoMision = new Label((misiones.get(i).getCompletada() ? "Completada" : "Pendiente"), (misiones.get(i).getCompletada() ? labelStyleCompletada : labelStylePendiente));
	        tablaTareas.add(estadoMision).expand().row();
	    }

	    // Invalidar y volver a dibujar la tabla
//	    tablaTareas.invalidateHierarchy();
//	    tablaTareas.layout();
//	    
	}

	
	public Stage getStage() {
		return stage;
	}

}
