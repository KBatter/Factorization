package Factorization;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class StreamSolver extends Solver {
    public void solve() {
        startTime = System.nanoTime();
        IntStream.rangeClosed(2, MAX_VALUE)
                .parallel()
                .forEach(this::generateFactors);
        endTime = System.nanoTime();
    }

    /**
     * Generates the factors of val and stores them in a hashmap
     * @param val
     */
    private void generateFactors(int val) {
        ArrayList<Integer> factors;
        double sqrtResult;
        int step, divisionResult;

        factors = new ArrayList<>();
        sqrtResult = Math.sqrt(val);
        step = val % 2 == 0? 1:2;

        for(int j = 1; j <= sqrtResult; j += step) {
            if(val % j == 0) {
                factors.add(j);
                divisionResult = val / j;
                if(divisionResult != j) {
                    factors.add(divisionResult);
                }
            }
        }

        factorArray.put(val, factors);
    }
}
