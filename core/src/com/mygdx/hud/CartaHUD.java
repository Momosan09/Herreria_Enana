package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.npcs.dialogos.DialogosNPC;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;

public class CartaHUD implements HeadUpDisplay, Cerrable{

	private Stage stage;
	private ScreenViewport screenViewport;
	private Table tabla, contenedor;
	private Label cuerpoCarta;
	private Button cerrarBoton;
	private Skin skin;

	private EstiloFuente estiloFuente;
	private Label.LabelStyle labelStyle;
	private DialogosNPC datosCartaNpc;

	private boolean cerrar = false;
	
	
	
	
	public CartaHUD(DialogosNPC datosCartaNpc) {
		this.datosCartaNpc = datosCartaNpc;
		screenViewport = new ScreenViewport();
		stage = new Stage(screenViewport);

		crearFuentes();
		crearActores();
		poblarStage();
	}
	
	@Override
	public void crearFuentes() {
		estiloFuente = new EstiloFuente();
		labelStyle = estiloFuente.generarFuente(20, Colores.NEGRO, false);
		
	}

	@Override
	public void crearActores() {

		skin = new Skin(Gdx.files.internal(Recursos.SKIN));
		
		tabla = new Table();
		tabla.setFillParent(true);
		contenedor = new Table();
		contenedor.debug();
		contenedor.setFillParent(false);
		//contenedor.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		//contenedor.setPosition(Gdx.graphics.getWidth()/2-contenedor.getWidth()/2, Gdx.graphics.getHeight()/2-contenedor.getHeight()/2);

		cuerpoCarta = new Label(datosCartaNpc.getMensaje(0), labelStyle);
		cuerpoCarta.setWrap(true);//Te lo re afane Facu ejej
		
		cerrarBoton = new Button(skin);
		cerrarBoton.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				cerrar = true;
				System.out.println(HelpDebug.debub(getClass())+cerrar);
			}
		});
	}

	@Override
	public void poblarStage() {
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.CARTA_TEXTURA)));
		contenedor.add(cuerpoCarta).pad(10).expand().fill();//Te lo re afane Facu ejej
		contenedor.add(cerrarBoton).top();
		tabla.add(contenedor);
		stage.addActor(tabla);
		
	}

	@Override
	public void reEscalar(int width, int height) {
		    screenViewport.update(width, height, true);
	}

	@Override
	public void cerrar() {
	    contenedor.clear(); // Limpia todos los actores del contenedor
	    stage.unfocusAll(); // Desenfoca el stage para que no procese eventos
	    stage.clear(); // Limpia el stage completamente
		
	}

	public void render() {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();	
	}
	
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

	public Stage getStage() {
		return stage;
	}
	
	public boolean getCerrar() {
		return cerrar;
	}
}
