package chess.junit;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Zhexi
 * Queen is extended from Piece class
 */
public class Queen extends Piece
{
	public Queen(int color, String chessname) 
	{
		super(color, chessname);
		int type = (color == 1)? 2:-2;
		setType(type);
	}
	
	/* (non-Javadoc)
	 * @see chess.junit.Piece#updateReachable(chess.junit.Board)
	 * Queen moves in all 8 directions, but can't pass pieces
	 */
	@Override
	void updateReachable(Board b)
	{
		 ArrayList<Square>List = new ArrayList<Square>();
		Path generator = new Path();
		
		List.addAll(generator.checkUp (this, b,getCurrX(), getCurrY())) ;
		List.addAll(generator.checkDown (this,b,getCurrX(), getCurrY())) ;
		List.addAll(generator.checkRight (this,b,getCurrX(), getCurrY()))  ;
		List.addAll(generator.checkLeft (this,b,getCurrX(), getCurrY())) ;	
		List.addAll(generator.checkUpLeft(this,b, getCurrX(), getCurrY()));
		List.addAll(generator.checkUpRight(this,b, getCurrX(), getCurrY()));
		List.addAll(generator.checkDownLeft(this,b, getCurrX(), getCurrY()));
		List.addAll(generator.checkDownRight(this,b, getCurrX(), getCurrY()));
		
		setReachableList(List);
	}
}