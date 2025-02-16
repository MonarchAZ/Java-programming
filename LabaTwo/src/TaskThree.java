import java.util.Scanner;

public class TaskThree {
    public static int sumArr(int[] arr)
    {
        int sum = arr[0];        // Cумма подмассива
        int sumCurrArr = arr[0]; // Сумма подмассива в текущем элементе

        for (int i = 1; i < arr.length; i++)
        {
            sumCurrArr = Math.max(arr[i], sumCurrArr + arr[i]);
            sum = Math.max(sum, sumCurrArr);
        }
        return sum;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи количество элементов массива: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        System.out.println("Введи элементы массива:");
        for (int i = 0; i < size; i++)
        {
            arr[i] = scanner.nextInt();
        }

        int maxSum = sumArr(arr);
        System.out.println("Максимальная сумма подмассива: " + maxSum);
        scanner.close();
    }
}
