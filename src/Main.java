import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    private static Solver solution;

    public static void main(String[] args) throws IOException {
        boolean loop = true;
        int selection;

        System.out.println("Hello! Please select an option from the following.");
        System.out.println("""
                1) Single Threaded Solver
                2) Executor Solver
                3) Stream Solver
                4) Distributed Solver
                5) Timer
                0) Quit""");

        while(loop) {
            System.out.print("Please make your selection now: ");
            selection = Integer.parseInt(stdin.readLine().trim());
            switch(selection) {
                case 0 -> loop = false;
                case 1 -> option1();
                case 2 -> option2();
                case 3 -> option3();
                case 4 -> option4();
                case 5 -> option5();
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    private static void option1() {
        solution = new singleThreadedSolver();
        solution.solve();


    }

    private static void option2() {

    }

    private static void option3() {

    }

    private static void option4() {

    }

    private static void option5() {

    }
}
