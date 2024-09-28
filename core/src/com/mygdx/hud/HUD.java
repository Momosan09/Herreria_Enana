package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.audio.AudioManager;
import com.mygdx.entidades.Jugador;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.enums.Items;
import com.mygdx.eventos.EventoMisionAgregada;
import com.mygdx.eventos.Listeners;
import com.mygdx.historia.Mision;
import com.mygdx.pantallas.Juego;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Tiempo;


/*
 	https://libgdxinfo.wordpress.com/basic_image/
 	https://github.com/raeleus/viewports-sample-project
 */
public class HUD implements HeadUpDisplay, Ocultable, EventoMisionAgregada{

	private Texture dinero_Tex;
	private Texture reloj_Tex;
	private Texture tiempo_Tex;
	private Image tiempo_Img;
	private Image reloj;
	private ScreenViewport screenViewport;//Stage viene con un viewport pero no sirve, por eso usamos a este buen hombre
	private Stage stage;
	private Table hud;
	private Table dineroTable;
	private Table ultimasBatallasTable;
	private Table siguienteBatalla;
	private Table pedidosTable;
	private Table hudIzq, hudCen, hudDer;
	private Table barraItems;
	private Stack pila;
	
	private Sprite dineroImgSpr;
	private Label dineroLbl;
	private Label[][] monedas;
	private Label[] ultimaBatalla;
	private Label verBatallasAnteriores;
//	private TextButton verBatallasAnterioresBtn;
//	private TextButton pedidoBtn;
	private Label siguienteBatallaLbl;
	private Label nombreSiguienteBatalla;
	private Label siguienteBatallaDetalles;
	private Label centroLbl;
	private Label diaLbl;
	private Label horaLbl;
	private Label diarioLbl;
	private Label mensaje;
//	private Label barraAbajoLbl;
	private Label.LabelStyle labelStyle;
	private Label.LabelStyle labelVerde;
	private Label.LabelStyle labelMonedasStyle[];
	
	private ResultadosBatallasHUD resultadosHUD;
	private ProximaBatallaHUD proximaBatallaHUD;
	private DiarioHUD diarioHUD;
	
	private String dia = Recursos.bundle.get("dia.3");
	

	private boolean visible = MundoConfig.mostrarHUD;
	
	private Jugador jugador;
	private Juego juego;
	
    public HUD(Jugador jugador, Juego juego) {
    	this.jugador = jugador;
    	this.juego = juego;
    	resultadosHUD = new ResultadosBatallasHUD();
    	proximaBatallaHUD = new ProximaBatallaHUD();
    	diarioHUD = new DiarioHUD(jugador);
    	cargarTexturas();
    	crearFuentes();
    	crearActores();
    	poblarStage();
    	Listeners.agregarListener(this);
    }
	
