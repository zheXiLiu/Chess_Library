package chess.junit;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Zhexi Liu
 * 242 Assignment 1.1 Chess Libray
 * Piece class performs the check & move operation. It is the 
 * base class for all types of pieces.
 */
public abstract class Piece 
{
	private int currX, currY;
	private int color;
	private int type;
	private  String name;
	private static int wKingX = -1, wKingY = -1;
	private static int bKingX = -1, bKingY = -1;
	private ArrayList<Square> reachableList;
	private ArrayList<Square>legalMove;
	
	
	/**
	 * @param color  the color of chess piece, either black or white
	 * @param chessname  the name/type of chess piece (abbreviations)
	 */
	public Piece(int color, String chessname )
	{
		this.name = chessname;
		this.color = color;		
	}
	
	/**
	 * @return the current x coordinate of chess piece
	 */
	public int getCurrX(){
		return currX;
	}

	
	/**
	 * @param currX  new x coordinate of chess piece
	 * Update the current x coordinate
	 */
	public void setCurrX(int currX) {
		this.currX = currX;
	}
	
	/**
	 * @return the current y coordinate of chess piece
	 */
	public int getCurrY() {
		return currY;
	}

	/**
	 * @param currY  new y coordinate of chess piece
	 * Update the current y coordinate
	 */
	public void setCurrY(int currY) {
		this.currY = currY;
	}
	
	/**
	 * @return color attribute of chess piece, either black or white
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * @param type Define the type of piece (either King, Queen, Rook...etc)
	 */
	public void setType(int type)
	{
		this.type = type;
	}


	/**
	 * @return the type of chess piece (either King,Queen,Rook..etc)
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * @return the name of chess piece, built for game displaying & debugging
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * @return a list of moves that is legal. A legal move won't expose the king in check.
	 */
	public ArrayList<Square> getLegalMove()
	{
		return legalMove;
	}
	
	
	/**
	 * @return  the current x coordinate of white king
	 */
	public static int getwKingX() {
		return wKingX;
	}

	/**
	 * @param wKingX  the new x coordinate
	 * Update white king's x coordinate
	 */
	public static void setwKingX(int wKingX) {
		Piece.wKingX = wKingX;
	}

	
	/**
	 * @return  the current y coordinate of white king
	 */
	public static int getwKingY() {
		return wKingY;
	}

	/**
	 * @param wKingY  the new y coordinate
	 * Update white king's y coordinate
	 */
	public static void setwKingY(int wKingY) {
		Piece.wKingY = wKingY;
	}

	/**
	 * @return  the current x coordinate of black king
	 */
	public static int getbKingX() {
		return bKingX;
	}


	/**
	 * @param wKingX  the new x coordinate
	 * Update black king's x coordinate
	 */
	public static void setbKingX(int bKingX) {
		Piece.bKingX = bKingX;
	}

	/**
	 * @return  the current y coordinate of black king
	 */
	public static int getbKingY() {
		return bKingY;
	}

	/**
	 * @param wKingY  the new y coordinate
	 * Update black king's y coordinate
	 */
	public static void setbKingY(int bKingY) {
		Piece.bKingY = bKingY;
	}

	/**
	 * @return  the list of reachable path
	 */
	public ArrayList<Square> getReachableList()
	{
		//updateReachable(b);
		return reachableList;
	}
	
	
	/**
	 * @param list  newly updated list of reachable spaces
	 * update the current reachable list
	 */
	public void setReachableList(ArrayList<Square> list)
	{
		reachableList = list;
	}

	
	
	/**
	 * @param b  the current chess board
	 * Abstract Method, implementation is different for all different of pieces
	 */
	abstract void updateReachable(Board b);
	
	
	/**
	 * @param x new x coordinate
	 * @param y new y coordinate
	 * update the piece's new position
	 */
	public void setPlace(int x, int y)
	{
		this.currX = x;
		this.currY = y;
	}
	
	
	/**
	 * A piece is dead when it's attacked by opponent
	 * Set the coordinates to -1, out of board, and hence disapear
	 */
	public void setDead()
	{
		this.currX = -1;
		this.currY = -1;
	}


	/**
	 * @param b  current board
	 * @param x  x coordinate
	 * @param y  y coordinate
	 * @return if board[x][y] is an opponent's piece.
	 */
	public boolean isOpponent(Board b, int x, int y)
	{
		return (b.getBoard(x,y)!= null && b.getBoard(x,y).color != color );
	}

	/**
	 * @param b  current board
	 * @param x  x coordinate
	 * @param y  y coordinate
	 * @return if board[x][y] is a piece of same color.
	 */
	public boolean isSelfPiece(Board b, int x, int y)
	{
		return (b.getBoard(x,y)!=null &&  b.getBoard(x,y).color == color);
	}
	

	/**
	 * @return  the current position of king (with same color).
	 */
	public Square getSelfKingPosition()
	{
		return (color == -1)?new Square(wKingX, wKingY, null): 
									new Square(bKingX, bKingY, null) ;
	}	
	

	/**
	 * @param s  a square on board
	 * @param list  a list of squares to search from
	 * @return if the list contains the square s
	 * Works the same with the original list.contains() function.
	 */
	public boolean listContains(Square s, ArrayList<Square> list)
	{
		for (int i = 0 ; i < list.size(); i++)
		{
			Square p = list.get(i);
			if (p.x == s.x && p.y == s.y)
				return true;
		}
		return false;
	}

