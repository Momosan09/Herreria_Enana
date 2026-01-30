package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.npcs.dialogos.DialogosNPC;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.historia.Mision;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.recursos.Recursos;

public class CartaHUD extends HUD implements Cerrable{


	private Label cuerpoCarta;
	private Button cerrarBoton;
	private Skin skin;

	private DialogosNPC datosCartaNpc;

	private boolean cerrar = false;
	private boolean auxiliar = false; //es para mandar el mensaje una unica vez
	private Mision mision;
	
	public CartaHUD(DialogosNPC datosCartaNpc) {
		this.datosCartaNpc = datosCartaNpc;
		
		construir();
		visible = true;
	}
	
	public CartaHUD(DialogosNPC datosCartaNpc, Mision mision) {
		this.datosCartaNpc = datosCartaNpc;

		this.mision = mision;
		
		construir();
		visible = true;
	}
	

	@Override
	public void crearActores() {

		skin = new Skin(Gdx.files.internal(Recursos.hud.SKIN));
		
		tabla = new Table();
		tabla.setFillParent(true);
		contenedor = new Table();
		//contenedor.debug();
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
				MundoConfig.estadoJuego = EstadosDelJuego.JUEGO;
				cerrar = false;
			}
		});
	}

	@Override
	public void poblarStage() {
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.CARTA_TEXTURA)));
		contenedor.add(cuerpoCarta).pad(10).expand().fill();//Te lo re afane Facu ejej
		contenedor.add(cerrarBoton).top();
		tabla.add(contenedor);
		stage.addActor(tabla);
		
	}


	@Override
	public void cerrar() {
	    contenedor.clear(); // Limpia todos los actores del contenedor
	    stage.unfocusAll(); // Desenfoca el stage para que no procese eventos
	    stage.clear(); // Limpia el stage completamente
		
	}

	
	public void dispose() {
		Recursos.muxJuego.removeProcessor(stage);//tengo que sacar el stage del inputprocesor porque el mux es estatico, entonces cuando entro y salgo del juego, el mux agrega el nuevo stage pero sigue guardando el anterior
		stage.dispose();
		skin.dispose();
	}
	
	public Mision getMision() {
		return mision;
	}

	public Stage getStage() {
		return stage;
	}
	
	public boolean getCerrar() {
		return cerrar;
	}

	@Override
	public boolean seCerro() {
		if(cerrar && !auxiliar) {
			auxiliar = true;
			return true;
		}else {
			return false;
		}
		
	}
}
