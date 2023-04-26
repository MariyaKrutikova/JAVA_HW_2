import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*  Реализуйте алгоритм сортировки пузырьком числового массива, 
результат после каждой итерации запишите в лог-файл. */

public class Task2 {
 public static void main(String[] args) {
    int[] array = creatNewArray();
    printArray(array);
    int[] sortArray = bubbleSort(array);
    System.out.println("   ");
    printArray(sortArray);           
}

/*Метод для создания массива. Пользователь указывает размерность массива 
и последовательно вводит каждый элемент масииива */ 
private static int[] creatNewArray () {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Укажите размерность массива: ");
    int dimention = scanner.nextInt(); 
    int [] array = new int[dimention];
    for (int i = 0; i < array.length; i++) {
        System.out.printf("Введите %d-е число массива: ", i);
        int number = scanner.nextInt();
        array[i] = number;
    }
    scanner.close();
    return array;
 }

 /*Метод пузырьковой сортировк с логированием. */
 private static int[] bubbleSort (int [] array) {
    Logger logger = Logger.getLogger(Task2.class.getName());
    
    try {
        FileHandler fh = new FileHandler("logForBubbleSort.txt");
        SimpleFormatter sm = new SimpleFormatter();
        logger.addHandler(fh);
        fh.setFormatter(sm);
    }
    catch (IOException e) {
        System.out.println("Ошибка ввода");
    }

    int basket = 0;
    for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array.length - i - 1; j++) {
            if (array[j] > array[j+1]) {
                basket = array[j];
                array[j] = array[j+1];
                array[j+1] = basket;
            }
            logger.info("Проверили, какой из двух соседних элементов больше и, при необходимости, выполнили перестановку");
        }
        logger.info("Массив после перестановки элементов - " + Arrays.toString(array));    
    } 
    return array;
}

/*Метод для распечатывания массива */ 
private static void printArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
    System.out.println (array[i]);
    }
}

}

