package at.d4m.platformjump.screens;

import at.d4m.platformjump.Constants;
import at.d4m.platformjump.PhysicsTest;
import at.d4m.platformjump.renderer.GameRenderer;
import at.d4m.platformjump.renderer.Renderer;
import at.d4m.platformjump.world.PhysicsWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {

	PhysicsTest game;
	PhysicsWorld world;

	Renderer renderer;
	OrthographicCamera camera;

	public GameScreen(final PhysicsTest game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 16 * 6, 9 * 6);

		world = new PhysicsWorld();

		renderer = new GameRenderer();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Vector2 pos = world.getBobPosition();

		camera.position.x = pos.x + Constants.BOB_OFFSET_FROM_LEFT;

		camera.update();

		renderer.render(world, camera.combined);

		world.process();

		world.stepPhysics();

		if (world.isLost()) {
			int score = (int) world.getBobPosition().x;
			game.setScreen(new LoseScreen(game, score));
			dispose();
		}
	}

	@Override
	public void dispose() {
		world.dispose();
		renderer.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
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
