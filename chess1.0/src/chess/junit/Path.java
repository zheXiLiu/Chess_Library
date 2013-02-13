package chess.junit;
import java.util.ArrayList;

/**
 * @author Zhexi
 *  Path class contains path searching functions.It traverse squares in all 8 directions.
 *  Rock, Queen, Bishop can not pass other pieces, so the search stops as long as it finds a barrier piece
 */
public class Path 
{

	//Invoked by Rock & Queen
	public ArrayList<Square> checkUp (Piece p, Board b, int startRow, int constCol)
	{
		ArrayList<Square> list = new ArrayList<Square> ();
		
		for (int i = startRow+1; i < b.getRow() ; i++)
		{
			if (b.getBoard(i,constCol) == null)
			{	
				list.add( new Square(i, constCol, p) );
			}
			else
			{
				if (b.getBoard(i,constCol).getColor() != p.getColor())
				{
					list.add(new Square(i, constCol, p));
				}
				break; //find a barrier piece, stop
			}
		}
		
		return list;
	}
	
	/**
	 * @param p piece to start
	 * @param b current board
	 * @param startRow the starting row to check
	 * @param constCol constant column
	 * @return a list of reachable squares
	 */
	public ArrayList<Square> checkDown(Piece p,Board b, int startRow, int constCol)
	{
		ArrayList<Square> list = new ArrayList<Square> ();
		for (int i = startRow-1; i >= 0 ; i--)
		{
			if (b.getBoard(i,constCol) == null)
				list.add(new Square(i, constCol, p));
			else
			{
				if (b.getBoard(i,constCol).getColor() != p.getColor())
					list.add(new Square(i, constCol, p));
				break;//a barrier piece, stop
			}
		}
		return list;
	}
	
	/**
	 * @param p piece to start
	 * @param b current board
	 * @param startRow the starting row to check
	 * @param constCol constant column
	 * @return a list of reachable squares
	 */
	public ArrayList<Square> checkRight (Piece p, Board b, int constRow, int startCol)
	{
		ArrayList<Square> list = new ArrayList<Square> ();
		for (int j =  startCol +1; j < b.getCol() ; j++)
		{
			if (b.getBoard(constRow,j) == null)
				list.add(new Square(constRow,j,p));
			else
			{
				if (b.getBoard(constRow,j).getColor() != p.getColor())
					list.add(new Square(constRow,j,p));
				break;//find a barrier piece, stop
			}
		}
		return list;
	}
	

	/**
	 * @param p p piece to start
	 * @param b the current board
	 * @param constRow the constant row
	 * @param startCol the starting column to check
	 * @return a list of reachable squares
	 */
	public ArrayList<Square> checkLeft (Piece p, Board b, int constRow, int startCol)
	{
		ArrayList<Square> list = new ArrayList<Square> ();
		for (int j =  startCol-1; j >=0 ; j--)
		{
			if (b.getBoard(constRow,j) == null)
				list.add(new Square(constRow,j,p));
			else
			{
				if (b.getBoard(constRow,j).getColor() != p.getColor())
					list.add(new Square(constRow,j,p));
				break;//find a barrier piece, stop
			}
		}
		return list;
	}

	/**
	 * @param p p piece to start
	 * @param b the current board
	 * @param constRow the constant row
	 * @param startCol the starting column to check
	 * @return a list of reachable squares
	 */
	public ArrayList<Square> checkUpLeft(Piece p, Board b, int x, int y)
	{
		ArrayList<Square> list = new ArrayList<Square> ();
		x = x-1; y = y-1;
		while (x >=0 && y >= 0)
		{
			if (b.getBoard(x,y) == null)
				list.add(new Square(x,y,p));
			else
			{
				if (b.getBoard(x,y).getColor() != p.getColor())
					list.add(new Square(x,y,p));
				break;//find a barrier piece, stop
			}
			x--; y--;
		}
		
		return list;
	}
	
	
	/**
	 * @param p the piece to start
	 * @param b the current board
	 * @param x the starting row to check
	 * @param y the starting column to check
	 * @return a list of reachable squares
	 */
	public ArrayList<Square> checkUpRight(Piece p, Board b, int x, int y)
	{
		ArrayList<Square> list = new ArrayList<Square> ();
		x = x-1; y = y+1;
		while (x >=0 && y < b.getCol())
		{
			if (b.getBoard(x,y) == null)
				list.add(new Square(x,y,p));
			else
			{
				if (b.getBoard(x,y).getColor()!= p.getColor())
					list.add(new Square (x,y,p));
				break;//find a barrier piece, stop
			}
			x--; y++;
		}
		return list;
	}
	
	/**
	 * @param p the piece to start
	 * @param b the current board
	 * @param x the starting row to check
	 * @param y the starting column to check
	 * @return a list of reachable squares
	 */
	public ArrayList<Square> checkDownLeft(Piece p, Board b, int x, int y)
	{
		ArrayList<Square> list = new ArrayList<Square> ();
		x = x+1; y = y-1;
		while (x < b.getRow() && y >=0 )
		{
			if (b.getBoard(x,y) == null)
				list.add(new Square(x,y,p));
			else
			{
				if (b.getBoard(x,y).getColor() != p.getColor())
					list.add(new Square(x,y,p));
				break;//find a barrier piece, stop
			}
			x++; y--;
		}
		return list;
	}
	
	/**
	 * @param p the piece to start
	 * @param b the current board
	 * @param x the starting row to check
	 * @param y the starting column to check
	 * @return a list of reachable squares
	 */
	public ArrayList<Square> checkDownRight(Piece p, Board b, int x, int y)
	{
		ArrayList<Square> list = new ArrayList<Square> ();
		x = x+1; y = y+1;
		while (x < b.getRow() && y < b.getCol() )
		{
			if (b.getBoard(x,y) == null)
				list.add(new Square(x,y,p));
			else
			{
				if (b.getBoard(x,y).getColor() != p.getColor())
					list.add(new Square(x,y,p));
				break;
			}
			x++; y++;
		}
		return list;
	}
	
}
