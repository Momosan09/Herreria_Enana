package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Principal;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Texto{

	BitmapFont fuente;
	private Vector2 posicion;
	private String texto;
	GlyphLayout layout;

	public Texto(String rutaFuente, int dimension, Color color, boolean sombra) {
		posicion = new Vector2();
		FreeTypeFontGenerator generador = new FreeTypeFontGenerator(Gdx.files.internal(rutaFuente));
		FreeTypeFontParameter parametros = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parametros.size = dimension;
		parametros.color = color;

		if (sombra) {
			parametros.shadowColor = Color.BLACK;
			parametros.shadowOffsetX = 1;
			parametros.shadowOffsetY = 1;
		}
		fuente = generador.generateFont(parametros);
		layout = new GlyphLayout();

	}

	public void dibujar() {
		fuente.draw(Render.batch, texto, posicion.x, posicion.y);
	}

	public float getPosicionX() {
		return posicion.x;
	}

	public float getPosicionY() {
		return posicion.y;
	}

	public void setPosicion(float x, float y) {
		this.posicion.x = x;
		this.posicion.y = y;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
		layout.setText(fuente, texto);

	}
	
	public void setColor(Color color) {
		fuente.setColor(color);
	}

	public float getAncho() {
		return layout.width;
	}

	public float getAlto() {
		return layout.height;
	}

	public Vector2 getDimension() {
		return new Vector2(layout.width, layout.height);
	}

	public Vector2 getPosicion() {
		return new Vector2(posicion.x, posicion.y);
	}
	


}
