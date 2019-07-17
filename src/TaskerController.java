import Todo.Task;
import Todo.TodoCtrl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskerController implements Initializable {

    @javafx.fxml.FXML
    private javafx.scene.control.Button taskBtnMinus;
    @javafx.fxml.FXML
    private javafx.scene.control.Button taskBtnPlus;
    @javafx.fxml.FXML
    private javafx.scene.control.TableView<Task> taskTableView=new TableView<>();
    @javafx.fxml.FXML
    private javafx.scene.control.ListView taskListView;
    @javafx.fxml.FXML
    private javafx.scene.control.TextField taskTextField;
    @javafx.fxml.FXML
    private javafx.scene.control.ColorPicker TaskColorPicker;
    @javafx.fxml.FXML
    private javafx.scene.control.DatePicker taskDatePicker;
    @FXML
    private TableColumn<Task,Object> nameColumn,dueColumn,priorityColumn,terminateColumn;
    @FXML
    private Button addButton;
    @FXML
    private void setTaskBtnPlus(){
        //put code here dawg for plus button
        TodoCtrl.plsBtnActions(taskListView);
    }

    @FXML
    private void setTaskBtnMinus(){
        //put code here dawg for minus button
        TodoCtrl.minusBtnActions(taskListView);
    }

    public void initialize(URL url, ResourceBundle rb){
        TodoCtrl.todoCtrl(taskListView);
        TodoCtrl.manageTable(taskTableView);
        TodoCtrl.addButtonSetup(addButton,taskListView,taskTableView,taskTextField,taskDatePicker);
    }

}
