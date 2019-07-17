package Todo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Task implements Serializable {
    private String name;
    private LocalDate due;
    private ImageView priority;
    private Button priorityIllustrator;
    private Button terminate;

    public Task(String name, LocalDate due,ImageView priority) {
        this.name = name;
        this.due = due;
        terminate=new Button("X");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    public ImageView getPriority() {
        return priority;
    }

    public void setPriority(ImageView priority) {
        this.priority = priority;
    }

    public Button getPriorityIllustrator() {
        return priorityIllustrator;
    }

    public void setPriorityIllustrator(Button priorityIllustrator) {
        this.priorityIllustrator = priorityIllustrator;
    }

    public Button getTerminate() {
        return terminate;
    }

    public void setTerminate(Button terminate) {
        this.terminate = terminate;
    }
}
