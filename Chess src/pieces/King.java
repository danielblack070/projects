package pieces;

import chess.Coord;
import java.util.ArrayList;
import java.util.Arrays;

public class King extends Piece {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Coord> moves = new ArrayList<>(
			Arrays.asList(new Coord[] { new Coord(-1, -1), new Coord(-1, 1), new Coord(1, 1), new Coord(1, -1),
					new Coord(-1, 0), new Coord(0, -1), new Coord(1, 0), new Coord(0, 1) }));
	private double value = Double.POSITIVE_INFINITY;

	public King(boolean isWhite) {
		super(isWhite, "King", moves);
		setValue(this.value);
	}
}
