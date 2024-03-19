package org.lostarmy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {
    public ClientHandler(Socket clientSocket) throws IOException {
        in = new BufferedReader(new java.io.InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }
    private static BufferedReader in;
    private static PrintWriter out;
    public void print(String text){
        out.print(text);
    }
    public void println(String text){
        out.println(text);
    }
    public String readLine() throws IOException {
        return in.readLine();
    }
    public void close() throws IOException {
        in.close();
        out.close();
    }
}
