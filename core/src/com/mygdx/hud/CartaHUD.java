package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.entidades.npcs.dialogos.DialogosNPC;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.Config;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.Recursos;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;

public class CartaHUD implements HeadUpDisplay{

	private Stage stage;
	private Table contenedor;
	private Label cuerpoCarta;
	private Button cerrarBoton;
	private Skin botonCerrarSkin;

	private EstiloFuente estiloFuente;
	private Label.LabelStyle labelStyle;
	private DialogosNPC datosCartaNpc;
	InputMultiplexer multiplexer;
	
	
	
	
	public CartaHUD(DialogosNPC datosCartaNpc) {
		this.datosCartaNpc = datosCartaNpc;
		
		botonCerrarSkin = new Skin(Gdx.files.internal(Recursos.BOTON_TEXTO_JSON));
		
		crearFuentes();
		crearActores();
		poblarStage();
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void crearFuentes() {
		estiloFuente = new EstiloFuente();
		labelStyle = estiloFuente.generarFuente(20, Colores.NEGRO, false);
		
	}

	@Override
	public void crearActores() {
		stage = new Stage();
		
		contenedor = new Table();
		contenedor.debug();
		contenedor.setFillParent(false);
		contenedor.setSize(Config.ancho/2, Config.alto/2);
		contenedor.setPosition(Gdx.graphics.getWidth()/2-contenedor.getWidth()/2, Gdx.graphics.getHeight()/2-contenedor.getHeight()/2);

		cuerpoCarta = new Label(datosCartaNpc.getMensaje(0), labelStyle);
		cuerpoCarta.setWrap(true);//Te lo re afane Facu ejej
		
		cerrarBoton = new TextButton("a",botonCerrarSkin);
		cerrarBoton.setPosition(100, 10);
		cerrarBoton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println(HelpDebug.debub(getClass())+"hola");
			}
		});
	}

	@Override
	public void poblarStage() {
		contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.CARTA_TEXTURA)));
		contenedor.add(cuerpoCarta).pad(10).expand().fill().top();//Te lo re afane Facu ejej
		contenedor.add(cerrarBoton);
		//stage.addActor(contenedor);
		stage.addActor(cerrarBoton);
		
	}

	@Override
	public void reEscalar(int width, int heigth) {
		stage.getViewport().update(width, heigth);
	}

	public void render() {
		stage.draw();	
		stage.act(Gdx.graphics.getDeltaTime());
	}

}
