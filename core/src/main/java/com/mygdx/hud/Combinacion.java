package com.mygdx.hud;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.combinaciones.IngredientesId;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.ObjetosDelMapa.Minable.EstadosMinerales;
import com.mygdx.entidades.ObjetosDelMapa.Minable.TipoMinerales;
import com.mygdx.enums.EstadosDelJuego;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.MyDragAndDrop;
import com.mygdx.utiles.recursos.Recursos;

public class Combinacion extends HUD {

    private Label labelInv, titulo;
    private Button cerrarBoton;
    private Skin skin;

    private MyDragAndDrop dragNDrop;
    private Jugador jugador;

    private ArrayList<Image> combinables;

    private int pad = 20;
    private boolean visible = false;

    public Combinacion(Jugador jugador) {
        this.jugador = jugador;
        this.combinables = new ArrayList<>();

        dragNDrop = new MyDragAndDrop(this.jugador);
        dragNDrop.create();

        construir();
    }

    @Override
    public void dibujar() {
        if (visible) {
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
            dragNDrop.render();
        }
    }

    @Override
    public void reEscalar(int width, int heigth) {
        screenViewport.update(width, heigth, true);
        dragNDrop.resize(width, heigth);
    }

    @Override
    public void crearActores() {
        skin = new Skin(Gdx.files.internal(Recursos.hud.SKIN));

        contenedor = new Table();
        tabla = new Table();
        tabla.setFillParent(true);

        contenedor.setBackground(
                new TextureRegionDrawable(new Texture(Recursos.hud.YUNQUE_TEXTURA))
        );

        cerrarBoton = new Button(skin);
        cerrarBoton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ocultar();
                MundoConfig.estadoJuego = EstadosDelJuego.JUEGO;
            }
        });

        titulo = new Label(Recursos.bundle.get("combinacion.titulo"), labelStyle);
        labelInv = new Label(Recursos.bundle.get("combinacion.inventario"), labelStyle);
    }

    /**
     * Carga los ingredientes disponibles desde el inventario del jugador
     */
    public void traerInventario() {
        combinables.clear();

        for (IngredientesId id : IngredientesId.values()) {
            int cantidad = jugador.getCantidad(id);

            if (cantidad > 0) {
                Texture textura = obtenerTextura(id);
                if (textura != null) {
                    Image img = new Image(textura);
                    combinables.add(img);
                }
            }
        }

        // Posicionar (ejemplo simple)
        int x = 32;
        int y = 32;
        for (Image img : combinables) {
            img.setPosition(x, y);
            stage.addActor(img);
            x += 40;
        }
    }

    /**
     * Obtiene la textura segun el tipo de ingrediente
     */
    private Texture obtenerTextura(IngredientesId id) {

        // ITEM
        if (id.tipoI != null) {
            return id.tipoI.getTextura();
        }

        // MINERAL
        if (id.tipoM != null && id.estadoM != null) {
            String ruta =
                    id.tipoM.ruta + id.estadoM.ruta;
            return new Texture(Gdx.files.internal(ruta));
        }

        return null;
    }

    @Override
    public void poblarStage() {
        contenedor.pad(pad);
        contenedor.add(labelInv).top();
        contenedor.add(titulo).top();
        contenedor.add(cerrarBoton);
        contenedor.row();

        tabla.add(contenedor);
        stage.addActor(tabla);
    }

    public Stage getStage() {
        return stage;
    }

    public Stage getDragAndDrop() {
        return dragNDrop.getStage();
    }

    @Override
    public void mostrar() {
        if (!visible) {
            traerInventario();
            stage.getRoot().setTouchable(Touchable.enabled);
            dragNDrop.refrescar();
            visible = true;
        }
    }

    @Override
    public void ocultar() {
        visible = false;
        stage.unfocusAll();
        stage.getRoot().setTouchable(Touchable.disabled);
        stage.clear();
        dragNDrop.ocultar();
    }
}
