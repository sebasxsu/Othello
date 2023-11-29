package Presentacion;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Menu;

import Logica.OthelloFrame;
import Logica.OthelloMenu;

public class Othello extends JPanel implements MouseListener {
	static int[][] data = new int[8][8];
	static int gameSizeInt = 8;
	static JPanel panel = new JPanel();
	static int turn = 2;
	static int Negro = 0;
	static int Blanco = 0;
	static int vacia = 0;
	static int Azul = 0;
	static int fontX = 10;
	static int fontY = 498;
	static int noAzul = 0;
	static boolean noOneWin = false;

	// menu
	public Othello() {
		JFrame frame = new JFrame();
		frame.setTitle("Othello");
		frame.setLocationRelativeTo(null);
		frame.setLocation(390, 80);
		panel.setPreferredSize(new Dimension(481, 505));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(18, 199, 24));

		// menubar
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Opciones");
		JMenuItem newGame = new JMenuItem("Nuevo Juego");
		JCheckBoxMenuItem help = new JCheckBoxMenuItem("Ayuda");
		JMenuItem exitGame = new JMenuItem("Cerrar Juego");
		menuBar.add(file);
		file.add(newGame);
		file.add(help);
		help.setSelected(true);
		file.addSeparator();
		file.add(exitGame);
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data = new int[gameSizeInt][gameSizeInt];
				turn = 2;
				start();
				count();
				panel.repaint();
			}

		});
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.repaint();
			}

		});
		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (int i = 0; i < gameSizeInt; i++)
					for (int j = 0; j < gameSizeInt; j++) {
						g.setColor(new Color(61, 153, 111));
						g.fillRect(j * 60, i * 60, 60, 60);
						g.setColor(Color.black);
						g.drawRect(j * 60, i * 60, 60, 60);
					}
				for (int i = 0; i < data.length; i++) {
					for (int j = 0; j < data[i].length; j++) {
						switch (data[i][j]) {
							case 0:
								break;

							case 1:
								g.setColor(Color.BLACK);
								g.fillOval(5 + i * 60, 5 + j * 60, 50, 50);
								break;
							case 2:
								g.setColor(Color.WHITE);
								g.fillOval(5 + i * 60, 5 + j * 60, 50, 50);
								break;
							case -1:
								if (help.getState()) {
									g.setColor(Color.BLUE);
									g.fillOval(20 + i * 60, 20 + j * 60, 25, 25);
								}
								break;

						}
					}
				}
				g.setColor(Color.BLACK);
				g.setFont(new Font("Courier New", Font.BOLD, 15));
				if (vacia == 0) {
					if (Negro > Blanco) {
						g.drawString("Negro gana     Negro = " + Negro + "  Blanco = " + Blanco, fontX, fontY);
					} else if (Negro == Blanco || noOneWin) {
						g.drawString("Nadie gana     Negro = " + Negro + "  Blanco = " + Blanco, fontX, fontY);
					} else {
						g.drawString("Blanco gana     Negro = " + Negro + "  Blanco = " + Blanco, fontX, fontY);
					}
				} else {
					if (turn == 1) {
						g.drawString("Turno negro     negro = " + Negro + "  blanco = " + Blanco, fontX, fontY);
					} else {
						g.drawString("Turno blanco     negro = " + Negro + "  blanco = " + Blanco, fontX, fontY);
					}
				}
			}

			@Override
			public Dimension getPreferredSize() {
				if (gameSizeInt == 8) {
					return new Dimension(481, 505);
				} else {
					return new Dimension(601, 625);
				}
			}

		};

		frame.add(panel);
		frame.setJMenuBar(menuBar);
		panel.addMouseListener(this);
		frame.pack();

		frame.setVisible(true);

	}

	// main
	public static void main(String[] args) {
		start();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Othello();
			}
		});
	}

	static void start() {
		data[(gameSizeInt / 2) - 1][(gameSizeInt / 2) - 1] = 1;
		data[gameSizeInt / 2][(gameSizeInt / 2) - 1] = 2;
		data[(gameSizeInt / 2) - 1][gameSizeInt / 2] = 2;
		data[gameSizeInt / 2][gameSizeInt / 2] = 1;
		help();
		count();
	}

	// mouseclicked
	public void mouseClicked(MouseEvent arg0) {
		int x, y, i = 0, j = 0;
		x = arg0.getX();
		y = arg0.getY();
		i = x / 60;
		j = y / 60;
		if (accepted(i, j)) {
			if (turn == 1) {
				data[i][j] = turn;
				fillAll(i, j);
				turn = 2;
			} else {
				data[i][j] = turn;
				fillAll(i, j);
				turn = 1;
			}
			help();

			panel.repaint();
		}
	}

	/**
	 * VER si el movimiento es aceptado
	 */
	boolean accepted(int i, int j) {
		if (i < gameSizeInt && j < gameSizeInt) {
			if (data[i][j] == -1) {
				return true;
			}
		}
		return false;
	}

	// Mostrar la ayuda al jugador donde puede jugar

	static void help() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == -1) {
					data[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == turn) {
					check(i, j);
				}
			}
		}
		count();
		if (Azul == 0 && vacia != 0) {
			noAzul++;
			if (turn == 1) {
				turn = 2;
			} else {
				turn = 1;
			}
			if (noAzul > 1) {
				noOneWin = true;
			}
			if (!noOneWin) {
				help();
			}
		} else {
			noAzul = 0;
		}

	}

	/**
	 * Ver si el movimiento puede ser seleccionado
	 */
	static void check(int i, int j) {
		int notTurn;
		int oI = i;
		int oJ = j;
		boolean done = false;
		if (turn == 1) {
			notTurn = 2;
		} else {
			notTurn = 1;
		}

		// up
		while (i >= 0 && i < gameSizeInt && j - 1 >= 0 && j - 1 < gameSizeInt && !done) {
			if (data[i][j - 1] == notTurn) {
				if (i >= 0 && i < gameSizeInt && j - 2 >= 0 && j - 2 < gameSizeInt) {
					// ln("i1");
					if (data[i][j - 2] == 0) {
						// ln("i2");
						data[i][j - 2] = -1;
						done = true;
					} else if (data[i][j - 2] == notTurn) {
						j = j - 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;
		// ln("up-right");

		// up-right
		while (i + 1 >= 0 && i + 1 < gameSizeInt && j - 1 >= 0 && j - 1 < gameSizeInt && !done) {
			// ln("w1");
			if (data[i + 1][j - 1] == notTurn) {
				// ln("w2");
				if (i + 2 >= 0 && i + 2 < gameSizeInt && j - 2 >= 0 && j - 2 < gameSizeInt) {
					// ln("i1");
					if (data[i + 2][j - 2] == 0) {
						// ln("i2");
						data[i + 2][j - 2] = -1;
						done = true;
					} else if (data[i + 2][j - 2] == notTurn) {
						j = j - 1;
						i = i + 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}
		i = oI;
		j = oJ;
		done = false;
		// ln("right");

		// right
		while (i + 1 >= 0 && i + 1 < gameSizeInt && j >= 0 && j < gameSizeInt && !done) {
			// ln("w1");
			if (data[i + 1][j] == notTurn) {
				// ln("w2");
				if (i + 2 >= 0 && i + 2 < gameSizeInt && j >= 0 && j < gameSizeInt) {
					// ln("i1");
					if (data[i + 2][j] == 0) {
						// ln("i2");
						data[i + 2][j] = -1;
						done = true;
					} else if (data[i + 2][j] == notTurn) {
						i = i + 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;
		// ln("right-down");

		// right-down
		while (i + 1 >= 0 && i + 1 < gameSizeInt && j + 1 >= 0 && j + 1 < gameSizeInt && !done) {
			// ln("w1");
			if (data[i + 1][j + 1] == notTurn) {
				// ln("w2");
				if (i + 2 >= 0 && i + 2 < gameSizeInt && j + 2 >= 0 && j + 2 < gameSizeInt) {
					// ln("i1");
					if (data[i + 2][j + 2] == 0) {
						// ln("i2");
						data[i + 2][j + 2] = -1;
						done = true;
					} else if (data[i + 2][j + 2] == notTurn) {
						i = i + 1;
						j = j + 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;
		// ln("down");

		// down
		while (i >= 0 && i < gameSizeInt && j + 1 >= 0 && j + 1 < gameSizeInt && !done) {
			// ln("w1");
			if (data[i][j + 1] == notTurn) {
				// ln("w2");
				if (i >= 0 && i < gameSizeInt && j + 2 >= 0 && j + 2 < gameSizeInt) {
					// ln("i1");
					if (data[i][j + 2] == 0) {
						// ln("i2");
						data[i][j + 2] = -1;
						done = true;
					} else if (data[i][j + 2] == notTurn) {
						j = j + 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;
		// ln("down-left");

		// down-left
		while (i - 1 >= 0 && i - 1 < gameSizeInt && j + 1 >= 0 && j + 1 < gameSizeInt && !done) {
			// ln("w1");
			if (data[i - 1][j + 1] == notTurn) {
				// ln("w2");
				if (i - 2 >= 0 && i - 2 < gameSizeInt && j + 2 >= 0 && j + 2 < gameSizeInt) {
					// ln("i1");
					if (data[i - 2][j + 2] == 0) {
						// ln("i2");
						data[i - 2][j + 2] = -1;
						done = true;
					} else if (data[i - 2][j + 2] == notTurn) {
						j = j + 1;
						i = i - 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;
		// ln("left");

		// left
		while (i - 1 >= 0 && i - 1 < gameSizeInt && j >= 0 && j < gameSizeInt && !done) {
			// ln("w1");
			if (data[i - 1][j] == notTurn) {
				// ln("w2");
				if (i - 2 >= 0 && i - 2 < gameSizeInt && j >= 0 && j < gameSizeInt) {
					// ln("i1");
					if (data[i - 2][j] == 0) {
						// ln("i2");
						data[i - 2][j] = -1;
						done = true;
					} else if (data[i - 2][j] == notTurn) {
						i = i - 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;
		// ln("left-up");

		// left-up
		while (i - 1 >= 0 && i - 1 < gameSizeInt && j - 1 >= 0 && j - 1 < gameSizeInt && !done) {
			// ln("w1");
			if (data[i - 1][j - 1] == notTurn) {
				// ln("w2");
				if (i - 2 >= 0 && i - 2 < gameSizeInt && j - 2 >= 0 && j - 2 < gameSizeInt) {
					// ln("i1");
					if (data[i - 2][j - 2] == 0) {
						// ln("i2");
						data[i - 2][j - 2] = -1;
						done = true;
					} else if (data[i - 2][j - 2] == notTurn) {
						i = i - 1;
						j = j - 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

	}

	/**
	 * Cambiar el valor despues de que el jugador mueva
	 */
	static void fillAll(int i, int j) {
		int notTurn;
		int oI = i;
		int oJ = j;
		boolean done = false;
		if (turn == 1) {
			notTurn = 2;
		} else {
			notTurn = 1;
		}

		// fillal
		while (i >= 0 && i < gameSizeInt && j - 1 >= 0 && j - 1 < gameSizeInt && !done) {
			if (data[i][j - 1] == notTurn) {
				if (i >= 0 && i < gameSizeInt && j - 2 >= 0 && j - 2 < gameSizeInt) {
					if (data[i][j - 2] == turn) {
						for (int k = j - 1; k <= oJ; k++) {
							data[i][k] = turn;
							System.out.println("arriba-fillall " + i + " " + k);
						}
						done = true;
					} else if (data[i][j - 2] == notTurn) {
						j = j - 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;

		// up-right
		while (i + 1 >= 0 && i + 1 < gameSizeInt && j - 1 >= 0 && j - 1 < gameSizeInt && !done) {
			if (data[i + 1][j - 1] == notTurn) {
				if (i + 2 >= 0 && i + 2 < gameSizeInt && j - 2 >= 0 && j - 2 < gameSizeInt) {
					if (data[i + 2][j - 2] == turn) {
						int m = i + 1;
						for (int k = j - 1; k < oJ; k++) {
							System.out.println("DerechArriba" + m + " " + k);
							data[m][k] = turn;
							m--;
						}
						done = true;
					} else if (data[i + 2][j - 2] == notTurn) {
						j = j - 1;
						i = i + 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}
		i = oI;
		j = oJ;
		done = false;

		// right
		while (i + 1 >= 0 && i + 1 < gameSizeInt && j >= 0 && j < gameSizeInt && !done) {
			if (data[i + 1][j] == notTurn) {
				if (i + 2 >= 0 && i + 2 < gameSizeInt && j >= 0 && j < gameSizeInt) {
					if (data[i + 2][j] == turn) {
						for (int k = i + 1; k > oI; k--) {
							data[k][j] = turn;
							System.out.println("Derecha" + k + " " + j);
						}
						done = true;
					} else if (data[i + 2][j] == notTurn) {
						i = i + 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;

		// right-down
		while (i + 1 >= 0 && i + 1 < gameSizeInt && j + 1 >= 0 && j + 1 < gameSizeInt && !done) {
			if (data[i + 1][j + 1] == notTurn) {
				if (i + 2 >= 0 && i + 2 < gameSizeInt && j + 2 >= 0 && j + 2 < gameSizeInt) {
					if (data[i + 2][j + 2] == turn) {
						int m = i + 1;
						for (int k = j + 1; k > oJ; k--) {
							System.out.println("DerechAbajo " + m + " " + k);
							data[m][k] = turn;
							m--;
						}
						done = true;
					} else if (data[i + 2][j + 2] == notTurn) {
						i = i + 1;
						j = j + 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;

		// down
		while (i >= 0 && i < gameSizeInt && j + 1 >= 0 && j + 1 < gameSizeInt && !done) {
			if (data[i][j + 1] == notTurn) {
				if (i >= 0 && i < gameSizeInt && j + 2 >= 0 && j + 2 < gameSizeInt) {
					if (data[i][j + 2] == turn) {
						for (int k = j + 1; k > oJ; k--) {
							data[i][k] = turn;
							System.out.println("Abajo " + i + " " + k);
						}
						done = true;
					} else if (data[i][j + 2] == notTurn) {
						j = j + 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;

		// down-left
		while (i - 1 >= 0 && i - 1 < gameSizeInt && j + 1 >= 0 && j + 1 < gameSizeInt && !done) {
			if (data[i - 1][j + 1] == notTurn) {
				if (i - 2 >= 0 && i - 2 < gameSizeInt && j + 2 >= 0 && j + 2 < gameSizeInt) {
					if (data[i - 2][j + 2] == turn) {
						int m = i - 1;
						for (int k = j + 1; k > oJ; k--) {
							System.out.println("Abajo-izquiera " + m + " " + k);
							data[m][k] = turn;
							m++;
						}
						done = true;
					} else if (data[i - 2][j + 2] == notTurn) {
						j = j + 1;
						i = i - 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;

		// left
		while (i - 1 >= 0 && i - 1 < gameSizeInt && j >= 0 && j < gameSizeInt && !done) {
			if (data[i - 1][j] == notTurn) {
				if (i - 2 >= 0 && i - 2 < gameSizeInt && j >= 0 && j < gameSizeInt) {
					if (data[i - 2][j] == turn) {
						for (int k = i - 2; k < oI; k++) {
							data[k][j] = turn;
							System.out.println("abajoIzquiera " + k + " " + j);
						}
						done = true;
					} else if (data[i - 2][j] == notTurn) {
						i = i - 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

		i = oI;
		j = oJ;
		done = false;

		// left-up
		while (i - 1 >= 0 && i - 1 < gameSizeInt && j - 1 >= 0 && j - 1 < gameSizeInt && !done) {
			if (data[i - 1][j - 1] == notTurn) {
				if (i - 2 >= 0 && i - 2 < gameSizeInt && j - 2 >= 0 && j - 2 < gameSizeInt) {
					if (data[i - 2][j - 2] == turn) {
						int m = i - 1;
						for (int k = j - 1; k < oJ; k++) {
							System.out.println("abajo " + m + " " + k);
							data[m][k] = turn;
							m++;
						}
						done = true;
					} else if (data[i - 2][j - 2] == notTurn) {
						i = i - 1;
						j = j - 1;
					} else {
						done = true;
					}
				} else {
					done = true;
				}
			} else {
				done = true;
			}
		}

	}

	/**
	 * Contar cuantas piezas hay
	 */
	static void count() {
		Negro = 0;
		Blanco = 0;
		vacia = 0;
		Azul = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if (data[i][j] == 1) {
					Negro++;
				}
				if (data[i][j] == 2) {
					Blanco++;
				}
				if (data[i][j] == 0) {
					vacia++;
				}
				if (data[i][j] == -1) {
					Azul++;
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}