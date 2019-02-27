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
    return solveH(startingRow, startingCol, 1);
  }

  private boolean solveH(int startingRow, int startingCol, int level) {
    if (level > board.length * board[0].length) {
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
    if (addKnight(startingRow, startingCol, level)) {
      for (int i = 0; i < moves.length; i++) {
        if (solveH(moves[i][0], moves[i][1], level+1)) return true;
      }
      removeKnight(startingRow, startingCol);
    }
    return false;
  }

  private boolean addKnight(int row, int col, int level) {
    if (row < board.length && row >= 0 && col < board[0].length && col >= 0) {
      if (board[row][col] == 0) {
        board[row][col] = level;
        return true;
      }
    }
    return false;
  }

  private boolean removeKnight(int row, int col) {
    board[row][col] = 0;
    return true;
  }

  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol) {
    return countSolutionsH(startingRow, startingCol, 1);
  }

  private int countSolutionsH(int startingRow, int startingCol, int level) {
    int count = 0;
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
    if (addKnight(startingRow, startingCol, level)) {
      if (level == board.length * board[0].length) {
        removeKnight(startingRow, startingCol);
        return 1;
      }
      for (int i = 0; i < moves.length; i++) {
        count+=countSolutionsH(moves[i][0], moves[i][1], level+1);
      }
      removeKnight(startingRow, startingCol);
    }
    return count;
  }

  public static void main(String[] args) {
    KnightBoard a = new KnightBoard(5,5);
    System.out.println(a.solve(0,0));
    System.out.println(a);
  }

}
