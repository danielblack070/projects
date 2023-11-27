package pieces;

import chess.Coord;
import java.util.ArrayList;

public class Pawn extends Piece {
	private static final long serialVersionUID = 1L;
	private static ArrayList<Coord> moves = new ArrayList<>();

	public Pawn(boolean isWhite) {
		super(isWhite, "Pawn", moves);
		setValue(1.0D);
	}
}
