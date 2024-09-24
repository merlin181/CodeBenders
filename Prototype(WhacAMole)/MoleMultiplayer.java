import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.*;

public class MoleMultiplayer {
    private JFrame frame = new JFrame("CODE BENDERS: WHAC-A-MOLE");           // game frame
    private ImageIcon holeIcon;                    // background image for the game
    private ImageIcon moleIcon;                     // the mole to hit
    private ImageIcon bombIcon;                      // bomb, when hit game ends 
	private ImageIcon hammerIcon;                   // extra time 
    private JButton[] board = new JButton[8];     // buttons to be placed on where the holes are
    private JButton reset;
	private JButton pause;
	private JButton menu;	                // goes back to menu
    private Random random = new Random();
    private int score = 0;
	private int highScore = 0;                        // high score
    private JLabel highScoreLabel;
    private int time = 60;                       // game is initially played for a minute
	private Timer timeTimer;                   
    private Timer moleTimer;                     // mole appears every 1 second
    private Timer bombTimer;					// bomb appears every 1.5 seconds
	private Timer hammerTimer;						// moleExtra time appears every 20 secondsS
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JButton currMoleTile;
    private JButton currBombTile;
	private JButton currhammerTile;

    public MoleMultiplayer() {
        holeIcon = new ImageIcon(this.getClass().getResource("/holes.png"));
        holeIcon = new ImageIcon(holeIcon.getImage().getScaledInstance(1000, 650, java.awt.Image.SCALE_SMOOTH));

        bombIcon = new ImageIcon(this.getClass().getResource("/bomb.jpeg"));
        bombIcon = new ImageIcon(bombIcon.getImage().getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH));            // various images in the game

