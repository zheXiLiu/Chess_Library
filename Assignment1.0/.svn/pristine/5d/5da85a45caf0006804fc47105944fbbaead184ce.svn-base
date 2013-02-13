
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
import java.awt.event.* ;

import javax.swing.*;
 
/**
 * @author Zhexi
 * This class renders the whole chess game in game loop.
 */
public class BoardView {
 
	//GUI display components.
	private static JFrame window ; // The outer most window frame	
	private static JPanel statusPanel;//Current Game status, locate on top
	private static JPanel myPanel; //Chess board
	private static JPanel adminPanel;//Side bar for undo, restart, turn button
	private static JButton pieces[][], restartButton, redoButton, turnButton;//All the buttons 
	private static JLabel player1,player2, status; // Player names, scores
	private static JDialog pop;// Pop up window at the begining
	
	
	private static Board board;	
	private static ArrayList<Square> list,targetList = null;
	private static int targetX, targetY;
	private static String name1, name2 = null;
	static int player;
	private static int score1;
	private static int score2;
	private static Color hint = new Color(173,255,47);
	private static Color c2 = new Color(254,206,160);
	private static Color c3 = new Color(139,69,18);

    /**
     * @param board  Pass in a board configuration 
     * Initialize & draw
     */
    public BoardView(Board board){
  
        this.board = board;        
        window = new JFrame("Chess Game");
        window.setSize(600, 520);
      
        // Set up 3 panels for chess board, scores and players 
        initializeBoardPanel(); 
        initializeAdminPanel();
        initalizeStatusPanel();
        
        initializeButton(myPanel); // Set up 64 buttons for chess board squares
        getPlayerInfo();
        setStatus();
        addPanels();
     
        drawIconConfigration(board); // render everything
      
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    }
    
