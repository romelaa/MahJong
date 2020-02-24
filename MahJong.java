/*
 * Romela Azizyan
 * CS 3230 - MahJong
*/

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Image;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import java.awt.BorderLayout;

public class MahJong extends JFrame {
	private int gameNumber;

	private JMenuBar menuBar;

	private static MahJongBoard MAH;

	private static int WIDTH = 1100;

	private static int HEIGHT = 850;

	private static final long serialVersionUID = 1L;

	public MahJong() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to quit?") == 0) {
					System.exit(0);
				}
			}
		});

		Random rnd = new Random();
		this.gameNumber = Math.abs(rnd.nextInt());

		setupGame();

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu game = createGameMenu();
		JMenu sound = createSoundMenu();
		JMenu move = createMoveMenu();
		JMenu help = createHelpMenu();

		menuBar.add(game);
		menuBar.add(sound);
		menuBar.add(move);
		menuBar.add(help); //not working

		setVisible(true);

	}

	public void newGame(int gameNumber) {
		this.gameNumber = gameNumber;
		this.setTitle("MahJong - " + this.gameNumber);
		remove(MAH);
		MAH = new MahJongBoard(gameNumber);
		MAH.setPreferredSize(new Dimension(1200, 700));
		getContentPane().add(MAH);
		Move.resetMyMoves();
		repaint();
		revalidate();
		menuBar.repaint();
		menuBar.revalidate();
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	public static MahJongBoard getMAH() {
		return MAH;
	}

	public static void setMAH(MahJongBoard mAH) {
		MAH = mAH;
	}

	public static void main(String[] args) {
		new MahJong();
	}

	private void setupGame() {
		this.setTitle("MahJong - Game# " + this.gameNumber);
		this.setBackground(new Color(255,226,191));
		setSize(WIDTH, HEIGHT);
		setResizable(false);

		MahJongGUI mGUI = new MahJongGUI(this.getWidth());
		mGUI.setPreferredSize(new Dimension(1100, 110));
		getContentPane().add(mGUI, BorderLayout.SOUTH);

		MAH = new MahJongBoard(gameNumber);
		MAH.setPreferredSize(new Dimension(1100, 600));
		getContentPane().add(MAH, BorderLayout.CENTER);
	}

	private JMenu createGameMenu() {
		JMenu game = new JMenu("Game");
		JMenuItem gamePlay = new JMenuItem("Play");
		JMenuItem gameRestart = new JMenuItem("Restart");
		JMenuItem gameNumbered = new JMenuItem("Numbered");
		JMenuItem gameExit = new JMenuItem("Exit");

		game.add(gamePlay);
		gamePlay.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to start a new game?") == 0) {
                Random rnd = new Random();
                MahJongGUI.getMGUI().newGame(Math.abs(rnd.nextInt()));
            }

        });
		game.add(gameRestart);
		gameRestart.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to start over?") == 0) {
                MahJongGUI.getMGUI().newGame(gameNumber);
            }
        });
		game.add(gameNumbered);
		gameNumbered.addActionListener(e -> {

            String gn = JOptionPane.showInputDialog("Enter a Game Number:");
            if (gn != null) {
                if (JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to start a new game?") == 0) {
                    if (gn.matches("\\d{1,10}")) {
                        gameNumber = Integer.parseInt(gn);
                        MahJongGUI.getMGUI().newGame(gameNumber);
                    }
                } else {
                    JOptionPane.showMessageDialog(getRootPane(), gn + " is not a valid game number!");
                }
            }

        });
		game.add(gameExit);
		gameExit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(getRootPane(), "Are you sure you want to quit?") == 0) {
                System.exit(0);
            }
        });

		return game;
	}

	private JMenu createSoundMenu() {
		JMenu sound = new JMenu("Sound");
		JCheckBoxMenuItem soundOff = new JCheckBoxMenuItem("Sound On");

		sound.add(soundOff);
		soundOff.setSelected(true);
		soundOff.addActionListener(e -> Sounds.setSoundon(((JCheckBoxMenuItem) e.getSource()).isSelected()));

		return sound;
	}

	private JMenu createMoveMenu() {
		JMenu move = new JMenu("Move");
		JMenuItem moveUndo = new JMenuItem("Undo");

		move.add(moveUndo);
		moveUndo.addActionListener(e -> MahJongGUI.getMGUI().removeMove());

		return move;
	}

	private JMenu createHelpMenu() {
		JMenu help = new JMenu("Help");
		JMenuItem helpOperation = new JMenuItem("Operation");
		JMenuItem helpRules = new JMenuItem("Game Rules");

		help.add(helpOperation);
		helpOperation.addActionListener(e -> new Help("/Operations.html", "Operations"));
		help.add(helpRules);
		helpRules.addActionListener(e -> new Help("/Rules.html", "Rules"));

		return help;
	}
}
