package AB6;

import java.util.Arrays;
import java.util.Objects;

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
    private final AB6.LinearExpression[] terms;
    private final int size;

    //TODO: define private constructors as needed.
    private SumOfTerms(AB6.LinearExpression[] terms) {
        this.terms = terms;
        this.size = terms.length;
    }

    /**
     * Initializes 'this' as a sum of two terms, each with a variable.
     * @param t1 the first term in this sum.
     * @param t2 the second term in this sum.
     *          The following conditions holds: t1.getVar().equals(t2.getVar()) == false.
     */
    public SumOfTerms(IntVarTerm t1, IntVarTerm t2) {

        //TODO: implement constructor.
        this(new AB6.LinearExpression[]{t1, t2});
    }

    /**
     * Initializes 'this' as a sum of a term with a variable and a constant.
     * @param t the term != null.
     * @param c a constant != null, for which c.isZero() == false.
     */
    public SumOfTerms(IntVarTerm t, IntConst c) {

        //TODO: implement constructor.
        this(new AB6.LinearExpression[]{t, c});
    }


    //TODO: implement missing parts of this class.
    @Override
    public AB6.LinearExpression plus(AB6.LinearExpression e) {
        if (e instanceof AB6.SumOfTerms && isNegation((AB6.SumOfTerms) e)) {
            return ZERO;
        }

        AB6.LinearExpression[] newTerms = Arrays.copyOf(terms, size + 1);
        newTerms[size] = e;

        return combineAndSimplifyTerms(newTerms);
    }

    private boolean isNegation(AB6.SumOfTerms other) {
        return this.size == other.size && this.negate().equals(other);
    }

    @Override
    public AB6.LinearExpression plus(AB6.IntConst c) {
        return plus((AB6.LinearExpression) c);
    }

    @Override
    public AB6.LinearExpression plus(AB6.IntVarTerm t) {
        return plus((AB6.LinearExpression) t);
    }

    @Override
    public AB6.LinearExpression negate() {
        AB6.LinearExpression[] negatedTerms = new AB6.LinearExpression[size];
        for (int i = 0; i < size; i++) {
            negatedTerms[i] = terms[i].negate();
        }
        return new AB6.SumOfTerms(negatedTerms);
    }

    @Override
    public AB6.LinearExpression times(AB6.IntConst c) {
        if (c.isZero()) {
            return ZERO;
        }
        AB6.LinearExpression[] multipliedTerms = new AB6.LinearExpression[size];
        for (int i = 0; i < size; i++) {
            multipliedTerms[i] = terms[i].times(c);
        }
        return new AB6.SumOfTerms(multipliedTerms);
    }

    @Override
    public AB6.LinearExpression assignValue(IntVarConstMap varValues) {
        AB6.LinearExpression[] assignedTerms = new AB6.LinearExpression[size];
        boolean allConstant = true;

        for (int i = 0; i < size; i++) {
            assignedTerms[i] = terms[i].assignValue(varValues);
            if (!(assignedTerms[i] instanceof AB6.IntConst)) {
                allConstant = false;
            }
        }
        if (allConstant) {
            return sumAllConstants(assignedTerms);
        }

        return combineAndSimplifyTerms(assignedTerms);
    }

    private AB6.IntConst sumAllConstants(AB6.LinearExpression[] terms) {
        AB6.IntConst sum = ZERO;

        for (AB6.LinearExpression term : terms) {
            sum = sum.plus((AB6.IntConst) term);
        }
        return sum;
    }

    @Override
    public AB6.IntVarIterator iterator() {
        return new AB6.SumOfTermsIterator(terms);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        AB6.IntVarTerm[] varTerms = new AB6.IntVarTerm[size];
        AB6.IntConst constant = ZERO;
        int varTermCount = 0;


        for (AB6.LinearExpression term : terms) {
            if (term instanceof AB6.IntVarTerm) {
                varTerms[varTermCount++] = (AB6.IntVarTerm) term;
            } else if (term instanceof AB6.IntConst) {
                constant = constant.plus((AB6.IntConst) term);
            }
        }

        appendTerms(sb, varTerms, varTermCount);
        appendConstant(sb, constant);
        return sb.length() > 0 ? sb.toString().replaceAll("\\s", "") : "0";
    }

    private void appendTerms(StringBuilder sb, AB6.IntVarTerm[] varTerms, int varTermCount) {
        boolean isFirst = true;

        for (int i = 0; i < varTermCount; i++) {
            AB6.IntVarTerm varTerm = varTerms[i];
            AB6.IntConst coeff = varTerm.getCoeff();

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

            AB6.IntConst absCoeff = coeff.lessThan(ZERO) ? coeff.negate() : coeff;
            if (!absCoeff.equals(ONE)) {
                sb.append(absCoeff);
            }
            sb.append(varTerm.getVar());
            isFirst = false;
        }
    }

    private void appendConstant(StringBuilder sb, AB6.IntConst constant) {
        if (!constant.isZero()) {
            if (sb.length() > 0) {
                sb.append(constant.lessThan(ZERO) ? " - " : " + ");
            }
            AB6.IntConst absConstant = constant.lessThan(ZERO) ? constant.negate() : constant;
            sb.append(absConstant);
        }
    }

    private AB6.LinearExpression combineAndSimplifyTerms(AB6.LinearExpression[] newTerms) {


        AB6.IntVarTerm[] varTerms = new AB6.IntVarTerm[newTerms.length];
        AB6.IntConst constant = ZERO;
        int varTermCount = 0;

        for (AB6.LinearExpression term : newTerms) {
            if (term instanceof AB6.IntVarTerm compareVarTerm) {
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
            } else if (term instanceof AB6.IntConst) {
                constant = constant.plus((AB6.IntConst) term);
            }
        }

        if (varTermCount == 0) {
            return constant;
        } else if (varTermCount == 1 && constant.isZero()) {
            return varTerms[0];
        } else {
            AB6.LinearExpression[] resultTerms = new AB6.LinearExpression[varTermCount + (constant.isZero() ? 0 : 1)];
            System.arraycopy(varTerms, 0, resultTerms, 0, varTermCount);
            if (!constant.isZero()) {
                resultTerms[varTermCount] = constant;
            }
            return new AB6.SumOfTerms(resultTerms);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AB6.SumOfTerms that = (AB6.SumOfTerms) o;
        return size == that.size && Arrays.equals(terms, that.terms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, Arrays.hashCode(terms));
    }
}

// TODO: define further classes, if needed, either here or in a separate file.
class SumOfTermsIterator implements AB6.IntVarIterator {
    private final AB6.LinearExpression[] terms;
    private int currentTermIndex;
    private AB6.IntVarIterator currentIterator;

    SumOfTermsIterator(AB6.LinearExpression[] terms) {
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
    public AB6.IntVar next() {
        if (hasNext()) {
            return currentIterator.next();
        }
        return null;
    }
}
