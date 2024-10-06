import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppHitGame {
	private MoleHitGame game = new MoleHitGame();
    private JFrame frame;
    private JLabel label;
    private JButton SingleButton;
	private JButton BackButton;
    private JButton instructionsButton;
    private JButton exitButton;
	private JButton charButton;
	private ImageIcon moleIcon;
	private ImageIcon moleIcon2;
	private ImageIcon hammerIcon;
	private ImageIcon hammerIcon2;
	private ImageIcon hammerIcon3;
    private JTextArea instructionsArea;
    private ImageIcon BackgroundIcon;
	private Cursor hammerCursor;   // default
	private Cursor newhammerCursor;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();;
	private Image hammerImage;
	private JButton gameChar = new JButton();
	private JButton gameChar2 = new JButton();
	private JButton gameChar3 = new JButton();
	private JButton gameChar4 = new JButton();
	private JButton gameChar5 = new JButton();
	

    public AppHitGame() {

        moleIcon = new ImageIcon(this.getClass().getResource("/newMole.png"));                                      // mole image
        moleIcon = new ImageIcon(moleIcon.getImage().getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH));
		game.setMoleChar(moleIcon);  // default character
		
		moleIcon2 = new ImageIcon(this.getClass().getResource("/newMole2.png"));                                      // mole image
        moleIcon2 = new ImageIcon(moleIcon2.getImage().getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH));
		
		hammerIcon = new ImageIcon(this.getClass().getResource("/hammer.png"));                              
        hammerIcon = new ImageIcon(hammerIcon.getImage().getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH));
		
		hammerIcon2 = new ImageIcon(this.getClass().getResource("/hammer2.png"));                              
        hammerIcon2 = new ImageIcon(hammerIcon2.getImage().getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH));
		
		hammerIcon3 = new ImageIcon(this.getClass().getResource("hammer3.png"));                              
        hammerIcon3 = new ImageIcon(hammerIcon3.getImage().getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH));
		
		
		// cursor into hammer icon
		hammerImage = toolkit.getImage("hammer.png");    // default hammer                                        // hammer cursor         
		hammerCursor = toolkit.createCustomCursor(hammerImage, new Point(0, 0), "hammer");
		game.setHammerChar(hammerCursor);
		
		
		
        BackgroundIcon = new ImageIcon(this.getClass().getResource("/Background.jpeg"));
        BackgroundIcon = new ImageIcon(BackgroundIcon.getImage().getScaledInstance(900, 600, java.awt.Image.SCALE_SMOOTH));
        label = new JLabel(BackgroundIcon);
        label.setLayout(null);

        frame = new JFrame("CODE BENDERS: WHAC-A-MOLE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SingleButton = new JButton("Play");
        SingleButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        SingleButton.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
                game.startGame();
                frame.dispose();                       // get rid of menu frame when game starts
            }
        });
        SingleButton.setBounds(350, 100, 200, 50);
        label.add(SingleButton);
		
		BackButton = new JButton("Back");
        BackButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				instructionsArea.setVisible(false);
				SingleButton.setVisible(true);
				instructionsButton.setVisible(true);
				exitButton.setVisible(true);
				charButton.setVisible(true);
				BackButton.setVisible(false);
				gameChar.setVisible(false);        // makes some characters dissapear when back button is hit
				gameChar2.setVisible(false);
				gameChar3.setVisible(false);
				gameChar4.setVisible(false);
				gameChar5.setVisible(false);
            }
        });
		label.add(BackButton);
		BackButton.setBounds(350, 450, 200, 50);
		BackButton.setVisible(false);

        instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        instructionsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                instructionsArea.setVisible(true);        // make instructions appear and buttons dissapear
				SingleButton.setVisible(false);
				instructionsButton.setVisible(false);
				exitButton.setVisible(false);
				BackButton.setVisible(true);
				charButton.setVisible(false);
			
            }
        });
        instructionsButton.setBounds(350, 200, 200, 50);
        label.add(instructionsButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitButton.setBounds(350, 400, 200, 50);
        label.add(exitButton);
		
		charButton = new JButton("Characters");
        charButton.setFont(new Font("Monospaced", Font.BOLD, 20));          // game characters
        charButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
			instructionsArea.setVisible(false);
			SingleButton.setVisible(false);
			instructionsButton.setVisible(false);
			exitButton.setVisible(false);
			BackButton.setVisible(true);
			charButton.setVisible(false);
			chooseChar();
			
            }
        });
        charButton.setBounds(350, 300, 200, 50);
        label.add(charButton);


        instructionsArea = new JTextArea("Instructions:\n"                    // instruction for the game
                + "1. Click on the moles to score points.\n"
                + "2. Avoid clicking on the bombs Or else Game over.\n"
                + "3. Use the clock to gain extra time.\n"
				+ "4. Click on Characters button to choose your own characters.\n");
        instructionsArea.setEditable(false);
        instructionsArea.setVisible(false);
        instructionsArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        instructionsArea.setBounds(100, 100, 700, 400);
        label.add(instructionsArea);



        frame.add(label);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		frame.setResizable(false);
    }
	
	public void chooseChar() {                   // game characters 
		
		label.add(gameChar);
		gameChar.setIcon(moleIcon);
		gameChar.addActionListener(new ButtonActionListener(moleIcon));
		gameChar.setBounds(50, 100, 200, 100);   
		label.add(gameChar2);
		gameChar2.setIcon(moleIcon2);
		gameChar2.addActionListener(new ButtonActionListener(moleIcon2));
		gameChar2.setBounds(50, 300, 200, 100);   
		label.add(gameChar3);
		gameChar3.setIcon(hammerIcon);
		gameChar3.setBounds(630, 100, 200, 100);
		gameChar3.addActionListener(new ButtonActionListener2("hammer.png"));
		label.add(gameChar4);
		gameChar4.setIcon(hammerIcon2);
		gameChar4.setBounds(350, 100, 200, 100);
		gameChar4.addActionListener(new ButtonActionListener2("hammer2.png"));		
		label.add(gameChar5);
		gameChar5.setIcon(hammerIcon3);
        gameChar5.setBounds(350, 300, 200, 100);   // Set the position and size of the buttons and their sizes
		gameChar5.addActionListener(new ButtonActionListener2("hammer3.png"));
		
		
		gameChar.setVisible(true);
		gameChar2.setVisible(true);
		gameChar3.setVisible(true);
		gameChar4.setVisible(true);
		gameChar5.setVisible(true);
		
	}
	
	
	
	private class ButtonActionListener implements ActionListener {                    // when mole characters is selected              
 
	private ImageIcon gIcon;
	
	public ButtonActionListener(ImageIcon icon) {
		this.gIcon = icon;
		
	} // constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {     
		game.moveMole(); // first move a mole
		game.setMoleChar(gIcon);
        }
    }	
	
	private class ButtonActionListener2 implements ActionListener {              // when hammer character is selected
	
	private String image;
	
	public ButtonActionListener2(String image) {
		this.image = image;
		
	} // constructor
 	
    @Override
    public void actionPerformed(ActionEvent e) {
		Image newHammerImage = toolkit.getImage(image);                                         // hammer cursor         
		newhammerCursor = toolkit.createCustomCursor(newHammerImage, new Point(0, 0), "hammer");
		game.setHammerChar(newhammerCursor);

       }
    }	
		

    public static void main(String[] args) {
        new AppHitGame();
    }
}


