package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	static Key key = new Key();

	public static void main(String[] args) {
		JFrame frame = new JFrame("Chess");
		frame.setSize(600, 600);
		frame.setLocationRelativeTo((Component) null);
		frame.setDefaultCloseOperation(3);
		frame.setResizable(false);
		JPanel chessPanel = new JPanel(new BorderLayout());
		chessPanel.setBackground(Color.lightGray);
		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.setBackground(Color.lightGray);
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBackground(Color.lightGray);
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(textPanel, "North");
		mainPanel.add(chessPanel, "Center");
		mainPanel.add(buttonPanel, "South");
		mainPanel.setBackground(Color.lightGray);
		frame.add(mainPanel, "Center");
		JLabel turnLabel = new JLabel(".", 0);
		JLabel checkLabel = new JLabel(".", 0);
		checkLabel.setForeground(Color.red);
		JLabel generalLabel = new JLabel(".", 0);
		textPanel.add(turnLabel, "North");
		textPanel.add(checkLabel, "Center");
		textPanel.add(generalLabel, "South");
		final Coord mouseCoord = new Coord(-1, -1);
		frame.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				synchronized (this) {
					Main.key.set(String.valueOf(e.getKeyChar()));
					notifyAll();
				}
			}
		});
		frame.setFocusable(true);
		frame.requestFocusInWindow();

		chessPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseCoord.set(new Coord(e.getX(), e.getY()));
			}
		});
		JButton exitButton = new JButton("Exit");

		buttonPanel.add(exitButton);

		Game game = new Game();

		chessPanel.add(game.getGui(), "Center");
		frame.setVisible(true);

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		game.run(generalLabel, turnLabel, checkLabel, mouseCoord, frame, key);
	}
}
