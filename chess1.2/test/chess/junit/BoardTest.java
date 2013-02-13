package chess.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoardTest 
{	
	private static final int BLACK = 1, WHITE = -1;
	private static final int ROW = 8, COL = 8;
	private static final int originX = 0, originY = 0;
	private static final int centerX = ROW/2, centerY = COL/2;
	private static final int edgeX = originX, edgeY = originY+1;
	private static Board safeBoard;  // A game that is not ending
	private static Board unsafeBoard; // Checkmate Condition
	private static Board evenBoard;// Stalemate Condition
	
	static Piece bKing,bQueen,wQueen2,bKnight1,bKnight2, bRock1,bRock2,bBishop1,bBishop2;
	static Piece [] bPawns; 
	static Piece wKing,wQueen,wKnight1,wKnight2, wRock1,wRock2, wBishop1,wBishop2;
	static Piece [] wPawns;
	static boolean move;
	

	@BeforeClass
	/* Set up 3 boards and several pieces for test
	 * safe board has plenty of legal moves
	 * unsafe board is in check condition and has no legal move
	 * danger board is in stalemate condition, also no legal move
	 * */
	public static void setUpBeforeClass() throws Exception 
	{
		safeBoard = new Board(ROW,COL);
		unsafeBoard = new Board(ROW,COL);
		evenBoard = new Board(ROW,COL);
		
		bKing = new King(BLACK,"bk");
		bQueen = new Queen(BLACK, "bQ");
		bBishop1 = new Bishop(BLACK, "bB");
		bBishop2 = new Bishop(BLACK, "bB");
		bRock1 = new Rook(BLACK, "bR");
		bRock2 = new Rook(BLACK, "bR");
		bKnight1 = new Knight(BLACK, "bH");
		bKnight2 = new Knight(BLACK, "bH");
		bPawns = new Pawn[COL];
		for (int i = 0; i < COL;i++)
			bPawns[i] = new Pawn(BLACK,"bP");	
		
		wKing = new King (WHITE, "wk");
		wQueen = new Queen (WHITE,"wQ");
		wQueen2 = new Queen (WHITE,"wQ");
		wBishop1 = new Bishop(WHITE, "wB");
		wBishop2 = new Bishop(WHITE, "wB");
		wRock1 = new Rook(WHITE, "wR");
		wRock2 = new Rook(WHITE, "wR");
		wKnight1 = new Knight(WHITE, "wH");
		wKnight2 = new Knight(WHITE, "wH");
		wPawns = new Pawn[COL];
		for (int i = 0; i < COL;i++)
			wPawns[i] = new Pawn(WHITE,"wP");
		
		safeBoard.resetBoard();
		Piece [][] board1 = new Piece[][] {
				{null,		null,	  null,		null,	  bKing,	null,	   null,	 null},
				{null,		null,	  null,		bBishop1, null,		null,	   null,	 bPawns[7]},
				{bPawns[0],	null,	  null,		null,	  null,		bPawns[5], bRock2,	 null},
				{null,	  	bPawns[1],null,		bPawns[3],null,		null,	   null,	 null},
				{null,		wPawns[1],wPawns[2],null,	  null,		null,	   null,	 null},
				{wPawns[0],	null,	  null,		null,	  null,		wKnight2,  null,	 wRock2},
				{null, 		null,	  null,		wPawns[3],null,		wPawns[5], wPawns[6],null},
				{wRock1,    wKnight1, null,     wQueen,   wKing,	wBishop2,  null,     null}
			};
		
		safeBoard.setWholeBoard(board1);
		
		Piece [][] board2 = new Piece [][]
			{
				{null,	null,	null,	null, 	bKing,	null, null, null },
				{null,	null,	null,	null,	null,	null,   wPawns[4], null },
				{null,  null,   wKnight2,   null,   wQueen2,   null,   null, null },
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{wKing,null,null,null,null,null,null,null}
			};
		unsafeBoard.setWholeBoard(board2);
	
		Piece [][] board3 = new Piece [][]
			{
				{null,	null,	null,	null, 	bKing,	wBishop1, null, wKnight1 },
				{null,	null,	null,	null,	null,	null,   null, null },
				{null,  null,   null,   wQueen,   null,   null,   null, null },
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{wKing,null,null,null,null,null,null,null},
				
			};
		evenBoard.setWholeBoard(board3);
		
		safeBoard.update();
		unsafeBoard.update();
		evenBoard.update();
	}
	
	@Test
	//Try to locate a piece at invalid coordinate
	public void testInputPieceCheck()
	{
		boolean validPiece = safeBoard.inputPieceCheck(-1 ,999, -1);
		assertFalse(validPiece);
	}
	
	@Test
	//Try to find a piece at empty square
	public void testInputPieceCheckEmpty()
	{
		boolean validPiece = safeBoard.inputPieceCheck(0 , 0, -1);
		assertFalse(validPiece);
	}
	
	@Test
	//Try to move opponent's piece
	public void testInputPieceCheckOpponent()
	{
		boolean validPiece = safeBoard.inputPieceCheck(7,0,1);
		assertFalse(validPiece);
	}
		
	@Test
	//Try to move a valid selection on pieces
	public void testInputPieceCheckValid()
	{
		boolean validPiece = safeBoard.inputPieceCheck(7, 5, -1);
		assertTrue(validPiece);
	}

	@Test
	public void testUpDate()
	{	
		assertTrue (safeBoard.getBoard(7,4) instanceof King);
		assertTrue (safeBoard.getBoard(7,7) == null);
		assertTrue (unsafeBoard.getBoard(7,0) instanceof King);
		assertTrue (unsafeBoard.getBoard(2,4) instanceof Queen);
		
	}
	
	
	@Test
	public void testPlace_init() 
	{
		safeBoard.place_init(wBishop2, centerX, centerY);
		safeBoard.place_init(bKnight1, edgeX, edgeY);
		safeBoard.place_init(bRock1, originX, originY);
		
		assertTrue(safeBoard.getBoard(centerX,centerY) instanceof Bishop);
		assertTrue(safeBoard.getBoard(edgeX,edgeY) instanceof Knight);
		assertTrue(safeBoard.getBoard(originX,originY) instanceof Rook);

	}

	

	@Test
	//Move to a blank space
	public void testMoveOrderBlank() 
	{
		
		move = safeBoard.moveOrder(bBishop1, 2, 4);
		assertTrue(move);
		assertTrue (safeBoard.getBoard(2,4) instanceof Bishop);
	}
	
	@Test
	//Move to attack an opponent
	public void testMoveOrderOpponent()
	{
		move = safeBoard.moveOrder(wPawns[2], 3, 1);
		assertTrue(move);
		assertTrue (safeBoard.getBoard(3,1) instanceof Pawn);
	}

		
	@Test
	//Move to a space occupied by self
	public void testMoveOrderSelf()
	{
		move = safeBoard.moveOrder(wKing, 7 , 3);
		assertFalse(move);
		assertTrue (safeBoard.getBoard(7,1) instanceof Knight);
	}
		
	@Test
	//Move to a space out of board
	public void testMoveOrderOut()
	{
		move = safeBoard.moveOrder (wRock2, -10, 999);
		assertFalse(move);
	}


	@Test
	public void testGetKing() {
		Piece whiteKing = unsafeBoard.getKing(-1);
		Piece blackKing = unsafeBoard.getKing(1);
		
		assertEquals(whiteKing.getCurrX(), 7);
		assertEquals(whiteKing.getCurrY(), 0 );
		assertEquals(blackKing.getCurrX(), 0);
		assertEquals(blackKing.getCurrY(), 4 );
	}

	
	
	@Test
	public void testCheckGameEnds()
	{		
		unsafeBoard.printBoard();
		
		int isEnd = safeBoard.checkGameEnds(WHITE);
		assertEquals(isEnd, 0);
		
		int isEnd2  = unsafeBoard.checkGameEnds(BLACK);
		assertEquals(isEnd2, 1);
	
		
		isEnd = evenBoard.checkGameEnds(BLACK);
		assertEquals(isEnd, 2);
	}

}
