/** This class represents a letter with a color assigned to it.
 *
 * @author Jacqueline Louie
 */
public class ColoredLetter
{
    /** The letter in ColoredLetter */
    private String letter;

    /** The color in ColoredLetter */
    private String color;

    /** The color white */
    private static final String WHITE_BRIGHT = "\033[0;97m";

    /** The color red */
    private static final String RED_BRIGHT = "\033[0;91m";

    /** The color yellow */
    private static final String YELLOW_BRIGHT = "\033[0;93m";

    /** The color blue */
    private static final String BLUE_BRIGHT = "\033[0;94m";

    /** Instantiates a ColoredLetter object and sets color variable to a default color
     *
     * @param letter The letter or String
     */
    public ColoredLetter(String letter)
    {
        this.letter = letter;
        color = WHITE_BRIGHT;
    }

    /** Returns the letter
     *
     * @return the letter variable of ColoredLetter
     */
    public String getLetter()
    {
        return letter;
    }

    /** Returns the color
     *
     * @return the color variable of ColoredLetter
     */
    public String getColor()
    {
        return color;
    }

    /** Sets the letter of the ColoredLetter to a new value
     *
     * @param newLetter the new value of the letter variable
     */
    public void setLetter(String newLetter)
    {
        letter = newLetter;
    }

    /** Sets the color of the ColoredLetter to a new Color
     *
     * @param inWord whether the letter indicated is in the word
     * @param correctPlace whether the letter indicated is the right location
     */
    public void setColor(boolean inWord, boolean correctPlace)
    {
        if(inWord == false && correctPlace == false)
        {
            color = RED_BRIGHT;
        }
        else if (inWord == true && correctPlace == false)
        {
            color = YELLOW_BRIGHT;
        }
        else if (inWord == true && correctPlace)
        {
            color = BLUE_BRIGHT;
        }
    }
}
