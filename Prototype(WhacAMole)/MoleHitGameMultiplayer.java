import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoleHitGameMultiplayer {
    private JFrame frame;
    private JLabel label;
    private JButton SingleButton;
	private JButton BackButton;
	private JButton BackButton2;
    private JButton instructionsButton;
    private JTextArea instructionsArea;
    private ImageIcon BackgroundIcon;
	int played = 1;

    public MoleHitGameMultiplayer() {
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
					JOptionPane.showMessageDialog(frame, " Player " + played);
					MusicPlayer.RunMusic("Background_Music.wav");
					new MoleMultiplayer();
					frame.dispose();
            }
			
        });
        SingleButton.setBounds(350, 200, 200, 50);
        label.add(SingleButton);

		BackButton = new JButton("Back");
        BackButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				instructionsArea.setVisible(false);
				SingleButton.setVisible(true);
				instructionsButton.setVisible(true);
				BackButton.setVisible(false);
				BackButton2.setVisible(true);
            }
        });
		label.add(BackButton);
		BackButton.setBounds(350, 400, 200, 50);
		BackButton.setVisible(false);
		
		
		
		BackButton2 = new JButton("Back");
        BackButton2.setFont(new Font("Monospaced", Font.BOLD, 20));
        BackButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
				new AppHitGame();
            }
        });
		label.add(BackButton2);
		BackButton2.setBounds(350, 400, 200, 50);
		BackButton2.setVisible(true);

        instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instructionsArea.setVisible(true);
				SingleButton.setVisible(false);
				instructionsButton.setVisible(false);
				BackButton2.setVisible(false);
				BackButton.setVisible(true);
			
            }
        });
        instructionsButton.setBounds(350, 300, 200, 50);
        label.add(instructionsButton);


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
        new MoleHitGameMultiplayer();
    }
}