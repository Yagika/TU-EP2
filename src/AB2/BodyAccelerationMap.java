package AB2;
import AB1.Vector3;

/**
 * A map that associates a body with an acceleration vector. The number of
 * key-value pairs is not limited.
 */
public class BodyAccelerationMap {

    //TODO: declare variables.
    private Body[] bodies;
    private Vector3[] vector;
    private int size;

    /**
     * Initializes this map with an initial capacity.
     * @param initialCapacity specifies the size of the internal array. initialCapacity > 0.
     */
    public BodyAccelerationMap(int initialCapacity) {
        //TODO: define constructor.
        bodies = new Body[initialCapacity];
        vector = new Vector3[initialCapacity];
        size = 0;
    }

    /**
     * Adds a new key-value association to this map. If the key already exists in this map,
     * the value is replaced and the old value is returned. Otherwise, 'null' is returned.
     * @param key a body != null.
     * @param acceleration the acceleration vector to be associated with the key.
     * @return the old value if the key already existed in this map, or 'null' otherwise.
     */
    public Vector3 put(Body key, Vector3 acceleration) {
        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            if (bodies[i] == key) {
                Vector3 altValue = vector[i];
                vector[i] = acceleration;
                return altValue;

            }
        }
        if (size == bodies.length) {
            Body[] newBody = new Body[bodies.length * 2];
            Vector3[] newVector = new Vector3[vector.length * 2];
            for (int i = 0; i < size; i++) {
                newBody[i] = bodies[i];
                newVector[i] = vector[i];
            }
            bodies = newBody;
            vector = newVector;
        }
        bodies[size] = key;
        vector[size] = acceleration;
        size++;
        return null;
    }

    /**
     * Returns the value associated with the specified key, i.e. the acceleration vector
     * associated with the specified body. Returns 'null' if the key is not contained in this map.
     * @param key a body != null.
     * @return the value associated with the specified key (or 'null' if the key is not contained in
     * this map).
     */
    public Vector3 get(Body key) {
        //TODO: implement method.
        for (int i = 0; i < size; i++) {
            if (bodies[i] == key) {
                return vector[i];
            }
        }
        return null;
    }
}
