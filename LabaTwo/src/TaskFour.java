import java.util.Scanner;

public class TaskFour {
    public static int[][] rotateArr(int[][] arr)
    {
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                rotated[j][rows - 1 - i] = arr[i][j];
            }
        }
        return rotated;
    }

    public static int[][] inputArr()
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
        return arr;
    }

    public static void printArr(int[][] arr)
    {
        for (int[] row : arr)
        {
            for (int value : row)
            {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        int[][] arr = inputArr();
        int[][] rotatedArr = rotateArr(arr);
        System.out.println("Перевернутый на 90 массив по часовой:");
        printArr(rotatedArr);
    }
}