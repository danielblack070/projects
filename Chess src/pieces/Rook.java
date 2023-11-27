package pieces;

import chess.Coord;
import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends Piece {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Coord> moves = new ArrayList<>(
			Arrays.asList(new Coord[] { new Coord(-1, 0), new Coord(0, -1), new Coord(1, 0), new Coord(0, 1) }));

	public Rook(boolean isWhite) {
		super(isWhite, "Rook", moves);
		setValue(5.0D);
	}
}