public class MyThread extends Thread {
    private final int timeout;

    public MyThread(ThreadGroup threadGroup, String name, int timeout) {
        super(threadGroup, name);
        this.timeout = timeout;
    }

    @Override
    public void run() {
        while (true) {
            if (isInterrupted()) return;
            System.out.println("Я поток " +Thread.currentThread().getName() + ". Всем привет!");
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
