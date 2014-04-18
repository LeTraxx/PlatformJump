package at.d4m.platformjump.fontstexturesnstuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager {

	private static BitmapFont font;

	public static BitmapFont getDefaultFont() {
		if (font == null)
			generateFont();
		return font;
	}

	private static void generateFont() {
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/calibrib.ttf"));
		font = gen.generateFont(Gdx.graphics.getHeight() / 12);
		gen.dispose();
	}

	public static void dispose() {
		if (font != null)
			font.dispose();
	}

}
