package at.d4m.platformjump.fontstexturesnstuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Scene2dStyleManager {

	private static Skin defaultSkin;
	private static LabelStyle defaultLabStyle;

	public static Skin getDefaultSkin() {
		if (defaultSkin == null)
			createSkin();
		return defaultSkin;
	}

	public static LabelStyle getDeafaultLabelStyle() {
		if (defaultLabStyle == null)
			createLabelStyle();
		return defaultLabStyle;
	}

	private static void createSkin() {
		defaultSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
	}

	private static void createLabelStyle() {
		defaultLabStyle = new LabelStyle(FontManager.getDefaultFont(),
				Color.WHITE);
	}

	public static void dispose() {
		if (defaultSkin != null)
			defaultSkin.dispose();
	}

}
