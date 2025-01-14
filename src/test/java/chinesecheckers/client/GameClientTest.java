package chinesecheckers.client;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chinesecheckers.patterns.GameFacade;
import chinesecheckers.server.Board;
public class GameClientTest {
    private GameClient gameClient;
    private GameFacade gameFacade;
    private Board board;
    @BeforeEach
    public void setUp() {
        gameClient = new GameClient("localhost", 12345);
        board = mock(Board.class);
        gameFacade = mock(GameFacade.class);
        gameClient.setVariant("Standard");
    }
}