package huffman;

public class PrintTree {

    private static void printBinaryTree(HuffmanNode root, int level) {
        if (root == null) {
            return;
        }
        printBinaryTree(root.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t");
            }
            System.out.println("|-------" + root.data);
        } else {
            System.out.println(root.data);
        }
        printBinaryTree(root.left, level + 1);
    }

    static int height(HuffmanNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(height(node.left), height(node.right));
        }
    }

    public static void printBinaryTree(HuffmanNode node) {

        printBinaryTree(node, height(node));

    }

}
