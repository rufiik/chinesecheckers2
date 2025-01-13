package chinesecheckers.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    private int[][] board;
    private static final int ROWS = 17;
    private static final int COLUMNS = 25;
    private List<Set<int[]>> playerBases;
    private int[] opponentBaseMapping;

    public Board() {
        board = new int[ROWS][COLUMNS];
        initializeBoard();
        initializePlayerBases();
        
        
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
    private void initializePlayerBases() {
        playerBases = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            playerBases.add(new HashSet<>());
        }

        addPlayerBase(0, new int[][]{{0, 12}, {1, 11}, {1, 13}, {2, 10}, {2, 12}, {2, 14}, {3, 9}, {3, 11}, {3, 13}, {3, 15}});
        addPlayerBase(1, new int[][]{{16, 12}, {15, 11}, {15, 13}, {14, 10}, {14, 12}, {14, 14}, {13, 9}, {13, 11}, {13, 13}, {13, 15}});
        addPlayerBase(2, new int[][]{{12, 0}, {11, 1}, {12, 2}, {10, 2}, {11, 3}, {12, 4}, {9, 3}, {10, 4}, {11, 5}, {12, 6}});
        addPlayerBase(3, new int[][]{{12, 24}, {11, 23}, {12, 22}, {10, 22}, {9, 21}, {11, 21}, {10, 20}, {12, 20}, {11, 19}, {12, 18}});
        addPlayerBase(4, new int[][]{{4, 0}, {5, 1}, {4, 2}, {6, 2}, {5, 3}, {4, 4}, {5, 5}, {6, 4}, {7, 3}, {4, 6}});
        addPlayerBase(5, new int[][]{{4, 24}, {5, 23}, {4, 22}, {6, 22}, {5, 21}, {7, 21}, {4, 20}, {6, 20}, {4, 18}, {5, 19}});
    }

    public void initializeOpponentBaseMapping(int numberOfPlayers) {
        switch(numberOfPlayers) {
            case 2:
                opponentBaseMapping = new int[]{1, 0};
                break;
            case 3:
                opponentBaseMapping = new int[]{1, 4, 5};
                break;
            case 4:
                opponentBaseMapping = new int[]{3, 2, 5, 4};
                break;
            case 6:
                opponentBaseMapping = new int[]{1, 0, 5, 4, 3, 2};
                break;
            default:
                throw new IllegalArgumentException("Nieprawidłowa ilość graczy: " + numberOfPlayers);
        }
    }
    private void addPlayerBase(int playerIndex, int[][] positions) {
        for (int[] pos : positions) {
            playerBases.get(playerIndex).add(pos);
        }
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
                throw new IllegalArgumentException("Nieprawidłowa ilość graczy: " + numberOfPlayers);
        }
    }
    

    private void setPlayerPieces(int player, int[][] positions) {
        for (int[] pos : positions) {
            board[pos[0]][pos[1]] = player;
        }
    }

    public boolean isInOpponentBase(int x, int y, int playerId) {
        int opponentBaseIndex = opponentBaseMapping[playerId - 1];
        for (int[] base : playerBases.get(opponentBaseIndex)) {
            if (base[0] == x && base[1] == y) {
                return true;
            }
        }
        return false;
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

    public boolean hasPiece(int x, int y) {
        return board[x][y] != 0 && board[x][y] != 7;
    }
    public boolean isEmpty(int x, int y) {
        return board[x][y] == 0;
    }
    public List<int[]> getPossibleJumps(int startX, int startY, int playerId) {
    List<int[]> jumps = new ArrayList<>();
    int[][] directions = {
        {-2, 0}, {2, 0}, {0, -2}, {0, 2}, {-2, -2}, {2, 2}, {-2, 2}, {2, -2}
    };

    for (int[] dir : directions) {
        int midX = startX + dir[0] / 2;
        int midY = startY + dir[1] / 2;
        int endX = startX + dir[0];
        int endY = startY + dir[1];
        if (isWithinBoard(endX, endY) && hasPiece(midX, midY) && isEmpty(endX, endY)) {
            jumps.add(new int[]{endX, endY});
        }
    }

    return jumps;
}

public boolean isValidMultiJump(int startX, int startY, int endX, int endY, int playerId) {
    Set<String> visited = new HashSet<>();
    boolean result = canJump(startX, startY, endX, endY, playerId, visited);
    return result;
}

private boolean canJump(int startX, int startY, int endX, int endY, int playerId, Set<String> visited) {
    if (startX == endX && startY == endY) {
        return true;
    }

    visited.add(startX + "," + startY);

    for (int[] jump : getPossibleJumps(startX, startY, playerId)) {
        int nextX = jump[0];
        int nextY = jump[1];
        if (!visited.contains(nextX + "," + nextY) && canJump(nextX, nextY, endX, endY, playerId, visited)) {
            return true;
        }
    }

    visited.remove(startX + "," + startY);
    return false;
}

    public boolean isValidMove(int startX, int startY, int endX, int endY, int playerId) {
        if (!isWithinBoard(startX, startY) || !isWithinBoard(endX, endY)) {
            return false;
        }
        if (!hasPiece(startX, startY)) {
            return false;
        }
        if (!isEmpty(endX, endY)) {
            return false;
        }
        if (isInOpponentBase(startX, startY, playerId)) {
            if (!isInOpponentBase(endX, endY, playerId)) {
                return false; 
            }
        }
     if (isAdjacentMove(startX, startY, endX, endY) || isJumpMove(startX, startY, endX, endY) || isValidMultiJump(startX, startY, endX, endY, playerId)) {
       return true;
    }
    return false;
    }

    private boolean isWithinBoard(int x, int y) {
        return x >= 0 && x < ROWS && y >= 0 && y < COLUMNS && board[x][y] != 7;
    }

    private boolean isAdjacentMove(int startX, int startY, int endX, int endY) {
        int dx = Math.abs(startX - endX);
        int dy = Math.abs(startY - endY);
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == 1 && dy == 1);
    }

    private boolean isJumpMove(int startX, int startY, int endX, int endY) {
        int midX = (startX + endX) / 2;
        int midY = (startY + endY) / 2;
        return hasPiece(midX, midY) && isAdjacentMove(startX, startY, midX, midY) && isAdjacentMove(midX, midY, endX, endY);
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

    public boolean isPlayerInOpponentBase(int playerId) {
        int opponentBaseIndex = opponentBaseMapping[playerId - 1];
        Set<int[]> opponentBase = playerBases.get(opponentBaseIndex);

        for (int[] pos : opponentBase) {
            if (board[pos[0]][pos[1]] != playerId) {
                return false;
            }
        }
        return true;
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



    public int[][] getBoard() {
        return board;
    }

    public int[] getOpponentBaseMapping() {
        return opponentBaseMapping;
    }

}