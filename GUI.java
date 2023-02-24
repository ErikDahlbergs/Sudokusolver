package bst;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

public class GUI {
	private JFrame frame;
	private Container pane;
	private JPanel panel;
	private SudokuSolver a;
	private final Color appGreen =  new Color(0, 168, 107);
	private JTextField txt[][];

	public GUI(SudokuSolver sudoku) {
		a = sudoku;
		SwingUtilities.invokeLater(() -> createWindow("Sudoku"));
	}

	private void createWindow(String title) {
		createFrame(title);
		createPanel();
		createTxtField();
		createSoutPanel();
		frame.pack();
		frame.setVisible(true);
	}

	public void createFrame(String title) {
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = frame.getContentPane();
	}

	public void createPanel() {
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(appGreen, 5));
		panel.setPreferredSize(new Dimension(400, 400));
		panel.setLayout(new GridLayout(9,9));
		pane.add(panel, BorderLayout.NORTH);
	}

	public void createSoutPanel() {
		JPanel south = new JPanel();
		south.setBorder(BorderFactory.createMatteBorder(0, 5, 5, 5, appGreen));
		JButton solve = new JButton("Solve");

		solve.addActionListener(e -> {
			a.setNumbers(getBoard());
			if (a.solve()) {
				setTxtField(a.getNumbers());
			} else {
				JOptionPane noSolution = new JOptionPane();
				noSolution.showMessageDialog(null, "Sudokut har ingen lösning");
			}
		});
		JButton	clear = new JButton("Clear");
		clear.addActionListener(e -> {
			a.clear();
			setTxtField(a.getNumbers());
		});

		south.setLayout(new FlowLayout());
		south.add(solve);
		south.add(clear);
		south.setPreferredSize(new Dimension(400, 50));
		pane.add(south, BorderLayout.SOUTH);
	}

	private void createTxtField(int[][] board) {
		txt = new JTextField[9][9];		
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] == 0) {
					txt[row][col] = new JTextField();
					txt[row][col].setHorizontalAlignment(JTextField.CENTER);
					int r = row;
					int c = col;
					txt[row][col].addActionListener(e -> {
						try {
						if (Integer.parseInt(txt[r][c].getText()) < 1
								|| Integer.parseInt(txt[r][c].getText()) > 9 || Integer.parseInt(txt[r][c].getText()) == 0) {
							txt[r][c].setText("");
						} 
						} catch (NumberFormatException e1) {
							txt[r][c].setText("");
						}
					});
					txt[row][col].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent e) {
						}

						@Override
						public void focusLost(FocusEvent e) {
							try {
							if (Integer.parseInt(txt[r][c].getText()) < 1
									|| Integer.parseInt(txt[r][c].getText()) > 9 || Integer.parseInt(txt[r][c].getText()) == 0) {
								txt[r][c].setText("");
							} 
							} catch (NumberFormatException e1) {
								txt[r][c].setText("");
							}
						}
					});
					panel.add(txt[row][col]);

				} else {
					txt[row][col] = new JTextField();
					txt[row][col].setHorizontalAlignment(JTextField.CENTER);
					txt[row][col].setText(Integer.toString(board[row][col]));
					int r = row;
					int c = col;
					txt[row][col].addActionListener(e -> {
						try {
						if (Integer.parseInt(txt[r][c].getText()) < 1
								|| Integer.parseInt(txt[r][c].getText()) > 9 || Integer.parseInt(txt[r][c].getText()) == 0) {
							txt[r][c].setText("");
						}
						} catch (NumberFormatException e1) {
							txt[r][c].setText("");
						}
					});
					txt[row][col].addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent e) {
						}

						@Override
						public void focusLost(FocusEvent e) {
							// TODO Auto-generated method stub
						try {
							if (Integer.parseInt(txt[r][c].getText()) < 1
									|| Integer.parseInt(txt[r][c].getText()) > 9 || Integer.parseInt(txt[r][c].getText()) == 0) {
								txt[r][c].setText("");
							}
						} catch (NumberFormatException e1) {
							txt[r][c].setText("");
						}
						}
					});
					panel.add(txt[row][col]);
				}
				if (boardDesign(row, col)) {
					txt[row][col].setBackground(appGreen);
				}
			}
		}
	}

	private void setTxtField(int[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] == 0) {
					txt[row][col].setText("");
				} else {
					txt[row][col].setText(Integer.toString(board[row][col]));
				}
			}
		}
	}

	public void createTxtField() {
		createTxtField(a.getNumbers());
	}

	/**
	 * Gör om textfield till en 2d array av integer values. TIPS: Använd i solve()
	 * :)
	 * 
	 * @return sudoku-brädet som en int[][]
	 */
	private int[][] getBoard() {
		int[][] readBoard = new int[9][9];
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (txt[row][col].getText().isEmpty()) {
					readBoard[row][col] = 0;
				} else {
					readBoard[row][col] = Integer.parseInt(txt[row][col].getText());
				}
			}
		}
		return readBoard;
	}

	private boolean boardDesign(int row, int col) {
		if (row < 3 && col < 3 || row > 5 && col > 5 || row < 3 && col > 5 || row > 5 && col < 3
				|| row > 2 && row < 6 && col > 2 && col < 6) {
			return true;
		}
		return false;
	}
}
