import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WriteToFileTest {
    WriteToFile write;
    Player player = new Player(1);
    Player player2 = new Player(2);

    @BeforeEach
    void setUp() {
        write = new WriteToFile();
    }

    @Test
    void writeToFileSingle() throws IOException {
        write.WriteToFileSingle(player);
        player.setNickname("playertest");
        player.setScore(1000);
        File singleplayer = new File("scores/singleplayer.txt");
        assertEquals(singleplayer,write.WriteToFileSingle(player));

    }

    @Test
    void writeToFileComp() throws IOException {
        File multiplayer = new File("scores/multiplayer.txt");
        player.setNickname("playertest");
        player2.setNickname("playertest2");
        player.setScore(1000);
        player2.setScore(2000);
        assertEquals(multiplayer,write.WriteToFileComp(player,player2));
    }
}