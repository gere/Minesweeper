package minesweeper;

public class Untouched extends AbstractSquare implements Square {
    
    private final boolean bomb;

    public Untouched(boolean bomb) {
        this.bomb = bomb;
    }

    @Override
    public Square dig() {        
        return new Dug();
    }

    @Override
    public Square flag() {
        
        return new Flagged(this.bomb);
    }

    @Override
    public Square deflag() {
        return this;
    }
    
    public boolean isUntouched() {
        return true;
    }

    @Override
    public boolean hasBomb() {
        return this.bomb;
    }
    
    @Override 
    public String toString() {
        return("-");
    }

}
