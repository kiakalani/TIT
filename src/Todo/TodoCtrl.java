package Todo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.File;

public class TodoCtrl {
    private static File[] taskNames;
    private static void initializeTaskNames() {
        taskNames=new File("bin/todo").listFiles();
    }
    private static void defineListView(ListView<String> listView) {
        ObservableList<String> wrds= FXCollections.observableArrayList();
        for (File file:taskNames) {
            wrds.add(file.getName());
        }
        listView.setItems(wrds);
    }

    public static void todoCtrl(ListView<String> listView) {
        initializeTaskNames();
        defineListView(listView);
    }
}
