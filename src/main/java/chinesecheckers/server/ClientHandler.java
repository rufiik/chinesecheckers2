package chinesecheckers.server;
import java.io.*;
import java.net.*;

import chinesecheckers.patterns.Observer;

public class ClientHandler implements Observer {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private final int playerId;
    private final int maxPlayers;

    public ClientHandler(Socket socket, int playerId, int maxPlayers) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.playerId = playerId;
        this.maxPlayers = maxPlayers;
        sendMessage("Witaj, Graczu " + playerId + "!");
        sendMessage("PLAYER_ID:" + playerId);
        sendMessage("Liczba graczy:" + maxPlayers);
    }

    @Override
    public void update(String message) {
        sendMessage(message);
    }
    
    public void sendMessage(String message) {
            out.println(message);
    }
    
    public void sendGameState(Board board) {
        out.println("Stan planszy:" + board.toString());
    }

    public String receiveMessage() {
        try {
            String message = in.readLine();
            if (message != null && !message.isEmpty()) {
                return message;
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas odbierania wiadomości: " + e.getMessage());
        }
        return null;
    }

    public boolean isConnected() {
        try {
            socket.sendUrgentData(0xFF);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getPlayerId() {
        return playerId;
    }
    public int getMaxPlayers() {
        return maxPlayers;
    }
    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }
}