// Задание 2. Сумма ряда

import java.util.Scanner;

public class TaskTwo
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи количество чисел в ряде: ");
        int count = scanner.nextInt();  // кол-во чисел в ряде
        if (count <= 0)
        {
            System.out.println("Так нельзя. Оно должно быть положительным!");
            return;
        }

        int sum = 0; // наша будущая сумма знакочередующего ряда

        for (int i = 0; i < count; i++)
        {
            System.out.print("Введи число №" + (i + 1) + ": ");
            int num = scanner.nextInt();

            if (i % 2 == 0)
            {
                sum += num;
            }
            else
            {
                sum -= num;
            }
        }
        System.out.println("Сумма этих чисел (знакочередующаяся): " + sum);
        scanner.close();
    }
}