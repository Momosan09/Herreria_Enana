package com.mygdx.hud;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.armas.Equipo;
import com.mygdx.armas.modificadores.AplicadorDeModificadores;
import com.mygdx.armas.modificadores.Modificadores;
import com.mygdx.entidades.Jugador;
import com.mygdx.utiles.recursos.Recursos;
import com.mygdx.utiles.sonidos.ListaSonidos;
import com.mygdx.utiles.sonidos.SonidosManager;

public class ModificadoresHUD extends HUD {

    private Table equipos, centro, modificadores;
    private Table resultado;
    private Table mensajeDeEspera;

    private DragAndDrop dragAndDrop;

    private Label textoEspera;
    private Timer.Task animacionTextoTask;

    private Texture texturaEquipoVacia = new Texture(Recursos.HOJA_HIERRO_0);
    private Texture texturaModificadorVacia = new Texture(Recursos.minerales.CARBON_POLVO);
    private Texture texturaResultadoVacia = new Texture(Recursos.HOJA_HIERRO_0);

    private Image imageTargetEquipamiento;
    private Image imageTargetModificador;
    private Image imageResultado;

    private Equipo equipoAModificar = null;
    private Modificadores modificadorAEquipar = null;
    private Equipo equipoModificado = null;
    
    private Jugador j;

    public ModificadoresHUD(Jugador j) {
        this.j = j;
        screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
        dragAndDrop = new DragAndDrop();
        construir();
    }

