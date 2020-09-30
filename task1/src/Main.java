public class Main {
    public static void main(String[] args) throws InterruptedException {

        final ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
        System.out.println("Создаю потоки...");
        Thread myThread1 = new MyThread(threadGroup, "1", 2300);
        Thread myThread2 = new MyThread(threadGroup, "2", 2500);
        Thread myThread3 = new MyThread(threadGroup, "3", 2100);
        Thread myThread4 = new MyThread(threadGroup, "4", 2900);

        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();

        Thread.sleep(15000);

        System.out.println("Завершаю все потоки.");
        threadGroup.interrupt();
    }
}