	@Override
	public void poblarStage() {

		screenViewport = new ScreenViewport();//Stage viene con un viewport pero no sirve, por eso usamos a este buen hombre
		stage = new Stage(screenViewport);//le pasamos a este buen hombre
		//Gdx.input.setInputProcessor(stage);

		hud = new Table();
		hud.setFillParent(true);
		//hud.debug();
		//Izquierda
		hudIzq = new Table();

		
			//Dinero
		dineroTable = new Table();
		dineroTable.setFillParent(false);
//		dineroTable.setDebug(true);
		dineroTable.setBackground(new SpriteDrawable(dineroImgSpr));//para poner sprite de fondo
		dineroTable.add(dineroLbl);
		dineroTable.row();
		dineroTable.add(monedas[0][0], monedas[0][1], monedas[1][0], monedas[1][1], monedas[2][0], monedas[2][1]);
		
		hudIzq.add(dineroTable).left();
		
		hudIzq.row().spaceTop(20);
			//Ultimas batallas
		/*ultimasBatallasTable = new Table();
		ultimasBatallasTable.add(ultimaBatalla[0], ultimaBatalla[1]);
		ultimasBatallasTable.row();
		ultimasBatallasTable.add(verBatallasAnteriores);
		//ultimasBatallasTable.setBackground(new TextureRegionDrawable(new Texture(Recursos.CUADRO_HUD)));
		hudIzq.add(ultimasBatallasTable);*/
		
			//Siguiente batalla
		
		hudIzq.row().spaceTop(20);
		/*siguienteBatalla = new Table();
		
		siguienteBatalla.add(siguienteBatallaLbl);
		siguienteBatalla.add(nombreSiguienteBatalla);
		siguienteBatalla.row();
		siguienteBatalla.add(siguienteBatallaDetalles).left();
		//siguienteBatalla.setBackground(new TextureRegionDrawable(new Texture(Recursos.CUADRO_HUD)));
		
		hudIzq.add(siguienteBatalla).left();*/
		
		//Centro
		
		hudCen = new Table();

//		hudCen.add(centroLbl).expand();
//		hudCen.setDebug(true);
//		hud.setDebug(true);
//	    hudDer.setDebug(true);
		
		//Derecha
		hudDer = new Table();
		pedidosTable = new Table();
		
		
		//pedidos
		pedidosTable.add(diarioLbl);
		//pedidosTable.add(pedidoBtn);
		
		
		pila = new Stack();
		
	    // Ajustar tamaño de las imágenes para que conserven su tamaño original
	    tiempo_Img.setSize(tiempo_Img.getWidth(), tiempo_Img.getHeight());
	    reloj.setSize(reloj.getWidth(), reloj.getHeight());

	    // Añadir las imágenes a la pila sin forzar su tamaño
	    pila.add(tiempo_Img);
	    pila.add(reloj);

	    // Añadir la pila a hudDer, asegurando que no se estiren ni aplasten
	    hudDer.add(pila).size(reloj.getWidth(), reloj.getHeight()); // Usa el tamaño de la imagen original


		hudDer.row();
		hudDer.add(diaLbl);
		hudDer.row();
		hudDer.add(horaLbl);
		hudDer.row();
		hudDer.add(pedidosTable);
		hudDer.row();
		hudDer.add(mensaje).fillX().expandY().top();

		//barra items
		Image imagen = new Image(Items.PICO.getTextura());
		barraItems.add(imagen);
		
	
		//Gral
		hud.add(hudIzq).top();
		hud.add(hudCen).expand();
		hud.add(hudDer).fillY();
		hud.row();

		hud.add(barraItems).colspan(3);

		hud.pad(15);

        stage.addActor(hud);


	}
	
	@Override
	public void reEscalar(int width, int height) {
		screenViewport.update(width, height, true);//actualizamos cuando la ventana se reescala
		resultadosHUD.reEscalar(width, height);
		proximaBatallaHUD.reEscalar(width, height);
		diarioHUD.reEscalar(width, height);
	}
	public void dispose() {
		proximaBatallaHUD.dispose();
		diarioHUD.dispose();
		resultadosHUD.dispose();
		Recursos.muxJuego.removeProcessor(stage);//tengo que sacar el stage del inputprocesor porque el mux es estatico, entonces cuando entro y salgo del juego, el mux agrega el nuevo stage pero sigue guardando el anterior
		stage.dispose();
	}
	
	private void cargarTexturas() {
		dinero_Tex = new Texture(Recursos.DINERO_HUD);
		dineroImgSpr = new Sprite(dinero_Tex);
		
		reloj_Tex = new Texture(Recursos.MARCO_RELOJ);
		tiempo_Tex = new Texture(Recursos.DIBUJO_RELOJ);
		tiempo_Img = new Image(tiempo_Tex);
	    tiempo_Img.setOrigin(tiempo_Img.getWidth() / 2f, tiempo_Img.getHeight() / 2f);
		reloj = new Image(reloj_Tex);
	}

