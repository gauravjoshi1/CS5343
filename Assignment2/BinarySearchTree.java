class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinarySearchTree {
    TreeNode root;

    void insertInTree(int nodeVal) {
        root = insertInBST(root, nodeVal);
    }

    private TreeNode insertInBST(TreeNode node, int nodeVal) {
        if (node == null) return new TreeNode(nodeVal);

        if (nodeVal > node.val) {
            node.right = insertInBST(node.right, nodeVal);
        } else {
            node.left = insertInBST(node.left, nodeVal);
        }

        return node;
    }

    void deleteInTree(int val) {
        root = traverse(root, val);
    }

    private TreeNode traverse(TreeNode root, int nodeVal) {
        if (root == null) return null;

        if (root.val == nodeVal) {
            return deleteFromTree(root);
        }

        if (root.val > nodeVal) {
            root.left = traverse(root.left, nodeVal);
        } else {
            root.right = traverse(root.right, nodeVal);
        }

        return root;
    }

    private TreeNode deleteFromTree(TreeNode root) {
        // remove leaf
        if (root.left == null && root.right == null) {
            return null;
        }

        if (root.right != null) {
            TreeNode rootNext = successor(root);
            root.val = rootNext.val;
            root.right = traverse(root.right, rootNext.val);
        } else {
            TreeNode rootPred = predecessor(root);
            root.val = rootPred.val;
            root.left = traverse(root.left, rootPred.val);
        }

        return root;
    }

    private TreeNode predecessor(TreeNode node) {
        node = node.left;

        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    private TreeNode successor(TreeNode node) {
        node = node.right;

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    void inorder(){
        printInOrder(root);
    }

    private void printInOrder(TreeNode node) {
        if (node == null) return;

        printInOrder(node.left);
        System.out.print(node.val + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        int[] treeValArr = new int[]{40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46};

        for (int treeVal : treeValArr) {
            bst.insertInTree(treeVal);
        }
        System.out.println("Inorder Traversal: ");
        bst.inorder();
        System.out.println("\n---");
        System.out.println("Now deleting 40");
        bst.deleteInTree(40);
        bst.inorder();
        System.out.println("\n---");
        System.out.println("Now deleting 20");
        bst.deleteInTree(20);
        bst.inorder();
    }
}
