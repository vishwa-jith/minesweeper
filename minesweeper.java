import java.lang.*;
import java.awt.event.*;
import java.awt.*;
import java.applet.*;
import java.util.*;
import java.util.Random.*;
import javax.swing.*;

/*
<applet code="minesweeper" width=800 height=800>
</applet>
*/
public class minesweeper extends Applet
implements ActionListener
{
	static char z[][]=new char[100][100];
    	static int Number_of_rows,Number_of_columns,number_of_mines,Factor,FontSize;
    	public Button B[][]=new Button[100][100];
    	public static void input()
    	{
		JFrame frame = new JFrame("..........WELCOME..........");
    		String name = JOptionPane.showInputDialog(frame, "1) Easy 2) Moderate 3) Difficult");
		if(name.equals("1"))	//Lvl 1
		{
			Number_of_rows=9;	//Number of rows
			Number_of_columns=9;	//Number of columns
			number_of_mines=10;	//Number of mines
			FontSize=40;	//FontSize
			Factor=9;	//Unlocking factor
		}
		else if(name.equals("2"))
		{
			Number_of_rows=16;	//Number of rows
			Number_of_columns=16;	//Number of columns
			number_of_mines=40;	//Number of mines
			FontSize=35;	//FontSize
			Factor=10;	//Unlocking factor
		}
		else if(name.equals("3"))
		{
			Number_of_rows=24;	//Number of rows
			Number_of_columns=24;	//Number of columns
			number_of_mines=99;	//Number of mines
			FontSize=25;	//FontSize
			Factor=11;	//Unlocking factor
		}
		else
		{
			System.exit(0);	//Termination 
		}
		for(int i=0;i<Number_of_rows+1;i++)
		{
			for(int j=0;j<Number_of_columns+1;j++)
			{
				z[i][j]='0';	//Initialization
			}
		}	
        	Random rand1=new Random();	
        	for(int i=0;i<number_of_mines;i++)	
        	{		
            		int l= rand1.nextInt(Number_of_rows)+1;	//Random row index
            		int k= rand1.nextInt(Number_of_columns)+1;	//Random column index
            		z[l][k]='X';	//Setting mines
        	}
        	for(int i=0;i<Number_of_rows+1;i++)
        	{
			for(int j=0;j<Number_of_columns+1;j++)
            		{
               			try
               			{
               				if(z[i][j]!='X')	//8 different cases for mine adjacency
               				{
                   				if(z[i-1][j]=='X')
                   				{
                       					z[i][j]+=1;
                    				}
                   				if(z[i+1][j]=='X')
                   				{
                       					z[i][j]+=1;
                    				}
                   				if(z[i][j-1]=='X')
                   				{
                       					z[i][j]+=1;
                   				}
                   				if(z[i][j+1]=='X')
                   				{
                       					z[i][j]+=1;
                   				}
                   				if(z[i+1][j+1]=='X')
                   				{
                       					z[i][j]+=1;
                    				}
                   				if(z[i-1][j-1]=='X')
                   				{
                       					z[i][j]+=1;
                    				}
		   				if(z[i-1][j+1]=='X')
		   				{
							z[i][j]+=1;
		   				}
		   				if(z[i+1][j-1]=='X')
		   				{
							z[i][j]+=1;
						}
               				}
               			}
               			catch(ArrayIndexOutOfBoundsException e)
               			{}
            		}
		}	            
	}
    	public void init()
    	{
        	input();	//Setting mines at Random
		setLayout(new GridLayout(Number_of_rows,Number_of_columns));	//Setting GridLayout
		for(int i=1;i<Number_of_rows+1;i++)
        	{
			for(int j=1;j<Number_of_columns+1;j++)
        		{
		        	B[i][j]=new Button();	//Creation of button
		        	add(B[i][j]);	//adding button
                		try
				{
                			B[i][j].setBackground(Color.orange);	//Setting up background
                			B[i][j].addActionListener(this);	//Setting up ActionListener
                			if(z[i][j]=='X')
                			{
                				B[i][j].setActionCommand("Color.red");	//Setting ActionCommand for mines
                			}
                			else
                			{
                    				B[i][j].setActionCommand(Character.toString(z[i][j])); //Setting ActionCommand other than mines
                			}
            			}
            			catch(ArrayIndexOutOfBoundsException e)
            			{}
            		}
        	}
    	}
    	public void actionPerformed(ActionEvent e)
    	{
		Font f=new Font("Monotype Corsiva",Font.BOLD,FontSize);
		setFont(f);	//Setting font
		if(e.getActionCommand().equals("Color.red"))	//There exist a mine
        	{
            		for(int i=1;i<Number_of_rows+1;i++)
            		{
                		for(int j=1;j<Number_of_columns+1;j++)
                		{
                    			if(B[i][j].getActionCommand().equals("Color.red"))	//Finding all existing mines
                    			{
                        			B[i][j].setBackground(Color.red);	//Setting all mines to red color
                    			}
                		}
            		}
        	}
        	else if(e.getActionCommand().equals("0"))	//There exist no mines
        	{
	    		int x=Number_of_rows/Factor;
            		for(int i=1;i<Number_of_rows+1;i++)
            		{
                		for(int j=1;j<Number_of_columns+1;j++)
                		{
					if(B[i][j]==e.getSource()) 	//Getting source button
					{
						B[i][j].setBackground(Color.green);	//Setting current source to green color
						B[i][j].setLabel("0");	
						int c,d;
						c=i-x;
						d=j-x;
						if(c<1)
							c=1;
						if(d<1)
							d=1;
						int u,v;
						u=i+x;
						v=j+x;
						if(u>Number_of_rows)
							u=Number_of_rows;
						if(v>Number_of_columns)
							v=Number_of_columns;
						for(int k=1;k<Number_of_rows+1;k++)
	    					{
							for(int l=1;l<Number_of_columns+1;l++)
							{
								if(k>=c && k<=u && l>=d && l<=v)	//Setting the range of elements to exist
								{
									if(B[k][l].getActionCommand().equals("0"))	
									{		
										B[k][l].setBackground(Color.green);
										B[k][l].setLabel(B[k][l].getActionCommand());
									}
									else if(!B[k][l].getActionCommand().equals("Color.red"))
									{
										B[k][l].setBackground(Color.yellow);
										B[k][l].setLabel(B[k][l].getActionCommand());
									}
								}
							}
		   				}
					}
                		}
            		}
		}
	     	else	//There exist a adjacent mine
	        {
	     		for(int i=1;i<Number_of_rows+1;i++)
	            	{
	                	for(int j=1;j<Number_of_columns+1;j++)
	                	{
	                    		if(e.getSource()==B[i][j])
        	            		{
						String s=e.getActionCommand();
						B[i][j].setBackground(Color.yellow);	
						//Setting all other elements except mines and no mines to yellow color
                        			B[i][j].setLabel(s);
						break;
                			}
                		}
            		}
        	}
    	}
}
            

        
        
        
        
        
        
