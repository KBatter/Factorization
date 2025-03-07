import java.util.ArrayList;

public interface Solver {
    public ArrayList<Integer> getFactorsOf(int index) throws InvalidIndexException;
    public float getSolveTime();
}
