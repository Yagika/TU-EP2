package AB6;

class SingleIntVarIterator implements IntVarIterator {
    private final AB6.IntVar var;
    private boolean hasNext = true;

    SingleIntVarIterator(AB6.IntVar var) {
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
