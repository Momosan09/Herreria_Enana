package com.mygdx.utiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class DibujarFiguras {

	private ArrayList<Rectangle> rectangulos = new ArrayList<>();
	private ArrayList<String> colores = new ArrayList<>();
	private ShapeRenderer render = new ShapeRenderer();
	
	public void agregarRectangulo(Rectangle rectangulo, String color) {
		rectangulos.add(rectangulo);
		colores.add(color);
	}

    public void render() {
    	render.setAutoShapeType(true);
    render.begin(ShapeType.Filled);
    for (int i  = 0;i<rectangulos.size();i++) {
    	final Rectangle rectangulo = rectangulos.get(i);
    	render.setColor(Color.valueOf(colores.get(i)));
    	render.rect(rectangulo.x, rectangulo.y, rectangulo.width, rectangulo.height);
    }
    render.end();
    }
}
