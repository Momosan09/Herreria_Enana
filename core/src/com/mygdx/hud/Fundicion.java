package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Minable.Hierro;
import com.mygdx.entidades.ObjetosDelMapa.procesados.LingoteHierro;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;

public class Fundicion implements Ocultable, HeadUpDisplay{

	private ScreenViewport screenViewport;
	private Stage stage;
	private Table contenedor, tabla;
	private Label titulo;
	private Button botonArriba, botonAbajo;
	private TextButton fundirBoton;
	private Image hierro, imgElegido, imgResultado;
	private Label.LabelStyle labelStyle;
	private Skin skinArriba, skinAbajo, skinTextButton;
	private Jugador jugador;
	public int cantidad = 0;
	private int hierroEnElInventario=0;
	public boolean elegido = false, fabricar = false;
	 public boolean visible=false;
	
	 
	 public Fundicion(Jugador jugador) {
		 	this.jugador= jugador;
	    	screenViewport = new ScreenViewport();
	        stage = new Stage(screenViewport);
	        
	        crearFuentes();
	        crearActores();
	        poblarStage();
	 }
	


	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
			
	}
	
	@Override
	public void crearActores() {
		skinArriba = new Skin(Gdx.files.internal(Recursos.SKIN_BOTON_ARRIBA));
		skinAbajo = new Skin(Gdx.files.internal(Recursos.SKIN_BOTON_ABAJO));
		skinTextButton = new Skin(Gdx.files.internal(Recursos.SKIN));
		
		
		tabla = new Table();
		tabla.setFillParent(true);
		tabla.setDebug(true);
		
		contenedor = new Table();
		contenedor.setDebug(true);
		
		titulo = new Label("Horno de funducion", labelStyle);
		
		botonArriba = new Button(skinArriba);
		botonAbajo = new Button(skinAbajo);
		fundirBoton = new TextButton("Fundir x" + cantidad, skinTextButton);
		
		hierro = new Image(new Texture(Recursos.HIERRO));
		imgElegido = new Image(new Texture(Recursos.HIERRO));
		imgElegido.scaleBy(3);
		
		imgResultado = new Image(new Texture(Recursos.LINGOTE_HIERRO));
		
		
		botonArriba.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if(cantidad < hierroEnElInventario) {//ahora es solo con hierro pero tengo que hacer que se pueda con todos los minerales
					System.out.println(HelpDebug.debub(getClass())+"sumar");	
					cantidad++;
					fundirBoton.setText("Fundir x" + cantidad);
					System.out.println(cantidad);
				}else if(cantidad > hierroEnElInventario){
					botonArriba.setDisabled(true);
				}
			}
		});
		
		
		botonAbajo.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(cantidad >= 1) {
					System.out.println(HelpDebug.debub(getClass())+"restar");
					cantidad--;
					fundirBoton.setText("Fundir x" + cantidad);
					System.out.println(cantidad);
				}else if(cantidad <= 0){
					botonAbajo.setDisabled(true);
				}
			}
		});
		

		
		fundirBoton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {

				fabricar = true;
				System.out.println(cantidad);
				jugador.eliminarPorNombreDadoYCantidad("hierro", cantidad);
			}
		});
		

		
		hierro.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				elegido = true;
				}
		});
		
		imgResultado.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				for(int i = 0; i < cantidad;i++) {
					jugador.getMinerales().add(new LingoteHierro(0,0,false,Recursos.LINGOTE_HIERRO));
				}
//				System.out.println("a9------gregado");
				cantidad = 0;
				fabricar = false;
				fundirBoton.setText("Fundir x" + cantidad);
				}
		});
		
		
	}

	@Override
	public void poblarStage() {
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.HORNO_TEXTURA)));

		contenedor.add();
		contenedor.add(titulo);
		contenedor.row();
		contenedor.add(hierro).left();
		contenedor.add(imgElegido).expand();
		contenedor.add();
		contenedor.add(botonArriba);
		contenedor.row();
		contenedor.add();
		contenedor.add(imgResultado).expand();
		contenedor.add();
		contenedor.add(botonAbajo);
		
		tabla.add(contenedor);
		tabla.row();
		tabla.add(fundirBoton);
		stage.addActor(tabla);
		
		
	}

	@Override
	public void reEscalar(int width, int heigth) {
    	screenViewport.update(width, heigth, true);
		
	}
	
	@Override
	public void render() {

		if(visible) {
			//DibujarFiguras.dibujarRectanguloLleno(contenedor.getX(), contenedor.getY(), contenedor.getWidth(), contenedor.getHeight(), new Color(0,0,0,.7f));
			if(elegido) {
				imgElegido.setVisible(true);
				if(fabricar) {
					imgResultado.setVisible(true);
				}else {
					imgResultado.setVisible(false);
				}
			}else {
				imgElegido.setVisible(false);
				imgResultado.setVisible(false);
			}
			
			
	    	stage.act(Gdx.graphics.getDeltaTime());
	    	stage.draw();
		}
	}

	
	public void tieneHierro(Jugador jugador) {
		hierroEnElInventario=jugador.buscarCantidadDeMineralesPorNombre("hierro");
//		System.out.println("hierro en el inventario " + hierroEnElInventario);
		if(hierroEnElInventario >0) {
			fundirBoton.setDisabled(false);
		}else {
			fundirBoton.setDisabled(true);
		}
	}
	
	@Override
	public void mostrar() {
		visible = true;
		
	}

	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
	}
	
	public Stage getStage() {
		return stage;
	}






	
}
