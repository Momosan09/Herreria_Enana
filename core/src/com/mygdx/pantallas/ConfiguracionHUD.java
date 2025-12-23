package com.mygdx.pantallas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.hud.HeadUpDisplay;
import com.mygdx.utiles.recursos.Recursos;

public class ConfiguracionHUD implements Screen{
    private Skin skin;

    private Stage stage;

    
     public ConfiguracionHUD() {
    	 create();
     }
    
    public void create() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal(Recursos.config.PANTALLA_CONFIG_SKIN));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);

        Stack stack = new Stack();

        Image image = new Image(skin, "fondoConfig");
        image.setScaling(Scaling.fill);
        stack.addActor(image);

        Table table1 = new Table();
        table1.pad(10.0f);
        table1.align(Align.top);

        Table table2 = new Table();

        ImageButton imageButton = new ImageButton(skin);
        table2.add(imageButton);

        Label label = new Label("Configuracion", skin);
        label.setAlignment(Align.center);
        table2.add(label).growX();
        table1.add(table2).growX().align(Align.top);

        table1.row();
        table2 = new Table();

        Table table3 = new Table();

        label = new Label("Pantalla", skin);
        table3.add(label).align(Align.left).colspan(2);

        table3.add();

        table3.row();
        label = new Label("Resolucion de pantalla", skin);
        table3.add(label);

        SelectBox<String> selectBox = new SelectBox(skin);
        selectBox.setItems("1920x1080", "1270x728");
        table3.add(selectBox).padLeft(15.0f).width(160.0f).height(40.0f);

        table3.row();
        label = new Label("Pantalla Completa", skin);
        table3.add(label).align(Align.left);

        CheckBox checkBox = new CheckBox(null, skin);
        table3.add(checkBox);
        table2.add(table3);

        table3 = new Table();

        label = new Label("Lenguaje", skin);
        table3.add(label);

        table3.add();

        table3.row();
        label = new Label("Lenguaje", skin);
        table3.add(label);

        selectBox = new SelectBox(skin);
        selectBox.setItems("Espa�ol", "Ingles", "Argentino");
        table3.add(selectBox).padLeft(15.0f).width(120.0f).height(40.0f);
        table2.add(table3).pad(10.0f);

        table2.row();
        table3 = new Table();

        label = new Label("Sonido", skin);
        table3.add(label).align(Align.left).colspan(2);

        table3.add();

        table3.row();
        label = new Label("Volumen de la musica", skin);
        table3.add(label).align(Align.left);

        Slider slider = new Slider(0.0f, 100.0f, 1.0f, false, skin, "default-horizontal");
        table3.add(slider);

        table3.row();
        label = new Label("Volumen del menu", skin);
        table3.add(label).align(Align.left);

        slider = new Slider(0.0f, 100.0f, 1.0f, false, skin, "default-horizontal");
        table3.add(slider);
        table2.add(table3).align(Align.left);

        table2.add();
        table1.add(table2).grow();
        stack.addActor(table1);

        table1 = new Table();
        table1.padLeft(1.0f);
        table1.padRight(0.0f);
        table1.padTop(36.0f);
        table1.padBottom(0.0f);
        stack.addActor(table1);
        table.add(stack).grow();
        stage.addActor(table);
    }

    public void render() {

        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public Stage getStage() {
    	return stage;
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {

        stage.act();
        stage.draw();
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
    
}
