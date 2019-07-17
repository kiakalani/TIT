package Todo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private String name;
    private LocalDate due;
//    private ImageView priority;
    private Button priorityIllustrator;
    private Button terminate;
    private enum PriorityEnum{
        LOW,MEDIUM,HIGH
    }
    private PriorityEnum priorityDefiner;
    public Task(String name,int year, int month, int day) {
        this.name=name;
        due=LocalDate.of(year,month,day);
        priorityDefiner=PriorityEnum.LOW;
        System.out.println(priorityDefiner.ordinal());
        System.out.println("Todo/priority/"+(priorityDefiner.ordinal()+1)+".jpg");
//        priorityIllustrator=new Button(priorityDefiner.toString());
//
//        priorityIllustrator.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if (priorityDefiner.ordinal()!=2) {
//                    priorityDefiner = PriorityEnum.values()[priorityDefiner.ordinal() + 1];
//                }else priorityDefiner=PriorityEnum.LOW;
//                priorityIllustrator.setText(priorityDefiner.toString());
//            }
//        });
//        terminate=new Button("X");
    }
    public void setBtns() {
        priorityIllustrator=new Button(priorityDefiner.toString());

        priorityIllustrator.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (priorityDefiner.ordinal()!=2) {
                    priorityDefiner = PriorityEnum.values()[priorityDefiner.ordinal() + 1];
                }else priorityDefiner=PriorityEnum.LOW;
                priorityIllustrator.setText(priorityDefiner.toString());
            }
        });
        terminate=new Button("X");
    }

    public String getName() {
        return name;
    }

    public LocalDate getDue() {
        return due;
    }

//    public ImageView getPriority() {
//        return priority;
//    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", due=" + due +
                ", priorityIllustrator=" + priorityIllustrator +
                ", terminate=" + terminate +
                ", priorityDefiner=" + priorityDefiner +
                '}';
    }

    public Button getTerminate() {
        return terminate;
    }

    public PriorityEnum getPriorityDefiner() {
        return priorityDefiner;
    }

    public Button getPriorityIllustrator() {
        return priorityIllustrator;
    }

}

