package fr.idmc.raizo;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Pour lire les données du client
            out = new PrintWriter(clientSocket.getOutputStream(), true); // Pour envoyer des données au client

            // Initial handshake
            out.println("WHO_ARE_YOU_?");
            String response = in.readLine();
            if ("ITS_ME".equals(response)) {
                out.println("GIMME_PASSWORD");
                String password = in.readLine(); // Normalement, vous vérifieriez ce mot de passe
                if (isValidPassword(password)) {
                    out.println("HELLO_YOU");
                    handleClient(); // Gérer les commandes du client
                } else {
                    out.println("YOU_DONT_FOOL_ME");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isValidPassword(String password) {
        // Remplacez par la logique réelle de validation du mot de passe
        return "azerty".equals(password);
    }

    private void handleClient() throws IOException {
        out.println("OK");
        // Logique principale pour gérer les commandes du client
        String clientMessage;
        while ((clientMessage = in.readLine()) != null) {
            // Traitez le message du client
            if (clientMessage.startsWith("READY")) {
                // Traitez la commande READY
            } else if (clientMessage.startsWith("FOUND")) {
                // Traitez la commande FOUND
                String[] parts = clientMessage.split(" ");
                String hash = parts[1];
                String nonce = parts[2];
                // Validez et traitez la solution trouvée
            }
            // Traitez d'autres commandes
        }
    }
}