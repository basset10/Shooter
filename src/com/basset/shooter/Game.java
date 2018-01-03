package com.basset.shooter;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;

public class Game {


	static float xPosition = 1280/2;
	static float yPosition = 720/2;
	static float playerSpeed = 1.0f;
	static float xSpeed = 0.0f;
	static float ySpeed = 0.0f;
	

	static void initialize(){

	}

	static void update(float delta) {
		hvlDrawQuadc(xPosition, yPosition, 20, 20, Color.gray);
		movePlayer(delta);
	}


	static void movePlayer(float delta) {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			ySpeed = ySpeed - (delta * 100.0f);
			if(ySpeed >= 200) {
				ySpeed = 200;
			}
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			ySpeed = ySpeed + (delta * 100.0f);
			if(ySpeed <= -200) {
				ySpeed = -200;
			}
		}else {
			
			HvlMath.stepTowards(ySpeed, (10.0f*delta), 0.0f);
			
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			xSpeed = xSpeed - (delta * 100.0f);
			if(xSpeed <= -200) {
				xSpeed = -200;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			xSpeed = xSpeed + (delta * 100.0f);
			if(xSpeed >= 200) {
				xSpeed = 200;
			}
		}
		
		
		yPosition = HvlMath.limit(yPosition + (delta * ySpeed), 10, 710);
		xPosition = HvlMath.limit(xPosition + (delta * xSpeed), 10, 1270);

	}

}
