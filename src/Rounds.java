import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The class Rounds represents the type of rounds that we have implemented in our game.
 * @author Anna Papadopoulou
 * @author Glykeria Fountoukidou
 */
public class Rounds {
    ArrayList<Player> players;
    ArrayList<String> types;
    ArrayList<String> typescomp;
    String current_round;

    /**
     * The constructor of the class, initialises the variables, adds the different types of rounds in
     * the Arraylists, for singleplayer and multiplayer.
     * @param players the list of the players of the game.
     * @author Glykeria Fountoukidou
     */
    public Rounds(ArrayList<Player> players){
        this.players = players;
        types = new ArrayList<>();
        types.add("Right Answer");
        types.add("Gamble");
        types.add("Timer");

        typescomp = new ArrayList<>();
        typescomp.add("Right Answer");
        typescomp.add("Gamble");
        typescomp.add("Timer");
        typescomp.add("Quick Answer");
        typescomp.add("Thermometer");
    }

    /**
     * This method chooses a random round from the types Arraylist for the singleplayer mode
     * using ThreadLocalRandom. It generates a random integer within a range,
     * and then it sets the value of that random index of the types list, as the type of the current round.
     * If the current round turns out to be 'Right Answer' it calss RightAnswer() method to set the players'
     * score increasements.
     * @author Glykeria Fountoukidou
     */
    public void ChooseRound(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, types.size());
        current_round = types.get(randomNum);
        if (current_round.equals("Right Answer")){
            RightAnswer();
        }
    }

    /**
     * This method chooses a random round from the types Arraylist for the multiplayer mode
     * using ThreadLocalRandom. It generates a random integer within a range,
     * and then it sets the value of that random index of the types list, as the type of the current round.
     * If the current round turns out to be 'Right Answer' it calss RightAnswer() method to set the players'
     * score increasements.
     * @author Glykeria Fountoukidou
     */
    public void ChooseRoundComp(){
        for(Player player : players)
            player.increasement = 0; //set increasements back to 0
        int randomNum = ThreadLocalRandom.current().nextInt(0, typescomp.size());
        current_round = typescomp.get(randomNum);
        if (current_round.equals("Right Answer")){
            RightAnswer();
        }
    }

    /**
     * This method is a getter for the current_round variable.
     * @return the current type of round
     * @author Glykeria Fountoukidou
     */
    public String getCurrent_round(){
        return current_round;
    }

    /**
     * A method that carries out the Right Answer type round. The player's score is increased by 1000 each time his
     * answer is correct.
     * @author Anna Papadopoulou
     */
    public void RightAnswer(){

        for(Player player : players)
            player.increasement = 1000;
    }



}


