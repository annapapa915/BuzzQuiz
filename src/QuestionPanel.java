import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is one of the GUI classes and it represents the panel with all the
 * components of the main single player game of the quiz.
 * When the user selects the number of rounds he/she wants to play, this panel is called and
 * the game begins. All the logic of the main game is managed by this class. It handles the rounds and
 * the user's answers and score.
 * @author Glykeria Fountoukidou
 * @author Anna Papadopoulou
 */
public class QuestionPanel{

    public ArrayList<JButton> buttons = new ArrayList<>();
    WriteToFile w = new WriteToFile();
    //the variable panel represents the main panel of the game.
    JPanel panel = new JPanel() {
        private final Image img = ImageIO.read(new File("src/images/background3.png"));
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0,0,1250,700, this);
        }
    };
    JLabel question;
    JLabel score;
    int index; // represents the player's answer (which button he/she pressed)
    ArrayList<Player> players;
    Integer num;
    ArrayList<Question> questions;
    BettingPanel betting_panel;
    JFrame frame;
    Integer max_rounds; //the number of rounds that the player chose to play.
    Integer current_round; //the number of the current round that the user is playing.
    Rounds round;
    public Timer timer;
    int timer_count = 5000;
    JLabel question_image;

    /**
     * The constructor initializes all the variables according to the parameters.
     * @param frame the frame which all the components of the panel are on, the Single Player Frame.
     * @param questions the list of all the questions
     * @param r the number of rounds the player chose to play.
     * @param players the list of the players of the game (it's always 1 in single player mode).
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Glykeria Fountoukidou
     * @author Anna Papadopoulou
     */
    public QuestionPanel(JFrame frame, ArrayList<Question> questions, int r, ArrayList<Player> players) throws IOException {   this.players = players;
        max_rounds = r;
        current_round = 0;
        round = new Rounds(players);
        this.frame = frame;
        frame.validate();
        LayoutManager layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        this.questions = questions;
        num = -1;//questions list index is -1, becomes 0 when ShowRound() is called for the first time.
        ShowRound();
        frame.setVisible(true);
    }


    /**
     * This method shows and handles the timer in the Timer type of round.
     * It creates a label for the timer and then creates an instance of a Timer.
     * The timer starts from 5000 milliseconds and then counts down to 0. It creates a variable
     * timer_count starting at 5000 and each millisecond it decreases by 100. The player's score increasement
     * is the final timer_count value multiplied by 0.2.
     * @author Anna Papadopoulou
     * @author Glykeria Fountoukidou
     */
    public void ShowTimer()
    {
        JLabel label = new JLabel("Timer: 5000");
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBackground(new Color(255,204,229));
        label.setForeground(new Color(117,95,188));
        label.setOpaque(true);
        panel.add(label);
        timer_count = 5000;
        timer = new Timer(100, e -> {
            if (timer_count <= 0) {
                timer.stop();
            } else {
                timer_count -= 100;
            }
            players.get(0).increasement = (int) ((timer_count)*0.2);
            label.setText("Timer: "+ timer_count);
        });
    }


    /**
     * This method handles the rounds of the game. Using the Rounds class, it randomly chooses
     * a round type for each round. It displays the number of the current round
     * and the type using two labels. Also, it creates a button 'Begin' so that when the user
     * presses it, the round begins, and the panel with the question appears. When all the rounds are
     * finished, the frame is disposed and it creates an instance of PrintScoreFrame to view the player's
     * final score.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Glykeria Fountoukidou
     */
    public void ShowRound() throws IOException {
        num++; //increase questions list index
        panel.removeAll();

        if (current_round.equals(max_rounds)) {
            w.WriteToFileSingle(players.get(0));
            frame.setVisible(false);
            frame.dispose();
            PrintScoreFrame end = new PrintScoreFrame(players.get(0));
        } else {
            round.ChooseRound();
            JLabel round_label = new JLabel("Round: " + (current_round+1));
            JLabel type_label = new JLabel(round.getCurrent_round());

            round_label.setFont(new Font("Serif", Font.PLAIN, 35));
            round_label.setBackground(new Color(255,204,229));
            round_label.setForeground(new Color(117,95,188));
            round_label.setOpaque(true);
            round_label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createVerticalStrut(40));

            type_label.setFont(new Font("Serif", Font.PLAIN, 30));
            type_label.setBackground(new Color(255,204,229));
            type_label.setForeground(new Color(117,95,188));
            type_label.setOpaque(true);
            type_label.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(round_label);
            panel.add(Box.createVerticalStrut(40));
            panel.add(type_label);

            JButton begin = new JButton("Begin");
            begin.setAlignmentX(Component.CENTER_ALIGNMENT);
            begin.setFont(new Font("Serif", Font.PLAIN, 25));
            begin.setBackground(new Color(204,229,255));
            begin.setForeground(new Color(117,95,188));
            panel.add(Box.createVerticalStrut(40));
            panel.add(begin);

            panel.revalidate();
            panel.repaint();
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.invalidate();
            frame.validate();


            begin.addActionListener(e -> {
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                frame.getContentPane().remove(panel);
                frame.repaint();
                if (round.getCurrent_round().equals("Gamble"))//if type is gamble
                {
                    try {
                        showGamblePanel(questions.get(num), players.get(0));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else
                    showPanel();
            });
        }
        current_round++;
    }


    /**
     * This method shows the Gamble Panel created in BettingPanel each time we have a Gamble type round.
     * The betting panel is added to the frame and all of its buttons' action listeners.
     * If the betting panel is shown in the competitive game mode, it is adjusted, so that it appears two times, once
     * for each player.
     * @param q symbolizes the current question
     * @param p symbolizes the player in single player mode.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Anna Papadopoulou
     */
    public void showGamblePanel(Question q,Player p) throws IOException {
        betting_panel = new BettingPanel(frame,q, p);
        frame.getContentPane().add(betting_panel.panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        ArrayList<JButton> gamble_buttons = betting_panel.getButtons();
        int i = 1;
        for(JButton button : gamble_buttons){
            int finalI = i;
            button.addActionListener(e -> {
                button.setBackground(Color.pink);
                button.setOpaque(true);
                button.setBorderPainted(false);
                betting_panel.panel.repaint();
                p.increasement= finalI * 250;
                frame.getContentPane().remove(betting_panel.panel);
                frame.repaint();
                //if its the last player picking, show questions
                if (p.equals(players.get(players.size()-1)))
                    showPanel();
                else {
                    try {
                        showGamblePanel(q,players.get(players.size()-1)); //else show again the gamble choices
                    } catch (IOException ioException) {                   //for the next player
                        ioException.printStackTrace();
                    }
                }

            });
            i++;
        }
    }


    /**
     * This method is responsible for displaying the questions, answers, and all the labels and
     * buttons associated with them. First it shuffles the questions answers, and checks if the question
     * is ImageQuestion or regular question. If its ImageQuestion, it creates another label with the
     * image as the background. If the current round is 'Timer' it calls the ShowTimer() method to display
     * the timer at the top of the main panel. Then it creates the labels and buttons, and adds the
     * ActionListeners to the buttons. In the ActionListener the index of the button is saved in the variable
     * 'index' and the handleAnswer() method is called. It also creates an 'OK' button that when the player
     * presses it calls ShowRound() so the next round begins, or if the current round has not already
     * finished (the player hasn't answered 5 questions yet) it calls ShowPanel() again to display the next
     * question, working recursively until each question is answered.
     * @author Glykeria Fountoukidou
     * @author Anna Papadopoulou
     *
     */
    public void showPanel() {

        panel.removeAll();

        //QuestionPanel q1 = new QuestionPanel(frame,questions.subList((round) * 5, (round) * 5 + 5)));
        Question q = questions.get(num);
        q.ShuffleAnswers();
        if (q instanceof ImageQuestion) {
            String image_path = "src/images/Questions/" + ((ImageQuestion) q).getImage();
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(image_path));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            question_image = new JLabel(new ImageIcon(image));
            question_image.setAlignmentX(Component.CENTER_ALIGNMENT);

        }
        if (round.getCurrent_round().equals("Timer"))
        {
            ShowTimer();
            timer.restart();
        }

        for ( String answer: q.getOptions()) {
            JButton option = new JButton(answer);
            buttons.add(option);
        }

        question = new JLabel(q.question);
        score = new JLabel();
        score.setText("Score: "+players.get(0).getScore());

        score.setFont(new Font("Serif", Font.PLAIN, 20));
        score.setAlignmentX(Component.CENTER_ALIGNMENT);
        score.setBackground(new Color(255,204,229));
        score.setForeground(new Color(51,51,255));
        score.setOpaque(true);
        panel.add(Box.createVerticalStrut(8));
        panel.add(score);

        panel.add(Box.createVerticalStrut(15));
        question.setFont(new Font("Serif", Font.PLAIN, 20));
        question.setBackground(new Color(255,204,229));
        question.setForeground(new Color(51,51,255));
        question.setOpaque(true);
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(question);
        if (q instanceof ImageQuestion) {
            panel.add(Box.createVerticalStrut(10));
            panel.add(question_image);
        }
        for (JButton answer: buttons) {
            panel.add(Box.createVerticalStrut(20));
            answer.setBackground(Color.PINK);
            answer.setForeground(new Color(51,51,255));
            answer.setFont(new Font("Serif", Font.PLAIN, 18));
            answer.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(answer);
        }


        for (JButton b:buttons) {
            b.addActionListener(e -> {
                index = buttons.indexOf(e.getSource());
                for (JButton b2 : buttons) {
                    b2.setEnabled(false); //disable all buttons
                }
                if (round.current_round.equals("Timer")){
                    timer.stop();
                }
                handleAnswer(index, q,players.get(0));

                JButton next = new JButton("OK");
                next.setAlignmentX(Component.CENTER_ALIGNMENT);
                next.setFont(new Font("Serif", Font.PLAIN, 25));
                next.setBackground(new Color(204,229,255));
                next.setForeground(new Color(117,95,188));
                panel.add(Box.createVerticalStrut(25));
                panel.add(next);
                panel.add(Box.createVerticalStrut(20));

                next.addActionListener(evt -> {
                    panel.removeAll();
                    frame.getContentPane().remove(panel);
                    frame.repaint();
                    buttons.clear();
                    if ((num+1)%5 == 0) { //IF 5 QUESTIONS ARE FINISHED
                        try {
                            ShowRound(); //NEXT ROUND
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                    else {           //ELSE CONTINUE ON THE SAME ROUND
                        num++; //call this function again but increase num so next question appears.
                        if (round.getCurrent_round().equals("Gamble")) {
                            try {
                                showGamblePanel(questions.get(num),players.get(0));
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        else
                            showPanel();
                    }
                });
            });


        }
        panel.revalidate();
        panel.repaint();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

    }

    /**
     * This method handles the answer of the player. If he answered correctly, his/hers score increases
     * and it calls pintRight() method. If his/her answer was wrong, it calls printWrong()
     * @param userinput the player's answer as the index of the buttons array.
     * @param q the question the player answered.
     * @param p the player that is currently playing.
     * @author Glykeria Fountoukidou
     */
    public void handleAnswer(int userinput, Question q, Player p)
    {
        if (q.checkCorrectAnswer(userinput)) {
            p.increaseScore();
            printRight(panel,buttons,userinput,p);
        }
        else
            printWrong(q,panel,buttons,userinput);
    }

    /**
     * This method displays the player's answer in red if he has picked a wrong answer.
     * @param q the current question
     * @param p the current panel
     * @param b list of player's buttons
     * @param answer index of answer the user picked.
     * @author Glykeria Fountoukidou
     */
    public void printWrong(Question q,JPanel p, ArrayList<JButton> b,int answer){
        JLabel label = new JLabel("Wrong Answer!");
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setBackground(new Color(102,178,255));
        label.setForeground(Color.black);
        label.setOpaque(true);        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        b.get(answer).setBackground(new Color(255,51,51));
        b.get(answer).setOpaque(true);
        b.get(answer).setBorderPainted(false);

        //set the correct answer from the buttons arraylist green. (same index as the right_num)
        b.get(q.right_num).setBackground(new Color(102,255,178));
        b.get(q.right_num).setOpaque(true);
        b.get(q.right_num).setBorderPainted(false);

        p.add(Box.createVerticalStrut(20));
        p.add(label);
        p.revalidate();
        p.repaint();
    }

    /**
     * This method displays the correct answer in green after the player has answered the question.
     * @param panel the current panel
     * @param b list of player's buttons
     * @param answer index of answer the user picked.
     * @param player Player type object of current player.
     * @author Glykeria Fountoukidou
     */
    public void printRight(JPanel panel, ArrayList<JButton> b,int answer, Player player){
        JLabel label = new JLabel("Correct answer!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        label.setBackground(new Color(102,178,255));
        label.setForeground(new Color(255,255,153));
        label.setOpaque(true);
        score.setText("Score: " + player.getScore());

        b.get(answer).setBackground(new Color(102,255,178));
        b.get(answer).setOpaque(true);
        b.get(answer).setBorderPainted(false);
        panel.add(Box.createVerticalStrut(20));
        panel.add(label);
        panel.revalidate();
        panel.repaint();
    }

}

