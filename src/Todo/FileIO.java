package Todo;

import java.io.*;

public class FileIO {
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private File file;

    public FileIO(File file) {
        this.file=file;
    }
    public FileIO(String path) {
        file=new File(path);
    }
    public Object readObject() {
        Object object=new Object();
        try{
            is=new ObjectInputStream(new FileInputStream(file.getPath()));
            try {
                object = is.readObject();
            }catch (ClassNotFoundException err) {
                System.out.println("Class not found");
            }
            is.close();
        }catch (IOException err) {
            System.out.println("File not found");
        }
        return object;
    }
    public void writeObject(Object object) {
        try{
            os=new ObjectOutputStream(new FileOutputStream(file));
            os.flush();
            os.writeObject(object);
            os.close();
        }catch (IOException err){

        }
    }
}
