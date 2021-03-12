import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * The class HighscoresPanel creates a new panel in StartFrame.
 * @author Anna Papadopoulou
 */
public class HighscoresPanel {
    JPanel panel;
    JButton single;
    JButton comp;

    /**
     * The constructor of the class, it displays three new buttons, one that opens a frame with the single player
     * highscores, one that opens the games played in competitive and one that allows the player to navigate back to the main menu.
     * @param f the frame that the panel is added to (StartFrame).
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */
    public HighscoresPanel(JFrame f) throws IOException {

        panel = new JPanel() {
            private final Image img = ImageIO.read(new File("src/images/background.png"));
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0,0,1250,700, this);
            }
        };

        LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        single = new JButton();
        comp = new JButton();

        single.setText("Single Player Scores");
        comp.setText("Competitive Scores");

        panel.add(Box.createVerticalStrut(50));
        single.setAlignmentX(Component.CENTER_ALIGNMENT);
        single.setBackground(Color.PINK);
        single.setForeground(new Color(117,95,188));
        single.setFont(new Font("Serif", Font.PLAIN, 30));
        panel.add(single);


        panel.add(Box.createVerticalStrut(20));
        comp.setAlignmentX(Component.CENTER_ALIGNMENT);
        comp.setBackground(Color.PINK);
        comp.setForeground(new Color(117,95,188));
        comp.setFont(new Font("Serif", Font.PLAIN, 30));
        panel.add(comp);

        f.add(panel,BorderLayout.CENTER);

        single.addActionListener((ActionEvent e) -> {
            f.setVisible(false);
            f.dispose();
            try {
                SinglePlayerHighscores sng = new SinglePlayerHighscores();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            f.invalidate();
            f.validate();
        });

        comp.addActionListener((ActionEvent e) -> {
            f.setVisible(false);
            f.dispose();
            try {
                CompetitiveHighscores comph = new CompetitiveHighscores();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            f.invalidate();
            f.validate();
        });

        JButton back = new JButton("Go back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setBackground(Color.PINK);
        back.setForeground(Color.BLUE);
        back.setFont(new Font("Serif", Font.PLAIN, 20));

        back.addActionListener((ActionEvent e) -> {
            f.setVisible(false);
            f.dispose();
            try {
                StartFrame s = new StartFrame();
                s.clip.stop();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
       panel.add(Box.createVerticalStrut(300));
       panel.add(back);


    }

}
