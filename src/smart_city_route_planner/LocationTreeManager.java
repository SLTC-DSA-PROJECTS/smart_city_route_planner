package smart_city_route_planner;

class AVLNode {
    String location;
    AVLNode left, right;
    int height;

    AVLNode(String loc) {
        location = loc;
        height = 1;
    }
}

class AVLTree {
    private AVLNode root;

    
    int height(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    
    int getBalance(AVLNode n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    
    AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    
    AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

   
    AVLNode insert(AVLNode node, String key) {
        if (node == null)
            return new AVLNode(key);

        if (key.compareToIgnoreCase(node.location) < 0)
            node.left = insert(node.left, key);
        else if (key.compareToIgnoreCase(node.location) > 0)
            node.right = insert(node.right, key);
        else
            return node; 

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

    
    void insert(String key) {
        root = insert(root, key);
    }

    
    void inOrder(AVLNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("- " + node.location);
            inOrder(node.right);
        }
    }

    
    void display() {
        if (root == null)
            System.out.println("No locations available.");
        else {
            System.out.println("All Locations (Sorted):");
            inOrder(root);
        }
    }
}

public class LocationTreeManager {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert("Colombo");
        tree.insert("Galle");
        tree.insert("Kandy");
        tree.insert("Matara");

        tree.display();
    }
}

