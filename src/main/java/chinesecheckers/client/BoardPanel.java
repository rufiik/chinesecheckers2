package chinesecheckers.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import chinesecheckers.server.Board;

public class BoardPanel extends JPanel {
    private Board board;
    private int playerColor;
    private boolean isPlayerTurn = false;
    private GameClient gameClient;

    private int cellSize = 30;
    private int boardWidth;
    private int boardHeight;
    private int startX;
    private int startY;

    private int selectedRow = -1;
    private int selectedCol = -1;
    private int draggedX = -1;
    private int draggedY = -1;
    private boolean dragging = false;

    public BoardPanel(Board board, int playerColor, GameClient gameClient) {
        this.board = board;
        this.playerColor = playerColor;
        this.gameClient = gameClient;
        
        calculateBoardDimensions();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isPlayerTurn) return;

                int col = (e.getX() - startX) / cellSize;
                int row = (e.getY() - startY) / cellSize;

                if (isWithinBoard(row, col)) {
                    int pieceColor = board.getBoard()[row][col];
                    if (pieceColor == playerColor) {
                        selectedRow = row;
                        selectedCol = col;
                        dragging = true;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!isPlayerTurn) return;

                if (dragging) {
                    int col = (e.getX() - startX) / cellSize;
                    int row = (e.getY() - startY) / cellSize;

                    if (isWithinBoard(row, col) && board.getBoard()[row][col] == 0 && !(row == selectedRow && col == selectedCol)) {
                        if (board.isValidMove(selectedRow, selectedCol, row, col, playerColor)) {
                            board.movePiece(selectedRow, selectedCol, row, col, playerColor);
                            notifyMove(selectedRow, selectedCol, row, col);
                        }
                    }
                    
                    repaint();
                    selectedRow = -1;
                    selectedCol = -1;
                    dragging = false;
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!isPlayerTurn) return;

                if (dragging) {
                    draggedX = e.getX();
                    draggedY = e.getY();
                    repaint();
                }
            }
        });
    }

    private void calculateBoardDimensions() {
        boardWidth = board.getBoard()[0].length * cellSize;
        boardHeight = board.getBoard().length * cellSize;
        startX = (getWidth() - boardWidth) / 2;
        startY = (getHeight() - boardHeight) / 2;
    }

    private boolean isWithinBoard(int row, int col) {
        return row >= 0 && row < board.getBoard().length && col >= 0 && col < board.getBoard()[row].length;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        calculateBoardDimensions();
        drawBoard(g);

        if (dragging && selectedRow != -1 && selectedCol != -1) {
            g.setColor(getPieceColor(playerColor));
            g.fillOval(draggedX - cellSize / 2, draggedY - cellSize / 2, cellSize, cellSize);
        }
    }

    private void drawBoard(Graphics g) {
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                switch (board.getBoard()[i][j]) {
                    case 0:
                        g.setColor(Color.WHITE);
                        break;
                    case 7:
                        continue;
                    default:
                        g.setColor(getPieceColor(board.getBoard()[i][j]));
                        break;
                }
                g.fillOval(startX + j * cellSize, startY + i * cellSize, cellSize, cellSize);
            }
        }
    }

    private Color getPieceColor(int pieceColor) {
        switch (pieceColor) {
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.ORANGE;
            case 6:
                return new Color(128, 0, 128);
            default:
                return Color.WHITE;
        }
    }

    public void setPlayerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
    }

    private void notifyMove(int startX, int startY, int endX, int endY) {
        gameClient.sendMove(startX, startY, endX, endY);
    }
}
