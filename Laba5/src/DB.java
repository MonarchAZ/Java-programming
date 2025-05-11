import java.sql.*;

public class DB {
    private static final String DB_URL = "jdbc:sqlite:notes.db";    // файл БД

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);  // установление соединения
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Структура БД
    public static void init() {
        String usersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT UNIQUE NOT NULL);";

        String notesTable = "CREATE TABLE IF NOT EXISTS notes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "title TEXT," +
                "content TEXT," +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY(user_id) REFERENCES users(id));";

        // Получение соединения
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(usersTable);
            stmt.execute(notesTable);

            // Добавил пользователя по умолчанию если его нет
            String insertUser = "INSERT OR IGNORE INTO users(username) VALUES('default_user');";
            stmt.execute(insertUser);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}