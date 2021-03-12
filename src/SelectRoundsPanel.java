import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents the panel that the user chooses the number of rounds that he/she wants to play.
 * @author Glykeria Fountoukidou
 * @author Anna Papadopoulou
 */
public class SelectRoundsPanel {
    private ArrayList<JButton> buttons = new ArrayList<>();
    JPanel panel;
    SinglePlayerFrame singleplay;
    CompetitiveFrame competitive;

    /**
     * The constructor creates the whole panel and all its components and is added to the Start Frame.
     * It sets a background image, creates the labels, the buttons of each round number etc. and all their styling options.
     * When each of these buttons is clicked, using the actionlistener methods it creates instances of
     * the SinglePlayerFrame or Competitive frame class (according to the type of game) and
     * then this frame is disposed. The rest of the game continues on another frame created by this instance.
     * @param frame the frame that this panel is on.
     * @param isCompetitive represents whether the type of game is single player or multiplayer.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Glykeria Fountoukidou
     * @author Anna Papadopoulou
     */
    public SelectRoundsPanel(JFrame frame,boolean isCompetitive) throws IOException {

        panel = new JPanel(){
            private final Image img = ImageIO.read(new File("src/images/background2.png"));
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0,0,1250,700, this);
            }
        };

        LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        panel.add(Box.createVerticalStrut(100));
        JLabel label = new JLabel("Please choose the number of rounds you want to play:");
        label.setFont(new Font("Serif", Font.PLAIN, 30));

        label.setBackground(Color.PINK);
        label.setForeground(new Color(117,95,188));
        label.setOpaque(true);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);


        for (int i = 1; i < 6; i++) {
            JButton b = new JButton("" + i);
            b.setBackground(Color.PINK);
            b.setForeground(new Color(117,95,188));
            b.setFont(new Font("Serif", Font.PLAIN, 30));
            buttons.add(b);
            panel.add(Box.createVerticalStrut(20));
            panel.add(b);
        }

        frame.getContentPane().add(panel,BorderLayout.CENTER);

        for (JButton b : buttons) {
            b.addActionListener(e -> {
                if (isCompetitive) {
                    try {
                        competitive = new CompetitiveFrame(buttons.indexOf(e.getSource())+1);
                        frame.setVisible(false);
                        frame.dispose();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else {
                    try {
                        singleplay = new SinglePlayerFrame(buttons.indexOf(e.getSource())+1);
                        frame.setVisible(false);
                        frame.dispose();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });

        }
    }




}
