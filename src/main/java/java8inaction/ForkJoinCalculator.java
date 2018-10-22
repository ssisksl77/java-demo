package java8inaction;

public class ForkJoinCalculator extends java.util.concurrent.RecursiveTask<Long>{
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);

    }

    public ForkJoinCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    /**
     * RecursiveTask의 추상메서드 오버라이드
     * @return
     */
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinCalculator leftTask = new ForkJoinCalculator(numbers, start, start + length / 2);
        leftTask.fork(); // ForkJoinPool의 다른 스레드로 새로 생성한 테스크를 비동기로 실행한다.
        ForkJoinCalculator rightTask = new ForkJoinCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute(); // 두 번째 서브테스트를 동기 실행한다. 추가 분할이 일어날 수 있다.
        Long leftResult = leftTask.join(); // 첫번째 서브태스크의 결과를 가져옴. 아직이면 기다림
        return leftResult + rightResult;

    }

    /**
     * 더 분할 할 수 없으면 포문으로 계산.
     * @return
     */
    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
