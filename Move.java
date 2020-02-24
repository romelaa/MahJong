/*
 * Romela Azizyan
 * CS 3230 - MahJong
*/
import java.util.Stack;
import javax.swing.JPanel;

public class Move extends JPanel {

	private static final long serialVersionUID = 1L;
	private int tileOne;
	private int tileTwo;
	private static Stack<Move> myMoves = new Stack<Move>();

	//constructor
	public Move(int tileOne, int tileTwo) {
		this.setTileOne(tileOne);
		this.setTileTwo(tileTwo);
	}

	//Makes a new move, and adds it to the stack
	public static void addMove(Move move, JPanel playedTiles) {
		MahJongBoard.getTILES()[move.getTileOne()].setUnselected();
		Tile t1 = Tile.getTileClone(move.getTileOne());
		t1.setUnselected();
		t1.setVisible(true);
		playedTiles.add(t1,0);
		MahJongBoard.getTILES()[move.getTileTwo()].setUnselected();
		Tile t2 = Tile.getTileClone(move.getTileTwo());
		t2.setUnselected();
		t2.setVisible(true);
		playedTiles.add(t2,0);
		playedTiles.repaint();
		playedTiles.revalidate();
		myMoves.push(move);
	}

	//remove a move from the stack and GUI
	public static void removeMove(JPanel playedTiles) {
		if (playedTiles.getComponentCount() > 0) {
			Move popped = getMyMoves().pop();
			playedTiles.remove(0);
			playedTiles.remove(0);
			MahJongBoard.getTILES()[popped.tileOne].setVisible(true);
			MahJongBoard.getTILES()[popped.tileTwo].setVisible(true);
			playedTiles.repaint();
			playedTiles.revalidate();
		}
	}

	//Resets the moves to 0
	public static void resetMyMoves() {
		myMoves = new Stack<Move>();
		
	}
	
	//Getters and setters
	public int getTileTwo(){return tileTwo;}
	public void setTileTwo(int tileTwo){this.tileTwo = tileTwo;}
	public int getTileOne(){return tileOne;}
	public void setTileOne(int tileOne){this.tileOne = tileOne;}
	public static Stack<Move> getMyMoves(){return myMoves;}
	public static void setMyMoves(Stack<Move> myMoves){Move.myMoves = myMoves;}
}
