import java.util.Scanner;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku board row by row (use 0 for empty cells):");
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.println("Enter row " + (i + 1) + " (9 numbers separated by spaces):");
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        System.out.println("\nInput Sudoku board:");
        printBoard(board);

        if (solveBoard(board)) {
            System.out.println("\nSolved Sudoku board:");
            printBoard(board);
        } else {
            System.out.println("\nThis Sudoku board cannot be solved.");
        }
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
               !isNumberInColumn(board, number, column) &&
               !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int number = 1; number <= GRID_SIZE; number++) {
                        if (isValidPlacement(board, number, row, col)) {
                            board[row][col] = number;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
