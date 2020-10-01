import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {


    final private String name;
    final private int timeout;
    final private int messageCount;

    public MyCallable(String name, int timeout, int messageCount) {
        this.timeout = timeout;
        this.name = name;
        this.messageCount = messageCount;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < messageCount; i++) {
            System.out.println("Я задача " + name + ". Всем привет!");
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Я задача " + name + ". Я завершилась!");
        return messageCount;
    }
}
