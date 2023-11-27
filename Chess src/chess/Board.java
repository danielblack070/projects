package chess;

import java.io.Serializable;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Board implements Serializable {
	private Tile[][] tiles = new Tile[8][8];
	private static final long serialVersionUID = 1L;

	public Board() {
		int i;
		for (i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.tiles[i][j] = new Tile();
			}
		}

		this.tiles[0][0].setPiece((Piece) new Rook(true));
		this.tiles[0][1].setPiece((Piece) new Knight(true));
		this.tiles[0][2].setPiece((Piece) new Bishop(true));
		this.tiles[0][3].setPiece((Piece) new Queen(true));
		this.tiles[0][4].setPiece((Piece) new King(true));
		this.tiles[0][5].setPiece((Piece) new Bishop(true));
		this.tiles[0][6].setPiece((Piece) new Knight(true));
		this.tiles[0][7].setPiece((Piece) new Rook(true));
		for (i = 0; i < 8; i++) {
			this.tiles[1][i].setPiece((Piece) new Pawn(true));
		}
		for (i = 0; i < 8; i++) {
			this.tiles[6][i].setPiece((Piece) new Pawn(false));
		}
		this.tiles[7][0].setPiece((Piece) new Rook(false));
		this.tiles[7][1].setPiece((Piece) new Knight(false));
		this.tiles[7][2].setPiece((Piece) new Bishop(false));
		this.tiles[7][3].setPiece((Piece) new Queen(false));
		this.tiles[7][4].setPiece((Piece) new King(false));
		this.tiles[7][5].setPiece((Piece) new Bishop(false));
		this.tiles[7][6].setPiece((Piece) new Knight(false));
		this.tiles[7][7].setPiece((Piece) new Rook(false));
	}

	public Board(Board board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.tiles[i][j] = new Tile();
				this.tiles[i][j].setPiece(board.getTile(i, j).getPiece());
			}
		}
	}

	public Tile getTile(int id1, int id2) {
		return this.tiles[id1][id2];
	}
}
