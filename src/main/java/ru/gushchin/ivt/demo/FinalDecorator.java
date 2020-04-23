package ru.gushchin.ivt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("FinalDecorator")
@Lazy
@Scope("singleton")
public class FinalDecorator extends Decorator {

    @Autowired
    public FinalDecorator(@Lazy @Qualifier("PrivateDecorator")ReturnInterface component) {
        super(component);
    }

    /*
    Все последующие декораторы работают по идентичному принципу
     */

    @Override
    public StringBuilder sendBackLine() {
        String[] words = String.valueOf(component.sendBackLine()).split(" ");
        StringBuilder a = new StringBuilder();
        int b = 1; // Определяет позицию сразу за искомым словом
        for (String line : words){
            if ("final".equals(line)){
                break;
            }
            b++;
        }
        int i = 0; // Определяет позицию прямо перед искомым словом
        for (String ln : words){
            if(b - i == 1){
                a.append("<i><strong> <font color='purple' face='Compact'>"); // Теги вставляеются передм словом
            }
            i++;
            if ("final".equals(ln)){
                a.append(ln);
                a.append("</font> </strong></i>"); // Теги вставляются после слова
                continue;
            }
            a.append(" ");
            a.append(ln);
        }
        return a;
    }
}

