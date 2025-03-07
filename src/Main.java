import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    private static Solver factorizationSolver;

    /**
     * The main method for this project. Run this in order to run the various solvers.
     * @param args  Command line arguments (none currently supported)
     * @throws IOException For errors involving the buffered reader.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Hello! Please select an option from the following.");
        showMenu();

        boolean continueLoop = true;
        int selectedOption;

        while(continueLoop) {
            System.out.print("\nMake a selection: ");
            try {
                selectedOption = Integer.parseInt(stdin.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer.");
                continue;
            }
            switch(selectedOption) {
                case 0 -> continueLoop = false;
                case 1 -> {
                    singleThreadedSolution();
                    showMenu();
                }
                case 2 -> executorSolution();
                case 3 -> streamSolution();
                case 4 -> distributedSolution();
                case 5 -> timer();
                default -> System.out.println("Please select a valid option.");
            }
        }

        System.out.println("Exiting program. Goodbye!");
    }

    private static void showMenu() {
        System.out.println("""
                1) Single Threaded Solver
                2) Executor Solver
                3) Stream Solver
                4) Distributed Solver
                5) Timer
                0) Quit""");
    }

    /**
     * Utilizes a single threaded solver to compute the factors of all integers from 2 to 100,000.
     * Upon calling, this function takes some time to return control to the user.
     * Afterwards, the function allows the user to view the factors of any integer from 2 to 100,000
     * via a menu.
     * @throws IOException  If buffered reader throws an IOException
     */
    private static void singleThreadedSolution() throws IOException {
        if(!(factorizationSolver instanceof singleThreadedSolver)) {
            factorizationSolver = new singleThreadedSolver();
        }

        System.out.println("Welcome to the Single Threaded Factorizer!\n" +
                "Please enter a number and we will return the factors of that number and a note if that number is prime or not. Enter 0 to return to the main menu.");
        boolean continueLoop = true;
        int toFactor;

        ArrayList<Integer> factors;

        while(continueLoop) {
            System.out.print("\nPlease enter a number to get the factors of, or 0 to quit: ");

            try {
                toFactor = Integer.parseInt(stdin.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer.");
                continue;
            }

            if(toFactor == 0) {
                System.out.println("Returning to main menu...");
                continueLoop = false;
            } else {
                try {
                    factors = factorizationSolver.getFactorsOf(toFactor);
                    System.out.println("Factors of " + toFactor);
                    System.out.println(factors);
                    if(factors.size() == 2) {
                        System.out.println(toFactor + " is a prime number.");
                    } else {
                        System.out.println(toFactor + " is not a prime number.");
                    }
                } catch (InvalidIndexException e) {
                    System.out.println(e);
                }
            }
        }
    }

    /**
     * Currently non-functional.
     */
    private static void executorSolution() {
        System.out.println("This solver is not implemented yet.");
    }

    /**
     * Currently non-functional.
     */
    private static void streamSolution() {
        System.out.println("This solver is not implemented yet.");
    }

    /**
     * Currently non-functional.
     */
    private static void distributedSolution() {
        System.out.println("This solver is not implemented yet.");
    }

    /**
     * This function displays the time it took for the solver to execute on its last run in seconds.
     * Attempting to run it before running a solver will print an error without crashing.
     */
    private static void timer() {
        try {
            try {
                System.out.println("The previous factorization took " + factorizationSolver.getSolveTime() + " seconds.");
            } catch (RuntimeException e) {
                System.out.println("Please run a solver first.");
            }
        } catch(NullPointerException e) {
            System.out.println("Please select a solver first.");
        }
    }
}