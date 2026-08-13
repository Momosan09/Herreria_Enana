package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.charlas.Charla;
import com.mygdx.entidades.npcs.dialogos.charlas.Respuesta;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.eventos.Listeners;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.HelpDebug;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.recursos.Recursos;

public class Dialogo extends Actor implements Ocultable {

	private Stage stage = new Stage(new ScreenViewport());
	private Label monologo;
	private List<String> respuestas;
	private boolean visible = false;
	private Skin skin = new Skin(Gdx.files.internal(Recursos.hud.SKIN_DIALOGO_TEMPORAL));
	//Gdx.input.setInputProcessor(stage);

	public Dialogo() {

	Table table = new Table();
	table.setFillParent(true);

	Table table1 = new Table();

	table1.add().grow();

	table1.row();
	VerticalGroup verticalGroup = new VerticalGroup();

	monologo = new Label("El texto de la charla", skin);
//	monologo.setColor(skin.getColor("black"));
	verticalGroup.addActor(monologo);

	respuestas = new List<>(skin);
	respuestas.setName("listaRespuestas");
	respuestas.setItems("respuesta1", "respuesta2", "respuesta3");
	verticalGroup.addActor(respuestas);
	table1.add(verticalGroup).pad(10.0f);
	table.add(table1).grow();
	stage.addActor(table);
	
	
	respuestas.addListener(new ChangeListener() {

	    @Override
	    public void changed(ChangeEvent event, Actor actor) {

	        int indice = respuestas.getSelectedIndex();

	        if (indice >= 0 && indice < MundoConfig.locutor.getCharlaActual().respuestas().size()) {

	            Respuesta r = MundoConfig.locutor
	                    .getCharlaActual()
	                    .respuestas()
	                    .get(indice);

	            Listeners.setRespuestaElegida(r);
	        }
	    }
	});
	}
	
	
	public void actualizarCharla() {
		if(MundoConfig.acutualizarCharla && MundoConfig.locutor != null) {
//			System.out.println(HelpDebug.debub(getClass())+"interaccion");
		monologo.setText(MundoConfig.locutor.getCharlaActual().monologo());
		
		java.util.List<Respuesta> lista = MundoConfig.locutor.getCharlaActual().respuestas();

		if(!lista.isEmpty()) {
		    respuestas.setVisible(true);
		String[] items = new String[lista.size()];

		for (int i = 0; i < lista.size(); i++) {
		    items[i] = lista.get(i).texto();
		}

		respuestas.setItems(items);
		MundoConfig.acutualizarCharla = false;
	}else {
	    respuestas.setVisible(false);
	}
	}
		
	}
	
	public void dibujar() {
		if(visible) {
	    actualizarCharla();
	    stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw();
		}
		}


	@Override
	public void mostrar() {
		if(!visible) {
			visible = true;

		}
		
	}

	@Override
	public void ocultar() {
		if(visible == true) {
			visible = false;
		    respuestas.setSelectedIndex(-1);//limpia la respuesta seleccionada al cerrar
//			  stage.getRoot().setTouchable(Touchable.disabled);
//			  stage.unfocusAll();//Cuando esta oculto desenfoca el stage para que no procese eventos
		}
		
	}


	@Override
	public boolean getVisible() {
		return visible;
	}

	public Stage getStage() {
		return stage;
	}

}
