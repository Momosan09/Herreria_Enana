package com.mygdx.utiles;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Animator implements ApplicationListener {

	//https://libgdx.com/wiki/graphics/2d/2d-animation
	// Constant rows and columns of the sprite sheet
	private static final int FRAME_COLS = 5, FRAME_ROWS = 1;

	// Objects used
	Animation<TextureRegion> animacion; // Must declare frame type (TextureRegion)
	Texture spriteSheet;
	String rutaSpriteSheet;
	
	Vector2 posicion;
	
	// A variable for tracking elapsed time for the animation
	float stateTime;
	
	public Animator(String rutaSpriteSheet, Vector2 posicion) {
		this.rutaSpriteSheet = rutaSpriteSheet;
		this.posicion = posicion;
	}

	@Override
	public void create() {

		// Load the sprite sheet as a Texture
		spriteSheet = new Texture(rutaSpriteSheet);

		// Use the split utility method to create a 2D array of TextureRegions. This is
		// possible because this sprite sheet contains frames of equal size and they are
		// all aligned.
		TextureRegion[][] tmp = TextureRegion.split(spriteSheet,
				spriteSheet.getWidth() / FRAME_COLS,
				spriteSheet.getHeight() / FRAME_ROWS);

		// Place the regions into a 1D array in the correct order, starting from the top
		// left, going across first. The Animation constructor requires a 1D array.
		TextureRegion[] Frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				Frames[index++] = tmp[i][j];
			}
		}

		// Initialize the Animation with the frame interval and array of frames
		animacion = new Animation<TextureRegion>(.7f, Frames);


		// time to 0
		stateTime = 0f;
	}

	@Override
	public void render() {
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // El problema que tenia de las animaciones era que en render limpiaba toda la pantalla
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

		// Get current frame of animation for the current stateTime
		TextureRegion currentFrame = animacion.getKeyFrame(stateTime, true);
		Render.batch.draw(currentFrame, posicion.x, posicion.y); // Draw current frame at (50, 50)

	}
	
	public void reset() {
		stateTime = 0f;
	}

	@Override
	public void dispose() {
		spriteSheet.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}