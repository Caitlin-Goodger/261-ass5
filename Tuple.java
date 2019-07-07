/**
 * Tuple class for the LempelZip compression
 */
public class Tuple {
    int offset;
    int length;
    char character;

    /**
     * Each Tuple has an offset, length and character
     * @param o
     * @param l
     * @param c
     */
    public Tuple(int o, int l, char c) {
        offset = o;
        length = l;
        character = c;
    }

    @Override
    public String toString() {
        return "[" + offset +","+ length +","+ character + "]";
    }
}
