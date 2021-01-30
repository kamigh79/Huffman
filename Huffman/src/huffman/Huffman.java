package huffman;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {

    private static Map<Character, String> charPrefixHashMap = new HashMap<>();
    static HuffmanNode root;

    public static void main(String[] args) throws IOException {

        ReadTxt rt = new ReadTxt();

        String test = rt.test();

        Scanner input = new Scanner(System.in);
        int x = 0;

        while (true) {
            
            
            

            System.out.print("\nEnter 1 for encode"
                    + "\n      2 for decode"
                    + "\n      3 for print tree"
                    + "\n      4 for exit"
                    + "\n      ---------------------->>   ");
            x = input.nextInt();

            if (x == 1) {
                encode(test);
                ReadCmp rc = new ReadCmp();
                String cmp = rc.test();
                System.out.println("Encoded String is " + cmp);

            }
            if (x == 2) {
                ReadCmp rc = new ReadCmp();
                String cmp = rc.test();
                decode(cmp);

            }
            if (x == 3) {
                System.out.print("\n\n");
                PrintTree.printBinaryTree(root);
                System.out.print("\n\n");

            }
            if (x == 4) {
                System.out.println("FareWell My Friend");
                break;
            }

        }

    }

    private static HuffmanNode buildTree(Map<Character, Integer> freq) {

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();

        for (Character c : freq.keySet()) {

            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.data = c;
            huffmanNode.frequency = freq.get(c);
            huffmanNode.left = null;
            huffmanNode.right = null;
            priorityQueue.offer(huffmanNode);
        }
        assert priorityQueue.size() > 0;

        while (priorityQueue.size() > 1) {

            HuffmanNode x = priorityQueue.peek();
            priorityQueue.poll();
            HuffmanNode y = priorityQueue.peek();
            priorityQueue.poll();
            HuffmanNode sum = new HuffmanNode();

            sum.frequency = x.frequency + y.frequency;
            sum.data = '-';

            sum.left = x;

            sum.right = y;
            root = sum;

            priorityQueue.offer(sum);
        }

        return priorityQueue.poll();
    }

    private static void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {

        if (node != null) {
            if (node.left == null && node.right == null) {
                charPrefixHashMap.put(node.data, prefix.toString());

            } else {
                prefix.append('0');
                setPrefixCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setPrefixCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

    }

    private static String encode(String test) throws IOException {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < test.length() - 1; i++) {
            if (!freq.containsKey(test.charAt(i))) {
                freq.put(test.charAt(i), 0);
            }

            freq.put(test.charAt(i), freq.get(test.charAt(i)) + 1);
        }

        System.out.println("Character Frequency Map = " + freq);
        root = buildTree(freq);

        setPrefixCodes(root, new StringBuilder());
        System.out.println("Character Prefix Map = " + charPrefixHashMap);
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < test.length(); i++) {
            char c = test.charAt(i);
            s.append(charPrefixHashMap.get(c));
        }
        File file = new File("Tree.cmp");
        file.createNewFile();
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(s.toString().replace("null", ""));

        myWriter.close();

        return s.toString();

    }

    private static void decode(String code) {
        WriteTxt wt = new WriteTxt();
        StringBuilder stringBuilder = new StringBuilder();

        HuffmanNode temp = root;
        for (int i = 0; i < code.length() - 1; i++) {
            int j = Integer.parseInt(String.valueOf(code.charAt(i)));

            if (j == 0) {
                temp = temp.left;
                if (temp.left == null && temp.right == null) {
                    stringBuilder.append(temp.data);
                    temp = root;
                }
            }
            if (j == 1) {
                temp = temp.right;
                if (temp.left == null && temp.right == null) {
                    stringBuilder.append(temp.data);
                    temp = root;
                }
            }
        }

        wt.writer(stringBuilder.toString());
        System.out.println("Decoded string is " + stringBuilder.toString());

    }
}

class HuffmanNode implements Comparable<HuffmanNode> {

    public int frequency;
    public char data;
    public HuffmanNode left, right;

    public int compareTo(HuffmanNode node) {
        return frequency - node.frequency;
    }
}
