package com.mygdx.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Principal;
import com.mygdx.hud.HUD;
import com.mygdx.hud.HeadUpDisplay;
import com.mygdx.io.EntradaMenu;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.idiomas.IdiomaCompleto;

public class PantallaConfiguracion extends HUD implements Screen{
	

	final Principal game;
	private Skin skin;
	private Table interfaz, barraDeArriba, pantalla, sonido, idioma;
	//pantalla
	private Label pantallaTextos[];
	private SelectBox pantallaResolucionesSelectBox;
	private CheckBox pantallaCompletaCheck;
	//sonido
	private Slider sonidoSliders[];
	private Label sonidoTextos[];
	
	//Idioma
	private Label idiomaLbl;
	private SelectBox idiomasSelectBx;
	private ImageButton botonVolver;
	private Label interfazTextos[];
	private Label.LabelStyle estiloLabel, tituloEstilo;
	EntradaMenu entradas = new EntradaMenu();
	OrthographicCamera camara;
	
	public PantallaConfiguracion(final Principal game) {
		this.game = game;
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);
		//stage.setDebugAll(true);
		Gdx.input.setInputProcessor(stage);
		//Recursos.muxMenu.addProcessor(stage);
		
	}
	
	@Override
	public void show() {
		crearFuentes();
		crearActores();
		poblarStage();
		cambiarSeleccionadoSelectBox();

	}


	
	@Override
	public void render(float delta) {
    	stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		System.out.println("jack pot");
		//seleccionarOpcion();
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void poblarStage() {
	    crearFuentes();
	    crearBarraDeArriba();
	    interfaz.add(barraDeArriba).expandX();
	    interfaz.row();

	    // Tabla de la pantalla
	    pantalla.add(pantallaTextos[0]);
	    pantalla.add(pantallaResolucionesSelectBox).size(300, 20);
	    pantalla.row();
	    pantalla.add(pantallaTextos[1]);
	    pantalla.add(pantallaCompletaCheck);
	    interfaz.add(pantalla).pad(10,10,5,10).expandY(); // Agrega margen a la tabla pantalla

	    // Idioma
	    idioma.add(idiomaLbl);
	    idioma.add(idiomasSelectBx).size(300, 20);
	    interfaz.add(idioma).pad(10,10,5,10).expandY(); // Agrega margen a la tabla idioma
	    interfaz.row();

	    // Sonido
	    sonido.add(sonidoTextos[0]);
	    sonido.add(sonidoSliders[0]);
	    sonido.row();
	    sonido.add(sonidoTextos[1]);
	    sonido.add(sonidoSliders[1]);
	    interfaz.add(sonido).pad(5,10,10,10).expandY(); // Agrega margen a la tabla sonido

	    interfaz.setFillParent(true);
	   
	    stage.addActor(interfaz);
	}

	

	@Override
	public void crearFuentes() {
		estiloLabel = EstiloFuente.generarFuente(24, Colores.BLANCO, false);
		tituloEstilo = EstiloFuente.generarFuente(40, Colores.BLANCO, false);
		skin = new Skin(Gdx.files.internal(Recursos.PANTALLA_CONFIG_SKIN));
		
	}

	@Override
	public void crearActores() {
		botonVolver = new ImageButton(skin);
		botonVolver.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
    			game.setScreen(new PantallaMenu(game));
    			dispose();
            }
        });
		
		//pantalla Configs
		pantalla = new Table();
		pantallaTextos = new Label[4];
		pantallaTextos[0] = new Label(Recursos.bundle.get("pantallaConfiguracion.resolucion"), estiloLabel);
		pantallaResolucionesSelectBox = new SelectBox(skin);

		pantallaResolucionesSelectBox.setItems(Config.resolucionesString);
		pantallaResolucionesSelectBox.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(!pantallaCompletaCheck.isChecked()) {					
				sacarValorResolucion(pantallaResolucionesSelectBox.getSelected().toString());
				}
				
			}
		});
		
		
		pantallaCompletaCheck = new CheckBox("", skin);
		if(Config.prefs.getBoolean("pantallaCompleta")) {
			pantallaCompletaCheck.setChecked(true);
		}
		pantallaTextos[1] = new Label(Recursos.bundle.get("pantallaConfiguracion.pantallaCompleta"), estiloLabel);
		
		pantallaCompletaCheck.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(!pantallaCompletaCheck.isChecked()) {
					sacarValorResolucion(pantallaResolucionesSelectBox.getSelected().toString());
					Config.prefs.putBoolean("pantallaCompleta", false);

				}else {
					Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
					Config.prefs.putBoolean("pantallaCompleta", Gdx.graphics.isFullscreen());
				}
				
			}
		});
		
		
		//Sonido
		sonido = new Table();
		
		sonidoTextos = new Label[2];
		sonidoTextos[0] = new Label(Recursos.bundle.get("pantallaConfiguracion.volumenMusica"), estiloLabel);
		
		sonidoSliders = new Slider[3];
		sonidoSliders[0] = new Slider(0,1,.01f, false,skin);
		sonidoSliders[0].setValue(Config.prefs.getFloat("nivelVolumenMusica"));
		sonidoSliders[0].addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Config.volumenMusica = sonidoSliders[0].getValue();
				Config.prefs.putFloat("nivelVolumenMusica", Config.volumenMusica);
				System.out.println(Config.volumenMusica);
			}
		});
		
		sonidoTextos[1] = new Label(Recursos.bundle.get("pantallaConfiguracion.volumenMenus"), estiloLabel);
		
		sonidoSliders[1] = new Slider(0,1,.01f, false,skin);
		sonidoSliders[1].setValue(Config.prefs.getFloat("nivelVolumenMenues"));
		sonidoSliders[1].addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Config.volumenMenues = sonidoSliders[1].getValue();
				Config.prefs.putFloat("nivelVolumenMenues", Config.volumenMenues);
				System.out.println(Config.volumenMenues);
			}
		});
		
		//idioma
		idioma = new Table();
		idiomaLbl = new Label(Recursos.bundle.get("pantallaConfiguracion.idiomaLbl"), estiloLabel);
		idiomasSelectBx = new SelectBox(skin);
		idiomasSelectBx.setItems(Config.idiomasString);
		idiomasSelectBx.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				  String idiomaSeleccionado = idiomasSelectBx.getSelected().toString();
				traducirNombreIdioma(idiomasSelectBx.getSelected().toString());
				actualizarTextos();
		        Config.prefs.putString("idioma", idiomaSeleccionado);
		        Config.prefs.flush();
			}
		});
		
		interfazTextos = new Label[3];
		
		interfaz = new Table();
		//interfaz.setFillParent(true);
