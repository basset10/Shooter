package com.basset.shooter;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuadc;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.input.HvlInput;
import com.osreboot.ridhvl.painter.HvlCursor;

public class Screens {

	public static final int SCREEN_MAIN = 0, SCREEN_GAME = 1, SCREEN_CREDITS = 2;
	public static final float BUTTON_WIDTH = 384f, BUTTON_HEIGHT = 64f, STATIC_HEIGHT = 2048f;

	public static int currentScreen = SCREEN_MAIN;
	public static HvlInput mouseClick;
	public static boolean releaseFrame = false;

	private static ArrayList<HvlCoord2D> buttonsMain, buttonsCredits;
	private static ArrayList<String> buttonTextMain, buttonTextCredits;

	public static void initialize(){
		Game.initialize();
		
		mouseClick = new HvlInput(new HvlInput.InputFilter(){
			@Override
			public float getCurrentOutput() {
				return Mouse.isButtonDown(0) ? 1f : 0f;
			}
		});
		mouseClick.setReleasedAction(new HvlAction1<HvlInput>(){
			@Override
			public void run(HvlInput aArg) {
				releaseFrame = true;
			}
		});

		buttonsMain = new ArrayList<>();
		buttonTextMain = new ArrayList<>();
		buttonsMain.add(new HvlCoord2D(Display.getWidth()/2, Display.getHeight()/2));
		buttonTextMain.add("play");
		buttonsMain.add(new HvlCoord2D(Display.getWidth()/2, Display.getHeight()/8*5));
		buttonTextMain.add("credits");
		
		buttonsCredits = new ArrayList<>();
		buttonTextCredits = new ArrayList<>();
		buttonsCredits.add(new HvlCoord2D(Display.getWidth()/2, Display.getHeight()/8*5));
		buttonTextCredits.add("no one cares");
		
	}

	public static void update(float delta){
		if(currentScreen == SCREEN_GAME){
			Game.update(delta);
		}else if(currentScreen == SCREEN_CREDITS){
			updateCredits(delta);
		}else{
			updateMain(delta);
		}
		releaseFrame = false;
	}

	/**
	 * Update the main screen
	 */
	private static void updateMain(float delta){
		drawStatic(delta);
		Main.font.drawWordc("R3KT", Display.getWidth()/2, Display.getHeight()/4, new Color(0f, 0f, 0.5f));
		int hover = updateButtons(buttonsMain, buttonTextMain);
		if(hover != -1){
			if(releaseFrame){
				if(hover == 0){//if the 0 button is pressed
					currentScreen = SCREEN_GAME;
				}else if(hover == 1){//if the 1 button is pressed
					currentScreen = SCREEN_CREDITS;
				}
			}
		}
	}

	/**
	 * Update the credits screen
	 */
	private static void updateCredits(float delta){
		Main.font.drawWordc("CREDITS", Display.getWidth()/2, Display.getHeight()/4, new Color(0f, 0f, 0.5f), 0.5f);
		Main.font.drawWordc("os_reboot: 99.99 percent of the code", Display.getWidth()/2, Display.getHeight()/8*3, new Color(0f, 0f, 0.5f), 0.25f);
		Main.font.drawWordc("basset10: 0.01 percent of the code", Display.getWidth()/2, Display.getHeight()/8*4, new Color(0f, 0f, 0.5f), 0.25f);
		drawStatic(delta);
		int hover = updateButtons(buttonsCredits, buttonTextCredits);
		if(hover != -1){
			if(releaseFrame){
				if(hover == 0){//if the 0 button is pressed
					currentScreen = SCREEN_MAIN;
				}
			}
		}
	}

	/**
	 * Draws all buttons, and returns which button is being hovered over (-1 if none)
	 */
	private static int updateButtons(ArrayList<HvlCoord2D> buttons, ArrayList<String> text){
		int hoverIndex = -1;//hover index gets set if a button is hovered over
		int textIndex = 0;//text index counts so that the text array can be referenced
		for(HvlCoord2D c : buttons){
			if(HvlCursor.getCursorX() > c.x - (BUTTON_WIDTH/2) && HvlCursor.getCursorX() < c.x + (BUTTON_WIDTH/2) &&
					HvlCursor.getCursorY() > c.y - (BUTTON_HEIGHT/2) && HvlCursor.getCursorY() < c.y + (BUTTON_HEIGHT/2)){
				//if the button is hovered over
				hvlDrawQuadc(c.x, c.y, BUTTON_WIDTH, BUTTON_HEIGHT, new Color(0f, 0f, 0.5f));
				Main.font.drawWordc("[ " + text.get(textIndex) + " ]", c.x, c.y, Color.black, 0.25f);
				hoverIndex = textIndex;
			}else{
				//if the button isn't hovered over
				hvlDrawQuadc(c.x, c.y, BUTTON_WIDTH, BUTTON_HEIGHT, Color.blue);
				Main.font.drawWordc("[" + text.get(textIndex) + "]", c.x, c.y, Color.black, 0.25f);
			}
			textIndex++;
		}
		return hoverIndex;
	}
	
	public static void drawStatic(float delta){
		hvlRotate(Display.getWidth()/2, Display.getHeight()/2, 15);
		int count = HvlMath.randomIntBetween(0, 8);
		for(int i = 0; i < count; i++){
			float value = (float)Math.pow(Math.random(), 5.0);
			hvlDrawQuadc(HvlMath.randomFloatBetween((Display.getWidth()/2) - (STATIC_HEIGHT/2), (Display.getWidth()/2) + (STATIC_HEIGHT/2)), Display.getHeight()/2, 
					HvlMath.randomFloatBetween(1f, 16f), STATIC_HEIGHT, new Color(0f, 0f, value));
		}
		hvlResetRotation();
	}

}
