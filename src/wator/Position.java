package wator;

/**
 * Cette classe représente une position
 * @author  Jérémy Bossut et Julia Leven
 */
public class Position {

	// L'ordonné de la position (la hauteur)
	private final int row;
	
	// L'abscisse de la position l'abscisse)
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
    
    
    /**
     * Give the position to the leftup of the current position
     * @return position to the leftup
     */
    public Position leftup () {
        return new Position((row - 1), (col - 1));
    }

    /**
     * Give the position to the rightup of the current position
     * @return position to the rightup
     */
    public Position rightup () {
        return new Position((row - 1), (col + 1));
    }

    /**
     * Give the position leftdown of the current position
     * @return position leftdown
     */
    public Position leftdown () {
        return new Position((row + 1), (col - 1));
    }

    /**
     * Give the position rightdown of the current position
     * @return position rightdown
     */
    public Position rightdown () {
        return new Position((row + 1), (col + 1));
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