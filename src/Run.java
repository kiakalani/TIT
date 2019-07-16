import javafx.application.Application;
import javafx.stage.Stage;

public class Run  extends Application {
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
