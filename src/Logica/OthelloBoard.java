package Logica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class OthelloBoard extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;

    private int[][] data;
    private int gameSizeInt;
    private int turn;

    public OthelloBoard() {
        gameSizeInt = 8;
        data = new int[gameSizeInt][gameSizeInt];
        turn = 2;

        setPreferredSize(new Dimension(481, 505));
        addMouseListener(this);
    }

    public void resetBoard() {

    }

    @Override
    protected void paintComponent(Graphics g) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
