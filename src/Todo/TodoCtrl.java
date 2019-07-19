package Todo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;

public class TodoCtrl {
    /**
     * A file array for getting all the task roots
     */
    private static File[] taskNames;

    /**
     * Listing all the tasks in the project
     */
    private static void initializeTaskNames() {
        taskNames=new File("bin/todo").listFiles();
    }

    /**
     * Setting the items inside the list view
     * @param listView is the listview illustrated in the project
     */
    private static void defineListView(ListView<String> listView) { //Works fine
        ObservableList<String> wrds= FXCollections.observableArrayList();
        for (File file:taskNames) {
            wrds.add(file.getName());
        }
        listView.setItems(wrds);
    }

    /**
     * Removing the selected file by pressing the - button
     * @param listView
     */
    private static void removeFile(ListView<String> listView) { //Works fine
        if (listView.getSelectionModel().getSelectedIndex()!=-1)
        taskNames[listView.getSelectionModel().getSelectedIndex()].delete();
    }

    /**
     * This function renews the task names in case of change and then deals with resetting the listview
     * @param listView
     */
    public static void todoCtrl(ListView<String> listView) {    //Works fine
        initializeTaskNames();
        defineListView(listView);
    }

    /**
     * The - button actions
     * @param listView
     */
    public static void minusBtnActions(ListView<String> listView) { //Works fine
        removeFile(listView);
        initializeTaskNames();
        defineListView(listView);
    }

    /**
     * + button actions
     * @param listView
     */
    public static void plsBtnActions(ListView<String> listView) {   //Works fine
        TextInputDialog textInputDialog=new TextInputDialog();
        textInputDialog.setContentText("Please insert the task name:");
        String taskName=textInputDialog.showAndWait().get();
        new File("bin/todo/"+taskName).mkdirs();
        initializeTaskNames();
        defineListView(listView);
    }

    /**
     * This function loads the files inside a folder(Still working on it)
     * @param listView
     * @param taskTableView
     */
    private static void loadTodoFiles(ListView<String> listView,TableView<Task>taskTableView) {   //Not used yet but would probably work
        if (listView.getSelectionModel().getSelectedIndex()!=-1) {
            File[] files = new File("bin/todo/" + listView.getSelectionModel().getSelectedItem()).listFiles();
//        System.out.println(files.length);
            ObservableList<Task> tasks = FXCollections.observableArrayList();
            for (File file : files) {
                tasks.add((Task) new FileIO(file).readObject());
            }
            for (Task task : tasks) {
                task.setBtns();
            }
            setList(listView, taskTableView);
            taskTableView.setItems(tasks);
        }

    }

    /**
     * When the user clicks on an option in this listview, it would show all the tasks inside.
     * @param listView
     * @param taskTableView
     */
    public static void setList(ListView<String> listView,TableView<Task>taskTableView) {

        listView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                loadTodoFiles(listView,taskTableView);
                reloadFiles(taskTableView,listView);
                taskTableView.getSortOrder().add(taskTableView.getColumns().get(1));
            }
        });
        taskTableView.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                reloadFiles(taskTableView,listView);
            }
        });
    }

    /**
     * Defining the table's behaviour towards the matters
     * @param taskTableView
     */
    public static void manageTable(TableView<Task> taskTableView) { //Partially done
        taskTableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("name"));
        taskTableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("due"));
        taskTableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("daysRemaining"));
        taskTableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("priorityIllustrator"));
        taskTableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("terminate"));
        taskTableView.getSortOrder().add(taskTableView.getColumns().get(1));

    }

    /**
     * Defining the behavior of add button when the user decides to add a task to their todo
     * @param addButton
     * @param listView
     * @param taskTableView
     * @param textField
     * @param datePicker
     */
    public static void addButtonSetup(Button addButton,ListView<String>listView,TableView<Task> taskTableView,TextField textField,DatePicker datePicker) {  //Works for now
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String remaining= Duration.between(LocalDate.now().atStartOfDay(),datePicker.getValue().atStartOfDay()).toDays()+"";
                    Task task = new Task(textField.getText(), datePicker.getValue().getYear(), datePicker.getValue().getMonthValue(), datePicker.getValue().getDayOfMonth(),remaining);
                    taskTableView.getItems().add(task);
                    try {
                        new FileIO(taskNames[listView.getSelectionModel().getSelectedIndex()].getAbsolutePath() + "/" + System.currentTimeMillis() + ".txt").writeObject(task);
                    }catch (IndexOutOfBoundsException err) {
                        taskTableView.getItems().removeAll(taskTableView.getItems());
                        Alert alert=new Alert(Alert.AlertType.ERROR,"Woah! Please select a task in order to add a task");
                        alert.showAndWait();
                    }
                    task.setBtns();
                }catch (NullPointerException err) {
                    Alert alert=new Alert(Alert.AlertType.ERROR,"Woah! Please fill out all of the boxes provided");
                    alert.showAndWait();
                }
            }
        });
        reloadFiles(taskTableView, listView);
    }

    /**
     * This function is for the matter of modifying the current files if any change happens to the objects
     * @param taskTableView
     * @param listView
     */
    private static void updateFiles(TableView<Task> taskTableView,ListView<String>listView) {
        File[] flusher=taskNames[listView.getSelectionModel().getSelectedIndex()].listFiles();
        for (File file:flusher) {
            file.delete();
        }
        for (int i=0;i<taskTableView.getItems().size();i++) {
            new FileIO(taskNames[listView.getSelectionModel().getSelectedIndex()].getAbsolutePath() + "/" + System.currentTimeMillis()+".txt").writeObject(taskTableView.getItems().get(i));
        }
    }

    /**
     * This method would deal with updating the status of files once they have been removed from the project
     * @param taskTableView
     * @param listView
     */
    public static void reloadFiles(TableView<Task> taskTableView,ListView<String> listView) {
        for (int i=0;i<taskTableView.getItems().size();i++) {
            final int num=i;
            taskTableView.getItems().get(i).getTerminate().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    File[] files=taskNames[listView.getSelectionModel().getSelectedIndex()].listFiles();
                    files[num].delete();
                    System.out.println("Done");
                    loadTodoFiles(listView, taskTableView);
                    reloadFiles(taskTableView, listView);
                }
            });
            taskTableView.getItems().get(i).getPriorityIllustrator().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (taskTableView.getItems().get(num).getPriorityDefiner().ordinal()!=2) {
                        taskTableView.getItems().get(num).setPriorityDefiner(Task.PriorityEnum.values()[taskTableView.getItems().get(num).getPriorityDefiner().ordinal() + 1]);
                    }else taskTableView.getItems().get(num).setPriorityDefiner(Task.PriorityEnum.LOW);
                    File[] files=taskNames[listView.getSelectionModel().getSelectedIndex()].listFiles();
                    String remaining= Duration.between(LocalDate.now().atStartOfDay(),taskTableView.getItems().get(num).getDue().atStartOfDay()).toDays()+"";
                    Task task=new Task(taskTableView.getItems().get(num).getName(),taskTableView.getItems().get(num).getDue().getYear(),taskTableView.getItems().get(num).getDue().getMonthValue(),taskTableView.getItems().get(num).getDue().getDayOfMonth(),remaining);
                    task.setPriorityDefiner(taskTableView.getItems().get(num).getPriorityDefiner());
                    new FileIO(files[num]).writeObject(task);
                    taskTableView.getItems().get(num).getPriorityIllustrator().setText(taskTableView.getItems().get(num).getPriorityDefiner().toString());
                    loadTodoFiles(listView, taskTableView);
                    reloadFiles(taskTableView, listView);
                }
            });
        }
    }

}
