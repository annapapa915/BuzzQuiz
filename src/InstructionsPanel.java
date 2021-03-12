import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
/**
 * The class InstructionsPanel creates a new panel in StartFrame that contains instructions for the quiz game.
 * @author Anna Papadopoulou
 */
public class InstructionsPanel {
    JPanel panel;
    JButton back;
    /**
     * The constructor of the class, it creates a new panel that displays all of the instructions that are necessary
     * for playing the game. It also has a button that allows navigation back to the main menu.
     * @param f the frame the panel is added to (StartFrame).
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */
    public InstructionsPanel(JFrame f) throws IOException {

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

        JLabel single = new JLabel("Single Player Mode");

        JLabel howtoplaysingle = new JLabel("You have to answer 5 questions each round. "+"You can choose up to 5 rounds to play. "
                +"Each type of round is random and can be one of the following:");


        JLabel correct = new JLabel("Correct Answer: You gain 1000 points for answering correctly.");

        JLabel gamble = new JLabel("Gamble: You choose a bet corresponding to the category of the question. If you answer" +
                " correctly you gain the amount of bet you chose, otherwise you gain 0 points.");

        JLabel timer = new JLabel("Timer: A timer is present that counts 5 seconds. The amount of points you earn" +
                " equals to the time you have left multiplied by 0.2.");

        single.setFont(new Font("Verdana", Font.BOLD, 20));
        single.setAlignmentX(Component.CENTER_ALIGNMENT);
        single.setBackground(new Color(255,204,229));
        single.setForeground(Color.BLUE);
        single.setOpaque(true);
        panel.add(single);

        panel.add(Box.createVerticalStrut(20));
        howtoplaysingle.setFont(new Font("Verdana", Font.BOLD, 15));
        howtoplaysingle.setAlignmentX(Component.CENTER_ALIGNMENT);
        howtoplaysingle.setBackground(new Color(255,204,229));
        howtoplaysingle.setForeground(Color.BLUE);
        howtoplaysingle.setOpaque(true);
        panel.add(howtoplaysingle);

        panel.add(Box.createVerticalStrut(20));
        correct.setFont(new Font("Verdana",Font.PLAIN, 14));
        correct.setAlignmentX(Component.CENTER_ALIGNMENT);
        correct.setBackground(new Color(255,204,229));
        correct.setForeground(Color.BLUE);
        correct.setOpaque(true);
        panel.add(correct);

        panel.add(Box.createVerticalStrut(20));
        gamble.setFont(new Font("Verdana",Font.PLAIN, 14));
        gamble.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamble.setBackground(new Color(255,204,229));
        gamble.setForeground(Color.BLUE);
        gamble.setOpaque(true);
        panel.add(gamble);

        panel.add(Box.createVerticalStrut(20));
        timer.setFont(new Font("Verdana",Font.PLAIN, 14));
        timer.setAlignmentX(Component.CENTER_ALIGNMENT);
        timer.setBackground(new Color(255,204,229));
        timer.setForeground(Color.BLUE);
        timer.setOpaque(true);
        panel.add(timer);


        JLabel comp = new JLabel("Competitive Mode");

        JLabel howtoplaycomp = new JLabel("Two players have to answer 5 questions each round. "+"You can choose up to 5 rounds to play. ");

        JLabel howtoplaycomp2 = new JLabel("The first player can select an answer using the 1/2/3/4 keys,");

        JLabel howtoplaycomp3 = new JLabel(" while the second player can select an answer using the V/B/M/N keys."+
                                                "The players can move to the next question by pressing Enter.");

        JLabel howtoplaycomp4 = new JLabel("Each type of round is random and can be one of the following:");

        JLabel correct2 = new JLabel("Correct Answer: Each player gains 1000 points for answering correctly.");

        JLabel gamble2 = new JLabel("Gamble: Each player chooses a bet corresponding to the category of the question.");

        JLabel gamble2cont = new JLabel("If the player answers correctly, they gain the amount of bet they chose, otherwise they gain 0 points.");

        JLabel timer2 = new JLabel("Timer: A timer is present that counts 5 seconds. The amount of points each player earns" +
                " equals to the time they have left multiplied by 0.2.");

        JLabel quickanswer = new JLabel("Quick Answer: The first player that answers correctly gains 1000 point, while the second gains 500.");

        JLabel thermometer = new JLabel("Thermometer: This round has 10 questions instead of 5. The first player that answers correctly 5 questions, earns 5000 points.");

        panel.add(Box.createVerticalStrut(30));
        comp.setFont(new Font("Verdana", Font.BOLD, 20));
        comp.setAlignmentX(Component.CENTER_ALIGNMENT);
        comp.setBackground(new Color(255,204,229));
        comp.setForeground(Color.BLUE);
        comp.setOpaque(true);
        panel.add(comp);

        panel.add(Box.createVerticalStrut(20));
        howtoplaycomp.setFont(new Font("Verdana", Font.BOLD, 15));
        howtoplaycomp.setAlignmentX(Component.CENTER_ALIGNMENT);
        howtoplaycomp.setBackground(new Color(255,204,229));
        howtoplaycomp.setForeground(Color.BLUE);
        howtoplaycomp.setOpaque(true);
        panel.add(howtoplaycomp);

        howtoplaycomp2.setFont(new Font("Verdana", Font.BOLD, 15));
        howtoplaycomp2.setAlignmentX(Component.CENTER_ALIGNMENT);
        howtoplaycomp2.setBackground(new Color(255,204,229));
        howtoplaycomp2.setForeground(Color.BLUE);
        howtoplaycomp2.setOpaque(true);
        panel.add(howtoplaycomp2);

        howtoplaycomp3.setFont(new Font("Verdana", Font.BOLD, 15));
        howtoplaycomp3.setAlignmentX(Component.CENTER_ALIGNMENT);
        howtoplaycomp3.setBackground(new Color(255,204,229));
        howtoplaycomp3.setForeground(Color.BLUE);
        howtoplaycomp3.setOpaque(true);
        panel.add(howtoplaycomp3);

        howtoplaycomp4.setFont(new Font("Verdana", Font.BOLD, 15));
        howtoplaycomp4.setAlignmentX(Component.CENTER_ALIGNMENT);
        howtoplaycomp4.setBackground(new Color(255,204,229));
        howtoplaycomp4.setForeground(Color.BLUE);
        howtoplaycomp4.setOpaque(true);
        panel.add(howtoplaycomp4);

        panel.add(Box.createVerticalStrut(20));
        correct2.setFont(new Font("Verdana",Font.PLAIN, 14));
        correct2.setAlignmentX(Component.CENTER_ALIGNMENT);
        correct2.setBackground(new Color(255,204,229));
        correct2.setForeground(Color.BLUE);
        correct2.setOpaque(true);
        panel.add(correct2);

        panel.add(Box.createVerticalStrut(20));
        gamble2.setFont(new Font("Verdana",Font.PLAIN, 14));
        gamble2.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamble2.setBackground(new Color(255,204,229));
        gamble2.setForeground(Color.BLUE);
        gamble2.setOpaque(true);
        panel.add(gamble2);

        gamble2cont.setFont(new Font("Verdana",Font.PLAIN, 14));
        gamble2cont.setAlignmentX(Component.CENTER_ALIGNMENT);
        gamble2cont.setBackground(new Color(255,204,229));
        gamble2cont.setForeground(Color.BLUE);
        gamble2cont.setOpaque(true);
        panel.add(gamble2cont);

        panel.add(Box.createVerticalStrut(20));
        timer2.setFont(new Font("Verdana",Font.PLAIN, 14));
        timer2.setAlignmentX(Component.CENTER_ALIGNMENT);
        timer2.setBackground(new Color(255,204,229));
        timer2.setForeground(Color.BLUE);
        timer2.setOpaque(true);
        panel.add(timer2);

        panel.add(Box.createVerticalStrut(20));
        quickanswer.setFont(new Font("Verdana",Font.PLAIN, 14));
        quickanswer.setAlignmentX(Component.CENTER_ALIGNMENT);
        quickanswer.setBackground(new Color(255,204,229));
        quickanswer.setForeground(Color.BLUE);
        quickanswer.setOpaque(true);
        panel.add(quickanswer);

        panel.add(Box.createVerticalStrut(20));
        thermometer.setFont(new Font("Verdana",Font.PLAIN, 14));
        thermometer.setAlignmentX(Component.CENTER_ALIGNMENT);
        thermometer.setBackground(new Color(255,204,229));
        thermometer.setForeground(Color.BLUE);
        thermometer.setOpaque(true);
        panel.add(thermometer);

        back = new JButton("Go back");
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
        panel.add(Box.createVerticalStrut(50));
        panel.add(back);



        f.getContentPane().add(panel,BorderLayout.CENTER);
    }

}
