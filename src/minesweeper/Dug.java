package minesweeper;

public class Dug implements Square {

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
        return this;
    }

    @Override
    public boolean hasBomb() {
        return false;
    }
    
    @Override
    public String toString() {
        return " ";
    }
    
    @Override
    public boolean isDug() {
        return true;
    }

}
