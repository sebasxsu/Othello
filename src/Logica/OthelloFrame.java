package Logica;

import javax.swing.JFrame;

public class OthelloFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public OthelloFrame() {
        OthelloBoard board = new OthelloBoard();
        OthelloMenu menu = new OthelloMenu(board);

        setTitle("Othello");
        setLocationRelativeTo(null);
        setLocation(390, 80);
        setResizable(false);
        getContentPane().setBackground(new java.awt.Color(18, 199, 24));

        add(board);
        setJMenuBar(menu);
        pack();
    }
}
