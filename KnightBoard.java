public class KnightBoard{
  private int[] board;
  /** @throws IllegalArgumentException when either parameter is <= 0.**/
  public KnightBoard(int startingRows,int startingCols) {
    board = new[startingRows][startingCols];
  }
  public String toString() {
    String s = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        s+=board[i][j];
      }
    }
    return s;
  }
  /**
    *@throws IllegalStateException when the board contains non-zero values.
    *@throws IllegalArgumentException when either parameter is negative or out of bounds.
    *@return true when the board is solvable from the specified starting position
  */
  

}
