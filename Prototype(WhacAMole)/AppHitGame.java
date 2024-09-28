import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppHitGame {
    private JFrame frame;
    private JLabel label;
    private JButton SingleButton;
	private JButton BackButton;
    private JButton MultipleButton;
    private JButton instructionsButton;
    private JButton exitButton;
    private JTextArea instructionsArea;
    private ImageIcon BackgroundIcon;

    public AppHitGame() {
        BackgroundIcon = new ImageIcon(this.getClass().getResource("/Background.jpeg"));
        BackgroundIcon = new ImageIcon(BackgroundIcon.getImage().getScaledInstance(900, 600, java.awt.Image.SCALE_SMOOTH));
        label = new JLabel(BackgroundIcon);
        label.setLayout(null);

        frame = new JFrame("CODE BENDERS: WHAC-A-MOLE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SingleButton = new JButton("SinglePlayer");
        SingleButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        SingleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicPlayer.stopMusic(); // Stop any previously playing music
		MusicPlayer.RunMusic("Background_Music.wav");
                new MoleHitGame();
                frame.dispose();
            }
        });
        SingleButton.setBounds(350, 100, 200, 50);
        label.add(SingleButton);

        MultipleButton = new JButton("Multiplayer");
        MultipleButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        MultipleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				new MoleHitGameMultiplayer();
                frame.dispose();
            }
        });
        MultipleButton.setBounds(350, 200, 200, 50);
        label.add(MultipleButton);
		
		BackButton = new JButton("Back");
        BackButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				instructionsArea.setVisible(false);
				SingleButton.setVisible(true);
				MultipleButton.setVisible(true);
				instructionsButton.setVisible(true);
				exitButton.setVisible(true);
				BackButton.setVisible(false);
            }
        });
		label.add(BackButton);
		BackButton.setBounds(350, 400, 200, 50);
		BackButton.setVisible(false);

        instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructionsArea.setVisible(true);
				SingleButton.setVisible(false);
				MultipleButton.setVisible(false);
				instructionsButton.setVisible(false);
				exitButton.setVisible(false);
				BackButton.setVisible(true);
			
            }
        });
        instructionsButton.setBounds(350, 300, 200, 50);
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


        instructionsArea = new JTextArea("Instructions:\n"
                + "1. Click on the moles to score points.\n"
                + "2. Avoid clicking on the bombs Or else Game over.\n"
                + "3. Use the clock to gain extra time.\n");
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

    public static void main(String[] args) {
        new AppHitGame();
    }
}
