package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.enums.Respuestas;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;

public class Dialogo extends Actor implements HeadUpDisplay, Ocultable {

	private Npc locutor;
	private Jugador jugador;
	
	private ScreenViewport screenViewport;
	private Stage stage;
	private Table tabla, contenedor;
	private Label nombre, mensaje, respuestas[];
	private Image retrato;
//	private TypingLabel label;
	private NinePatchDrawable fondo;
//	private TypingLabel typingLabel;
	private Label.LabelStyle labelStyle;
	private int mensajeAMostrar, padding = 20;
	private boolean tieneRespuesta = false;
	private boolean mostrar = false;
	private String nombreCharlaActual = "";

	public Dialogo(Jugador jugador) {
		respuestas = new Label[2];
		poblarStage();
		Recursos.muxJuego.addProcessor(stage);
		this.jugador = jugador;
		
	}
	
	public void setLocutor(Npc locutor) {
		this.locutor = locutor;
		update();	
	}
	
	public Npc getLocutor() {
		return locutor;
	}
	
	@Override
	public void render() {
		if(mostrar) {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		}
	}
	
	public void mostrar() {
		mostrar = true;
	}
	
	public void ocultar() {
		mostrar = false;
	}
	
	public void dispose() {
		stage.dispose();
	}
	
	public void update() {
//		System.out.println(HelpDebug.debub(getClass())+locutor.getNombreCharlaActual());
		actualizarDatosCajaDialogo();
		


	}
	
	private void actualizarDatosCajaDialogo() {
		if(!nombreCharlaActual.equals(locutor.getNombreCharlaActual())) {
			nombreCharlaActual = locutor.getNombreCharlaActual();
			nombre.setText(locutor.getNombre());
			mensaje.setText(locutor.getCharlaActual().getMensaje());
			mensaje.setWrap(true);
			respuestas[0].setText(locutor.getCharlaActual().getRespuesta1());//texto de las respuestas NO ES EL VALOR DE LAS RESPUESTAS
			respuestas[1].setText(locutor.getCharlaActual().getRespuesta2());//texto de las respuestas NO ES EL VALOR DE LAS RESPUESTAS
			System.out.println(HelpDebug.debub(getClass())+"el locutor es " + locutor.getNombre());
			jugador.resetearRespuestas();
		}
		
	}
	
	public void limpiarDatos() {//Esto ayuda a que no queden datos del npc anterior en la caja de dialogo cuando se hable con uno nuevo
		boolean unaVez = false;
		if(!unaVez) {
			
			nombreCharlaActual = "error";
			nombre.setText("Hubo un error");
			mensaje.setText("No deberias estar viendo esto");
			mensaje.setWrap(true);
			respuestas[0].setText(".");//texto de las respuestas NO ES EL VALOR DE LAS RESPUESTAS
			respuestas[1].setText("-");//texto de las respuestas NO ES EL VALOR DE LAS RESPUESTAS
			jugador.resetearRespuestas();
			unaVez=true;
		}
		
	}
	
	@Override
	public void crearActores() {
		nombre = new Label("", labelStyle);
		mensaje = new Label("mensaje", labelStyle);

		respuestas[0] = new Label("respuesta1", labelStyle);
		respuestas[1] = new Label("respuesta2", labelStyle);
		agregarEventos();
		
		
		retrato = new Image(new Texture(Recursos.VENDEDOR_AMBULANTE_PORTRAIT));
		
		fondo = new NinePatchDrawable(new NinePatch(new Texture(Recursos.DIALOGO_HUD)));
	}
	
	@Override
	public void poblarStage() {
		crearFuentes();
		crearActores();
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		tabla = new Table();
		
		tabla.setFillParent(true);
		
		contenedor = new Table();
		contenedor.setFillParent(false);
		contenedor.setBackground(fondo);
		
		contenedor.add(nombre).left().expandX().padLeft(padding*2.25f);
		contenedor.row();
		contenedor.add(mensaje).left().expand().fill();
		contenedor.add(retrato).size(retrato.getWidth()*2,retrato.getHeight()*2);

		contenedor.row();
		contenedor.add(respuestas[0]);
		contenedor.row();
		contenedor.add(respuestas[1]);
		contenedor.padLeft(padding);
		contenedor.padRight(padding);
		contenedor.padBottom(padding);
		
		tabla.add(contenedor).bottom().expand();
		tabla.padBottom(padding);
		stage.addActor(tabla);
		
	}	
	
	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(22, Colores.BLANCO, false);
	}
	
	public void selectMensaje(int index) {
		mensajeAMostrar = index;
	}

	@Override
	public void reEscalar(int width, int heigth) {
		screenViewport.update(width, heigth,true);
		
	}
	
	public void agregarEventos() {
		respuestas[0].addListener(new InputListener() { //No uso el EventListener porque me complica para hacer lo de cambiar de color las labels
		    @Override
		    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		        // Este método se llama cuando el cursor entra en el área de la Label
		    	respuestas[0].setColor(Color.valueOf(Colores.SELECCIONADO));
		    }

		    @Override
		    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		        // Este método se llama cuando el cursor sale del área de la Label
		    	respuestas[0].setColor(Color.WHITE);
		    }

		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		    		
		    		jugador.respuesta1 = Respuestas.VERDADERO;
		    		jugador.respuesta2 = Respuestas.FALSO;
		    		respuestas[0].setColor(Color.CYAN);
		    	
		        return true;  // Devuelve true para indicar que el evento ha sido manejado
		    }
		});
		
		respuestas[1].addListener(new InputListener() {
		    @Override
		    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		        // Este método se llama cuando el cursor entra en el área de la Label
		    	respuestas[1].setColor(Color.valueOf(Colores.SELECCIONADO));
		    }

		    @Override
		    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		        // Este método se llama cuando el cursor sale del área de la Label
		    	respuestas[1].setColor(Color.WHITE);
		    }

		    @Override
		    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			
	    			jugador.respuesta1 = Respuestas.FALSO;
	    			jugador.respuesta2 = Respuestas.VERDADERO;
		    		respuestas[1].setColor(Color.CYAN);
		    	
		        return true;  // Devuelve true para indicar que el evento ha sido manejado
		    }
		});

	}
	
	public Stage getStage() {
	 return stage;
 }

	@Override
	public boolean getVisible() {
		return mostrar;
	}

}
