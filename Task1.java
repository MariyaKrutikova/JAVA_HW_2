import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*В файле содержится строка с исходными данными в такой форме:
{"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
Требуется на её основе построить и вывести на экран новую строку, в форме SQL запроса:
SELECT * FROM students WHERE name = "Ivanov" AND country = "Russia" AND city = "Moscow";
Для разбора строки используйте String.split. Сформируйте новую строку, используя StringBuilder. 
Значения null не включаются в запрос*/

public class Task1 {
    public static void main(String[] args) {
        String date = ReadFile();
        getSQLrequest(date); 
    }

    // Метод чтения данных их файла. Считывает данные из файла и выводит их  ввиде строки.
    public static String ReadFile(){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода файла!");
            // e.printStackTrace();
        }
        
        // System.out.println(sb);
        return sb.toString();
    }

    // Метод, выдающий строку, в виде SQL-запроса. 
    private static void getSQLrequest (String stringFromFile) {
        // Преобразуем строку из файла в массив строк. При этом убираем фигурные скобки и кавычки. Элементы массива строк разбиваем по ","
        String[] arrayOfDate = stringFromFile.substring(1, stringFromFile.length()-3)
                                             .replace("\"", "")
                                             .split(", ");
        // System.out.println(arrayOfDate.length);
        
        //Создаем словарь из массива строк.
        Map<String,String> dictionaryOfDate = new HashMap<String,String>();
        for (int i = 0; i < arrayOfDate.length; i++) {
            String[] arrayOfPair = arrayOfDate[i].split(":");
            dictionaryOfDate.put(arrayOfPair[0], arrayOfPair[1]);
        }
        

        //Формируем строку в виде SQL-запроса
        String str = "SELECT * FROM students WHERE "; // Задаем начало строка
        for (Map.Entry<String, String> entry: dictionaryOfDate.entrySet()) { //Проходим по всем парам словаря
        if (!entry.getValue().equals("null")) { //Смотрим не ровно ли значение из словаря null, если не равно, то добавляем к строке
            str = str + " " + entry.getKey() + " = " + "\"" + entry.getValue() + "\"";
            }
        }
        System.out.println(str);
    } 
}

