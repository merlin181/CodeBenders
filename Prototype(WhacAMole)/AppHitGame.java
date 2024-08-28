import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppHitGame {
    private JFrame frame;
    private JPanel panel;
    private JButton startButton;
    private JButton instructionsButton;
    private JButton exitButton;
    private JTextArea instructionsArea;
    public AppHitGame() {                                 // menu, before playing the game
        frame = new JFrame("CODE BENDERS: WHAC-A-MOLE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        startButton = new JButton("Start Game");                                     // goes on to start game
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                                                                    // Start the game
                new MoleHitGame();
                frame.dispose();                                // frame dissapears                                 
            }
        });

        instructionsButton = new JButton("Instructions");
        instructionsButton.setFont(new Font("Arial", Font.BOLD, 24));
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                                                                     // Show instructions
                instructionsArea.setVisible(true);
            }
        });

        exitButton = new JButton("Exit");                                     // exits the game
        exitButton.setFont(new Font("Arial", Font.BOLD, 24));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the game
                System.exit(0);
            }
        });

        instructionsArea = new JTextArea("Instructions:\n"
                + "1. Click on the moles to score points.\n"
                + "2. Avoid clicking on the bombs Or else Game over.\n"
                + "3. Use the clock to gain extra time.\n");
             //   + "4. Play solo or with a friend in multiplayer mode.");                // working on this one
        instructionsArea.setEditable(false);
        instructionsArea.setVisible(false);
        instructionsArea.setFont(new Font("Arial", Font.PLAIN, 18));

        // Create a panel for each button to center it
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.add(startButton);
        startButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel instructionsButtonPanel = new JPanel();
        instructionsButtonPanel.add(instructionsButton);
        instructionsButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);          // Put buttons at the center

        JPanel exitButtonPanel = new JPanel();
        exitButtonPanel.add(exitButton);
        exitButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                                                                                 // Add the panels to the main panel
        panel.add(startButtonPanel);
        panel.add(instructionsButtonPanel);
        panel.add(exitButtonPanel);
        panel.add(instructionsArea);

        frame.add(panel);
        frame.setSize(400, 300);                                                   // Set the frame size
        frame.setLocationRelativeTo(null);                                         // Center the frame when it appeares
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AppHitGame();
    }
}