	/**
	 * @param b  the current board
	 * @return all the reachable space occupied by opponent
	 */
	public ArrayList<Square> getOpponentAttackRange(Board b)
	{
		ArrayList<Square> attackRange = new ArrayList<Square>();

		for (int i = 0; i < b.getRow(); i++)
		{
			for (int j = 0; j < b.getCol(); j++)
			{
				if (isOpponent(b,i,j))
				{
					Piece p = b.getBoard(i,j);
					p.updateReachable(b);
					attackRange.addAll(p.reachableList);
				}
			}
		}	
		return attackRange;
	}

	//Return player's king location
	/**
	 * @param player the color of player, either 1.BLACK or 2.WHITE
	 * @return the square currently occupied by king
	 */
	public Square getKingByPlayer(int player)
	{
		Square king;
		if (player == -1)
			king = new Square(wKingX, wKingY,null);
		else
			king = new Square(bKingX, bKingY,null);
		return king;
			
	}
	
	

	/**
	 * @param b current chess board
	 * @param fakeX target x coordinate
	 * @param fakeY target y coordinate
	 * @param selfKing the square containing the player's own king
	 * @return This function does not change the actual configuration of board.
	 * It only perform a fake move (in free list), check if that move is legal.
	 * A legal move means it won't expose the king in danger.
	 * This function will recover the move & board in the end.
	 */
	public boolean doFakeMove(Board b, int fakeX, int fakeY, Square selfKing)
	{
		//record down the current position
		int dupX = currX, dupY = currY;
		int attack = 0;
		Piece p = null;

		//Perform the fake move, either attack or move to blank
		if (isOpponent(b,fakeX,fakeY))
		{
			p = b.getBoard(fakeX,fakeY);
			attack = 1;
			b.move_toAttack(this, fakeX, fakeY);

		}
		else
			b.move_toBlank(this, fakeX, fakeY);

		//Check if this move will expose the king
		boolean isLegalMove = isInCheck(b);
		
		//Recover
		b.move_toBlank(this, dupX, dupY);	
		if (attack == 1)
		{
			b.setBoard(fakeX,fakeY,p);
			p.setPlace(fakeX,fakeY);
		}
		return (!isLegalMove);
	}
	

	/**
	 * @param b  current board
	 * @param king the square containing the player's own king
	 * @return This function is invoked by checkGameEnds() function, runs at he beginning of each turn
	 *	It checks if there're any legal moves left by performing the fakeMoves in the reachable list
	 */
	public ArrayList<Square> getAllMoveable (Board b,  Piece king)
	{
		//Get king's location, can't but him in danger
		Square selfKing = new Square(king.currX, king.currY, null);		
		ArrayList<Square> moveableRange = new ArrayList<Square>();
				
		//Scan the board to check all self pieces
		for (int i = 0; i < b.getRow(); i++)
		{
			for (int j = 0; j < b.getCol(); j++)
			{
				if (isSelfPiece(b,i,j))
				{
					Piece p = b.getBoard(i,j);
					p.legalMove = new ArrayList<Square>();
					p.updateReachable(b); // update the functionally allowed list
					
					boolean canMove;
					
					//Perform the fake moves
					for (int k = 0; k < p.reachableList.size();k++)
					{
						Square s = p.reachableList.get(k);
						canMove = p.doFakeMove(b,s.x,s.y, selfKing);
						if (canMove == true)
						{
							moveableRange.add(new Square (s.x,s.y,p));
							p.legalMove.add(new Square (s.x,s.y,p));
						}

					}//End fake move loop
				}// End check for that piece
			}//End board scanning
		}	
		return moveableRange;
	}

	
	/**
	 * @param b current board
	 * @return all the pieces that can be moved
	 * If player choose to move any piece out side the list returned by this function, it will be rejected.
	 */
	public ArrayList<Square>  getLegalPieces (Board b)
	{
		ArrayList<Square> legalPieces = new ArrayList<Square>();
		for (int i = 0; i < b.getRow(); i++)
		{
			for (int j = 0; j < b.getCol(); j++)
			{
				if (isSelfPiece(b,i,j))
				{
					Piece p = b.getBoard(i,j);
					if (p.legalMove.size() !=0)
					{	
						legalPieces.add(new Square (i,j,null));
					}
				}

			}
		}	
		System.out.println("");
		return legalPieces;
	}	
	
	
	/**
	 * @param b current board
	 * @return If king is under attack
	 */
	public boolean isInCheck(Board b)
	{
		Square king;
		king = getKingByPlayer(color);
		ArrayList<Square> attackRange = getOpponentAttackRange(b);
		boolean check = listContains(king, attackRange);
		return check;
	}
	
		/**
		 * @param b current board
		 * For debugging, Helper function to print all legalMoves left.
		 */
		public void printLegalMove (Board b)
		{
			for (int i = 0; i < b.getRow(); i++)
			{
				for (int j = 0; j < b.getCol(); j++)
				{
					if (isSelfPiece(b,i,j))
					{
						Piece p = b.getBoard(i,j);
						if (p.legalMove.size() ==0)
							System.out.println(p.name+ " at " + i+j+ " cannot be moved");
						else
						{
							System.out.print(p.name+ " at " + i+j+ " can be moved to: ");
							for (int k = 0; k < p.legalMove.size();k++)
							{
								Square s = p.legalMove.get(k);
								System.out.print(" "+s.x+s.y+" ");	
							}

							System.out.println("");
						}
					}

				}
			}	
		}


	/**
	 * @param list  a list of squares on board
	 * For game displaying & debugging
	 */
	public void printlist( ArrayList<Square> list)
	{	
		for (int i = 0; i < list.size(); i++)
		{
			Square a = list.get(i);
			System.out.println(a.x+" "+ a.y);
		}
		System.out.println("_____end of list_____");
	}

}




