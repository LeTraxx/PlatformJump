package at.d4m.platformjump.screens;

import at.d4m.platformjump.PhysicsTest;
import at.d4m.platformjump.renderer.DebugRenderer;
import at.d4m.platformjump.renderer.Renderer;
import at.d4m.platformjump.world.PhysicsWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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
		camera.setToOrtho(false, 96, 54);

		world = new PhysicsWorld(new PhysicsWorld.PhysicsWorldEventListener() {
			
			@Override
			public void onLose() {
				world.dispose();
				world = new PhysicsWorld(this);
				
			}
		});

		renderer = new DebugRenderer();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Vector2 pos = world.getBobPosition();

		camera.position.x = pos.x + 25;
		// camera.position.y = pos.y;

		camera.update();

		renderer.render(world.getWorld(), camera.combined);
		
		world.processInput();

		world.stepPhysics();
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

	@Override
	public void dispose() {
		world.dispose();
	}

}
