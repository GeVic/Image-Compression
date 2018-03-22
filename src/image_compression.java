
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Veeki.yadav
 */
public class image_compression {

    static int[] freq2 = new int[256];

    /**
     *
     * @param freq
     */
    public static void lossy(int[] freq) {
        int n = 9;
        for (int i = 0; i < freq.length; i += 10) {
            int max = 0;
            if (i == 240) {
                n = 255;
            }
            for (int k = i; k <= n; k++) {
                if (freq[k] > max) {
                    max = freq[k];
                }
            }
            makenew(ArrayUtils.indexOf(freq, max), freq, i);
            if (n == 255) {
                i += 20;
            }
            n += 10;
        }

    }

    /**
     *
     * @param maxvalueIndex
     * @param freq
     * @param start
     */
    public static void makenew(int maxvalueIndex, int[] freq, int start) {
        int sum = 0;
        int end;

        if (start == 240) {
            end = start + 16;
        }
        end = start + 10;
        // to get the sum
        for (int i = start; i < end; i++) {
            sum += freq[i];
        }
        // create new array
        for (int i = start; i < end; i++) {
            if (i != maxvalueIndex) {
                freq2[i] = 0;
            } else {
                freq2[i] = sum;
            }
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // class object
        GrayScale gs = new GrayScale();
        priority_queue pq = new priority_queue();
        huffman_code hc = new huffman_code();
        priority_queue pq2 = new priority_queue();
        huffman_code hc2 = new huffman_code();

        // to add the frequency elements to the priority queue
        int[] freq = gs.freq;
        for (int i = 0; i < freq.length; i++) {
            pq.add(freq[i], i, null, null);
        }

        // for lossy compression
        lossy(freq);

        // to add the frequency elements to the priority queue
        for (int i = 0; i < freq2.length; i++) {
            pq2.add(freq2[i], i, null, null);
        }

        // to build the huffman tree
        Node root = hc.huffmanTree(freq, pq);
        Node root2 = hc2.huffmanTree(freq2, pq2);

        // to get the bit code
        hc.bitcode(root, "");
        hc2.bitcode(root2, "");

        // initial bit used
        double initbit = gs.width * gs.height * 8;

        // sum the compressed bit length and bit code 
        int sum = 0;
        for (int i : hc.str) {
            sum += i;
        }
        // for lossy compression
        int sum2 = 0;
        for (int i : hc2.str) {
            sum2 += i;
        }

        // to print the compression ratio
        double ratio = initbit / sum;
        double roundOff = (double) Math.round(ratio * 100) / 100;
        System.out.println("Compression ratio for part 1 : " + roundOff);

        double ratio2 = initbit / sum2;
        double roundOff2 = (double) Math.round(ratio2 * 100) / 100;
        System.out.println("Compression ratio for part 2 : " + roundOff2);

    }
}
