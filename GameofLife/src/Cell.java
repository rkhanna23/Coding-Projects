
public class Cell {

	protected int row; //stores row number
	protected int col; //stores col number
	
	/* TODO: Initialize instance variables */
	// Cell constructor and initializes instance variables
	public Cell(int row, int col) {
		this.row = row; 
		this.col = col; 
	}
	
	/* TODO: Write the following accessor methods */
	// returns Cell row number
	public int getRow() {
		return row;
	}
	// returns Cell column number
	public int getCol() {
		return col; 
	}
	
	/* @returns true if o is a Cell and o has the same
	 * row and col as the current cell
	 * Hint: Recall that java has an instance of keyword
	 */
	// returns true of o is a Cell that equals this cell
	public boolean equals(Object o) {
		try {
			Cell temp = (Cell)o; 
			if(temp.getRow() == this.row && temp.getCol() == this.col)
				return true; 
			return false; 
			
		}
		catch (ClassCastException E) {
			System.out.println("Not a Cell type"); 
			return false; 
		}
	}
}
