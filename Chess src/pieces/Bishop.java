package pieces;

import chess.Coord;
import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends Piece {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Coord> moves = new ArrayList<>(
			Arrays.asList(new Coord[] { new Coord(-1, -1), new Coord(-1, 1), new Coord(1, 1), new Coord(1, -1) }));

	public Bishop(boolean isWhite) {
		super(isWhite, "Bishop", moves);
		setValue(3.0D);
	}
}