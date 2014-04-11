package at.d4m.platformjump;

import com.badlogic.gdx.Input.Keys;

public final class Constants {

	private Constants() {
		throw new UnsupportedOperationException();
	}

	public static final int JUMP_KEY = Keys.SPACE;
	public static final float PLATFORM_WIDTH = 10;
	public static final float PLATFORM_BUFFER = 10;
	public static final float PLATFORM_FILL_WHEN_BUFFER_EMPTY = 20;
	public static final int GRAVITY = -100;
	public static final int JUMP_FORCE = 300;
	public static final float JUMP_FORCE_VERTICAL_MULTIPLIER = 2.5f;
	public static final float HEIGHT_FIRST_PLATFORM = 15f;
	public static final int SIZE_BODY = 2;
	public static final int MAX_JUMP = 12;
	public static final int DISTANCE_BETWEEN = 20;
	public static final float MAX_HEIGHT = 19f;
	public static final float MIN_HEIGHT = 11f;
	public static final int BOB_OFFSET_FROM_LEFT = 25;

}
