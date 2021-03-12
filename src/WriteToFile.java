import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * The class responsible for keeping the scores in text files for single player and competitive mode.
 * @author Anna Papadopoulou
 */
public class WriteToFile {

    /**
     * This method is used in single player mode. After the game has finished, it writes the player's username and
     * score in a new line in the singleplayer text. Each username and score is separated by the symbol :.
     * @param p a Player type object, corresponds to the player in single player mode.
     * @throws IOException if the file we're trying to read doesn't exist.
     * @return the single player text file with the new scores we wrote.
     * @author Anna Papadopoulou
     */
    public File WriteToFileSingle(Player p) throws IOException {
        File singleplayer = new File("scores/singleplayer.txt");

        Writer w = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(singleplayer, true), StandardCharsets.UTF_8));

        w.write(p.getNickname());
        w.write(":");
        w.write(String.valueOf(p.getScore()));
        w.write("\n");
        w.close();
        return singleplayer;
    }

    /**
     * This method is used in competitive mode. After the game has finished, it writes each player's username and
     * score, separates them by a comma and declares which player won or whether there was a tie.
     * Each username and score is separated by the symbol :, and all this information about a game is written on a new line.
     * @param p a Player type object, corresponds to the first player in competitive mode.
     * @param p2 a Player type object, corresponds to the second player in competitive mode.
     * @return the multiplayer text file with the new game information we wrote.
     * @throws IOException if the file we're trying to read doesn't exist.
     * @author Anna Papadopoulou
     */
    public File WriteToFileComp(Player p,Player p2) throws IOException {
        File multiplayer = new File("scores/multiplayer.txt");

        Writer w = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(multiplayer, true), StandardCharsets.UTF_8));

        w.write(p.getNickname());
        w.write(": ");
        w.write(String.valueOf(p.getScore()));
        w.write(", ");

        w.write(p2.getNickname());
        w.write(": ");
        w.write(String.valueOf(p2.getScore()));
        w.write(", ");

        if (p.getScore()>p2.getScore())
        {
            w.write(p.getNickname()+" is the winner!");
        }
        else if (p.getScore()==p2.getScore())
        {
            w.write("Tie!");
        }
        else
        {
            w.write(p2.getNickname()+" is the winner!");
        }
        w.write("\n");


        w.close();

        return multiplayer;
    }



}