//		interfaz.debug();
		
		interfazTextos[0] = new Label(Recursos.bundle.get("pantallaConfiguracion.volverMenuPrincipal"), estiloLabel);
		interfazTextos[1] = new Label(Recursos.bundle.get("pantallaConfiguracion.titulo"), tituloEstilo);
		interfazTextos[2] = new Label(Recursos.bundle.get("pantallaConfiguracion.pantallaCompleta")+" "+ (Config.pantallaCompleta?Recursos.bundle.get("si"):Recursos.bundle.get("no")) , estiloLabel);
		
		

	}
	
	private void crearBarraDeArriba() {
		barraDeArriba = new Table();
		barraDeArriba.add(botonVolver);
		barraDeArriba.add(interfazTextos[1]).expandX();
		
	}

	private void sacarValorResolucion(String s) {
		String[] resolucion = s.split("x");
		int ancho = Integer.parseInt(resolucion[0].trim());
		int alto = Integer.parseInt(resolucion[1].trim());
		Gdx.graphics.setWindowedMode(ancho, alto);
		Config.prefs.putInteger("pantallaAncho", ancho);
		Config.prefs.putInteger("pantallaAlto", alto);
	}
	
	/**
	 * Cambia el nombre del idioma elegido por el codigo del idioma elegido
	 * si apreto "Ingles" => "en"
	 */
	private void traducirNombreIdioma(String s) {
		if(s.equals(IdiomaCompleto.ARGENTINO.getNombre())) {
			Recursos.bundle =  I18NBundle.createBundle(Gdx.files.internal("locale/locale_es_ar"));	
			//System.out.println("idioma Argentino");
		}else if(s.equals(IdiomaCompleto.ESPANIOL.getNombre())) {
			Recursos.bundle =  I18NBundle.createBundle(Gdx.files.internal("locale/locale_es"));	
			//System.out.println("idioma español");
		}else if(s.equals(IdiomaCompleto.INGLES.getNombre())) {
			Recursos.bundle =  I18NBundle.createBundle(Gdx.files.internal("locale/locale_en"));	
			//System.out.println("idioma ingles");
		}

	}

	private void actualizarTextos() {
	    pantallaTextos[0].setText(Recursos.bundle.get("pantallaConfiguracion.resolucion"));
	    pantallaTextos[1].setText(Recursos.bundle.get("pantallaConfiguracion.pantallaCompleta"));
	    idiomaLbl.setText(Recursos.bundle.get("pantallaConfiguracion.idiomaLbl"));
	    sonidoTextos[0].setText(Recursos.bundle.get("pantallaConfiguracion.volumenMusica"));
	    sonidoTextos[1].setText(Recursos.bundle.get("pantallaConfiguracion.volumenMenus"));
	    interfazTextos[0].setText(Recursos.bundle.get("pantallaConfiguracion.volverMenuPrincipal"));
	    interfazTextos[1].setText(Recursos.bundle.get("pantallaConfiguracion.titulo"));
	}
	
	/**
	 * Cambia el idioma seleccionado en del selectbox a el actual
	 */
	private void cambiarSeleccionadoSelectBox() {
		String idiomaGuardado = Config.prefs.getString("idioma"); // Valor predeterminado
		traducirNombreIdioma(idiomaGuardado);
		idiomasSelectBx.setSelected(idiomaGuardado);
	}

	


	@Override
	public void dispose() {
		Recursos.muxMenu.removeProcessor(stage);
		Config.prefs.putBoolean("pantallaCompleta", Gdx.graphics.isFullscreen());
		Config.prefs.flush();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	

}
