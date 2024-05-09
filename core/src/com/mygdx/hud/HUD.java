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
import com.mygdx.entidades.Jugador;
import com.mygdx.enums.Items;
import com.mygdx.pantallas.Juego;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.Recursos;


/*
 	https://libgdxinfo.wordpress.com/basic_image/
 	https://github.com/raeleus/viewports-sample-project
 */
public class HUD implements HeadUpDisplay, Ocultable{

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
	private Label diarioLbl;
//	private Label barraAbajoLbl;
	private Label.LabelStyle labelStyle;
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
		
		//Derecha
		hudDer = new Table();
		pedidosTable = new Table();
		
		pila = new Stack();
		
		//pedidos
		pedidosTable.add(diarioLbl);
		//pedidosTable.add(pedidoBtn);
		
		pila.add(reloj);
		pila.add(tiempo_Img);
		hudDer.add(pila);
		hudDer.row();
		hudDer.add(diaLbl);
		hudDer.row();
		hudDer.add(pedidosTable).bottom();

		//barra items
		Image imagen = new Image(Items.PICO.getTextura());
		barraItems.add(imagen);
		
	
		//Gral
		hud.add(hudIzq).top();
		hud.add(hudCen).expand();
		hud.add(hudDer).size(200, 200).top();
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
		//pedidoBtn = new TextButton("",skin);
		
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
					diarioHUD.mostrar();
				}
			}
		});
		
		agregarAnimaciones();
		
	}
	
	
	
	@Override
	public void crearFuentes() {
    	labelStyle = EstiloFuente.generarFuente(26, Colores.BLANCO, false);
    	labelMonedasStyle = new Label.LabelStyle[3];
    	labelMonedasStyle[0] = EstiloFuente.generarFuente(16, Colores.AU, false); 
    	labelMonedasStyle[1] = EstiloFuente.generarFuente(16, Colores.AG, false); 
    	labelMonedasStyle[2] = EstiloFuente.generarFuente(16, Colores.CU, false); 
	}

	@Override
	public void render() {
		if(visible) {
		//screenViewport.apply();//no estoy muy seguro de que hace esto
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		monedas[0][1].setText(jugador.dinero[0]);
		monedas[1][1].setText(jugador.dinero[1]);
		monedas[2][1].setText(jugador.dinero[2]);
		resultadosHUD.render();
		proximaBatallaHUD.render();//Nose porque no funciona el click de proximaBatallaHUD cuando lo quiero usar despues de haber abierto resultadosHUD
		diarioHUD.render();
		}
		determinarDia(juego.getDia());
		diaLbl.setText(dia);
		
		
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
	
	private void determinarDia(int dia) {
		switch (dia) {
		case 1:
			this.dia = Recursos.bundle.get("dia.1");
			break;
		case 2:
			this.dia = Recursos.bundle.get("dia.2");
			break;
		case 3:
			this.dia = Recursos.bundle.get("dia.3");
			break;
		case 4:
			this.dia = Recursos.bundle.get("dia.4");
			break;
		case 5:
			this.dia = Recursos.bundle.get("dia.5");
			break;
		case 6:
			this.dia = Recursos.bundle.get("dia.6");
			break;
		case 7:
			this.dia = Recursos.bundle.get("dia.7");
			break;
		}
	}
	
	private void agregarAnimaciones() {
	    tiempo_Img.setOrigin(tiempo_Img.getWidth() / 2f, tiempo_Img.getHeight() / 2f);

	    tiempo_Img.addAction(Actions.forever(Actions.rotateBy(15, 1, Interpolation.linear)));//hacer esto bien
	    	
	    
	}

	@Override
	public boolean getVisible() {
		return visible;
	}

}
