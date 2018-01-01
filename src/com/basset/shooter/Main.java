package com.basset.shooter;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;


public class Main extends HvlTemplateInteg2D{
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		super(200, 1280, 720, "r3kt", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize() {
		Game.initialize();
		
	}

	@Override
	public void update(float delta) {
		Game.update(delta);
	}
	
}
