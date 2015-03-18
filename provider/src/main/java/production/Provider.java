package production;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static java.lang.System.lineSeparator;

public class Provider {

    private HttpServer server;
    private final String xmlResponse = "" +
            "<output>" + lineSeparator() +
                "<content>" + lineSeparator() +
                    "<partA>A</partA>" + lineSeparator() +
                    "<partB>B</partB>" + lineSeparator() +
                "</content>" + lineSeparator() +
            "</output>";

    public Provider() throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(8888);
        server = HttpServer.create(socketAddress, 0);
        server.createContext("/provider", providerHandler());
        server.start();
    }

    private HttpHandler providerHandler() {
        return new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                Headers headers = httpExchange.getResponseHeaders();
                headers.add("Content-Type", "application/xml");
                httpExchange.sendResponseHeaders(200, xmlResponse.length());

                OutputStream outputStream = httpExchange.getResponseBody();
                outputStream.write(xmlResponse.getBytes());
                outputStream.close();
                httpExchange.close();
            }
        };
    }

    public void stopServer() {
        server.stop(0);
    }
}
