package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@Log
public class Main {
    public static void main(String[] args) throws IOException {

        try {
            InetSocketAddress localhost = new InetSocketAddress("localhost", 3306);
            HttpServer httpServer = HttpServer.create(localhost, 0);

            // http://localhost:1234/

            httpServer.createContext("/", new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    byte[] bytes = "Hello".getBytes();

                    // ГОТОВЛЮ ЗАГОЛОВКИ ОТВЕТА
                    exchange.sendResponseHeaders(200, bytes.length);

                    // ГОТОВЛЮ ТЕЛО ОТВЕТА
                    OutputStream responseBody = exchange.getResponseBody();
                    responseBody.write(bytes);
                    log.info("Запрос получен, ответ отправлен");
                    responseBody.close();
                }
            });
            httpServer.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}