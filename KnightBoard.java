public class KnightBoard{
  private int[][] board;
  //data structure
  private Tile[][] data;

  //Constructor
  /** @throws IllegalArgumentException when either parameter is <= 0.*/
  public KnightBoard(int startingRows,int startingCols) {
    if (startingRows < 0 || startingCols < 0) throw new IllegalArgumentException();
    board = new int[startingRows][startingCols];
    data = new Tile[startingRows][startingCols];
    fillEdges();
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

  private void clearBoard(){
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = 0;
      }
    }
  }
  //to check if board has anything other than 0
  private boolean isEmpty(){
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != 0) {
          return false;
        }
      }
    }
    return true;
  }
  /**
  *@throws IllegalStateException when the board contains non-zero values.
  *@throws IllegalArgumentException when either parameter is negative or out of bounds.
  *@return true when the board is solvable from the specified starting position
  */
  public boolean solve(int startingRow, int startingCol) {
    if (!isEmpty()) throw new IllegalStateException();
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) throw new IllegalArgumentException();
    return solveH(startingRow, startingCol, 1);
  }

  private boolean solveH(int startingRow, int startingCol, int level) {
    //when it reaches all tiles on the board, it is solved
    if (level > board.length * board[0].length) {
      return true;
    }
    //array of possible moves given coordinates
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
    //add beginning position
    if (addKnight(startingRow, startingCol, level)) {
      //loop through all possible moves from the inital position, continue recursively for next moves
      for (int i = 0; i < moves.length; i++) {
        if (solveH(moves[i][0], moves[i][1], level+1)) return true;
      }
      //if no move in the array is possible, remove the knight
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
    if (!isEmpty()) throw new IllegalStateException();
    if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) throw new IllegalArgumentException();
    return countSolutionsH(startingRow, startingCol, 1);
  }

  private int countSolutionsH(int startingRow, int startingCol, int level) {
    //number of solutions
    int count = 0;
    ////array of possible moves given coordinates
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
      //base case, if it reaches the end of the board, remove that piece and go through other moves, return 1 as a solution was found
      if (level == board.length * board[0].length) {
        removeKnight(startingRow, startingCol);
        return 1;
      }
      //loop through all possible moves from the inital position, continue recursively for next moves, adding on to the number of solutions
      for (int i = 0; i < moves.length; i++) {
        count+=countSolutionsH(moves[i][0], moves[i][1], level+1);
      }
      ////if no move in the array is possible, remove the knight
      removeKnight(startingRow, startingCol);
    }
    return count;
  }

  //initialize data structure
  private String fillEdges() {
    String s = "";
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[i].length; j++) {
        //majority of the board can make 8 moves max 
        data[i][j] = new Tile(i, j, 8);
        //cases for 6 moves
        if (i == 1 || j == 1|| i == data.length - 2 || j == data.length - 2) {
          data[i][j] = new Tile(i, j, 6);
        }
        //cases for 4 moves
        if (i == 0 || j == 0 || i == data.length - 1 || j == data.length - 1 || (i == data.length - 2 || i == 1 && j == 1) || (i == 1 && j == data[i].length - 2) || (i == data.length - 2 && j == data[i].length - 2)) {
          data[i][j] = new Tile(i, j, 4);
        }
        //cases for 3 moves
        if ((i == 1 || i == data.length - 2 && j == 0) || (j == 1 || j ==data[i].length - 2 && i == data.length - 1) || (j == 1 || j == data[i].length - 2 && i == 0) || (i == 1 || i == data.length - 2 && j == data[i].length - 1)){
          data[i][j] = new Tile(i, j, 3);
        }
        //cases for 2 moves
        if ((j == 0 || j == data[i].length -1 && i == 0) || (j == 0 || j == data[i].length - 1 && i == data.length - 1)) {
          data[i][j] = new Tile(i, j, 2);
        }
        s += "" + data[i][j];
      }
      s += "\n";
    }
    return s;
  }

}
