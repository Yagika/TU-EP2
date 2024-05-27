package AB6;

/**
 * This data structure maps variables ('IntVar' objects) to constants ('IntConst' objects).
 * It is implemented as a binary search tree where variables are sorted lexicographically according
 * their name using the 'compareTo' method of String. There is no limit on the number of
 * key-value mappings stored in the map.
 */
//
// TODO: define further classes and methods for the implementation of the binary search tree, if
//  needed.
//
public class IntVarConstTreeMap implements IntVarConstMap //TODO: uncomment clause.
{

    //TODO: declare variables.
    private TreeNode root;
    private int size;
    public IntVarConstTreeMap() {
        //TODO: implement constructor.
        this.root = null;
        this.size = 0;
    }


    public IntVarConstTreeMap(IntVarConstTreeMap map) {

        if (map.root != null) {
            this.root = new TreeNode(map.root);
        }
        this.size = map.size;
    }
    @Override

    public AB6.IntConst put(AB6.IntVar key, AB6.IntConst value) {

        if (root == null) {
            root = new AB6.TreeNode(key, value, null, null);
            size++;
            return null;
        }

        boolean[] added = new boolean[]{false};

        AB6.IntConst toReturn = root.put(key, value, added);
        if (added[0]) size++;
        return toReturn;
    }
    @Override

    public AB6.IntConst get(AB6.IntVar key) {

        if (root == null) {
            return null;
        }

        return root.get(key);
    }
    @Override

    public boolean containsKey(AB6.IntVar key) {
        if (root == null) {
            return false;
        }

        return root.containsKey(key);
    }

    @Override

    public int size() {

        return size;
    }

    public AB6.IntConst remove(IntVar key) {
        if (root == null || !containsKey(key)) {
            return null;
        }
        size--;
        IntConst removedValue = root.remove(key);
        if (removedValue != null) {
            size--;
        }
        return removedValue;
    }


    @Override

    public IntVarSet keySet() {
        IntVarSet result = new IntVarSetImpl();
        if (root != null) root.addToIntVarSet(result);
        return result;
    }
}

// TODO: define further classes, if needed (either here or in a separate file).

class TreeNode {
    private TreeNode left;
    private AB6.TreeNode right;
    private AB6.IntVar key;
    private AB6.IntConst value;

    public TreeNode(AB6.IntVar key, AB6.IntConst value, AB6.TreeNode left, AB6.TreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.value = value;
    }
    public TreeNode(AB6.TreeNode node) {
        this.key = node.key;

        if (node.left != null) this.left = new AB6.TreeNode(node.left);
        if (node.right != null) this.right = new AB6.TreeNode(node.right);

        this.value = node.value;
    }
    AB6.IntConst put(AB6.IntVar p, AB6.IntConst value, boolean[] added) {

        if (this.key == p) {
            AB6.IntConst toReturn = this.value;
            this.value = value;
            return toReturn;
        }

        if (key.getName().compareTo(p.getName()) < 0) {
            if (left == null) {
                left = new AB6.TreeNode(p, value,null, null);
                added[0] = true;
                return null;
            } else {
                return left.put(p, value, added);
            }
        } else {
            if (right == null) {
                right = new AB6.TreeNode(p, value,null, null);
                added[0] = true;
                return null;
            } else {
                return right.put(p, value, added);
            }
        }
    }

    public IntConst get(AB6.IntVar p) {
        if (key == p) {
            return value;
        }

        if (key.getName().compareTo(p.getName()) < 0) {
            if (left == null) {
                return null;
            }
            return left.get(p);
        } else {
            if (right == null) {
                return null;
            }
            return right.get(p);
        }
    }

    public boolean containsKey(IntVar key) {
        if (this.key == key) {
            return true;
        }
        if (this.key.getName().compareTo(key.getName()) < 0) {
            return left != null && left.containsKey(key);
        } else {
            return right != null && right.containsKey(key);
        }
    }

    public void addToIntVarSet(IntVarSet set) {
        if (left != null) {
            left.addToIntVarSet(set);
        }
        set.add(key);
        if (right != null) {
            right.addToIntVarSet(set);
        }
    }

    public String toString() {
        String result;
        result = right == null ? "" : right.toString();
        result += (result.isEmpty() ? "" : ", ") + this.key+"="+ value;
        result += left == null ? "" :  ", " + left;
        return result;
    }

    public TreeNode remove(IntVar k) {
        if (k.getValue() < this.key.getValue()) {
            if (this.left != null) {
                this.left = this.left.remove(k);
            }
            return this;
        } else if (k.getValue() > this.key.getValue()) {
            if (this.right != null) {
                this.right = this.right.remove(k);
            }
            return this;
        } else {
            if (this.left == null) {
                return this.right;
            } else if (this.right == null) {
                return this.left;
            } else {
                TreeNode minRight = this.right.findMin();
                this.key = minRight.key;
                this.value = minRight.value; // Copy value from minRight
                this.right = this.right.remove(minRight.key);
                return this;
            }
        }
    }

    private TreeNode findMin() {
        TreeNode current = this;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }


}
