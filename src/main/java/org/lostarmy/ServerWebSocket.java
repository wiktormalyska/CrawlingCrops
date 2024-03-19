package org.lostarmy;

import org.lostarmy.screen.ScreenHandler;
import org.lostarmy.utils.HandlersManager;

import java.net.*;
import java.io.*;

// Server requires .env file with PORT variable in it, for example: PORT=2121
public class ServerWebSocket {

    public void start(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            boolean isRunning = true;
            System.out.println("LostArmy Hosting Server started on port " + port);
            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        ServerWebSocket server = new ServerWebSocket();
        server.start(getPort());
    }

    public static int getPort() throws IOException {
        File file = new File(".env");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int port;
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            String[] splitted = line.split("=");
            if (splitted[0].equals("PORT")) {
                port = Integer.parseInt(splitted[1]);
                return port;
            }
        }
        throw new RuntimeException("Port not found in .env file");
    }
}
