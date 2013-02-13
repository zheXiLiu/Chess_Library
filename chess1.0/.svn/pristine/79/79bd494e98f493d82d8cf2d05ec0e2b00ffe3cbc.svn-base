package chess.junit;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Zhexi
 * Pawn is an extended class from Piece class
 */
public class Pawn extends Piece
{
	public Pawn(int color, String chessname) 
	{
		super(color, chessname);
		int type = (color == 1)? 6:-6;
		setType(type);
	}

	/* (non-Javadoc)
	 * @see chess.junit.Piece#updateReachable(chess.junit.Board)
	 */
	@Override
	void updateReachable(Board b) 
	{
		
		ArrayList<Square>List = new ArrayList<Square>();
		int direction = this.getColor();
		check_PawnMove(b,direction,List);
		check_PawnAttack(b,direction,List);
		
		setReachableList(List);
	}
	

	/**
	 * @param b the current board
	 * @param direction the same value with player color
	 * @param List a list to store squares
	 *  Pawns can move forward in unit square, (2 for first move)
	 * It can't move : 1) out of bonds 2) if the space in front has barriers
	 */
	public void check_PawnMove(Board b, int direction, ArrayList<Square>List)
	{			
		int tarX = getCurrX() + direction;
		if (tarX >= b.getRow() ||tarX <0)
			return;
		
		boolean black_init = (direction == 1 && getCurrX() == 1);
		boolean white_init = (direction == -1 && getCurrX() == 6);
		if (black_init || white_init)
		{
			if (b.getBoard(tarX,getCurrY()) == null)
			{	
				if (b.getBoard(getCurrX()+(direction*2),getCurrY()) == null)
					List.add(new Square(getCurrX()+(direction*2),getCurrY(),this));
					List.add(new Square(tarX,getCurrY(),this));
			}
		}
		else
		{
			if  (b.getBoard(tarX,getCurrY()) == null )
				List.add(new Square(tarX,getCurrY(),this));
		}			
	}

	/**
	 * @param b the current board
	 * @param direction direction the same value with player color
	 * @param List a list to store squares
	 * Pawns can attack unit cells in 45 degrees, but can't move to that square if it's blank
	 */
	public void check_PawnAttack(Board b , int direction,ArrayList<Square>List)
	{
		int tarX = getCurrX() + direction;
		if (tarX >= b.getRow() || tarX <0)
			return;
		if (getCurrY()-1 >= 0)
		{
			Piece target = b.getBoard(tarX,getCurrY()-1);
			if (target!= null && target.getColor() != getColor())
				List.add(new Square(tarX,getCurrY()-1,this));
		}
		if (getCurrY()+1 < b.getCol())
		{
			Piece target = b.getBoard(tarX,getCurrY()+1);
			if (target!= null && target.getColor() != getColor())
				List.add(new Square(tarX,getCurrY()+1,this));
		}
	}
	
}