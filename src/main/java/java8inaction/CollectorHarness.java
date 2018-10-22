package java8inaction;

public class CollectorHarness {
    public static void main(String[] args) {
        long start = System.nanoTime();
        PrimeNumbersCollector.partitionPrimesWithCustomCollector(1_000_000);
        long duration = (System.nanoTime() - start) / 1_000_000;

        System.out.println(duration);
    }
}
