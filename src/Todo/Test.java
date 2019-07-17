package Todo;

public class Test {
    public static void main(String[] args) {
        Task task=(Task)new FileIO("File.txt").readObject();
        System.out.println(task);
//        Task task=new Task("Name",2019,12,12);
//        new FileIO("File.txt").writeObject(task);
    }
}
