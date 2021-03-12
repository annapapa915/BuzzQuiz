import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * The class SinglePlayerHighscores creates a frame that shows the player the top 20 high scores in single player mode.
 * @author Anna Papadopoulou
 */
public class SinglePlayerHighscores {

    private int limit;

    JFrame f;
    JPanel singlepanel;
    ArrayList<JLabel> labels= new ArrayList<>();
    HashMap<String, Integer> map = new HashMap<>();
    LinkedHashMap<String, Integer> sortedmap = new LinkedHashMap<>();

    /**
     * The constructor of the class creates a new JFrame, where it reads the games and scores saved in the singleplayer.txt.
     * Once that happens, if there are more than 20 games played the panel only shows the 20 highest scores.
     * If there are less than twenty games played, the panel displays all of the scores saved in the text.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */
    public SinglePlayerHighscores() throws IOException {

        f = new JFrame("Buzz Quiz!");
        f.setSize(1250, 700);
        f.setResizable(true);

        singlepanel = new JPanel() {
            private final Image img = ImageIO.read(new File("src/images/background.png"));
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0,0,1250,700, this);
            }
        };

        LayoutManager layout = new BoxLayout(singlepanel, BoxLayout.Y_AXIS);
        singlepanel.setLayout(layout);
        readSingleFile();

        JLabel label1 = new JLabel("Top 20 highscores in Single Player mode!");
        label1.setFont(new Font("Verdana",Font.BOLD, 18));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setBackground(new Color(255,204,229));
        label1.setForeground(Color.BLUE);
        label1.setOpaque(true);
        singlepanel.add(Box.createVerticalStrut(20));
        singlepanel.add(label1);

        for (String key : sortedmap.keySet())
        {
            JLabel l = new JLabel(key+": "+ sortedmap.get(key));
            labels.add(l);
        }
        limit = Math.min(labels.size(), 20);

        for (int i=0;i<limit;i++)
        {
            labels.get(i).setFont(new Font("Verdana",Font.PLAIN, 14));
            labels.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
            labels.get(i).setBackground(new Color(255,204,229));
            labels.get(i).setForeground(Color.BLUE);
            labels.get(i).setOpaque(true);
            singlepanel.add(Box.createVerticalStrut(5));
            singlepanel.add(labels.get(i));
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
        singlepanel.add(Box.createVerticalStrut(20));
        singlepanel.add(back);

        f.add(singlepanel,BorderLayout.CENTER);

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
     * A method that reads the scores in single player and then stores them to the HashMap map.
     * That way, each username is the key and each score the value. In order to allow duplicates keys,
     * if the key that is read is already contained in the map, the symbol _ is added. The number of times the key is contained
     * in the map corresponds to the number of times the symbol _ appears in the new keys. After reading all the keys and values in the map,
     * we sort it in a new LinkedMap called sorted, by making use of stream. A comparator compares if each value is in descending order
     * and the new sorted map is added to the LinkedMap. Once the process finishes, the reader closes.
     * @throws IOException if the file we're trying to read doesn't exist.
     * @author Anna Papadopoulou
     */
    public void readSingleFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("scores/singleplayer.txt"));
        String line;

        while ((line = reader.readLine()) != null)
        {

            String[] parts = line.split(":", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                while (map.containsKey(key)) key += "_";
                map.put(key, Integer.parseInt(value));
            }
        }

        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedmap.put(x.getKey(), x.getValue()));


        reader.close();

    }


}
