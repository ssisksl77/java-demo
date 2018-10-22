package java8lambda.chapter01;

import java8lambda.chapter01.dto.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class C03_Comparator {
    public static void main(String[] args) {
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));

        /** 나이순 정렬을 해보자 sort()는 리스트를 수정하기 때문에 위험하다. sorted로 새로운 것을 리턴하자.
         * Comparator는 같으면 0, p1 > p2 면 양수, p1 < p2면 음수**/
        List<Person> ascendingAge =
                people.stream()
                    .sorted((p1, p2) -> p1.ageDifference(p2))
                    .collect(toList());
        /** 좀 더 간결하게 **/
        ascendingAge = people.stream().sorted(Person::ageDifference).collect(toList());
        /** 정렬 순서 바꿔보기 : 메서드 레퍼런서는 어떻게 해야 하지? **/
        Person.printPeople("Sorted in descending order by age: ",
                people.stream()
                    .sorted((p1, p2) -> p2.ageDifference(p1))
                    .collect(toList()));
        /** revered()를 이용하면 comparator가 반대로 숫자를 리턴하게 된다.**/
        Comparator<Person> compareAscending = (p1, p2) -> p1.ageDifference(p2);
        Comparator<Person> compareDescending = compareAscending.reversed();

        Person.printPeople("오름차순 정렬", people.stream().sorted(compareAscending).collect(toList()));
        Person.printPeople("내림차순 정렬:", people.stream().sorted(compareDescending).collect(toList()));

    }
}


