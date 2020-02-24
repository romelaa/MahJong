/*
 * Romela Azizyan
 * CS 3230 - MahJong
*/
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class MahJongBoard extends JPanel implements MouseListener {
	private static Tile[] TILES = new Tile[144];

	private static Image img;

	private static Sounds sound = new Sounds();

	private static Fireworks fire;

	private static final long serialVersionUID = 1L;

	public MahJongBoard(int gameNumber) {
		fire = new Fireworks(this);
		TILES = createTiles(gameNumber);

		this.setBackground(new Color(255,226,191));
		MahJongModel model = new MahJongModel();
		MahJongModel.setTileNumber(0);
		MahJongModel.setCounter(0);

		model.preferredLayoutSize(this);
		this.setLayout(model);
		for (Tile t : TILES) {
			add(t);
			t.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Tile sT = (Tile) e.getSource();
					playClick(sT);
				}

			});
		}
		addMouseListener(this);
		setVisible(true);
		this.repaint();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		img = getToolkit().getImage(getClass().getResource("images/dragon_bg.png"));
		g.drawImage(img, this.getWidth() / 2 - img.getWidth(this) / 2,
				50 + this.getHeight() / 2 - img.getHeight(this) / 2, this);
	}

	private Tile[] createTiles(int gameNumber) {
		ArrayList<Tile> rtn = new ArrayList<Tile>();
		for (int c = 0; c < 4; c++) {
			for (int i = 1; i < 10; i++) {
				rtn.add(new CharacterTile(Character.forDigit(i, 10)));
			}

			rtn.add(new CharacterTile('N'));
			rtn.add(new CharacterTile('S'));
			rtn.add(new CharacterTile('E'));
			rtn.add(new CharacterTile('W'));

			rtn.add(new CharacterTile('C'));

			rtn.add(new CharacterTile('F'));

			rtn.add(new WhiteDragonTile());

			for (int i = 1; i < 10; i++) {
				rtn.add(new CircleTile(i));
			}

			rtn.add(new Bamboo1Tile());

			for (int i = 2; i < 10; i++) {
				rtn.add(new BambooTile(i));
			}
		}

		rtn.add(new FlowerTile("Chrysanthemum"));
		rtn.add(new FlowerTile("Orchid"));
		rtn.add(new FlowerTile("Plum"));
		rtn.add(new FlowerTile("Bamboo"));
		rtn.add(new SeasonTile("Spring"));
		rtn.add(new SeasonTile("Summer"));
		rtn.add(new SeasonTile("Fall"));
		rtn.add(new SeasonTile("Winter"));

		Collections.shuffle(rtn, new Random(gameNumber));
		Tile[] myReturn = new Tile[144];
		int i = 0;
		for (Tile t : rtn)
			myReturn[i++] = t;
		return myReturn;
	}

	public static void playClick(Tile sT) {
		if (sT.isPlayable()) {
			for (int i = 0; i <= TILES.length - 1; i++) {
				if (TILES[i] == sT) {
					if (MahJongGUI.getMGUI().getTileOne() == null) {
						MahJongGUI.getMGUI().setTileOne(i);
						sT.setSelected();
						sound.singleClick();
					} else {
						if (TILES[MahJongGUI.getMGUI().getTileOneIndex()].matches(sT)) {
							TILES[MahJongGUI.getMGUI().getTileOneIndex()].setVisible(false);
							sT.setVisible(false);
							Move move = new Move(i, MahJongGUI.getMGUI().getTileOneIndex());
							MahJongGUI.getMGUI().addMove(move);
							sound.doubleClick();

							if (Move.getMyMoves().size() == 72) {

								fire.setExplosions(23, 1000);
								fire.fire();

							}
						} else {
							sound.noMatchClick();
						}

						TILES[MahJongGUI.getMGUI().getTileOneIndex()].setUnselected();
						sT.setUnselected();
						MahJongGUI.getMGUI().resetTileOne();

					}
				}
			}
		}

	}

	public static Tile[] getTILES() {
		return TILES;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (MahJongGUI.getMGUI().getTileOne() != null) {
			TILES[MahJongGUI.getMGUI().getTileOneIndex()].setUnselected();
			MahJongGUI.getMGUI().resetTileOne();
			sound.noMatchClick();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}