import java.util.ArrayList;

/**
 * A new instance of LempelZiv is created for every run.
 */
public class LempelZiv {
    //Random large viewRange value
    int viewRange = 100000;
    /**
     * Take uncompressed input as a text string, compress it, and return it as a
     * text string.
     */
    public String compress(String input) {
        char[] textArray = input.toCharArray();
        int cursor = 0;
        int lookAhead = 0;
        ArrayList<Tuple> textComp = new ArrayList<>();
        //Read through the input string
        while(cursor<input.length()) {
            //Find how much to look ahead
            if(cursor + lookAhead >= input.length()) {
                lookAhead = input.length();
            } else {
                lookAhead = cursor + lookAhead;
            }
            int prevMatch;
            //Find the prevMatch
            if(cursor-viewRange < 0) {
                prevMatch =0;
            } else {
                prevMatch = cursor-viewRange;
            }
            int matchLength = 0;
            int matchLoc = 0;
            String s;
            if(cursor ==0 ) {
                s = "";
            } else {
                s = input.substring(prevMatch,cursor);
            }
            String next = input.substring(cursor, cursor + matchLength);
            if(s.contains(next)) {
                matchLength++;
                while(matchLength<=lookAhead) {
                    if( cursor + matchLength >= input.length()-1) {
                        matchLength = 1;
                        break;
                    }
                    next = input.substring(cursor, cursor + matchLength);
                    matchLoc = s.indexOf(next);
                    if(cursor + matchLength < input.length() && matchLoc>-1) {
                        matchLength++;
                    } else {
                        break;
                    }
                }
                matchLength--;
                matchLoc = s.indexOf(input.substring(cursor, cursor + matchLength));
                cursor += matchLength;
                char c = textArray[cursor];

                int offset = viewRange-matchLoc;
                if(viewRange+matchLength>=cursor) {
                    offset = cursor - matchLoc - matchLength;
                }
                //Create a new tuple with values found
                textComp.add(new Tuple(offset,matchLength,c));
            } else {
                char c = textArray[cursor];
                //Create a new tuple
                textComp.add(new Tuple(0,0,c));
            }
            cursor++;
        }
        StringBuilder comp = new StringBuilder();
        for(int i = 0; i<textComp.size();i++) {
            comp.append(textComp.get(i).toString());
        }
        //System.out.println(comp);
        return comp.toString();
    }

    /**
     * Take compressed input as a text string, decompress it, and return it as a
     * text string.
     */
    public String decompress(String compressed) {
        ArrayList<Tuple> comp = new ArrayList<>();
        char[] textArray = compressed.toCharArray();
        int i =0;
        //Read the string given
        while(i<textArray.length) {
            //Begining of a new tuple
            if(textArray[i] == '[') {
                i++;
                //First value is the offset
                String offset = "";
                while(textArray[i] != ',') {
                    offset = offset + textArray[i];
                    i++;
                }
                i++;
                //Second value is the length
                String length = "";
                while(textArray[i] != ',') {
                    length = length + textArray[i];
                    i++;
                }
                i++;
                //Third value is the character
                char c = textArray[i];
                //Create a new tuple with the information read
                comp.add(new Tuple(Integer.parseInt(offset),Integer.parseInt(length),c));
            }
            i++;
        }
       StringBuilder output = new StringBuilder();
       // Check every tuple
        for (Tuple t : comp) {
            if (t.length == 0) {
                //Add character to string
                output.append(t.character);
            } else {
                for (int j = 0; j < t.length; j++) {
                    //Add it as many times as needed
                    output.append(output.charAt(output.length() - t.offset));
                }
                output.append(t.character);
            }
       }
        return output.toString();
    }

    /**
     * The getInformation method is here for your convenience, you don't need to
     * fill it in if you don't want to. It is called on every run and its return
     * value is displayed on-screen. You can use this to print out any relevant
     * information from your compression.
     */
    public String getInformation() {
        return "";
    }
}
