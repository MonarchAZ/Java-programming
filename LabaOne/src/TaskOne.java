// Зуев Артур Вадимович, 3МО, 1 группа, 1 подгруппа
// Задание 1. Сиракузская последовательность

import java.util.Scanner;

public class TaskOne
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введи число: ");
        int num = scanner.nextInt();

        if (num <= 0)
        {
            System.out.println("Это отрицательное число, введи то что > 0!");
            return;
        }
        int steps = 0;

        while (num != 1)
        {
            if (num % 2 == 0)
            {
                num = num / 2;
            }
            else
            {
                num = 3 * num + 1;
            }
            steps++;
        }
        System.out.println("Кол-во шагов до единицы: " + steps);
        scanner.close();
    }
}
