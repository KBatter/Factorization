package Factorization;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorSolver extends Solver {

    /**
     * Generates the factors of every integer from 2 to MAX_VALUE,
     * storing them in an array for quick, repeated access.
     * @throws RuntimeException if an error occurs while executing threads.
     */
    public void solve() throws RuntimeException {
        System.out.println("Generating factors. Please wait...");

        startTime = System.nanoTime();

        int arrayLength = MAX_VALUE + 1;

        // Current testing on my computer has this as the fastest thread count.
        int threadCount = Runtime.getRuntime().availableProcessors() + 10;
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {

            // Calculate amount of work each thread must do
            int chunkSize = arrayLength / threadCount;
            // Create an array of futures
            // to keep track of what work must be completed
            Future<?>[] futures = new Future<?>[threadCount];

            for (int i = 0; i < threadCount; i++) {
                int start = i * chunkSize;
                int end = (i == threadCount - 1) ? arrayLength : (start + chunkSize);
                // Submit a lambda (Runnable) to the list of futures (work)
                futures[i] = executor.submit(() -> generateFactors(start, end));
            }

            for (int i = 0; i < threadCount; i++) {
                try {
                    // Work done (for that thread...)
                    futures[i].get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException("An error occurred while executing the solver. Please try again.");
                }
            }

            executor.shutdown();
        }

        endTime = System.nanoTime();
    }

    /**
     * Generates a list of factors for each integer from [start, end).
     * This is primarily a helper method for solve().
     * @param start The first integer to get the factors of
     * @param end   The index to stop before
     */
    private void generateFactors(int start, int end) {
        ArrayList<Integer> factors;
        double sqrtResult;
        int step, divisionResult;

        for(int i = start; i < end; i++) {
            factors = new ArrayList<>();
            sqrtResult = Math.sqrt(i);
            step = i % 2 == 0? 1:2;

            for(int j = 1; j <= sqrtResult; j += step) {
                if(i % j == 0) {
                    factors.add(j);
                    divisionResult = i / j;
                    if(divisionResult != j) {
                        factors.add(divisionResult);
                    }
                }
            }

            factorArray.put(i, factors);
        }
    }
}
