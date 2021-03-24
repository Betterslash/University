package ro.ubb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.Ui.ClientConsole;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ro.ubb.Config");
        ClientConsole clientConsole = context.getBean(ClientConsole.class);
        clientConsole.run();
    }
}
