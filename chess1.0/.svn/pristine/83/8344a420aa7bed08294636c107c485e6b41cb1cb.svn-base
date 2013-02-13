package chess.junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PiecesTest {

	private static final int BLACK = 1, WHITE = -1;
	private static final int ROW = 8, COL = 8;
	private static final int originX = 0, originY = 0;
	private static final int centerX = ROW/2, centerY = COL/2;
	private static final int edgeX = originX, edgeY = originY+1;
	private static Board safeBoard;  // A game that is not ending
	private static Board unsafeBoard; // Checkmate Condition
	private static Board evenBoard;// Stalemate Condition
	
	static Piece bKing,bQueen,wQueen2,bKnight1,bKnight2, bRock1,bRock2,bBishop1,bBishop2,wClown,wChance;
	static Piece [] bPawns; 
	static Piece wKing,wQueen,wKnight1,wKnight2, wRock1,wRock2, wBishop1,wBishop2;
	static Piece [] wPawns;
	
	
	@BeforeClass
	/* Set up two boards and several pieces for test
	 * safe board has plenty of legal moves
	 * unsafe board is in check condition and has no legal move
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
		wClown = new Clown(WHITE, "wL");
		wChance = new Chancellor(WHITE, "wC");
		for (int i = 0; i < COL;i++)
			wPawns[i] = new Pawn(WHITE,"wP");

		safeBoard.resetBoard();
		Piece [][] board1 = new Piece[][] {
				{null,		null,	  null,		null,	  bKing,	null,	   bKnight2,	 null},
				{null,		null,	  null,		bBishop1, null,		null,	   null,	 bPawns[7]},
				{bPawns[0],	null,	  null,		null,	  null,		bPawns[5], bRock2,	 null},
				{null,	  	bPawns[1],null,		bPawns[3],null,		null,	   null,	 null},
				{null,		wPawns[1],wPawns[2],null,	  null,		null,	   null,	 null},
				{wPawns[0],	null,	  null,		null,	  null,		wKnight2,  null,	 wRock2},
				{null, 		null,	  null,		null ,null,		wPawns[5], wPawns[6],null},
				{wKing,    wBishop1, null,     wQueen,   null,	null,  null,     null}
			};
		safeBoard.setWholeBoard(board1);
		
		
		Piece [][]board2 = new Piece [][]
				{
					{null,	null,	null,	null, 	bKing,	null, null, null },
					{null,	null,	null,	null,	null,	null,   wPawns[4], null },
					{wRock1,  null,   wKnight1,   null,   wQueen2,   null,   null, null },
					{null,null,null,null,null,null,null,null},
					{bRock1,null,null,null,null,null,null,wBishop2},
					{null,null,null,null,null,null,null,null},
					{wPawns[3], bQueen, null,  null,null,null,null,null},
					{wKing,		null,   null,  bKnight1,null,null,null,null},
				};
		unsafeBoard.setWholeBoard(board2);
		
		safeBoard.update();
		unsafeBoard.update();
	}


	@Test
	public void testUpdateReachable() 
	{
		wKing.updateReachable(safeBoard);
		assertEquals(wKing.getReachableList().size() ,2);
		wQueen.updateReachable(safeBoard);
		assertEquals(wQueen.getReachableList().size(),13);
		wPawns[6].updateReachable(safeBoard);
		assertEquals(wPawns[6].getReachableList().size(),2);
		bBishop1.updateReachable(safeBoard);
		assertEquals(bBishop1.getReachableList().size(),6);
		bKnight2.updateReachable(safeBoard);
		assertEquals(bKnight2.getReachableList().size(),2);
		bRock2.updateReachable(safeBoard);
		assertEquals(bRock2.getReachableList().size(), 6);
		
		Board blank  = new Board(8,8);
		blank.place_init(wClown, 4, 4);
		wClown.updateReachable(blank);
		assertEquals(wClown.getReachableList().size(),8);
		
		blank.place_init(wChance, 0, 0);
		wChance.updateReachable(blank);
		assertEquals(wChance.getReachableList().size(),16);
		
	}
	

	@Test
	public void testGetOpponentAttackRange() 
	{
		 ArrayList<Square> list = bKing.getOpponentAttackRange(safeBoard);
		 assertTrue(bKing.listContains(new Square(7,7,null), list));
		 assertEquals(list.size(),42);
		
		 list= wKing.getOpponentAttackRange(unsafeBoard);
		 assertTrue(bKing.listContains(new Square(7,0,null), list));
		 assertEquals(list.size(),41);
	}

	

	@Test
	public void testGetKingByPlayer() {
		Square whiteKing = wQueen.getKingByPlayer(WHITE);
		Square blackKing = wKing.getKingByPlayer(BLACK);
		
		assertEquals(whiteKing.x, 7);
		assertEquals(whiteKing.y, 0);
		assertEquals(blackKing.x, 0);
		assertEquals(blackKing.y, 4);
	}
	

	@Test
	public void testGetAllMoveable()
	{
		ArrayList<Square> list = bKing. getAllMoveable (safeBoard,  bKing);
		assertEquals(list.size(), 25);
		list = wKing. getAllMoveable (safeBoard,  wKing);
		assertEquals(list.size(), 42);
		list = bKing. getAllMoveable (unsafeBoard,  bKing);
		assertEquals(list.size(), 0);
		list = wKing. getAllMoveable (unsafeBoard,  wKing);
		assertEquals(list.size(), 0);
		
		wKing.printLegalMove(safeBoard);
		
	}

	
	
	@Test
	public void testGetLegalPieces() 
	{
		bKing. getAllMoveable (safeBoard,  bKing);
		ArrayList<Square> list =  bKing.getLegalPieces (safeBoard);
		assertEquals(list.size(), 9);
		
		wKing. getAllMoveable (safeBoard,  wKing);
		list =  wKing.getLegalPieces (safeBoard);
		assertEquals(list.size(), 8);
		
		wKing. getAllMoveable (unsafeBoard,  wKing);
		list =  wKing.getLegalPieces (unsafeBoard);
		assertEquals(list.size(), 0);
	}

	
	@Test
	public void testIsInCheck()
	{
		boolean inCheck = wKing.isInCheck(safeBoard);
		assertFalse(inCheck);
		
		inCheck = bKing.isInCheck(unsafeBoard);
		assertTrue(inCheck);
	}
}
