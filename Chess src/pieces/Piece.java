package pieces;

import chess.Coord;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Serializable {
	private static final long serialVersionUID = 1L;
	private double value;
	private boolean isWhite;
	private boolean hasMoved;
	private String type;
	private ArrayList<Coord> possibleMoves;
	private boolean enPassantVictim;

	public Piece(boolean isWhite, String type, ArrayList<Coord> possibleMoves) {
		this.isWhite = isWhite;
		this.hasMoved = false;
		this.type = type;
		this.possibleMoves = possibleMoves;
		this.enPassantVictim = false;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return this.value;
	}

	public boolean getIsWhite() {
		return this.isWhite;
	}

	public void setHasMoved() {
		this.hasMoved = true;
	}

	public boolean getHasMoved() {
		return this.hasMoved;
	}

	public String getType() {
		return this.type;
	}

	public ArrayList<Coord> getPossibleMoves() {
		return this.possibleMoves;
	}

	public void setEnPassantVictim(boolean enPassantVictim) {
		this.enPassantVictim = enPassantVictim;
	}

	public boolean getEnPassantVictim() {
		return this.enPassantVictim;
	}
}