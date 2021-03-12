import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {
    FileParser f;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getQuestions() {
        f = new FileParser("Question.txt");
        assertNull(f.getQuestions());
    }

    @Test
    void getQuestions2() {
        f = new FileParser("questions.java");
        assertNull(f.getQuestions());
    }

    @Test
    void getQuestions3() {
        f = new FileParser("Questions");
        assertNull(f.getQuestions());
    }
}