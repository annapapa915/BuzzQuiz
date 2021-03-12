import java.util.*;
/**
 * The class Question contains methods and attributes of the quiz game's questions.
 * @author Anna Papadopoulou
 * @author Glykeria Fountoukidou
 */
public class Question {

    String question;
    String right_answer;
    String[] options;
    String category;
    Integer right_num;

    /**
     * The constructor of the class Question initializes all the variables, according to its parameters.
     * Also, we find the index of the answers array in which the correct answer is, and save it to right_num.
     * @param question represents the question.
     * @param answers represents the answers of the question.
     * @param right_answer represents the correct answer among all the possible answers.
     * @param category represents the category of the question.
     * @author Glykeria Fountoukidou
     */
    public Question(String question, String[] answers, String right_answer, String category){
        this.question = question;
        this.right_answer = right_answer;
        this.category = category;
        this.options = answers;
        List<String> ans = Arrays.asList(options);
        ans.toArray(options); //create a list, and then save them again on the answers array.
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(right_answer))
                right_num = i; //save the right answer as a letter of the alphabet.
        }
    }


    /**
     * A getter that returns a question.
     * @return a question.
     * @author Anna Papadopoulou
     */
    public String getQuestion(){

        return question;

    }


    /**
     * A getter that returns a question's category.
     * @return the category of a question.
     * @author Glykeria Fountoukidou
     */
    public String getCategory(){

        return category;

    }

    /**
     * This method returns the array of the question's answers.
     * @return the question's possible answers.
     * @author Glykeria Fountoukidou
     */
    public String[] getOptions(){
        return options;
    }


    /**
     * A method that checks if the answer given by the player is the correct answer.
     * @param user_answer represents the answer (index) that the user has pressed out of all the other options.
     * @return true if the player chose the correct answer, false otherwise.
     * @author Glykeria Fountoukidou
     */
    public boolean checkCorrectAnswer(Integer user_answer){
        return user_answer.equals(right_num);
    }

    /**
     * A method that shuffles the answers array, so they appear at random order.
     * Also, it finds the index of the answers array in which the correct answer is, and saves it to right_num
     * because now that they are at randomized order, the index of the correct answer might have changed.
     * @author Glykeria Fountoukidou
     */
    public void ShuffleAnswers(){
        List<String> ans = Arrays.asList(options);
        Collections.shuffle(ans);
        ans.toArray(options); //create a list, shuffle the answers, and then save them again on the answers array.
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(right_answer))
                right_num = i; //save the right answer as a letter of the alphabet.
        }

    }





}