import java.util.Scanner;

public class TaskTwo {
    public static int[] unArrs(int[] arr1, int[] arr2)
    {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] unArr = new int[n1 + n2]; // Объединённый массив

        int i = 0, j = 0, k = 0;        // Индексы для исходных и объединённых массивов
        // Объединение
        while (i < n1 && j < n2)
        {
            if (arr1[i] < arr2[j])
            {
                unArr[k++] = arr1[i++];
            }
            else
            {
                unArr[k++] = arr2[j++];
            }
        }
        while (i < n1)
        {
            unArr[k++] = arr1[i++];
        }
        while (j < n2)
        {
            unArr[k++] = arr2[j++];
        }
        return unArr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введи кол-во элементов первого массива: ");
        int size1 = scanner.nextInt();
        int[] arr1 = new int[size1];
        System.out.println("Введи элементы первого массива (в отсортированном порядке):");
        for (int i = 0; i < size1; i++)
        {
            arr1[i] = scanner.nextInt();
        }

        System.out.print("Введи кол-во элементов второго массива: ");
        int size2 = scanner.nextInt();
        int[] arr2 = new int[size2];
        System.out.println("Введи элементы второго массива (в отсортированном порядке):");
        for (int i = 0; i < size2; i++)
        {
            arr2[i] = scanner.nextInt();
        }

        int[] unArr = unArrs(arr1, arr2);

        System.out.println("Объединенный массив:");
        for (int element : unArr)
        {
            System.out.print(element + " ");
        }
        scanner.close();
    }
}
