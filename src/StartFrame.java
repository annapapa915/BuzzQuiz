import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class StartFrame creates a frame for the main menu of the game.
 * @author Anna Papadopoulou
 * @author Glykeria Fountoukidou
 */
public class StartFrame {

    public Clip clip;
    JButton oneplayer;
    JButton competitive;
    JButton instructions;
    JButton highscores;
    JFrame frame;


    /**
     * The constructor of the class, it creates a new frame that contains the main menu. Here, the player can pick
     * whether they want to see the instructions, play a new game in competitive or single player mode or check the high scores.
     * Each button creates a new panel that contains the choice the player picked. A soundtrack is added for immersion.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     * @author Glykeria Fountoukidou
     */
    public StartFrame() throws IOException {


        JPanel panel = new JPanel() {
            private final Image img = ImageIO.read(new File("src/images/background.png"));
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0,0,1250,700, this);
            }
        };


        LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);


        frame = new JFrame("Buzz Quiz!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        BufferedImage image = ImageIO.read(new File("src/images/starticon.png"));
        JLabel label = new JLabel(new ImageIcon(image));
        panel.add(Box.createVerticalStrut(100));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        oneplayer = new JButton();
        competitive = new JButton();
        instructions = new JButton();
        highscores = new JButton();

        oneplayer.setText("Play");
        competitive.setText("Competitive");
        instructions.setText("Instructions");
        highscores.setText("Highscores");


        panel.add(Box.createVerticalStrut(50));
        oneplayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        oneplayer.setBackground(Color.PINK);
        oneplayer.setForeground(new Color(117,95,188));
        oneplayer.setFont(new Font("Serif", Font.PLAIN, 30));
        panel.add(oneplayer);

        panel.add(Box.createVerticalStrut(20));
        competitive.setAlignmentX(Component.CENTER_ALIGNMENT);
        competitive.setBackground(Color.PINK);
        competitive.setForeground(new Color(117,95,188));
        competitive.setFont(new Font("Serif", Font.PLAIN, 30));
        panel.add(competitive);

        panel.add(Box.createVerticalStrut(20));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setBackground(Color.PINK);
        instructions.setForeground(new Color(117,95,188));
        instructions.setFont(new Font("Serif", Font.PLAIN, 30));
        panel.add(instructions);

        panel.add(Box.createVerticalStrut(20));
        highscores.setAlignmentX(Component.CENTER_ALIGNMENT);
        highscores.setBackground(Color.PINK);
        highscores.setForeground(new Color(117,95,188));
        highscores.setFont(new Font("Serif", Font.PLAIN, 30));
        panel.add(highscores);

        frame.setSize(1250,700);
        frame.setResizable(false);

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


        oneplayer.addActionListener((ActionEvent e) -> {
            frame.getContentPane().remove(panel);
            SelectRoundsPanel rounds = null;
            try {
                rounds = new SelectRoundsPanel(frame,false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            frame.invalidate();
            frame.validate();
        });

        competitive.addActionListener((ActionEvent e) -> {
            frame.getContentPane().remove(panel);
            SelectRoundsPanel rounds = null;
            try {
                rounds = new SelectRoundsPanel(frame,true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            frame.invalidate();
            frame.validate();

        });

        instructions.addActionListener((ActionEvent e) -> {
            frame.getContentPane().remove(panel);
            try {
                InstructionsPanel ins = new InstructionsPanel(frame);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            frame.invalidate();
            frame.validate();
        });

        highscores.addActionListener((ActionEvent e) -> {
            frame.getContentPane().remove(panel);
            try {
                HighscoresPanel high = new HighscoresPanel(frame);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            frame.invalidate();
            frame.validate();
        });


        File file = new File("src/soundtrack/ScatterBrain.wav");

        // sailor-moon themed soundtrack
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            // plays in loop so that it doesn't stop while playing the game
            clip.loop(200);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }


        frame.setVisible(true);
    }




}