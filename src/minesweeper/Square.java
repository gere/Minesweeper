package minesweeper;
/**
 * Operazioni: 
 * dig
 * flag 
 * deflag
 * toString
 * hasBomb
 * Data type definition:
 *  Square = Untouched(bomb:bool)
 *         + Dug()
 *         + Flagged(bomb:bool)
 * 
 * 
 * 
 * 
 * 
 * 
 * @author simo
 *
 */
public interface Square {
    
    public Square dig();
    public Square flag();
    public Square deflag();
    public boolean hasBomb();
    
    default public boolean isDug() {
        return false;
    }
    
    default public boolean isUntouched() {
        return false;
    }
    
    default public boolean isFlagged() {
        return false;
    }
    
    @Override public String toString();
    
}
