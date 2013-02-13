
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;
import java.awt.*;
 

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;



import javax.swing.JTextField;
import javax.swing.JLabel;


 


/**
 * @author Zhexi
 *  This window pops up when players first launch this application
 *  It asks basic player.
 */
public class StartGUI extends JFrame
{
	  JFrame jf;
	  JButton jb1;
	  JPanel jp1,jp2,jp3,jp4;
	  JLabel jl1,jl2,jl3;
	  JTextField jt1,jt2;
	  CheckboxGroup players;
	  Checkbox player1;
	  Checkbox player2;
	  
	  String retlist[];
	  
	  StartGUI()
	  {	  
		  
		  jf = new JFrame("Please enter names");
		  
		  jp1 = new JPanel();
		  jp2 = new JPanel();
		  jp3 = new JPanel();
		  jp4 = new JPanel();
		  
		  jl1 = new JLabel("Playing BLACK: ");
		  jl2 = new JLabel("Playing WHITE: ");
		  jl3 = new JLabel("First: ");
		  
		  jt1 = new  JTextField(10);
		  jt2 = new  JTextField(10);
		  
		  retlist = new String[3];
		  jb1 = new JButton("Start");
		  jb1.addActionListener(new ActionListener() {
			  
	            public void actionPerformed(ActionEvent e)
	            {
	                //Execute when button is pressed
	            	JButton b = (JButton)e.getSource();
	    	    	
	            	retlist[0] = jt1.getText();
	            	retlist[1] = jt2.getText();
	    	    	
	    	    	retlist[2] = players.getSelectedCheckbox().getLabel();
	    	    	System.out.println(retlist[2] + " plays first " + retlist[0] + " " + retlist[1]);
	    	    	
	    	    	jf.setVisible(false);
	    	    	 
	            }
	        });  
		  
		  players=new CheckboxGroup();
		  player1 =new Checkbox("white",true,players);
		  
		  player2 =new Checkbox("black",false,players);
		  
		 
		  jf.setLayout(new GridLayout(6,1));
		  
		  jp2.add(jl1);
		  jp2.add(jt1);
		  jp3.add(jl2);
		  jp3.add(jt2); 
		  jp4.add(jl3);
		  jp4.add(player1);
		  jp4.add(player2);
		  
		  jp4.add(jb1);
		  
		  jf.add(jp1);
		  jf.add(jp2);
		  jf.add(jp3);
		  jf.add(jp4);
		  
		  jf.setSize(360,240);
		  jf.setVisible(true);
		  
		  
		  jf.setLocation(200,200);
		 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
		 
	  }
	  
	  
	  public String update(int n)
	  {
		  return retlist[n];
	  }
	 
	  
	  public static void main (String [] args)
	  {
		  StartGUI  g = new StartGUI();
	  }

}


