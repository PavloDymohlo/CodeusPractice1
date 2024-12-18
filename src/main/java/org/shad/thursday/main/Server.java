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

    //todo Implement fields

    /**
     * Initializes the server with a fixed thread pool.
     */
    public Server() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Main entry point for starting the server.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Starts the server and listens for incoming connections on the specified port.
     * For each connection, a task is submitted to a thread pool for handling.
     */
    public void run() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Handles an individual client connection.
     *
     * @param connection The client socket connection.
     */
    private void handleConnection(Socket connection) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}