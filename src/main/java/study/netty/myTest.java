package study.netty;

public class myTest {
    public static void main(String[] args) {
        String dd = "1.abc";
        String[] ss = dd.split("\\.");
        System.out.println(ss[0]+ss[1]);
    }
}
