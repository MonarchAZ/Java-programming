import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NoteWindow {

    private MainWindow mainWindow;      // главное окно
    private int userId;                 // ID пользователя
    private Note note;                  // редактируемая запись

    public NoteWindow(MainWindow mainWindow, int userId, Note note) {
        this.mainWindow = mainWindow;
        this.userId = userId;
        this.note = note;
    }

    // Отображение окна и настройка
    public void show(Stage stage) {
        stage.setTitle(note == null ? "Создать заметку" : "Редактировать заметку");

        Label titleLabel = new Label("Заголовок:");     // поле заголовка
        TextField titleField = new TextField();
        if (note != null) titleField.setText(note.getTitle());  // заполняем если редактируем

        Label contentLabel = new Label("Содержание:");   // текст заметки
        TextArea contentArea = new TextArea();
        contentArea.setPrefRowCount(10);                    // начальная высота
        if (note != null) contentArea.setText(note.getContent());

        Button saveBtn = new Button("Сохранить");        // кнопка сохранения
        saveBtn.setOnAction(e -> {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();

            // Проверка на ошибку с пустым заголовком
            if (title.isEmpty()) {
                showAlert("Ошибка", "Заголовок не может быть пустым");
                return;
            }

            // В зависимости от режима вызываем нужный метод
            if (note == null) {
                addNote(title, content);                    // создание новой записи
            } else {
                updateNote(note.getId(), title, content);   // редактирование
            }
            mainWindow.refreshList();
            stage.close();
        });

        // Кнопка отмены
        Button cancelBtn = new Button("Отмена");
        cancelBtn.setOnAction(e -> stage.close());

        VBox vbox = new VBox(10, titleLabel, titleField, contentLabel, contentArea, saveBtn, cancelBtn);
        vbox.setPadding(new Insets(10));

        // Настройка окна по размеру
        Scene scene = new Scene(vbox, 300, 350);
        stage.setScene(scene);
        stage.show();
    }

    // Добавление новой записи
    private void addNote(String title, String content) {
        String sql = "INSERT INTO notes(user_id, title, content) VALUES(?, ?, ?)";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);     // параметры
            stmt.setString(2, title);
            stmt.setString(3, content);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Обновление записи
    private void updateNote(int id, String title, String content) {
        String sql = "UPDATE notes SET title = ?, content = ? WHERE id = ?";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, content);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}