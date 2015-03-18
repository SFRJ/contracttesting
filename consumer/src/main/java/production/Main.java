package production;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Consumer consumer = new Consumer();
        String providersUrl = "http://localhost:8888/provider";
        System.out.println(consumer.getValueFromProvider(providersUrl));
    }
}
