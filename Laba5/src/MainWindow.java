import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.*;

public class MainWindow {

    private ListView<Note> noteListView;
    private ObservableList<Note> noteList;
    private TextField searchField;
    private int userId = 1; // ID текущего пользователя

    // Отображение главного экрана
    public void show(Stage primaryStage) {
        primaryStage.setTitle("Электронный ежедневник");

        BorderPane root = new BorderPane();

        // Поиск
        searchField = new TextField();
        searchField.setPromptText("Поиск...");
        searchField.textProperty().addListener((obs, oldVal, newVal) -> loadNotes(newVal));

        // Список заметок
        noteList = FXCollections.observableArrayList();
        noteListView = new ListView<>(noteList);
        // Настраиваем отображение элементов списка
        noteListView.setCellFactory(param -> new ListCell<Note>() {
            @Override
            protected void updateItem(Note item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitle());       // Показываем только название заметки
                }
            }
        });

        loadNotes("");

        // Кнопки управления
        Button addBtn = new Button("Добавить заметку");
        addBtn.setOnAction(e -> openNoteWindow(null));

        Button editBtn = new Button("Редактировать");
        editBtn.setOnAction(e -> {
            Note selected = noteListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                openNoteWindow(selected);
            }
        });

        Button deleteBtn = new Button("Удалить");
        deleteBtn.setOnAction(e -> {
            Note selected = noteListView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                deleteNote(selected.getId());
                loadNotes(searchField.getText());
            }
        });

        HBox buttons = new HBox(10, addBtn, editBtn, deleteBtn);
        buttons.setPadding(new Insets(10));

        root.setTop(searchField);
        root.setCenter(noteListView);
        root.setBottom(buttons);
        // Настройка окна
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // Загрузка заметок из БД
    private void loadNotes(String filter) {
        noteList.clear();
        String sql = "SELECT * FROM notes WHERE user_id = ? AND title LIKE ? ORDER BY created_at DESC";

        try (Connection conn = DB.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, "%" + filter + "%");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Note n = new Note(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("created_at"));
                noteList.add(n);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Открытие окна редактирования
    private void openNoteWindow(Note note) {
        NoteWindow nw = new NoteWindow(this, userId, note);
        Stage stage = new Stage();
        nw.show(stage);
    }

    // Обновление заметок (вызывается после сохранения)
    void refreshList() {
        loadNotes(searchField.getText());
    }

    // Удаление заметки (по id)
    private void deleteNote(int id) {
        String sql = "DELETE FROM notes WHERE id = ?";
        try (Connection conn = DB.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}