        moleIcon = new ImageIcon(this.getClass().getResource("/mole.jpeg"));
        moleIcon = new ImageIcon(moleIcon.getImage().getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH));
		
		hammerIcon = new ImageIcon(this.getClass().getResource("/clock.jpeg"));
        hammerIcon = new ImageIcon(hammerIcon.getImage().getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH));

        JLabel label = new JLabel(holeIcon);
        label.setLayout(null);                         // Set the layout to null so we can set the position and size of the buttons manually

        int[][] positions = {
                {60, 55}, // position of button 1
                {310, 140}, // position of button 2
                {600, 100}, // position of button 3
                {40, 270}, // position of button 4
                {350, 330}, // position of button 5
                {665, 230}, // position of button 6
                {185, 500}, // position of button 7
                {640, 440}, // position of button 8
        };

        int[][] buttonSizes = {
                {150, 75}, // size of button 1
                {150, 75}, // size of button 2
                {150, 75}, // size of button 3
                {150, 75}, // size of button 4
                {150, 75}, // size of button 5
                {150, 75}, // size of button 6
                {150, 75}, // size of button 7
                {150, 75}, // size of button 8
        };
		
        for (int i = 0; i < 8; i++) {
			JButton tile = new JButton();
			tile.addActionListener(new ButtonActionListener());          // button actionListener
			tile.addActionListener(new ButtonActionListener());
			tile.setBackground(new Color(0, 0, 0, 0));         // make the buttons invisible
			tile.setBorder(null);                                  // no border for button on the holes
			tile.setContentAreaFilled(false);
			tile.setFocusPainted(false);
            board[i] = tile;
            label.add(tile);                                                // Add the buttons to the JLabel
            tile.setFocusable(false);
			 
			
            tile.setBounds(positions[i][0], positions[i][1], buttonSizes[i][0], buttonSizes[i][1]);   // Set the position and size of the buttons and their sizes
        }
		
		
		menu = new JButton("Menu");
		menu.addActionListener(new ButtonActionListener3());
		label.add(menu);
		
		pause = new JButton("Pause");
		pause.addActionListener(new ButtonActionListener4());
		label.add(pause);
		
        reset = new JButton("Restart");                                         // restart button
        reset.addActionListener(new ButtonActionListener2());
        label.add(reset);
       //reset.setBounds(1000, 1000, 100, 30);
	   
	   highScoreLabel = new JLabel("High Score: 0");
	   highScoreLabel.setFont(new Font("Arial", Font.BOLD, 24));       // highscore stored in file
		

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 24));              // current score
        //scoreLabel.setBounds(10, 10, 200, 30);

        timeLabel = new JLabel("Time: 60");
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 24));       // time
       // timeLabel.setBounds(10, 40, 200, 30);

        label.add(scoreLabel);
        label.add(timeLabel);
		
		JPanel topPanel = new JPanel();                                           // panel to put the highScore, score, timer and start button
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setPreferredSize(new Dimension(200, 150));
        //topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		topPanel.add(scoreLabel);
		topPanel.add(Box.createVerticalStrut(10));       // space between
		topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(timeLabel);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(highScoreLabel);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(reset);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(pause);
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(Box.createVerticalStrut(10));
		topPanel.add(menu);



		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image hammerImage = toolkit.getImage("hammer.png");                                            // hammer cursor         
		Cursor hammerCursor = toolkit.createCustomCursor(hammerImage, new Point(0, 0), "hammer");
		frame.setCursor(hammerCursor);                                                                  // makes the cursor a hammer
        frame.add(label);
        frame.pack();
		frame.add(topPanel, BorderLayout.EAST);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);                                  // frame appears in the middle
        frame.setVisible(true);
		
		
		loadHighScore();

        // Start the mole timer
        moleTimer = new Timer(1000, new ActionListener() {                     // mole actions
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currMoleTile != null) {
                    currMoleTile.setIcon(null);
                    currMoleTile = null;
                }

                int randomIndex = random.nextInt(8);
                currMoleTile = board[randomIndex];
                currMoleTile.setIcon(moleIcon);
            }
        });
        moleTimer.start();
		
		
		
		
		 hammerTimer = new Timer(20000, new ActionListener() {                       // moleExtra time actions
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currhammerTile != null) {
                    currhammerTile.setIcon(null);
                    currhammerTile = null;
                }

                int randomIndex = random.nextInt(8);
                currhammerTile = board[randomIndex];
                currhammerTile.setIcon(hammerIcon);
            }
        });
        hammerTimer.start();
		

        // Start the bomb timer
        bombTimer = new Timer(1500, new ActionListener() {                         // bomb actions
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currBombTile != null) {
                    currBombTile.setIcon(null);
                    currBombTile = null;
                }

                int randomIndex = random.nextInt(8);
                currBombTile = board[randomIndex];
                currBombTile.setIcon(bombIcon);
            }
        });
        bombTimer.start();

        // Start the time timer
        timeTimer = new Timer(1000, new ActionListener() {                       // game time action
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                timeLabel.setText("Time: " + time);

                if (time == 0) {                                                // game over, everything stops
					moleTimer.stop();
                    bombTimer.stop();
					hammerTimer.stop();
					timeTimer.stop();
                    JOptionPane.showMessageDialog(frame, "Time's up! Your score is " + score);
                    for (int i = 0; i < 8; i++) {
                        board[i].setEnabled(false);
                    }
                    MoleHitGameMultiplayer game = new MoleHitGameMultiplayer();
					game.played = 2;
                }
            }
        });
        timeTimer.start();
    }

    private class ButtonActionListener implements ActionListener {
        @Override                                                                // game play 
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getIcon() == moleIcon) {
                score += 10;
				if (score > highScore) {
					highScore = score;
					highScoreLabel.setText("High Score: " + highScore);
				}
                scoreLabel.setText("Score: " + score);
                button.setIcon(null);
            }  else if (button.getIcon() == hammerIcon) {
            time += 10;
            timeLabel.setText("Time: " + time);
            button.setIcon(null);
			} else if (button.getIcon() == bombIcon) {
				moleTimer.stop();
                bombTimer.stop();
				timeTimer.stop();
                JOptionPane.showMessageDialog(frame, "Game Over! Your score is " + score);
                for (int i = 0; i < 8; i++) {
                    board[i].setEnabled(false);
                }
				saveHighScore();
               
            }
        }
    }
	
	
		private void loadHighScore() {                                                   // high score from file
        try {
            File highScoreFile = new File("highscore.txt");
            if (highScoreFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(highScoreFile));                // using bufferReader!
                String line = reader.readLine();
                highScore = Integer.parseInt(line);
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading high score: " + e.getMessage());
        }
        highScoreLabel.setText("High Score: " + highScore);
    }
	
	
		    private void saveHighScore() {                                     // save the high score each time
        try {
            File highScoreFile = new File("highscore.txt");
            FileWriter writer = new FileWriter(highScoreFile);
            writer.write(String.valueOf(highScore));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving high score: " + e.getMessage());
        }
    }
	

    private class ButtonActionListener2 implements ActionListener {                           // when restart button hit
        @Override
        public void actionPerformed(ActionEvent e) {
            moleTimer.stop();
            bombTimer.stop();
			hammerTimer.stop();
			timeTimer.stop();
            new MoleHitGame();
			loadHighScore();
        }
    }	
	
	private class ButtonActionListener3 implements ActionListener {                           // when menu button hit
    @Override
    public void actionPerformed(ActionEvent e) {
        moleTimer.stop();
        bombTimer.stop();
        hammerTimer.stop();
        timeTimer.stop();
        int option = JOptionPane.showConfirmDialog(null, "Do you want to go the Menu?", "Game Pause", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.NO_OPTION) {
                                                                                     // User clicked No, show a timer for 3 seconds and then continue the game
            JOptionPane.showMessageDialog(null, "Game will resume in 3 seconds...");
            try {
                Thread.sleep(3000); // wait for 3 seconds
            } catch (InterruptedException ex) {
                                                              // handle interruption
            }
                                                          // continue the game
            moleTimer.start();
            bombTimer.start();
            hammerTimer.start();
            timeTimer.start();
        } else if (option == JOptionPane.YES_OPTION) {
                                                           // User clicked Yes, go back to menu
            new AppHitGame();
            frame.dispose();
        }
    }
}	
		
		private class ButtonActionListener4 implements ActionListener {                           // when pause button hit
        @Override
        public void actionPerformed(ActionEvent e) {
            moleTimer.stop();
            bombTimer.stop();
			hammerTimer.stop();
			timeTimer.stop();
			JOptionPane.showMessageDialog(frame, "Game is PAUSED  Your SCORE IS " + score);
                                                                                                 // continue the game
			JOptionPane.showMessageDialog(null, "Game will resume in 3 seconds...");
			 try {
                Thread.sleep(3000);                                                // wait for 3 seconds
            } catch (InterruptedException ex) {
                                                                                // handle interruption
            }
			
            moleTimer.start();
            bombTimer.start();
            hammerTimer.start();
            timeTimer.start();
        }
    }
	
	
}