package chess;

import pieces.Piece;

public class TestForCheck {
	private boolean isCheck = false;

	public TestForCheck(Board board, Coord king) {
		boolean isWhite = board.getTile(king.getX(), king.getY()).getPiece().getIsWhite();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = board.getTile(i, j).getPiece();
				if (piece != null && piece.getIsWhite() != isWhite) {
					if (piece.getType() == "Rook" || piece.getType() == "Queen")
						if (i == king.getX()) {
							if (j + 1 == king.getY() || j - 1 == king.getY()) {
								this.isCheck = true;
								break;
							}
							if (j < king.getY()) {
								for (int k = j + 1; k < king.getY() && board.getTile(i, k).getPiece() == null; k++) {
									if (k + 1 == king.getY())
										this.isCheck = true;
								}
								if (this.isCheck)
									break;
							} else {
								for (int k = king.getY() + 1; k < j && board.getTile(i, k).getPiece() == null; k++) {
									if (k + 1 == j)
										this.isCheck = true;
								}
								if (this.isCheck)
									break;
							}
						} else if (j == king.getY()) {
							if (i + 1 == king.getX() || i - 1 == king.getX()) {
								this.isCheck = true;
								break;
							}
							if (i < king.getX()) {
								for (int k = i + 1; k < king.getX() && board.getTile(k, j).getPiece() == null; k++) {
									if (k + 1 == king.getX())
										this.isCheck = true;
								}
								if (this.isCheck)
									break;
							} else {
								for (int k = king.getX() + 1; k < i && board.getTile(k, j).getPiece() == null; k++) {
									if (k + 1 == i)
										this.isCheck = true;
								}
								if (this.isCheck)
									break;
							}
						}
					if (piece.getType() == "Bishop" || piece.getType() == "Queen") {
						if (i < king.getX()) {
							if (king.getX() - i == king.getY() - j) {
								if (i + 1 == king.getX() && j + 1 == king.getY()) {
									this.isCheck = true;

									break;
								}
								for (int k = 1; k < king.getX() - i
										&& board.getTile(i + k, j + k).getPiece() == null; k++) {
									if (k + 1 == king.getX() - i)
										this.isCheck = true;
								}
								if (this.isCheck) {
									break;
								}
							} else if (king.getX() - i == j - king.getY()) {
								if (i + 1 == king.getX() && j - 1 == king.getY()) {
									this.isCheck = true;

									break;
								}
								for (int k = 1; k < king.getX() - i
										&& board.getTile(i + k, j - k).getPiece() == null; k++) {
									if (k + 1 == king.getX() - i)
										this.isCheck = true;
								}
								if (this.isCheck) {
									break;
								}
							}

						} else if (i - king.getX() == king.getY() - j) {
							if (i - 1 == king.getX() && j + 1 == king.getY()) {
								this.isCheck = true;

								break;
							}
							for (int k = 1; k < i - king.getX()
									&& board.getTile(i - k, j + k).getPiece() == null; k++) {
								if (k + 1 == i - king.getX())
									this.isCheck = true;
							}
							if (this.isCheck) {
								break;
							}
						} else if (i - king.getX() == j - king.getY()) {
							if (i - 1 == king.getX() && j - 1 == king.getY()) {
								this.isCheck = true;

								break;
							}
							for (int k = 1; k < i - king.getX()
									&& board.getTile(i - k, j - k).getPiece() == null; k++) {
								if (k + 1 == i - king.getX())
									this.isCheck = true;
							}
							if (this.isCheck) {
								break;
							}
						}

					} else if (piece.getType() == "Knight") {
						for (int k = 0; k < piece.getPossibleMoves().size(); k++) {
							if (king.equalTo(new Coord(i + ((Coord) piece.getPossibleMoves().get(k)).getX(),
									j + ((Coord) piece.getPossibleMoves().get(k)).getY()))) {
								this.isCheck = true;
								break;
							}
						}
						if (this.isCheck)
							break;
					} else if (piece.getType() == "Pawn") {
						if (piece.getIsWhite()) {
							if (i + 1 == king.getX() && (j + 1 == king.getY() || j - 1 == king.getY())) {
								this.isCheck = true;

								break;
							}
						} else if (i - 1 == king.getX() && (j + 1 == king.getY() || j - 1 == king.getY())) {
							this.isCheck = true;

							break;
						}
					} else if (piece.getType() == "King" && king.getX() - i < 2 && i - king.getX() < 2
							&& king.getY() - j < 2 && j - king.getY() < 2) {
						this.isCheck = true;
						break;
					}
				}
			}
		}
	}

	public boolean getIsCheck() {
		return this.isCheck;
	}
}
