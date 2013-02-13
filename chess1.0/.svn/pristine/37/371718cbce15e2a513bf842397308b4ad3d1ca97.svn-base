package chess.junit;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Zhexi
 * Rook is extended from Piece class
 */
public class Rook extends Piece
{
	public Rook(int color, String chessname) 
	{
		super(color, chessname);
		int type = (color == 1)? 3:-3;
		setType(type);
	}

	/* (non-Javadoc)
	 * @see chess.junit.Piece#updateReachable(chess.junit.Board)
	 * Rock moves horizontally & vertically
	 */
	@Override
	public void updateReachable (Board b)
	{				
		ArrayList<Square>List = new ArrayList<Square>();
		Path generator = new Path();
		
		List.addAll(generator.checkUp (this,b,getCurrX(), getCurrY())) ;
		List.addAll(generator.checkDown (this,b,getCurrX(), getCurrY())) ;
		List.addAll(generator.checkRight (this,b,getCurrX(), getCurrY())) ;
		List.addAll(generator.checkLeft (this, b,getCurrX(), getCurrY())) ;	
		
		setReachableList(List);
	}
}
