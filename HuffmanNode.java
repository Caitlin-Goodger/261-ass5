/**
 * Node for the Huffman Coding encoding
 */
public class HuffmanNode {
    //Each node has a char, frequency and node to the left and right
    char character;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char c, int f, HuffmanNode l, HuffmanNode r) {
        character = c;
        frequency =f;
        left = l;
        right = r;
    }

    /**
     * If a node is a leaf then the left and right are null as there is not more tree
     * @return
     */
    public boolean isLeaf() {
        if(left == null && right == null) {
            return true;
        }
        return false;
    }

    /**
     * Compare frequencies to see which is more frequent
     * @param o
     * @return
     */
    public int compareTo(HuffmanNode o) {
        return frequency-o.frequency;
    }

}
