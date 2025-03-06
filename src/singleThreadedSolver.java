import java.util.HashMap;
import java.util.ArrayList;

public class singleThreadedSolver implements Solver {
    // On boot-up, generate a map of all integers from 2 - 1,000,000 to their factors...
    HashMap<Integer, ArrayList<Integer>> factorMap = new HashMap<>(1000000);
    long startTime;
    long endTime;

    public void solve() {
        startTime = System.nanoTime();
        for(int i = 2; i < 1000001; i++) {
            ArrayList<Integer> factors = new ArrayList<>();
            // First, add the trivial factors, 1 and the number itself.
            factors.add(1);
            factors.add(i);

            for(int j = i / 2; j > 1; j--) {
                // For now, use brute force approach
                if(i % j == 0) {
                    factors.add(j);
                }
            }

            factorMap.put(i, factors);
        }
        endTime = System.nanoTime();
    }

    public ArrayList<Integer> getResults(int index) {
        return factorMap.get(index);
    }

    public int getSolveTime() {
        return (int) (endTime - startTime);
    }
}
