package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@Log
public class MainAgain {
    public static void main(String[] args) {

        try {
            InetSocketAddress localhost = new InetSocketAddress("127.0.0.2", 1234);
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
