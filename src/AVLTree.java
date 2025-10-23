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

    private int getHeight(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    private int getBalance(AVLNode n) {
        return (n == null) ? 0 : getHeight(n.left) - getHeight(n.right);
    }

    private int calculateHeight(AVLNode n) {
    	return (n == null) ? -1 : Math.max(getHeight(n.left), getHeight(n.right)) + 1;
    }
    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = calculateHeight(y);
        x.height = calculateHeight(x);

        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode temp = y.left;

        y.left = x;
        x.right = temp;

        y.height = calculateHeight(y);
        x.height = calculateHeight(x);
        
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

        node.height = calculateHeight(node);
        

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