package chinesecheckers.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initializeBoardForPlayers(2);
        board.initializeOpponentBaseMapping(2);
    }

    @Test
    void testInitializeBoard() {
        assertNotNull(board.getBoard());
        assertEquals(17, board.getBoard().length);
        assertEquals(25, board.getBoard()[0].length);

        assertEquals(1, board.getBoard()[3][9]);
        assertEquals(2, board.getBoard()[13][9]);
        assertEquals(7, board.getBoard()[0][0]);
        assertEquals(7, board.getBoard()[16][24]);

    }
    @Test
    void testInitializeBoardFor2Players() {
        board.initializeBoardForPlayers(2);
        board.initializeOpponentBaseMapping(2);
        assertEquals(1, board.getBoard()[3][9]); 
        assertEquals(2, board.getBoard()[13][9]); 
    }

    @Test
    void testInitializeBoardFor3Players() {
        board.initializeBoardForPlayers(3);
        board.initializeOpponentBaseMapping(3);
        assertEquals(1, board.getBoard()[3][9]);
        assertEquals(2, board.getBoard()[13][9]); 
        assertEquals(3, board.getBoard()[12][0]); 
    }

    @Test
    void testInitializeBoardFor4Players() {
        board.initializeBoardForPlayers(4);
        board.initializeOpponentBaseMapping(4);
        assertEquals(1, board.getBoard()[3][9]);
        assertEquals(2, board.getBoard()[13][9]);
        assertEquals(3, board.getBoard()[12][0]); 
        assertEquals(4, board.getBoard()[12][24]); 
    }

    @Test
    void testInitializeBoardFor6Players() {
        board.initializeBoardForPlayers(6);
        board.initializeOpponentBaseMapping(6);
        assertEquals(1, board.getBoard()[3][9]); 
        assertEquals(2, board.getBoard()[13][9]); 
        assertEquals(3, board.getBoard()[12][0]); 
        assertEquals(4, board.getBoard()[12][24]); 
        assertEquals(5, board.getBoard()[4][0]); 
        assertEquals(6, board.getBoard()[4][24]); 
    }
    @Test
    void testInitializeBoardIllegalArgumentException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            board.initializeBoardForPlayers(5);
        });
        assertEquals("Nieprawidłowa ilość graczy: 5", exception.getMessage());
    }

    @Test
    void testMovePiece() {
        String result = board.movePiece(3, 9, 4, 8, 1);
        assertEquals("Ruch wykonany z (3,9) na (4,8).", result);
        assertEquals(1, board.getBoard()[4][8]);
        assertEquals(0, board.getBoard()[3][9]);
    }

    @Test
    void testInvalidMove() {
        String result = board.movePiece(0, 12, 0, 13, 1);
        assertEquals("Nieprawidłowy ruch z (0,12) na (0,13).", result);
    }

    @Test
    void testIsInOpponentBase() {
        assertTrue(board.isInOpponentBase(16, 12, 1));
        assertFalse(board.isInOpponentBase(0, 12, 1));
    }
    @Test
    void testInitializeOpponentBaseMapping() {
        Board board = new Board();
        board.initializeOpponentBaseMapping(2);
        assertArrayEquals(new int[]{1, 0}, board.getOpponentBaseMapping());
        board.initializeOpponentBaseMapping(3);
        assertArrayEquals(new int[]{1, 4, 5}, board.getOpponentBaseMapping());
        board.initializeOpponentBaseMapping(4);
        assertArrayEquals(new int[]{3, 2, 5, 4}, board.getOpponentBaseMapping());
        board.initializeOpponentBaseMapping(6);
        assertArrayEquals(new int[]{1, 0, 5, 4, 3, 2}, board.getOpponentBaseMapping());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            board.initializeOpponentBaseMapping(5);
        });
        assertEquals("Nieprawidłowa ilość graczy: 5", exception.getMessage());
    }
    @Test
    void TestValidMoves(){
        //Test within bounds
        assertTrue(board.isValidMove(3, 9, 4, 8,1));
        // Test valid adjacent move
        board.getBoard()[3][9] = 1; 
        String result = board.movePiece(3, 9, 4, 8, 1);
        assertEquals("Ruch wykonany z (3,9) na (4,8).", result);
        assertEquals(1, board.getBoard()[4][8]);
        assertEquals(0, board.getBoard()[3][9]);

        // Test valid jump move
        board.getBoard()[4][8] = 1; 
        board.getBoard()[5][7] = 2; 
        result = board.movePiece(4, 8, 6, 6, 1);
        assertEquals("Ruch wykonany z (4,8) na (6,6).", result);
        assertEquals(1, board.getBoard()[6][6]);
        assertEquals(0, board.getBoard()[4][8]);

        // Test valid multi-jump move
        board.getBoard()[6][6] = 1; 
        board.getBoard()[7][5] = 2; 
        board.getBoard()[9][3] = 2; 
        result = board.movePiece(6, 6, 10, 2, 1);
        assertEquals("Ruch wykonany z (6,6) na (10,2).", result);
        assertEquals(1, board.getBoard()[10][2]);
        assertEquals(0, board.getBoard()[6][6]);

    }
    @Test
    void TestIsPlayerInOponnentBase(){
        assertTrue(board.isInOpponentBase(16, 12, 1));
        assertTrue(board.isInOpponentBase(0, 12, 2));
        assertFalse(board.isInOpponentBase(0, 12, 1));
    }
    @Test
    void testIsValidMove() {
        board.initializeBoardForPlayers(2);
        board.initializeOpponentBaseMapping(2);

        // Test case: start position is out of bounds
        assertFalse(board.isValidMove(-1, 9, 4, 8, 1));
        assertFalse(board.isValidMove(3, 9, 17, 8, 1));

        // Test case: start position does not have a piece
        assertFalse(board.isValidMove(4, 8, 5, 7, 1));

        // Test case: end position is not empty
        board.getBoard()[3][9] = 1; 
        board.getBoard()[4][8] = 2; 
        assertFalse(board.isValidMove(3, 9, 4, 8, 1));

        // Test case: start position is in opponent base but end position is not
        board.getBoard()[16][12] = 1; 
        assertFalse(board.isValidMove(16, 12, 15, 11, 1));
          // Test case: start position is in opponent base and end position is also in opponent base
        board.getBoard()[16][12] = 1; 
        board.getBoard()[15][11] = 0; 
        assertTrue(board.isValidMove(16, 12, 15, 11, 1));
        // Test case: valid move
        board.getBoard()[3][9] = 1;
        board.getBoard()[4][8] = 0; 
        assertTrue(board.isValidMove(3, 9, 4, 8, 1));
    }
    @Test
    void testIsPlayerInOpponentBase() {
        board.getBoard()[16][12] = 1;
        board.getBoard()[15][11] = 1;
        board.getBoard()[15][13] = 1;
        board.getBoard()[14][10] = 1;
        board.getBoard()[14][12] = 1;
        board.getBoard()[14][14] = 1;
        board.getBoard()[13][9] = 1;
        board.getBoard()[13][11] = 1;
        board.getBoard()[13][13] = 1;
        board.getBoard()[13][15] = 1;

        // Sprawdzenie, czy metoda zwraca true, gdy wszystkie pionki gracza 1 są w bazie przeciwnika
        assertTrue(board.isPlayerInOpponentBase(1));

        board.getBoard()[13][15] = 0;

        // Sprawdzenie, czy metoda zwraca false, gdy jeden pionek gracza 1 nie jest w bazie przeciwnika
        assertFalse(board.isPlayerInOpponentBase(1));

        board.getBoard()[0][12] = 2;
        board.getBoard()[1][11] = 2;
        board.getBoard()[1][13] = 2;
        board.getBoard()[2][10] = 2;
        board.getBoard()[2][12] = 2;
        board.getBoard()[2][14] = 2;
        board.getBoard()[3][9] = 2;
        board.getBoard()[3][11] = 2;
        board.getBoard()[3][13] = 2;
        board.getBoard()[3][15] = 2;

        // Sprawdzenie, czy metoda zwraca true, gdy wszystkie pionki gracza 2 są w bazie przeciwnika
        assertTrue(board.isPlayerInOpponentBase(2));

        // Ustawienie jednego pionka gracza 2 poza bazą przeciwnika
        board.getBoard()[3][15] = 0;

        // Sprawdzenie, czy metoda zwraca false, gdy jeden pionek gracza 2 nie jest w bazie przeciwnika
        assertFalse(board.isPlayerInOpponentBase(2));
    }
}