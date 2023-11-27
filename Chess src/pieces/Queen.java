package pieces;

import chess.Coord;
import java.util.ArrayList;
import java.util.Arrays;

public class Queen extends Piece {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Coord> moves = new ArrayList<>(
			Arrays.asList(new Coord[] { new Coord(-1, -1), new Coord(-1, 1), new Coord(1, 1), new Coord(1, -1),
					new Coord(-1, 0), new Coord(0, -1), new Coord(1, 0), new Coord(0, 1) }));

	public Queen(boolean isWhite) {
		super(isWhite, "Queen", moves);
		setValue(9.0D);
	}
}
