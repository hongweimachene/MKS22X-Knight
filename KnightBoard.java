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
        if (board[i][j] > 9) {
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

  }

  private boolean solveH(int startingRow, int startingCol, int level) {

  }

  private boolean addKnight(int row, int col) {
    int[][] moves = new int[][] {
      {row+2, col-1},
      {row+2, col+1},
      {row-2, col-1},
      {row-2, col+1},
      {row-1, col+2},
      {row+1, col+2},
      {row-1, col-2},
      {row+1, col-2}
    };
    
  }
  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol) {

  }


}
