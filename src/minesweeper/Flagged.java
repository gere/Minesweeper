package minesweeper;

public class Flagged extends AbstractSquare implements Square {

    private final boolean bomb;
    
    public Flagged(boolean bomb) {
        this.bomb = bomb;
    }

    @Override
    public Square dig() {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public Square flag() {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public Square deflag() {
        // TODO Auto-generated method stub
        return new Untouched(this.bomb);
    }

    @Override
    public boolean hasBomb() {
        // TODO Auto-generated method stub
        return this.bomb;
    }
    
    @Override
    public boolean isFlagged() {
        return true;
    }
    
    @Override
    public String toString() {
        return "F";
    }

}
