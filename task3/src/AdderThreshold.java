import java.util.concurrent.RecursiveTask;

public class AdderThreshold extends RecursiveTask<Long> {

    private final int[] array;
    private final int start;
    private final int end;
    private final int threshold;

    public AdderThreshold(int[] array, int start, int end, int threshold) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    @Override
    protected Long compute() {
        if (end - start <= threshold) {
            long sum = 0;
            for (int i = start; i < end; ++i)
                sum += array[i];
            return sum;
        } else {
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
