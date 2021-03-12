import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * This class is responsible for reading the file with the questions of the game.
 * @author Glykeria Fountoukidou
 */
public class FileParser {

    ArrayList<Question> questions;
    File file;
    Scanner scanner;

    /**
     * The constructor creates the new file with the path that is given as a parameter. Creates a new scanner
     * so it can read the file. Initializes the questions Arraylist, and calls the readFile function.
     * @param path a String that represents the path of the questions file.
     * @author Glykeria Fountoukidou
     */
    public FileParser(String path) {
        try{
            file = new File(path);
            scanner = new Scanner(file);
            questions = new ArrayList<>();
            readFile();
        }
        catch (FileNotFoundException e){
            System.out.println("An error occured. Could not find file");
            e.getStackTrace();
        }
    }

    /**
     * This method reads each line of the questions file and saves the data in the according variable.
     * When it finishes reading a whole question's data, it creates a Question or ImageQuestion class item
     * and adds it to the questions Arraylist. the variable question_num represents the ID of the questions
     * and is user for ImageQuestions only. Questions number 31 to 50 are ImageQuestion type.
     * @author Glykeria Fountoukidou
     */
    public void readFile() {
        int question_num = 1;
        Question q;
        while (scanner.hasNextLine()) {
            String[] answers = new String[4];
            String question = scanner.nextLine();
            for (int i = 0; i < 4; i++) {
                answers[i] = scanner.nextLine();
            }
            String correct_answer = scanner.nextLine();
            String category = scanner.nextLine();
            if (question_num < 31)
                q = new Question(question, answers, correct_answer, category);
            else
                q = new ImageQuestion(question, answers, correct_answer, category, question_num);
            questions.add(q);
            question_num++;
        }
    }

    /**
     * This method returns the ArrayList of all the Question items created from reading the file.
     * @return an array list of all the questions.
     * @author Glykeria Fountoukidou
     */
    public ArrayList<Question> getQuestions(){
        return questions;
    }

}
