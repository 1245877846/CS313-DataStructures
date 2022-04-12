public class myBST {
    protected BSTnode root;
    // v1.1
    // add the search customer method.

    public myBST() {
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

    // search a customer
    public Customer search(Customer x) {
        return search(x, root);
    }

    public void Inorder() {
        Inorder(root);
    }

    // BST methods(v1.1 )
    private static BSTnode insertBST(Customer x, BSTnode t) {
        if (t == null) {
            t = new BSTnode(x);
        } else { // t!=null
            if (t.data.compareTo(x) > 0) {// x < t.data
                t.left = insertBST(x, t.left);
            } else { // x >= t.data
                t.right = insertBST(x, t.right);
            }
        }
        return t;
    }

    // delete an element from the BST
    private static BSTnode deleteBST(Customer x, BSTnode t) {
        if (t == null) {
            throw new IllegalArgumentException("already empty");
        } else {
            if (t.data.compareTo(x) == 0) {
                // for node which has 0 or 1 child
                if (t.left == null) {
                    return t.right;
                } else if (t.right == null) {
                    return t.left;
                    // for node which has 2 children
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

    // search a customer
    private static Customer search(Customer x, BSTnode t) {
        if (t == null) {
            return null;
        } else if (t.data.compareTo(x) == 0) {
            return t.data;
        } else {
            if (t.data.compareTo(x) < 0) {
                return search(x, t.right);
            } else {// t.data.compareTo(x) > 0
                return search(x, t.left);
            }
        }
    }

    // find the lowest node.data
    private static Customer findMin(BSTnode t) {
        if (t.left == null) {
            return t.data;
        }
        return findMin(t.left);
    }

    // find the highest node.data
    private static Customer findMax(BSTnode t) {
        if (t.right == null) {
            return t.data;
        }
        return findMax(t.right);
    }

    // print out the Binary search tree through Inorder
    private static void Inorder(BSTnode t) {
        if (t != null) {
            // left root right
            Inorder(t.left);
            System.out.println(t.data);
            Inorder(t.right);
        }
    }
}
