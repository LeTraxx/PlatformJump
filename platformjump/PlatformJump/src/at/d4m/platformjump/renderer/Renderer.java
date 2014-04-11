package at.d4m.platformjump.renderer;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.World;

public interface Renderer {

	public void render(World world, Matrix4 projMatrix);
	public void dispose();

}
