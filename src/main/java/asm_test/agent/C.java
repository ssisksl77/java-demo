package asm_test.agent;

public class C{
    public static long timer;

    public void m() throws Exception {
        timer -= System.currentTimeMillis();
        Thread.sleep(100);
        timer += System.currentTimeMillis();
    }

    public static void main(String[] args) {

    }
}
