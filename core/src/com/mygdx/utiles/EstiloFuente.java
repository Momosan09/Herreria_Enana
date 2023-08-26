package com.mygdx.utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class EstiloFuente {
	public Label.LabelStyle labelStyle;
	
	public Label.LabelStyle generarFuente (int tamano, String hex, boolean sombra) {
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(Recursos.FUENTE_TEMPORAL));
	    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
	    
	    parameter.size = tamano;
	    parameter.color = Color.valueOf(hex);
	    parameter.borderWidth = 1;
	    if(sombra) {
	    	parameter.shadowOffsetX = 3;
	    	parameter.shadowOffsetY = 3;
	    }
	    
	    BitmapFont font24 = generator.generateFont(parameter); // tamaño de la fuente 24 pixeles
	    generator.dispose();
	 
	    labelStyle = new Label.LabelStyle();
	    labelStyle.font = font24;
	    return labelStyle;
	}
	
}
