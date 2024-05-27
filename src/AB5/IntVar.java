package AB5;

import java.util.Objects;

/**
 * This class represents a free variable which can take on integer values. Each object of
 * this class represents a different variable (regardless of the name). This means that
 * for two 'IntVar' reference values 'a' and 'b', a.equals(b) == true only if 'a' and 'b'
 * refer to the same object (a == b has value 'true').
 */
//
// TODO: define further classes, if needed.
//
public class IntVar implements IntVarTerm //TODO: uncomment clause.
{
    private final String name;

    /**
     * Initializes this variable with a specified name.
     *
     * @param name, the name != null.
     */
    public IntVar(String name) {

        this.name = name;
    }

    /**
     * Returns the name of this variable.
     *
     * @return the name of this variable.
     */
    public String getName() {

        return name;
    }

    @Override
    /**
     * Returns a readable representation of 'this', which is the name of this variable.
     * @return a readable representation of 'this'.
     */
    public String toString() {

        return name;
    }

    //TODO: define missing parts of this class.
    @Override
    public LinearExpression plus(LinearExpression e) {
        return e.plus(this);
    }

    @Override
    public LinearExpression plus(IntVarTerm t) {
        if (this.equals(t.getVar())) {
            return new ConstVarProduct(t.getCoeff().plus(ONE), this);
        }
        return new SumOfTerms(this, t);
    }

    @Override
    public LinearExpression negate() {
        return new ConstVarProduct(ONE.negate(), this);
    }

    @Override
    public LinearExpression times(IntConst c) {
        if (c.isZero()) {
            return ZERO;
        }
        if (c.plus(ONE.negate()).isZero()) {
            return this.negate();
        }
        if (c.plus(ONE).isZero()) {
            return this;
        }
        return new ConstVarProduct(c, this);
    }

    @Override
    public LinearExpression assignValue(IntVarConstMap varValues) {
        IntConst value = varValues.get(this);
        if (value != null) {
            return value;
        }
        return this;
    }

    @Override
    public IntVar getVar() {
        return this;
    }

    @Override
    public IntConst getCoeff() { //coeff immer 1
        return ONE;
    }

    @Override
    public IntVarIterator iterator() {

        return new SingleIntVarIterator(this);
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

// TODO: define further classes, if needed, either here or in a separate file.
