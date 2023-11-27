package chess;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import pieces.Bishop;
import pieces.Knight;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Game {
	boolean whiteTurn = true;
	Coord enPassant = null;
	Coord whiteKing = new Coord(0, 4);
	Coord blackKing = new Coord(7, 4);
	Board board = new Board();
	GUI gui = new GUI(this.board);
	boolean running = true;
	Thread whileThread = new Thread(() -> {
		do {

		} while (this.running);
	});

	public void run(JLabel generalLabel, JLabel turnLabel, JLabel checkLabel, Coord mouseCoord, JFrame frame, Key key) {
		boolean canMove = true;

		while (canMove) {
			Coord king;
			checkLabel.setText(".");
			if (this.whiteTurn) {
				king = this.whiteKing;
				turnLabel.setText("White plays");
			} else {

				king = this.blackKing;
				turnLabel.setText("Black plays");
			}
			TestForCheck test = new TestForCheck(this.board, king);
			canMove = false;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (this.board.getTile(i, j).getPiece() != null
							&& this.board.getTile(i, j).getPiece().getIsWhite() == this.whiteTurn) {
						Move testForMove = new Move(this.board, new Coord(i, j), king);
						if (testForMove.getLegalMoves().size() > 0) {
							canMove = true;
							break;
						}
					}
				}
			}
			if (canMove) {
				if (test.getIsCheck()) {
					checkLabel.setText("Ckeck!");
				}

				boolean accept = false;
				ArrayList<Coord> legalMoves = null;
				int x = -1;
				int y = -1;
				int x2 = -1;
				int y2 = -1;
				while (!accept) {
					x = -1;
					y = -1;
					x2 = -1;
					y2 = -1;
					boolean success = false;
					while (!success) {
						generalLabel.setText("Choose piece!");
						if (mouseCoord != null) {
							x = (450 - mouseCoord.getY()) / 50;
							y = (mouseCoord.getX() - 100) / 50;
							frame.repaint();
							if (x > -1 && x < 8 && y > -1 && y < 8 && this.board.getTile(x, y).getPiece() != null
									&& this.board.getTile(x, y).getPiece().getIsWhite() == this.whiteTurn) {
								Move move = new Move(this.board, new Coord(x, y), king);
								legalMoves = move.getLegalMoves();
								if (legalMoves.size() < 1) {
									continue;
								}
								success = true;
								mouseCoord.set(new Coord(-1, -1));
							}
						}
					}

					generalLabel.setText("Choose move!");
					mouseCoord.set(new Coord(-1, -1));
					this.gui.setLegalMoves(legalMoves);
					boolean legal = false;
					while (!legal) {
						if (mouseCoord != null) {
							x2 = (450 - mouseCoord.getY()) / 50;
							y2 = (mouseCoord.getX() - 100) / 50;
							frame.repaint();
							if ((new Coord(x2, y2)).equalTo(new Coord(x, y))) {
								legal = true;
								mouseCoord.set(new Coord(-1, -1));
								this.gui.clearLegalMoves();
								break;
							}
							for (int j = 0; j < legalMoves.size(); j++) {
								if ((new Coord(x2, y2)).equalTo(legalMoves.get(j))) {
									legal = true;
									accept = true;
									mouseCoord.set(new Coord(-1, -1));
									this.gui.clearLegalMoves();
									break;
								}
							}
						}
					}
				}
				this.board.getTile(x2, y2).setPiece(this.board.getTile(x, y).getPiece());
				if (this.enPassant != null && this.board.getTile(x, y).getPiece().getType() == "Pawn"
						&& ((this.whiteTurn && this.enPassant.equalTo(new Coord(x2 - 1, y2)))
								|| (!this.whiteTurn && this.enPassant.equalTo(new Coord(x2 + 1, y2))))) {
					this.board.getTile(this.enPassant.getX(), this.enPassant.getY()).setPiece(null);
				}

				this.enPassant = null;
				if (this.board.getTile(x2, y2).getPiece().getType() == "Pawn") {
					if (x2 == x + 2 || x2 == x - 2) {
						this.board.getTile(x2, y2).getPiece().setEnPassantVictim(true);
						this.enPassant = new Coord(x2, y2);
					} else {
						this.board.getTile(x2, y2).getPiece().setEnPassantVictim(false);
					}
					if (this.board.getTile(x2, y2).getPiece().getType() == "Pawn" && (x2 == 7 || x2 == 0)) {
						generalLabel.setText("Promote your pawn, Press a key, q: Queen, r: Rook, n: Knight, b: Bishop");

						boolean success = false;
						key.set("");
						while (!success) {
							synchronized (this) {
								success = true;
								String promotion = key.get();
								if (promotion.equals("q")) {
									this.board.getTile(x2, y2).setPiece((Piece) new Queen(this.whiteTurn));
								} else if (promotion.equals("r")) {
									this.board.getTile(x2, y2).setPiece((Piece) new Rook(this.whiteTurn));
								} else if (promotion.equals("n")) {
									this.board.getTile(x2, y2).setPiece((Piece) new Knight(this.whiteTurn));
								} else if (promotion.equals("b")) {
									this.board.getTile(x2, y2).setPiece((Piece) new Bishop(this.whiteTurn));
								} else {
									success = false;
								}

							}
						}
					}
				}
				this.board.getTile(x2, y2).getPiece().setHasMoved();
				this.board.getTile(x, y).setPiece(null);
				if (this.board.getTile(x2, y2).getPiece().getType() == "King") {
					if (this.whiteTurn) {
						this.whiteKing = new Coord(x2, y2);
					} else {
						this.blackKing = new Coord(x2, y2);
					}

					if (y2 == y + 2) {
						this.board.getTile(x2, y2 - 1).setPiece(this.board.getTile(x2, 7).getPiece());
						this.board.getTile(x2, 7).setPiece(null);
					} else if (y2 == y - 2) {
						this.board.getTile(x2, y2 + 1).setPiece(this.board.getTile(x2, 0).getPiece());
						this.board.getTile(x2, 0).setPiece(null);
					}
				}
				this.whiteTurn = !this.whiteTurn;
				continue;
			}
			if (test.getIsCheck()) {
				if (this.whiteTurn) {
					checkLabel.setText("Checkmate! Black wins!");
					turnLabel.setText(".");
					generalLabel.setText(".");
					continue;
				}
				checkLabel.setText("Checkmate! White wins!");
				turnLabel.setText(".");
				generalLabel.setText(".");

				continue;
			}
			checkLabel.setText("Draw!");
			turnLabel.setText(".");
			generalLabel.setText(".");
		}
	}

	public GUI getGui() {
		return this.gui;
	}
}
