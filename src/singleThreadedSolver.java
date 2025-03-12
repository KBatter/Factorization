import java.util.ArrayList;

public class singleThreadedSolver extends Solver {
    /**
     * This method generates a HashMap which stores each integer from 2 to 1,000,000
     * and their divisors. This is accomplished using a brute force algorithm.
     * This method is called when the factors of a number are required.
     */
    protected void solve() {
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
}
