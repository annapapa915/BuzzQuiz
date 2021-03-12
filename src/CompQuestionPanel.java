import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is one of the GUI classes and it represents the panel with all the
 * components of the main multiplayer game of the quiz. It extends the QuestionPanel which is
 * the class that manages the single player game. There are many similarities between these two game modes.
 * After the users select the number of rounds they want to play, this panel is called in CompetitiveFrame and
 * the game begins. All the logic of the main game is managed by this class. It handles the rounds and
 * the users' answers and scores.
 * @author Glykeria Fountoukidou
 * @author Anna Papadopoulou
 */
public class CompQuestionPanel extends QuestionPanel {
    JPanel player1, player2;
    JLabel score1, score2;
    WriteToFile w2 = new WriteToFile();
    boolean pressed1, pressed2 = false;
    ArrayList<JButton> buttons2 = new ArrayList<>();
    int index2 ;
    Player first_pressed;
    int correct_answers1, correct_answers2 = 0;
    int MAX_ROUND_QUESTIONS, current_round_questions;
    boolean finish_round = false;


    /**
     * The constructor of the class calls the super() method so that the super class initializes all the
     * variables needed.
     * @param frame the frame which all the components of the panel are on, the Competitive Frame.
     * @param questions the list of all the questions
     * @param r the number of rounds the players chose to play.
     * @param pl the list of the players of the game (it's always 2 in multiplayer mode).
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Glykeria Fountoukidou
     */
    public CompQuestionPanel(JFrame frame, ArrayList<Question> questions, int r,ArrayList<Player> pl) throws IOException {
        super(frame, questions, r,pl);
    }

