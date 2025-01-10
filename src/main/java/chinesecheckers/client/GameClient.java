package chinesecheckers.client;

import java.io.*;
import java.net.*;
import javax.swing.SwingUtilities;
import chinesecheckers.server.Board;

public class GameClient {
    private final String host;
    private final int port;
    private boolean isPlayerTurn = false;
    private boolean isConnected = true;
    private ServerGUI boardGUI;
    private Board board;

    public GameClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Połączono z serwerem!");

            board = new Board();
            SwingUtilities.invokeLater(() -> {
                boardGUI = new ServerGUI(board);
            });

            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        synchronized (System.out) {
                            if (serverMessage.equals("Serwer został zamknięty.")) {
                                System.out.println(serverMessage);
                                stopConnection();
                                break;
                            } else if (serverMessage.startsWith("Twoja tura!")) {
                                isPlayerTurn = true;
                                SwingUtilities.invokeLater(() -> {
                                    boardGUI.showPlayerTurnMessage();
                                });
                            } else if (serverMessage.equals("Gra już się rozpoczęła.")) {
                                closeConnection();
                                break;
                            } else if (serverMessage.startsWith("Stan planszy:")) {
                                String gameState = serverMessage.substring("Stan planszy:".length()).trim();
                                board.update(gameState);
                                SwingUtilities.invokeLater(() -> {
                                    boardGUI.repaint();
                                });
                            } else {
                                System.out.println(serverMessage);
                            }
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Połączenie z serwerem zostało przerwane: " + e.getMessage());
                } finally {
                    isConnected = false;
                    stopConnection();
                }
            }).start();

            while (isConnected) {
                if (isPlayerTurn) {

                    isPlayerTurn = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Nie można połączyć się z serwerem! Sprawdź, czy serwer jest uruchomiony i spróbuj ponownie.");
            System.exit(1);
        }
    }

    public void stopConnection() {
        isConnected = false;
        System.out.println("Rozłączono z serwerem.");
    }

    public void closeConnection() {
        isConnected = false;
        System.out.println("Gra już się rozpoczęła.");
    }

    public static void main(String[] args) {
        GameClient client = new GameClient("localhost", 12345);
        client.start();
    }
}