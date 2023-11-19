package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class DibujarFiguras {

    
    public static void dibujarRectanguloLleno(float posX, float posY, float ancho, float alto, Color color) {
        Gdx.gl.glEnable(GL30.GL_BLEND);
        Render.shapeR.begin(ShapeRenderer.ShapeType.Filled);
        Render.shapeR.rect(posX, posY, ancho, alto);
        Render.shapeR.setColor(color);
        Render.shapeR.end();
    }
}
