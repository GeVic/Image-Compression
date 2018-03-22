
/**
 *
 * @author veeki.yadav
 */
public class Node {

    Integer freq, value;
    Node left, right;

    /**
     * Node constructor
     */
    Node() {
        freq = null;
        value = null;
    }

    /**
     *
     * @param freq
     * @param value
     * @param left
     * @param right
     */
    public Node(Integer freq, Integer value, Node left, Node right) {
        this.freq = freq;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     *
     * @param node
     * @return
     */
    public boolean isLeaf(Node node) {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return true;
        }
        return false;
    }

}
