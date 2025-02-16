import java.util.Scanner;

public class TaskFive {
    public static int[] coupleEl(int[] arr, int target)
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[i] + arr[j] == target)
                {
                    return new int[]{arr[i], arr[j]};
                }
            }
        }
        return null;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи размер массива: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        System.out.println("Введи элементы массива:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Введи значение: ");
        int target = scanner.nextInt();

        int[] result = coupleEl(arr, target);
        if (result != null) {
            System.out.println("Пара нашлась: " + result[0] + " и " + result[1]);
        } else {
            System.out.println("Пары не существует");
        }
        scanner.close();
    }
}
