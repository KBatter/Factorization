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
            throw new InvalidIndexException("This index is too small for the solver. Valid indices are greater than or equal to 2, but less than or equal to 100,000.");
        } else if (index > MAX_VALUE) {
            throw new InvalidIndexException("This index is too large for the solver. Valid indices are greater than or equal to 2, but less than or equal to 100,000.");
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
            throw new RuntimeException("A solver has not been run yet. Please run a solver first.");
        }

        return result;
    }
}
