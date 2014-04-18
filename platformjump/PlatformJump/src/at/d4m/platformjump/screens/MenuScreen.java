package at.d4m.platformjump.screens;

import at.d4m.platformjump.PhysicsTest;
import at.d4m.platformjump.fontstexturesnstuff.Scene2dStyleManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen implements Screen {

	final PhysicsTest game;

	// private OrthographicCamera camera;

	private Stage stage;

	public MenuScreen(final PhysicsTest rain) {
		this.game = rain;
		
		stage = new Stage();

		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		Label startGameLabel = new Label("PlatformJump", Scene2dStyleManager.getDeafaultLabelStyle());
		table.add(startGameLabel);

		TextButton testButton = new TextButton("Start Game",  Scene2dStyleManager.getDefaultSkin());
		table.add(testButton);

		testButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				dispose();
				game.setScreen(new GameScreen(game));
			}

		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Gdx.input.setInputProcessor(stage);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

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
