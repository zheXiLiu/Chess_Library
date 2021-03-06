package chess.junit;

import static org.junit.Assert.*;
import java.awt.Color;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class PathTest 
{
	private static final int BLACK = 1, WHITE = -1;
	private static final int ROW = 8, COL = 8;
	private static final int originX = 0, originY = 0;
	private static final int centerX = ROW/2, centerY = COL/2;
	private static final int edgeX = originX, edgeY = originY+1;
	private static  Path generator;
	private static Board board;  // onltargetY a single piece
	private static Board barrierBoard; // Board with multiple pieces, obstacles
	private static ArrayList<Square> list;
	static Piece wQueen;
	
	static Piece [] wPawns,bPawns;
	
	
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
		board = new Board(ROW,COL);
		wQueen = new Queen (WHITE, "wQ");
		generator = new Path();
		wQueen.setReachableList(new ArrayList<Square>());
		list = new ArrayList<Square>();
		
		barrierBoard = new Board(ROW,COL);
		
		bPawns = new Piece[COL];
		wPawns = new Piece[COL];
		for (int i = 0; i < COL;i++)
			bPawns[i] = new Pawn(BLACK,"bP");
		
		for (int i = 0; i < COL;i++)
			wPawns[i] = new Pawn(WHITE,"wP");
		
		Piece [][]board = new Piece[][] {
				{null,		null,	  null,		null,	 	wPawns[4],	null,	   null,	    null},
				{null,		null,	  null,		bPawns[2], 	null,		null,	   null,	    bPawns[7]},
				{bPawns[0],	null,	  null,		null,	    null,		bPawns[5], bPawns[6],   null},
				{null,	  	null	,null,		bPawns[3],  null,		null,	   null,	    null},
				{null, 		bPawns[1], null,	null,	    null,	    null,  	   null,        null},
				{null,		wPawns[1],null,null,	    null,		null,	   null,	    null},
				{wPawns[0],	null,	  wPawns[2],		null,	   null,		null, 	   wPawns[7],	 	null},
				{null, 		null,	  null,		null,	     bPawns[4],	    null,  	   null,        null}
			};
		barrierBoard.setWholeBoard(board);
		barrierBoard.update();
    }
	
	@Test
	public void testCheckVertical() 
	{
		int targetX,targetY,size1,size2;
		Board b;
		
		targetX = centerX;  targetY = centerY;
		size1 = 3;  size2 = 4;
		b = board;	
		checkVerticalHelper(targetX, targetY, size1, size2, b);
		
		
		 targetX = originX;  targetY = originY;
		 size1 = 7;   size2 = 0;	
		 b = board;	
		checkVerticalHelper(targetX, targetY, size1, size2, b);
		
		 targetX = edgeX; targetY = edgeY;
		 size1 = 7;  size2 = 0;
		 b = board;
		checkVerticalHelper(targetX, targetY, size1, size2, b);
		
		
		targetX = centerX;  targetY = centerY;
		size1 = 3;  size2 = 3;
		b = barrierBoard;
		checkVerticalHelper(targetX, targetY, size1, size2, b);
		
	}

	
	
	@Test
	public void testCheckHorizontal() 
	{	
		int targetX,targetY,size1,size2;
		Board b = board;
		
		targetX = centerX;  targetY = centerY;
		size1 = 4;  size2 = 3;
		CheckHorizontalHelper(targetX, targetY, size1, size2, b);
			
		 targetX = originX; targetY = originY;
		 size1 = 0; size2 = 7;
		CheckHorizontalHelper(targetX, targetY, size1, size2, b);
		
		
		 targetX = edgeX;   targetY = edgeY;
		 size1 = 1; size2 = 6;
		CheckHorizontalHelper(targetX, targetY, size1, size2, b);
		
		
		targetX = centerX;  targetY = centerY;
		size1 = 3; size2 = 3;
		 b = barrierBoard;
		CheckHorizontalHelper(targetX, targetY, size1, size2, b);
	}

	
	
	@Test
	public void testCheckDiagnol() 
	{
		int targetX,targetY,size;
		Board b = board;
		
		targetX = centerX;  targetY = centerY;
		size = 13;
		checkDiagnoalHelper(targetX, targetY, size, b);
		
		targetX = originX; targetY = originY;
		size = 7;
		checkDiagnoalHelper(targetX, targetY, size, b);
		
		targetX = edgeX;  targetY = edgeY;
		size = 7;	
		checkDiagnoalHelper(targetX, targetY, size, b);
		
		
		targetX = centerX;  targetY = centerY;
		b = barrierBoard;   size = 5;
		checkDiagnoalHelper(targetX, targetY, size, b);		
		
	}
	
	
	
	//_________Refractored Helper Functions_________________________
	
	private void CheckHorizontalHelper(int targetX, int targetY, int size1, int size2,
			Board b) {
		b.place_init(wQueen, targetX, targetY);
		list = generator.checkLeft(wQueen, b, targetX, targetY);
		assertEquals(list.size(),size1);
		list = generator.checkRight(wQueen, b, targetX, targetY);
		assertEquals(list.size(),size2);
		if (b.getBoard(6, 0) == null)
			b.resetBoard();
	}
	
	private void checkVerticalHelper(int targetX, int targetY, int size1, int size2, Board b) {
		b.place_init(wQueen, targetX, targetY);
		list = generator.checkUp(wQueen, b, targetX, targetY);
		assertEquals(list.size(), size1);
		list = generator.checkDown(wQueen, b, targetX, targetY);
		assertEquals(list.size(), size2);
		if (b.getBoard(6, 0) == null)
			b.resetBoard();
	}
	
	private void checkDiagnoalHelper(int targetX, int targetY, int size, Board b) {
		b.place_init(wQueen, targetX, targetY);
		list = generator.checkUpLeft(wQueen, b,targetX, targetY);
		list.addAll ( generator.checkUpRight(wQueen,b, targetX, targetY) );
		list.addAll ( generator.checkDownLeft(wQueen, b, targetX, targetY) );
		list.addAll ( generator.checkDownRight(wQueen, b, targetX, targetY) );
		assertEquals(list.size(), size);
		if (b.getBoard(6, 0) == null)
			b.resetBoard();
	}
	
}
