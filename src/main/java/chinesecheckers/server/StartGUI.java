package chinesecheckers.server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartGUI {
    private int selectedPlayers;
    private final JFrame frame;

    public StartGUI() {
        frame = new JFrame("Wybór liczby graczy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
               
        frame.setLayout(new GridLayout(5, 1, 20, 20));

        JLabel label = new JLabel("Wybierz liczbę graczy:", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(label);


        int[] playerOptions = {2, 3, 4, 6};
        Color[] buttonColors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE};
        for (int i = 0; i < playerOptions.length; i++) {
            int option = playerOptions[i];
            JButton button = new JButton(String.valueOf(option));
            button.setFont(new Font("Arial", Font.BOLD, 45));
            button.setBackground(buttonColors[i]);
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.addActionListener((ActionEvent e) -> {
                selectedPlayers = option;
                synchronized (this) {
                    this.notify();
                }
                frame.dispose();
            });
            frame.add(button);
        }

        frame.setVisible(true);
    }

    public synchronized int getSelectedPlayers() {
        try {
            this.wait(); // Oczekuj na wybór graczy
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return selectedPlayers;
    }
}