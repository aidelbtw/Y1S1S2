import java.util.ArrayList;
public abstract class L13Q1TestBST {
    public static void main(String[] args) {
        int[] data = {45, 88, 54, 76, 98, 1, 2, 20, 6, 53, 42, 100, 86, 32, 28, 65, 14};

        L13Q1BST<Integer> tree = new L13Q1BST<>();

        System.out.print("Input Data: ");
        for (int i = 0; i < data.length; i++) {
            tree.insert(data[i]);
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        System.out.print("Inorder (sorted): ");
        tree.inorder();
        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder();
        System.out.println();

        System.out.print("Preorder: ");
        tree.preorder();
        System.out.println();

        System.out.println("Height of BST: " + tree.height());

        System.out.println("Root for BST is: " + tree.getRoot());

        System.out.println("Check whether 10 is in the tree? " + tree.search(10));

        System.out.println("Delete 53");
        tree.delete(53);

        System.out.print("Updated Inorder data (sorted): ");
        tree.inorder();
        System.out.println();

        System.out.println("Min Value :" + tree.minValue());
        System.out.println("Max Value :" + tree.maxValue());

        ArrayList<L13Q1TreeNode<Integer>> path = tree.path(6);
        System.out.print("A path from the root to 6 is: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getElement());
            if (i < path.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
