package com.mygdx.hud.actoresEspeciales;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.recursos.Recursos;
import com.badlogic.gdx.graphics.Texture;

public class SolapaGeneralesHUD extends Table{
	
	/*TODO acerca el fichero de guerra (asignarle armas a los generales) acordate que la idea es que el jugador deje todos los equipos en la caja de entregas por la noche. 
	 * Al dia siguiente esos equipos lo va a haber agarrado un soldado y llevado a las barracas, el jugador tiene que viajar hasta alli para asignarle manualmente a cada general los equipos. 
	 * Asi que cuando lo agregue eso, no va a salir del inventario del jugador los equpipos si no de otro inventario creado apartir de lo que habia ne la caja de entregas la noche anterios
	 *
	 */
	private Table solapa, retrato, equipo, cualidades, miniTablaArma, miniTablaArmadura, miniTablaAccesorio;
	
	private Label nombreGeneral, nombreArma, nombreArmadura, nombreAccesorio;
	private Label.LabelStyle labelStyle;
	
	private Image retratoGeneral, armaImage, armaduraImage, accesorioImage;
	private Texture texturaArmaImageVacia, texturaArmaduraImageVacia, texturaAccesoriosImageVacia;
	
	
	public SolapaGeneralesHUD(String retrato, String nombre) {
		crearFuentes();
		retratoGeneral = new Image(new Texture(retrato));
		nombreGeneral = new Label(nombre, labelStyle);
		crearActores();
		poblarStage();
		
		solapa.setDebug(true);
		
	}
	
	protected void crearFuentes() {
		labelStyle = EstiloFuente.generarFuente(30, Colores.BLANCO, false);
	}
	
	private void poblarStage() {
		//solapa.setFillParent(true);
		this.add(solapa);
		
		retrato.pad(25);
		retrato.add(nombreGeneral);
		retrato.row();
		retrato.add(retratoGeneral);
		
		solapa.add(retrato);
		
		miniTablaArma.pad(5);
		miniTablaArma.add(nombreArma);
		miniTablaArma.row();
		miniTablaArma.add(armaImage);
		
		miniTablaArmadura.pad(5);
		miniTablaArmadura.add(nombreArmadura);
		miniTablaArmadura.row();
		miniTablaArmadura.add(armaduraImage);
		
		miniTablaAccesorio.pad(5);
		miniTablaAccesorio.add(nombreAccesorio);
		miniTablaAccesorio.row();
		miniTablaAccesorio.add(accesorioImage);
		
		
		equipo.setFillParent(true);
		equipo.add(miniTablaArma);
		equipo.row();
		equipo.add(miniTablaArmadura);
		equipo.row();
		equipo.add(miniTablaAccesorio);
		
		solapa.add(equipo);
		
		solapa.row();
		
		solapa.add(cualidades);
		
	}
	
	private void crearActores() {
		texturaArmaImageVacia = new Texture(Recursos.HOJA_HIERRO_0);
		texturaArmaduraImageVacia = new Texture(Recursos.itemsYmision.DISCO_HIERRO);
		texturaAccesoriosImageVacia = new Texture(Recursos.itemsYmision.MANGO_MADERA_0);
		
		armaImage = new Image(texturaArmaImageVacia);
		armaduraImage = new Image(texturaArmaduraImageVacia);
		accesorioImage = new Image(texturaAccesoriosImageVacia);
		
		armaImage.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {

				System.out.println("click");
			}
		});
		
		armaduraImage.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {

				System.out.println("click");
			}
		});
		
		accesorioImage.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {

				System.out.println("click");
			}
		});
		
		solapa = new Table();
		
		retrato = new Table();
		
		equipo = new Table();
		
		miniTablaArma = new Table();
		miniTablaArmadura = new Table();
		miniTablaAccesorio = new Table();
		
		cualidades = new Table();
		
		nombreArma = new Label("Arma", labelStyle);
		nombreArmadura = new Label("Armadura", labelStyle);
		nombreAccesorio = new Label("Accesorio", labelStyle);
	}
	
	public void poblarTablaArma(String nombre, Texture textura, String cualidades) {
		nombreArma.setText(nombre);
		armaImage.setDrawable(new TextureRegionDrawable(textura));
		miniTablaArma.add(new Label(cualidades, labelStyle));
	}
	
	public void poblarTablaArmadura(String nombre, Texture textura, String cualidades) {
		nombreArmadura.setText(nombre);
		armaduraImage.setDrawable(new TextureRegionDrawable(textura));
		miniTablaArmadura.add(new Label(cualidades, labelStyle));
	}
	
	public void poblarTablaAccesorio(String nombre, Texture textura, String cualidades) {
		nombreAccesorio.setText(nombre);
		accesorioImage.setDrawable(new TextureRegionDrawable(textura));
		miniTablaAccesorio.add(new Label(cualidades, labelStyle));
	}
	
}
