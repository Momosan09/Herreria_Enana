package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.game.Principal;
import com.mygdx.pantallas.Juego;
import com.mygdx.pantallas.PantallaMenu;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.DibujarFiguras;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.recursos.Recursos;

public class PausaHUD extends HUD{

	
	private final Juego game;

	private Table tablaLienzo, tablaVideo, tablaGamePlay, tablaAudio, tablaSalir;
	private Label titulo;
	private Label videoLbl, gameplayLbl, audioLbl, salirLbl;
	private NinePatchDrawable fondo;
	
	//sub-tabla Video
	private Label lblVideoOpcion1, lblVideoOpcion2;
	
	//sub-tabla Gameplay
	private Label lblGameplayOpcion1, lblGameplayOpcion2;

	//sub-tabla Audio
	private Label lblAudioOpcion1, lblAudioOpcion2;
	
	//sub-tabla Salir
	private Label lblSalirOpcion1, lblSalirOpcion2;
	
	private int padding = 20;
	
	private Label.LabelStyle labelStyle, labelStyleVentana ,labelStyleVentanaSeleccionada;
	
	
	private PausaVentanas ventanaActiva = PausaVentanas.GAMEPLAY; // empieza en la ventana de gameplay
	
	public PausaHUD(final Juego game) {
		super();
		this.game = game;
		construir();
	}
	

	@Override
	public void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
		labelStyleVentana = EstiloFuente.generarFuente(20, Colores.BLANCO, false);
		labelStyleVentanaSeleccionada = EstiloFuente.generarFuente(20, Colores.CU, false);
	}

	@Override
	public void crearActores() {
		tabla = new Table();
		tabla.setFillParent(true);
		
		contenedor = new Table();
		
		tablaLienzo = new Table();
		
		//sub-tablas
		tablaVideo = new Table();
		tablaGamePlay = new Table();
		tablaAudio = new Table();
		tablaSalir = new Table();
		
		titulo = new Label(Recursos.bundle.get("pausa.titulo"),labelStyle);
		videoLbl = new Label(Recursos.bundle.get("pausa.video"), labelStyleVentana);
		gameplayLbl = new Label(Recursos.bundle.get("pausa.gameplay"), labelStyleVentana);
		audioLbl = new Label(Recursos.bundle.get("pausa.audio"), labelStyleVentana);
		salirLbl = new Label(Recursos.bundle.get("pausa.salir"), labelStyleVentana);
		
		//sub-tablas labels
		lblVideoOpcion1 = new Label(Recursos.bundle.get("pausa.ventana.video.opc1"), labelStyle);
		lblVideoOpcion2 = new Label(Recursos.bundle.get("pausa.ventana.video.opc2"), labelStyle);
		
		lblGameplayOpcion1 = new Label(Recursos.bundle.get("pausa.ventana.gameplay.opc1"), labelStyle);
		lblGameplayOpcion2 = new Label(Recursos.bundle.get("pausa.ventana.gameplay.opc2"), labelStyle);
		
		lblAudioOpcion1 = new Label(Recursos.bundle.get("pausa.ventana.audio.opc1"), labelStyle);
		lblAudioOpcion2 = new Label(Recursos.bundle.get("pausa.ventana.audio.opc2"), labelStyle);
		
		lblSalirOpcion1 = new Label(Recursos.bundle.get("pausa.ventana.salir.opc1"), labelStyle);
		lblSalirOpcion2 = new Label(Recursos.bundle.get("pausa.ventana.salir.opc2"), labelStyle);
		
		videoLbl.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("video click");
				ventanaActiva = PausaVentanas.VIDEO;
				cambiarVentana();
				}
		});
		
		gameplayLbl.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("gameplay click");
				ventanaActiva = PausaVentanas.GAMEPLAY;
				cambiarVentana();
				}
		});
		
		audioLbl.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("audio click");
				ventanaActiva = PausaVentanas.AUDIO;
				cambiarVentana();
				}
		});
		
		salirLbl.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("salir click");
				ventanaActiva = PausaVentanas.SALIR;
				cambiarVentana();
				}
		});
		
		lblSalirOpcion1.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(HelpDebug.debub(getClass())+"Finalizar_conexion");
				cambiarColorLabels(lblSalirOpcion1, lblSalirOpcion2);
//				game.setScreen(new PantallaMenu(game));
				
				}
		});
		
		lblSalirOpcion2.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(HelpDebug.debub(getClass())+"Menu principal");
				cambiarColorLabels(lblSalirOpcion2, lblSalirOpcion1); 
				game.salirDelJuego();
				}
		});
		
		fondo = new NinePatchDrawable(new NinePatch(new Texture(Recursos.hud.PAUSA_HUD)));
	}
	


	@Override
	public void poblarStage() {
		

		
		contenedor.add(titulo).colspan(4).padTop(padding);
		contenedor.row();
		contenedor.add(videoLbl).padLeft(padding);
		contenedor.add(gameplayLbl);
		contenedor.add(audioLbl);
		contenedor.add(salirLbl).padRight(padding);
		contenedor.row();
		contenedor.add(tablaLienzo).pad(padding);
		
		tablaLienzo.add(tablaGamePlay);
		
		//sub-tablas
		tablaVideo.add(lblVideoOpcion1);
		tablaVideo.row();
		tablaVideo.add(lblVideoOpcion2);
		
		tablaGamePlay.add(lblGameplayOpcion1);
		tablaGamePlay.row();
		tablaGamePlay.add(lblGameplayOpcion2);
		
		tablaAudio.add(lblAudioOpcion1);
		tablaAudio.row();
		tablaAudio.add(lblAudioOpcion2);
		
		tablaSalir.add(lblSalirOpcion1);
		tablaSalir.row();
		tablaSalir.add(lblSalirOpcion2);
		
		contenedor.setBackground(fondo);
		tabla.add(contenedor);
		stage.addActor(tabla);
	}
	
	public void cambiarVentana() {
		tablaLienzo.clear();
		switch (ventanaActiva) {
		case VIDEO:
			
			cambiarColorLabels(videoLbl,gameplayLbl,audioLbl,salirLbl);
			tablaLienzo.add(tablaVideo);
			break;

		case GAMEPLAY:
			cambiarColorLabels(gameplayLbl,videoLbl,audioLbl,salirLbl);
			tablaLienzo.add(tablaGamePlay);
			break;
		
		case AUDIO:
			cambiarColorLabels(audioLbl,videoLbl,gameplayLbl,salirLbl);
			tablaLienzo.add(tablaAudio);
			break;
		
		case SALIR:
			cambiarColorLabels(salirLbl,videoLbl,gameplayLbl,audioLbl);
			tablaLienzo.add(tablaSalir);
			break;
		}
	}


	@Override
	public void dibujar() {
		if(visible) {
			lblSalirOpcion1.setVisible(false);
			lblSalirOpcion2.setVisible(true);
			stage.draw();
    	stage.act(Gdx.graphics.getDeltaTime());

	}

	}

	public void cambiarColorLabels(Label seleccionada, Label ...labels) {//varargs para pasar una cantidad no definida de labels (igual siempre se la cantidad pero bueh...)
		seleccionada.setColor(Color.valueOf(Colores.AU));
		for (Label label : labels) {
			label.setColor(Color.valueOf(Colores.BLANCO));
		}
	}


	public void dispose() {
		Recursos.muxJuego.removeProcessor(stage);
		stage.dispose();
		this.dispose();

	}
	
}
