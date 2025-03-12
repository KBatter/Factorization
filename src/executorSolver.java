import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class executorSolver extends Solver {
    protected void solve() throws RuntimeException {
        System.out.println("Generating factors. Please wait...");

        startTime = System.nanoTime();

        int arrayLength = MAX_VALUE + 1;
        factorArray = new ArrayList[arrayLength];
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        // Calculate amount of work each thread must do
        int chunkSize = arrayLength / threadCount;
        Future<?>[] futures = new Future<?>[threadCount];

        for(int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = (i == threadCount - 1)? arrayLength:(start + chunkSize);

            futures[i] = executor.submit(() -> generateFactors(start, end));
        }

        for(Future<?> future: futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("An error occurred while executing the solver. Please try again.");
            }
        }

        executor.shutdown();

        endTime = System.nanoTime();
    }

    private void generateFactors(int start, int end) {
        ArrayList<Integer> factors;

        for(int i = start; i < end; i++) {
            factors = new ArrayList<>();
            factors.add(i);

            for(int j = i / 2; j > 1; j--) {
                if(i % j == 0) {
                    factors.add(j);
                }
            }

            factors.add(1);

            factorArray[i] = factors;
        }
    }
}
