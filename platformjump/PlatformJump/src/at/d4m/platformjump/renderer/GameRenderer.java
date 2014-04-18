package at.d4m.platformjump.renderer;

import at.d4m.platformjump.fontstexturesnstuff.FontManager;
import at.d4m.platformjump.world.PhysicsWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;

public class GameRenderer extends Box2DDebugRenderer implements Renderer,
		Disposable {

	BitmapFont font;
	SpriteBatch batch;

	public GameRenderer() {
		super();

		batch = new SpriteBatch();
		font = FontManager.getDefaultFont();
	}

	@Override
	public void render(PhysicsWorld world, Matrix4 projMatrix) {
		batch.begin();

		font.draw(batch, "Score: " + (int) world.getBobPosition().x, 0,
				Gdx.graphics.getHeight());

		batch.end();

		super.render(world.getWorld(), projMatrix);
	}

	@Override
	public void dispose() {
		//font is disposed by font manager
		batch.dispose();

		super.dispose();
	}

}