	@Override
	public void crearActores() {
		
		//IZQUIERDA
		dineroLbl = new Label(Recursos.bundle.get("hud.dinero"), labelStyle);
		monedas = new Label[3][2];
		monedas[0][0] = new Label("Au: ", labelStyle);
		monedas[0][1] = new Label("0", labelMonedasStyle[0]);
		monedas[1][0] = new Label("Ag: ", labelStyle);
		monedas[1][1] = new Label("0", labelMonedasStyle[1]);
		monedas[2][0] = new Label("Cu: ", labelStyle);
		monedas[2][1] = new Label("0", labelMonedasStyle[2]);
		
		ultimaBatalla = new Label[2];
		ultimaBatalla[0] = new Label(Recursos.bundle.get("hud.ultimaBatalla"), labelStyle);
		ultimaBatalla[1] = new Label("Aca mostrar resultado", labelStyle);
		

		
		
		verBatallasAnteriores = new Label(Recursos.bundle.get("hud.verAnteriores"), labelStyle);

		
		//verBatallasAnterioresClick = new TextButton("Click", skin);
		siguienteBatallaLbl = new Label(Recursos.bundle.get("hud.siguienteBatalla"), labelStyle);
		nombreSiguienteBatalla = new Label("Nombre-de-Batalla", labelStyle);
		siguienteBatallaDetalles = new Label(Recursos.bundle.get("hud.verDetalles"), labelStyle);

		
		//CENTRO
		centroLbl = new Label("Centro", labelStyle);


		//DERECHA
		diaLbl = new Label(dia, labelStyle);
		diarioLbl = new Label(Recursos.bundle.get("hud.verPedidos"), labelStyle);
		horaLbl = new Label(MundoConfig.horaDelMundo + ":" + MundoConfig.minutoDelMundo, labelStyle);
		//pedidoBtn = new TextButton("",skin);
		mensaje = new Label("Mensaje Mostrando: ...", labelVerde);
		mensaje.setWrap(true);
		mensaje.setVisible(false);
		
		//BARRA ITEMS
		barraItems = new Table();
		barraItems.setDebug(true);
		//barraAbajoLbl = new Label("Barra De items", labelStyle);
		
		
		
		//EVENTOS
		verBatallasAnteriores.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!proximaBatallaHUD.getVisible() && !diarioHUD.getVisible()) {
					resultadosHUD.mostrar(); // Abre resultadosHUD		
					Recursos.muxJuego.removeProcessor(proximaBatallaHUD.getStage());//Arregla el bug ese que no deja usar el otro boton
					Recursos.muxJuego.removeProcessor(resultadosHUD.getStage());
					Recursos.muxJuego.addProcessor(resultadosHUD.getStage());
					
					
				}
				//resultadosHUD.cerrar = !mostrarResultadosBatalla;		
				//System.out.println(HelpDebug.debub(getClass())+"click");
			}
		});
		
		siguienteBatallaDetalles.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!resultadosHUD.getVisible() && !diarioHUD.getVisible()) {
					proximaBatallaHUD.mostrar();
					Recursos.muxJuego.removeProcessor(resultadosHUD.getStage());//Arregla el bug ese que no deja usar el otro boton
					Recursos.muxJuego.removeProcessor(proximaBatallaHUD.getStage());
					Recursos.muxJuego.addProcessor(proximaBatallaHUD.getStage());
					
				}
			}
		});
		
		diarioLbl.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!proximaBatallaHUD.getVisible() && !resultadosHUD.getVisible()) {
					diarioHUD.agregarMisiones();
					MundoConfig.estadoJuego = EstadosDelJuego.DIARIO;
				}
			}
		});
		

		
	}
	
	
	
	@Override
	public void crearFuentes() {
    	labelStyle = EstiloFuente.generarFuente(26, Colores.BLANCO, false);
    	labelVerde = EstiloFuente.generarFuente(26, Colores.VERDE, false);
    	labelMonedasStyle = new Label.LabelStyle[3];
    	labelMonedasStyle[0] = EstiloFuente.generarFuente(16, Colores.AU, false); 
    	labelMonedasStyle[1] = EstiloFuente.generarFuente(16, Colores.AG, false); 
    	labelMonedasStyle[2] = EstiloFuente.generarFuente(16, Colores.CU, false); 
	}

	@Override
	public void render() {
		if(visible) {
		//screenViewport.apply();//no estoy muy seguro de que hace esto
		rotarReloj();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		monedas[0][1].setText(jugador.dinero[0]);
		monedas[1][1].setText(jugador.dinero[1]);
		monedas[2][1].setText(jugador.dinero[2]);
		resultadosHUD.render();
		proximaBatallaHUD.render();//Nose porque no funciona el click de proximaBatallaHUD cuando lo quiero usar despues de haber abierto resultadosHUD
		diarioHUD.render();
		horaLabel();
		}
		diaLbl.setText(MundoConfig.dia);
		
		
	}

	public Stage getStage() {
		return stage;
	}

	public ResultadosBatallasHUD getResultadosBatallasHUD() {
		return resultadosHUD;
	}
	
	public ProximaBatallaHUD getProximaBatallaHUD() {
		return proximaBatallaHUD;
	}
	
	public DiarioHUD getDiarioHUD() {
		return diarioHUD;
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
	

	
	private void rotarReloj() {

	    float minutoDelDia = MundoConfig.horaDelMundo*60 + MundoConfig.minutoDelMundo;
	    
	    if(minutoDelDia == 1440) minutoDelDia = 0;
	    
	    
	    if(minutoDelDia >= 0 && minutoDelDia < 240) {//De 0000Hs a 0400Hs (de 12AM a 4AM)
	        // No restamos nada porque ya es desde el inicio del día.
	        float angulo = minutoDelDia * 11.25f/60;
	        tiempo_Img.setRotation(angulo + 315);
	    }
	    
	    if(minutoDelDia >= 240 && minutoDelDia < 480) {//De 0400Hs a 0800Hs
	    	minutoDelDia -= 240;//El juego empieza a las 0600Hs => 360mins
	    	float angulo = minutoDelDia * (22.5f/60);

	    	tiempo_Img.setRotation(angulo);
	    }
	    
	    if(minutoDelDia >= 480 && minutoDelDia < 960) {//De 0800Hs a 1600Hs
	    	minutoDelDia -= 480;
	    	float angulo = minutoDelDia * 11.25f/60;
	 
	    	tiempo_Img.setRotation(angulo+90);
	    }
	    
	    if(minutoDelDia >= 960 && minutoDelDia < 1200) {//De 1600Hs a 2000Hs 
	    	minutoDelDia -= 960;
	    	float angulo = minutoDelDia * 22.5f/60;
	    	tiempo_Img.setRotation(angulo+180);
	    }
	    
	    if(minutoDelDia >= 1200 && minutoDelDia < 1440) {//De 2000Hs a 0400Hs
	    	minutoDelDia -= 1200;
	    	float angulo = minutoDelDia * 11.25f/60;
	    	tiempo_Img.setRotation(angulo+270);
	    }

	}

	public void horaLabel() {
		horaLbl.setText((MundoConfig.horaDelMundo <= 9 ? "0" + MundoConfig.horaDelMundo:MundoConfig.horaDelMundo ) + ":" + (MundoConfig.minutoDelMundo <= 9 ? "0" + MundoConfig.minutoDelMundo:MundoConfig.minutoDelMundo ));
	}
	    
	

	@Override
	public boolean getVisible() {
		return visible;
	}

	@Override
	public void misionAgregada(Mision mision) {
	    // Muestra el label 'mensaje'
	    mensaje.setVisible(true);

	    mensaje.setText("Mision agregada: " + mision.getTipoMision() + " x" + mision.getCantidadObjetivo()+ " " + mision.getObjeto());
	    Tiempo.actorEsperar(mensaje, 4);

	}


}
