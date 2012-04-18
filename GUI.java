import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class GUI implements ActionListener {

	private int grid = 12;
	//GUI Components
    private JFrame window = new JFrame("Robber Knights");
    private JPanel board =  new JPanel();
    private JPanel playerStatus = new JPanel();
    private JDialog menu = new JDialog();
    private JButton gridButtons[][] = new JButton[grid][grid];		
    private JButton close = new JButton("Close");
    private JButton newGame = new JButton("Start Game");
    private ButtonGroup group = new ButtonGroup();
    private JMenuBar menuBar = new JMenuBar();		
    private JMenu fileMenu = new JMenu("File");
    private JMenu helpMenu = new JMenu("Help");
    private JRadioButton fourPlayers = new JRadioButton("4 Players");
    private JRadioButton threePlayers = new JRadioButton("3 Players");
    private JRadioButton twoPlayers = new JRadioButton("2 Players");
    private JTextField playerOneName = new JTextField();
	private JTextField playerTwoName = new JTextField();
	private JTextField playerThreeName = new JTextField();
	private JTextField playerFourName = new JTextField();
	private String[] playerNames = new String[4];
	private JLabel playerNameLabel = new JLabel();
	private JLabel playerNameField = new JLabel();
	private JLabel knightsLabel = new JLabel();
	private JLabel knightsField = new JLabel();
	private JLabel tilesLabel = new JLabel();
	private JLabel tilesField = new JLabel();
	private JButton playTiles[] = new JButton[2];
	private JButton endTurn = new JButton("End Turn");
	private JButton back = new JButton("Back");	
	private JLabel playerOne = new JLabel();
	private JLabel playerTwo = new JLabel();
	private JLabel playerThree = new JLabel();
	private JLabel playerFour = new JLabel();
    private int numPlayers = 2;
    
  //Creates GUI
    public GUI()
    {
    	JMenuItem newAction = new JMenuItem("Start Game");
        JMenuItem exitAction = new JMenuItem("Exit");
        
        JMenuItem helpAction = new JMenuItem("Help");
        JMenuItem aboutAction = new JMenuItem("About");
        
    	//Creates the main frame 
    	window.setSize(800,600);                        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        window.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
       
        board.setSize(new Dimension(600,600));
        board.setLayout(new GridLayout(grid,grid));
        

		playerNameLabel.setText("Player: ");
		playerNameField.setText("Test 1");
		knightsLabel.setText("Knights Remaining: ");
		knightsField.setText("0");
		tilesLabel.setText("Tiles Remaining: ");
		tilesField.setText("0");
		playTiles[0] = new JButton("Tile 1");
		playTiles[1] = new JButton("Tile 2");
		
		playerStatus.setSize(new Dimension(200,600));
        playerStatus.setLayout(new GridLayout(8,2));
		
		playerStatus.add(playerNameLabel);
		playerStatus.add(playerNameField);
		playerStatus.add(knightsLabel);
		playerStatus.add(knightsField);
		playerStatus.add(tilesLabel);
		playerStatus.add(tilesField);
		playerStatus.add(playTiles[0]);
		playerStatus.add(playTiles[1]);
		playerStatus.add(back);
		playerStatus.add(endTurn);
        c.weighty = 0.1;
		c.weightx = 0.1;
        window.add(board, c);
        //c.weightx = .2;
        window.add(playerStatus, c);
        window.setResizable(false);
        window.setJMenuBar(menuBar);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        
        fileMenu.add(newAction);
        fileMenu.add(exitAction);
        
        helpMenu.add(helpAction);
        helpMenu.add(aboutAction);
        
        //Creates the new game menu
        newAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
            	JPanel namePane = new JPanel();
            	JPanel radioPane = new JPanel();
            	
            	namePane.setLayout(new GridLayout(4,2));
            	radioPane.setLayout(new GridLayout(3,1));
            	
            	menu.setSize(350,250);
                menu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                menu.setLayout(new GridLayout(2,1));
                menu.setResizable(false);
                
                playerOne.setText("Player One: ");
                playerTwo.setText("Player Two: ");
                playerThree.setText("Player Three: ");
                playerFour.setText("Player Four: ");
                namePane.add(playerOne);
                namePane.add(playerOneName);
                namePane.add(playerTwo);
                namePane.add(playerTwoName);
                namePane.add(playerThree);
                namePane.add(playerThreeName);
                namePane.add(playerFour);
                namePane.add(playerFourName);   
                
                group.add(twoPlayers);
                group.add(threePlayers);
                group.add(fourPlayers);
                twoPlayers.setEnabled(true);
                
                radioPane.add(twoPlayers);
                radioPane.add(threePlayers);
                radioPane.add(fourPlayers);
                
                menu.add(radioPane);
                menu.add(namePane);
                menu.add(newGame);
                menu.add(close);
                
                twoPlayers.setSelected(true);
                playerThreeName.disable();
                playerThreeName.setBackground(null);
                playerFourName.disable();
                playerFourName.setBackground(null);
                //newGame.setEnabled(true);
                
              //Clears the board and hides the new game menu
                twoPlayers.addActionListener(new ActionListener() 
                {
					public void actionPerformed(ActionEvent arg0) {
						playerThreeName.disable();
						playerThreeName.setText(null);
						playerThreeName.setBackground(null);
						playerFourName.disable();
						playerFourName.setText(null);
						playerFourName.setBackground(null);
						numPlayers = 2;
						
					}	
                });
                threePlayers.addActionListener(new ActionListener() 
                {
					public void actionPerformed(ActionEvent arg0) {
						playerThreeName.enable();
						playerThreeName.setBackground(Color.WHITE);
						playerFourName.disable();
						playerFourName.setText(null);
						playerFourName.setBackground(null);
						numPlayers = 3;
						
					}	
                });
                fourPlayers.addActionListener(new ActionListener() 
                {
					public void actionPerformed(ActionEvent arg0) {
						playerThreeName.enable();
						playerThreeName.setBackground(Color.WHITE);
						playerFourName.enable();
						playerFourName.setBackground(Color.WHITE);
						numPlayers = 4;
						
					}	
                });
                newGame.addActionListener(new ActionListener() 
                {
                	public void actionPerformed(ActionEvent arg0) 
                	{
                		reset();
                		playerNames[0] = playerOneName.getText();
                		playerNames[1] = playerTwoName.getText();
                		playerNames[2] = playerThreeName.getText();
                		playerNames[3] = playerFourName.getText();
                		
                		menu.setVisible(false);
                	}
                });
                
                //Hides the new game menu
                close.addActionListener(new ActionListener() 
                {
                	public void actionPerformed(ActionEvent arg0) 
                	{
                		menu.setVisible(false);
                	}
                });
                
                menu.setVisible(true);
            }
        });
        
        //Closes program
        exitAction.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent arg0)
        	{
        		System.exit(0);
        	}
        });
        //Put the squares on the main frame
        for(int x = 0; x < grid; x++)
        {
        	for(int y = 0; y < grid; y++)
        	{
        		gridButtons[x][y] = new JButton();                    
        		board.add(gridButtons[x][y]);                        
        		gridButtons[x][y].addActionListener(this);            
        		Font f = new Font("comicbd.ttf", 1, 20);    
        		gridButtons[x][y].setFont(f);                        
        		gridButtons[x][y].setEnabled(false);
        	}
        }
        					
        
        
        
        window.setVisible(true);     
    }


    
  //Resets the board
    public void reset() 
    {
    	for (int x = 0; x < grid; x++)
    	{
    		for (int y = 0; y < grid; y++)
    		{
    			gridButtons[x][y].setText("");
    			gridButtons[x][y].setName("");
                gridButtons[x][y].setEnabled(false);
                gridButtons[x][y].setBackground(null);
    		}
    	}
    	
    	gridButtons[6][6].setText("U");
    	gridButtons[6][6].setEnabled(false);
    	gridButtons[6][6].setBackground(null);
    	gridButtons[6][6].setName("used");
    	
    	gridButtons[5][6].setText("U");
    	gridButtons[5][6].setEnabled(false);
    	gridButtons[5][6].setBackground(null);
    	gridButtons[5][6].setName("used");
    	
    	gridButtons[6][5].setText("U");
    	gridButtons[6][5].setEnabled(false);
    	gridButtons[6][5].setBackground(null);
    	gridButtons[6][5].setName("used");
    	
    	gridButtons[5][5].setText("U");
    	gridButtons[5][5].setEnabled(false);
    	gridButtons[5][5].setBackground(null);
    	gridButtons[5][5].setName("used");
    	
    	gridButtons[4][5].setText("");
    	gridButtons[4][5].setEnabled(true);
    	gridButtons[4][5].setBackground(null);
    	
    	gridButtons[5][4].setText("");
    	gridButtons[5][4].setEnabled(true);
    	gridButtons[5][4].setBackground(null);
    	
    	gridButtons[4][6].setText("");
    	gridButtons[4][6].setEnabled(true);
    	gridButtons[4][6].setBackground(null);
    	
    	gridButtons[5][7].setText("");
    	gridButtons[5][7].setEnabled(true);
    	gridButtons[5][7].setBackground(null);
    	
    	gridButtons[6][4].setText("");
    	gridButtons[6][4].setEnabled(true);
    	gridButtons[6][4].setBackground(null);
    	
    	gridButtons[7][5].setText("");
    	gridButtons[7][5].setEnabled(true);
    	gridButtons[7][5].setBackground(null);
    	
    	gridButtons[7][6].setText("");
    	gridButtons[7][6].setEnabled(true);
    	gridButtons[7][6].setBackground(null);
    	
    	gridButtons[6][7].setText("");
    	gridButtons[6][7].setEnabled(true);
    	gridButtons[6][7].setBackground(null);
    	
    	
    }
  //Selects the square you want to go
    public void actionPerformed(ActionEvent a) 
    {
    	//Draws the letter on the button, disable the button and enables adjacent.
    	
        JButton pressedButton = (JButton)a.getSource();
        pressedButton.setText("U");
        pressedButton.setName("used");
        pressedButton.setEnabled(false);
        int tempArray[] = new int[2];
        
        for(int x = 0; x < grid - 1; x++)
        {
        	for(int y = 0; y < grid - 1; y++)
        	{
        		if (gridButtons[x][y] == a.getSource())
        		{        			
        			
        			if (x == 0 || y == 0)
        			{
        				tempArray = shift(x, y);
        				x = tempArray[0];
        				y = tempArray[1];
        				
        			}
        			//gridButtons[x][y].setName("used");
        			modAdjacent(x,y);
        		}
        	}
        }
        for(int x = grid - 1; x > 1; x--)
        {
        	for(int y = grid - 1; y > 1; y--)
        	{
        		if (gridButtons[x][y] == a.getSource())
        		{        			
        			
        			if (x == grid - 1 || y == grid - 1)
        			{
        				tempArray = shift(x, y);
        				x = tempArray[0];
        				y = tempArray[1];
       
        			}
        			//gridButtons[x][y].setName("used");
        			modAdjacent(x,y);
        		}
        	}
        }
        
    }
   
    private int[] shift(int x, int y) 
    {
    	int flag;
    	int j, k;
    	JButton tempGrid[][] = new JButton[grid][grid];
    	int tempArray[] = new int[2];
    	tempGrid = initBoard(tempGrid);
    	if (x == 0 || y == 0)
    	{
    		j = 0;
	    	if (x == 0)
	    	{
	    		flag = 0;
	    		x = 1;
	    	}
	    	else
	    	{
	    		flag = 1;
	    		y = 1;
	    	}
	    	
			while(j < grid -1)
			{
				k = 0;
			
				while(k < grid - 1)
				{
					loopOne: switch (flag) 
					{
					case 0: tempGrid[j+1][k].setText(gridButtons[j][k].getText());
							tempGrid[j+1][k].setName(gridButtons[j][k].getName());
							tempGrid[j+1][k].setEnabled(gridButtons[j][k].isEnabled());
							tempGrid[j+1][k].setBackground(gridButtons[j][k].getBackground());
							tempGrid[0][k].setText("");
				    		tempGrid[0][k].setName("");
				    		tempGrid[0][k].setEnabled(false);
				    		tempGrid[0][k].setBackground(null);
							k++;
							break loopOne;
					case 1: tempGrid[j][k+1].setText(gridButtons[j][k].getText());
							tempGrid[j][k+1].setName(gridButtons[j][k].getName());
							tempGrid[j][k+1].setEnabled(gridButtons[j][k].isEnabled());
							tempGrid[j][k+1].setBackground(gridButtons[j][k].getBackground());
							tempGrid[j][0].setText("");
				    		tempGrid[j][0].setName("");
				    		tempGrid[j][0].setEnabled(false);
				    		tempGrid[j][0].setBackground(null);
							k++;
							break loopOne;
					}
				}
				j++;
			}
    	}
			if (x == grid - 1 || y == grid - 1)
	    	{
				j = grid - 1;
	    	
		    	if (x == grid - 1)
		    	{
		    		flag = 0;
		    		x = grid - 2;
		    	}
		    	else
		    	{
		    		flag = 1;
		    		y = grid - 2;
		    	}
		    	
		    	while(j > 0)
				{
					k = grid - 1;
					
					while(k > 0)
					{
						loopTwo: switch (flag) 
						{
						case 0: tempGrid[j-1][k].setText(gridButtons[j][k].getText());
								tempGrid[j-1][k].setName(gridButtons[j][k].getName());
								tempGrid[j-1][k].setEnabled(gridButtons[j][k].isEnabled());
								tempGrid[j-1][k].setBackground(gridButtons[j][k].getBackground());
								tempGrid[0][k].setText("");
					    		tempGrid[0][k].setName("");
					    		tempGrid[0][k].setEnabled(false);
					    		tempGrid[0][k].setBackground(null);
								k--;
								break loopTwo;
						case 1: tempGrid[j][k-1].setText(gridButtons[j][k].getText());
								tempGrid[j][k-1].setName(gridButtons[j][k].getName());
								tempGrid[j][k-1].setEnabled(gridButtons[j][k].isEnabled());
								tempGrid[j][k-1].setBackground(gridButtons[j][k].getBackground());
								tempGrid[j][0].setText("");
					    		tempGrid[j][0].setName("");
					    		tempGrid[j][0].setEnabled(false);
					    		tempGrid[j][0].setBackground(null);
								k--;
								break loopTwo;
						}
					}
					j--;
				}
	    	}
	    	for (int l = 0; l < grid ; l++)
	    	{
	    		tempGrid[l][0].setText("");
	    		tempGrid[l][0].setName("");
	    		tempGrid[l][0].setEnabled(false);
	    		tempGrid[l][0].setBackground(null);
	    		
	    		tempGrid[l][grid - 1].setText("");
	    		tempGrid[l][grid - 1].setName("");
	    		tempGrid[l][grid - 1].setEnabled(false);
	    		tempGrid[l][grid - 1].setBackground(null);
	    		
	    		tempGrid[0][l].setText("");
	    		tempGrid[0][l].setName("");
	    		tempGrid[0][l].setEnabled(false);
	    		tempGrid[0][l].setBackground(null);
	    		
	    		tempGrid[grid - 1][l].setText("");
	    		tempGrid[grid - 1][l].setName("");
	    		tempGrid[grid - 1][l].setEnabled(false);
	    		tempGrid[grid - 1][l].setBackground(null);
	    		
	    	}
    
    	for(int n = 0; n < grid; n++ )
    	{
    		for (int m = 0; m < grid; m++)
    		{
    			
    			gridButtons[n][m].setText(tempGrid[n][m].getText());
				gridButtons[n][m].setName(tempGrid[n][m].getName());
				gridButtons[n][m].setEnabled(tempGrid[n][m].isEnabled());
				gridButtons[n][m].setBackground(tempGrid[n][m].getBackground());
				if(gridButtons[n][m].getName() == "used")
    			{
        			if (gridButtons[n-1][m].getName() != "used")
        			{
        				gridButtons[n-1][m].setEnabled(true);
        			}
        			if (gridButtons[n+1][m].getName() != "used")
        			{
        				gridButtons[n+1][m].setEnabled(true);
        			}
        			if (gridButtons[n][m-1].getName() != "used")
        			{
        				gridButtons[n][m-1].setEnabled(true);
        			}
        			if (gridButtons[n][m+1].getName() != "used")
        			{
        				gridButtons[n][m+1].setEnabled(true);
        			}
    			}
    		}
    	}
    	tempArray[0] = x;
    	tempArray[1] = y;
    	return tempArray;
    }
    private JButton[][] initBoard(JButton[][] board)
    {
    	for(int j = 0 ; j < grid ; j++)
    	{
    		for(int k = 0 ; k < grid ; k++)
    		{
    			board[j][k] = new JButton();
    		}
    	}
		return board;
    	
    }
    private void modAdjacent(int x, int y)
    {
    	if (gridButtons[x-1][y].getName() != "used")
		{
			gridButtons[x-1][y].setEnabled(true);
		}
		if (gridButtons[x+1][y].getName() != "used")
		{
			gridButtons[x+1][y].setEnabled(true);
		}
		if (gridButtons[x][y-1].getName() != "used")
		{
			gridButtons[x][y-1].setEnabled(true);
		}
		if (gridButtons[x][y+1].getName() != "used")
		{
			gridButtons[x][y+1].setEnabled(true);
		}
    }



	//Runs the Robber Knights Game
    public static void main(String[] args)
    {
       new GUI();
    }
}


    
        
