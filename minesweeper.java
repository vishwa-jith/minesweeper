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
    	static int n,m,n1,m1;
    	public Button B[][]=new Button[100][100];
    	public static void input()
    	{
		JFrame frame = new JFrame("..........WELCOME..........");
    		String name = JOptionPane.showInputDialog(frame, "1) Easy 2) Moderate 3) Difficult");
		if(name.equals("1"))	//Lvl 1
		{
			n=9;	//Number of rows
			n1=6;	//2*Number of mines
			m1=9;	//Unlocking factor
			m=9;	//Number of columns
		}
		else if(name.equals("2"))
		{
			n=11;	//Number of rows
			n1=5;	//Mine factor
			m1=10;	//Unlocking factor
			m=11;	//Number of columns
		}
		else if(name.equals("3"))
		{
			n=13;	//Number of rows
			n1=4;	//Mine factor
			m1=11;	//Unlocking factor
			m=13;	//Number of columns
		}
		else
		{
			System.exit(0);	//Termination condition
		}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				z[i][j]='0';	//Initialization
			}
		}	
        	Random rand1=new Random();	
        	for(int i=0;i<(n*m)/n1;i++)	
        	{		
            		int l= rand1.nextInt(n);	//Random row index
            		int k= rand1.nextInt(m);	//Random column index
            		z[l][k]='X';	//Setting mines
        	}
        	for(int i=0;i<n;i++)
        	{
			for(int j=0;j<m;j++)
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
		setLayout(new GridLayout(n,m));	//Setting Layout
		for(int i=0;i<n;i++)
        	{
			for(int j=0;j<m;j++)
        		{
		        	B[i][j]=new Button();	//Creation of button
		        	add(B[i][j]);	//adding of button
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
		Font f=new Font("Monotype Corsiva",Font.BOLD,40);
		setFont(f);	//Setting font
		if(e.getActionCommand().equals("Color.red"))	//There exist a mine
        	{
            		for(int i=0;i<n;i++)
            		{
                		for(int j=0;j<m;j++)
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
	    		int i,j,x=n/m1,k,l;
            		for(i=0;i<n;i++)
            		{
                		for(j=0;j<m;j++)
                		{
					if(B[i][j]==e.getSource()) 	//Getting source button
					{
						B[i][j].setBackground(Color.green);	//Setting current source to green color
						B[i][j].setLabel("0");	
						int c,d;
						c=i-x;
						d=j-x;
						if(c<0)
							c=0;
						if(d<0)
							d=0;
						int u,v;
						u=i+x;
						v=j+x;
						if(u>n)
							u=n-1;
						if(v>m)
							v=m-1;
						for(k=0;k<n;k++)
	    					{
							for(l=0;l<m;l++)
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
	     		for(int i=0;i<n;i++)
	            	{
	                	for(int j=0;j<m;j++)
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
            

        
        
        
        
        
        
