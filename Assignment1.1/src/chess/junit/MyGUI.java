package chess.junit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;
 
 
/**
 * @author Zhexi
 * This class renders the whole chess game in game loop.
 */
public class MyGUI implements ActionListener{
 
	private static Board board;
	private static JFrame window ;
	private static JButton pieces[][];

    /**
     * @param board  Pass in a board configuration 
     * Initialize & draw
     */
    public MyGUI(Board board){
  
        this.board = board;        
        window = new JFrame("Chess Game");
        window.setSize(500, 500);
        JPanel myPanel = initializePanel();
        initializeButton(myPanel);
        setUpMenu(window);
        
        window.add(myPanel);
        drawIconConfigration(board);
        
       
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    }
    
   
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
				
				
			}
		}

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
     		   	
     		   Color c1 = new Color(209,139,70);
     		   Color c2 = new Color(254,206,160);
     		   
     		   if (count == 1)
     			   pieces[i][j].setBackground(c1);
     		   else
     			  pieces[i][j].setBackground(Color.BLACK);
     		   	
     		  
     		  count = -count;
     		 pieces[i][j].addActionListener(this);
     	
     		   myPanel.add(pieces[i][j]);
     		   
     	   }
     	   count = -count;
         }
        
    }
 
    /**
     * @return a new JPanel for drawing
     */
    private JPanel initializePanel() {
        JPanel myPanel = new JPanel();
        myPanel.setPreferredSize(new Dimension(400,400));
        myPanel.setLayout(new GridLayout(8,8));  
        return myPanel;
    }
 
    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        
        JMenu game = new JMenu("Game");
        JMenu help = new JMenu("Help");
        
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(this);
        JMenuItem pause = new JMenuItem("Pause");
        pause.addActionListener(this);
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
     
    @Override
    public void actionPerformed(ActionEvent e) {
       /*
    	*/
    	
    	JButton b = (JButton)e.getSource();
    	int height = b.getHeight();
    	int width = b.getWidth();
    	
    	int yy = b.getLocation().x/width;
    	int xx = b.getLocation().y/height;
    	
    	JOptionPane.showMessageDialog(null,
                "I was clicked by "+xx+" " +yy,
                "Title here", JOptionPane.INFORMATION_MESSAGE);
    	
    	
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
		
    }
    
    
    public int mod (int num)
    {
    	return num;
    }
    
    
    public static void main(String[] args) {   
    	
    }
}


