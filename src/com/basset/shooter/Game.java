package com.basset.shooter;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;

public class Game {

	static final float MAX_SPEED = 300;
	static final float ACCELERATION = 3500.0f;
	static final int PLAYER_SIZE = 10;
	static float xPosition = 1280/2;
	static float yPosition = 720/2;
	static float playerSpeed = 1.0f;
	static float xSpeed = 0.0f;
	static float ySpeed = 0.0f;
	

	static void initialize(){

	}

	static void update(float delta) {
		movePlayer(delta);
		hvlDrawQuadc(xPosition, yPosition, PLAYER_SIZE, PLAYER_SIZE, Color.gray);
		hvlDrawQuadc(500, 250, 50, 50, Color.red);
	}

	static void movePlayer(float delta) {
		//Vertical Movement
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			ySpeed = ySpeed - (delta * ACCELERATION);
			if(ySpeed <= -MAX_SPEED) {
				ySpeed = -MAX_SPEED;
			}
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			ySpeed = ySpeed + (delta * ACCELERATION);
			if(ySpeed >= MAX_SPEED) {
				ySpeed = MAX_SPEED;
			}
		}else{
			ySpeed = HvlMath.stepTowards(ySpeed, ACCELERATION*delta, 0.0f);
		}
		
		//Horizontal Movement
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			xSpeed = xSpeed - (delta * ACCELERATION);
			if(xSpeed <= -MAX_SPEED) {
				xSpeed = -MAX_SPEED;
			}
		}else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			xSpeed = xSpeed + (delta * ACCELERATION);
			if(xSpeed >= MAX_SPEED) {
				xSpeed = MAX_SPEED;
			}
		}else{
			xSpeed = HvlMath.stepTowards(xSpeed, ACCELERATION*delta, 0.0f);
		}
		yPosition = HvlMath.limit(yPosition + (delta * ySpeed), PLAYER_SIZE/2, 720 - (PLAYER_SIZE/2));
		xPosition = HvlMath.limit(xPosition + (delta * xSpeed), PLAYER_SIZE/2, 1280 - (PLAYER_SIZE/2));
	}
	
	static void restart(){
		xPosition = 1280/2;
		yPosition = 720/2;
		playerSpeed = 1.0f;
		xSpeed = 0.0f;
		ySpeed = 0.0f;
	}
	
}
