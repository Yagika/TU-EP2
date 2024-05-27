package AB5;


/**
 * This data structure maps variables ('IntVar' objects) to constants ('IntConst' objects).
 * It is implemented as hash map. The map allows multiple variables with the
 * same name as long as they are not identical. There is no limit on the number of key-value
 * mappings stored in the map. This class implements 'IntVarConstMap'.
 */
//
// TODO: define further classes and methods for the implementation of this class, if
//  needed.
//
public class IntVarConstHashMap implements IntVarConstMap //TODO: uncomment clause.
{

    // TODO: declare variables.
    private int size;
    private IntVar[] keys;
    private IntConst[] values;

    /**
     * Initializes this map as an empty map.
     */
    public IntVarConstHashMap() {
        clear();
    }

    private void clear() {
        keys = new IntVar[65];
        values = new IntConst[65];
        size = 0;
    }

    /**
     * Initializes this map as an independent copy of the specified map. Later changes of 'this'
     * will not affect 'map' and vice versa.
     */
    public IntVarConstHashMap(IntVarConstHashMap map) {
        //TODO: implement constructor.
        System.arraycopy(map.keys, 0, keys, 0, map.keys.length);
        System.arraycopy(map.values, 0, values, 0, map.values.length);
        size = map.size;
    }

    //TODO: define missing parts of this class.
    @Override
    public IntConst get(IntVar k) {
        return values[find(k)];
    }
    @Override
    public IntConst put(IntVar k, IntConst v) {
        if (k == null) {
            return null;
        }
        int i = find(k);
        IntConst old = values[i];
        values[i] = v;
        if (keys[i] == null) {
            keys[i] = k;
            if (++size >= 0.75 * keys.length) {
                doubleCapacity();
            }
        }
        return old;
    }
    private int find(IntVar k) {
        if (k == null) {
            return keys.length - 1;
        }
        int i = k.hashCode() & (keys.length - 2);
        while (keys[i] != null && !keys[i].equals(k)) {
            i = (i + 1) & (keys.length - 2);
        }
        return i;
    }
    @Override
    public IntConst remove(IntVar k) {
        int i = find(k);
        IntConst result = values[i];
        if (keys[i] != null) {
            keys[i] = null;
            values[i] = null;
            size--;
            for (i = (i + 1) & (keys.length - 2); keys[i] != null; i = (i + 1) & (keys.length - 2)) {
                IntVar tempKey = keys[i];
                IntConst tempValue = values[i];
                keys[i] = null;
                values[i] = null;
                size--;
                put(tempKey, tempValue);
            }
        }
        return result;
    }
    @Override
    public boolean containsKey(IntVar k) {
        return keys[find(k)] != null;
    }
    @Override
    public int size() {

        return size;
    }


    @Override
    public IntVarQueue keyQueue() {
        IntVarQueue result = new IntVarQueue();
        for (int i = 0; i < keys.length - 1; i++) {
            if (keys[i] != null) {
                result.add(keys[i]);
            }
        }
        return result;
    }

    private void doubleCapacity() {
        IntVar[] newKey = new IntVar[(keys.length * 2) - 1];
        IntConst[] newValue = new IntConst[(values.length * 2) - 1];
        System.arraycopy(keys, 0, newKey, 0, keys.length);
        System.arraycopy(values, 0, newValue, 0, values.length);
        keys = newKey;
        values = newValue;
    }
    public int hashCode() {
        int h = size;
        for (int i = 0; i < keys.length - 1; i++) {
            if (keys[i] != null) {
                h += keys[i].hashCode();
                if (values[i] != null) {
                    h += values[i].hashCode();
                }
            }
        }
        return h;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != IntVarConstHashMap.class) return false;
        IntVarConstHashMap that = (IntVarConstHashMap) o;
        if (size != that.size) return false;
        for (int i = 0; i < keys.length - 1; i++) {
            if (keys[i] != null
                    && (values[i] == null
                    ? !that.containsKey(keys[i])
                    || that.get(keys[i]) != null
                    : !values[i].equals(that.get(keys[i])))) {
                return false;
            }

        }
        return true;
    }
}


// TODO: define further classes, if needed, either here or in a separate file.
