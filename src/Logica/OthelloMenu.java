package Logica;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OthelloMenu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Othello Menu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("Opciones");

        JMenuItem newGame = new JMenuItem("Nuevo Juego");
        JCheckBoxMenuItem help = new JCheckBoxMenuItem("Ayuda");
        JMenuItem exitGame = new JMenuItem("Cerrar Juego");

        file.add(newGame);
        file.add(help);
        help.setSelected(true);
        file.addSeparator();
        file.add(exitGame);
        menuBar.add(file);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

        // Lógica de ActionListener para cada elemento del menú
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para iniciar un nuevo juego
                // Puedes llamar a los métodos relevantes de tu clase Othello
                // Ejemplo: Othello.startNewGame();
                System.out.println("Nuevo Juego iniciado");
            }
        });

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para la opción de ayuda
                // Puedes llamar a los métodos relevantes de tu clase Othello
                // Ejemplo: Othello.showHelp();
                System.out.println("Ayuda seleccionada");
            }
        });

        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para cerrar el juego
                // Puedes llamar a los métodos relevantes de tu clase Othello
                // Ejemplo: Othello.exitGame();
                System.out.println("Juego cerrado");
                System.exit(0);
            }
        });
    }
}