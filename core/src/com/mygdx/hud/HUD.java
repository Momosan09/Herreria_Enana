package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;


/*
 	https://libgdxinfo.wordpress.com/basic_image/
 	https://github.com/raeleus/viewports-sample-project
 */
public class HUD implements HeadUpDisplay, Ocultable{

	private Texture dinero_Tex;
	private Texture reloj_Tex;
	private Image reloj;
	private ScreenViewport screenViewport;//Stage viene con un viewport pero no sirve, por eso usamos a este buen hombre
	private Stage stage;
	private Table hud;
	private Table dineroTable;
	private Table ultimasBatallasTable;
	private Table siguienteBatalla;
	private Table pedidosTable;
	private Table hudIzq, hudCen, hudDer;
	
	private Sprite dineroImgSpr;
	private Label dineroLbl;
	private Label[][] monedas;
	private Label[] ultimaBatalla;
	private Label verBatallasAnteriores;
	private TextButton verBatallasAnterioresBtn;
	private TextButton pedidoBtn;
	private Label siguienteBatallaLbl;
	private Label nombreSiguienteBatalla;
	private Label siguienteBatallaDetalles;
	private Label centroLbl;
	private Label diaLbl;
	private Label pedidoLbl;
	private Label barraAbajoLbl;
	private Label.LabelStyle labelStyle;
	private Label.LabelStyle labelMonedasStyle[];
	
	private ResultadosBatallasHUD resultadosHUD;
	private ProximaBatallaHUD proximaBatallaHUD;

	public boolean visible = true;
	
    public HUD() {
    	resultadosHUD = new ResultadosBatallasHUD();
    	proximaBatallaHUD = new ProximaBatallaHUD();
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
		dineroTable.setDebug(true);
		dineroTable.setBackground(new SpriteDrawable(dineroImgSpr));//para poner sprite de fondo
		dineroTable.add(dineroLbl);
		dineroTable.row();
		dineroTable.add(monedas[0][0], monedas[0][1], monedas[1][0], monedas[1][1], monedas[2][0], monedas[2][1]);
		
		hudIzq.add(dineroTable).left();
		
		hudIzq.row().spaceTop(20);
			//Ultimas batallas
		ultimasBatallasTable = new Table();
		ultimasBatallasTable.add(ultimaBatalla[0], ultimaBatalla[1]);
		ultimasBatallasTable.row();
		ultimasBatallasTable.add(verBatallasAnteriores);
		//ultimasBatallasTable.setBackground(new TextureRegionDrawable(new Texture(Recursos.CUADRO_HUD)));
		hudIzq.add(ultimasBatallasTable);
		
			//Siguiente batalla
		siguienteBatalla = new Table();
		
		hudIzq.row().spaceTop(20);
		
		siguienteBatalla.add(siguienteBatallaLbl);
		siguienteBatalla.add(nombreSiguienteBatalla);
		siguienteBatalla.row();
		siguienteBatalla.add(siguienteBatallaDetalles).left();
		//siguienteBatalla.setBackground(new TextureRegionDrawable(new Texture(Recursos.CUADRO_HUD)));
		
		hudIzq.add(siguienteBatalla).left();
		
		
		//Centro
		
		hudCen = new Table();
		hudCen.add().expand();
		
		//Derecha
		hudDer = new Table();
		pedidosTable = new Table();
		
			//pedidos
		pedidosTable.add(pedidoLbl);
		//pedidosTable.add(pedidoBtn);
		
		hudDer.add(reloj);
		hudDer.row();
		hudDer.add(diaLbl);
		hudDer.row();
		hudDer.add(pedidosTable).bottom();

		
	
		//Gral
		hud.add(hudIzq).top();
		hud.add(hudCen).expand();
		hud.add(hudDer).size(200, 200).top();
		hud.row();

		hud.add(barraAbajoLbl).colspan(3);
		hud.pad(15);

        stage.addActor(hud);


	}
	
	@Override
	public void reEscalar(int width, int height) {
		screenViewport.update(width, height, true);//actualizamos cuando la ventana se reescala
		resultadosHUD.reEscalar(width, height);
		proximaBatallaHUD.reEscalar(width, height);
	}
	public void dispose() {
		stage.dispose();
	}
	
	private void cargarTexturas() {
		dinero_Tex = new Texture(Recursos.DINERO_HUD);
		dineroImgSpr = new Sprite(dinero_Tex);
		
		reloj_Tex = new Texture(Recursos.RELOJ_HUD);
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
		diaLbl = new Label("Luns", labelStyle);
		pedidoLbl = new Label(Recursos.bundle.get("hud.verPedidos"), labelStyle);
		//pedidoBtn = new TextButton("",skin);
		
		//ABAJO
		barraAbajoLbl = new Label("Barra De items", labelStyle);
		
		
		
		//EVENTOS
		verBatallasAnteriores.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!proximaBatallaHUD.getVisible()) {
					resultadosHUD.mostrar(); // Abre resultadosHUD		
					
				}
				//resultadosHUD.cerrar = !mostrarResultadosBatalla;
				
				//System.out.println(HelpDebug.debub(getClass())+"click");
			}
		});
		
		siguienteBatallaDetalles.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!resultadosHUD.getVisible()) {
					proximaBatallaHUD.mostrar();
					
				}
			}
		});
		
		pedidoLbl.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(HelpDebug.debub(getClass())+"click");
			}
		});
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
		
		resultadosHUD.render();
		proximaBatallaHUD.render();//Nose porque no funciona el click de proximaBatallaHUD cuando lo quiero usar despues de haber abierto resultadosHUD
		}
		
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

	@Override
	public void mostrar() {
		visible = true;
		
	}

	@Override
	public void ocultar() {
		visible = false;
		stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
		
	}

}
