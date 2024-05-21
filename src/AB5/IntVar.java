package AB5;

import java.util.Iterator;
import java.util.Collections;
import java.util.List;
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
    public IntVar getVar() {
        return this;
    }

    @Override
    public IntConst getCoeff() {
        return new IntConst(1);
    }

    @Override
    public LinearExpression times(IntConst c) {
        return new ConstVarProduct(c, this);
    }

    @Override

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IntVar intVar = (IntVar) obj;
        return name.equals(intVar.name);
    }

    @Override

    public int hashCode() {
        return name.hashCode();

    }

    @Override

    public LinearExpression assignValue(IntVarConstMap map) {
        if (map.containsKey(this)) {
            return map.get(this);
        } else {
            return this;
        }
    }

    @Override
    public LinearExpression plus(LinearExpression e) {
        if (e instanceof IntVar) {
            return new SumOfTerms(this, (IntVar) e);
        }
        return new SumOfTerms(this, new IntConst(0));
    }

    public boolean isZero() {
        return getCoeff().isZero();
    }

    @Override
    public IntVarTerm negate() {
        return new ConstVarProduct(new IntConst(-1), this);
    }
    @Override
    public Iterator<IntVar> iterator() {
        List<IntVar> singletonList = Collections.singletonList(this);
        return singletonList.iterator();
    }
}

// TODO: define further classes, if needed, either here or in a separate file.
