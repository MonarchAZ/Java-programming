// Задание 5. Дважды чётное число
// Дважды четное: при первом делении на два получаем чётное число
// При следующем делении того числа на 2 тоже должно получиться чётное
// Говоря проще, число дожно нацело делиться на 4, тогда оно дважды чётное

import java.util.Scanner;

public class TaskFive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи число: ");
        int num = scanner.nextInt();
        if (num < 100 || num > 999)
        {
            System.out.println("Число не трехзначное или отрицательно");
            return;
        }

        if (num % 4 == 0)
        {
            System.out.println("Число дважды чётное");
        }
        else
        {
            System.out.println("Число не дважды чётное");
        }
        scanner.close();
    }
}
