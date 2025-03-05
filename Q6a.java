class NumberPrinter {
    // Method to print zero
    public void printZero() {
        System.out.print("0");
    }

    // Method to print even numbers
    public void printEven(int num) {
        System.out.print(num);
    }

    // Method to print odd numbers
    public void printOdd(int num) {
        System.out.print(num);
    }
}

class ThreadController {
    private final int n; // Maximum number to print
    private int count = 0; // Shared counter to synchronize thread execution
    private final NumberPrinter printer; // Reference to the NumberPrinter instance
    private final Object lock = new Object(); // Lock object for synchronization

    // Constructor to initialize the ThreadController with the given number and
    // printer
    public ThreadController(int n, NumberPrinter printer) {
        this.n = n;
        this.printer = printer;
    }

    // Method for ZeroThread to print '0' in sequence
    public void printZero() {
        synchronized (lock) {
            for (int i = 0; i < n; i++) {
                // Ensure '0' prints when count is even
                while (count % 2 != 0) {
                    try {
                        lock.wait(); // Wait until the condition is met
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                printer.printZero(); // Print '0'
                count++; // Increment count to allow number printing
                lock.notifyAll(); // Notify waiting threads
            }
        }
    }

    // Method for EvenThread to print even numbers
    public void printEven() {
        synchronized (lock) {
            for (int i = 2; i <= n; i += 2) {
                // Ensure even numbers print in sequence after '0'
                while (count % 4 != 3) {
                    try {
                        lock.wait(); // Wait until it's time to print an even number
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                printer.printEven(i); // Print the even number
                count++; // Increment count
                lock.notifyAll(); // Notify other threads
            }
        }
    }

    // Method for OddThread to print odd numbers
    public void printOdd() {
        synchronized (lock) {
            for (int i = 1; i <= n; i += 2) {
                // Ensure odd numbers print in sequence after '0'
                while (count % 4 != 1) {
                    try {
                        lock.wait(); // Wait until it's time to print an odd number
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                printer.printOdd(i); // Print the odd number
                count++; // Increment count
                lock.notifyAll(); // Notify other threads
            }
        }
    }
}

public class Q6a {

    /*
     * You are given a class NumberPrinter with three methods: printZero, printEven,
     * and printOdd.
     * These methods are designed to print the numbers 0, even numbers, and odd
     * numbers, respectively.
     * 
     * Task:
     * Create a ThreadController class that coordinates three threads:
     * 1. ZeroThread: Calls printZero to print 0s.
     * 2. EvenThread: Calls printEven to print even numbers.
     * 3. OddThread: Calls printOdd to print odd numbers.
     * 
     * These threads should work together to print the sequence "0102030405..." up
     * to a specified number n.
     * The output should be interleaved, ensuring that the numbers are printed in
     * the correct order.
     */

    /*
     * Approach:
     * Step 1: Define a NumberPrinter Class
     * - Contains three methods: printZero(), printEven(int num), and printOdd(int
     * num).
     * - These methods are responsible for printing 0, even numbers, and odd
     * numbers, respectively.
     * 
     * Step 2: Implement Thread Synchronization with ThreadController
     * - Maintain a shared counter `count` to track the sequence of numbers being
     * printed.
     * - Use a lock (Object lock) to synchronize thread execution and ensure correct
     * order.
     * 
     * Step 3: Create Methods for Each Thread
     * - **printZero() (ZeroThread)**
     * - Runs `n` times.
     * - Ensures `0` is printed at even values of `count`.
     * - Uses `wait()` to pause execution if the condition isn’t met.
     * - Calls `printZero()` and increments `count`, then notifies all threads.
     * 
     * - **printEven() (EvenThread)**
     * - Runs for all even numbers up to `n`.
     * - Waits until `count % 4 == 3`, ensuring correct interleaving.
     * - Calls `printEven(num)`, increments `count`, and notifies all threads.
     * 
     * - **printOdd() (OddThread)**
     * - Runs for all odd numbers up to `n`.
     * - Waits until `count % 4 == 1`, ensuring correct interleaving.
     * - Calls `printOdd(num)`, increments `count`, and notifies all threads.
     * 
     * Step 4: Launch Threads in Main Method
     * - Initialize `NumberPrinter` and `ThreadController`.
     * - Create and start three threads for `printZero`, `printEven`, and
     * `printOdd`.
     * 
     * Step 5: Thread Execution and Interleaved Printing
     * - The threads coordinate via `wait()` and `notifyAll()` calls.
     * - Ensures the correct sequence is printed: "0102030405..." up to `n`.
     */

    public static void main(String[] args) {
        int n = 5; // Define the maximum number to print
        NumberPrinter printer = new NumberPrinter(); // Create a NumberPrinter instance
        ThreadController controller = new ThreadController(n, printer); // Initialize ThreadController

        // Create three threads for printing zero, even, and odd numbers
        Thread zeroThread = new Thread(controller::printZero);
        Thread evenThread = new Thread(controller::printEven);
        Thread oddThread = new Thread(controller::printOdd);

        // Start the threads to begin execution
        zeroThread.start();
        evenThread.start();
        oddThread.start();
    }
}

// Output: 0102030405