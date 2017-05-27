package minesweeper;

public abstract class AbstractSquare {
    protected int count = 0;
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public void decrementCount() {
        if (this.count > 0) this.count--;
    }
    
    public int getCount() {
        return count;
    }
}
