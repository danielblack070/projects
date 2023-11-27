package pieces;

import chess.Coord;
import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends Piece {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Coord> moves = new ArrayList<>(
			Arrays.asList(new Coord[] { new Coord(-2, 1), new Coord(-2, -1), new Coord(2, 1), new Coord(2, -1),
					new Coord(-1, 2), new Coord(-1, -2), new Coord(1, 2), new Coord(1, -2) }));

	public Knight(boolean isWhite) {
		super(isWhite, "Knight", moves);
		setValue(3.0D);
	}
}
