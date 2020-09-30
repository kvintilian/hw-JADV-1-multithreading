import java.util.concurrent.RecursiveTask;

public class Adder extends RecursiveTask<Long> {

    private final int[] array;
    private final int start;
    private final int end;

    public Adder(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        final int diff = end - start;
        switch (diff) {
            case 0:
                return 0L;
            case 1:
                return (long) array[start];
            case 2:
                return (long) array[start] + array[start + 1];
            default:
                return forkTasksAndGetResult();
        }
    }

    private Long forkTasksAndGetResult() {
        final int middle = (end - start) / 2 + start;
        Adder subAdder1 = new Adder(array, start, middle);
        Adder subAdder2 = new Adder(array, middle, end);
        invokeAll(subAdder1, subAdder2);
        return subAdder1.join() + subAdder2.join();
    }
}
