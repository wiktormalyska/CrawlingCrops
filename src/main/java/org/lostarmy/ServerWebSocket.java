package org.lostarmy;
import org.lostarmy.screen.ScreenHandler;
import org.lostarmy.utils.HandlersManager;

import java.net.*;
import java.io.*;

public class ServerWebSocket {
    private boolean isRunning;
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        isRunning = true;
        System.out.println("LostArmy Hosting Server started on port " + port);
        while (isRunning) {
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> handleClient(clientSocket)).start();
        }
    }
    private void handleClient(Socket clientSocket) {
        try {
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandler.println("Welcome to Lost Army ceated by Wiktor Małyska!");
            clientHandler.println("");
            clientHandler.println("Type start to start game or exit to exit");

            boolean exit = false;
            while (!exit) {
                String readLine = clientHandler.readLine();
                switch (readLine) {
                    case "start" -> {
                        int mapX = 50;
                        int mapY = 50;
                        int screenX = 13;
                        int screenY = 100;
                        ScreenHandler screenHandler = new ScreenHandler(screenX, screenY, true, clientHandler);
                        HandlersManager.init(screenHandler, mapX, mapY);

                        exit = true;
                    }
                    case "exit" -> {
                        clientHandler.println("Game exited");
                        exit = true;
                    }
                    default -> clientHandler.println("Invalid command");
                }
                //clientHandler.println(readLine);
            }

            clientHandler.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) throws IOException {
        ServerWebSocket server=new ServerWebSocket();
        server.start(6666);
    }
}
