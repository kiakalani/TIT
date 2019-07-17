package Todo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    public static void minusBtnActions(ListView<String> listView) {
        removeFile(listView);
        initializeTaskNames();
        defineListView(listView);
    }
    public static void plsBtnActions(ListView<String> listView) {
        TextInputDialog textInputDialog=new TextInputDialog();
        textInputDialog.setContentText("Please insert the task name:");
        String taskName=textInputDialog.showAndWait().get();
        new File("bin/todo/"+taskName).mkdirs();
        initializeTaskNames();
        defineListView(listView);
    }
    private static void removeFile(ListView<String> listView) {
        taskNames[listView.getSelectionModel().getSelectedIndex()].delete();
    }
}
