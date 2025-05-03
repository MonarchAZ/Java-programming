import java.util.*;

class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "\"" + title + "\" автор " + author + " (" + year + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return year == book.year &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }
}

class Library {
    private List<Book> books;
    private Set<String> uniqueAuthors;
    private Map<String, Integer> authorBookCount;

    public Library() {
        books = new ArrayList<>();
        uniqueAuthors = new HashSet<>();
        authorBookCount = new HashMap<>();
    }

    public void addBook(Book book) {
        if (books.contains(book)) {
            System.out.println("Книга " + book + " уже есть в библиотеке.");
            return;
        }
        books.add(book);
        uniqueAuthors.add(book.getAuthor());
        authorBookCount.put(book.getAuthor(),
                authorBookCount.getOrDefault(book.getAuthor(), 0) + 1);
    }

    public void removeBook(Book book) {
        if (!books.remove(book)) {
            System.out.println("Книга " + book + " не найдена");
            return;
        }
        int count = authorBookCount.getOrDefault(book.getAuthor(), 0);
        if (count <= 1) {
            authorBookCount.remove(book.getAuthor());
            uniqueAuthors.remove(book.getAuthor());
        } else {
            authorBookCount.put(book.getAuthor(), count - 1);
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getYear() == year) {
                result.add(b);
            }
        }
        return result;
    }

    public void printAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Библиотека пустая.");
            return;
        }
        System.out.println("Все книги в библиотеке:");
        for (Book b : books) {
            System.out.println("  " + b);
        }
    }

    public void printUniqueAuthors() {
        if (uniqueAuthors.isEmpty()) {
            System.out.println("Авторы не найдены");
            return;
        }
        System.out.println("Уникальные авторы:");
        for (String author : uniqueAuthors) {
            System.out.println("  " + author);
        }
    }

    public void printAuthorStatistics() {
        if (authorBookCount.isEmpty()) {
            System.out.println("В библиотеку ещё не добавили книг.");
            return;
        }
        System.out.println("Количество книг по авторам:");
        for (Map.Entry<String, Integer> entry : authorBookCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
}

public class LibraryTest {
    public static void main(String[] args) {
        Library library = new Library();

        Book b1 = new Book("Бог всегда путешествует инкогнито", "Лоран Гунель", 2007);
        Book b2 = new Book("Человек, который хотел быть счастливым", "Лоран Гунель", 2010);
        Book b3 = new Book("Эссенциализм", "Грег Маккеон", 2014);
        Book b4 = new Book("Трансерфинг реальности", "Вадим Зеланд", 2004);
        Book b5 = new Book("Богатый папа бедный папа", "Роберт Кийосаки", 1997);
        Book b6 = new Book("Самый богатый человек в Вавилоне", "Джорж Клейсон", 1926);
        Book b7 = new Book("КармаLogic", "Алексей Ситников", 2015);
        Book b8 = new Book("Тонкое искусство пофигизма", "Марк Мэнсон", 2016);
        Book b9 = new Book("Автобиография", "Уилл Смитт", 2021);
        Book b10 = new Book("48 законов власти", "Роберт Грин", 1998);

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);
        library.addBook(b5);
        library.addBook(b6);
        library.addBook(b7);
        library.addBook(b8);
        library.addBook(b9);
        library.addBook(b10);

        library.addBook(b1);

        System.out.println();
        library.printAllBooks();

        System.out.println();
        library.printUniqueAuthors();

        System.out.println();
        library.printAuthorStatistics();

        System.out.println();
        System.out.println("Книги автора Лорана Гунеля:");
        List<Book> booksLaurent = library.findBooksByAuthor("Лоран Гунель");
        for (Book b : booksLaurent) {
            System.out.println("  " + b);
        }

        System.out.println();
        System.out.println("Книги, написанные в 2016 году:");
        List<Book> yearBooks = library.findBooksByYear(2016);
        for (Book b : yearBooks) {
            System.out.println("  " + b);
        }

        System.out.println();
        System.out.println("Удаляем книгу: " + b3);
        library.removeBook(b3);

        System.out.println();
        library.printAllBooks();
        System.out.println();
        library.printUniqueAuthors();
        System.out.println();
        library.printAuthorStatistics();

        System.out.println();
        System.out.println("Попробуем удалить несуществующую книжку");
        library.removeBook(new Book("Тест", "Тест", 2020));
    }
}