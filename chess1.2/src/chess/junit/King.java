package chess.junit;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Zhexi
 * King is an extended class from the Piece class
 */
public class King extends Piece
{
	public King(int color, String chessname) {
		super(color, chessname);
		int type = (getColor() == 1)? 1:-1;
		setType(type);
		
	}

	/* (non-Javadoc)
	 * @see chess.junit.Piece#setPlace(int, int)
	 * king's setPlace is unique from every body else, it needs to update the static variable
	 */
	public void setPlace(int x, int y)
	{
		if (this.getColor() == -1)
		{
			setwKingX(x);   
			setwKingY(y);
		}
		else
		{
			setbKingX(x);
			setbKingY(y);
		}
		
		setCurrX(x);  
		setCurrY(y);
	}
	
	
	/* (non-Javadoc)
	 * @see chess.junit.Piece#updateReachable(chess.junit.Board)
	 * functionally king moves in all 8 unit directions
	 */
	@Override
	void updateReachable(Board b) 
	{
		ArrayList<Square> List = new ArrayList<Square> ();
		canAttackByKing(b,getCurrX()-1,getCurrY()-1,List);
		canAttackByKing(b,getCurrX()-1,getCurrY(),List);
		canAttackByKing(b,getCurrX()-1,getCurrY()+1,List);
		canAttackByKing(b,getCurrX(),getCurrY()-1,List);
		canAttackByKing(b,getCurrX(),getCurrY()+1,List);
		canAttackByKing(b,getCurrX()+1,getCurrY()-1,List);
		canAttackByKing(b,getCurrX()+1,getCurrY(),List);
		canAttackByKing(b,getCurrX()+1,getCurrY()+1,List);
		
		setReachableList(List);
	}
	
	/*
	 * functionally, a king's move can not:
	 * 1) move out of bonds   2) attack own piece
	 * But path generated in this reachable list might not be legal moves.
	*/
	
	/**
	 * @param b current chess board
	 * @param x target x coordinate
	 * @param y target y coordinate
	 * @param List a list to store squares near king
	 */
	void canAttackByKing(Board b, int x, int y, ArrayList<Square> List)
	{
		if (x >= b.getRow() || x <0 || y >= b.getCol() || y <0)
			return;
		if (b.getBoard(x,y)==null)
			List.add( new Square (x, y , this));
		else if (b.getBoard(x,y).getColor() != getColor())
			List.add( new Square (x, y , this));
	}
	
}

