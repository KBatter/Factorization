import java.util.ArrayList;

/**
 * Superclass for all solvers.
 * This defines the behavior for getTime and getFactors, to avoid unnecessary code duplication.
 */

public abstract class Solver {
    protected ArrayList<Integer>[] factorArray;
    protected long startTime = 0;
    protected long endTime = -1;
    protected final int MAX_VALUE = 100000;

    protected void solve() {}

    /**
     * This method gets the factors associated with a number, index.
     * @param index The integer whose factors should be returned
     * @return      An Integer array containing the factors of the number
     * @throws InvalidIndexException    if the index given is out of bounds
     */
    public ArrayList<Integer> getFactorsOf(int index) throws InvalidIndexException {
        if (index < 2) {
            throw new InvalidIndexException("Index too small for solver.");
        } else if (index > MAX_VALUE) {
            throw new InvalidIndexException("Index too large for solver.");
        } else {
            ArrayList<Integer> result;

            if(factorArray == null) {
                solve();
            }

            result = factorArray[index];

            return result;
        }
    }

    /**
     * This method returns the time it took to factor every number in seconds.
     * @return  the solving time in seconds (float).
     * @throws RuntimeException if the solver has not been run yet.
     */
    public float getSolveTime() throws RuntimeException {
        float result = (float) ((endTime - startTime) / 1000000000);

        if(result < 0) {
            throw new RuntimeException();
        }

        return result;
    }
}
