public class AVLTree {

    private class AVLNode {
        private String location;
        private AVLNode left, right;
        private int height;

        AVLNode(String loc) {
            this.location = loc;
            this.height = 1;
        }
    }

    private AVLNode root;

    private int height(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    private int getBalance(AVLNode n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private AVLNode insert(AVLNode node, String key) {
        if (node == null)
            return new AVLNode(key);

        int comparison = key.compareToIgnoreCase(node.location);

        if (comparison < 0) {
            node.left = insert(node.left, key);
        } else if (comparison > 0) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.compareToIgnoreCase(node.left.location) < 0)
            return rotateRight(node);

        if (balance < -1 && key.compareToIgnoreCase(node.right.location) > 0)
            return rotateLeft(node);

        if (balance > 1 && key.compareToIgnoreCase(node.left.location) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && key.compareToIgnoreCase(node.right.location) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("- " + node.location);
            inOrder(node.right);
        }
    }

    public void insert(String key) {
        root = insert(root, key);
    }

    public void display() {
        if (root == null)
            System.out.println("No locations available.");
        else {
            System.out.println("All Locations (Sorted):");
            inOrder(root);
        }
    }
}