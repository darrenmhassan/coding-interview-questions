/**
 * Problem: 794
 * Source: https://leetcode.com/problems/valid-tic-tac-toe-state/
 */
class ValidTicTacToeState {
    public boolean validTicTacToe(String[] board) {
        int numX = count(board, 'X');
        int numO = count(board, 'O');
        if (gameWon(board, 'X')) {
            return numX == numO + 1;
        }
        if (gameWon(board, 'O')) {
            return numX == numO;
        }
        return numX == numO || numX == numO + 1;
    }
    private int count(String[] board, char xOrO) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == xOrO) {
                    count++;
                }
            }
        }
        return count;
    }
    private boolean gameWon(String[] board, char xOrO) {
        return threeInRow(board, xOrO) || threeInColumn(board, xOrO) || threeInDiag(board, xOrO);
    }
    private boolean threeInRow(String[] board, char xOrO) {
        String threeInRow = xOrO == 'X' ? "XXX" : "OOO";
        boolean gameWon = false;
        for (int i = 0; i < 3 && !gameWon; i++) {
            if (board[i] == threeInRow) {
                gameWon = true;
            }
        }
        return gameWon;
    }
    private boolean threeInColumn(String[] board, char xOrO) {
        boolean gameWon = false;
        for (int i = 0; i < 3 && !gameWon; i++) {
            if (board[0].charAt(i) == xOrO
                    && board[1].charAt(i) == xOrO
                    && board[2].charAt(i) == xOrO) {
                gameWon = true;
            }
        }
        return gameWon;
    }
    private boolean threeInDiag(String[] board, char xOrO) {
        boolean gameWon = false;
        if (board[0].charAt(0) == xOrO
                && board[1].charAt(1) == xOrO
                && board[2].charAt(2) == xOrO) {
            gameWon = true;
        }
        if (!gameWon && board[0].charAt(2) == xOrO
                && board[1].charAt(1) == xOrO
                && board[2].charAt(0) == xOrO) {
            gameWon = true;
        }
        return gameWon;
    }

    public static void main(String[] argc) {
        String[] input = {"XXX", "   ", "OOO"};
        ValidTicTacToeState sol = new ValidTicTacToeState();
        System.out.println(sol.validTicTacToe(input));
    }
}
