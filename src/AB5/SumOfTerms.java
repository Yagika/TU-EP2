package AB5;

import java.util.*;

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
    private final LinearExpression[] terms;
    private final int size;

    //TODO: define private constructors as needed.
    private SumOfTerms(LinearExpression[] terms) {
        this.terms = terms;
        this.size = terms.length;
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
        this(new LinearExpression[]{t1, t2});
    }

    /**
     * Initializes 'this' as a sum of a term with a variable and a constant.
     *
     * @param t the term != null.
     * @param c a constant != null, for which c.isZero() == false.
     */
    public SumOfTerms(IntVarTerm t, IntConst c) {
        //TODO: implement constructor.
        this(new LinearExpression[]{t, c});
    }

    @Override
    public LinearExpression plus(LinearExpression e) {
        if (e instanceof SumOfTerms && isNegation((SumOfTerms) e)) {
            return ZERO;
        }

        LinearExpression[] newTerms = Arrays.copyOf(terms, size + 1);
        newTerms[size] = e;

        return combineAndSimplifyTerms(newTerms);
    }

    private boolean isNegation(SumOfTerms other) {
        return this.size == other.size && this.negate().equals(other);
    }

    @Override
    public LinearExpression plus(IntConst c) {
        return plus((LinearExpression) c);
    }

    @Override
    public LinearExpression plus(IntVarTerm t) {
        return plus((LinearExpression) t);
    }

    @Override
    public LinearExpression negate() {
        LinearExpression[] negatedTerms = new LinearExpression[size];
        for (int i = 0; i < size; i++) {
            negatedTerms[i] = terms[i].negate();
        }
        return new SumOfTerms(negatedTerms);
    }

    @Override
    public LinearExpression times(IntConst c) {
        if (c.isZero()) {
            return ZERO;
        }
        LinearExpression[] multipliedTerms = new LinearExpression[size];
        for (int i = 0; i < size; i++) {
            multipliedTerms[i] = terms[i].times(c);
        }
        return new SumOfTerms(multipliedTerms);
    }

    @Override
    public LinearExpression assignValue(IntVarConstMap varValues) {
        LinearExpression[] assignedTerms = new LinearExpression[size];
        boolean allConstant = true;

        for (int i = 0; i < size; i++) {
            assignedTerms[i] = terms[i].assignValue(varValues);
            if (!(assignedTerms[i] instanceof IntConst)) {
                allConstant = false;
            }
        }
        if (allConstant) {
            return sumAllConstants(assignedTerms);
        }

        return combineAndSimplifyTerms(assignedTerms);
    }

    private IntConst sumAllConstants(LinearExpression[] terms) {
        IntConst sum = ZERO;

        for (LinearExpression term : terms) {
            sum = sum.plus((IntConst) term);
        }
        return sum;
    }

    @Override
    public IntVarIterator iterator() {
        return new SumOfTermsIterator(terms);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntVarTerm[] varTerms = new IntVarTerm[size];
        IntConst constant = ZERO;
        int varTermCount = 0;


        for (LinearExpression term : terms) {
            if (term instanceof IntVarTerm) {
                varTerms[varTermCount++] = (IntVarTerm) term;
            } else if (term instanceof IntConst) {
                constant = constant.plus((IntConst) term);
            }
        }

        appendTerms(sb, varTerms, varTermCount);
        appendConstant(sb, constant);
        return sb.length() > 0 ? sb.toString().replaceAll("\\s", "") : "0";
    }

    private void appendTerms(StringBuilder sb, IntVarTerm[] varTerms, int varTermCount) {
        boolean isFirst = true;

        for (int i = 0; i < varTermCount; i++) {
            IntVarTerm varTerm = varTerms[i];
            IntConst coeff = varTerm.getCoeff();

            for (int j = i + 1; j < varTermCount; j++) {
                if (varTerm.getVar().equals(varTerms[j].getVar())) {
                    coeff = coeff.plus(varTerms[j].getCoeff());
                    varTerms[j] = varTerms[--varTermCount];
                    j--;
                }
            }

            if (coeff.isZero()) continue;

            if (!isFirst) {
                sb.append(coeff.lessThan(ZERO) ? " - " : " + ");
            } else if (coeff.lessThan(ZERO)) {
                sb.append("-");
            }

            IntConst absCoeff = coeff.lessThan(ZERO) ? coeff.negate() : coeff;
            if (!absCoeff.equals(ONE)) {
                sb.append(absCoeff);
            }
            sb.append(varTerm.getVar());
            isFirst = false;
        }
    }

    private void appendConstant(StringBuilder sb, IntConst constant) {
        if (!constant.isZero()) {
            if (sb.length() > 0) {
                sb.append(constant.lessThan(ZERO) ? " - " : " + ");
            }
            IntConst absConstant = constant.lessThan(ZERO) ? constant.negate() : constant;
            sb.append(absConstant);
        }
    }

    private LinearExpression combineAndSimplifyTerms(LinearExpression[] newTerms) {


        IntVarTerm[] varTerms = new IntVarTerm[newTerms.length];
        IntConst constant = ZERO;
        int varTermCount = 0;

        for (LinearExpression term : newTerms) {
            if (term instanceof IntVarTerm compareVarTerm) {
                boolean combined = false;
                for (int i = 0; i < varTermCount; i++) {
                    if (varTerms[i].getVar().equals(compareVarTerm.getVar())) {
                        varTerms[i] = new ConstVarProduct(varTerms[i].getCoeff().plus(compareVarTerm.getCoeff()), compareVarTerm.getVar());
                        combined = true;
                        break;
                    }
                }
                if (!combined) {
                    varTerms[varTermCount++] = compareVarTerm;
                }
            } else if (term instanceof IntConst) {
                constant = constant.plus((IntConst) term);
            }
        }

        if (varTermCount == 0) {
            return constant;
        } else if (varTermCount == 1 && constant.isZero()) {
            return varTerms[0];
        } else {
            LinearExpression[] resultTerms = new LinearExpression[varTermCount + (constant.isZero() ? 0 : 1)];
            System.arraycopy(varTerms, 0, resultTerms, 0, varTermCount);
            if (!constant.isZero()) {
                resultTerms[varTermCount] = constant;
            }
            return new SumOfTerms(resultTerms);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SumOfTerms that = (SumOfTerms) o;
        return size == that.size && Arrays.equals(terms, that.terms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, Arrays.hashCode(terms));
    }
}

// TODO: define further classes, if needed, either here or in a separate file.

class SumOfTermsIterator implements IntVarIterator {
    private final LinearExpression[] terms;
    private int currentTermIndex;
    private IntVarIterator currentIterator;

    SumOfTermsIterator(LinearExpression[] terms) {
        this.terms = terms;
        this.currentTermIndex = 0;
        this.currentIterator = terms[0].iterator();
    }

    @Override
    public boolean hasNext() {
        while (!currentIterator.hasNext() && currentTermIndex < terms.length - 1) {
            currentTermIndex++;
            currentIterator = terms[currentTermIndex].iterator();
        }
        return currentIterator.hasNext();
    }

    @Override
    public IntVar next() {
        if (hasNext()) {
            return currentIterator.next();
        }
        return null;
    }
}

