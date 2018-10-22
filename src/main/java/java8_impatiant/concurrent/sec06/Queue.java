package java8_impatiant.concurrent.sec06;

/**
 * 조건 대기, 메서드들 동기화하면 이러한 연산이 원자적으로 동작해야 한다.
 *
 */
public class Queue {
    class Node { Object value; Node next; };
    private Node head;
    private Node tail;

    public synchronized void add(Object newValue) {
        Node n = new Node();
        if (head == null) head = n;
        else tail.next = n;
        tail = n;
        tail.value = newValue;
        notifyAll();  // wait 중인 친구들을 전부 불러모은다.
    }

    public synchronized Object remove() {
        if (head == null) return null;
        Node n = head;
        head = n.next;
        return n.value;
    }

    /**
     * wait() 으로 현재 스레드를 비활성화하고 잠금을 놓는다.
     * wait을 호출하면 해당 객체의 대기 집합(wait set)에 들어가게 된다. 해당 스레드는 잠금을 이용할 수 있게 되더라도
     * 실행할 수 있는 상태로 만들어지지 않는다. 대신 또 다른 스레드에서 같은 객체에 notifyAll을 호출할 때까지 비활성화 상태로 머문다.
     *
     */
    public synchronized Object take() throws InterruptedException {
        while (head == null) wait();
        Node n = head;
        head = n.next;
        return n.value;
    }
}