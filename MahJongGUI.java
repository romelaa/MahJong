/*
 * Romela Azizyan
 * CS 3230 - MahJong
*/
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;

public class MahJongGUI extends JPanel {
	private int tileOneIndex;

	private Tile tileOne;

	private JPanel buttons;

	private JPanel playedTiles;

	private JScrollPane tilesScrollPane;

	private static MahJongGUI MGUI;

	private static final long serialVersionUID = 1L;

	
	public MahJongGUI(int width) {
		setupMahJongGUI(width);

		Button undoButton = createUndoButton();
		undoButton.setFont(new Font("Baskerville Old Face", Font.BOLD, 15));
		undoButton.setBackground(new Color(245,235,253));
		
		Button newGame = createNewGameButton();
		newGame.setFont(new Font("Baskerville Old Face", Font.BOLD, 15));
		newGame.setBackground(new Color(245,235,253));
		
		Button playAgain = createPlayAgainButton();
		playAgain.setFont(new Font("Baskerville Old Face", Font.BOLD, 15));
		playAgain.setBackground(new Color(245,235,253));

		buttons = new JPanel();
		buttons.setBackground(new Color(252,232,229));
		buttons.setPreferredSize(new Dimension(200, 100));
		
		buttons.add(newGame);
		buttons.add(playAgain);
		buttons.add(undoButton);

		playedTiles = new JPanel();
		playedTiles.setLayout(new FlowLayout(FlowLayout.LEFT));
		playedTiles.setBackground(new Color(252,232,229));

		tilesScrollPane = new JScrollPane(playedTiles);
		tilesScrollPane.setPreferredSize(new Dimension(800, 100));

		JScrollBar jsb = createScrollBar();
		tilesScrollPane.setHorizontalScrollBar(jsb);

		tilesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tilesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

		add(tilesScrollPane);
		add(buttons);
	}

	protected void newGame(int gameNumber) {
		((MahJong)getRootPane().getParent()).newGame(gameNumber);
		setupGUI();
	}

	private void setupGUI() {
		resetTileOne();
		playedTiles.removeAll();
		revalidate();
		repaint();
	}

	private Button createUndoButton() {
		Button undoButton = new Button("Undo");
		undoButton.setPreferredSize(new Dimension(150, 25));
		undoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MahJongGUI.MGUI.removeMove();
			}
		});

		return undoButton;
	}

	private Button createNewGameButton() {
		Button newGame = new Button("New Game");
		newGame.setPreferredSize(new Dimension(150, 25));
		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to start a new game?") == 0) {
					Random rnd = new Random();
					MahJongGUI.getMGUI().newGame(Math.abs(rnd.nextInt()));
				}
			}
		});

		return newGame;
	}

	private Button createPlayAgainButton() {
		Button playAgain = new Button("Play Again");
		playAgain.setPreferredSize(new Dimension(150, 25));
		playAgain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to start over?") == 0) {
					MahJongGUI.getMGUI().newGame(((MahJong)getRootPane().getParent()).getGameNumber());
				}
			}
		});

		return playAgain;
	}

	private JScrollBar createScrollBar() {
		JScrollBar jsb = new JScrollBar();
		jsb.setBackground(new Color(252,232,229));
		jsb.setForeground(new Color(252,232,229));
		jsb.setOrientation(JScrollBar.HORIZONTAL);
		jsb.setPreferredSize(new Dimension(800, 10));

		return jsb;
	}

	private void setupMahJongGUI(int width) {
		MahJongGUI.setMGUI(this);
		this.setBackground(new Color(252,232,229));
		setBounds(0, 0, width, 110);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	public void setTileOne(int i) {
		tileOneIndex = i;
		try {
			tileOne = (Tile) MahJongBoard.getTILES()[tileOneIndex].clone();
		} catch (Exception e) {
			System.out.print("That didn't work");
		}
		tileOne.setUnselected();
		(MahJongBoard.getTILES()[tileOneIndex]).setUnselected();
		this.add(tileOne, BorderLayout.LINE_START);
		this.repaint();
		this.revalidate();
	}

	public void resetTileOne() {
		if (tileOne != null) {
			this.remove(tileOne);
			tileOne = null;
			tileOneIndex = -1;
			this.repaint();
			this.revalidate();
		}
	}
	
	public int getTileOneIndex(){return tileOneIndex;}

	public Tile getTileOne(){return tileOne;}

	public void addMove(Move move){Move.addMove(move, playedTiles);}

	public void removeMove(){Move.removeMove(playedTiles);}

	public static MahJongGUI getMGUI(){return MGUI;}

	public static void setMGUI(MahJongGUI mGUI){MGUI = mGUI;}
}
