package AB5;

class SingleIntVarIterator implements IntVarIterator {
    private final IntVar var;
    private boolean hasNext = true;

    SingleIntVarIterator(IntVar var) {
        this.var = var;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public IntVar next() {
        if (hasNext) {
            hasNext = false;
            return var;
        }
        return null;
    }
}

