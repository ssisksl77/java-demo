package visitor;

/**
 * 구문트리를 검색하여 수식을 평가하거나 수식을 그대로 문자열로 하거나 하는 작업을 생각해보자.
 * BNF로 표현했다고 치자.
 *
 * 이 프로그램과 설계에는 문제가 없음.
 * 변경에 대한 유연성에 대해서도 다음과 같은 경우에 따라 각각 가산해서 대응할 수 있으므로 Visitor 패턴을 사용하는 것은
 * 객체지향 프레임워크로서는 더할 나위 없는 방법이라고 말할 수 있다.
 * - 식의 증가에 대해 (뺄셈의 추가)
 *  a. Visitor의 메소드를 늘린다.
 *  b. 식의 인터페이스를 계승하는 클래스를 늘린다.
 * - 식에 대한 처리 종류의 증가에 대해 (JSON으로 변경)
 *  a. Visitor를 계승하는 클래스를 늘린다.
 */

interface Visitor<N,R> {
    R plus(Expr<N> e1, Expr<N> e2); // 덧셈 식을 위한 메소드
    R square(Expr<N> e); // 제곱 식을 위한 메소드
    R number(N n); // 숫자를 위한 메소드
}

interface Expr<N> {
    <R> R accept(Visitor<N,R> v);
}

class Plus<N> implements Expr<N> {
    Expr<N> e1;
    Expr<N> e2;
    public Plus(Expr<N> e1, Expr<N> e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
    @Override public <R> R accept(Visitor<N, R> v) { return v.plus(e1, e2); }
}

class Square<N> implements Expr<N> {
    Expr<N> e;
    public Square(Expr<N> e) { this.e = e; }
    @Override  public <R> R accept(Visitor<N, R> v) { return v.square(e); }
}

class Number<N> implements Expr<N> {
    N n;
    public Number(N n) { this.n = n; }
    @Override  public <R> R accept(Visitor<N, R> v) { return v.number(n); }
}

// 식의 평가를 실시하는 Visitor
class Eval implements Visitor<Integer, Integer> {
    @Override
    public Integer plus(Expr<Integer> e1, Expr<Integer> e2) {
        return e1.accept(this) + e2.accept(this);
    }

    @Override
    public Integer square(Expr<Integer> e) {
        Integer x = e.accept(this);
        return x;
    }

    @Override
    public Integer number(Integer n) {
        return n;
    }
}

// 식을 문자열로 하는 Visitor
class Show implements Visitor<Integer, String> {
    @Override
    public String plus(Expr<Integer> e1, Expr<Integer> e2) {
        return e1.accept(this) + " + " + e2.accept(this);
    }

    @Override
    public String square(Expr<Integer> e) {
        return "(" + e.accept(this) + ")^2";
    }

    @Override
    public String number(Integer n) {
        return n + "";
    }
}
public class TestVisitor {
    public static void main(String[] args) {
        //  e = 1 + (2 + 3) ^ 2
        // 실제로는 구문 해석 등에 의해서 좀 더 크고 복잡한 것을 가정
        Expr e = new Plus(new Number(1), new Square(new Plus(new Number(2)
                                                                ,new Number(3))));
        System.out.println(e.accept(new Show()));
        System.out.println(e.accept(new Eval()));
    }
}
