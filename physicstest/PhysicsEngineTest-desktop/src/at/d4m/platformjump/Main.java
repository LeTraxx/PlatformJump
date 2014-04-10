package at.d4m.platformjump;

import at.d4m.platformjump.PhysicsTest;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "PhysicsEngineTest";
		cfg.width = 800;
		cfg.height = 450;

		new LwjglApplication(new PhysicsTest(), cfg);
	}
}
