package chess.junit;
import java.awt.Color;
import java.util.ArrayList;

public class Board 
{
	private Piece board [][];
	private int row ,col;

	ArrayList<Square>legalPieces; //all the pieces that can be moved in the current board.
	
	/**
	 * @param row number of rows on chess board
	 * @param col number of columns on chess board
	 * initialize a 2D chess board of rows * columns.
	 */
	public Board(int row, int col)
	{
		this.row = row;
		this.col = col;		
		board = new Piece[row][col];
	}
	
	/**
	 * @param x  x coordinate on the board
	 * @param y  y coordinate on the board
	 * @return the piece located at board[x][y];
	 */
	public Piece getBoard(int x, int y) {
		return board[x][y];
	}

	/**
	 * @param x  x coordinate on the board
	 * @param y  y coordinate on the board
	 * @param p  the piece need to be placed at board[x][y]
	 */
	public void setBoard(int x, int y, Piece p) {
		board[x][y] = p;
	}
	
	/**
	 * @param b a board configuration
	 */
	public void setWholeBoard(Piece[][] b)
	{
		this.board = b;
	}
	
	/**
	 * @return the number of rows on chess board
	 */
	public int getRow() {
		return row;
	}


	/**
	 * @return  the number of columns on chess board
	 */
	public int getCol() {
		return col;
	}

	/**
	 * clean up the board, set every square to black
	 */
	public void resetBoard()
	{
		for (int i = 0; i<row; i++)
		{
			for (int j = 0; j < col ; j++)
				board[i][j] = null;
		}
	}
	
	/**
	 * Given a 2D array pieces configuration, set up the board
	 */
	public void update()
	{
		for (int i = 0; i<row; i++)
		{
			for (int j = 0; j < col ; j++)
			{
				if (board[i][j] != null)
				{
					place_init(board[i][j],i,j);
				}		
			}
		}
	}
	
	/**
	 * print the current board configuration, for game displaying & debugging
	 */
	public void printBoard()
	{
		for (int j = 0; j < col;j++)
		{
			System.out.print("    "+j);
		}
		System.out.println();
		for (int i = 0; i<row; i++)
		{
			System.out.print(i);
			for (int j = 0; j < col ; j++)
			{
				if (board[i][j] == null)
					System.out.print("   "+"__");
				else 
					System.out.print("   "+board[i][j].getName() );
			}
			System.out.println("");
			System.out.println("");
		}
	}
	
	int bKingX, bKingY, wKingX, wKingY;
	
	//First time place a piece on board
	/**
	 * @param p  piece that needs to be placed on board
	 * @param x  the x coordinate to place p
	 * @param y  the y coordinate to place p
	 */
	public void place_init(Piece p, int x, int y)
	{
		board[x][y] = p;
		if (p instanceof King)
		{
			if (p.getColor() == -1)
			{
				wKingX = x;
				wKingY = y;
			}
			else
			{
				bKingX = x;
				bKingY = y;
			}
		}
		p.setPlace(x,y);
	}
	
	
	/**
	 * @param  x the x coordinate of player input
	 * @param  y the y coordinate of player input
	 * @param  player the color of the current player, either black or white
	 * @return  true or false
	 * The selected piece to move can not be :
	 * 1) out of bonds  2) empty  3) opponent's piece
	 * all above conditions return false;
	 */
	public boolean inputPieceCheck(int x,int y, int player)
	{

		if (x < 0 || x >= row || y < 0 || y > col)
			return false;
		
		if (board[x][y] == null)
			return false;
	
		if (player !=  board[x][y].getColor())
			return false;
		
		else return true;
	}
	
	
	/**
	 * @param  p piece that needs to be moved
	 * @param  targetX target x coordinate input by user
	 * @param  targetY target y coordinate input by user
	 * @return true if that move can be executed, false otherwise
	 */
	public boolean moveOrder (Piece p, int targetX, int targetY)
	{
			//bounds check
			if (targetX < 0 || targetX >= col || targetY < 0 || targetY >= col)
				return false;
			
			//update King's location all the time
			if (p instanceof King)
			{
				if (p.getColor() == -1)
				{
					wKingX = p.getCurrX();
					wKingY = p.getCurrY();
				}
				else
				{
					bKingX = p.getCurrX();
					bKingY = p.getCurrY();
				}
			}
			
			//Check, cannot attack own pieces
			if (board[targetX][targetY] != null)
			{	
				Piece target = board[targetX][targetY];
				if (target.getColor() == p.getColor())
					return false;
				System.out.println("Move to attack! "+target.getName() + " at "+ targetX+targetY + " is DEAD!" );
				move_toAttack(p, targetX, targetY);
			}
			else
			{
				System.out.println("Move to blank");
				move_toBlank(p,targetX,targetY);
			}
			return true;	
	}
	
	/**
	 * @param  p the piece that needs to be moved to a blank square
	 * @param  targetX target x coordinate
	 * @param  targetY target y coordinate
	 */
	public void move_toBlank(Piece p, int targetX, int targetY)
	{
		board[p.getCurrX()][p.getCurrY()] = null;
		board[targetX][targetY] = p;
		p.setPlace(targetX, targetY);
		if (p instanceof King)
		{
			if (p.getColor() == -1)
			{
				wKingX = targetX;
				wKingY = targetY;
			}
			else
			{
				bKingX = targetX;
				bKingY = targetY;
			}
		}
	}
	
	//Target is an opponent's piece
	/**
	 * @param  p the piece that needs to perform the attack
	 * @param  targetX the target's x coordinate
	 * @param  targetY the target's y coordinate
	 */
	public void move_toAttack(Piece p, int targetX, int targetY)
	{
		Piece target = board[targetX][targetY];
		target.setDead();
		move_toBlank(p,targetX,targetY);
		
	}
	
	/**
	 * @param player the color of the player, either BLACK or WHITE
	 * @return the location of his/her king
	 */
	public Piece getKing (int player)
	{
		if (player == -1)
			return (board[wKingX][wKingY]);
		else
			return (board[bKingX][bKingY]);
	}
	

	/**
	 * @param  player the color of the player, either BLACK or WHITE
	 * @return  1 if stalemate, 2 if checkmate, 0 if game is not end.
	 * This method runs at the beginning at each turn, it checks if there're legal moves left
	 * A legal move won't put the king in danger next step. If no such move available, call game ends.
	 */
	public int checkGameEnds(int player)
	{
		Piece king = getKing(player);
		System.out.println("dsd");
		
		if (king == null)
		{
			System.out.println("WTF");
		}
		boolean inCheck = king.isInCheck(this);
		//boolean inCheck = false;
		System.out.println("_____");
		if (inCheck == true)
			System.out.println("Check!!!"+ board[bKingX][bKingY].getName() + " is in danger! ");	
	
		ArrayList<Square> list =  king.getAllMoveable(this, king);
		if(list.size() == 0 && inCheck == true)
			return 1;
		
		else if (list.size() == 0 && inCheck == false)
			return 2;
		
		else if (inCheck == true)
			return 3;
		
		else
		{
			return 0;
		}
	}
}
