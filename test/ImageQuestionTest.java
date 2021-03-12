import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageQuestionTest {
    String[] answers = {"Tom Cruise", "Johnny Depp","Tom Holland","Robert Downey Jr"};
    ImageQuestion question;
    @BeforeEach
    void setUp() {
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Execution of Unit for class ImageQuestion done");
    }

    @Test
    void getImage() {
        question = new ImageQuestion("Who is this actor?",answers,"Robert Downey Jr",
                "cinema",26);
        assertEquals("Question26.jpg",question.getImage());
    }

    @Test
    void getImage2() {
        question = new ImageQuestion("Who is this actor?",answers,"Robert Downey Jr",
                "cinema",35);
        assertEquals("Question35.jpg",question.getImage());
    }

    @Test
    void getImage3() {
        question = new ImageQuestion("Who is this actor?",answers,"Robert Downey Jr",
                "cinema",50);
        assertNull(question.getImage());
    }

    @Test
    void getImage4() {
        question = new ImageQuestion("Who is this actor?",answers,"Robert Downey Jr",
                "cinema",80);
        assertNull(question.getImage());
    }

}