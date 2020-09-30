import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int[] randomArray = generateIntArray(500000000, 0, 100000);
        CalcSingleThread(randomArray);
        CalcForkJoin(randomArray);
        CalcForkJoin2(randomArray);
    }

    public static void CalcSingleThread(int[] intArr) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (Integer i : intArr) {
            sum += i;
        }
        long avg = sum/intArr.length;
        long end = System.currentTimeMillis();
        System.out.printf("Один поток: \n  - Сумма: %d \n  - Среднее: %d \n  - Время: %d \n", sum, avg, end - start);
    }

    public static void CalcForkJoin(int[] intArr) {
        Adder adder = new Adder(intArr, 0, intArr.length);
        long start = System.currentTimeMillis();
        final Long sum = new ForkJoinPool().invoke(adder);
        long avg = sum/intArr.length;
        long end = System.currentTimeMillis();
        System.out.printf("ForkJoin с суммированием парами: \n  - Сумма: %d \n  - Среднее: %d \n  - Время: %d \n", sum, avg, end - start);
    }

    public static void CalcForkJoin2(int[] intArr) {
        AdderThreshold adder = new AdderThreshold(intArr, 0, intArr.length, intArr.length/Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        final Long sum = new ForkJoinPool().invoke(adder);
        long avg = sum/intArr.length;
        long end = System.currentTimeMillis();
        System.out.printf("ForkJoin с суммированием пачками: \n  - Сумма: %d \n  - Среднее: %d \n  - Время: %d \n", sum, avg, end - start);
    }

    public static int[] generateIntArray(int size, int min, int max) {
        int[] ints = new int[size];
        Random random = new Random();
        System.out.println("Создаем целочисленный массив длиной " + size);
        for (int i = 0; i < size; i++) {
            ints[i] = random.nextInt((max - min) + 1) + min;
        }
        return ints;
    }
}
