package net.obviam.starassault.model;

import com.badlogic.gdx.math.Vector2;

public class Level {

	private int width;
	private int height;
	private Block[][] blocks;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Block[][] getBlocks() {
		return blocks;
	}

	public void setBlocks(Block[][] blocks) {
		this.blocks = blocks;
	}

	public Level() {
		loadDemoLevel();
	}
	
	public Block get(int x, int y) {
		return blocks[x][y];
	}

	private void loadDemoLevel() {
		width = 10;
		height = 7;
		blocks = new Block[width][height];
		for (int col = 0; col < width; col++) {
			for (int row = 0; row < height; row++) {
				blocks[col][row] = null;
			}
		}
		for (int col = 0; col < 10; col++) {
			if((col % 2) == 0){
				blocks[col][0] = new Block(new Vector2(col, 0));
				blocks[col][1] = new Block(new Vector2(col, 1));
			}
		}		
	}
}
