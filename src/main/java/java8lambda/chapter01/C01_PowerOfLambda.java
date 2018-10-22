package java8lambda.chapter01;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * p.5
 */
public class C01_PowerOfLambda {
    private final static List<BigDecimal> prices = Arrays.asList(new BigDecimal("10"), new BigDecimal("30"),
            new BigDecimal("17"), new BigDecimal("20"), new BigDecimal("15"), new BigDecimal("18"),
            new BigDecimal("45"), new BigDecimal("12"));


    /**
     *
     * 1. 기존방법을 한 번 보자.
     * 가격이 $20보다 높으면 10%할인은 한다고 가정하자.
     * 그리고 총 할인된 price의 전체 합계를 출력한다 해보자.
     */
    private static void discountImperative() {
        BigDecimal totalOfDiscountedPrices = BigDecimal.ZERO;

        for (BigDecimal price : prices) {
            if (price.compareTo(BigDecimal.valueOf(20)) > 0)
                totalOfDiscountedPrices = totalOfDiscountedPrices.add(price.multiply(BigDecimal.valueOf(0.9)));
        }
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }

    private static void discountFuncional(List<BigDecimal> priceList) {
        final BigDecimal totalOfDiscountedPrices = priceList.stream()
                .filter(p -> p.compareTo(BigDecimal.valueOf(20)) > 0)
                .map(p -> p.multiply(BigDecimal.valueOf(0.9)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }

    public static void main(String[] args) {
        discountImperative();
        discountFuncional(prices);
    }
}
