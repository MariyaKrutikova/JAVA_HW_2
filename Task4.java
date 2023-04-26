import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/*К калькулятору из предыдущего ДЗ добавить логирование.*/

public class Task4 {
    public static void main(String[] args) throws java.io.IOException{

        Logger loger = Logger.getLogger(Task4.class.getName());
        FileHandler fh = new FileHandler("logForCalculator.xml");
        XMLFormatter xml = new XMLFormatter();
        loger.addHandler(fh);
        fh.setFormatter(xml);

        System.out.print("Какую опирацию необходимо выполнить над указанными числами (+, -, /, *): \n");
        char operation = (char) System.in.read();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите первое число:");
        int a = scanner.nextInt();
                   
        System.out.print("Введите второе число:");
        int b = scanner.nextInt();
           
        scanner.close();

        if (operation == '+') {
            System.out.printf("Сумма чисел %d + %d = %d \n", a, b, a + b);
            loger.info("Выполнили сложение");
        }
        else if (operation == '-') {
            System.out.printf("Разность чисел %d - %d = %d \n", a, b, a - b);
            loger.info("Выполнили вычитание");
        }
        else if (operation == '/') {
            if (b == 0) {
                System.out.println("Ошибка! Попытка разделить на ноль.");
                loger.info("Не выполнили деление");
            }
            else {
                System.out.printf("Отношение чисел %d / %d = %d \n", a, b, a/b);
                loger.info("Выполнили деление");
            }
        }
        else if (operation == '*') {
            System.out.printf("Произведение чисел %d * %d = %d \n", a, b, a*b);
            loger.info("Выполнили умножение");
        }
        else {
            System.out.println("Указана некорректная операция. Повторите ввод!");
            loger.info("Некорректный ввод операции");
        }

        loger.info("Завершение работы калькулятора");
    }
}
