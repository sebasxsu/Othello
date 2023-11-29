package Logica;

import javax.swing.JFrame;

public class OthelloFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public OthelloFrame() {
        OthelloBoard board = new OthelloBoard();
        OthelloMenu menu = new OthelloMenu();

        setTitle("Othello");
        setLocationRelativeTo(null);
        setLocation(390, 80);
        setResizable(false);
        getContentPane().setBackground(new java.awt.Color(18, 199, 24));

        add(board);
        setMenuBar(menu);
        pack();
    }

    private void setMenuBar(OthelloMenu menu) {
    }
}
