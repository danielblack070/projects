package chess;

import java.io.Serializable;

public class Coord implements Serializable {
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void set(Coord c) {
		this.x = c.getX();
		this.y = c.getY();
	}

	public boolean equalTo(Coord c) {
		if (getX() == c.getX() && getY() == c.getY())
			return true;
		return false;
	}
}
