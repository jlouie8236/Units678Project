import java.util.Arrays;

public class Runner {
    public static void main(String[] args)
    {
        Wordle wordle = new Wordle();

        /*
        Things I need for this project:
        xml file of 5-letter words
        a method to split the words into individual letters
        a method to check if the words are actual words
        a method to check if the letters are in the word
        a way to check if the right letters are in the right place
        a way to randomly choose a letter from list
        the list of words guessed?

         */

        String testWord = "apple";
        String[] testLetters = testWord.split("");
        for (int i = 0; i < testLetters.length; i++)
        {
            System.out.print(testLetters[i] + " ");
        }
    }
}
