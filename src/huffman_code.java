
import java.util.ArrayList;

/**
 *
 * @author veeki.yadav
 */
public class huffman_code {

    Node t = new Node();
    public ArrayList<Integer> str = new ArrayList<>();

    /**
     * build a huffman tree
     *
     * @param freq
     * @return
     */
    public Node huffmanTree(int[] freq, priority_queue pq) {
        // merging two small tree 
        for (int i = 0; i < freq.length - 1; i++) {
            Node left = pq.poll();
            //System.out.println("Node 1 removes : " + left.freq );
            Node right = pq.poll();
            //System.out.println("Node 2 removes : " + right.freq );
            pq.add(left.freq + right.freq, 0, left, right);
        }
        return pq.poll();
    }

    /**
     *
     * @param node
     * @param s
     */
    public void bitcode(Node node, String s) {
        if (node != null) {
            if (!t.isLeaf(node)) {
                if (node.left != null) {
                    bitcode(node.left, s + "0");
                }
                if (node.right != null) {
                    bitcode(node.right, s + "1");
                }

            } else {
                if (node.freq != 0) {
                    str.add(node.freq * s.length());
                } else {
                    str.add(s.length());
                }
            }
        }
    }
}
