package com.basset.shooter;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;


public class Main extends HvlTemplateInteg2D{
	
	public static void main(String[] args) {
		new Main();
	}

	public static HvlFontPainter2D font;
	
	public Main() {
		super(200, 1280, 720, "r3kt", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize() {
		getTextureLoader().loadResource("INOF");
		font = new HvlFontPainter2D(getTexture(0), HvlFontPainter2D.Preset.FP_INOFFICIAL);
		font.setCharSpacing(8f);
		
		Screens.initialize();
	}

	@Override
	public void update(float delta) {
		Screens.update(delta);
	}
	
}
