package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DibujarFiguras {

    private ShapeRenderer render; // Cambio: No es estática

    public DibujarFiguras() {
        render = new ShapeRenderer(); // Inicializa render en el constructor
    }

    public void dibujarRectanguloLleno(float posX, float posY, float ancho, float alto, Color color) {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        render.begin(ShapeRenderer.ShapeType.Filled);
        render.rect(posX, posY, ancho, alto);
        render.setColor(color);
        render.end();
    }
}
