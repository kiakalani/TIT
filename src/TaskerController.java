import Todo.Task;
import Todo.TodoCtrl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskerController implements Initializable {

    @javafx.fxml.FXML
    private javafx.scene.control.Button taskBtnMinus;
    @javafx.fxml.FXML
    private javafx.scene.control.Button taskBtnPlus;
    @javafx.fxml.FXML
    private javafx.scene.control.TableView taskTableView;
    @javafx.fxml.FXML
    private javafx.scene.control.ListView taskListView;
    @javafx.fxml.FXML
    private javafx.scene.control.TextField taskTextField;
    @javafx.fxml.FXML
    private javafx.scene.control.ColorPicker TaskColorPicker;
    @javafx.fxml.FXML
    private javafx.scene.control.DatePicker taskDatePicker;

    @FXML
    private void setTaskBtnPlus(){
        //put code here dawg for plus button
    }

    @FXML
    private void setTaskBtnMinus(){
        //put code here dawg for minus button
    }

    public void initialize(URL url, ResourceBundle rb){

    }

}
