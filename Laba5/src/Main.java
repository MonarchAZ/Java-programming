import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DB.init();                          // создание БД, главного окна
        MainWindow mw = new MainWindow();
        mw.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);                       // сам запуск приложения
    }
}