    /**
     * @return a panel with 8*8 grid drawing of the chessboard
     */
    private void initializeBoardPanel() 
    {
        myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(500,500));
        myPanel.setLayout(new GridLayout(8,8));       
    }
    
    /**
     *  This panel contains player information, redo, restart and turn buttons
     */
    private void initializeAdminPanel() 
    {
    	adminPanel = new JPanel();
        adminPanel.setPreferredSize(new Dimension(100,500));
        adminPanel.setLayout(new GridLayout(5,1));  
    }
    
    /**
     *  This panel displays the current status of the game
     */
    private void initalizeStatusPanel()
    {
    	 statusPanel = new JPanel();
         statusPanel.setBackground(Color.yellow);
         adminPanel.setPreferredSize(new Dimension(120,50));
    }
    
    
    /**
     *  Pop up window for user input. 
     *  Display player info in side status panel
     */
    private void getPlayerInfo()
    {
    	StartGUI admin = new StartGUI();
        while (name1 == null || name2 == null)
        {
        	name1 = admin.update(0);
            name2 = admin.update(1);
        	System.out.println("REtrived Color> " +player);
        }
        
         player = (admin.retlist[2].equals ("white"))?-1:1;   
         player1 = new JLabel("<html><p>"+name1+"</p><br><p>Score: " + score1+"</p></html>");
         player2 = new JLabel("<html><p>"+name2+"</p><br><p>Score: " + score2+"</p></html>");
         
         adminPanel.add(player1);
         adminPanel.add(restartButton);
         adminPanel.add(turnButton);
         adminPanel.add(redoButton);
         adminPanel.add(player2);
    }
  
    
    /**
     * @param myPanel The current panel that is drawing on
     * Initialize JButton settings
     */
    private void initializeButton(JPanel myPanel) 
    {
    	 pieces = new JButton[8][8];
         
         int count = -1;
         for(int i = 0; i<8;i++)
         {
     	   for(int j = 0; j < 8; j++)
     	   {
     		   pieces[i][j] = new JButton("");   		   	
     		   myPanel.add(pieces[i][j]);	 
     	   }
     	  
     	   count = -count;
         }       
         
         restartButton = new JButton("Restart");
         redoButton =  new JButton("Redo");
         turnButton = new JButton("Turn");
    }
    
    /**
     *  Set the current status of the game: 
     *  1. white/black moves next   2. checkmate  3. stalemeate
     */
    private void setStatus()
    {
    	status = new JLabel("Current Game Status");      
        status.setFont(new Font("Helvetica", Font.PLAIN, 18));
        statusPanel.add(status);
    }
    
    
    /**
     * Add all 3 panels to the main window frame
     */
    private void addPanels()
    {
    	  window.add(statusPanel,BorderLayout.NORTH );
          window.add(myPanel,BorderLayout.CENTER);
          window.add(adminPanel,BorderLayout.EAST);    
    }
 
  
    //______________________Set State Functions________________________
   
  
    /**
     * @param b  The current chess board
     * @param startplayer  the player to move next 
     * This is the begin of each turn
     */
    public void setInitialState(Board b,int startplayer)
    {
    	setCleanState();
    	drawIconConfigration(b);
    	setSelectPieceState(b.getKing(startplayer).getLegalMove(), startplayer);
    }
    
    /**
     * @param list  A list of legal target squares
     * @param player the player to move next
     * This is the state to select the piece to move
     */
    public void setSelectPieceState(ArrayList<Square> list, int player)
    {
    	this.list = list;
    	Icon img = null;
    	
    	//Change chess buttons appearances 
    	for (int i = 0; i < 8; i ++)
    	{
    		for (int j = 0; j < 8 ; j ++)
    		{
    			if ( listContains(list, i, j))
    				pieces[i][j].setEnabled(true);
    			else
    			{
    				img= pieces[i][j].getIcon();
    				pieces[i][j].setDisabledIcon(img);
    				pieces[i][j].setEnabled(false);
    			}		
    		}
    	}
    	
    	redoButton.setEnabled(false);
    	turnButton.setEnabled(false);
    	
    	//Change play info display
    	if (player == -1)
    	{
    		player2.setOpaque(true);
    		player2.setBackground(hint);
    		player1.setBackground(null);
    		status.setText("WHITE moves next");
    		status.setBackground(null);
    	}
    	else
    	{
    		player1.setOpaque(true);
    		player1.setBackground(hint);
    		player2.setBackground(null);
    		status.setText("BLACK  moves next");
    		status.setBackground(null);
    	}
    
    }
    
    /**
     * @param list  A list of legal target squares to move
     * @param x  the x coordinate of current selected piece
     * @param y the y coordinate of current selected piece
     * After select a piece, players have to select a target square
     */
    public void setSelectTargetState(ArrayList<Square>list ,int x,int y)
    {
    	Icon img = null;
    	this.list = list;
    	if (targetList == null)
    		targetList = list;
    	
    	targetX = x; targetY = y;
    	
    	//Change chess baord apperence
    	for (int i = 0; i < 8; i ++)
    	{
    		for (int j = 0; j < 8 ; j ++)
    		{
    			if ( listContains(list, i, j))
    			{
    				pieces[i][j].setEnabled(true);
    				pieces[i][j].setBackground(hint);
    			}
    			else
    			{
    				img= pieces[i][j].getIcon();
    				pieces[i][j].setDisabledIcon(img);
    				pieces[i][j].setEnabled(false);
    			}		
    		}
    	}
    	pieces[x][y].setEnabled(true);
    	pieces[x][y].setBackground(Color.yellow);
    	
    	redoButton.setEnabled(false);
    	turnButton.setEnabled(false);
    }
    
    /**
     *  This is the state when players have finished their moves
     */
    public void setAfterMoveState( )
    {
    	Icon img = null;
    	for (int i = 0; i < 8 ; i ++)
    	{
    		for (int j = 0; j < 8; j ++)
    		{
    			img = pieces[i][j].getIcon();
    			pieces[i][j].setDisabledIcon(img);
    			pieces[i][j].setEnabled(false);
      		   
    		}
    	}
    	
    	redoButton.setEnabled(true);
    	turnButton.setEnabled(true);
    	 System.out.println("In afer state");
    }
    /**
     * @param player the player to move next
     * This state means that player is currently in check
     */
    public void setCheckState(int player)
    {
    	status.setOpaque(true);
    	status.setBackground(Color.red);
        status.setFont(new Font("Helvetica", Font.PLAIN, 20));
       
        String now = (player == 1)? "BLACK":"WHITE";
        status.setText(now+ " moves now, Check!! ");
    }
    
    
    /**
     * @param msg message to display
     * @param score1 the score of player1
     * @param score2 the score of player2
     * when checkmate or stalemate, the score of winner increase by one
     */
    public void setGameEndState(String msg , int score1, int score2)
    {
    	status.setOpaque(true);
    	status.setBackground(Color.red);
        status.setFont(new Font("Helvetica", Font.PLAIN, 20));
       
        player1.setText("<html><p>"+name1+"</p><br><p>Score: " + score1+"</p></html>");
        player2 .setText("<html><p>"+name2+"</p><br><p>Score:" + score2+"</p></html>");
        status.setText(msg);
    }
    
    /**
     * clean up the action listeners on the current chess board
     */
    public void setCleanState()
    {
    	for (int i = 0; i < 8 ; i++)
    	{
    		for (int j = 0 ; j < 8 ; j++)
    			
    			 for( ActionListener al : pieces[i][j].getActionListeners() ) {
    				 pieces[i][j].removeActionListener( al );
    			    }
    	}
    }
    
    /**
     * clean up the action listeners on the side admin panel
     */
    public void setCleanButton()
    {
    	
    	for( ActionListener al : restartButton.getActionListeners() ) 
    	{
    		restartButton.removeActionListener( al );
    	}
    	for( ActionListener al : redoButton.getActionListeners() ) 
    	{
    		restartButton.removeActionListener( al );
    	}
    	for( ActionListener al : turnButton.getActionListeners() ) 
    	{
    		restartButton.removeActionListener( al );
    	}
    }
    
    /**
     * @param p the attacker
     * @param x the target's x coordinate
     * @param y the target's y coordinate
     */
    public void setAttackState( Piece p, int x, int y)
    {
    	Icon img = pieces[p.getCurrX()][p.getCurrY()].getIcon();
    	pieces[x][y].setIcon(img);
    	 System.out.println("In attack state");
    }
    
  
    
    /**
     * @param b the current board configuration
     */
    public void setUndoState(Board b)
    {
    	 drawIconConfigration(b);
    }
    
  
    
 //_________________Overide ActionListeners____________________
    /**
     * @param a  action listener
     * This method is called before the player needs to select a piece to move
     * It adds ActionListeners to the buttons in the legalPiece list
     */
    public void addPieceListener(ActionListener a) { 
    	
    	for (int i = 0; i < 8; i ++)
    	{
    		for (int j = 0; j < 8 ; j ++)
    		{
    			if ( listContains(list, i, j))
    				pieces[i][j].addActionListener(a);
    		}
    	}
      }
    
 /**
 * @param a  action listener
 * This method is called before player needs to select a target square
 * It adds ActionListeners to the buttons in the targetList.
 */
