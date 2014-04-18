package at.d4m.platformjump;

import at.d4m.platformjump.fontstexturesnstuff.FontManager;
import at.d4m.platformjump.fontstexturesnstuff.Scene2dStyleManager;
import at.d4m.platformjump.screens.MenuScreen;

import com.badlogic.gdx.Game;

public class PhysicsTest extends Game {

	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		FontManager.dispose();
		Scene2dStyleManager.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
