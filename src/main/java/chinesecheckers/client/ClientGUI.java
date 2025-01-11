package chinesecheckers.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chinesecheckers.server.Board;

public class ClientGUI extends JFrame {
    private Board board;
    private JLabel turnLabel;
    private JLabel colorLabel;
    private int playerColor;
    private BoardPanel boardPanel;
    private GameClient gameClient;
    private JButton skipButton;
    public ClientGUI(Board board, int playerColor, GameClient gameClient) {
        this.board = board;
        this.playerColor = playerColor;
        this.gameClient = gameClient;

        setTitle("Chinese Checkers");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        turnLabel = new JLabel("Oczekiwanie na turę...");
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel.setFont(new Font("Serif", Font.BOLD, 20));
        turnLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        colorLabel = new JLabel("Twój kolor: " + getColorName(playerColor));
        colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        colorLabel.setFont(new Font("Serif", Font.BOLD, 20));
        colorLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        

        skipButton = new JButton("Skip Turn");
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skipTurn();
            }
        });

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(turnLabel);
        topPanel.add(skipButton);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(colorLabel, BorderLayout.SOUTH);
        boardPanel = new BoardPanel(board, playerColor, gameClient);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
            
        add(mainPanel);
        setVisible(true);        
    }

    public void showPlayerTurnMessage() {
        turnLabel.setText("Twoja tura!");
        boardPanel.setPlayerTurn(true);
    }

    public void endPlayerTurn() {
        turnLabel.setText("Oczekiwanie na turę...");
        boardPanel.setPlayerTurn(false);
    }

    private void skipTurn() {
        gameClient.skipTurn();
    }

    private String getColorName(int playerColor) {
        switch (playerColor) {
            case 1: return "Czerwony";
            case 2: return "Niebieski";
            case 3: return "Żółty";
            case 4: return "Zielony";
            case 5: return "Pomarańczowy";
            case 6: return "Fioletowy";
            default: return "Nieznany";
        }
    }
}