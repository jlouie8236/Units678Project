import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/** This the main logic class for the Wordle game
 *
 * @author Jacqueline Louie
 */
public class WordFind
{
    /** The grid to hold the ColoredLetter values */
    private ColoredLetter[][] grid;

    /** The list of possible answers */
    private ArrayList<String> answers;

    /** The list of possible guesses */
    private ArrayList<String> guesses;

    /** The hidden word of the game in String form*/
    private String correctWordFull;

    /** The hidden word of the game split into letter in a String array */
    private String[] correctWord;

    /** A boolean to indicate the end of the game */
    private boolean end;

    /** The blank box to fill the grid in the beginning of the game */
    private static final String blank = "\u1AC5"; // Blank Box for grid

    /** The value of the blue color used to indicate a correct letter */
    private static final String BLUE = "\033[0;94m"; // Blue

    /** The value to reset the text color */
    private static final String RESET = "\033[0m";  // Text Reset

    /** Instantiates a WordFind object and all necessary values needed in the game */
    public WordFind()
    {
        answers = new ArrayList<>();
        guesses = new ArrayList<>();
        loadAnswers();
        loadGuesses();
        end = false;
        grid = new ColoredLetter[6][5];
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                ColoredLetter temp = new ColoredLetter(blank);
                grid[i][j] = temp;
            }
        }
        correctWordFull = generateAnswer();
        correctWord = splitWordToLetters(correctWordFull);

    }

    /** Starts the game. */
    public void startGame()
    {
        Scanner scan = new Scanner(System.in);
        int guessNum = 0;
        introText();
        while (!end)
        {
            System.out.println(RESET + "What's your guess?");
            String guess = scan.nextLine();
            if (checkWord(guess))
            {
                String[] guessList = splitWordToLetters(guess.toLowerCase());
                loadWord(guessList, guessNum);
                checkAndSet(guessList, guessNum);
                printGrid();
                guessNum++;
                if (allBlue(guessNum))
                {
                    System.out.println("You win!");
                    end = true;
                }
                else if (guessNum > 5)
                {
                    System.out.println("No more guesses. Game over!");
                    System.out.println("The word was: " + correctWordFull);
                    end = true;
                }
            }
            else
            {
                System.out.println("That word isn't in our dictionary. Try again");
            }
        }
    }

    /** Prints the grid out */
    public void printGrid()
    {
        for (ColoredLetter[] row : grid)
        {
            for (ColoredLetter col : row)
            {
                System.out.print(col.getColor() + col.getLetter() + " ");
            }
            System.out.println();
        }
        System.out.print(RESET + "");
    }

    /** Prints the introduction to the Word game */
    private void introText()
    {
        System.out.println("Hi! Welcome to the WordFind Game!");
        System.out.println("You have 6 tries to guess the word");
        System.out.println("When you guess the word, colors will give you hints: ");
        System.out.println("If the letter is red, the letter is not in the word");
        System.out.println("If the letter is yellow, the letter is in the word, but not in the right place");
        System.out.println("If the letter is blue, the letter is in the right place");
        System.out.println("Let's play!");
    }

    /** Checks if the given word is in the allowed answers or guesses ArrayLists
     *
     * @param word the word to check
     * @return a boolean value indicating if the word is in the lists or not
     */
    private boolean checkWord(String word) {
        boolean isValid = false;
        for (String value : guesses)
        {
            if (value.equals(word))
            {
                isValid = true;
            }
        }
        for (String value : answers)
        {
            if (value.equals(word))
            {
                isValid = true;
            }
        }
        return isValid;
    }

    /** Loads the given String array into the given index row number in grid variable
     *
     * @param splitGuess the String array to load
     * @param guessNum the row to load the array into
     */
    private void loadWord(String[] splitGuess, int guessNum)
    {
        for (int i = 0; i < 5; i++)
        {
            grid[guessNum][i].setLetter(splitGuess[i]);
        }
    }

    /** Checks the String array given compared to the hidden answer string, and uses those results to set the colors of the ColoredLetter objects in grid
     *
     * @param splitGuess The String array that contains each letter of a guessed word
     * @param guessNum The index of the row that contains the ColoredLetter objects that will have their colors changed
     */
    private void checkAndSet(String[] splitGuess, int guessNum)
    {
        for (int i = 0; i < correctWord.length; i++)
        {
            String letterGuessed = splitGuess[i];
            String correctLetter = correctWord[i];

            if (letterGuessed.equals(correctLetter))
            {
                grid[guessNum][i].setColor(true, true);
            }
            else if (correctWordFull.indexOf(letterGuessed) != -1)
            {
                grid[guessNum][i].setColor(true, false);
            }
            else
            {
                grid[guessNum][i].setColor(false, false);
            }
        }
    }

    /** Returns whether all the values in a selected row are all blue.
     *
     * @param guess the index of the row to check
     * @return a boolean indicating if all the values in a selected row are blue
     */
    private boolean allBlue(int guess) {
        int countBlue = 0;
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[guess - 1][i].getColor().equals(BLUE))
            {
                countBlue++;
            }
        }
        if (countBlue == 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /** Splits the given word into a String array of each letter in the word
     *
     * @param word the word to split into letters
     * @return the String array of letters in the word
     */
    private String[] splitWordToLetters(String word)
    {
        return word.split("");
    }

    /** Generates a random word from the answers ArrayList
     *
     * @return a random word from answers ArrayList
     */
    private String generateAnswer()
    {
        int randomIndex = (int) (Math.random() * answers.size());
        return answers.get(randomIndex);
    }

    /** Loads all the String values in the answers txt file into an ArrayList */
    private void loadAnswers()
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

    /** Loads all the String values in the guesses txt file into an ArrayList */
    private void loadGuesses()
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
