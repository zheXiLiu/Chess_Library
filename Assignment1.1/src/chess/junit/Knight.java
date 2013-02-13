package chess.junit;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Zhexi
 * Knight is an extended chess class from Piece class
 *
 */
public class Knight extends Piece
{

	public Knight(int color, String chessname) 
	{
		super(color, chessname);
		int type = (color == 1)? 5:-5;
		setType(type);

	}

	/* (non-Javadoc)
	 * @see chess.junit.Piece#updateReachable(chess.junit.Board)
	 *  Knight moves in all 8 directions with L-shapes
	 */
	@Override
	void updateReachable(Board b) 
	{
		ArrayList<Square> List = new ArrayList<Square>();
	
		checkLshape(this, b,getCurrX()+2,getCurrY()+1,List);
		checkLshape(this,b,getCurrX()+2,getCurrY()-1,List);
		checkLshape(this,b,getCurrX()-2,getCurrY()+1,List);
		checkLshape(this,b,getCurrX()-2,getCurrY()-1,List);
		checkLshape(this,b,getCurrX()+1,getCurrY()+2,List);
		checkLshape(this,b,getCurrX()+1,getCurrY()-2,List);
		checkLshape(this,b,getCurrX()-1,getCurrY()+2,List);
		checkLshape(this,b,getCurrX()-1,getCurrY()-2,List);
		
		setReachableList (List);
	}
	
	/**
	 * @param p the current Knight piece
	 * @param b the current chess board
	 * @param x the target x coordinate
	 * @param y the target x coordinate
	 * @param List a list to store reachable squares near Knight
	 */
	public void checkLshape(Piece p, Board b, int x, int y, ArrayList<Square> List)
	{
		if (x >= 0 && y >= 0 && x < b.getRow() && y < b.getCol())
		{
			if (b.getBoard(x,y) == null)
				List.add(new Square(x,y,p));
			else
			{
				if (b.getBoard(x,y).getColor() != p.getColor())
					List.add(new Square(x,y,p));
			}
		}
	}


}