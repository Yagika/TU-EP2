package AB5;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents a linear expression consisting of more than one term.
 * For example, 3x - y + 5 consists of multiple terms. 'SumOfTerms' provides an iterator over all
 * variables occurring in 'this'. The order of the iteration is not specified.
 * In this example it iterates over elements 'x' and 'y'. This class implements 'LinearExpression'.
 */
//
// TODO: define further classes, if needed.
//
public class SumOfTerms implements LinearExpression // TODO: uncomment clause.
{

    //TODO: declare variables.
    private Set<IntVarTerm> terms;

    //TODO: define private constructors as needed.
    public SumOfTerms() {
        terms = new HashSet<>();
    }

    public SumOfTerms(Set<IntVarTerm> terms) {
        this.terms = new HashSet<>(terms);
    }

    /**
     * Initializes 'this' as a sum of two terms, each with a variable.
     *
     * @param t1 the first term in this sum.
     * @param t2 the second term in this sum.
     *           The following conditions holds: t1.getVar().equals(t2.getVar()) == false.
     */
    public SumOfTerms(IntVarTerm t1, IntVarTerm t2) {
        //TODO: implement constructor.
        this();
        terms.add(t1);
        terms.add(t2);
    }

    /**
     * Initializes 'this' as a sum of a term with a variable and a constant.
     *
     * @param t the term != null.
     * @param c a constant != null, for which c.isZero() == false.
     */
    public SumOfTerms(IntVarTerm t, IntConst c) {
        //TODO: implement constructor.
        this();
        terms.add(t);
        terms.add(c);
    }

    public LinearExpression plus(IntVarTerm t) {
        SumOfTerms newSum = new SumOfTerms();
        newSum.terms.add(t);
        return newSum;
    }

    @Override
    public LinearExpression plus(IntConst c) {
        SumOfTerms newSum = new SumOfTerms();
        newSum.terms.add(c);
        return newSum;
    }

    @Override
    public LinearExpression plus(LinearExpression e) {
        return e.plus(this);
    }

    @Override
    public LinearExpression negate() {
        SumOfTerms negatedSum = new SumOfTerms();
        for (IntVarTerm term : terms) {
            negatedSum.terms.add((IntVarTerm) term.negate());
        }
        return negatedSum;
    }

    public Iterator<IntVar> iterator() {
        Set<IntVar> intVars = new HashSet<>();
        for (IntVarTerm term : terms) {
            if (term instanceof IntVar) {
                intVars.add((IntVar) term);
            }
        }
        return intVars.iterator();
    }

    //TODO: implement missing parts of this class.
    @Override
    public LinearExpression assignValue(IntVarConstMap map) {
        for (IntVarTerm term : terms) {
            if (term instanceof IntVar) {
                IntVar var = (IntVar) term;
                if (map.containsKey(var)) {
                    return map.get(var);
                }
            }
        }
        return this;
    }

    @Override
    public LinearExpression times(IntConst c) {
        Set<IntVarTerm> newTerms = new HashSet<>();
        for (IntVarTerm term : terms) {
            if (term instanceof IntVar) {
                newTerms.add(new ConstVarProduct(c, (IntVar) term));
            } else if (term instanceof IntConst) {
                // Multiply the constant by c
                IntConst constTerm = (IntConst) term;
                newTerms.add(new IntConst(constTerm.getValue() * c.getValue()));
            }
        }
        return new SumOfTerms(newTerms);
    }

    public boolean isZero() {
        for (IntVarTerm term : terms) {
            if (!term.isZero()) {
                return false;
            }
        }
        return true;
    }
}

// TODO: define further classes, if needed, either here or in a separate file.
