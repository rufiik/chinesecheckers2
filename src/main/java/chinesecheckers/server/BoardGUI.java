package chinesecheckers.server;
import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JFrame {
    private Board board;

    public BoardGUI(Board board) {
        this.board = board;
        setTitle("Chinese Checkers");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new BoardPanel());
        setVisible(true);
    }

    private class BoardPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBoard(g);
        }

        private void drawBoard(Graphics g) {
            int cellSize = 40;
            int boardWidth = board.getBoard()[0].length * cellSize;
            int boardHeight = board.getBoard().length * cellSize;
            int startX = (getWidth() - boardWidth) / 2;
            int startY = (getHeight() - boardHeight) / 2;
        
            for (int i = 0; i < board.getBoard().length; i++) {
                for (int j = 0; j < board.getBoard()[i].length; j++) {
                    switch( board.getBoard()[i][j] ) {
                        case 0:
                            g.setColor(Color.WHITE);
                            break;
                        case 1:
                            g.setColor(Color.RED);
                            break;
                        case 2:
                            g.setColor(Color.BLUE);
                            break;
                        case 3:
                            g.setColor(Color.YELLOW);
                            break;
                        case 4:
                            g.setColor(Color.GREEN);
                            break;
                        case 5:
                            g.setColor(Color.ORANGE);
                            break;
                        case 6:
                            g.setColor(new Color(128, 0, 128)); 
                            break;
                        case 7:
                            continue;
                    }
                    g.fillOval(startX + j * cellSize, startY + i * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}