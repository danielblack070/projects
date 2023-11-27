package chess;

import java.io.Serializable;
import pieces.Piece;

public class Tile implements Serializable {
	private static final long serialVersionUID = 1L;
	private Piece pieceOnTile = null;

	public void setPiece(Piece piece) {
		this.pieceOnTile = piece;
	}

	public Piece getPiece() {
		return this.pieceOnTile;
	}
}
