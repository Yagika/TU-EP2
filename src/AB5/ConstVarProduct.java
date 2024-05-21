package AB5;

import java.util.Collections;
import java.util.Iterator;

/**
 * This class represents a product of a constant coefficient and a variable (i.e. a linear term).
 */
//
// TODO: define further classes, if needed.
//
public class ConstVarProduct implements IntVarTerm // TODO: uncomment clause.
{

    // TODO: declare variables.
    private final IntConst coefficient;
    private final IntVar variable;

    /**
     * Initialized this product of 'coeff' and 'var' (for example 3x is such a product).
     * @param coeff the coefficient of the term which is not 0 or 1,
     *              coeff != null && coeff.isZero() == false &&
     *              coeff.plus(new IntConst(-1)).isZero == false.
     * @param var the variable in the term, var != null.
     */
    public ConstVarProduct(IntConst coeff, IntVar var) {

        // TODO: implement constructor.
        this.coefficient = coeff;
        this.variable = var;
    }

    //TODO: define missing parts of this class.
    @Override
    public LinearExpression plus(LinearExpression e) {
        return null;
    }

    @Override
    public LinearExpression times(IntConst c) {
        return null;
    }


    public LinearExpression times(IntVarTerm t) {
        return null;
    }

    @Override
    public IntVarTerm negate() {
        return new ConstVarProduct((IntConst) coefficient.negate(), variable);
    }
    @Override
    public IntConst getCoeff() {
        return coefficient;
    }
    public IntVar getVar() {
        return variable;
    }
    public LinearExpression assignValue(IntVarConstMap map) {
        IntConst updatedCoefficient = coefficient;
        IntConst varValue = map.get(variable);
        if (varValue != null) {
            updatedCoefficient = updatedCoefficient.times(varValue);
        }
        return new ConstVarProduct(updatedCoefficient, variable);
    }

    public boolean isZero() {
        return coefficient.isZero();
    }
    @Override
    public Iterator<IntVar> iterator() {
        return Collections.singleton(variable).iterator();
    }
}

// TODO: define further classes, if needed, either here or in a separate file.
