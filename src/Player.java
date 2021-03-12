/**
 * The class Player controls the player's score, nickname and ID
 * @author Anna Papadopoulou
 * @author Glykeria Fountoukidou
 */
public class Player {

    private int score;
    private String nickname;
    int ID;
    /**
     * This integer contains the amount we increase the player's score each time. It depends on the type of round the player is playing.
     */
    public int increasement;


    /**
     * The constructor of the class initializes the score, the nickname, and the increasement
     * Also, it initializes the ID of the player according to the parameter. (ID = 1 means this player is
     * Player 1, and ID = 2 means this player is Player 2.
     * @param id an integer that symbolizes the unique id of each player.
     * @author Glykeria Fountoukidou
     */
    public Player(int id){
        ID = id;
        score=0;
        increasement=0;
        nickname = "";

    }

    /**
     * This methos is a setter for the ID of the player. It checks if the parameter is a valid
     * ID ( 1 or 2 as there are maximum 2 players per game) and returns true if the ID has been set
     * successfully and false otherwise.
     * @param id represents the player's ID.
     * @return true if the ID is valid (1 or 2) false otherwise.
     * @author Glykeria Fountoukidou
     */
    public boolean setID(int id){
        if (id == 1 || id == 2) {
            this.ID = id;
            return true;
        }
        else
            return false;
    }

    /**
     * A getter that returns the player's score.
     * @return the player's score.
     * @author Anna Papadopoulou
     */
    public int getScore(){

        return score;

    }

    /**
     * A setter that sets the player's score to a number we desire. Used exclusively in testing.
     * @param score an integer used to replace the score's value with.
     * @author Anna Papadopoulou
     */
    public void setScore(int score){
        this.score=score;
    }
    

    /**
     * A method that increases the player's score according to the amount given.
     * @author Anna Papadopoulou
     */
    public void increaseScore(){

        score = score+increasement;

    }

    /**
     * This method is a setter for the player's nickname.
     * @param nickname a String that represents the player's nickname.
     * @author Anna Papadopoulou
     */
    public void setNickname(String nickname){
        this.nickname  = nickname;
    }

    /**
     * This method is a getter for the player's nickname.
     * @return a String of the player's nickname.
     * @author Anna Papadopoulou
     */
    public String getNickname(){

        return nickname;

    }



}