package ru.gushchin.ivt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("PublicDecorator")
@Lazy
@Scope("singleton")
public class PublicDecorator extends Decorator {

    @Autowired
    public PublicDecorator(@Lazy @Qualifier("StaticDecorator") ReturnInterface component) {
        super(component);
    }

    @Override
    public StringBuilder sendBackLine() {
        String[] words = String.valueOf(component.sendBackLine()).split(" ");
        StringBuilder a = new StringBuilder();
        int b = 1;
        for (String line : words){
            if ("public".equals(line)){
                break;
            }
            b++;
        }

        int i = 0;
        for (String ln : words){
            if(b - i == 1){
                a.append("<i><strong> <font color='green' face='Compact'>");
            }
            i++;
            if ("public".equals(ln)){
                a.append(ln);
                a.append("</font></strong></i>");
                continue;
            }
            a.append(" ");
            a.append(ln);
        }
        return a;
    }
}
