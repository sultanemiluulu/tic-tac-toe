import java.util.Scanner;

public class Main {
    public static void output(int[][] board) {
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 1) System.out.print("X ");
                else if (board[i][j] == 2) System.out.print("O ");
                else System.out.print("  ");
                if (j == 2) {
                    System.out.println("|");
                }
            }
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {

        int ROWS = 3, COLS = 3;
        int[][] board = new int[ROWS][COLS];
        int CROSS = 1;
        int NOUGHT = 2;
        int MOVE = 0;
        boolean END = false;

//        OUTPUT
        output(board);

//      MAIN LOOP
        while (!END) {
//          MAKE MOVE
            int xCoordinate = 0, yCoordinate = 0;

            boolean isCorrect = false;

            while (!isCorrect) {
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter the coordinates: ");

                if (scanner.hasNextInt()) {
                    xCoordinate = scanner.nextInt() - 1;
                }
                else {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                if (scanner.hasNextInt()) {
                    yCoordinate = scanner.nextInt() - 1;
                }
                else {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                if ((xCoordinate > 2 || xCoordinate < 0) || (yCoordinate > 2 || yCoordinate < 0)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                if (board[xCoordinate][yCoordinate] == 0) {
                    if (MOVE % 2 == 0)
                        board[xCoordinate][yCoordinate] = CROSS;
                    else board[xCoordinate][yCoordinate] = NOUGHT;
                    isCorrect = true;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }

            MOVE++;

            int crossCount = 0, noughtCount = 0;
            int WINNER = 0;

//          CHECK ROWS
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == CROSS) {
                        crossCount++;
                    } else if (board[i][j] == NOUGHT) {
                        noughtCount++;
                    }
                }
                if (crossCount == ROWS & WINNER == 0) {
                    WINNER += 1;
                } else if (noughtCount == ROWS & WINNER == 0) {
                    WINNER += 2;
                }
                crossCount = 0;
                noughtCount = 0;
            }

//          CHECK COLUMNS
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[j][i] == CROSS) {
                        crossCount++;
                    } else if (board[j][i] == NOUGHT) {
                        noughtCount++;
                    }
                }
                if (crossCount == ROWS & WINNER == 0) {
                    WINNER += 1;
                } else if (noughtCount == ROWS & WINNER == 0) {
                    WINNER += 2;
                }
                crossCount = 0;
                noughtCount = 0;
            }

//          CHECK BOTTOM TO TOP DIAGONAL
            for (int i = 0; i < board.length; i++) {
                if (board[board.length - i - 1][i] == CROSS) {
                    crossCount++;
                } else if (board[board.length - i - 1][i] == NOUGHT) {
                    noughtCount++;
                }
                if (crossCount == ROWS & WINNER == 0) {
                    WINNER += 1;
                } else if (noughtCount == ROWS & WINNER == 0) {
                    WINNER += 2;
                }
            }

            crossCount = 0;
            noughtCount = 0;

//          CHECK TOP TO BOTTOM DIAGONAL
            for (int i = 0; i < board.length; i++) {
                if (board[i][i] == CROSS) {
                    crossCount++;
                } else if (board[i][i] == NOUGHT) {
                    noughtCount++;
                }
                if (crossCount == ROWS & WINNER == 0) {
                    WINNER += 1;
                } else if (noughtCount == ROWS & WINNER == 0) {
                    WINNER += 2;
                }
            }

//          NEW OUTPUT
            output(board);

//          CHECK GAME END
            if (WINNER == 1) {
                System.out.println("X wins");
                END = true;
            } else if (WINNER == 2) {
                System.out.println("O wins");
                END = true;
            } else if (WINNER == 0 && MOVE == 9) {
                System.out.println("Draw");
                END = true;
            }
        }

    }
}
