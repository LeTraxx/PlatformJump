package at.d4m.platformjump.world;

import at.d4m.platformjump.Constants;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class PhysicsWorld implements Disposable, InputProcessor {

	private World world;
	private Body bob;
	private int jumped = 0;
	private PlatformEngine platforms;
	private int nextXPos = 0;
	private boolean lost = false;

	public PhysicsWorld() {
		setup();
		Gdx.input.setInputProcessor(this);
	}

	enum Keys {
		LEFT, RIGHT, JUMP
	}

	public static Map<Keys, Boolean> keys = new HashMap<PhysicsWorld.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
	};

	public World getWorld() {
		return world;
	}

	public Vector2 getBobPosition() {
		return bob.getPosition();
	}

	// public PhysicsWorld(PhysicsWorldEventListener listener) {
	// this();
	//
	//
	// this.listener = listener;
	//
	// }

	private void setup() {
		world = new World(new Vector2(0, Constants.GRAVITY), true);
		platforms = new PlatformEngine(world);

		setUpBob();
	}

	private void setUpBob() {
		BodyDef bobDef = new BodyDef();

		bobDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bobDef.position.set(0, Constants.HEIGHT_FIRST_PLATFORM * 2
				+ Constants.SIZE_BODY * 2);

		PolygonShape rect = new PolygonShape();

		rect.setAsBox(Constants.SIZE_BODY, Constants.SIZE_BODY);

		bob = world.createBody(bobDef);

		// Create a fixture definition to apply our shape to
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = rect;
		fixtureDef.density = 10;
		fixtureDef.friction = 1;
		fixtureDef.restitution = 0;

		// Create our fixture and attach it to the body
		bob.createFixture(fixtureDef);

		// Remember to dispose of any shapes after you're done with them!
		// BodyDef and FixtureDef don't need disposing, but shapes do.
		rect.dispose();
	}

	public void process() {
		Vector2 pos = getBobPosition();
		if (keys.get(Keys.JUMP)) {
			// bob.applyForceToCenter(0, 500, true);
			if (jumped <= Constants.MAX_JUMP) {
				bob.applyLinearImpulse(Constants.JUMP_FORCE,
						Constants.JUMP_FORCE
								* Constants.JUMP_FORCE_VERTICAL_MULTIPLIER,
						pos.x, pos.y, true);
				jumped++;
			}
		}
		if (keys.get(Keys.LEFT) && jumped == 0) {
			bob.applyLinearImpulse(-Constants.MOVE_SPEED, 0, pos.x, pos.y, true);
		}
		if (keys.get(Keys.RIGHT) && jumped == 0) {
			bob.applyLinearImpulse(Constants.MOVE_SPEED, 0, pos.x, pos.y, true);
		}
		if (bob.getLinearVelocity().equals(Vector2.Zero)) {
			jumped = 0;
		}
		if (pos.x >= nextXPos) {
			platforms.addNextPlatform();
			if (pos.x > 50)
				platforms.removeFirstPlatform();
			nextXPos += Constants.DISTANCE_BETWEEN;
		}
		if (pos.y <= 0) {
			lost = true;
		}

	}

	public void stepPhysics() {
		world.step(1 / 60f, 6, 2);
	}

	@Override
	public void dispose() {
		world.dispose();
	}

	// EventListener Stuff

	public boolean isLost() {
		return lost;
	}

	// Input Processor Methods

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Constants.JUMP_KEY) {
			keys.get(keys.put(Keys.JUMP, true));
		}
		if (keycode == Constants.LEFT_KEY) {
			keys.get(keys.put(Keys.LEFT, true));
		}
		if (keycode == Constants.RIGHT_KEY) {
			keys.get(keys.put(Keys.RIGHT, true));
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Constants.JUMP_KEY) {
			keys.get(keys.put(Keys.JUMP, false));
		}
		if (keycode == Constants.LEFT_KEY) {
			keys.get(keys.put(Keys.LEFT, false));
		}
		if (keycode == Constants.RIGHT_KEY) {
			keys.get(keys.put(Keys.RIGHT, false));
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (screenX < Gdx.graphics.getWidth() / 4
				&& screenY > (Gdx.graphics.getHeight() / 4) * 3) {
			keys.get(keys.put(Keys.LEFT, true));
		} else if (screenX > (Gdx.graphics.getWidth() / 4) * 3
				&& screenY > (Gdx.graphics.getHeight() / 4) * 3) {
			keys.get(keys.put(Keys.RIGHT, true));
		} else {
			keys.get(keys.put(Keys.JUMP, true));
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (screenX < Gdx.graphics.getWidth() / 4
				&& screenY > (Gdx.graphics.getHeight() / 4) * 3) {
			keys.get(keys.put(Keys.LEFT, false));
		} else if (screenX > (Gdx.graphics.getWidth() / 4) * 3
				&& screenY > (Gdx.graphics.getHeight() / 4) * 3) {
			keys.get(keys.put(Keys.RIGHT, false));
		} else {
			keys.get(keys.put(Keys.JUMP, false));
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
