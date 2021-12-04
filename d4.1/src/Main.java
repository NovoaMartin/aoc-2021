import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static class Board {
        int[][] board;

        public Board(int[][] board) {
            this.board = board;
        }

        public void mark(int n) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == n) {
                        board[i][j] = -1;
                    }
                }
            }
        }

        public boolean checkWin() {
            boolean isColumnWin;
            boolean isRowWin;
            for (int i = 0; i < 5; i++) {
                isRowWin = true;
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] != -1) {
                        isRowWin = false;
                        break;
                    }
                }
                if (isRowWin) {
                    return true;
                }
            }
            for (int i = 0; i < 5; i++) {
                isColumnWin = true;
                for (int j = 0; j < 5; j++) {
                    if (board[j][i] != -1) {
                        isColumnWin = false;
                        break;
                    }
                }
                if (isColumnWin) {
                    return true;
                }
            }

            return false;
        }

        public void win(int n) {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] != -1) {
                        sum += board[i][j];
                    }
                }
            }
            System.out.println("Sum: " + sum);
            System.out.println("N: " + n);
            System.out.println(sum * n);
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("d4.1/input.txt"));
        int[] numbers = Arrays.stream(sc.next().split(",")).mapToInt(Integer::parseInt).toArray();
        ArrayList<Board> boards = new ArrayList<>();
        while (sc.hasNextInt()) {
            int[][] board = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
            boards.add(new Board(board));
        }

        for (int n : numbers) {
            for (Board board : boards) {
                board.mark(n);
                if (board.checkWin()) {
                    board.win(n);
                    return;
                }
            }
        }
    }
}
