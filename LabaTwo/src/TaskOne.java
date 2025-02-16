// 3МО, Зуев Артур Вадимович, 1 подгруппа

import java.util.Scanner;
import java.util.HashSet;

public class TaskOne {
    public static String taskOne(String str)
    {
        String longest = "";            // Наибольшая подстрока
        String currStr = "";            // Текущая
        HashSet<Character> charSet = new HashSet<>(); // Мн-во уникальных символов

        // Проход по каждому символу в строке
        for (char c : str.toCharArray())
        {   // Если символ не содержится в множестве - добавляем его туда
            if (!charSet.contains(c))
            {
                charSet.add(c);
                currStr += c; // Добавление символа к текущей подстроке

                // Проверка на длинную подстроку
                if (currStr.length() > longest.length())
                {
                    longest = currStr;
                }
            }
            else
            {
                // Если символ уже в множестве
                while (currStr.charAt(0) != c)
                {
                    charSet.remove(currStr.charAt(0));
                    currStr = currStr.substring(1);
                }
                // Выкидываем повторяющийся символ
                currStr = currStr.substring(1);
                currStr += c;
            }
        }
        return longest;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введи строку: ");
        String input = scanner.nextLine();
        String result = taskOne(input);
        System.out.println("Наибольшая подстрока с уникальными символами: " + result);
        scanner.close();
    }
}
