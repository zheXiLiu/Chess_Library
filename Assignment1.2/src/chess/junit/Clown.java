package chess.junit;

import java.util.ArrayList;

/**
 * @author Zhexi
 * Class Clown is an extended class from Piece class. It can only
 * moves in unit 8 directions, it cannot capture any pieces, but it can be
 * captured. So the solo purpose of this piece is to occupy space.
 *
 */
public class Clown extends Piece{

	public Clown(int color, String chessname) {
		super(color, chessname);
		int type = (color == 1)? 8:-8;
		setType(type);
	}

	/* (non-Javadoc)
	 * @see chess.junit.Piece#updateReachable(chess.junit.Board)
	 */
	@Override
	void updateReachable(Board b) {
		// TODO Auto-generated method stub
		ArrayList<Square> List = new ArrayList<Square>();
		
		checkUnit(this, b,getCurrX()+1,getCurrY()+1,List);
		checkUnit(this,b,getCurrX()+1,getCurrY(),List);
		checkUnit(this,b,getCurrX()+1,getCurrY()-1,List);
		checkUnit(this,b,getCurrX(),getCurrY()+1,List);
	
		checkUnit(this,b,getCurrX(),getCurrY()-1,List);
		checkUnit(this,b,getCurrX()-1,getCurrY()+1,List);
		checkUnit(this,b,getCurrX()-1,getCurrY(),List);
		checkUnit(this,b,getCurrX()-1,getCurrY()-1,List);
		
		setReachableList (List);
		
	}
	
	/**
	 * @param p  current chess piece
	 * @param b  current board b
	 * @param x  target X coordinate
	 * @param y  target Y coordinate
	 * @param List A list of reachable squares of this piece
	 */
	public void checkUnit (Piece p, Board b, int x, int y, ArrayList<Square> List)
	{
		if (x >= 0 && y >= 0 && x < b.getRow() && y < b.getCol())
		{
			if (b.getBoard(x,y) == null)
				List.add(new Square(x,y,p));
		}
	}

	

}
