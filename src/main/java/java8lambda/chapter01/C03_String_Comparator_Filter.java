package java8lambda.chapter01;

/**
 * 모든 것은 가능한 간단해야 하지만, 지나치게 간단하면 안된다. - 알버트 아인슈타인 -
 */
public class C03_String_Comparator_Filter {
    static void iterateString() {
        final String str = "w00t";

        /** 문자열이 아니라 숫자가 나온다. 이유는 chars() 메서드가 Characters의 스트림 대신 문자를 표현하는 int의 스트림을 리턴했기 때문. **/
        str.chars().forEach(ch-> System.out.println(ch));
        /** 메서드 레퍼런스를 사용하여 파라미터 라우팅(parameter routing)을 할 수 있다.
         *  인스턴스가 필요할 때(동적)와 static(정적) 일때 파라미터를 연결하는 방식이 다르다. **/
        str.chars().forEach(System.out::println);

        /** 형변환을 하여 케릭터가 보이도록 한다.**/
        str.chars().forEach(C03_String_Comparator_Filter::printChar);
        str.chars().mapToObj(ch -> Character.valueOf((char)ch)).forEach(System.out::println);

        /** 필터링도 해보자 **/
        str.chars().filter(ch -> Character.isDigit(ch)).forEach(ch -> printChar(ch));
        str.chars().filter(Character::isDigit).forEach(C03_String_Comparator_Filter::printChar);

        /** 메서드 레퍼런스는 일반적인 파라미터 라우팅을 제거하도록 해준다.**/

    }

    private static void printChar(int aChar) {
        System.out.println((char) aChar);
    }

    public static void main(String[] args) {
        iterateString();
    }
}