    @Override
    public void crearActores() {

        textoEspera = new Label("Modificando", labelStyle);
        textoEspera.setVisible(false);

        mensajeDeEspera = new Table();
        mensajeDeEspera.setVisible(false);
        mensajeDeEspera.add(textoEspera);

        resultado = new Table();

        imageTargetEquipamiento = new Image(texturaEquipoVacia);
        imageTargetModificador = new Image(texturaModificadorVacia);
        
        imageResultado = new Image(texturaResultadoVacia);
        imageResultado.setTouchable(Touchable.enabled);
        

        imageResultado.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if(equipoModificado != null) {            		
                imageResultado.setDrawable(new TextureRegionDrawable(texturaResultadoVacia));
                j.getInventarios().armas.add(equipoModificado);
                
                actualizarTablaEquipo();
                actualizarTablaModificadores();
            	}
            }
        });

        tabla = new Table();
        tabla.setFillParent(true);

        contenedor = new Table();

        equipos = new Table();
        centro = new Table();
        modificadores = new Table();

        crearTargets();
    }

    @Override
    public void poblarStage() {

        actualizarTablaEquipo();
        actualizarTablaModificadores();

        centro.add(imageTargetEquipamiento).size(64).pad(20);
        centro.row().pad(20, 0, 0, 0);
        centro.add(imageTargetModificador).size(64).pad(20);
        
        resultado.add(new Label("Resultado", labelStyle));
        resultado.row();
        resultado.add(imageResultado).size(96).pad(10);

        contenedor.add(equipos);
        contenedor.add(centro);
        contenedor.add(modificadores);
        contenedor.row();
        
        contenedor.add(resultado).colspan(3).padTop(15);
        contenedor.row();
        contenedor.add(mensajeDeEspera).colspan(3).padTop(10);

        tabla.add(contenedor);
        tabla.row();
        tabla.add(cerrarBtn);

        stage.addActor(tabla);
    }

    @Override
    public void mostrar() {
        if (!visible) {
            actualizarTablaEquipo();
            actualizarTablaModificadores();
            stage.getRoot().setTouchable(Touchable.enabled);
        }
        visible = true;
    }

    private void crearTargets() {

        Target equipamientoTarget = new Target(imageTargetEquipamiento) {

            @Override
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                return payload.getObject() instanceof Equipo;
            }

            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {
                equipoAModificar = (Equipo) payload.getObject();
                imageTargetEquipamiento.setDrawable(
                        new TextureRegionDrawable(equipoAModificar.getTextura())
                );
                reproducirSonidoSoltar();
                iniciarModificacion();
            }
        };

        Target modificadorTarget = new Target(imageTargetModificador) {

            @Override
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                return payload.getObject() instanceof Modificadores;
            }

            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {
                modificadorAEquipar = (Modificadores) payload.getObject();
                imageTargetModificador.setDrawable(
                        new TextureRegionDrawable(new Texture(modificadorAEquipar.ruta))
                );
                reproducirSonidoSoltar();
                iniciarModificacion();
            }
        };

        dragAndDrop.addTarget(equipamientoTarget);
        dragAndDrop.addTarget(modificadorTarget);
    }

    private void actualizarTablaEquipo() {

        equipos.clearChildren();
        int columnas = 0;

        for (final Equipo equipo : j.getInventarios().armas) {

            final Image img = new Image(equipo.getTextura());

            dragAndDrop.addSource(new Source(img) {
                @Override
                public Payload dragStart(InputEvent event, float x, float y, int pointer) {

                    Payload payload = new Payload();
                    payload.setObject(equipo);
                    payload.setDragActor(new Image(equipo.getTextura()));
                    return payload;
                }
            });

            equipos.add(img).size(64).pad(5);
            columnas++;
            if (columnas == 2) {
                columnas = 0;
                equipos.row();
            }
        }
    }

    private void actualizarTablaModificadores() {

        modificadores.clearChildren();
        int columnas = 0;

        for (final Modificadores mod : j.getInventarios().modificadores.obtenerModificadoresDisponibles()) {

            final Image img = new Image(new Texture(mod.ruta));

            dragAndDrop.addSource(new Source(img) {
                @Override
                public Payload dragStart(InputEvent event, float x, float y, int pointer) {

                    Payload payload = new Payload();
                    payload.setObject(mod);
                    payload.setDragActor(new Image(new Texture(mod.ruta)));
                    return payload;
                }
            });

            modificadores.add(img).size(48).pad(5);
            columnas++;
            if (columnas == 2) {
                columnas = 0;
                modificadores.row();
            }
        }
    }

    private void iniciarModificacion() {

        if (equipoAModificar == null || modificadorAEquipar == null) return;

        iniciarAnimacionTexto();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                terminarModificacion();
            }
        }, 3f);
    }

    private void terminarModificacion() {

        equipoAModificar.aplicarModificador(modificadorAEquipar);

        j.getInventarios().armas.remove(equipoAModificar);
        j.getInventarios().modificadores.consumir(modificadorAEquipar);

        detenerAnimacionTexto();

        imageResultado.setDrawable(
                new TextureRegionDrawable(equipoAModificar.getTextura())
        );

        imageTargetEquipamiento.setDrawable(
                new TextureRegionDrawable(texturaEquipoVacia)
        );
        imageTargetModificador.setDrawable(
                new TextureRegionDrawable(texturaModificadorVacia)
        );

        equipoModificado = equipoAModificar;

        equipoAModificar = null;
        modificadorAEquipar = null;
        
        actualizarTablaEquipo();
        actualizarTablaModificadores();
    }


    private void iniciarAnimacionTexto() {

        textoEspera.setVisible(true);
        mensajeDeEspera.setVisible(true);

        animacionTextoTask = new Timer.Task() {
            int puntos = 0;

            @Override
            public void run() {
                puntos = (puntos + 1) % 4;
                StringBuilder sb = new StringBuilder("Modificando");
                for (int i = 0; i < puntos; i++) sb.append(".");
                textoEspera.setText(sb.toString());
            }
        };

        Timer.schedule(animacionTextoTask, 0f, 0.5f);
    }

    private void detenerAnimacionTexto() {

        if (animacionTextoTask != null) {
            animacionTextoTask.cancel();
            animacionTextoTask = null;
        }

        textoEspera.setVisible(false);
        mensajeDeEspera.setVisible(false);
    }

    private void reproducirSonidoSoltar() {
        SonidosManager.reproducirSonido(ListaSonidos.MISION_RECIBIDA);
    }
}
