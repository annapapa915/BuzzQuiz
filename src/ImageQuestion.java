/**
 * The class ImageQuestion extends the class Question and represents a question that is
 * associated with a picture. The difference with Question class is that it has an ID, which
 * helps in getting the name of the picture file and use it in the GUI frames.
 * @author Glykeria Fountoukidou
 */
public class ImageQuestion extends Question{
    private Integer question_ID;
    /**
     * The constructor of the class Question initializes all the variables, according to its parameters.
     * @param question represents the question.
     * @param answers represents the answers of the question.
     * @param right_answer represents the correct answer among all the possible answers.
     * @param category represents the category of the question.
     * @param q_num represents the ID of the question.
     * @author Glykeria Fountoukidou
     */
    public ImageQuestion(String question, String[] answers, String right_answer, String category, int q_num) {
        super(question, answers, right_answer, category);
        question_ID = q_num;
    }

    /**
     * This method returns a String, which represents the file name of the picture associated with
     * the question. First, it checks if the ID is within the correct bounds (only questions 31 to 50
     * are associated with an image, the rest are regular questions). Then it creates the String of the
     * file name and return it.
     * @return a String, which represents the file name of the picture.
     * @author Glykeria Fountoukidou
     */
    public String getImage(){
        if (question_ID >=31 && question_ID <= 50) {
            String image_name = "Question" + question_ID.toString() + ".jpg";
            return image_name;
        }
        else
            return null;
    }
}
