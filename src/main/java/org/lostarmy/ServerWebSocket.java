package org.lostarmy;
import org.lostarmy.screen.ScreenHandler;
import org.lostarmy.utils.HandlersManager;

import java.net.*;
import java.io.*;

public class ServerWebSocket {
    private boolean isRunning;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static PrintWriter out;
    public static void print(String text){
        out.print(text);
    }
    public static void println(String text){
        out.println(text);
    }
    private static BufferedReader in;
    public static String readLine() throws IOException {
        return in.readLine();
    }
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        isRunning = true;
        while (isRunning) {
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> handleClient(clientSocket)).start();
        }
    }
    private void handleClient(Socket clientSocket) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Welcome to Lost Army ceated by Wiktor Małyska!");
            out.println("Type start to start game or exit to exit");

            boolean exit = false;
            while (!exit) {
                String readLine = in.readLine();
                switch (readLine) {
                    case "start" -> {
                        int mapX = 50;
                        int mapY = 50;
                        int screenX = 13;
                        int screenY = 100;
                        ScreenHandler screenHandler = new ScreenHandler(screenX, screenY, true);
                        HandlersManager.init(screenHandler, mapX, mapY);

                        exit = true;
                    }
                    case "exit" -> {
                        out.println("Game exited");
                        exit = true;
                    }
                    default -> out.println("Invalid command");
                }
                out.println(readLine);
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void stop() throws IOException {
        isRunning = false;
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException {
        ServerWebSocket server=new ServerWebSocket();
        server.start(6666);
    }
}
