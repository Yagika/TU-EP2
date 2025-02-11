package AB5;

import java.util.Queue;

/**
 * This data structure maps variables ('IntVar' objects) to constants ('IntConst' objects).
 * There is no limit on the number of key-value mappings stored in the map.
 */
public interface IntVarConstMap {

    /**
     * Returns the value associated with the specified key. More formally, if this map contains a
     * mapping from a key 'k' to a value 'v' such that key.equals(k), then this method returns 'v';
     * otherwise it returns 'null'. (There can be at most one such mapping.)
     * Returns 'null' if the key is not contained in this map.
     * @param key a variable != null.
     * @return the value associated with the specified key (or 'null' if the key is not contained in
     * this map).
     */
    IntConst get(IntVar key);

    /**
     * If the map previously contained a mapping for a key that equals 'key', the old value is
     * replaced by the specified value and the old value is returned. Otherwise, this method adds
     * a new key-value mapping to this map and returns 'null'.
     *
     * @param key   the key of the mapping != null.
     * @param value the constant to be associated with the key (can also be 'null').
     */
    IntConst put(IntVar key, IntConst value);

    /**
     * Removes the mapping for a key from this map if it is present. More formally, if this map
     * contains a mapping from key k to value v such that key.equals(k),
     * that mapping is removed. (The map can contain at most one such mapping.)
     * Returns the value to which this map previously associated the key, or 'null' if the map
     * contained no mapping for the key.
     *
     * @param key the key of the mapping to be removed, key != null.
     * @return
     */
    Object remove(IntVar key);

    /**
     * Returns 'true' if this map contains a mapping for the specified key. More formally, returns
     * 'true' if and only if this map contains a mapping for a key k such that key.equals(k).
     * (There can be at most one such mapping.)
     * @param key
     * @return
     */
    boolean containsKey(IntVar key);

    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map.
     */
    int size();


    /**
     * Returns a new 'IntVarQueue' object containing all the keys of this map. The order is not
     * specified.
     *
     * @return a new 'IntVarQueue' object containing all the keys of this map.
     */
    IntVarQueue keyQueue();
}
