package AB6;

/**
 * This class represents IS EQUAL condition. It represents the constraint that
 * two expressions are equal when their variables are assigned to specific values
 * (using the same variable assignments in both expressions).
 */
//
// TODO: define further classes and methods, if needed.
//
public class IsEqual implements Condition //TODO: uncomment clause.
{
    //TODO: define missing parts of this class.
    private LinearExpression expression1;
    private LinearExpression expression2;


    /**
     * Initializes 'this' with two linear expressions.
     * @param e1 the first expression, e1 != null.
     * @param e2 the second expression, e2 != null.
     */
    public IsEqual(LinearExpression e1, LinearExpression e2) {

        //TODO: implement constructor.
        this.expression1 = e1;
        this.expression2 = e2;
    }

    /**
     * Returns e1.assignValue(assignments).equals(e2.assignValue(assignments)).
     * @param /assignments the map with variable assignments, assignments != null.
     * @return e1.assignValue(assignments).equals(e2.assignValue(assignments)).
     */
    @Override
    public IntVarSet getVarSet() {
        IntVarSet varSet = new IntVarSetImpl();
        for (IntVar var : expression1) {
            varSet.add(var);
        }
        for (IntVar var : expression2) {
            varSet.add(var);
        }
        return varSet;
    }
    @Override
    public boolean getValue(IntVarConstMap assignments) {
        //TODO: implement method.
        return expression1.assignValue(assignments).equals(expression2.assignValue(assignments));
    }
}
