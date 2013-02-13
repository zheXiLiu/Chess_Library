package chess.junit;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Zhexi
 * Bishop is an extended class from Piece class
 * The bishop can move any number of squares diagonally, but may not leap over other pieces.
 */
public class Bishop extends Piece
{
	public Bishop(int color, String chessname) 
	{
		super(color, chessname);
		int type = (color == 1)? 4:-4;
		setType(type);
		
	}

	/* (non-Javadoc)
	 * @see chess.junit.Piece#updateReachable(chess.junit.Board)
	 */
	void updateReachable(Board b) 
	{
		ArrayList <Square> List = new ArrayList<Square>();
		Path generator = new Path();
		List.addAll(generator.checkUpLeft(this, b, getCurrX(), getCurrY() ));
		List.addAll(generator.checkUpRight(this,b, getCurrX(), getCurrY() ));
		List.addAll(generator.checkDownLeft(this,b, getCurrX(), getCurrY() ));
		List.addAll(generator.checkDownRight(this,b, getCurrX(), getCurrY() ));
		
		setReachableList(List);
		
	}
}