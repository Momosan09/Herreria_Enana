package com.mygdx.pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Principal;
import com.mygdx.io.Entradas;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.utiles.Texto;

public class PantallaMenu implements Screen {

	OrthographicCamera camara;
	final Principal game;
	//Stage trae su propio viewport, no es necesario crear uno
	
	private Stage stage;
	private Table menu;
	private Table opciones;
	private Label[] interfazTexto;
	private Label.LabelStyle tituloEstilo, subTituloEstilo, opcionEstilo, selccionadoEstilo, bottomEstilo;
	private EstiloFuente estiloFuente;
	
	
	Entradas entradas = new Entradas();
	
	private Texture fondoImg;
	private Sprite fondo;

	private Texto[] textos;
	private Music musicaMenu;

	private int cont = 2;
	
    private float fondoPosX;
    private float offSetX;
    
	public PantallaMenu(final Principal game) {
		this.game = game;
		
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//Stage trae su propio viewport, no es necesario crear uno
		
		crearInterfaz();
		
		fondoImg = new Texture(Recursos.FONDO_MENU);
		fondo = new Sprite(fondoImg);



		Gdx.input.setInputProcessor(entradas);

		// Inicializar la instancia de SpriteBatch en Render con la del juego
		Render.batch = game.batch;

	}

	@Override
	public void show() {
		musicaMenu = Gdx.audio.newMusic(Gdx.files.internal(Recursos.MUSICA_MENU));
		/*
		textos = new Texto[5];
		textos[0] = new Texto(Recursos.FUENTE_TEMPORAL, 62, Color.WHITE, false);
		textos[1] = new Texto(Recursos.FUENTE_TEMPORAL, 42, Color.WHITE, false);
		textos[2] = new Texto(Recursos.FUENTE_TEMPORAL, 38, Color.WHITE, false);
		textos[3] = new Texto(Recursos.FUENTE_TEMPORAL, 38, Color.WHITE, false);
		textos[4] = new Texto(Recursos.FUENTE_TEMPORAL, 36, Color.SALMON, false);


		textos[0].setTexto("Herreria Enana");
		textos[0].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[0].getAncho() / 2), Gdx.graphics.getHeight());

		textos[1].setTexto("Menu Principal");
		textos[1].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[1].getAncho() / 2),
				(Gdx.graphics.getHeight()) - (textos[1].getAlto() * 2));

		textos[2].setTexto("Jugar");
		textos[2].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[2].getAncho() / 2), (Config.alto/2) + textos[2].getAlto()*3);

		textos[3].setTexto("Configuracion");
		textos[3].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[3].getAncho() / 2), (Config.alto/2) + textos[3].getAlto());

		textos[4].setTexto("El videojuego de herreria por combinacion");
		textos[4].setPosicion((Gdx.graphics.getWidth() / 2) - (textos[4].getAncho() / 2), textos[4].getAlto() * 2);
	*/
		fondo.setPosition(0, stage.getViewport().getWorldHeight() - 200); // creo que el segundo parametro lo hace una medida mas relativa, nose, investigar

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		stage.draw();
		musicaMenu.setLooping(true);
		//musicaMenu.play();

		camara.update();
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();
		
		//fondoEnMovimiento(delta);


/*
		for (int i = 0; i < textos.length; i++) {
			textos[i].dibujar();
		}*/
		Render.batch.end();
		seleccionarOpcion();
		// entradas.seleccionarOpcion(textos,2,3);

	}

	@Override
	public void resize(int width, int height) {
		offSetX = fondoImg.getWidth() - camara.viewportWidth; //usa el ancho de la textura porque este va a ser siempre constante, en el caso de que modifique el tamaño del sprite aca no deberia haber cambios
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
	public void dispose() {
		stage.dispose();
		game.font.dispose();
		entradas.efectoSonidoTeclas.dispose();
		musicaMenu.pause();
		musicaMenu.dispose();

	}
	
	private void crearEstilosDeLabel() {
		estiloFuente = new EstiloFuente();
		tituloEstilo = estiloFuente.generarFuente(80, Colores.BLANCO, false);
		subTituloEstilo = estiloFuente.generarFuente(26, Colores.BLANCO, false);
		opcionEstilo = estiloFuente.generarFuente(22, Colores.BLANCO, false);
		selccionadoEstilo = estiloFuente.generarFuente(22, Colores.SELECCIONADO, false);
		bottomEstilo = estiloFuente.generarFuente(20, Colores.MENUBOTTOMCOLOR, false);
	}
	
	private void crearInterfaz() {
		crearEstilosDeLabel();
		stage = new Stage();
		
		menu = new Table();
		menu.setFillParent(true);
		menu.debug();
		
		interfazTexto = new Label[5];
		
		interfazTexto[0] = new Label("Herreria Enana", tituloEstilo);
		interfazTexto[1] = new Label("Menu Principal", subTituloEstilo);
		interfazTexto[4] = new Label("El videojuego de herreria por combinacion", bottomEstilo);
		
		//Opciones
		opciones = new Table();
		opciones.debug();
		interfazTexto[2] = new Label("Jugar", opcionEstilo);
		interfazTexto[3] = new Label("Configuraciones", opcionEstilo);
		
		menu.add(interfazTexto[0]);
		menu.row();
		menu.add(interfazTexto[1]);
		menu.row();
		
		//opciones
			opciones.add(interfazTexto[2]);
			opciones.row();
			opciones.add(interfazTexto[3]);
		
		menu.add(opciones).expand();
		menu.row();
		menu.add(interfazTexto[4]).bottom().padBottom(20);
		
		
		stage.addActor(menu);
	}

	private void seleccionarOpcion() {
		int seleccion = entradas.seleccionarOpcion(interfazTexto, 2, 3);

		if (seleccion == 2) {
			game.setScreen(new Juego(game));
			dispose();
		} else if (seleccion == 3) {
			game.setScreen(new PantallaConfiguracion(game));
			dispose();

		}
		if (seleccion == -1) {
			
		}

	
	}
    private void fondoEnMovimiento(float delta) {
    	float velocidadDesplazamiento = 30;
    	
        fondo.draw(Render.batch);
        fondoPosX -= velocidadDesplazamiento * delta;
        if (fondoPosX <= offSetX*-1) {
            fondoPosX = 0;
            fondo.setX(0);//aca tengo que poner la imagen en el 0X para solucionar el problema de que se veia el fondo de la ventana cuando se reinicaba el recorrido
            //sin fondo.setX(0) hasta que sale del if se sigue modificando la posicion del fondo y solo se seteea al final del metodo dando un espacio "negro" en la imagen que se mueve
        }

        fondo.setX(fondoPosX);
    }



}
