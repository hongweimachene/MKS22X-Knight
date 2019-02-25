public class KnightBoard{
  private int[][] board;

  /** @throws IllegalArgumentException when either parameter is <= 0.*/
  public KnightBoard(int startingRows,int startingCols) {
    board = new int[startingRows][startingCols];
  }

  public String toString() {
    String s = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] > 0) {
          s+=board[i][j] + " ";
        } else {
          s+= " " + board[i][j] + " ";
        }
      }
      s+="\n";
    }
    return s;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return true when the board is solvable from the specified starting position
  */
  public boolean solve(int startingRow, int startingCol) {
    return solveH(startingRow, startingCol, 1);
  }

  private boolean solveH(int startingRow, int startingCol, int level) {
    if (level == board.length * board[0].length) {
      return true;
    }
    int[][] moves = new int[][] {
      {startingRow+2, startingCol-1},
      {startingRow+2, startingCol+1},
      {startingRow-2, startingCol-1},
      {startingRow-2, startingCol+1},
      {startingRow-1, startingCol+2},
      {startingRow+1, startingCol+2},
      {startingRow-1, startingCol-2},
      {startingRow+1, startingCol-2}
    };
    for (int i = 0; i < moves.length; i++) {
      if (addKnight(level, moves, i)) {
        return solveH(moves[i][0], moves[i][1], level+1);
      }
    }
    return false;
  }

  private boolean addKnight(int level, int[][] moves, int move) {
    if (moves[move][0] < board.length && moves[move][0] >= 0 && moves[move][1] < board[0].length && moves[move][1] >= 0) {
      if (board[moves[move][0]][moves[move][1]] == 0) {
        board[moves[move][0]][moves[move][1]] = level;
        return true;
      }
    }
    return false;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol) {

  }


}