    /**
     * This method handles the rounds of the game. Using the Rounds class, it randomly chooses
     * a round type for each round. It sets the maximum number of questions per round at 5 except when
     * the round is 'Thermometer' where it sets the maximum at 10.
     * It displays the number of the current round and the type using two labels.
     * Also, it creates a button 'Begin' so that when the user
     * presses it, the round begins, and the panel with the question appears.
     * When all the rounds are finished, the frame is disposed and it creates an
     * instance of CompPrintScoreFrame to view the players' final score.
     * @throws IOException if there is a failure while reading the background image used in the panel.
     * @author Glykeria Fountoukidou
     */
    @Override
    public void ShowRound() throws IOException {
        panel.removeAll();
        MAX_ROUND_QUESTIONS = 5;
        current_round_questions = 0;
        num++; //increase questions list index
        if (current_round.equals(max_rounds)) {
            frame.setVisible(false);
            frame.dispose();
            w2.WriteToFileComp(players.get(0),players.get(1));
            CompPrintScoreFrame end = new CompPrintScoreFrame(players.get(0),players.get(1));
        } else {
            round.ChooseRoundComp();
            if (round.getCurrent_round().equals("Thermometer")){
                MAX_ROUND_QUESTIONS=10;
            }
            JLabel round_label = new JLabel("Round: " + (current_round+1));
            JLabel type_label = new JLabel(round.getCurrent_round());

            panel.add(Box.createVerticalStrut(40));
            round_label.setFont(new Font("Serif", Font.PLAIN, 35));
            round_label.setAlignmentX(Component.CENTER_ALIGNMENT);
            round_label.setBackground(new Color(255,204,229));
            round_label.setForeground(new Color(117,95,188));
            round_label.setOpaque(true);
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

            panel.repaint();
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.validate();


            begin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
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
                }
            });
        }
        current_round++;
    }


    /**
     * This method is responsible for displaying the questions, answers, and all the labels for
     * each player and buttons associated with them.
     * First it shuffles the questions answers, and checks if the question
     * is ImageQuestion or regular question. If its ImageQuestion, it creates another label with the
     * image as the background. If the current round is 'Timer' it calls the ShowTimer() method to display
     * the timer at the top of the main panel. Then it creates the labels and buttons for each player.
     * For each set of buttons, we create a KeyBinding instance to assign certain keys to the buttons so
     * the players can play using the keyboard.
     * Then it adds the ActionListeners for each set of buttons (different listener for each player's set).
     * In the ActionListeners the index of the button is saved in the variable 'index' and
     * the handleAnswer() method is called. It also creates an 'OK' button that when the player
     * presses it calls ShowRound() so the next round begins, or if the current round has not already
     * finished (the player hasn't answered 5 questions yet) it calls ShowPanel() again to display the next
     * question.
     * @author Glykeria Fountoukidou
     * @author Anna Papadopoulou
     *
     */
    @Override
    public void showPanel(){
        panel.removeAll();

        JPanel QuestionsPanel = new JPanel();
        QuestionsPanel.setOpaque(false);


        //QuestionPanel q1 = new QuestionPanel(frame,questions.subList((round) * 5, (round) * 5 + 5)));
        Question q = questions.get(num);
        current_round_questions++;
        q.ShuffleAnswers();

        if (round.getCurrent_round().equals("Timer"))
        {
            ShowTimer();
            super.timer.restart();
        }


        for ( String answer: q.getOptions()) {
            JButton option = new JButton(answer);
            buttons.add(option);
        }

        for ( String answer: q.getOptions()) {
            JButton option = new JButton(answer);
            buttons2.add(option);
        }

        JLabel question = new JLabel(q.question);
        question.setFont(new Font("Serif", Font.PLAIN, 20));
        question.setBackground(new Color(204,229,255));
        question.setForeground(new Color(117,95,188));
        question.setOpaque(true);
        question.setAlignmentY(Component.CENTER_ALIGNMENT);
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(question,BorderLayout.NORTH);
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
            panel.add(Box.createVerticalStrut(10));
            panel.add(question_image);
        }

        //PLAYER 1
        player1 = new JPanel();
        player1.setOpaque(false);
        score1 = new JLabel("Score: "+players.get(0).getScore());
        createlabel(score1);

        JLabel nickname1 = new JLabel(players.get(0).getNickname());
        createlabel(nickname1);

        player1.setLayout(new BoxLayout(player1, BoxLayout.Y_AXIS));
        player1.add(nickname1,BorderLayout.CENTER);
        player1.add(Box.createVerticalStrut(5));
        player1.add(score1,BorderLayout.CENTER);

        //PLAYER 2
        score2 = new JLabel("Score: "+players.get(1).getScore());
        createlabel(score2);

        player2 = new JPanel();
        player2.setOpaque(false);
        player2.setLayout(new BoxLayout(player2, BoxLayout.Y_AXIS));
        JLabel nickname2 = new JLabel(players.get(1).getNickname());
        createlabel(nickname2);

        player2.add(nickname2,BorderLayout.CENTER);
        player2.add(Box.createVerticalStrut(5));
        player2.add(score2,BorderLayout.CENTER);

        handleButtons(buttons);
        handleButtons2(buttons2);


        for (JButton b: buttons) {
            player1.add(Box.createVerticalStrut(20));
            b.setBackground(new Color(204,229,255));
            b.setForeground(new Color(117,95,188));
            b.setFont(new Font("Serif", Font.PLAIN, 18));
            b.setAlignmentY(Component.CENTER_ALIGNMENT);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            player1.add(b,BorderLayout.CENTER);
        }


        for (JButton b: buttons2) {
            player2.add(Box.createVerticalStrut(20));
            b.setBackground(new Color(204,229,255));
            b.setForeground(new Color(117,95,188));
            b.setFont(new Font("Serif", Font.PLAIN, 18));
            b.setAlignmentY(Component.CENTER_ALIGNMENT);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            player2.add(b,BorderLayout.CENTER);
        }

        for (JButton b:buttons) {
            b.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
                index = buttons.indexOf(e.getSource());
                for (JButton b:buttons) {
                    b.setEnabled(false);
                }
                if (round.current_round.equals("Timer")){
                    players.get(0).increasement = (int) ((timer_count)*0.2);
                }
                pressed1 = true;
                if (pressed2) {
                    if (round.current_round.equals("Timer")){
                        timer.stop();
                    }
                    first_pressed = players.get(1);
                    handleAnswers(q);
                    createOkButton();
                }
            }
            });

        }

        for (JButton b:buttons2) {
            b.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
                index2 = buttons2.indexOf(e.getSource());
                for (JButton b:buttons2) {
                    b.setEnabled(false);
                }
                if (round.current_round.equals("Timer")){
                    players.get(1).increasement = (int) ((timer_count)*0.2);
                }
                pressed2 = true;
                if (pressed1) {
                    if (round.current_round.equals("Timer")){
                        timer.stop();
                    }
                    first_pressed = players.get(0);
                    handleAnswers(q);
                    createOkButton();
                }
            }
            });

        }


        QuestionsPanel.setLayout(new GridLayout(1,2));
        QuestionsPanel.add(player1);
        QuestionsPanel.add(player2);

        panel.add(Box.createVerticalStrut(15));
        panel.add(QuestionsPanel,BorderLayout.CENTER);

        panel.repaint();
        frame.getContentPane().add(panel);
        frame.validate();

    }

    /**
     * This method adds certain characteristics to a label such as font, background and
     * foreground colors, alignments, opaque.
     * @param label the JLabel to edit.
     * @author Glykeria Fountoukidou
     */
    public void createlabel(JLabel label){
        label.setFont(new Font("Serif", Font.PLAIN, 22));
        label.setBackground(new Color(255,204,229));
        label.setForeground(new Color(51,51,255));
        label.setOpaque(true);
        label.setAlignmentY(Component.CENTER_ALIGNMENT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * This method creates some instances of KeyBinding to assign certain keys to
     * the first set of buttons (for Player 1).
     * Each button clicks on the questions using the top-down 1,2,3,4 order.
     * The mouse can also be used for clicking on questions, something that might be useful for future implementations.
     * @param buttons the list of buttons to assign the keys to.
     * @author Glykeria Fountoukidou
     * @author Anna Papadopoulou
     */
    public void handleButtons (ArrayList<JButton> buttons)
    {   KeyBinding keyBinding = new KeyBinding(buttons.get(0),"1");
        keyBinding = new KeyBinding(buttons.get(1),"2");
        keyBinding = new KeyBinding(buttons.get(2),"3");
        keyBinding = new KeyBinding(buttons.get(3),"4");

    }
    /**
     * This method creates some instances of KeyBinding to assign certain keys to
     * the second set of buttons (for Player 2).
     * Each button clicks on the questions using the top-down V,B,M,N order.
     * The mouse can also be used for clicking on questions, something that might be useful for future implementations.
     * @param buttons the list of buttons to assign the keys to.
     * @author Glykeria Fountoukidou
     * @author Anna Papadopoulou
     */
    public void handleButtons2(ArrayList<JButton> buttons)
    {
        KeyBinding keyBinding = new KeyBinding(buttons.get(0),"V");
        keyBinding = new KeyBinding(buttons.get(1),"B");
        keyBinding = new KeyBinding(buttons.get(2),"N");
        keyBinding = new KeyBinding(buttons.get(3),"M");
    }

    /**
     * A method that utilizes a counter for the Thermometer type round. Each time a player answers correctly,
     * the count is increased by one.
     * @param input1 first player's input.
     * @param input2 second player's input.
     * @param q symbolizes the current question.
     * @author Glykeria Fountoukidou
     */
    public void Thermometer(int input1, int input2, Question q){
        if (q.checkCorrectAnswer(input1)){
            correct_answers1++;
        }
        if (q.checkCorrectAnswer(input2))
            correct_answers2++;
    }

    /**
     * A method that checks which player's input was correct and first. It checks whether the first player that answered actually
     * answered correctly.
     * @param input1 first player's input.
     * @param input2 second player's input.
     * @param q symbolizes the current question.
     * @return true if the player that answered first answered correctly.
     * @author Anna Papadopoulou
     */
    public boolean QuickAnswer(int input1, int input2, Question q)
    {
        boolean first_is_correct = false;// checks if the player that answered first, is actually correct
        if (first_pressed.equals(players.get(0))) {
            if (q.checkCorrectAnswer(input1))
                first_is_correct = true;
        }
        else
        if (q.checkCorrectAnswer(input2))
            first_is_correct = true;
        return first_is_correct;
    }

    /**
     * A method that contains a special utilization of handleAnswer reserved for the Thermometer type round.
     * The index of the buttons the player's pressed are added to the Thermometer method and the handleAnswer is called for each player.
     * @param q the current question.
     * @author Glykeria Fountoukidou
     */
    public void handleAnswers(Question q){
        if (round.getCurrent_round().equals("Thermometer")) {
            Thermometer(index, index2, q);
        }
        handleAnswer(index,q,players.get(0));
        handleAnswer(index2,q,players.get(1));
    }

    /**
     * This method handles the answer of the player. If he answered correctly, his/hers score increases
     * according to the type of round and it calls the printRight() method. If his/her answer was wrong, it calls printWrong().
     * By overriding the original handleAnswers, we have added various checks for the new type of rounds in multiplayer.
     * @param userinput the player's answer as the index of the buttons array.
     * @param q the question the player answered.
     * @param p the player that is currently playing.
     * @author Glykeria Fountoukidou
     */
    @Override
    public void handleAnswer(int userinput, Question q, Player p) {
        if (q.checkCorrectAnswer(userinput)) {
            if (round.getCurrent_round().equals("Thermometer")){
                if (correct_answers1 == 5 && correct_answers2 == 5) {
                        if (p.equals(first_pressed)) {
                            p.increasement = 5000;
                        }
                        else
                            p.increasement = 0;
                }
                else if (correct_answers2 == 5){
                    players.get(0).increasement = 0;
                    players.get(1).increasement = 5000;
                }
                else if (correct_answers1 == 5){
                    players.get(1).increasement = 0;
                    players.get(0).increasement = 5000;
                }
            }
            else {
                if (round.getCurrent_round().equals("Quick Answer")) {
                        if (p.equals(first_pressed))
                            p.increasement = 1000;
                        else
                            if (QuickAnswer(index, index2, q)) //returns true
                                p.increasement = 500;                               //if player that
                            else p.increasement = 1000;                             //answered first
                    }                                                               //answered correctly.
                }
            p.increaseScore();
            if (p.equals(players.get(0))) {
                score = score1;
                printRight(player1, buttons, index, p);
            }
            else {
                score = score2;
                printRight(player2, buttons2, index2, p);
            }
        }
        else {
            if (p.equals(players.get(0)))
                printWrong(q,player1,buttons,index);
            else
                printWrong(q,player2,buttons2,index2);
        }

    }

    /**
     * This method creates an 'OK' button when both players have finished answering the question.
     * It has a Key Binding attached to the button so when one of the players presses the 'enter' key the button clicks.
     * In the ActionListener of the button, it checks if the current round questions have reached the
     * maximum number. If they did it sets finish_round as true and otherwise as false. If the round is
     * 'Thermometer' and some player has already answered 5 questions correctly we want the round to end so
     * it sets the finish_round as true. If the current round has finished, it calls ShowRound() function
     * so the next round begins. If the round hasn't finished, we increase the num (index of question's list)
     * by 1 and call ShowPanel() to display the next question and all its components. If the type of round
     * is 'Gamble' instead of ShowPanel(), it calls ShowGamblePanel().
     * @author Glykeria Fountoukidou
     */
    public void createOkButton(){
        JButton next = new JButton("OK");
        next.setAlignmentX(Component.CENTER_ALIGNMENT);
        next.setFont(new Font("Serif", Font.PLAIN, 25));
        next.setBackground(new Color(204,229,255));
        next.setForeground(new Color(117,95,188));
        panel.add(next);
        panel.add(Box.createVerticalStrut(50));


        next.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0), "enter");
        next.getActionMap().put("enter", new AbstractAction() {
            public void actionPerformed(ActionEvent event) {
               next.doClick();
            }
        });

        next.addActionListener(evt -> {
            if (current_round_questions == MAX_ROUND_QUESTIONS) //IF ALL ROUND QUESTIONS ARE FINISHED
                finish_round = true;              //set finish_round as true so the next round begins
            else
                finish_round = false;
            pressed1 = pressed2 = false;
            buttons.clear();
            buttons2.clear();
            frame.getContentPane().remove(panel);
            frame.repaint();
            //if round is thermometer, set the correct_answers counters to zero when the new round begins
            if (round.getCurrent_round().equals("Thermometer")) {
                if (correct_answers1 == 5 || correct_answers2 == 5) {
                    correct_answers1 = correct_answers2 = 0;
                    for (Player player : players)
                        player.increasement = 0;
                    finish_round = true;
                }
            }
            if (finish_round) {
                //panel.removeAll();
                panel.repaint();
                try {
                    ShowRound(); //NEXT ROUND
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {           //ELSE CONTINUE ON THE SAME ROUND
                num++; //call this function again but increase num so next question appears.
                if (round.getCurrent_round().equals("Gamble")) {
                    try {
                        showGamblePanel(questions.get(num),players.get(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                    showPanel();
            }
        });
    }

}