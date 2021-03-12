import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class BettingPanel creates a betting panel used for the Gamble type round.
 * @author Anna Papadopoulou
 */
public class BettingPanel {

    JPanel panel;
    JButton bet1,bet2,bet3,bet4;

    /**
     * The constructor of the class, it creates a new JPanel that shows the bets the player can choose and the category of
     * the following question in the quiz game. It can be used for both the competitive and the single player mode.
     * @param frame the frame in which we add the panel with its components.
     * @param q the question that follows the betting panel.
     * @param p the player.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */

    public BettingPanel(JFrame frame,Question q,Player p) throws IOException {
        panel = new JPanel() {
            private final Image img = ImageIO.read(new File("src/images/background3.png"));
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0,0,1250,700, this);
            }
        };

        LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        panel.add(Box.createVerticalStrut(100));

        JLabel label1 = new JLabel("Category is: "+q.getCategory());
        JLabel label2 = new JLabel(p.getNickname());
        JLabel label = new JLabel("Please choose your bet:");

        label1.setFont(new Font("Serif", Font.PLAIN, 30));
        label1.setBackground(new Color(255,204,229));
        label1.setForeground(new Color(51,51,255));
        label1.setOpaque(true);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label1);

        label2.setFont(new Font("Serif", Font.PLAIN, 30));
        label2.setBackground(new Color(255,204,229));
        label2.setForeground(new Color(51,51,255));
        label2.setOpaque(true);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(15));
        panel.add(label2);

        panel.add(Box.createVerticalStrut(30));

        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setBackground(new Color(255,204,229));
        label.setForeground(new Color(51,51,255));
        label.setOpaque(true);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(5));
        panel.add(label);

        panel.add(Box.createVerticalStrut(30));

        bet1 = new JButton();
        bet1.setText("250");
        bet1.setFont(new Font("Serif", Font.PLAIN, 20));
        bet1.setBackground(new Color(255,204,229));
        bet1.setForeground(new Color(51,51,255));
        bet1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(bet1);

        panel.add(Box.createVerticalStrut(10));
        bet2 = new JButton();
        bet2.setText("500");
        bet2.setFont(new Font("Serif", Font.PLAIN, 20));
        bet2.setBackground(new Color(255,204,229));
        bet2.setForeground(new Color(51,51,255));
        bet2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(bet2);

        panel.add(Box.createVerticalStrut(10));
        bet3 = new JButton();
        bet3.setText("750");
        bet3.setFont(new Font("Serif", Font.PLAIN, 20));
        bet3.setBackground(new Color(255,204,229));
        bet3.setForeground(new Color(51,51,255));
        bet3.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(bet3);

        panel.add(Box.createVerticalStrut(10));
        bet4 = new JButton();
        bet4.setText("1000");
        bet4.setFont(new Font("Serif", Font.PLAIN, 20));
        bet4.setBackground(new Color(255,204,229));
        bet4.setForeground(new Color(51,51,255));
        bet4.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(bet4);


    }

    /**
     * A simple getter that returns the betting buttons in the form of an Arraylist.
     * @return buttons in the form of an Arraylist.
     * @author Anna Papadopoulou
     */
    public ArrayList<JButton> getButtons(){
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(bet1);
        buttons.add(bet2);
        buttons.add(bet3);
        buttons.add(bet4);
        return buttons;
    }

}

