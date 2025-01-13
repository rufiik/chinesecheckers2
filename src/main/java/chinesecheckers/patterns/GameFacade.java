package chinesecheckers.patterns;

import chinesecheckers.client.ClientGUI;
import chinesecheckers.client.GameClient;
import chinesecheckers.server.Board;

public class GameFacade {
    private Board board;
    private ClientGUI clientGUI;
    private GameClient gameClient;

    public GameFacade(Board board, ClientGUI clientGUI, GameClient gameClient) {
        this.board = board;
        this.clientGUI = clientGUI;
        this.gameClient = gameClient;
    }

    public void initializeGame(int maxPlayers) {
        board.initializeOpponentBaseMapping(maxPlayers);
        clientGUI.initialize();
    }

    public void updateGameState(String gameState) {
        board.update(gameState);
        clientGUI.repaint();
    }

    public void sendMove(int startX, int startY, int endX, int endY) {
        gameClient.sendMove(startX, startY, endX, endY);
    }

    public void skipTurn() {
        gameClient.skipTurn();
    }

    public void showPlayerTurnMessage() {
        clientGUI.showPlayerTurnMessage();
    }

    public void updateStandings(String rankMessage) {
        clientGUI.updateStandings(rankMessage);
    }

    public void endPlayerTurn() {
        clientGUI.endPlayerTurn();
    }

    public void endGame() {
        clientGUI.endGame();
    }
}
