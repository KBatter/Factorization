import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class executorSolver extends Solver {
    protected void solve() {
        System.out.println("Generating factors. Please wait...");

        factorArray = new ArrayList[MAX_VALUE + 1];

        startTime = System.nanoTime();
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        int chunkSize = (MAX_VALUE + 1) / threadCount;
        Future<?>[] futures = new Future<?>[threadCount];

        for(int i = 0; i < threadCount; i++) {
            int start = i * chunkSize;
            int end = (i == threadCount - 1)? (MAX_VALUE + 1):(start + chunkSize);

            futures[i] = executor.submit(() -> generateFactors(start, end));
        }

        for(Future<?> future: futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
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

            // Note that any number > n / 2 cannot possibly be a factor of n.
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
