package at.d4m.platformjump.renderer;

import at.d4m.platformjump.world.PhysicsWorld;

import com.badlogic.gdx.math.Matrix4;

public interface Renderer {

	public void render(PhysicsWorld world, Matrix4 projMatrix);
	public void dispose();

}
