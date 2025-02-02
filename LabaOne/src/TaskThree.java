// Задание 3. Клад

import java.util.Scanner;

public class TaskThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи координаты клада (x y): ");
        int axisX = scanner.nextInt();      // координаты клада
        int axisY = scanner.nextInt();
        scanner.nextLine();

        int currX = 0;              // начальные координаты игрока
        int currY = 0;

        int instructionCount = 0;   // число инструкций для достижения клада

        // Ввод направлений
        while (true)
        {
            System.out.print("Введи направление (север, восток, юг, запад) или 'стоп': ");
            String direction = scanner.nextLine().trim();

            if (direction.equalsIgnoreCase("стоп"))
            {
                break;
            }

            // Количество шагов
            System.out.print("Введи кол-во шагов: ");
            String stepsLine = scanner.nextLine().trim();
            int steps = Integer.parseInt(stepsLine);

            // Считаем *текущие* координаты
            switch (direction.toLowerCase())
            {
                case "север":
                    currY += steps;
                    break;
                case "юг":
                    currY -= steps;
                    break;
                case "восток":
                    currX += steps;
                    break;
                case "запад":
                    currX -= steps;
                    break;
                default:
                    System.out.println("Такого направления не существует.");
                    continue;
            }
            instructionCount++;

            // Достижение клада
            if (currX == axisX && currY == axisY)
            {
                System.out.println("Число инструкций чтобы достичь клада: " + instructionCount);
                return;
            }
        }
        System.out.println("Клад не найден. Число инструкций: " + instructionCount);
        scanner.close();
    }
}