package at.d4m.platformjump.world;

import java.util.LinkedList;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class PlatformEngine {

	private World world;
	private LinkedList<Platform> platforms;
	private int xPos = 0;

	public PlatformEngine(World w) {
		this.world = w;

		setUpQueue();

		addToQueue();

		setUpGround();
	}

	public Platform getNextPlatform() {
		Platform p = platforms.poll();
		if (platforms.size() < Constants.PLATFORM_BUFFER) {
			addToQueue();
		}
		return p;
	}

	private void setUpQueue() {
		platforms = new LinkedList<Platform>();

		platforms.add(new Platform(Constants.HEIGHT_FIRST_PLATFORM));

		addToQueue();
	}

	private void addToQueue() {
		for (int i = 0; i < Constants.PLATFORM_FILL_WHEN_BUFFER_EMPTY; i++) {
			platforms.add(new Platform(MathUtils.random(Constants.MIN_HEIGHT, Constants.MAX_HEIGHT)));
		}
	}

	private void setUpGround() {
		for (int i = 0; i < Constants.PLATFORM_BUFFER; i++) {
			addNext();
		}
	}

	public void addNext() {
		Platform plat = getNextPlatform();
		addPlatform(plat.height, xPos);
		xPos += Constants.DISTANCE_BETWEEN;
	}

	private void addPlatform(float height, float xposition) {
		// Create our body definition
		BodyDef groundBodyDef = new BodyDef();
		// Set its world position
		groundBodyDef.position.set(new Vector2(xposition, height));

		// Create a body from the defintion and add it to the world
		Body groundBody = world.createBody(groundBodyDef);

		// Create a polygon shape
		PolygonShape groundBox = new PolygonShape();
		// Set the polygon shape as a box which is twice the size of our view
		// port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(Constants.PLATFORM_WIDTH / 2, height);
		// Create a fixture from our polygon shape and add it to our ground body
		groundBody.createFixture(groundBox, 0.0f);
		// Clean up after ourselves
		groundBox.dispose();
	}

}
