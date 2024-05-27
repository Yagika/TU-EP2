package AB6;

/**
 * This class represents a Boolean negation (NOT).
 */
//
// TODO: define further classes and methods, if needed.
//
public class NOT implements Condition //TODO: uncomment clause.
{
    //TODO: define missing parts of this class.
    private Condition condition;

    /**
     * Initializes 'this' as the negation 'NOT c'.
     * @param c the operand for the NOT operator, c != null.
     */
    public NOT(Condition c) {

        //TODO: implement constructor.
        this.condition = c;
    }
    @Override
    public IntVarSet getVarSet() {
        return condition.getVarSet();
    }

    @Override
    public boolean getValue(IntVarConstMap assignments) {
        return !condition.getValue(assignments);
    }
}