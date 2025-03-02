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
    private final int n;
    private int count = 0; // Start from 0 for correct sequence
    private final NumberPrinter printer;
    private final Object lock = new Object();

    public ThreadController(int n, NumberPrinter printer) {
        this.n = n;
        this.printer = printer;
    }

    public void printZero() {
        synchronized (lock) {
            for (int i = 0; i < n; i++) {
                while (count % 2 != 0) { // Zero prints when count is even
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                printer.printZero();
                count++;
                lock.notifyAll();
            }
        }
    }

    public void printEven() {
        synchronized (lock) {
            for (int i = 2; i <= n; i += 2) {
                while (count % 4 != 3) { // Ensures even numbers print correctly
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                printer.printEven(i);
                count++;
                lock.notifyAll();
            }
        }
    }

    public void printOdd() {
        synchronized (lock) {
            for (int i = 1; i <= n; i += 2) {
                while (count % 4 != 1) { // Ensures odd numbers print correctly
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                printer.printOdd(i);
                count++;
                lock.notifyAll();
            }
        }
    }
}

public class Q6a {
    public static void main(String[] args) {
        int n = 5;
        NumberPrinter printer = new NumberPrinter();
        ThreadController controller = new ThreadController(n, printer);

        Thread zeroThread = new Thread(controller::printZero);
        Thread evenThread = new Thread(controller::printEven);
        Thread oddThread = new Thread(controller::printOdd);

        zeroThread.start();
        evenThread.start();
        oddThread.start();
    }
}