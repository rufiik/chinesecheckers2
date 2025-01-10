package chinesecheckers.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        List<int[][]> positions = new ArrayList<>();
        positions.add(new int[][]{{0, 12}, {1, 11}, {1, 13}, {2, 10}, {2, 12}, {2, 14}, {3, 9}, {3, 11}, {3, 13}, {3, 15}});
        positions.add(new int[][]{{16, 12}, {15, 11}, {15, 13}, {14, 10}, {14, 12}, {14, 14}, {13, 9}, {13, 11}, {13, 13}, {13, 15}});
        positions.add(new int[][]{{12, 0}, {11, 1}, {12, 2}, {10, 2}, {11, 3}, {12, 4}, {9, 3}, {10, 4}, {11, 5}, {12, 6}});
        positions.add(new int[][]{{12, 24}, {11, 23}, {12, 22}, {10, 22}, {9, 21}, {11, 21}, {10, 20}, {12, 20}, {11, 19}, {12, 18}});
        positions.add(new int[][]{{4, 0}, {5, 1}, {4, 2}, {6, 2}, {5, 3}, {4, 4}, {5, 5}, {6, 4}, {7, 3}, {4, 6}});
        positions.add(new int[][]{{4, 24}, {5, 23}, {4, 22}, {6, 22}, {5, 21}, {7, 21}, {4, 20}, {6, 20}, {4, 18}, {5, 19}});
        Map<Integer, Integer> opposingPositions = new HashMap<>();
        opposingPositions.put(0, 1); 
        opposingPositions.put(1, 0);
        opposingPositions.put(2, 5); 
        opposingPositions.put(5, 2);
        opposingPositions.put(3, 4); 
        opposingPositions.put(4, 3);
        List<int[][]> selectedPositions = new ArrayList<>();
        
        switch (numberOfPlayers) {
            case 2:
                setPlayerPieces(1, new int[][]{{0, 12}, {1, 11}, {1, 13}, {2, 10}, {2, 12}, {2, 14}, {3, 9}, {3, 11}, {3, 13}, {3, 15}});
                setPlayerPieces(2, new int[][]{{16, 12}, {15, 11}, {15, 13}, {14, 10}, {14, 12}, {14, 14}, {13, 9}, {13, 11}, {13, 13}, {13, 15}});
                break;
            case 3:
          selectedPositions.clear();
        List<Integer> availableIndices = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            availableIndices.add(i);
        }
        Collections.shuffle(availableIndices);
        for (int index : availableIndices) {
            if (opposingPositions.containsKey(index)) {
                int opposingIndex = opposingPositions.get(index);
                boolean opposingSelected = selectedPositions.contains(positions.get(opposingIndex));
                if (opposingSelected) {
                    continue; 
                }
            }
            selectedPositions.add(positions.get(index));
        }
        for (int i = 0; i < Math.min(3, selectedPositions.size()); i++) {
            setPlayerPieces(i + 1, selectedPositions.get(i));
        }
                break;
            case 4:
          selectedPositions.clear();
        Set<Integer> selectedIndices = new HashSet<>();
        List<Integer> availableIndices1 = new ArrayList<>(opposingPositions.keySet()); 
        Collections.shuffle(availableIndices1);
        for (int index : availableIndices1) {
            if (selectedIndices.contains(index)) continue; 
            int opposingIndex = opposingPositions.get(index);
            selectedIndices.add(index);
            selectedIndices.add(opposingIndex);
            selectedPositions.add(positions.get(index));
            selectedPositions.add(positions.get(opposingIndex));
            if (selectedPositions.size() == 4) break;
        }
        for (int i = 0; i < selectedPositions.size(); i++) {
            setPlayerPieces(i + 1, selectedPositions.get(i));
        }
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
    public synchronized String processMove(String move, int playerId) {
        if (isValidMove(move)) {
            return "Ruch wykonany: " + move;
        } else {
            return "Nieprawidłowy ruch: " + move;
        }
    }

    private boolean isValidMove(String move) {
        return true;
    }

    public int[][] getBoard() {
        return board;
    }

}