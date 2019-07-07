/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {
    int[] matches; //Match table for input string

    public KMP(String pattern, String text) {
        matches = new int[pattern.length()];
        matches[0] = -1;
        matches[1] = 0;
        int i =0;
        int position = 2;
        char[] textArray = text.toCharArray();
        //Set up the match table for the pattern
        while(position<pattern.length()) {
            if(textArray[position-1] == textArray[i]) {
                matches[position] = i+1;
                position++;
                i++;
            } else if(i>0){
                i = matches[i];
            } else {
                matches[position] = 0;
                position++;
            }
        }
    }

    /**
     * Perform KMP substring search on the given text with the given pattern.
     *
     * This should return the starting index of the first substring match if it
     * exists, or -1 if it doesn't.
     */
    public int search(String pattern, String text) {
        int p = 0;
        int i = 0;
        char[] patternArray = pattern.toCharArray();
        char[] textArray = text.toCharArray();
        while(i + p<text.length()) { //Search for a match in the match table
            if(patternArray[p] == textArray[p+i]) {
                p++;
                if(p==matches.length) { //If it has been found then return the location
                    return i;
                }
            } else if(matches[p]==-1){
                i = i + p + 1;
                p = 0;
            } else {
                i = i + p - matches[p];
                p = matches[p];
            }
        }
        return -1;
    }
}

