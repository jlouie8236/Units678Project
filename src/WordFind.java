import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class WordFind {
    private String[] dictionary;
    private ArrayList<String> answers;
    private ArrayList<String> guesses;
    //colors
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN

    public WordFind()
    {

    }

    // to get the "dictionary"
    public void loadAnswers()
    {
        try {
            Scanner input = new Scanner(new File("src/answers.txt"));
            while (input.hasNext())
            {
                String word = input.next();
                answers.add(word);
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    public void loadGuesses()
    {
        try {
            Scanner input = new Scanner(new File("src/guesses.txt"));
            while(input.hasNext())
            {
                String word = input.next();
                guesses.add(word);
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }
}
