package com.mygdx.pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Principal;
import com.mygdx.hud.HeadUpDisplay;
import com.mygdx.io.Entradas;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.mygdx.utiles.Render;
import com.mygdx.red.Cliente;

public class PantallaMenu implements Screen, HeadUpDisplay{

	private OrthographicCamera camara;
	final Principal game;
	//Stage trae su propio viewport, no es necesario crear uno	//Error, si es necesario crear uno, para los hud va el screenViewPort
	private ScreenViewport screenViewPort;
	private Stage stage;
	private Table interfaz;
	private Table opciones;
	private Label[] interfazTexto;
	private Label mensajePerdidaConexion;
	private Label.LabelStyle tituloEstilo, subTituloEstilo, opcionEstilo, selccionadoEstilo, bottomEstilo, perdidaConexion;
	Entradas entradas = new Entradas();
	
	private Texture fondoImg;
	private Sprite fondo;

	private Music musicaMenu;

	private int cont = 2;
	
    private float fondoPosX, offSetX, transparencia=0;
    
    private boolean mostrarMensajeDeDesconectado=false;
    
    //Red
    private Cliente cliente;
    
	public PantallaMenu(final Principal game) {
		this.game = game;
		
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fondoImg = new Texture(Recursos.FONDO_MENU);
		fondo = new Sprite(fondoImg);
		crearFuentes();
		crearActores();
		poblarStage();
		
		musicaMenu = Gdx.audio.newMusic(Gdx.files.internal(Recursos.MUSICA_MENU));

		Gdx.input.setInputProcessor(entradas);

		// Inicializar la instancia de SpriteBatch en Render con la del juego
		Render.batch = game.batch;
		fondo.setPosition(0, stage.getViewport().getWorldHeight() - fondoImg.getHeight()); // creo que el segundo parametro lo hace una medida mas relativa, nose, investigar

	}
	
	
	public PantallaMenu(final Principal game, boolean mostrarMensajeDeDesconectado) {
		this.game = game;
		
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		fondoImg = new Texture(Recursos.FONDO_MENU);
		fondo = new Sprite(fondoImg);
		crearFuentes();
		crearActores();
		poblarStage();
		
		musicaMenu = Gdx.audio.newMusic(Gdx.files.internal(Recursos.MUSICA_MENU));

		Gdx.input.setInputProcessor(entradas);

		// Inicializar la instancia de SpriteBatch en Render con la del juego
		Render.batch = game.batch;
		fondo.setPosition(0, stage.getViewport().getWorldHeight() - fondoImg.getHeight()); // creo que el segundo parametro lo hace una medida mas relativa, nose, investigar
		
		this.mostrarMensajeDeDesconectado = mostrarMensajeDeDesconectado;

	}

	@Override
	public void show() {



		

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		musicaMenu.setLooping(true);
		musicaMenu.play();

		camara.update();
		Render.batch.setProjectionMatrix(camara.combined);
		Render.batch.begin();
		fondoEnMovimiento(delta);
		Render.batch.end();

		if(mostrarMensajeDeDesconectado) {
			mensajePerdidaConexion.setVisible(true);
		}else {
			mensajePerdidaConexion.setVisible(false);
		}
		
		interfaz.act(delta);//ejecuta las Actions de esta tabla
		stage.draw();
		
		seleccionarOpcion();

	}

	@Override
	public void resize(int width, int height) {
		offSetX = fondoImg.getWidth() - camara.viewportWidth; //usa el ancho de la textura porque este va a ser siempre constante, en el caso de que modifique el tamaño del sprite aca no deberia haber cambios
		screenViewPort.update(width, height, true);		
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
//		fondoImg.dispose();
		stage.dispose();
		game.font.dispose();
		entradas.efectoSonidoTeclas.dispose();
		musicaMenu.stop();
		musicaMenu.dispose();

	}
	
	@Override
	public void crearFuentes() {
		tituloEstilo = EstiloFuente.generarFuente(80, Colores.BLANCO, false);
		subTituloEstilo = EstiloFuente.generarFuente(26, Colores.BLANCO, false);
		opcionEstilo = EstiloFuente.generarFuente(22, Colores.BLANCO, false);
		selccionadoEstilo = EstiloFuente.generarFuente(22, Colores.SELECCIONADO, false);
		bottomEstilo = EstiloFuente.generarFuente(20, Colores.MENUBOTTOMCOLOR, false);
		perdidaConexion = EstiloFuente.generarFuente(24, Colores.AU, false);
	}
	
	@Override
	public void crearActores() {
		screenViewPort = new ScreenViewport();
		stage = new Stage(screenViewPort);
			
		interfaz = new Table();
		interfaz.setFillParent(true);
		interfaz.debug();
		
		interfazTexto = new Label[6];
		
		interfazTexto[0] = new Label("Herreria Enana", tituloEstilo);
		interfazTexto[1] = new Label(Recursos.bundle.get("menuPrincipal.menuPrincipal"), subTituloEstilo);
		interfazTexto[5] = new Label(Recursos.bundle.get("menuPrincipal.textoRespira"), bottomEstilo);
		
		//Opciones
		opciones = new Table();
		opciones.debug();
		interfazTexto[2] = new Label(Recursos.bundle.get("menuPrincipal.jugar"), opcionEstilo);
		interfazTexto[3] = new Label(Recursos.bundle.get("menuPrincipal.red"), opcionEstilo);
		interfazTexto[4] = new Label(Recursos.bundle.get("menuPrincipal.configuraciones"), opcionEstilo);

		mensajePerdidaConexion = new Label(Recursos.bundle.get("menuPrincipal.perdidaConexion"), perdidaConexion);
	}

	@Override
	public void poblarStage() {
		interfaz.add(interfazTexto[0]);
		interfaz.row();
		interfaz.add(interfazTexto[1]);
		interfaz.row();
			
		interfaz.add(mensajePerdidaConexion);
		interfaz.row();
		
		//opciones
			opciones.add(interfazTexto[2]);
			opciones.row();
			opciones.add(interfazTexto[3]);
			opciones.row();
			opciones.add(interfazTexto[4]);
		
		interfaz.add(opciones).expand();
		interfaz.row();
		interfaz.add(interfazTexto[5]).bottom().padBottom(20);
		agregarAnimaciones();
		
		stage.addActor(interfaz);
	}
	
	private void seleccionarOpcion() {
		int seleccion = entradas.seleccionarOpcion(interfazTexto, 2, 4);

		if (seleccion == 2) {
			game.setScreen(new Juego(game, false));
			dispose();
		} else if (seleccion == 3) {
			System.out.println(HelpDebug.debub(getClass())+"Red");
			game.setScreen(new PantallaConexion(game));
			dispose();
			

		}else if (seleccion == 4){
			game.setScreen(new PantallaConfiguracion(game));
			dispose();
		}
		if (seleccion == -1) {
			
		}

	
	}
    private void fondoEnMovimiento(float delta) {
    	float velocidadDesplazamiento = 30;
    	if(fondo!=null) {
    		
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
    
    private void agregarAnimaciones() {
    	interfazTexto[5].addAction(Actions.forever(Actions.sequence(
    			Actions.fadeOut(2f, Interpolation.fade)	
    	)));
  }

	@Override
	public void reEscalar(int width, int heigth) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
