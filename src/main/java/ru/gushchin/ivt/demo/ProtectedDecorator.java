package ru.gushchin.ivt.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("ProtectedDecorator")
@Lazy
@Scope("singleton")
public class ProtectedDecorator extends Decorator {
    public ProtectedDecorator( @Lazy @Qualifier("PrivateDecorator") ReturnInterface component) {
        super(component);
    }

    @Override
    public StringBuilder sendBackLine() {
        String[] words = String.valueOf(component.sendBackLine()).split(" ");
        StringBuilder a = new StringBuilder();
        int b = 1;
        for (String line : words){
            if ("protected".equals(line)){
                break;
            }
            b++;
        }
        int i = 0;
        for (String ln : words){

            if(b - i == 1){
                a.append("<i><strong> <font color='orange' face='Compact'>");
            }
            i++;
            if ("protected".equals(ln)){
                a.append(ln);
                a.append("</font> </strong></i>");
                continue;
            }
            a.append(" ");
            a.append(ln);
        }
        return a;
    }
}
