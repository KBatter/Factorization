import java.util.ArrayList;

public class singleThreadedSolver implements Solver {
    private ArrayList<Integer>[] factorArray;
    private long startTime = 0;
    private long endTime = -1;
    private final int MAX_VALUE = 100000;

    /**
     * This method generates a HashMap which stores each integer from 2 to 1,000,000
     * and their divisors. This is accomplished using a brute force algorithm.
     * This method is called when the factors of a number are required.
     */
    private void solve() {
        System.out.println("Generating factors. Please wait...");
        startTime = System.nanoTime();
        factorArray = new ArrayList[MAX_VALUE + 1];
        ArrayList<Integer> factors;

        for(int i = 2; i <= MAX_VALUE; i++) {
            factors = new ArrayList<>();
            factors.add(i);

            // Note that any number > n / 2 cannot possibly be a factor of n.
            for(int j = i / 2; j > 1; j--) {
                if(i % j == 0) {
                    factors.add(j);
                }
            }

            factors.add(1);

            factorArray[i] = factors;
        }

        endTime = System.nanoTime();
    }

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
