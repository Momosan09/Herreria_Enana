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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.entidades.Jugador;
import com.mygdx.entidades.Npc;
import com.mygdx.entidades.npcs.dialogos.charlas.Charla;
import com.mygdx.entidades.npcs.dialogos.charlas.Respuesta;
import com.mygdx.utiles.Colores;
import com.mygdx.utiles.EstiloFuente;
import com.mygdx.utiles.MundoConfig;
import com.mygdx.utiles.recursos.Recursos;

public class Dialogo extends Actor implements HeadUpDisplay, Ocultable {

    private Npc locutor;
    private Jugador jugador;

    private ScreenViewport screenViewport;
    private Stage stage;
    private Table tabla, contenedor;

    private Label nombre, mensaje;
    private Array<Label> respuestas = new Array<>();

    private Image retrato;
    private NinePatchDrawable fondo;
    private Label.LabelStyle labelStyle;

    private int padding = 20;
    private boolean mostrar = false;
    private String charlaActualId = "";

    public Dialogo(Jugador jugador) {
        this.jugador = jugador;
        poblarStage();
        Recursos.muxJuego.addProcessor(stage);
    }

    public void setLocutor(Npc locutor) {
        this.locutor = locutor;
        actualizarContenido();
    }

    public Npc getLocutor() {
        return locutor;
    }

    @Override
    public void dibujar() {
        if (mostrar) {
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
    }

    public void mostrar() {
        mostrar = true;
        stage.getRoot().setTouchable(Touchable.enabled);
    }

    public void ocultar() {
        mostrar = false;
        stage.getRoot().setTouchable(Touchable.disabled);
    }

    public void dispose() {
        stage.dispose();
    }

    public void actualizarContenido() {

        if (locutor == null) return;

        Charla charla = locutor.getCharlaActual();
        if (charla == null) return;

        if (!charlaActualId.equals(charla.id())) {

            charlaActualId = charla.id();

            nombre.setText(locutor.getNombre());
            mensaje.setText(charla.monologo());
            mensaje.setWrap(true);

            reconstruirRespuestas(charla);
        }
    }

    private void reconstruirRespuestas(Charla charla) {

        // eliminar respuestas anteriores del contenedor
        for (Label l : respuestas) {
            l.remove();
        }
        respuestas.clear();

        // si no hay respuestas → cerrar diálogo automáticamente
        if (charla.respuestas().isEmpty()) {
            //ocultar();
            return;
        }

        int index = 0;

        for (Respuesta respuesta : charla.respuestas()) {

            final int indiceRespuesta = index;

            Label label = new Label(respuesta.texto(), labelStyle);

            label.addListener(new InputListener() {

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                    MundoConfig.dialogoManager.elegirRespuesta(indiceRespuesta);

                    actualizarContenido();
                    return true;
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    label.setColor(Color.valueOf(Colores.SELECCIONADO));
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    label.setColor(Color.WHITE);
                }
            });

            respuestas.add(label);

            contenedor.row();
            contenedor.add(label).left().padTop(5);

            index++;
        }
    }

    @Override
    public void crearActores() {

        nombre = new Label("", labelStyle);
        mensaje = new Label("", labelStyle);

        retrato = new Image(new Texture(Recursos.npc.enanos.portraits.VENDEDOR_AMBULANTE_PORTRAIT));

        fondo = new NinePatchDrawable(
                new NinePatch(new Texture(Recursos.hud.DIALOGO_HUD))
        );
    }

    @Override
    public void poblarStage() {

        crearFuentes();
        crearActores();

        screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);

        tabla = new Table();
        tabla.setFillParent(true);

        contenedor = new Table();
        contenedor.setBackground(fondo);

        contenedor.add(nombre).left().expandX().padLeft(padding * 2.25f);
        contenedor.row();

        contenedor.add(mensaje).left().expand().fill();
        contenedor.add(retrato)
                .size(retrato.getWidth() * 2, retrato.getHeight() * 2);

        contenedor.padLeft(padding);
        contenedor.padRight(padding);
        contenedor.padBottom(padding);

        tabla.add(contenedor).bottom().expand();
        tabla.padBottom(padding);

        stage.addActor(tabla);
    }

    @Override
    public void crearFuentes() {
        labelStyle = EstiloFuente.generarFuente(22, Colores.BLANCO, false);
    }

    @Override
    public void reEscalar(int width, int height) {
        screenViewport.update(width, height, true);
    }

    @Override
    public boolean getVisible() {
        return mostrar;
    }

    public Stage getStage() {
        return stage;
    }
}