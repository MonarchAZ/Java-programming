// Зуев Артур, 3МО, 1 подгруппа.
// Для входа в режиме админа логин: admin. Пароль: 123
// Для входа в режиме пользователя никакого логина и пароля не предусмотрел.
// В выводе информации о зале "1" - место занято, "0" - место свободно

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class Seat {
    private boolean isBooked;

    public Seat() {
        this.isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        isBooked = true;
    }

    @Override
    public String toString() {
        return isBooked ? "1" : "O";
    }
}

class Hall {
    private String name;
    private int rows;
    private int columns;
    private List<Session> sessions;

    public Hall(String name, int rows, int columns) {
        this.name = name;
        this.rows = rows;
        this.columns = columns;
        this.sessions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }
}

class Session {
    private String movieName;
    private Date startTime;
    private int duration;
    private Seat[][] seats; // Места для этого сеанса

    public Session(String movieName, Date startTime, int duration, int rows, int columns) {
        this.movieName = movieName;
        this.startTime = startTime;
        this.duration = duration;
        this.seats = new Seat[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                this.seats[i][j] = new Seat();
            }
        }
    }

    public String getMovieName() {
        return movieName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public boolean bookSeat(int row, int col) {
        if (row < 0 || row >= seats.length || col < 0 || col >= seats[0].length)
        {
            return false;
        }
        if (!seats[row][col].isBooked())
        {
            seats[row][col].book();
            return true;
        }
        return false;
    }

    public void printHallPlan() {
        System.out.println("План зала для фильма \"" + movieName + "\":");
        for (Seat[] row : seats)
        {
            for (Seat seat : row)
            {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return "Фильм: " + movieName + ", Начало: " + sdf.format(startTime) + ", Длительность: " + duration + " минут";
    }
}

class Cinema {
    private String name;
    private List<Hall> halls;

    public Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }

    public List<Hall> getHalls() {
        return halls;
    }
}

public class Main {
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "123";
    private static Map<String, Cinema> cinemas = new HashMap<>();
    private static boolean isRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (isRunning)
        {
            System.out.println("\nВыбери как войти в систему (введи цифру):");
            System.out.println("1. Администратор");
            System.out.println("2. Пользователь");
            System.out.println("3. Завершить программу");

            int userType = scanner.nextInt();
            scanner.nextLine();

            switch (userType)
            {
                case 1:
                    adminLogin(scanner);
                    break;
                case 2:
                    userMenu(scanner);
                    break;
                case 3:
                    System.out.println("Отключение...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Такого выбора не дано");
            }
        }
    }

    private static void adminLogin(Scanner scanner) {
        System.out.println("Введи логин:");
        String login = scanner.nextLine();

        System.out.println("Введи пароль:");
        String password = scanner.nextLine();

        if (login.equals(ADMIN_LOGIN) && password.equals(ADMIN_PASSWORD))
        {
            adminMenu(scanner);
        }
        else
        {
            System.out.println("Неверный логин или пароль");
        }
    }

    private static void adminMenu(Scanner scanner) {
        boolean isAdminActive = true;

        while (isAdminActive)
        {
            System.out.println("\nТы вошёл как админ. Выбери опцию (цифрой):");
            System.out.println("1. Добавить кинотеатр");
            System.out.println("2. Добавить зал");
            System.out.println("3. Создать сеанс");
            System.out.println("4. Продать билет");
            System.out.println("5. Показать план зала");
            System.out.println("6. Показать все залы и расписание");
            System.out.println("7. Выйти в главное меню");
            System.out.println("8. Завершение программы");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.print("Введи название кинотеатра: ");
                    String cinemaName = scanner.nextLine();
                    cinemas.put(cinemaName, new Cinema(cinemaName));
                    System.out.println("Кинотеатр был успешно добавлен!");
                    break;
                case 2:
                    System.out.print("Введи название кинотеатра: ");
                    cinemaName = scanner.nextLine();
                    Cinema cinema = cinemas.get(cinemaName);
                    if (cinema != null)
                    {
                        System.out.print("Введи название зала: ");
                        String hallName = scanner.nextLine();
                        System.out.print("Введи количество рядов в зале: ");
                        int rows = scanner.nextInt();
                        System.out.print("Введи количество мест в ряду: ");
                        int columns = scanner.nextInt();
                        scanner.nextLine();
                        cinema.addHall(new Hall(hallName, rows, columns));
                        System.out.println("Зал был успешно добавлен!");
                    }
                    else
                    {
                        System.out.println("Кинотеатр не был найден");
                    }
                    break;
                case 3:
                    System.out.print("Введи название кинотеатра: ");
                    cinemaName = scanner.nextLine();
                    cinema = cinemas.get(cinemaName);
                    if (cinema != null)
                    {
                        System.out.print("Введи название зала: ");
                        String hallName = scanner.nextLine();
                        Hall hall = cinema.getHalls().stream()
                                .filter(h -> h.getName().equals(hallName))
                                .findFirst()
                                .orElse(null);
                        if (hall != null)
                        {
                            System.out.print("Введи название фильма: ");
                            String movieName = scanner.nextLine();
                            System.out.print("Введи время начала (формат ГГГГ-ММ-ДД ЧЧ:ММ): ");
                            String timeString = scanner.nextLine();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            try
                            {
                                Date startTime = sdf.parse(timeString);
                                System.out.print("Введи длительность фильма (в минутах): ");
                                int duration = scanner.nextInt();
                                scanner.nextLine();
                                hall.addSession(new Session(movieName, startTime, duration, hall.getRows(), hall.getColumns()));
                                System.out.println("Сеанс был успешно добавлен!");
                            }
                            catch (ParseException e)
                            {
                                System.out.println("Неверный формат даты!");
                            }
                        }
                        else
                        {
                            System.out.println("Зал не был найден");
                        }
                    }
                    else
                    {
                        System.out.println("Кинотеатр не был найден");
                    }
                    break;
                case 4:
                    sellTicket(scanner);
                    break;
                case 5:
                    showHallPlanForSession(scanner);
                    break;
                case 6:
                    showAllCinemas();
                    break;
                case 7:
                    isAdminActive = false;
                    break;
                case 8:
                    System.out.println("Отключение...");
                    isRunning = false;
                    isAdminActive = false;
                    break;
                default:
                    System.out.println("Такого выбора не существует!");
            }
        }
    }

    private static void userMenu(Scanner scanner) {
        while (true)
        {
            System.out.println("\nТы вошёл как пользователь. Выбери опцию:");
            System.out.println("1. Показать доступные кинотеатры, залы с расписанием");
            System.out.println("2. Посмотреть план зала");
            System.out.println("3. Купить билет");
            System.out.println("4. Выйти в главное в главное меню");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    showAllCinemas();
                    break;
                case 2:
                    showHallPlanForSession(scanner);
                    break;
                case 3:
                    sellTicket(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Такого выбора не существует!");
            }
        }
    }

    private static void showHallPlanForSession(Scanner scanner) {
        System.out.print("Введи название кинотеатра: ");
        String cinemaName = scanner.nextLine();
        Cinema cinema = cinemas.get(cinemaName);
        if (cinema != null)
        {
            System.out.print("Введи название зала: ");
            String hallName = scanner.nextLine();
            Hall hall = cinema.getHalls().stream()
                    .filter(h -> h.getName().equals(hallName))
                    .findFirst()
                    .orElse(null);
            if (hall != null)
            {
                if (hall.getSessions().isEmpty())
                {
                    System.out.println("Увы в этом зале нет доступных сеансов");
                    return;
                }
                System.out.println("Доступные сеансы:");
                for (int i = 0; i < hall.getSessions().size(); i++)
                {
                    System.out.println((i + 1) + ". " + hall.getSessions().get(i));
                }
                System.out.print("Выбери сеанс по номеру: ");
                int sessionIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (sessionIndex < 0 || sessionIndex >= hall.getSessions().size())
                {
                    System.out.println("Неверный выбор сеанса!");
                    return;
                }
                Session session = hall.getSessions().get(sessionIndex);
                session.printHallPlan();
            }
            else
            {
                System.out.println("Зал не найден!");
            }
        }
        else
        {
            System.out.println("Кинотеатр не найден!");
        }
    }

    private static void sellTicket(Scanner scanner) {
        System.out.print("Введи название кинотеатра: ");
        String cinemaName = scanner.nextLine();
        Cinema cinema = cinemas.get(cinemaName);
        if (cinema != null)
        {
            System.out.print("Введи название зала: ");
            String hallName = scanner.nextLine();
            Hall hall = cinema.getHalls().stream()
                    .filter(h -> h.getName().equals(hallName))
                    .findFirst()
                    .orElse(null);
            if (hall != null)
            {
                if (hall.getSessions().isEmpty())
                {
                    System.out.println("В этом зале нет доступных сеансов! Кино не будет");
                    return;
                }
                System.out.println("Доступные сеансы:");
                for (int i = 0; i < hall.getSessions().size(); i++)
                {
                    System.out.println((i + 1) + ". " + hall.getSessions().get(i));
                }
                System.out.print("Выбери сеанс по номеру: ");
                int sessionIndex = scanner.nextInt() - 1;
                if (sessionIndex < 0 || sessionIndex >= hall.getSessions().size())
                {
                    System.out.println("Неверный выбор сеанса");
                    return;
                }
                Session session = hall.getSessions().get(sessionIndex);
                System.out.print("Введи номер ряда: ");
                int row = scanner.nextInt() - 1;
                System.out.print("Введи номер места: ");
                int col = scanner.nextInt() - 1;
                scanner.nextLine();
                System.out.print("Введи номер карты (16 цифр): ");
                String cardNumber = scanner.nextLine();
                if (cardNumber.matches("\\d{16}"))
                {
                    if (session.bookSeat(row, col))
                    {
                        System.out.println("Билет успешно продан. Спасибо за покупку!");
                    }
                    else
                    {
                        System.out.println("Место занято или неверное расположение");
                    }
                }
                else
                {
                    System.out.println("Неправильный номер карты!");
                }
            }
            else
            {
                System.out.println("Зал не найден!");
            }
        }
        else
        {
            System.out.println("Кинотеатр не найден!");
        }
    }

    private static void showAllCinemas() {
        cinemas.forEach((cinemaNameKey, cinemaObj) -> {
            System.out.println("Кинотеатр: " + cinemaObj.getName());
            for (Hall hall : cinemaObj.getHalls()) {
                System.out.println("  Зал: " + hall.getName());
                for (Session session : hall.getSessions()) {
                    System.out.println("    " + session);
                }
            }
        });
    }
}