import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Создаю потоки...");
        System.out.println("Вариант с invokeAll");
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<MyCallable> myCallableList = fillCallableList(4, 5);
        List<Future<Integer>> resultList = null;

        try {
            resultList = threadPool.invokeAll(myCallableList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Все задачи выполнились!");
        threadPool.shutdown();

        System.out.println("Результаты: ");
        for (int i = 0; i < resultList.size(); i++) {
            System.out.printf("Поток %d вывел %d приветствий\n", i + 1, resultList.get(i).get());
        }

        System.out.println("\nВариант с invokeAny");
        threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        myCallableList = fillCallableList(5, 8);
        Integer integerFuture = threadPool.invokeAny(myCallableList);
        threadPool.shutdownNow();
        System.out.println("Один из потоков завершился и вывел " + integerFuture + " приветствия");
    }

    public static List<MyCallable> fillCallableList(int count, int messageMaxCount) {
        List<MyCallable> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= count; i++) {
            MyCallable myCallable = new MyCallable(Integer.toString(i),
                    2000 + random.nextInt(1000),
                    random.nextInt(messageMaxCount) + 1);
            result.add(myCallable);
        }
        return result;
    }
}
