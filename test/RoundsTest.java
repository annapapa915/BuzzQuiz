import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoundsTest {
    Rounds rounds;
    Player player;
    @BeforeEach
    void setUp() {
        player = new Player(1);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        rounds = new Rounds(players);
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Execution of JUnit for class Rounds done");
    }

    @Test
    void getCurrent_round() {
        rounds.ChooseRound();
        String current_round = rounds.getCurrent_round();
        assertEquals(current_round, rounds.getCurrent_round());
    }

    @Test
    void rightAnswer() {
        rounds.RightAnswer();
       assertEquals(1000, player.increasement);
    }
}