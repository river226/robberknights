/**
 *
 * Group 5 Robber Knights Game
 *
 * Members: Jedidiah Johnson, Alex Sokol, Aaron Thrasher, Rebecca Rasmussen, Tony Dederich
 *
 * The GUI class:
 * 
 * Implements the user facing portion of the program. It also functions as the driver for the entire game
 * and listens for all input from the user, interpreting the appropriate calls to make. 
 *
 */

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GUI implements ActionListener {


        //GUI Components
        private int grid = 12; // Defaults the grid size to 12x12
        private int imageSize = 66;  //Default size is for 2 player game
        private JFrame window = new JFrame("Robber Knights");   
        private JPanel board =  new JPanel();
        private JPanel playerStatus = new JPanel();
        private JDialog menu = new JDialog();
        private JDialog tilePick = new JDialog();
        private JButton gridButtons[][] = new JButton[grid][grid];  
        private JButton initTile[][] = new JButton[4][3];
        private JButton close = new JButton("Close");
        private JButton newGame = new JButton("Start Game");
        private JRadioButton fourPlayers = new JRadioButton("4 Players");
        private JRadioButton threePlayers = new JRadioButton("3 Players");
        private JRadioButton twoPlayers = new JRadioButton("2 Players");
        private JTextField playerOneName = new JTextField();
        private JTextField playerTwoName = new JTextField();
        private JTextField playerThreeName = new JTextField();
        private JTextField playerFourName = new JTextField();
        private String[] playerNames = new String[4];
        private JLabel playerNameField = new JLabel();
        private JLabel knightsField = new JLabel();
        private JLabel tilesField = new JLabel();
        private JButton playTiles[] = new JButton[2];
        private JButton endTurn = new JButton("End Turn");
        private JButton back = new JButton("Deselect");     
        private int numPlayers = 2;
        private Game game;
        private Tile[][] temp = new Tile[numPlayers][3];
        private Tile[][] tempGrid;
        private Tile[] tempHandTile = new Tile[2];
        private Tile[] tempHand = new Tile[2];
        private Tile selectedHandTile;
        private Tile unselectedHandTile;
        private int tilesPicked[] = new int[4];
        private JButton begin = new JButton("Begin");
        private JButton playerHandTile[] = new JButton[2];
        private int playerHandTileSelect = 0;
        private Boolean castlePlayed = false;

        // Creates GUI
        public GUI() throws IOException{
        	
        		JLabel playerOne = new JLabel();
        		JLabel playerTwo = new JLabel();
            	JLabel playerThree = new JLabel();
            	JLabel playerFour = new JLabel();
        		JLabel tilesLabel = new JLabel();
        		JLabel knightsLabel = new JLabel();
        		JLabel playerNameLabel = new JLabel();
        		JMenu fileMenu = new JMenu("File");
        		JMenu helpMenu = new JMenu("Help");
        		JMenuBar menuBar = new JMenuBar(); 
        		ButtonGroup group = new ButtonGroup();
                JMenuItem newAction = new JMenuItem("Start Game");
                JMenuItem exitAction = new JMenuItem("Exit");
                JMenuItem helpAction = new JMenuItem("Help");
                JMenuItem aboutAction = new JMenuItem("About");
                playTiles[0] = new JButton("Tile 1");
                playTiles[1] = new JButton("Tile 2");
                JLabel handLabel = new JLabel("Hand:");
                JPanel namePane = new JPanel();
                JPanel radioPane = new JPanel();
                JLabel lblPlayerOne = new JLabel("Player One:");
                JLabel lblPlayerTwo = new JLabel("Player Two:");
                JLabel lblPlayerThree = new JLabel("Player Three:");
                JLabel lblPlayerFour = new JLabel("Player Four:");
                JButton reset = new JButton("Reset Selection");
                JButton tileBack = new JButton("Back");
                
                //Creates the main frame 
                window.setSize(800,650);                        
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

                BufferedImage titleScreen = ImageIO.read(new File("src/library/images/Logo/robnight600.png")); // may need to add 'src/' to file path
                window.setIconImage(new ImageIcon("src/library/images/icon/R_icon_small.png").getImage()); // may need to add 'src/' to file path
                JLabel startScreen = new JLabel(new ImageIcon(titleScreen));
                window.setContentPane(startScreen);

                board.setLocation(0, 0);

                board.setSize(new Dimension(600, 600));
                board.setLayout(new GridLayout(grid,grid));
                playerNameLabel.setBounds(5, 10, 50, 25);

                playerNameLabel.setText("Player: ");
                playerNameField.setBounds(65, 10, 125, 25);
                playerNameField.setText("Test 1");
                knightsLabel.setBounds(5, 45, 115, 25);
                knightsLabel.setText("Knights Remaining: ");
                knightsField.setBounds(120, 45, 70, 25);
                knightsField.setText("0");
                tilesLabel.setBounds(5, 80, 100, 25);
                tilesLabel.setText("Tiles Remaining: ");
                tilesField.setBounds(105, 80, 85, 25);
                tilesField.setText("0");
                
                playerStatus.setLocation(600, 0);
                playerStatus.setSize(new Dimension(200, 600));
                playerStatus.setLayout(null);

                playerStatus.add(playerNameLabel);
                playerStatus.add(playerNameField);
                playerStatus.add(knightsLabel);
                playerStatus.add(knightsField);
                playerStatus.add(tilesLabel);
                playerStatus.add(tilesField);
                playerStatus.add(playTiles[0]);
                playerStatus.add(playTiles[1]);
               
                handLabel.setBounds(80, 115, 40, 25);
                playerStatus.add(handLabel);

                for(int y = 0; y < 2; y++)
                {
                        playerHandTile[y] = new JButton();
                        playerHandTile[y].addActionListener(this);            
                        Font f = new Font("comicbd.ttf", 1, 20);    
                        playerHandTile[y].setFont(f);
                }                

                playerHandTile[0].setBounds(35, 150, 60, 60);
                playerStatus.add(playerHandTile[0]);

                playerHandTile[1].setBounds(100, 150, 60, 60);
                playerStatus.add(playerHandTile[1]);
                back.setBounds(5, 220, 90, 25);
                playerStatus.add(back);
                endTurn.setBounds(100, 220, 90, 25);
                playerStatus.add(endTurn);
                endTurn.setEnabled(false);
                window.getContentPane().setLayout(null);

                window.setResizable(false);
                window.setJMenuBar(menuBar);

                menuBar.add(fileMenu);
                menuBar.add(helpMenu);

                fileMenu.add(newAction);
                fileMenu.add(exitAction);

                helpMenu.add(helpAction);
                helpMenu.add(aboutAction);

                namePane.setLayout(new GridLayout(4,2));
                radioPane.setLayout(new GridLayout(3,1));

                //Creates the "New Game" Menu
                menu.setSize(350,250);
                menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

                menu.getContentPane().add(radioPane);
                menu.getContentPane().add(namePane);
                menu.getContentPane().add(newGame);
                menu.getContentPane().add(close);

                twoPlayers.setSelected(true);
                playerThreeName.setEditable(false);
                playerThreeName.setBackground(null);
                playerFourName.setEditable(false);
                playerFourName.setBackground(null);

                //Creates the Initial Tile Picking Window
                tilePick.setSize(305,355);
                tilePick.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                tilePick.getContentPane().setLayout(null);
                lblPlayerFour.setBounds(5, 200, 100, 25);
                tilePick.getContentPane().add(lblPlayerFour);                
                lblPlayerThree.setBounds(5, 135, 100, 25);
                tilePick.getContentPane().add(lblPlayerThree);                
                lblPlayerTwo.setBounds(5, 70, 100, 25);
                tilePick.getContentPane().add(lblPlayerTwo);
                lblPlayerOne.setBounds(5, 5, 100, 25);
                tilePick.getContentPane().add(lblPlayerOne);
                
                for(int x = 0; x < 4; x++){
                        for(int y = 0; y < 3; y++){
                        	
                                initTile[x][y] = new JButton();
                                initTile[x][y].addActionListener(this);            
                                Font f = new Font("comicbd.ttf", 1, 20);    
                                initTile[x][y].setFont(f); 
                                tilePick.getContentPane().add(initTile[x][y]);
                        }
                        tilesPicked[x] = 0;
                }

                initTile[3][2].setBounds(235, 200, 60, 60);
                initTile[3][1].setBounds(170, 200, 60, 60);
                initTile[3][0].setBounds(105, 200, 60, 60);
                initTile[2][2].setBounds(235, 135, 60, 60);
                initTile[2][1].setBounds(170, 135, 60, 60);
                initTile[2][0].setBounds(105, 135, 60, 60);
                initTile[1][2].setBounds(235, 70, 60, 60);
                initTile[1][1].setBounds(170, 70, 60, 60);
                initTile[1][0].setBounds(105, 70, 60, 60);
                initTile[0][2].setBounds(235, 5, 60, 60);
                initTile[0][1].setBounds(170, 5, 60, 60);
                initTile[0][0].setBounds(105, 5, 60, 60);

                tileBack.setBounds(155, 300, 100, 25);
                tilePick.getContentPane().add(tileBack);
                begin.setBounds(50, 300, 100, 25);
                tilePick.getContentPane().add(begin);
                begin.setEnabled(false);                
                reset.setBounds(80, 270, 150, 25);
                tilePick.getContentPane().add(reset);
                tilePick.setResizable(false);


                //Creates the functionality of the "New Game" Menu
                newAction.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                                twoPlayers.addActionListener(new ActionListener(){
                                        public void actionPerformed(ActionEvent arg0) {
                                                playerThreeName.setEditable(false);
                                                playerThreeName.setText(null);
                                                playerThreeName.setBackground(null);
                                                playerFourName.setEditable(false);
                                                playerFourName.setText(null);
                                                playerFourName.setBackground(null);
                                                numPlayers = 2;
                                                imageSize = 66;
                                        }       
                                });
                                
                                //Enable/Disable certain fields and sets the number of players
                                threePlayers.addActionListener(new ActionListener(){
                                        public void actionPerformed(ActionEvent arg0) {
                                                playerThreeName.setEditable(true);
                                                playerThreeName.setBackground(Color.WHITE);
                                                playerFourName.setEditable(false);
                                                playerFourName.setText(null);
                                                playerFourName.setBackground(null);
                                                numPlayers = 3;
                                                imageSize = 55;
                                        }       
                                });
                                
                                //Enable/Disable certain fields and sets the number of players
                                fourPlayers.addActionListener(new ActionListener(){
                                        public void actionPerformed(ActionEvent arg0) {
                                                playerThreeName.setEditable(true);
                                                playerThreeName.setBackground(Color.WHITE);
                                                playerFourName.setEditable(true);
                                                playerFourName.setBackground(Color.WHITE);
                                                numPlayers = 4;
                                                imageSize = 50;
                                        }       
                                });
                                
                                //Clears the board and hides the new game menu
                                newGame.addActionListener(new ActionListener(){
                                        public void actionPerformed(ActionEvent arg0){
                                                playerNames[0] = playerOneName.getText();
                                                playerNames[1] = playerTwoName.getText();
                                                playerNames[2] = playerThreeName.getText();
                                                playerNames[3] = playerFourName.getText();
                                                game = new Game(numPlayers, playerNames);
                                                grid = game.getBoard().length;
                                                gridButtons = new JButton[grid][grid];
                                                tempGrid = new Tile[grid][grid];
                                                board.setLayout(new GridLayout(grid,grid));
                                                temp = game.firstMove();
                                                for(int i = 0; i < numPlayers; i++){
                                                        for(int j = 0; j < 3; j++){
                                                                try {
                                                                        initTile[i][j].setIcon(new ImageIcon(ImageIO.read(new File(temp[i][j].getImage()))));
                                                                } catch (IOException e) {
                                                                        // TODO Auto-generated catch block
                                                                        e.printStackTrace();
                                                                }
                                                        }
                                                }
                                                switch (numPlayers){
                                                case 2: 
                                                        for(int x = 0; x < 3; x++){
                                                                initTile[2][x].setVisible(false);
                                                                initTile[3][x].setVisible(false);
                                                        }
                                                        break;
                                                case 3:
                                                        for(int x = 0; x < 3; x++){
                                                                initTile[2][x].setVisible(true);
                                                                initTile[3][x].setVisible(false);
                                                        }
                                                        break;
                                                default:
                                                        for(int x = 0; x < 3; x++){
                                                                initTile[2][x].setVisible(true);
                                                                initTile[3][x].setVisible(true);
                                                        }
                                                        break;
                                                }
                                                menu.setVisible(false);
                                                tilePick.setVisible(true);
                                        }
                                });

                                //Hides the new game menu
                                close.addActionListener(new ActionListener(){
                                        public void actionPerformed(ActionEvent arg0){
                                                menu.dispose();
                                        }
                                });
                                menu.setVisible(true);
                        }
                });
                
                //Displays the initial tiles picked.
                begin.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0) {
                                for(int i = 0; i < numPlayers; i++){
                                        for(int j = 0; j < 3; j++){

                                                if(initTile[i][j].isEnabled()){
                                                        game.returnToHand(i, temp[i][j]);
                                                        temp[i][j] = null;
                                                }
                                        }
                                }
                                window.getContentPane().add(board);
                                window.getContentPane().add(playerStatus);
                                board.removeAll();
                                setBoard();                             
                                board.validate();                                

                                tempGrid = game.setUpBoard(temp);

                                try {
                                        repaint();
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                                board.repaint();
                                playerStatus.repaint();
                                tilePick.dispose();
                                menu.dispose(); 
                                resetInitialTile();
                                try {
                                        newTurn();
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                        }       
                });
                
                //Goes back to the menu screen.
                tileBack.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){                        	
                                tilePick.dispose();     
                                menu.setVisible(true);
                                resetInitialTile();
                                begin.setEnabled(false);
                        }       
                });
                
                //Resets the initial tiles selected.
                reset.addActionListener(new ActionListener(){                	
                        public void actionPerformed(ActionEvent arg0) {
                                resetInitialTile(); 
                                begin.setEnabled(false);
                        }       
                });

                //Closes program
                exitAction.addActionListener(new ActionListener(){
                	
                        public void actionPerformed(ActionEvent arg0){                        	
                                System.exit(0);
                        }
                });

                //Deselects tile selected to play
                back.addActionListener(new ActionListener(){                	
                        public void actionPerformed(ActionEvent arg0){                        	
                              deselect();
                              enable();
                              back.setEnabled(false);
                        }
                });
                
                //Ends current player turn
                endTurn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent arg0){                        	
                        	if(castlePlayed){
                        		game.move.endMove();
                        		castlePlayed = false;
                        		endTurn.setText("End Turn");
                        		if(game.move.getCurrentMove() == 3){
                        			game.move.endTurn(tempHand);
                        			try {
                        				newTurn();
                        			} catch (IOException e) {
                        				e.printStackTrace();
                        			}
                        		}else{
                        			try {
                                    	updateHand();
                                    	deselect();
                                    	repaint();
                                    	disable();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
                        		}
                        	}else{
	                        	deselect();
	                			game.move.endTurn(tempHand);
	                			back.setEnabled(false);
	                			try {
	                				newTurn();
	                			} catch (IOException e) {
	                				// TODO Auto-generated catch block
	                				e.printStackTrace();
	                			}
                        	}                        		
                        }
                });
                
                window.setVisible(true);
        }
        
        public void setBoard()
        {
                //Put the squares on the main frame
                for(int x = 0; x < grid; x++){
                        for(int y = 0; y < grid; y++){
                        	
                                gridButtons[x][y] = new JButton();                    
                                board.add(gridButtons[x][y]);                        
                                gridButtons[x][y].addActionListener(this);            
                                Font f = new Font("comicbd.ttf", 1, 20);    
                                gridButtons[x][y].setFont(f);                        
                                gridButtons[x][y].setEnabled(false);
                        }
                }
        }

        //Deselects the tiles at the Tile selection screen during a new game.
        public void resetInitialTile(){
        	
                for(int x = 0; x < 4; x++){
                        for(int y = 0; y < 3; y++){
                        	
                                initTile[x][y].setEnabled(true);
                        }
                        tilesPicked[x] = 0;
                }
        }

        //Updates the gui to match what the board has for tiles.
        public void repaint() throws IOException{
        	
        	tempGrid = game.getBoard();
                for (int x = 0; x < grid; x++){
                        for (int y = 0; y < grid; y++){
                        	
                                if(tempGrid[x][y] != null){
                                	Image img = (new ImageIcon(ImageIO.read(new File(tempGrid[x][y].getImage())))).getImage().getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
                                        gridButtons[x][y].setText("");
                                        gridButtons[x][y].setName("");
                                        gridButtons[x][y].setEnabled(true);
                                        gridButtons[x][y].setIcon(new ImageIcon(img));
                                        
                                }else{
                                	gridButtons[x][y].setIcon(null);
                                	gridButtons[x][y].setEnabled(false);                                	
                                }
                        }
                }
                disable();
        }

        //Begins a new turn
        public void newTurn() throws IOException{
        		
        		castlePlayed = false;
        		endTurn.setEnabled(false);
        		enable();
                game.nextTurn(); 
                playerStatus.setBackground(game.move.getColor());
                playerNameField.setText(game.move.getName());
                updateHand();
                disable();
        }
        
        //Disables current enabled buttons on the board and hand
        public void disable(){
        	
        	for(int x = 0; x < grid; x ++){
        		for(int y = 0; y < grid; y++){
        			gridButtons[x][y].setDisabledIcon(gridButtons[x][y].getIcon());
        			gridButtons[x][y].setEnabled(false);
        		}
        		
        	}
        	if (playerHandTileSelect > 0){
	        	for(int x = 0; x < 2; x++){
	                    playerHandTile[x].setEnabled(false);
	            }
        	}
        }

      //Enables hand
        public void enable()
        {
        	for(int x = 0; x < 2; x++){
                    playerHandTile[x].setEnabled(true);
            }
        }
        
        //Grabs the next tiles off the deck

        public void updateHand() throws IOException
        {
        	
        	tempHandTile = game.move.getHand();
        	if(tempHandTile.length == 2){
        		tempHand = tempHandTile;
        	}else{
        		if(tempHand[0] == null){
        			tempHand[0] = tempHandTile[0];
        		}else{
        			tempHand[1] = tempHandTile[0];
        		}
        	}
            for(int x = 0; x < 2; x++){
                    playerHandTile[x].setIcon(new ImageIcon(ImageIO.read(new File(tempHand[x].getImage()))));
            }
            tilesField.setText(String.valueOf(game.move.getRemainingTiles()));
            knightsField.setText(String.valueOf(game.move.getReminingKnights()));
        }
        //Enables valid moves when given a location list
        public void validMoves(LocationList list){
        	
        	disable();
            for(;list.isNext();list = list.getNext()){
                        gridButtons[list.getX()][list.getY()].setEnabled(true);
                }
            gridButtons[list.getX()][list.getY()].setEnabled(true);
        }
        
        //Resets the board
        public void reset(){
        	
                for (int x = 0; x < grid; x++){
                        for (int y = 0; y < grid; y++){
                        	
                                gridButtons[x][y].setText("");
                                gridButtons[x][y].setName("");
                                gridButtons[x][y].setEnabled(false);
                                gridButtons[x][y].setBackground(null);
                        }
                }
         }
        
        //Deselects currently selected tile from the players hand
        public void deselect(){
        	
        	if(playerHandTileSelect == 1){
	        	for(int x = 0; x < 2; x++){
	                    playerHandTile[x].setEnabled(false);
	            }
	        	for (int x = 0; x < grid; x++){
	                    for (int y = 0; y < grid; y++){
	                    	
	                    	if(gridButtons[x][y].getIcon() == null)
	                    		gridButtons[x][y].setEnabled(false);
	                    }
	            }
	            playerHandTileSelect--;
        	}else{
        		for(int x = 0; x < 2; x++)
	            {
	                    playerHandTile[x].setEnabled(true);
	            }        		       		
        	}        	
        }
        //Selects the square you want to go
        public void actionPerformed(ActionEvent a){
        	
                //Plays knights
        		if(castlePlayed){
        			for(int x = 0; x < grid; x++){
                        for(int y = 0; y < grid; y++){
                        	
                            if(gridButtons[x][y] == a.getSource()){
                            	int n = game.getBoard()[x][y].minKnight();
                        		game.move.playKnights(x, y, n);
                        		knightsField.setText(String.valueOf(game.move.getReminingKnights()));
                        		
                                try {
                                	Image img = (new ImageIcon(ImageIO.read(new File(game.getBoard()[x][y].getImage())))).getImage().getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
                                    gridButtons[x][y].setIcon(new ImageIcon(img));
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                                if(game.move.getKnightsLeft()>0){
                           		validMoves(game.move.KnightMoves());
                                }else{
                                	game.move.endMove();
                                	castlePlayed = false;
                            		endTurn.setText("End Turn");
                            		if(game.move.getCurrentMove() == 3){
                            			game.move.endTurn(tempHand);
                            			try {
                            				newTurn();
                            			} catch (IOException e) {
                            				// TODO Auto-generated catch block
                            				e.printStackTrace();
                            			}
                            		}else{
                                    try {
                                    	updateHand();
                                    	deselect();
                                    	repaint();
										disable();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										}
                            		}
                                }
                            }
                        }
                    }
        		}
        		
        		//Plays Tile
                if(playerHandTileSelect == 1){
                        for(int x = 0; x < grid; x++){
                                for(int y = 0; y < grid; y++){
                                	
                                        if(gridButtons[x][y] == a.getSource()){
                                        		
                                        		
                                                try {
                                                	Image img = (new ImageIcon(ImageIO.read(new File(selectedHandTile.getImage())))).getImage().getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
                                                        gridButtons[x][y].setIcon(new ImageIcon(img));
                                                } catch (IOException e) {
                                                        // TODO Auto-generated catch block
                                                        e.printStackTrace();
                                                }
                                                gridButtons[x][y].setDisabledIcon(gridButtons[x][y].getIcon());
                                                gridButtons[x][y].setEnabled(false);
                                                for(int j = 0; j < 2; j++){
                                                	
                                                	if(selectedHandTile == tempHand[j]){
                                                		tempHand[j] = null;
                                                	}
                                                }
                                                game.move.makeMove(selectedHandTile, x, y, unselectedHandTile);
                                                if(game.move.getCurrentMove()>0){
                                        			endTurn.setEnabled(true);
                                        		}
                                                if (castlePlayed = game.move.startKnightMove() == true)
                                                {
                                                	try {
                                                		repaint();
                                                		x=(x==0)?++x:x;
    										        	x=(x==grid-1)?--x:x;
    										        	y=(y==0)?++y:y;
    										        	y=(y==grid-1)?--y:y;
    										        	game.move.adjustBoarder(x, y);
    												} catch (IOException e) {
    													// TODO Auto-generated catch block
    													e.printStackTrace();
    												}
                                                		deselect();
                                                		disable();
                                                		back.setEnabled(false);
                                                		endTurn.setText("End Move");
                                                		validMoves(game.move.KnightMoves());
                                                }else{
                                                		deselect();
                                                		if(game.move.getCurrentMove() == 3)
                                                		{
                                                			game.move.endTurn(tempHand);
                                                			try {
                                                				newTurn();
                                                				repaint();
                                                			} catch (IOException e) {
                                                				// TODO Auto-generated catch block
                                                				e.printStackTrace();
                                                			}
                                                		}else{
                                                        try {
	                                                        	updateHand();
	                                                        	deselect();
	                                                        	disable();
	                                                        	repaint();
	        											} catch (IOException e) {
	        											// TODO Auto-generated catch block
	        											e.printStackTrace();
	        											}
                                                		}
                                                }
                                        }
                                }
                        }
                }
                //Selects card from hand
                for(int x = 0; x < 2; x++){
                	
                        if (playerHandTile[x] == a.getSource()){
                                if(playerHandTileSelect < 1){
                                		playerHandTileSelect++;
                                		disable();
                                		back.setEnabled(true);
                                		validMoves(game.move.getValidMoves());
                                        playerHandTile[x].setEnabled(false);
                                        selectedHandTile = tempHand[x];         
                                        
                                        if (x == 0){
                                                unselectedHandTile = tempHand[1];
                                        }else{
                                                unselectedHandTile = tempHand[0];
                                        }
                                }
                        }
                }
                //Selects initial tiles from the tile selection screen
                for(int x = 0; x < numPlayers; x++){
                        for(int y = 0; y < 3; y++){
                        	
                                if (initTile[x][y] == a.getSource()){
                                        if (tilesPicked[x] < 2){
                                                if(initTile[x][y].isEnabled()){
                                                        initTile[x][y].setEnabled(false);
                                                        tilesPicked[x]++;
                                                }
                                        }
                                }
                        }
                }
                //Disable/Enable the begin button depending on if they have two tiles selected or not for each player.
                switch (numPlayers){
                case 2: 
                        if (tilesPicked[0] == 2 && tilesPicked[1] == 2){
                                begin.setEnabled(true);
                        }else{
                                begin.setEnabled(false);
                        }
                        break;
                case 3: 
                        if (tilesPicked[0] == 2 && tilesPicked[1] == 2 && tilesPicked[2] == 2){
                                begin.setEnabled(true);
                        }else{
                                begin.setEnabled(false);
                        }
                        break;
                default: 
                        if (tilesPicked[0] == 2 && tilesPicked[1] == 2 && tilesPicked[2] == 2 && tilesPicked[3] == 2){
                                begin.setEnabled(true);
                        }else{
                                begin.setEnabled(false);
                        }
                        break;
                }
        }

        //Runs the Robber Knights Game
        public static void main(String[] args) throws IOException{
                new GUI();
        }
}