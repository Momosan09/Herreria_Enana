package com.mygdx.utiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import box2dLight.RayHandler;

public class Render {

	public static SpriteBatch batch;
	public static RayHandler rayHandler;
	public static  OrthogonalTiledMapRenderer tiledMapRenderer;
	public static ShapeRenderer shapeR = new ShapeRenderer();
}
