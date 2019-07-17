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

    public static void todoCtrl(ListView<String> listView,Button plsBtn,Button minusBtn) {
        initializeTaskNames();
        defineListView(listView);
        setButtonActions(listView,plsBtn,minusBtn);
    }
    private static void setButtonActions(ListView<String> listView,Button plsBtn,Button minusBtn){
        minusBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeFile(listView);
                initializeTaskNames();
                defineListView(listView);
            }
        });
    }
    private static void removeFile(ListView<String> listView) {
        taskNames[listView.getSelectionModel().getSelectedIndex()].delete();
    }
}
