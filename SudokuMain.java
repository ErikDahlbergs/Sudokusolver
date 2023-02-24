package bst;

public class SudokuMain {


	
	public static void main(String[] args) {
		SudokuSolver sudokuSolver = new SolverOfSudoku();
		GUI Sudoku = new GUI(sudokuSolver);
	}

}
