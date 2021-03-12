import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(1);
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Execution of JUnit for class Player done");
    }

    @Test
    void setID() {
        assertTrue(player.setID(1));
    }

    @Test
    void setID2() {
        assertTrue(player.setID(2));
    }

    @Test
    void setID3() {
        assertFalse(player.setID(4));
    }

    @Test
    void setID4() {
        assertFalse(player.setID(7));
    }

    @Test
    void getScore() {
        assertEquals(0,player.getScore());
    }

    @Test
    void increaseScore() {
        player.increasement = 1000;
        player.increaseScore();
        assertEquals(1000,player.getScore());
    }

    @Test
    void getNickname() {
        player.setNickname("Glykeria");
        assertEquals("Glykeria",player.getNickname());
    }


    @Test
    void getNickname2() {
        assertEquals("",player.getNickname());
    }
}