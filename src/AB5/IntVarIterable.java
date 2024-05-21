package AB5;

import java.util.Iterator;

/**
 * An object providing an iterator over its 'IntVar' elements.
 */
public interface IntVarIterable extends Iterable<IntVar> {

    /**
     * Returns an iterator over elements of type 'IntVar'.
     *
     * @return an iterator over elements of type 'IntVar'.
     */
    Iterator<IntVar> iterator();
}
