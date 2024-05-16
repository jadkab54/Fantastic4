package fr.idmc.raizo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CentralServer {
    private static final int PORT = 1337;
    private static final int MAX_CLIENTS = 100;
    private ExecutorService clientThreads;

    public CentralServer() {
        clientThreads = Executors.newFixedThreadPool(MAX_CLIENTS);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientThreads.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CentralServer server = new CentralServer();
        server.start();
    }
}