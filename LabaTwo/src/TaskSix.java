import java.util.Scanner;

public class TaskSix {
    public static int sumEl(int[][] arr)
    {
        int sum = 0;
        for (int[] row : arr)
        {
            for (int el : row)
            {
                sum += el;
            }
        }
        return sum;
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
        int sum = sumEl(arr);
        System.out.println("Сумма: " + sum);
    }
}

