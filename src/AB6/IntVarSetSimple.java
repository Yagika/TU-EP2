package AB6;

public interface IntVarSetSimple extends Iterable<IntVar> {

    boolean contains(IntVar v);

    int size();
}
