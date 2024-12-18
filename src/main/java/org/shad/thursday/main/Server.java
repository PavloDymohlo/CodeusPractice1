package org.shad.thursday.main;

import org.shad.thursday.warmup.Request;
import org.shad.thursday.warmup.RequestHandler;
import org.shad.thursday.warmup.entity.Response;
import org.shad.thursday.warmup.EndpointInvoker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 8080;


    public Server() {
    }

    public static void main(String[] args) {
        new Server().run();
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket connection = serverSocket.accept();
                handleConnection(connection);
                System.out.println("Server start");
            }
        } catch (IOException e) {
            System.out.println("Could not start server: " + e.getMessage());
        }
    }

    private void handleConnection(Socket connection) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                PrintStream pout = new PrintStream(connection.getOutputStream())
        ) {
            Request request = new RequestHandler().parseRequest(in);
            Response response = new EndpointInvoker("org.shad").handleRequest(request);
            pout.print(response.serialize());

        } catch (IOException e) {
            System.out.println("Error reading request: " + e.getMessage());
        } finally {
            try {
                connection.close();
                System.out.println("Finish connection");
            } catch (IOException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
