import java.util.concurrent.*;
import java.util.Scanner;

class FactorialTask implements Callable<Long> {
    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    public Long call() {
        return calculateFactorial(number);
    }

    private long calculateFactorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

class PR3Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть число, щоб обчислити його фактор: ");
        int number = scanner.nextInt();

        ExecutorService executor = Executors.newFixedThreadPool(1); // Single-threaded pool for simplicity
        Future<Long> future = executor.submit(new FactorialTask(number));

        try {
            System.out.println("Фактор " + number + " це " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Помилка при обчисленні коефіцієнта: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }
}
