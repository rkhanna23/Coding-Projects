/*
 * 1. It creates a constantly repeating pattern that has no end, with two pyramid like shapes (guns) deflecting off each other
 *  producing 4 blocks (glider) that slowly make their way to the bottom right of the screen. It's significant because it proves that in cellular automtata it is possible to have 
 *   configurations with limitless numbers of cells from an intital pattern with finite numbers of cells. 
 * 
 * 2. Because the game is turned based, it means we cannot change the state of the array dynamically (can't change it
 *  while scanning through each element of the 2d grid). Therefore, an entirely separate 2-D array of the same size has to
 *  be used to keep track of where the markings and only update the board after the entire grid has been scanned.
 *  We need more memory because we needed to allocate a whole new chunk memory for this new 2-D array of the same size.
 * 
 * 3. Previously, Cells were used only to map the Location on the GUI to a row and columns in the 2-D Array. With additional 
 *  functionality within the Cell class, the grid class now has to hold a 2-D array of Cells rather than rows and columns. 
 *  Cons of this: 
 *  - Implementing an entire hierarchy of classes. Meaning all the variables and methods (toggle or boxSize) not only have to exist
 *  in the Grid class(since grid has to be able to reference these methods) but also exist in Cell class (since these methods
 *  are actually implemented within the Cell class). Implementing this hierarchy of classes takes more time and means rewriting
 *  methods that are basically nothing but headers.
 *  Pros of this: 
 *  A bottom up implementation enhances the readability of the Code. The grid class will become a class meant to
 *  manage the array of Cells rather than implement the vast majority of logic in one class in addition to managing. 
 *  Other programmers can make modifications to how each individual cell reacts (maybe it changes to red or blue 
 *  for different scenarios) by simply changing the Cell class. Vice versa, if they want to add different commands to manage
 *  the Cells (draw different patterns) they could implement it into the Grid class. By changing the functionality
 *  within the classes, it aids the readability and reusability of this game for other programmers and users.
 *  
 * 4. For an board of infinite dimensions and size, we would implement an ArrayList of ArrayList (2-D ArrayList) 
 *  which can freely append new rows and new columns. When the pattern within the game extends beyond certain bounds of the window, 
 *  we can use the add method to add rows and columns to the 2-D ArrayLists, effectively enlarging the WindowSize.
 */
import java.awt.Color;
import java.awt.Container;
import javax.swing.JRootPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import objectdraw.*;
public class GameOfLife extends WindowController implements KeyListener {
	
	protected static final int WINDOW_SIZE = 616;
	protected static final int BOX_SIZE = 15;
	protected Cell lastToggledCell;
	
	protected Grid grid;
	
	public void begin() {
		int yoffset = 0;
		
		/* The coordinate system of the grid is thrown off slightly by
		 * the existence of the system menu bar.  The code below figures out
		 * the height of the menu bar. The call to resize at the end of this
		 * method takes this offset into account when making the whole grid
		 * visible. 
		 */
		Container c = this;
		while(! (c instanceof JRootPane)) {
			yoffset += (int)(c.getParent().getY());
			c = c.getParent();
		}
		grid = new Grid(WINDOW_SIZE, BOX_SIZE, canvas);
        requestFocus();
        addKeyListener(this);
        canvas.addKeyListener(this);
        lastToggledCell = null;
        resize(WINDOW_SIZE, WINDOW_SIZE + yoffset);
	}
	// Toggles the cell that was clicked on and keep track of it
	public void onMousePress(Location point) {
		/* TODO: Toggle the cell that was clicked on
		 * and keep track of what cell you just 
		 * changed. 
		 */
		lastToggledCell = grid.toggle(point); 
		

	}
	// Toggle the cell under the mouse if it wasn't the last cell to be toggled
	public void onMouseDrag(Location point) {
		/* TODO: Toggle the cell under the mouse if
		 * it wasn't the last cell to be toggled. 
		 */
		if(!lastToggledCell.equals(grid.getCell(point))) {
			lastToggledCell = grid.toggle(point); 
		}
	}
	
    // Required by KeyListener Interface.
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
 // If letter is g, calls gliderGun, else if letter is c, clears, else toggles
    public void keyTyped(KeyEvent e)
    {
    	char letter = e.getKeyChar();
    	if(letter == 'g' && lastToggledCell != null) {
    		grid.gliderGun(lastToggledCell.getRow(), lastToggledCell.getCol());
    	} else if (letter == 'c') {
    		/* TODO: Clear the grid */
    		// Clears grid
    		grid.clear();
    	}
    	else {
    		/* TODO: Toggle whether the grid is running */
    		//Toggles running
    		grid.toggleRunning();
    	}
    }
	
    public static void main(String[] args) { 
        new GameOfLife().startController(WINDOW_SIZE, WINDOW_SIZE); 
	}

}
