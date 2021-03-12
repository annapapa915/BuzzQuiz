import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    Question question;
    String[] answers = {"1","2","3","4"};
    String correct_answer;
    @BeforeEach
    void setUp() {
        correct_answer = "4";
        question = new Question("How much is 2+2?",answers,correct_answer,"cinema");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Execution of JUnit for class Question done");
    }

    @Test
    void getQuestion() {
        assertEquals("How much is 2+2?",question.getQuestion());
    }


    @Test
    void getCategory() {
        assertEquals("cinema",question.getCategory());
    }

    @Test
    void getOptions() {
        assertEquals(answers,question.getOptions());
    }

    @Test
    void checkCorrectAnswer2() {
        assertFalse(question.checkCorrectAnswer(0));
    }

    @Test
    void checkCorrectAnswer3() {
        assertFalse(question.checkCorrectAnswer(1));
    }

    @Test
    void checkCorrectAnswer4() {
        assertFalse(question.checkCorrectAnswer(2));
    }

    @Test
    void checkCorrectAnswer() {
        assertTrue(question.checkCorrectAnswer(3));
    }

    @Test
    void checkCorrectAnswer5() {
        assertFalse(question.checkCorrectAnswer(10));
    }


}