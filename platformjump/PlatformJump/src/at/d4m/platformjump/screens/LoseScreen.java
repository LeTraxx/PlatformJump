package at.d4m.platformjump.screens;

import at.d4m.platformjump.PhysicsTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class LoseScreen implements Screen {

	@SuppressWarnings("unused")
	// used in click listener from start new button
	private final PhysicsTest game;

	private Stage stage;
	private Skin defaultSkin;

	public LoseScreen(final PhysicsTest game, final int score) {
		this.game = game;

		defaultSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		stage = new Stage();

		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		Label lab = new Label("Lose!\nPunkte: " + score, defaultSkin);
		table.add(lab);

		TextButton startNewButton = new TextButton("Try Again", defaultSkin);
		table.add(startNewButton);

		startNewButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				dispose();
				game.setScreen(new GameScreen(game));
			}

		});

		TextButton toMenuButton = new TextButton("Menu", defaultSkin);
		table.add(toMenuButton);

		toMenuButton.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(new MenuScreen(game));
				dispose();
			}

		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Gdx.input.setInputProcessor(stage);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		// Table.drawDebug(stage);

	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

	}

	@Override
	public void dispose() {
		stage.dispose();
		defaultSkin.dispose();
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
