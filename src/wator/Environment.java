package wator;

import java.util.Observable;

public class Environment extends Observable {
	
	private Agent[][] map;
	
	private static final int WIDTH = 20;
	private static final int HEIGHT = 20;
	
	public Environment() {
		this.map = new Agent[WIDTH][HEIGHT];
		for(int i = 0; i < WIDTH; i++) {
			for(int j = 0; j < HEIGHT; j++) {
				this.map[i][j] = null;
			}
		}
	}

	public Agent[][] getMap() {
		return map;
	}
	
	public boolean isValidPosition(Position pos) {
		int x = pos.getRow();
		int y = pos.getCol();
		return x < WIDTH && x >= 0 && y < HEIGHT && y >= 0 ;
	}
}