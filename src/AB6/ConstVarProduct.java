package AB6;

import java.util.Objects;

/**
 * This class represents a product of a constant coefficient and a variable (i.e. a linear term).
 */
//
// TODO: define further classes, if needed.
//
public class ConstVarProduct implements IntVarTerm //TODO: uncomment clause.
{

    // TODO: declare variables.
    private final IntConst coeff;
    private final IntVar var;
    /**
     * Initialized this product of 'coeff' and 'var' (for example 3x is such a product).
     * @param coeff the coefficient of the term which is not 0 or 1,
     *              coeff != null && coeff.isZero() == false &&
     *              coeff.plus(new IntConst(-1)).isZero == false.
     * @param var the variable in the term, var != null.
     */
    public ConstVarProduct(IntConst coeff, IntVar var) {

        // TODO: implement constructor.
        this.coeff = coeff;
        this.var = var;
    }

    //TODO: define missing parts of this class.
    @Override
    public IntVar getVar() {
        return var;
    }
    @Override
    public IntConst getCoeff() {
        return coeff;
    }
    @Override
    public AB6.LinearExpression plus(AB6.LinearExpression e) {
        return e.plus(this);
    }
    @Override
    public AB6.LinearExpression times(AB6.IntConst c) {
        if (c.isZero()) {
            return this;
        }
        return new AB6.SumOfTerms(this, c);
    }
    @Override
    public AB6.LinearExpression negate() {
        return new ConstVarProduct(coeff.negate(), var);
    }
    @Override
    public AB6.LinearExpression assignValue(AB6.IntVarConstMap varValues) {
        AB6.IntConst value = varValues.get(var);
        if (value != null) {
            return coeff.times(value);
        }
        return this;
    }

    @Override
    public AB6.IntVarIterator iterator() {
        return new SingleIntVarIterator(var);
    }

    @Override
    public String toString() {
        if (coeff.equals(ONE.negate())) {
            return "-" + var;
        }
        if (coeff.equals(ONE)) {
            return var.toString();
        }
        return coeff + var.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(coeff, var);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AB6.ConstVarProduct that = (AB6.ConstVarProduct) o;
        return Objects.equals(coeff, that.coeff) && Objects.equals(var, that.var);
    }
}

// TODO: define further classes, if needed, either here or in a separate file.
