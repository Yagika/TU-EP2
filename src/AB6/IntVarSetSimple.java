package AB6;

public interface IntVarSetSimple extends Iterable<IntVar> {
    default IntVarSetSimple keySet(IntVar v) {
        if (v.equals(new IntVar(e))){
            return true;
        }
    }

    boolean contains(IntVar v);

    int size();
}
