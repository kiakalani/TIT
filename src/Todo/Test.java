package Todo;

public class Test {
    public static void main(String[] args) {
        System.out.println((Task)new FileIO("todo/2/1563409589112.txt").readObject());
    }
}
