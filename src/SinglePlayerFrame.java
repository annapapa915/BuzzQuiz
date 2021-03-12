import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The class SinglePlayerFrame creates a frame for the single player mode.
 * @author Anna Papadopoulou
 */
public class SinglePlayerFrame {
    JFrame frame;
    ArrayList<Question> questions = new ArrayList<>();//save all questions here
    ArrayList<Player> players = new ArrayList<>();
    int rounds;
    JPanel panel;
    JLabel label;

    /**
     * The constructor of the class, it creates a new JFrame and first adds a new panel that asks for the player
     * to input a username. In case the player didn't type a username, the program defaults their username to Player1
     * The questions are shuffled and the players are added to an Arraylist to keep track of them (only one player in single player mode).
     * Following that, a new panel is added that contains the questions for competitive and the game begins.
     * @param r symbolizes the number of rounds the player has picked.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */
    public SinglePlayerFrame(int r) throws IOException {
        Player player = new Player(1);
        players.add(player);

        rounds = r;
        FileParser file = new FileParser("Questions.txt");
        questions = file.getQuestions();
        Collections.shuffle(questions);
        frame = new JFrame("Buzz Quiz!");
        frame.setSize(1250, 700);
        frame.setResizable(true);
        panel = new JPanel() {
            private Image img = ImageIO.read(new File("src/images/background3.png"));
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0,0,1250,700, this);
            }
        };

        LayoutManager layout = new GridBagLayout();
        panel.setLayout(layout);

        label= new JLabel("Enter a nickname for yourself: ");
        label.setFont(new Font("Verdana",Font.PLAIN, 20));
        label.setBackground(new Color(255,204,229));
        label.setForeground(new Color(117,95,188));
        label.setOpaque(true);

        panel.add(label);

        JTextField text = new JTextField(40);

        panel.add(text);

        panel.add(Box.createHorizontalStrut(30));
        JButton next = new JButton("Next");
        next.setBackground(new Color(255,204,229));
        next.setForeground(new Color(117,95,188));
        next.setFont(new Font("Serif", Font.PLAIN, 20));

        panel.add(next);
        frame.add(panel);

        next.addActionListener((ActionEvent e) -> {
            String nick = text.getText();
            if (nick.equals(""))
                player.setNickname("Player1");
            else
                player.setNickname(nick);
            frame.remove(panel);
            try {
                Begin();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        //Set Image Icon
        new ImageIcon();
        try {
            frame.setIconImage(ImageIO.read(new File("src/images/golden.png")));
        }
        catch(IOException ex) {
            System.out.println("When reading icon file: " + ex.getMessage());
        }

        frame.getContentPane().add(panel,BorderLayout.CENTER);

        //Center View
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        int x = (d.width - frame.getWidth()) / 2;
        int y = (d.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);

    }

    /**
     * A simple void method that calls the QuestionPanel's constructor, where the new panel with the questions is added
     * to the frame and the game begins.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */
    public void Begin() throws IOException {
        QuestionPanel q1 = new QuestionPanel(frame, questions,rounds,players);
    }



}





