public class Tile{
  private int row, col, moves;
  public Tile(int rows, int cols, int edges) {
    row = rows;
    col = cols;
    moves = edges;
  }
  public int getRow() {
    return row;
  }
  public int getCol() {
    return col;
  }
  public int getMoves() {
    return moves;
  }

  public String toString() {
    return "" + moves;
  }
}
