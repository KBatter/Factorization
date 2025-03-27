package Factorization;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The main class for this project.
 * This contains the driving method, main, which starts the program
 * and allows the user to access the menu.
 */
public class Main {
    private static final BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
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
            System.out.print("\nPlease make a selection now: ");
            try {
                selectedOption = Integer.parseInt(stdin.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please input an integer.");
                continue;
            }
            switch(selectedOption) {
                case 0 -> continueLoop = false;
                case 1, 2, 3, 4 -> dispatch(selectedOption);
                case 5 -> timer();
                default -> System.out.println("Please select a valid option.");
            }
        }

        System.out.println("Exiting program. Goodbye!");
    }

    /**
     * Dispatch method for the menu. Since methods getFactors and showMenu
     * were called after every solver initialization, they were put in
     * a helper method to reduce code duplication.
     * @param code  The number inputted by the user.
     * @throws IOException  For errors involving the buffered reader.
     */
    private static void dispatch(int code) throws IOException {
        switch(code) {
            case 1 -> {
                System.out.println("Welcome to the Single Threaded Factorizer!\n" +
                        "Please enter a number and we will return the factors of that number and a note if that number is prime or not. Enter 0 to return to the main menu.");
                factorizationSolver = new SingleThreadedSolver();
            }
            case 2 -> {
                System.out.println("Welcome to the Executor Factorizer!\n" +
                        "Please enter a number and we will return the factors of that number and a note if that number is prime or not. Enter 0 to return to the main menu.");
                factorizationSolver = new ExecutorSolver();
            }
            case 3 -> {
                System.out.println("Welcome to the Stream Factorizer!\n" +
                        "Please enter a number and we will return the factors of that number and a note if that number is prime or not. Enter 0 to return to the main menu.");
                factorizationSolver = new StreamSolver();
            }
            case 4 -> {
                System.out.println("This solver is not implemented yet.");
                return;
            }
        }
        getFactors();
        showMenu();
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

    private static void getFactors() throws IOException {
        try {
            factorizationSolver.solve();
        } catch (RuntimeException e) {
            return;
        }

        boolean continueLoop = true;
        int toFactor;

        ArrayList<Integer> factors;

        while (continueLoop) {
            System.out.print("\nPlease enter an integer from 2 - 100,000 to get the factors of, or 0 to quit: ");

            try {
                toFactor = Integer.parseInt(stdin.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer.");
                continue;
            }

            if (toFactor == 0) {
                System.out.println("Returning to main menu...");
                continueLoop = false;
            } else {
                try {
                    factors = factorizationSolver.getFactorsOf(toFactor);
                    System.out.println("Factors of " + toFactor);
                    System.out.println(factors);
                    if (factors.size() == 2) {
                        System.out.println(toFactor + " is a prime number.");
                    } else {
                        System.out.println(toFactor + " is not a prime number.");
                    }
                } catch (InvalidIndexException | RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * This function displays the time it took for the solver to execute on its last run in seconds.
     * Attempting to run it before running a solver will print an error without crashing.
     */
    private static void timer() {
        try {
            try {
                System.out.println("The previous factorization took " + factorizationSolver.getSolveTime() + " nanoseconds.");
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        } catch(NullPointerException e) {
            System.out.println("Please select a solver first.");
        }
    }
}