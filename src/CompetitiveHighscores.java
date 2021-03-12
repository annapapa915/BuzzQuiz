import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * The class CompetitiveHighscores creates a frame that shows the player the recent games in competitive mode.
 * @author Anna Papadopoulou
 */
public class CompetitiveHighscores {
    private int limit;

    JFrame f;
    JPanel comppanel;
    ArrayList<String> matches = new ArrayList<>();
    ArrayList<JLabel> labels= new ArrayList<>();

    /**
     * The constructor of the class creates a new JFrame, where it reads the games and scores saved in the multiplayer.txt.
     * Once that happens, if there are more than 20 games played the panel only shows the 20 most recent ones.
     * If there are less than twenty games played, the panel displays all of the games saved in the text.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */
    public CompetitiveHighscores() throws IOException {
        f = new JFrame("Buzz Quiz!");
        f.setSize(1250, 700);
        f.setResizable(true);

        comppanel = new JPanel() {
            private final Image img = ImageIO.read(new File("src/images/background.png"));
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0,0,1250,700, this);
            }
        };

        LayoutManager layout = new BoxLayout(comppanel, BoxLayout.Y_AXIS);
        comppanel.setLayout(layout);


        JLabel label1 = new JLabel("20 most recent matches played in Competitive mode!");
        label1.setFont(new Font("Verdana",Font.BOLD, 18));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setBackground(new Color(255,204,229));
        label1.setForeground(Color.BLUE);
        label1.setOpaque(true);
        comppanel.add(Box.createVerticalStrut(20));
        comppanel.add(label1);

        readCompFile();

        for(String m : matches) {
            JLabel l = new JLabel(m);
            labels.add(l);
        }

        // if>20 then display the first twenty games
        // else display all of the games
        limit = Math.min(labels.size(), 20);

        for (int i=1;i<=limit;i++)
        {
            labels.get(labels.size()-i).setFont(new Font("Verdana",Font.PLAIN, 14));
            labels.get(labels.size()-i).setAlignmentX(Component.CENTER_ALIGNMENT);
            labels.get(labels.size()-i).setBackground(new Color(255,204,229));
            labels.get(labels.size()-i).setForeground(Color.BLUE);
            labels.get(labels.size()-i).setOpaque(true);
            comppanel.add(Box.createVerticalStrut(5));
            comppanel.add(labels.get(labels.size()-i));
        }

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
        comppanel.add(Box.createVerticalStrut(20));
        comppanel.add(back);


        f.add(comppanel,BorderLayout.CENTER);

        //Set Image Icon
        new ImageIcon();
        try {
            f.setIconImage(ImageIO.read(new File("src/images/golden.png")));
        }
        catch(IOException ex) {
            System.out.println("When reading icon file: " + ex.getMessage());
        }

        //Center View
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        int x = (d.width - f.getWidth()) / 2;
        int y = (d.height - f.getHeight()) / 2;
        f.setLocation(x, y);

        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    /**
     * A method that reads the games played in competitive and then stores them to the Arraylist matches.
     * Each game is a line in the multiplayer text and contains the player's score and who won.
     * @author Anna Papadopoulou
     * @throws IOException if the file we're trying to read doesn't exist.
     */
    public void readCompFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("scores/multiplayer.txt"));
        String line;

        while ((line = reader.readLine()) != null)
        {
            matches.add(line);
        }
        reader.close();

    }




}
