// Задание 4. Грузоперевозки

import java.util.Scanner;

public class TaskFour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи общее кол-во дорог: ");
        int sumRoads = scanner.nextInt();

        int maxH = 0;       // максимальная высота
        int topRoad = -1;   // лучшая дорога

        for (int numRoad = 1; numRoad <= sumRoads; numRoad++)
        {
            System.out.print("Введи кол-во туннелей для дороги №" + numRoad + ": ");
            int numTun = scanner.nextInt();
            int minH = Integer.MAX_VALUE;

            for (int tun = 0; tun < numTun; tun++)
            {
                System.out.print("Введи высоту туннеля №" + (tun + 1) + ": ");
                int tunH = scanner.nextInt();
                if (tunH < minH)
                {
                    minH = tunH;
                }
            }

            // Проверка на лучшую дорогу
            if (minH > maxH)
            {
                maxH = minH;
                topRoad = numRoad;
            }
        }
        System.out.println("Лучшая дорога: №" + topRoad);
        System.out.println("Допустимая высота: " + maxH);
        scanner.close();
    }
}
