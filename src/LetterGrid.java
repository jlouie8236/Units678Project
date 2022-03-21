public class LetterGrid {

    private String[][] wordGrid;
    //colors
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN

    public LetterGrid()
    {
        wordGrid = new String[5][6];
    }

}
