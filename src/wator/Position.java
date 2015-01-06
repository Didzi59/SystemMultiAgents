package wator;

public class Position {

	private final int row;
	private final int col;
	
	/**
	 * Constructor of a Position
	 *
	 * @param row row of the position
	 * @param col column of the position
	 */
	public Position (int row, int col) {
	    this.row = row;
	    this.col = col;
	}

    /**
     * Give the position to the left of the current position
     * @return position to the left
     */
    public Position left () {
        return new Position(row, (col - 1));
    }

    /**
     * Give the position to the right of the current position
     * @return position to the right
     */
    public Position right () {
        return new Position(row, (col + 1));
    }

    /**
     * Give the position up of the current position
     * @return position up
     */
    public Position up () {
        return new Position((row - 1), col);
    }

    /**
     * Give the position down of the current position
     * @return position down
     */
    public Position down () {
        return new Position((row + 1), col);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj != null && (obj instanceof Position)) {
            Position p = (Position) obj;
            return (this.row == p.getRow() && this.col == p.getCol());
        }
        return false;
    }

    /**
     * Get the row
     * @return row
     */
    public int getRow () {
        return this.row;
    }

    /**
     * Get the column
     * @return column
     */
    public int getCol () {
        return this.col;
    }

    @Override
    public String toString () {
        return "Position(" + row + "," + col + ")";
    }

    @Override
    public int hashCode () {
        int result = row;
        result = 31 * result + col;
        return result;
    }
	
}