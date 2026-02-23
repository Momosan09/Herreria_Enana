package com.mygdx.hud;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.entidades.Jugador;
import com.mygdx.hud.actoresEspeciales.SolapaGeneralesHUD;
import com.mygdx.utiles.recursos.Recursos;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class GeneralesHUD extends HUD {

    private Table menuSolapas;
    private Table contenedorSolapas;

    private List<SolapaGeneralesHUD> solapas;
    private List<TextButton> botonesSolapas;

    private int solapaActiva = -1;
    private static final int MAX_SOLAPAS = 3;

    public GeneralesHUD(Jugador jugador) {
        super(jugador);
        construir();
    }

    @Override
    protected void crearActores() {

        menuSolapas = new Table();
        contenedorSolapas = new Table();

        solapas = new ArrayList<>();
        botonesSolapas = new ArrayList<>();
        
        contenedor.setBackground(new TextureRegionDrawable(new Texture(Recursos.hud.CARTA_TEXTURA)));
    }

    @Override
    protected void poblarStage() {

        //tabla.setFillParent(true);

        // Menu superior
        contenedor.add(menuSolapas).padBottom(10);
        contenedor.row();

        // Contenedor central
        contenedor.add(contenedorSolapas).left();
        contenedor.row();

        contenedor.add(cerrarBtn).padTop(10);
        
        tabla.add(contenedor);

        stage.addActor(tabla);
    }

    /**
     * Agrega una nueva solapa al HUD (max 3)
     */
    public void agregarSolapa(String titulo, SolapaGeneralesHUD solapa) {

        if (solapas.size() >= MAX_SOLAPAS) return;

        final int index = solapas.size();

        solapas.add(solapa);
        solapa.setVisible(false);

        contenedorSolapas.add(solapa).expand().fill();
        //contenedorSolapas.row();

        TextButton btn = new TextButton(titulo, skin);
        botonesSolapas.add(btn);

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                activarSolapa(index);
            }
        });

        menuSolapas.add(btn).pad(5);

        // Si es la primera, activarla automáticamente
        if (solapas.size() == 1) {
            activarSolapa(0);
        }
    }

    /**
     * Activa una solapa y desactiva las demas
     */
    private void activarSolapa(int index) {

        if (index < 0 || index >= solapas.size()) return;

        for (int i = 0; i < solapas.size(); i++) {
            solapas.get(i).setVisible(i == index);
            botonesSolapas.get(i).setDisabled(i == index);
        }

        solapaActiva = index;
    }
    
    public List<SolapaGeneralesHUD> getSolapas() {
    	return solapas;
    }
    
    public SolapaGeneralesHUD getSolapaActiva() {
    	return solapas.get(solapaActiva);
    }
    

    @Override
    protected void mostrar() {
        if (!visible) {
            stage.getRoot().setTouchable(Touchable.enabled);
        }
        visible = true;
    }
}
