import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/*В файле содержится строка с данными:
[{"фамилия":"Иванов","оценка":"5","предмет":"Математика"}, {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"}, {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
Напишите метод, который разберёт её на составные части и, используя StringBuilder создаст массив строк такого вида:
Студент Иванов получил 5 по предмету Математика.
Студент Петрова получил 4 по предмету Информатика.
Студент Краснов получил 5 по предмету Физика. */

public class Task3 {
    public static void main(String[] args) {
        String dateBase = readFile(); // считываем файл в строку
        // System.out.println(dateBase);
        String[] st = arrayOfDates(dateBase); // преобразуем строку в массив строк.
        stringOfInformation(st); //получаем информацию об оценках студентов
    }

    // Метод для считывания информации из файла. Получаем строку.
    public static String readFile() {
        StringBuilder sb = new StringBuilder();
        
        try {
            FileReader myFile = new FileReader("File_Task3.txt");
            BufferedReader reader = new BufferedReader(myFile);
            String line;
            try {
                while ((line = reader.readLine()) != null){
                    sb.append(line).append(System.lineSeparator());
                }
            } catch (IOException VariableDeclaratorId) {
                System.out.println("Ошибочка!");
            }
        }
        catch (FileNotFoundException VariableDeclaratorId) {
            System.out.println("Файл не найден!");
        }
        return sb.toString();        
    }

 
    // Метод для получения массива строк. В качестве аргумента метод получает строку. 
    //Каждая строка в массиве содержит информацию об одном студенте.
    public static String[] arrayOfDates (String str) {
        
        StringBuilder dateBase = new StringBuilder(str);
        StringBuilder newDateBase = new StringBuilder();
        // Boolean flag = true;
               
        while (dateBase.length()>3) {
            int indexStart = dateBase.indexOf("{");
            int indexStop = dateBase.indexOf("}");
            // System.out.printf("(%d, %d) \n",indexStart, indexStop);

            String firstDate = dateBase.substring(indexStart+1, indexStop);
            // System.out.println(firstDate);
            // System.out.println("    ");

            dateBase.delete(0, indexStop+1);
            // System.out.println(dateBase.length());
            // System.out.println(dateBase);
            // System.out.println("    ");

            newDateBase.append(firstDate).append(";");
            // System.out.println(newDateBase);
            // System.out.println("    ");
          
        }
        // System.out.println(newDateBase);
        String myDateBase = newDateBase.toString();
        String[] endDateBase = myDateBase.split(";");
        // for (String line: endDateBase) System.out.println(line);
        // System.out.println(endDateBase.length);

        return endDateBase;
        }

    
    //Метод для получения информации в требуемом формате.   
    public static void stringOfInformation (String [] endDateBase){

        for (int i = 0; i < endDateBase.length; i++) {
            String[] firstStudetInfo = endDateBase[i].substring(1, endDateBase[i].length()-3)
                                                     .replace("\"", "")
                                                     .split(",");
            // System.out.println(firstStudetInfo.length);


            Map<String, String> dictionaryOfStudent = new HashMap<String,String>();
            for (int j = 0; j < firstStudetInfo.length; j++) {
                String[] arrayOfPare = firstStudetInfo[j].split(":");
                dictionaryOfStudent.put(arrayOfPare[0], arrayOfPare[1]);
            } 
            
            String stringForFormInformation = "Студент получил по предмету .";
                        
            StringBuilder builder = new StringBuilder(stringForFormInformation);
            StringBuilder newBuilder = new StringBuilder();

            int indexOne = builder.indexOf(" ");
            newBuilder = builder.insert(indexOne + 1 , dictionaryOfStudent.get("фамилия") + " ");

            int indexTwo = newBuilder.indexOf("ил");
            newBuilder = newBuilder.insert(indexTwo + 3 , dictionaryOfStudent.get("оценка") + " ");

            int indexThree = builder.indexOf(".");
            newBuilder = newBuilder.insert(indexThree , dictionaryOfStudent.get("предмет") + "ка");

            System.out.println(builder);
        }
         
    }
}


