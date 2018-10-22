package java8lambda.chapter01;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

/**
 * 소프트 웨어를 설계하는 방식에는 두 가지가 있다.
 * 하나는 설계를 간결하게 해서 결함 자체를 없애는 것이며, 다른 하나는 가능한 많은 기능을 집어넣어 결함이 발생할 가능성을 없애는 것이다.
 * 물론 첫번째 방법이 훨씬 어렵다.
 * - 찰스 앤서니, 리처드 홀 -
 *
 */
public class C02_Element {
    private static void pickName(final List<String> names, final String startingLetter) {
        String foundName = null;  // null 초기화, 예외의 문제가 될 수 있다.

        for (String name : names) {
            if (name.startsWith(startingLetter)) {
                foundName = name;
                break;
            }
        }

        if (foundName != null) System.out.println(foundName);
        else System.out.println("No name found");
    }

    /**
     * 람다 사용하기
     */
    private static void pickName2(final List<String> names, final String startingLetter) {
        final Optional<String> foundName = names.stream()
                .filter(name -> name.startsWith(startingLetter))
                .findFirst();
        System.out.println(foundName.orElse("No name found"));
        foundName.ifPresent(name -> System.out.println("Hello " + name));

    }

    /**
     * 앨리먼트 조인하기
     */
    private static void pickName3(final List<String> names, final String startingLetter) {
        for (String name : names) System.out.print(name + ", ");
        System.out.println();

        /** 마지막 콤마를 없애기 위해 수정 **/
        for (int i = 0; i < names.size()-1; i++) System.out.print(names.get(i) + ", ");
        if (names.size() > 1) System.out.println(names.get(names.size()-1));
        /** 잘 나오지만 더럽다 **/

        /** 람다 사용 : StringJointer class **/
        System.out.println(String.join(",", names));
        /** map, filter, reduce 등을 이용하자.
         * reduce로 하나의 스트링으로 바꿀 수 있지만 collect()라는 Convenience method를 이용하여 하보자.
         * 값을 타깃으로 값을 모으는 리듀스의 다른 형태이다.
         */
        System.out.println(names.stream().map(String::toUpperCase)
                                        .collect(joining(", ")));
    }



    public static void main(String[] args) {

    }
}