public void addTargetListener(ActionListener a) { 
	 	Square s = new Square(targetX, targetY,null);
	 	list.add(s);
    	for (int i = 0; i < 8; i ++)
    	{
    		for (int j = 0; j < 8 ; j ++)
    		{
    			if ( listContains(list, i, j))
    				pieces[i][j].addActionListener(a);
    		}
    	}
      }
 
    /**
     * Add ActionListener on Turn Button
     */
    public void addTurnListener(ActionListener a) { 
    	turnButton.addActionListener(a);
      }
    
    /**
     * Add ActionListener on Redo Button
     */
    public void addRedoListener(ActionListener a) { 
    	redoButton.addActionListener(a);
      }
    
     /**
     * Add ActionListener on restart Button
     */
    public void addRestartListener(ActionListener a) { 
    	restartButton.addActionListener(a);
      }
    

    //______________________Rest of the drawings__________________________
    /**
     * @param b the current chess board 
     * at the beginning of each turn, draw the board configuration
     */
    public void drawIconConfigration(Board b)
    {
    	for (int i = 0; i < b.getRow(); i++)
		{
			for (int j = 0; j < b.getCol(); j++)
			{				
				if (b.getBoard(i,j)!=null)
				{
					int type = b.getBoard(i,j).getType();
					drawSingleIcon(i,j,type);
					
				}
				else
				{
					pieces[i][j].setIcon(null);
				}
				
				 if ((i+j)%2 == 1)
	      			   pieces[i][j].setBackground(c3);
	      		   else
	      			  pieces[i][j].setBackground(c2);
				
			}
		}
    }
  
    
    /**
     * @param i x coordinate of chess piece
     * @param j y coordinate of chess piece
     * @param type the type of chess piece
     * This function draws a single chess icon image.
     */
    public void drawSingleIcon(int i, int j, int type)
    {
    	ImageIcon img = null;
		String imgName = null;
		switch (type)
		{
			case -1:
				imgName = "/img/wKing.png";
				break;
		
			case 1:
				imgName = "/img/bKing.png";
				break;
			
			case -2:
				imgName = "/img/wQueen.png";
				break;
			case 2:
				imgName = "/img/bQueen.png";
				break;
				
			case -3:
				imgName = "/img/wRook.png";
				break;
			case 3:
				imgName = "/img/bRook.png";
				break;
				
			case -4:
				imgName = "/img/wBishop.png";
				break;
			case 4:
				imgName = "/img/bBishop.png";
				break;
				
			case -5:
				imgName = "/img/wKnight.png";
				break;
			case 5:
				imgName = "/img/bKnight.png";
				break;
			case -6:
				imgName = "/img/wPawn.png";
				break;
			case 6:
				imgName = "/img/bPawn.png";
				break;
				
			case -7:
				imgName = "/img/wChance.png";
				break;
			case 7:
				imgName = "/img/bChance.png";
				break;
			case -8:
				imgName = "/img/wClown.png";
				break;
			case 8:
				imgName = "/img/bClown.png";
				break;
		}
		img = new ImageIcon(getClass().getResource(imgName));
		pieces[i][j].setIcon(img);
		pieces[i][j].setDisabledIcon(img);
		
    }
    
    
    public boolean listContains(ArrayList<Square> list, int x, int y)
    {
    	for (int i = 0 ; i < list.size(); i++)
    	{
    		Square s = list.get(i);
    		if (s.x  == x && s.y == y)
    			return true;
    	}
    	return false;
    }
    
    
    
    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        
        JMenu game = new JMenu("Game");
        JMenu help = new JMenu("Help");
        
        JMenuItem newGame = new JMenuItem("New Game");
      
        JMenuItem pause = new JMenuItem("Pause");
    
        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem instruction = new JMenuItem("Instructions");
        JMenuItem about = new JMenuItem("About");
   
        
        game.add(newGame);
        game.add(pause);
        game.add(quit);
        help.add(instruction);
        help.add(about);
        
        menubar.add(game);
        menubar.add(help);
        window.setJMenuBar(menubar);
    }
     
 
}


