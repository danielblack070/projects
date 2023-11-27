package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private Board board;
	private ArrayList<Coord> legalMoves = null;

	public GUI(Board board) {
		this.board = board;
	}

	public void paint(Graphics g) {
		Color white = new Color(200, 200, 150);
		Color black = new Color(100, 100, 50);
		Color whiteGreen = new Color(150, 250, 100);
		Color blackGreen = new Color(50, 150, 0);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				String source = "";
				if ((i + j) % 2 == 0) {
					g.setColor(black);
				} else {
					g.setColor(white);
				}

				g.fillRect(100 + j * 50, 50 + (7 - i) * 50, 50, 50);
				if (this.legalMoves != null) {
					for (int k = 0; k < this.legalMoves.size(); k++) {
						if (((Coord) this.legalMoves.get(k)).equalTo(new Coord(i, j))) {
							if ((i + j) % 2 == 0) {
								g.setColor(blackGreen);
							} else {

								g.setColor(whiteGreen);
							}
							g.fillRect(100 + j * 50, 50 + (7 - i) * 50, 50, 50);
						}
					}
				}
				if (this.board.getTile(i, j).getPiece() != null) {
					if (this.board.getTile(i, j).getPiece().getIsWhite()) {
						source = String.valueOf(source) + "l";
					} else {

						source = String.valueOf(source) + "d";
					}
					source = String.valueOf(source) + this.board.getTile(i, j).getPiece().getType() + ".png";
					try {
						Image image = ImageIO.read(getClass().getClassLoader().getResource(source));
						g.drawImage(image, 100 + j * 50, 50 + (7 - i) * 50, 50, 50, null);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void setLegalMoves(ArrayList<Coord> legalMoves) {
		this.legalMoves = legalMoves;
	}

	public void clearLegalMoves() {
		this.legalMoves = null;
	}
}
