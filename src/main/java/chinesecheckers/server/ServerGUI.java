package chinesecheckers.server;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ServerGUI {
    private int selectedPlayers;
    private final JFrame frame;
    private final JTextArea logArea;

    public ServerGUI() {
        frame = new JFrame("Chińskie warcaby - serwer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
               
        frame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JLabel label = new JLabel("Wybierz liczbę graczy:", SwingConstants.CENTER);
        label.setFont(new Font("FF DIN", Font.BOLD, 30));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        topPanel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        int[] playerOptions = {2, 3, 4, 6};
        Color[] buttonColors = {new Color(139, 0, 0), new Color(0, 100, 0), new Color(0, 0, 139), new Color(255, 140, 0)};
        for (int i = 0; i < playerOptions.length; i++) {
            int option = playerOptions[i];
            JButton button = new JButton(String.valueOf(option));
            button.setFont(new Font("Serif", Font.BOLD, 40));
            button.setBackground(buttonColors[i]);
            button.setForeground(Color.WHITE);
            button.setOpaque(true);
            button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            button.addActionListener((ActionEvent e) -> {
                selectedPlayers = option;
                synchronized (this) {
                    this.notify();
                }
                frame.getContentPane().remove(topPanel);
                frame.revalidate();
                frame.repaint();
            });
            buttonPanel.add(button);
        }
        topPanel.add(buttonPanel, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        PrintStream printStream = new PrintStream(new CustomOutputStream(logArea));
        System.setOut(printStream);
        System.setErr(printStream);

        frame.setVisible(true);
    }

    public synchronized int getSelectedPlayers() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return selectedPlayers;
    }

    public void waitForWindowClose() {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                synchronized (ServerGUI.this) {
                    ServerGUI.this.notify();
                }
            }
        });
    
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Serwer został przerwany podczas oczekiwania na zamknięcie okna.");
            }
        }
    }
    

    class CustomOutputStream extends OutputStream {
        private final JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            SwingUtilities.invokeLater(() -> {
                textArea.append(String.valueOf((char) b));
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
        }

        @Override
        public void write(byte[] b, int off, int len) {
            String text = new String(b, off, len, StandardCharsets.UTF_8);
            SwingUtilities.invokeLater(() -> {
                textArea.append(text);
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
        }
    }
}