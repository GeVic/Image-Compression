
/**
 *
 * @author veeki.yadav
 */
public class priority_queue {

    private int capacity = 256;
    protected int size = 0;

    // Node class
    Node[] queue = new Node[capacity];

    /**
     *
     * @param parentIndex
     * @return
     */
    private int getLeftIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**
     *
     * @param parentIndex
     * @return
     */
    private int getRightIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /**
     *
     * @param childIndex
     * @return
     */
    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     *
     * @param index
     * @return
     */
    private boolean hasLeftChild(int index) {
        return getLeftIndex(index) < size;
    }

    /**
     *
     * @param index
     * @return
     */
    private boolean hasRightChild(int index) {
        return getRightIndex(index) < size;
    }

    /**
     *
     * @param index
     * @return
     */
    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    /**
     *
     * @param index
     * @return
     */
    private Node leftChild(int index) {
        return queue[getLeftIndex(index)];
    }

    /**
     *
     * @param index
     * @return
     */
    private Node rightChild(int index) {
        return queue[getRightIndex(index)];
    }

    /**
     *
     * @param index
     * @return
     */
    private Node parent(int index) {
        return queue[getParentIndex(index)];
    }

    /**
     *
     * @param indexOne
     * @param indexTwo
     */
    private void swap(int indexOne, int indexTwo) {
        Node temp = queue[indexOne];
        queue[indexOne] = queue[indexTwo];
        queue[indexTwo] = temp;
    }

    /**
     * to get the peak
     *
     * @return peak node
     */
    public Node peak() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        return queue[0];
    }

    /**
     * to pull the element
     *
     * @return deleted node
     */
    public Node poll() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        Node elem = queue[0];
        queue[0] = queue[size - 1];
        size--;
        heapifyDown();
        return elem;
    }

    /**
     *
     * @param item
     * @param num
     * @param left
     * @param right
     */
    public void add(int item, int num, Node left, Node right) {
        queue[size] = new Node(item, num, left, right);
        size++;
        heapifyUp();
    }

    /**
     * to traverse down
     */
    public void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftIndex(index);
            if (hasRightChild(index) && rightChild(index).freq < leftChild(index).freq) {
                smallerChildIndex = getRightIndex(index);

            }
            if (queue[index].freq < queue[smallerChildIndex].freq) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }

    }

    /**
     * travers up
     */
    public void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index).freq > queue[index].freq) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    /**
     * print the tree in pre-order
     *
     * @param node
     */
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.freq + ",");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

}
