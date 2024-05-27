package AB6;

public class IntVarSetImpl implements IntVarSet {
    private IntVar[] vars;
    private int size;

    public IntVarSetImpl() {
        vars = new IntVar[10];
        size = 0;
    }

    @Override
    public void add(IntVar v) {
        if (size == vars.length) {
            IntVar[] newVars = new IntVar[vars.length * 2];
            System.arraycopy(vars, 0, newVars, 0, size);
            vars = newVars;
        }
        vars[size++] = v;
    }

    @Override
    public boolean contains(IntVar v) {
        for (int i = 0; i < size; i++) {
            if (vars[i].equals(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public IntVarIterator iterator() {
        return new IntVarIterator() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }
            @Override
            public IntVar next() {
                return vars[currentIndex++];
            }
        };
    }
}