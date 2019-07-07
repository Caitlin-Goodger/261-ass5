import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
    //Tree of Nodes
    HuffmanNode tree;
    /**
     * This would be a good place to compute and store the tree.
     */
    public HuffmanCoding(String text) {
        char[] textArray = text.toCharArray();
        HashMap<Character,Integer> frequency = new HashMap<>();

        //Calculate the frequencies of each char in the text
        for(char c : textArray) {
            if(frequency.containsKey(c)) {
                frequency.replace(c,frequency.get(c)+1);
            } else{
                frequency.put(c,1);
            }
        }
        //Create a priority queue of nodes with the least frequent at the begining
        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<>((HuffmanNode::compareTo));
        for(char c : frequency.keySet()) {
            nodes.add(new HuffmanNode(c,frequency.get(c),null,null));
        }

        //Remove 2 nodes and then create a new node that joins the two of them
        while(nodes.size()>1) {
            HuffmanNode n1 = nodes.poll();
            HuffmanNode n2 = nodes.poll();

            HuffmanNode n3 = new HuffmanNode('\0',n1.frequency+n2.frequency,n1,n2);
            nodes.add(n3);
        }
        tree = nodes.poll();//The last node is then the top of the tree
//        for(char c : frequency.keySet()) {
//            System.out.println(c + " " + frequency.get(c));
//        }

    }

    /**
     * Take an input string, text, and encode it with the stored tree. Should
     * return the encoded text as a binary string, that is, a string containing
     * only 1 and 0.
     */
    public String encode(String text) {
        char[] textArray = text.toCharArray();
        HashMap<Character,String> table = buildTable(tree); //Create the table
        for(Character c : table.keySet()) {
            System.out.println(c + ": " + table.get(c));
        }
        StringBuilder encodeString = new StringBuilder();
//        For each char in the text add the value from the tree to the encodedString
        for(char c : textArray) {
            encodeString.append(table.get(c));
        }
        System.out.println("Finished Encoding");
        return encodeString.toString();
    }

    /**
     * Build Table to start the building of the table from the top of the tree
     * @param n
     * @return
     */
    public HashMap<Character,String> buildTable(HuffmanNode n) {
        HashMap<Character, String> tableValues = new HashMap<>();
        buildTable(tableValues,n,"");
        return tableValues;
    }

    /**
     * Build table to recursively go down all of the tree
     * @param tableValues
     * @param n
     * @param s
     */
    public void buildTable(HashMap<Character,String> tableValues, HuffmanNode n, String s) {
        //If the nodes is a leaf then add that character to the map with the code for it
        if(n.isLeaf()) {
            tableValues.put(n.character,s);
            return;
        }
        //Otherwise recursively call, making the string slightly longer
        buildTable(tableValues,n.left,s+'0');
        buildTable(tableValues,n.right,s+'1');
    }

    /**
     * Take encoded input as a binary string, decode it using the stored tree,
     * and return the decoded text as a text string.
     */
    public String decode(String encoded) {
        char[] textArray = encoded.toCharArray();
        StringBuilder output = new StringBuilder();
        //For the length of the string given
        for(int i =0; i<encoded.length();i++) {
            HuffmanNode n = tree;
            while(!n.isLeaf()) {
                char c = textArray[i];
                //Keep going down the tree depending on what value it is, either left or right
                if(c =='1') {
                    n = n.right;
                } else {
                    n = n.left;
                }
                i++;
            }
            //If it is a leaf then add it to the output
            output.append(n.character);
            i--;
        }
        return output.toString();
    }

    /**
     * The getInformation method is here for your convenience, you don't need to
     * fill it in if you don't wan to. It is called on every run and its return
     * value is displayed on-screen. You could use this, for example, to print
     * out the encoding tree.
     */
    public String getInformation() {
        return "";
    }
}
