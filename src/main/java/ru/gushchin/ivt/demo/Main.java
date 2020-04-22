package ru.gushchin.ivt.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        // Коротко, но понятно
        //String path = FileManager.getPath(); // Пользователь указывает путь к файлу с кодом
        //File result_file = FileManager.checkFile(); // Создается файл, в который будут записаны результаты
        //List<String> list = FileManager.makeList(path); // Создается список всех строк кода из исходного файл
        //FileManager.PrintInFile(list, result_file); // Все строки записываются в .txt файл
        //FileManager.CopyToHTML(result_file); // Создается копия .txt файла, но уже с расширением .html

        // Максимально коротко
        FileManager.CopyToHTML(FileManager.PrintInFile(FileManager.makeList(FileManager.getPath()), FileManager.checkFile()));

    }
}

