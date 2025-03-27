package Factorization;
import java.util.ArrayList;
import java.util.HashMap;

public class SingleThreadedSolver extends Solver {
    /**
     * This method generates a HashMap which stores each integer from 2 to 100,000
     * and their divisors. This is accomplished using a brute force algorithm.
     * This method is called when the factors of a number are required.
     */
    public void solve() {
        System.out.println("Generating factors. Please wait...");

        startTime = System.nanoTime();

        int arrayLength = MAX_VALUE + 1;

        ArrayList<Integer> factors;
        double sqrtResult;
        int step;

        for(int i = 2; i < arrayLength; i++) {
            factors = new ArrayList<>();

            sqrtResult = Math.sqrt(i);
            // All factors of an odd number are also odd
            step = i % 2 == 0? 1:2;
            for(int j = 1; j <= sqrtResult; j += step) {
                if(i % j == 0) {
                    factors.add(j);
                    if(i / j != j) {
                        factors.add(i / j);
                    }
                }
            }

            factorArray.put(i, factors);
        }
        endTime = System.nanoTime();
    }
}
