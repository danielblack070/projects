package chess;

public class Key {
	volatile String key = "";

	public void set(String s) {
		this.key = s;
	}

	public String get() {
		return this.key;
	}
}
