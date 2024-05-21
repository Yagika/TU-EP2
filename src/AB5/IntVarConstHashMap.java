package AB5;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;


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
public class IntVarConstHashMap  implements IntVarConstMap //TODO: uncomment clause.
{

    // TODO: declare variables.
    private final Map<IntVar, IntConst> map;
    /**
     * Initializes this map as an empty map.
     */
    public IntVarConstHashMap() {
        //TODO: implement constructor.
        map = new HashMap<>();
    }

    /**
     * Initializes this map as an independent copy of the specified map. Later changes of 'this'
     * will not affect 'map' and vice versa.
     */
    public IntVarConstHashMap(IntVarConstHashMap map) {
        //TODO: implement constructor.
        this.map = new HashMap<>(map.map);
    }

    //TODO: define missing parts of this class.
    @Override
    public IntConst get(IntVar variable) {
        return map.get(variable);
    }
    @Override
    public void put(IntVar variable, IntConst constant) {
        map.put(variable, constant);
    }

    @Override
    public IntConst remove(IntVar variable) {
        return map.remove(variable);
    }
    @Override
    public boolean containsKey(IntVar variable) {
        return map.containsKey(variable);
    }
    @Override
    public int size() {
        return map.size();
    }
    public Queue<IntVar> keyQueue() {
        return new LinkedList<>(map.keySet());    }
}

// TODO: define further classes, if needed, either here or in a separate file.
