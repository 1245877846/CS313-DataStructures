public class myBST {
    BSTnode root;

    public myBST() {
        this.root = null;
    }

    public myBST(Customer x) {
        root = new BSTnode(x);
    }

    // member methods
    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    public void insertBST(Customer x) {
        root = insertBST(x, root);
    }

    public void deleteBST(Customer x) {
        root = deleteBST(x, root);
    }

    public void Inorder() {
        Inorder(root);
    }

    // BST methods
    public static BSTnode insertBST(Customer x, BSTnode t) {
        if (t == null) {
            t = new BSTnode(x);
        } else { // t!=null
            if (t.data.compareTo(x) > 0) {//x < t.data
                t.left = insertBST(x, t.left);
            } else { // x >= t.data
                t.right = insertBST(x, t.right);
            }
        }
        return t;
    }

    // delete an element from the BST
    public static BSTnode deleteBST(Customer x, BSTnode t) {
        if (t == null) {
            throw new IllegalArgumentException("empty");
        } else {
            if (t.data.compareTo(x) == 0) {
                //for node which has 0 or 1 child
                if (t.left == null) {
                    return t.right;
                } else if (t.right == null) {
                    return t.left;
                //for node wich has 2 children
                } else {
                    t.data = findMin(t.right);
                    // delete the original one
                    t.right = deleteBST(t.data, t.right);
                }
            } else {
                if (t.data.compareTo(x) > 0) {
                    t.left = deleteBST(x, t.left);
                } else { // x >= t.data
                    t.right = deleteBST(x, t.right);
                }
            }
        }
        return t;
    }

    //find customer with the lowest ascii code
    public static Customer findMin(BSTnode t) {
        if (t.left == null) {
            return t.data;
        }
        return findMin(t.left);
    }

    //find customer with the highest ascii code
    public static Customer findMax(BSTnode t) {
        if (t.right == null) {
            return t.data;
        }
        return findMax(t.right);
    }

    //print out the Customer list from a to z
    public static void Inorder(BSTnode t) {
        if (t != null) {
            // left root right
            Inorder(t.left);
            System.out.print(t.data + " ");
            Inorder(t.right);
        }
    }
}
