/**
 * Class for Brute Force Search algorithm
 */
public class BruteForce {

    /**
     * Brute force search.
     * Compares char one at a time
     * @param pattern
     * @param text
     * @return
     */
    public int search(String pattern, String text) {
        int pLength = pattern.length();
        int tLength = text.length();
        char[] patternArray = pattern.toCharArray(); //Convert string to an array of chars
        char[] textArray = text.toCharArray();
        for(int i = 0;i<tLength-pLength;i++) {
            if(patternArray[0] == textArray[i]) { //If they match
                boolean found = true;
                for(int j = 0; j<pLength;j++) {
                    if(patternArray[j] != textArray[i+j]) { //Keep check until one doesn't match
                        found = false;
                        break;
                    }
                }
                if(found) { //If found return the location of it
                    return i;
                }
            }
        }
        return -1;
    }
}
