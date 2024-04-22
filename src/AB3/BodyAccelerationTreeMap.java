package AB3;

import AB1.Vector3;
import AB2.Body;

/**
 * This data structure maps bodies to vectors ('Vector3' objects). It is implemented
 * as a binary search tree where bodies are sorted based on their masses.
 * The map allows multiple bodies with the same mass as long as they are not identical.
 * There is no limit on the number of key-value pairs stored in the map.
 */
//
// TODO: define further classes and methods for the implementation of the binary search tree, if
//  needed.
//
public class BodyAccelerationTreeMap {

    //TODO: declare variables.
    private Node root;
    private static class Node {
        Body key;
        Vector3 value;
        Node left, right;

        Node(Body key, Vector3 value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    /**
     * Adds a new key-value association to this map. If the key already exists in this map,
     * the value is replaced and the old value is returned. Otherwise, 'null' is returned.
     * @param key a body != null.
     * @param value the vector to be associated with the key (can also be 'null').
     * @return the old value if the key already existed in this map, or 'null' otherwise.
     */
    public Vector3 put(Body key, Vector3 value) {

        // TODO: implement method.
        if (key == null) {
            throw new IllegalArgumentException("Fehler");
        }

        if (root == null) {
            root = new Node(key, value);
            return null;
        }
        Node parent = null;
        Node current = root;
        while (current != null) {
            if (key.getMass()==current.key.getMass()) {
                Vector3 oldValue = current.value;
                current.value = value;
                return oldValue;
            } else if (key.getMass()<current.key.getMass()) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }
        // Insert the new node based on the comparison result
        Node newNode = new Node(key, value);
        if (key.getMass()<current.key.getMass()) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        return null;
    }


    /**
     * Returns the value associated with the specified key, i.e. the vector
     * associated with the specified body. Returns 'null' if the key is not contained in this map.
     * @param key a body != null.
     * @return the value associated with the specified key (or 'null' if the key is not contained in
     * this map).
     */
    public Vector3 get(Body key) {

        // TODO: implement method.
        if (key == null) {
            throw new IllegalArgumentException("Fehler");
        }

        Node current = root;
        while (current != null) {
            if (key.getMass() == current.key.getMass()) {
                return current.value;
            } else if (key.getMass() < current.key.getMass()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    /**
     * Returns 'true' if this map contains a mapping for the specified key.
     * @param key a body != null.
     * @return 'true' if this map contains a mapping for the specified key, or 'false' otherwise.
     */
    public boolean containsKey(Body key) {

        // TODO: implement method.
        if (key == null) {
            throw new IllegalArgumentException("Fehler");
        }

        Node current = root;
        while (current != null) {
            if (key.getMass() == current.key.getMass()) {
                return true;
            } else if (key.getMass() < current.key.getMass()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    /**
     *  Returns a readable representation of this map, in which key-value pairs are listed in
     *  descending order according to the mass of the bodies.
     */
    public String toString() {

        // TODO: implement method.
        StringBuilder sb = new StringBuilder();
        toStringHelper(root, sb);
        return sb.toString();
    }
    private void toStringHelper(Node node, StringBuilder sb) {
        if (node != null) {
            toStringHelper(node.right, sb);
            sb.append("(").append(node.key).append(", ").append(node.value).append(")\n");
            toStringHelper(node.left, sb);
        }
    }
    public AB3.BodyQueue getMaxAndMin(Body key){
        for (int i = 0; i < ; i++) {
            if (){

            }
        }
        if (this == null) {
            return AB3.BodyQueue[];
        }else {
            return new AB3.BodyQueue[];
        }
    }
}
// TODO: define further classes, if needed (either here or in a separate file).
