package at.d4m.platformjump;

import at.d4m.platformjump.screens.GameScreen;

import com.badlogic.gdx.Game;

public class PhysicsTest extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {

	}

	@Override
	public void render() {
		// Gdx.gl.glClearColor(1, 1, 1, 1);
		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
