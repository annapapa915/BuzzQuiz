import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The class CompetitiveFrame creates a frame for the competitive type mode.
 * @author Anna Papadopoulou
 * @author Glykeria Fountoukidou
 */
public class CompetitiveFrame{
    ArrayList<Player> players;
    JFrame frame;
    ArrayList<Question> questions;  //save all questions here
    int rounds;
    JPanel panel;
    JLabel label,label2;

    /**
     * The constructor of the class, it creates a new JFrame and first adds a new panel that asks for the players
     * to input usernames. In case the players don't type usernames, the program defaults their usernames to Player1 and Player2 respectively.
     * The questions are shuffled and the players are added to an Arraylist to keep track of them (easier to add more players in future implementations).
     * Following that, a new panel is added that contains the questions for competitive and the game begins.
     * @param r symbolizes the number of rounds the player has picked.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */
    public CompetitiveFrame(int r) throws IOException {
        players = new ArrayList<>();
        Player player1 = new Player(1);
        players.add(player1);
        Player player2 = new Player(2);
        players.add(player2);
        rounds = r;
        FileParser file = new FileParser("Questions.txt");
        questions = file.getQuestions();
        Collections.shuffle(questions);
        frame = new JFrame("Buzz Quiz!");
        frame.setSize(1250, 700);
        frame.setResizable(true);
        panel = new JPanel() {
            private final Image img = ImageIO.read(new File("src/images/background3.png"));
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0,0,1250,700, this);
            }
        };

        LayoutManager layout = new GridBagLayout();
        panel.setLayout(layout);

        label= new JLabel("Enter a nickname for Player 1: ");
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

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;

        label2= new JLabel("Enter a nickname for Player 2: ");
        label2.setFont(new Font("Verdana",Font.PLAIN, 20));
        label2.setBackground(new Color(255,204,229));
        label2.setForeground(new Color(117,95,188));
        label2.setOpaque(true);

        panel.add(label2,c);

        JTextField text2 = new JTextField(40);
        panel.add(Box.createHorizontalStrut(30));

        panel.add(text2,c);

        next.addActionListener((ActionEvent e) -> {
            String nick = text.getText();
            String nick2 = text2.getText();
            // if the player hasn't typed a nickname, automatically set it to Player1
            if (nick.equals(""))
                player1.setNickname("Player1");
            else
                player1.setNickname(nick);
            // if the player hasn't typed a nickname, automatically set it to Player2
            if (nick2.equals(""))
                player2.setNickname("Player2");
            else
                player2.setNickname(nick2);

            frame.remove(panel);
            try {
                Begin();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        frame.add(panel);

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
     * A simple void method that calls the CompQuestionPanel's constructor, where the new panel with the questions is added
     * to the frame and the game begins.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Glykeria Fountoukidou
     */
    public void Begin() throws IOException {
        CompQuestionPanel competitive = new CompQuestionPanel(frame,questions,rounds,players);
    }



}

