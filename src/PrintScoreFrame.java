import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * The class PrintScoreFrame creates a frame that displays the player's score after the game has ended in single player mode.
 * @author Anna Papadopoulou
 */
public class PrintScoreFrame {
    JFrame frame;
    JPanel panel;

    /**
     * The constructor of the class, each time a game in single player mode, it creates a new JFrame that
     * includes a panel showing the player's score.
     * The player then, can either pick to play a new game and be redirected to the main menu or exit the program.
     * @param p the player, a Player type object.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */
    public PrintScoreFrame(Player p) throws IOException {

        frame = new JFrame("Buzz Quiz!");
        panel = new JPanel(){
        private final Image img = ImageIO.read(new File("src/images/background3.png"));
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0,0,1250,700, this);
        }
        };

        BufferedImage image = ImageIO.read(new File("src/images/scoreicon.png"));
        JLabel icon = new JLabel(new ImageIcon(image));
        panel.add(Box.createVerticalStrut(100));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(icon);


        JLabel label = new JLabel("You finished with score: " + p.getScore());
        label.setFont(new Font("Serif", Font.PLAIN, 50));
        label.setBackground(new Color(204,229,255));
        label.setForeground(new Color(51,51,255));
        label.setOpaque(true);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setSize(1250,700);
        frame.setResizable(true);

        //Center View
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        int x = (d.width-frame.getWidth())/2;
        int y = (d.height-frame.getHeight())/2;
        frame.setLocation(x, y);

        //Set Image Icon
        new ImageIcon();
        try {
            frame.setIconImage(ImageIO.read(new File("src/images/golden.png")));
        }
        catch(IOException ex) {
            System.out.println("When reading icon file: " + ex.getMessage());
        }

        frame.getContentPane().add(panel,BorderLayout.CENTER);


        LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        panel.add(label);

        JButton play_again = new JButton("Play again");
        panel.add(Box.createVerticalStrut(20));
        play_again.setBackground(new Color(204,229,255));
        play_again.setForeground(new Color(117,95,188));
        play_again.setFont(new Font("Serif", Font.PLAIN, 24));
        play_again.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(play_again);

        JButton exit = new JButton("Exit");
        panel.add(Box.createVerticalStrut(15));
        exit.setBackground(new Color(204,229,255));
        exit.setForeground(new Color(117,95,188));
        exit.setFont(new Font("Serif", Font.PLAIN, 24));
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(exit);

        play_again.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                try {
                    StartFrame s = new StartFrame();
                    s.clip.stop();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.getContentPane().add(panel,BorderLayout.CENTER);

        frame.setVisible(true);

    }


}
