package Todo;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.Date;

public class Task {
    private String name;
    private Date due;
    private ImageView priority;
    private Button priorityIllustrator;
    private Button terminate;

    public Task(String name, Date due) {
        this.name = name;
        this.due = due;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
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
