package chess.junit;

import java.util.ArrayList;

/**
 * @author Zhexi
 *  Chancellor is extended from Knight class. This piece combines the
 *  power of a Rook and a Knight.
 */
public class Chancellor extends Knight {

	public Chancellor(int color, String chessname) 
	{
		super(color, chessname);
		int type = (color == 1)? 7:-7;
		setType(type);
	}
	
	void updateReachable(Board b) 
	{
		ArrayList<Square> List = new ArrayList<Square>();
	
		checkLshape(this, b,getCurrX()+2,getCurrY()+1,List);
		checkLshape(this,b,getCurrX()+2,getCurrY()-1,List);
		checkLshape(this,b,getCurrX()-2,getCurrY()+1,List);
		checkLshape(this,b,getCurrX()-2,getCurrY()-1,List);
		checkLshape(this,b,getCurrX()+1,getCurrY()+2,List);
		checkLshape(this,b,getCurrX()+1,getCurrY()-2,List);
		checkLshape(this,b,getCurrX()-1,getCurrY()+2,List);
		checkLshape(this,b,getCurrX()-1,getCurrY()-2,List);
		
		Path generator = new Path();
		List.addAll(generator.checkUp (this,b,getCurrX(), getCurrY())) ;
		List.addAll(generator.checkDown (this,b,getCurrX(), getCurrY())) ;
		List.addAll(generator.checkRight (this,b,getCurrX(), getCurrY())) ;
		List.addAll(generator.checkLeft (this, b,getCurrX(), getCurrY())) ;	
		
		
		setReachableList (List);
	}

}
