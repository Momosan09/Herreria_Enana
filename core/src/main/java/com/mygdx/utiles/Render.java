package com.mygdx.utiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import box2dLight.RayHandler;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class Render {

	public static SpriteBatch batch;
	public static RayHandler rayHandler;
	public static  OrthogonalTiledMapRenderer tiledMapRenderer;
	public static ShapeDrawer shapeDr;
	public static ShapeRenderer shapeR = new ShapeRenderer();
	
	
	public static void iniciarShapeDrawer() {	
		if(batch != null) {
			
	Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
	pixmap.setColor(Color.WHITE);
	pixmap.drawPixel(0, 0);
	Texture texture = new Texture(pixmap); //remember to dispose of later
	pixmap.dispose();
	TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
	
	shapeDr = new ShapeDrawer(batch, region);
		}
	}
}
