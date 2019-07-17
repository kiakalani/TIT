package Todo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.*;

public class TodoCtrl {
    private static File[] taskNames;
    private static void initializeTaskNames() {
        taskNames=new File("bin/todo").listFiles();
    }
    private static void defineListView(ListView<String> listView) { //Works fine
        ObservableList<String> wrds= FXCollections.observableArrayList();
        for (File file:taskNames) {
            wrds.add(file.getName());
        }
        listView.setItems(wrds);
    }
    private static void removeFile(ListView<String> listView) { //Works fine
        taskNames[listView.getSelectionModel().getSelectedIndex()].delete();
    }

    public static void todoCtrl(ListView<String> listView) {    //Works fine
        initializeTaskNames();
        defineListView(listView);
    }

    public static void minusBtnActions(ListView<String> listView) { //Works fine
        removeFile(listView);
        initializeTaskNames();
        defineListView(listView);
    }
    public static void plsBtnActions(ListView<String> listView) {   //Works fine
        TextInputDialog textInputDialog=new TextInputDialog();
        textInputDialog.setContentText("Please insert the task name:");
        String taskName=textInputDialog.showAndWait().get();
        new File("bin/todo/"+taskName).mkdirs();
        initializeTaskNames();
        defineListView(listView);
    }
    public static void loadTodoFiles(ListView<String> listView) {   //Not used yet but would probably work
        File[] files=new File("bin/todo/"+listView.getSelectionModel().getSelectedItem()).listFiles();
        ObservableList<Task> tasks=FXCollections.observableArrayList();
        try {
            try {
                for (File file : files) {
                    tasks.add((Task) (new ObjectInputStream(new FileInputStream(file)).readObject()));
                }
            }catch (IOException error) {
                System.out.println("Error 404");
            }
        }catch (ClassNotFoundException err) {
            System.out.println("Critical error");
        }
    }
    public static void manageTable(TableView<Task> taskTableView) {
        taskTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        taskTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("due"));
        taskTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("priorityIllustrator"));
        taskTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("terminate"));
        for (Task task:taskTableView.getItems()) {  //Doesn't work yet...
            task.getTerminate().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Terminated");   //code here
                }
            });
        }
    }
    public static void addButtonSetup(Button addButton,ListView<String>listView,TableView<Task> taskTableView,TextField textField,DatePicker datePicker) {  //Works for now
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Task task=new Task(textField.getText(),datePicker.getValue(),new ImageView());
                taskTableView.getItems().add(task);
                try {
                    ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(new File(taskNames[listView.getSelectionModel().getSelectedIndex()].getAbsolutePath() + "/" + System.currentTimeMillis())));
                    o.writeObject(task);
                    o.close();
                }catch (IOException err) {

                }
            }
        });
    }

}
