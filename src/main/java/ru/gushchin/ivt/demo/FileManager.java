package ru.gushchin.ivt.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Этот класс реализует всю работу с файлами
public class FileManager {

    // Функция возвращает путь к файлу, который пользователь введет
    static String getPath(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Пожалуйста, введите путь к файлу, который вы бы хотели перевести в HTML формат: ");
        String path = reader.nextLine(); // Читается вся строка
        File file = new File(path);
        while (!file.exists()) {
            System.out.println("Такого файла не существует! Попробуйте указать другой путь: ");
            path = reader.nextLine();
            file = new File(path);
        }
        return path;

    }

    // Функция считывае исходный код и помещает его в список
    static List<String> makeList(String path){
        BufferedReader br = null;
        List<String> list = new LinkedList<>(); // Список, в который читаются все строки файла
        try {
            // Чтение из одного файла
            br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                int count_spaces = 0; // Счетчик пробелов
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) != ' ') { // Если встречается не пробел, то цикл завершается
                        break;
                    } else if (line.charAt(i) == ' ') { // Если встречается пробел, то счетчик пробелов увеличивается на 1
                        count_spaces++;
                    }
                }
                StringBuffer buf = new StringBuffer(line);
                for (int j = 0; j < count_spaces; j++) { // В строку вставляется соответствующее количество пробелов в начало
                    buf.insert(0, "&nbsp");
                }
                line = buf.toString();
                list.add(line); // Строка помещается в список всех строк
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return list;
    }

    // Функция создает файл, в который будут выведены результаты работы
    static File checkFile() throws IOException{
        File file = new File("result.txt"); // Обозначаем файл для записи
        if (!file.exists())
            file.createNewFile(); // Если файла нет, то он создается
        return file;
    }

    // Записывает весь список строк в файл, добавляя HTML разметку
    // Записывает весь список строк в файл, добавляя HTML разметку
    static void PrintInFile(List<String> list, File file){
        String line2 =
                "<head>" +
                        "<title>" +
                        "Hey There!" + // Первая часть разметки
                        "</title>" +
                        "</head>" +
                        "<body>";
        String line3 =
                "\n" +
                        " </body>\n" + // Вторая часть разметки
                        "</html>";
        try {
            if (!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            pw.print(line2); // Первая часть разметки пишется в файл
            for (String line1 : list){
                pw.print("<br>");
                // Применяем все возможные декораторы для строки и записываем в файл
                ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
                ReturnInterface returnInt = ctx.getBean("FinalDecorator", FinalDecorator.class);
                pw.print(new ReturnDecorator(new FinalDecorator(new PrivateDecorator(new ProtectedDecorator(new PublicDecorator(new StaticDecorator(new VoidDecorator(new ReturnClass(new StringBuilder(line1))))))))).sendBackLine().toString()); // Каждая строка из списка пишется в файл
                pw.println("</br>");
            }
            pw.print(line3); // Вторая часть разметки пишется в файл
            pw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Функция создает копию .txt файла и меняет расширение на .html
    static void CopyToHTML(File source)throws IOException{
        InputStream is = null;
        OutputStream os = null;
        File dest = new File("result.html"); // Файл, куда будет записан html код
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length); // Копирование файла в новый
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            is.close();
            os.close();
        }
        System.out.println("Работа завершена, результат можно увидеть в файле " + dest.getAbsolutePath().toString());

    }

}
