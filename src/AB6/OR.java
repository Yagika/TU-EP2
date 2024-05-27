package AB6;

/**
 * This class represents a Boolean OR-combination.
 */
//
// TODO: define further classes and methods, if needed.
//
public class OR implements Condition //TODO: uncomment clause.
{
    //TODO: define missing parts of this class.
    private Condition cond1, cond2;
    /**
     * Initializes 'this' as the Boolean combination 'c1 OR c2'.
     * @param c1 the first operand, c1 != null.
     * @param c2 the second operand, c2 != null.
     */
    public OR(Condition c1, Condition c2) {

        //TODO: implement constructor.
        this.cond1 = c1;
        this.cond2 = c2;
    }
    @Override
    public IntVarSet getVarSet() {
        IntVarSet vars = new IntVarSetImpl();
        for (IntVar var : cond1.getVarSet()) {
            vars.add(var);
        }
        for (IntVar var : cond2.getVarSet()) {
            vars.add(var);
        }
        return vars;
    }

    @Override
    public boolean getValue(IntVarConstMap assignments) {
        return cond1.getValue(assignments) || cond2.getValue(assignments);
    }
}