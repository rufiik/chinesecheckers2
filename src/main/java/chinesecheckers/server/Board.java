package chinesecheckers.server;

public class Board {
    private int[][] board;
    private static final int ROWS = 17;
    private static final int COLUMNS = 25;

    public Board() {
        board = new int[ROWS][COLUMNS];
        initializeBoard();
    }

    private void initializeBoard() {
        board = new int[][] {
            {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
            {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
            {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
            {7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 0, 7, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7},
            {0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0},
            {7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7},
            {7, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 7},
            {7, 7, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 7, 7},
            {7, 7, 7, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 7, 7, 7},
            {7, 7, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 7, 7},
            {7, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 7},
            {7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7},
            {0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0},
            {7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 0, 7, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7},
            {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
            {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
            {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}
        };
    }

    public void initializeBoardForPlayers(int numberOfPlayers) {
        switch (numberOfPlayers) {
            case 2:
                setPlayerPieces(1, new int[][]{{0, 12}, {1, 11}, {1, 13}, {2, 10}, {2, 12}, {2, 14}, {3, 9}, {3, 11}, {3, 13}, {3, 15}});
                setPlayerPieces(2, new int[][]{{16, 12}, {15, 11}, {15, 13}, {14, 10}, {14, 12}, {14, 14}, {13, 9}, {13, 11}, {13, 13}, {13, 15}});
                break;
    
            case 3:
                setPlayerPieces(1, new int[][]{{0, 12}, {1, 11}, {1, 13}, {2, 10}, {2, 12}, {2, 14}, {3, 9}, {3, 11}, {3, 13}, {3, 15}});
                setPlayerPieces(2, new int[][]{{12, 24}, {11, 23}, {12, 22}, {10, 22}, {9, 21}, {11, 21}, {10, 20}, {12, 20}, {11, 19}, {12, 18}});
                setPlayerPieces(3, new int[][]{{12, 0}, {11, 1}, {12, 2}, {10, 2}, {11, 3}, {12, 4}, {9, 3}, {10, 4}, {11, 5}, {12, 6}});
                break;
    
            case 4:
                setPlayerPieces(1, new int[][]{{4, 0}, {5, 1}, {4, 2}, {6, 2}, {5, 3}, {4, 4}, {5, 5}, {6, 4}, {7, 3}, {4, 6}});
                setPlayerPieces(2, new int[][]{{4, 24}, {5, 23}, {4, 22}, {6, 22}, {5, 21}, {7, 21}, {4, 20}, {6, 20}, {4, 18}, {5, 19}});
                setPlayerPieces(3, new int[][]{{12, 0}, {11, 1}, {12, 2}, {10, 2}, {11, 3}, {12, 4}, {9, 3}, {10, 4}, {11, 5}, {12, 6}});
                setPlayerPieces(4, new int[][]{{12, 24}, {11, 23}, {12, 22}, {10, 22}, {9, 21}, {11, 21}, {10, 20}, {12, 20}, {11, 19}, {12, 18}});
                break;
    
            case 6:
                setPlayerPieces(1, new int[][]{{0, 12}, {1, 11}, {1, 13}, {2, 10}, {2, 12}, {2, 14}, {3, 9}, {3, 11}, {3, 13}, {3, 15}});
                setPlayerPieces(2, new int[][]{{16, 12}, {15, 11}, {15, 13}, {14, 10}, {14, 12}, {14, 14}, {13, 9}, {13, 11}, {13, 13}, {13, 15}});
                setPlayerPieces(3, new int[][]{{12, 0}, {11, 1}, {12, 2}, {10, 2}, {11, 3}, {12, 4}, {9, 3}, {10, 4}, {11, 5}, {12, 6}});
                setPlayerPieces(4, new int[][]{{12, 24}, {11, 23}, {12, 22}, {10, 22}, {9, 21}, {11, 21}, {10, 20},{12, 20}, {11, 19}, {12, 18}});
                setPlayerPieces(5, new int[][]{{4, 0}, {5, 1}, {4, 2}, {6, 2}, {5, 3}, {4, 4}, {5, 5}, {6, 4}, {7, 3}, {4, 6}});
                setPlayerPieces(6, new int[][]{{4, 24}, {5, 23}, {4, 22}, {6, 22}, {5, 21}, {7, 21}, {4, 20}, {6, 20}, {4, 18}, {5, 19}});
                break;
    
            default:
                throw new IllegalArgumentException("Invalid number of players: " + numberOfPlayers);
        }
    }
    

    private void setPlayerPieces(int player, int[][] positions) {
        for (int[] pos : positions) {
            board[pos[0]][pos[1]] = player;
        }
    }

    public synchronized String movePiece(int startX, int startY, int endX, int endY, int playerId) {
        if (isValidMove(startX, startY, endX, endY, playerId)) {
            board[endX][endY] = board[startX][startY];
            board[startX][startY] = 0;
            return "Ruch wykonany z (" + startX + "," + startY + ") na (" + endX + "," + endY + ").";
        } else {
            return "Nieprawidłowy ruch z (" + startX + "," + startY + ") na (" + endX + "," + endY + ").";
        }
    }
    
    public void update(String gameState) {
        String[] rows = gameState.split(";");
        for (int i = 0; i < rows.length; i++) {
            String[] cells = rows[i].split(",");
            for (int j = 0; j < cells.length; j++) {
                board[i][j] = Integer.parseInt(cells[j]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell).append(",");
            }
            sb.deleteCharAt(sb.length() - 1); 
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1); 
        return sb.toString();
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY, int playerId) {
        return true;
    }

    public int[][] getBoard() {
        return board;
    }

}