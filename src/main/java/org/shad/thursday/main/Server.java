package org.shad.thursday.main;

import org.shad.thursday.warmup.Request;
import org.shad.thursday.main.entity.Response;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * A multithreaded HTTP server that processes client requests and sends responses.
 * The server uses a fixed thread pool to handle incoming connections concurrently.
 */
public class Server {

    private static final int PORT = 8080;
    private static final int THREAD_POOL_SIZE = 10;
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    private final ExecutorService executorService;

    /**
     * Initializes the server with a fixed thread pool.
     */
    public Server() {
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    /**
     * Main entry point for starting the server.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Server().run();
    }

    /**
     * Starts the server and listens for incoming connections on the specified port.
     * For each connection, a task is submitted to a thread pool for handling.
     */
    public void run() {
        logger.info("Server starting on port " + PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutting down server...");
            executorService.shutdown();
        }));

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket connection = serverSocket.accept();
                executorService.submit(() -> handleConnection(connection));
                logger.info("Accepted new connection.");
            }
        } catch (IOException e) {
            logger.severe("Could not start server: " + e.getMessage());
        }
    }

    /**
     * Handles an individual client connection.
     *
     * @param connection The client socket connection.
     */
    private void handleConnection(Socket connection) {
        logger.info("Handling connection from: " + connection.getRemoteSocketAddress());

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                PrintStream pout = new PrintStream(connection.getOutputStream())
        ) {
            try {
                Request request = new RequestHandler().parseRequest(in);
                Response response = new EndpointInvoker("org.shad").handleRequest(request);
                pout.print(response.serialize());
                logger.info("Sent response: " + response.serialize());
            } catch (IllegalArgumentException e) {
                String badRequestResponse =
                        "HTTP/1.0 400 Bad Request\r\n" +
                                "Content-length: 0\r\n" +
                                "\r\n";
                pout.print(badRequestResponse);
                logger.warning("400 Bad Request: " + e.getMessage());
            }
        } catch (IOException e) {
            logger.severe("Error processing request: " + e.getMessage());
        } finally {
            try {
                connection.close();
                logger.info("Connection closed.");
            } catch (IOException e) {
                logger.severe("Error closing connection: " + e.getMessage());
            }
        }
    }
}