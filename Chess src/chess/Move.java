package chess;

import java.util.ArrayList;

public class Move {
	private ArrayList<Coord> legalMoves = new ArrayList<>();

	int id1;
	int id2;
	String type;
	ArrayList<Coord> possibleMoves;
	boolean isWhite;
	boolean hasMoved;
	boolean unlimited;
	Coord king;
	TestForCheck test;
	Board castlingTest;
	Board checkTest;

	public Move(Board board, Coord chosen, Coord king) {
		this.id1 = chosen.getX();
		this.id2 = chosen.getY();
		this.type = board.getTile(this.id1, this.id2).getPiece().getType();
		this.possibleMoves = new ArrayList<>(board.getTile(this.id1, this.id2).getPiece().getPossibleMoves());
		this.isWhite = board.getTile(this.id1, this.id2).getPiece().getIsWhite();
		this.hasMoved = board.getTile(this.id1, this.id2).getPiece().getHasMoved();
		this.unlimited = false;
		this.king = new Coord(king.getX(), king.getY());

		if (this.type == "Queen" || this.type == "Rook" || this.type == "Bishop")
			this.unlimited = true;

		if (this.type == "Pawn")
			if (this.id1 < 7 && this.isWhite) {
				if (board.getTile(this.id1 + 1, this.id2).getPiece() == null)
					this.possibleMoves.add(new Coord(1, 0));
				if (this.id1 < 6 && !this.hasMoved && this.possibleMoves.size() > 0
						&& board.getTile(this.id1 + 2, this.id2).getPiece() == null)
					this.possibleMoves.add(new Coord(2, 0));
				if (this.id2 > 0 && ((board.getTile(this.id1 + 1, this.id2 - 1).getPiece() != null
						&& !board.getTile(this.id1 + 1, this.id2 - 1).getPiece().getIsWhite())
						|| (board.getTile(this.id1, this.id2 - 1).getPiece() != null
								&& !board.getTile(this.id1, this.id2 - 1).getPiece().getIsWhite()
								&& board.getTile(this.id1, this.id2 - 1).getPiece().getEnPassantVictim())))
					this.possibleMoves.add(new Coord(1, -1));
				if (this.id2 < 7 && ((board.getTile(this.id1 + 1, this.id2 + 1).getPiece() != null
						&& !board.getTile(this.id1 + 1, this.id2 + 1).getPiece().getIsWhite())
						|| (board.getTile(this.id1, this.id2 + 1).getPiece() != null
								&& !board.getTile(this.id1, this.id2 + 1).getPiece().getIsWhite()
								&& board.getTile(this.id1, this.id2 + 1).getPiece().getEnPassantVictim())))
					this.possibleMoves.add(new Coord(1, 1));

			} else if (this.id1 > 0 && !this.isWhite) {
				if (board.getTile(this.id1 - 1, this.id2).getPiece() == null)
					this.possibleMoves.add(new Coord(-1, 0));
				if (this.id1 > 1 && !this.hasMoved && this.possibleMoves.size() > 0
						&& board.getTile(this.id1 - 2, this.id2).getPiece() == null)
					this.possibleMoves.add(new Coord(-2, 0));
				if (this.id2 > 0 && ((board.getTile(this.id1 - 1, this.id2 - 1).getPiece() != null
						&& board.getTile(this.id1 - 1, this.id2 - 1).getPiece().getIsWhite())
						|| (board.getTile(this.id1, this.id2 - 1).getPiece() != null
								&& board.getTile(this.id1, this.id2 - 1).getPiece().getIsWhite()
								&& board.getTile(this.id1, this.id2 - 1).getPiece().getEnPassantVictim())))
					this.possibleMoves.add(new Coord(-1, -1));
				if (this.id2 < 7 && ((board.getTile(this.id1 - 1, this.id2 + 1).getPiece() != null
						&& board.getTile(this.id1 - 1, this.id2 + 1).getPiece().getIsWhite())
						|| (board.getTile(this.id1, this.id2 + 1).getPiece() != null
								&& board.getTile(this.id1, this.id2 + 1).getPiece().getIsWhite()
								&& board.getTile(this.id1, this.id2 + 1).getPiece().getEnPassantVictim())))
					this.possibleMoves.add(new Coord(-1, 1));

			}
		if (this.type == "King") {
			this.test = new TestForCheck(board, king);
			if (!this.test.getIsCheck() && !this.hasMoved && board.getTile(this.id1, this.id2 - 4).getPiece() != null
					&& !board.getTile(this.id1, this.id2 - 4).getPiece().getHasMoved()
					&& board.getTile(this.id1, this.id2 - 3).getPiece() == null
					&& board.getTile(this.id1, this.id2 - 2).getPiece() == null
					&& board.getTile(this.id1, this.id2 - 1).getPiece() == null) {
				this.castlingTest = new Board(board);
				this.castlingTest.getTile(this.id1, this.id2 - 1)
						.setPiece(this.castlingTest.getTile(this.id1, this.id2).getPiece());
				this.castlingTest.getTile(this.id1, this.id2).setPiece(null);
				this.test = new TestForCheck(this.castlingTest, new Coord(this.id1, this.id2 - 1));
				if (!this.test.getIsCheck())
					this.possibleMoves.add(new Coord(0, -2));
			}
			if (!this.test.getIsCheck() && !this.hasMoved && board.getTile(this.id1, this.id2 + 3).getPiece() != null
					&& !board.getTile(this.id1, this.id2 + 3).getPiece().getHasMoved()
					&& board.getTile(this.id1, this.id2 + 2).getPiece() == null
					&& board.getTile(this.id1, this.id2 + 1).getPiece() == null) {
				this.castlingTest = new Board(board);
				this.castlingTest.getTile(this.id1, this.id2 + 1)
						.setPiece(this.castlingTest.getTile(this.id1, this.id2).getPiece());
				this.castlingTest.getTile(this.id1, this.id2).setPiece(null);
				this.test = new TestForCheck(this.castlingTest, new Coord(this.id1, this.id2 + 1));
				if (!this.test.getIsCheck())
					this.possibleMoves.add(new Coord(0, 2));
			}
		}
		for (int i = 0; i < this.possibleMoves.size(); i++) {
			int next1 = this.id1 + ((Coord) this.possibleMoves.get(i)).getX();
			int next2 = this.id2 + ((Coord) this.possibleMoves.get(i)).getY();
			while (next1 > -1 && next1 < 8 && next2 > -1 && next2 < 8 && (board.getTile(next1, next2).getPiece() == null
					|| board.getTile(next1, next2).getPiece().getIsWhite() != this.isWhite)) {
				this.checkTest = new Board(board);
				this.checkTest.getTile(next1, next2).setPiece(this.checkTest.getTile(this.id1, this.id2).getPiece());
				this.checkTest.getTile(this.id1, this.id2).setPiece(null);
				if (this.type == "King")
					king = new Coord(next1, next2);
				this.test = new TestForCheck(this.checkTest, king);
				if (!this.test.getIsCheck())
					this.legalMoves.add(new Coord(next1, next2));
				if (!this.unlimited || board.getTile(next1, next2).getPiece() != null)
					break;
				next1 += ((Coord) this.possibleMoves.get(i)).getX();
				next2 += ((Coord) this.possibleMoves.get(i)).getY();
			}
		}
	}

	public ArrayList<Coord> getLegalMoves() {
		return this.legalMoves;
	}
}
