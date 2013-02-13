
import chess.junit.*;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;

public class main {
	
	private static final int BLACK = 1;
	private static final int WHITE = -1;
	private static final int ROW = 8;
	private static final int COL = 8;
	
	private static Board b;
	private static Piece bKing,bQueen,bKnight1,bKnight2, bRook1,bRook2,bBishop1,bBishop2,bChance,bClown;
	private static Piece [] bPawns; 
	private static Piece wKing,wQueen,wKnight1,wKnight2, wRook1,wRook2, wBishop1,wBishop2,wChance,wClown;
	private static Piece [] wPawns;
	
	private static MyGUI g;
	
	
	public static void main(String [] args) throws IOException
	{
		default_init();
		default_place();
		g = new MyGUI(b);
	
		System.out.println("Who plays first? -1: White  1: Black");
		BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		String input = reader.readLine();
		int player = Integer.parseInt(input);
	
			boolean running = true;
			while (running)
			{
				
				String playerColor = (player == 1)? "BLACK":"WHITE";
				int isEnd = b.checkGameEnds(player);
				
				
				if (isEnd != 0)
				{
					
					if (isEnd == 1) System.out.println("Checkmate!" + (playerColor)+ " lose!");
					else  System.out.println("Stalemate");
					break;
				}
				
				
				Piece selfKing = b.getKing(player);
				System.out.println(playerColor+", please select the piece to move, press h for hints: ");
								
				String move =  new BufferedReader(new InputStreamReader (System.in)).readLine();
				if (move.equals("h"))
				{
					selfKing.printLegalMove(b);
				}
				else
				{
					int moveNum = Integer.parseInt(move);
					int moveX = moveNum/10;
					int moveY = moveNum%10;
					
					boolean format = b.inputPieceCheck(moveX,moveY,player);
					
					if (format == false)
					{
						System.out.print("Move Rejected!! ");
						continue;
					}
					
					Piece p = b.getBoard(moveX,moveY);
					format = p.listContains( new Square(moveX,moveY,null) , p.getLegalPieces(b));
					if (format == false)
					{
						System.out.print("This piece can not be moved!! ");
						continue;
					}
					
					System.out.println(p.getName() + " check " + p.getCurrX()+p.getCurrY());
					p.printlist(p.getLegalMove());
					
					while(true)
					{
						System.out.println(playerColor+" move piece "+moveX+moveY + " to: ");
						String moveTo =  new BufferedReader(new InputStreamReader (System.in)).readLine();
					
						int moveToNum = Integer.parseInt(moveTo);
						int moveToX = moveToNum/10;
						int moveToY = moveToNum%10;	
						System.out.println("Move to inpit: " + moveToX + moveToY);
						
						
						
						boolean logic = p.listContains(new Square(moveToX,moveToY,null), p.getLegalMove());
					
						if (logic == false)
						{
							continue;
						}
						else
						{
							b.moveOrder(p, moveToX , moveToY );
							break;
						}				
					}
					b.printBoard();
					g.drawIconConfigration(b);
					
					player = -player;
					
				}
			}

		}
	
	public static void default_init()
	{
		b = new Board(ROW, COL);
		bKing = new King(BLACK,"bk");
		bQueen = new Queen(BLACK, "bQ");
		bBishop1 = new Bishop(BLACK, "bB");
		bBishop2 = new Bishop(BLACK, "bB");
		bRook1 = new Rook(BLACK, "bR");
		bRook2 = new Rook(BLACK, "bR");
		bKnight1 = new Knight(BLACK, "bH");
		bKnight2 = new Knight(BLACK, "bH");
		
		bPawns = new Pawn[COL];
		for (int i = 0; i < COL;i++)
			bPawns[i] = new Pawn(BLACK,"bP");	
		
		wKing = new King (WHITE, "wk");
		wQueen = new Queen (WHITE,"wQ");
		wBishop1 = new Bishop(WHITE, "wB");
		wBishop2 = new Bishop(WHITE, "wB");
		wRook1 = new Rook(WHITE, "wR");
		wRook2 = new Rook(WHITE, "wR");
		wKnight1 = new Knight(WHITE, "wH");
		wKnight2 = new Knight(WHITE, "wH");
		
		wPawns = new Pawn[COL];
		for (int i = 0; i < COL;i++)
			wPawns[i] = new Pawn(WHITE,"wP");
		
		//___________Costume piece__________
		wChance = new Chancellor(WHITE, "wC");
		wClown = new Clown(WHITE, "wL");
		bClown = new Clown (BLACK, "bL");
		bChance = new Chancellor(BLACK, "bC");
	}
	
	public static void default_place()
	{
		Piece board[][]= new Piece[][]
				{
			{bRook1,	bKnight1,	bBishop1,	bQueen, 	bKing,	bBishop2,bKnight2, bRook2 },
			{bPawns[0],	bPawns[1],	bPawns[2],	bPawns[3],	bPawns[4],	bPawns[5],  bPawns[6], bPawns[7] },
				{null,  null,   null,   null,  null,   null,   null, null },
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{wPawns[0],	wPawns[1],	wPawns[2],	wPawns[3],	wPawns[4],	wPawns[5],   wPawns[6], wPawns[7] },
				{wRook1,wKnight1,wBishop1,wQueen,wKing,wBishop2,wKnight2,wRook2}
				};
		
		b.setWholeBoard(board);
		b.update();
		
	}

		 
	}

