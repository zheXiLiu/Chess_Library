import chess.junit.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JButton;
import java.util.ArrayList;
import java.util.EventObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

public class BoardController {

	// final variables for board configuration
	private static final int BLACK = 1;
	private static final int WHITE = -1;
	private static final int ROW = 8;
	private static final int COL = 8;

	// Board & all Pieces
	private static Board b;
	private static Piece Target;
	private static Piece bKing, bQueen, bKnight1, bKnight2, bRook1, bRook2,
			bBishop1, bBishop2, bChance, bClown;
	private static Piece[] bPawns;
	private static Piece wKing, wQueen, wKnight1, wKnight2, wRook1, wRook2,
			wBishop1, wBishop2, wChance, wClown;
	private static Piece[] wPawns;

	// Player movement variables
	private static int moveX = -1, moveY = -1;
	private static int moveToX = -2, moveToY = -2;
	private static int player, score1, score2;
	private static BoardView view;
	private static int undo, restart = -1;

	public static void main(String[] args) throws IOException {
		default_init(); // init all pieces
		default_place2(); // place all pieces on board
		view = new BoardView(b); // create a board view
		player = view.player; // assign player
		boolean running = true;
		while (running) // This big loop of this game
		{
			view.setCleanButton(); // clean all the Action Listeners
			resetInput();
			addRestartListener();

			String playerColor = (player == 1) ? "BLACK" : "WHITE";
			Piece selfKing = b.getKing(player);

			/*
			 * Check the current status of the game: 1. check 2. checkmate 3.
			 * not in check (and statelmate)
			 */
			int isEnd = b.checkGameEnds(player);
			if (isEnd == 1 || isEnd == 2) {
				String msg = null;
				if (isEnd == 1) {
					msg = " Checkmate! " + (playerColor) + " lose!";
					if (player == 1)
						score2 += 1;
					else
						score1 += 1;
				} else if (isEnd == 2)
					msg = "Stalemate";

				view.setGameEndState(msg, score1, score2);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}// freeze here to alert game ends

				int res = JOptionPane.showConfirmDialog(null,
						"Restart the Game?");
				if (res == 0) {
					default_init();
					default_place();
					view.drawIconConfigration(b);
					continue;
				} else
					break;
			}

			if (isEnd == 3) {
				System.out.println("Check!!");
				view.setCheckState(player);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while (true) // The loop waiting for move input
			{
				view.setCleanState();  //First clean up all static movement variables
				resetInput();
				
				//Wait for player to select a piece to move
				view.setSelectPieceState(selfKing.getLegalPieces(b), player);
				addPieceListener();		
				while (moveX == -1) {
					System.out.println("Wating For Piece Selection");
				}
				Piece p = b.getBoard(moveX, moveY);
				
				//Wait for player to select a target destination square
				view.setSelectTargetState(p.getLegalMove(), p.getCurrX(),p.getCurrY());
				
				view.addTargetListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {

						JButton b = (JButton) event.getSource();
						int height = b.getHeight();
						int width = b.getWidth();
						moveToY = b.getLocation().x / width;
						moveToX = b.getLocation().y / height;
					}
				});

				while (moveToX == -2) {
					System.out.println("Waiting for target Selection..");
				}
				
				/*	If user click on the same piece twice, pretend as nothing ever happened,
				 *  back to select piece state*/
				if (moveToX == moveX && moveToY == moveY) {
					System.out.println("Re-entering");
					view.setUndoState(b);
					resetInput();
					continue;
				}
				Target = b.getBoard(moveToX, moveToY);
				
				b.moveOrder(p, moveToX, moveToY); //Send the piece to target square
				view.drawIconConfigration(b); //Draw
				view.setAfterMoveState(); // Disable all chess piece buttons

				/*
				 * The following events are set available only after player finish a move
				 * He/she can select either to undo, or pass the turn to opponents
				 * */
				view.addRedoListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						undo = 1;
					}
				});
				view.addTurnListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						undo = 2;
					}
				});

				while (undo == -1) {
					System.out.println("Waiting for player's decision");
				}

				if (undo != 1) {
					System.out.println("Pass Turn!");
					resetInput();
					player = -player;
					break;
				} else {  //In undo state
					if (Target != null)
						b.place_init(Target, moveToX, moveToY);
					else
						b.setBoard(moveToX, moveToY, null);
					b.place_init(p, moveX, moveY);
					
					resetInput();
					view.setUndoState(b);
				}
			}
			resetInput(); // cleanup everything
		}
	}

	/**
	 *  Override actionPerformed method and get the selected piece x,y coordinate
	 */
	private static void addPieceListener()
	{
		view.addPieceListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JButton b = (JButton) event.getSource();
				int height = b.getHeight();
				int width = b.getWidth();

				moveY  = b.getLocation().x / width;
				moveX = b.getLocation().y / height;
			}

		});

	}
	
	/**
	 *  Override actionPerformed method and get the target x,y coordinate
	 */
	
	private static void addRestartListener() {
		view.addRestartListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int res = JOptionPane.showConfirmDialog(null,
						"WHITE agrees to restart? ");
				if (res == 0) {
					int res2 = JOptionPane.showConfirmDialog(null,
							"BLACK agrees to restart? ");
					if (res2 == 0) {
						default_init();
						default_place();
						restart = 1;
						undo = 0;
						b.update();
						view.setCleanState();
						view.drawIconConfigration(b);
					}
				}
			}
		});
	}

	/**
	 * Set up the standard board configuration with all chess pieces
	 */
	public static void default_place() {
		Piece board[][] = new Piece[][] {
				{ bRook1, bKnight1, bBishop1, bQueen, bKing, bBishop2,
						bKnight2, bRook2 },
				{ bPawns[0], bPawns[1], bPawns[2], bPawns[3], bPawns[4],
						bPawns[5], bPawns[6], bPawns[7] },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ wPawns[0], wPawns[1], wPawns[2], wPawns[3], wPawns[4],
						wPawns[5], wPawns[6], wPawns[7] },
				{ wRook1, wKnight1, wBishop1, wQueen, wKing, wBishop2,
						wKnight2, wRook2 } };

		b.setWholeBoard(board);
		b.update();
	}

	/**
	 * Self-defined board setup
	 */
	public static void default_place2() {
		Piece board[][] = new Piece[][] {
				{ null, bRook1, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, wQueen, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, bKing, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ wRook1, wKnight1, null, null, wKing, wBishop2, wKnight2,
						wRook2 } };

		b.setWholeBoard(board);
		b.update();
	}

	/**
	 * This function is called at the beginning of each loop clear all the
	 * global values.
	 */
	public static void resetInput() {
		moveX = -1;
		moveY = -1;
		moveToX = -2;
		moveToY = -2;
		undo = restart = -1;
	}

	/**
	 * @return the Board object
	 */
	public static Board getBoard() {
		return b;
	}

	/**
	 * Instantiate all the chess piece objects for both black & white
	 */
	public static void default_init() {
		b = new Board(ROW, COL);
		bKing = new King(BLACK, "bk");
		bQueen = new Queen(BLACK, "bQ");
		bBishop1 = new Bishop(BLACK, "bB");
		bBishop2 = new Bishop(BLACK, "bB");
		bRook1 = new Rook(BLACK, "bR");
		bRook2 = new Rook(BLACK, "bR");
		bKnight1 = new Knight(BLACK, "bH");
		bKnight2 = new Knight(BLACK, "bH");

		bPawns = new Pawn[COL];
		for (int i = 0; i < COL; i++)
			bPawns[i] = new Pawn(BLACK, "bP");

		wKing = new King(WHITE, "wk");
		wQueen = new Queen(WHITE, "wQ");
		wBishop1 = new Bishop(WHITE, "wB");
		wBishop2 = new Bishop(WHITE, "wB");
		wRook1 = new Rook(WHITE, "wR");
		wRook2 = new Rook(WHITE, "wR");
		wKnight1 = new Knight(WHITE, "wH");
		wKnight2 = new Knight(WHITE, "wH");

		wPawns = new Pawn[COL];
		for (int i = 0; i < COL; i++)
			wPawns[i] = new Pawn(WHITE, "wP");

		// ___________Costume piece__________
		wChance = new Chancellor(WHITE, "wC");
		wClown = new Clown(WHITE, "wL");
		bClown = new Clown(BLACK, "bL");
		bChance = new Chancellor(BLACK, "bC");
	}
}
