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

    @Override
    public IntConst remove(IntVar key) {
        if (key != null) {
            TreeNode parent = null;
            TreeNode current = root;
            while (current != null && !current.getKey().equals(key)) {
                parent = current;
                int cmp = key.getName().compareTo(current.getKey().getName());
                if (cmp < 0) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }

            if (current == null) {
                return null;
            }

            IntConst oldValue = current.getValue();

            if (current.getLeft() == null && current.getRight() == null) {
                if (current == root) {
                    root = null;
                } else if (current.equals(parent.getLeft())) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            } else if (current.getLeft() != null && current.getRight() != null) {
                TreeNode successorParent = current;
                TreeNode successor = current.getRight();
                while (successor.getLeft() != null) {
                    successorParent = successor;
                    successor = successor.getLeft();
                }
                current.setKey(successor.getKey());
                current.setValue(successor.getValue());
                if (successorParent.getLeft() == successor) {
                    successorParent.setLeft(successor.getRight());
                } else {
                    successorParent.setRight(successor.getRight());
                }
            } else {
                TreeNode child = (current.getLeft() != null) ? current.getLeft() : current.getRight();
                if (current == root) {
                    root = child;
                } else if (current == parent.getLeft()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            }
            size--;
            return oldValue;
        }
        return null;
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

    public IntVar getKey() {
        return key;
    }

    public void setKey(IntVar key) {
        this.key = key;
    }

    public IntConst getValue() {
        return value;
    }

    public void setValue(IntConst value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

}
