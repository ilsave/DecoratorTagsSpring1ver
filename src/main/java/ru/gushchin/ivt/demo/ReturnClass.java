package ru.gushchin.ivt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("ReturnClass")
public class ReturnClass implements ReturnInterface{
    StringBuilder line ; // Строка из файла

    @Autowired
    public ReturnClass( @Value("line1") StringBuilder line) {
        this.line = line;
    } // Конструктор, инициализирует строку


//    public  void Foo(StringBuilder line){
//        this.line = line;
//    }


    @Override
    public StringBuilder sendBackLine() {
        return line;
    } // Возвращает строку
}
