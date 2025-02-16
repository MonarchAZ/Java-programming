import java.util.Scanner;

public class TaskSeven {
    public static int[] searchMaxEl(int[][] arr)
    {
        int[] maxEl = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            int max = arr[i][0];
            for (int j = 1; j < arr[i].length; j++)
            {
                if (arr[i][j] > max)
                {
                    max = arr[i][j];
                }
            }
            maxEl[i] = max;
        }
        return maxEl;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи кол-во строк: ");
        int rows = scanner.nextInt();
        System.out.print("Введи кол-во столбцов: ");
        int cols = scanner.nextInt();

        int[][] arr = new int[rows][cols];

        System.out.println("Введи элементы массива:");
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                arr[i][j] = scanner.nextInt();
            }
        }

        int[] maxEl = searchMaxEl(arr);

        System.out.println("Максимальные числа построчно:");
        for (int max : maxEl)
        {
            System.out.println(max);
        }
    }
}

