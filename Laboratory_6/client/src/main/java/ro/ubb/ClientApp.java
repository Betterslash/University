package ro.ubb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.Ui.ClientConsole;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.Config");
        ClientConsole clientConsole = context.getBean(ClientConsole.class);
        clientConsole.run();
    }
}
