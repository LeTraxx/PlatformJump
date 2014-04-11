package at.d4m.platformjump.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class PhysicsWorld implements Disposable {

	private World world;
	private Body bob;
	private int jumped = 0;
	private PlatformEngine platforms;
	private int nextXPos = 0;
	private boolean lost = false;

	public PhysicsWorld() {
		setup();
	}

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
		if (Gdx.input.isKeyPressed(Constants.JUMP_KEY) || Gdx.input.isTouched()) {
			// bob.applyForceToCenter(0, 500, true);
			if (jumped <= Constants.MAX_JUMP) {
				bob.applyLinearImpulse(Constants.JUMP_FORCE,
						Constants.JUMP_FORCE
								* Constants.JUMP_FORCE_VERTICAL_MULTIPLIER,
						pos.x, pos.y, true);
				jumped++;
			}
		}
		if (bob.getLinearVelocity().equals(Vector2.Zero)) {
			jumped = 0;
		}
		if (pos.x >= nextXPos) {
			platforms.addNext();
			nextXPos += Constants.DISTANCE_BETWEEN;
		}
		if (pos.y <= 0) {
			lost = true;
		}

	}

	public void stepPhysics() {
		world.step(1 / 60f, 6, 2);
	}

	public World getWorld() {
		return world;
	}

	public Vector2 getBobPosition() {
		return bob.getPosition();
	}

	@Override
	public void dispose() {
		world.dispose();
	}

	// EventListener Stuff

	public boolean isLost() {
		return lost;
	}
}
