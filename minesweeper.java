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
	public boolean g;
    	public Button B[][]=new Button[100][100];
    	public static void input()
    	{
		JFrame frame = new JFrame("..........WELCOME..........");
    		String name = JOptionPane.showInputDialog(frame, "1) Easy 2) Moderate 3) Difficult");
		if(name.equals("1"))
		{
			n=9;
			n1=6;
			m1=9;
			m=9;
		}
		else if(name.equals("2"))
		{
			n=11;
			n1=5;
			m1=10;
			m=11;
		}
		else if(name.equals("3"))
		{
			n=13;
			n1=4;
			m1=11;
			m=13;
		}
		else
		{
			System.exit(0);
		}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				z[i][j]='0';
			}
		}	
        	Random rand1=new Random();
        	for(int i=0;i<(n*m)/n1;i++)
        	{
            		int l= rand1.nextInt(n);
            		int k= rand1.nextInt(m);
            		z[l][k]='X';
        	}
        	for(int i=0;i<n;i++)
        	{
			for(int j=0;j<m;j++)
            		{
               			try
               			{
               				if(z[i][j]!='X')
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
        	input();
		g=false;
		setLayout(new GridLayout(n,m));
		for(int i=0;i<n;i++)
        	{
			for(int j=0;j<m;j++)
        		{
		        	B[i][j]=new Button();
		        	add(B[i][j]);
                		try
				{
                			B[i][j].setBackground(Color.orange);
                			B[i][j].addActionListener(this);
                			if(z[i][j]=='X')
                			{
                				B[i][j].setActionCommand("Color.red");
                			}
                			else
                			{
                    				B[i][j].setActionCommand(Character.toString(z[i][j]));
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
		setFont(f);		
		if(e.getActionCommand().equals("Color.red"))
        	{
            		for(int i=0;i<n;i++)
            		{
                		for(int j=0;j<m;j++)
                		{
                    			if(B[i][j].getActionCommand().equals("Color.red"))
                    			{
                        			B[i][j].setBackground(Color.red);
                    			}
                		}
            		}
        	}
        	else if(e.getActionCommand().equals("0"))
        	{
	    		int i,j,x=n/m1,k,l;
            		for(i=0;i<n;i++)
            		{
                		for(j=0;j<m;j++)
                		{
					if(B[i][j]==e.getSource()) 
					{
						B[i][j].setBackground(Color.green);
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
								if(k>=c && k<=u && l>=d && l<=v)
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
	     	else
	        {
	     		for(int i=0;i<n;i++)
	            	{
	                	for(int j=0;j<m;j++)
	                	{
	                    		if(e.getSource()==B[i][j])
        	            		{
						String s=e.getActionCommand();
						B[i][j].setBackground(Color.yellow);
                        			B[i][j].setLabel(s);
						break;
                			}
                		}
            		}
        	}
    	}
}
            

        
        
        
        
        
        