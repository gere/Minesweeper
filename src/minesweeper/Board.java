/* Copyright (c) 2007-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * TODO: Specification
 */
public class Board {
    
    // TODO: Abstraction function, rep invariant, rep exposure, thread safety
    
    // TODO: Specify, test, and implement in problem 2
    private final Square[][] board;
    private final int rows;
    private final int cols;
    private final double BOMB_PERCENTAGE = 0.10;
    
    private class Coordinate {
        public int col;
        public int row;
        
        public Coordinate(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
    
    public Board(int x, int y) {
       rows = x;
       cols = y;
       board = new Square[rows][cols];
       for(int row = 0; row < rows; row++) {
           for (int col = 0; col < cols; col++) {
               boolean hasBomb = Math.random() < BOMB_PERCENTAGE; 
               board[row][col] = new Untouched(hasBomb);
           }
       }
    }
    public Board(BufferedReader in) throws IOException{
        
        
        String[] firstLine = in.readLine().split(" ");
        
        cols = Integer.parseInt(firstLine[0]);
        rows = Integer.parseInt(firstLine[1]);
        board = new Square[rows][cols];
        
        int row = 0;
        while (in.ready()) {
            String[] line = in.readLine().split(" ");
            int col = 0;
            for (String v : line) {
                boolean hasBomb = (v.equals("1")) ? true : false;
                board[row][col] = new Untouched(hasBomb);
                col++;
            }
            row++;
        }       
    }  
    
    public String dig(int col, int row) {
        if (checkCoordinates(col, row)) {
            Square sq = board[row][col];
            if (sq.isUntouched()) {
                if (sq.hasBomb()) boom(col, row);
                else {
                    board[row][col] = new Dug();
                    checkAndDigNeighbors(col, row);
                }
            }
        }        
        return this.toString();
    }
    
    public String flag(int col, int row) {
        if (checkCoordinates(col, row)) {
            Square sq = board[row][col];
            if (sq.isUntouched()) board[row][col] = sq.flag();
        }
        return this.toString();
    }
    
    public String deflag(int col, int row) {
        if (checkCoordinates(col, row)) {
            Square sq = board[row][col];
            if (sq.isFlagged()) board[row][col] = sq.deflag();
        }
        return this.toString();
    }
    
    private void checkAndDigNeighbors(int col, int row) {
        List<Coordinate> neighbors = getNeighbors(col, row);
        List<Coordinate> toCheck = new ArrayList<Coordinate>();
        boolean noBombsInNeighbors = true;
        for (Coordinate c : neighbors) {
            Square sq = board[c.row][c.col];
            if (sq.hasBomb()) {
                noBombsInNeighbors = false;
                break;
            }
            else if (sq.isUntouched() && !sq.isDug()) {
                toCheck.add(c);
            }            
        }
        if (noBombsInNeighbors) {
            for (Coordinate c : toCheck) {
                board[c.row][c.col] = new Dug();
                checkAndDigNeighbors(c.col, c.row);
            }
        }
    }
    
    private List<Coordinate> getNeighbors(int col, int row) {
        
        List<Coordinate> n = new ArrayList<Coordinate>();
        int prevRow = row - 1;
        int nextRow = row + 1;
        int prevCol = col - 1;
        int nextCol = col + 1;
        
        if (checkCoordinates(prevCol, prevRow)) n.add(new Coordinate(prevCol, prevRow));    // TOP LEFT
        if (checkCoordinates(col, prevRow)) n.add(new Coordinate(col, prevRow));            // TOP MIDDLE
        if (checkCoordinates(nextCol, prevRow)) n.add(new Coordinate(nextCol, prevRow));    // TOP RIGHT
        if (checkCoordinates(prevCol, row)) n.add(new Coordinate(prevCol, row));            // MIDDLE LEFT
        if (checkCoordinates(nextCol, row)) n.add(new Coordinate(nextCol, row));            // MIDDLE RIGHT
        if (checkCoordinates(prevCol, nextRow)) n.add(new Coordinate(prevCol, nextRow));    // BOTTOM LEFT
        if (checkCoordinates(col, nextRow)) n.add(new Coordinate(col, nextRow));            // BOTTOM MIDDLE
        if (checkCoordinates(nextCol, nextRow)) n.add(new Coordinate(nextCol, nextRow));    // BOTTOM RIGHT        
        
        return n;
    }
    
    public void boom(int col, int row) {
        System.out.println(String.format("boom at row: %d, col: %d\n", row, col));
    }
    
    private boolean checkCoordinates(int col, int row) {
        return col >= 0 && row >= 0 && row < rows && col < cols; 
    }
    
    @Override 
    public String toString() {
        String result = "";
        for(int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Square sq = board[row][col];
                String strSq = sq.toString();            
                result += (col == 0) ? strSq : String.format(" %s", strSq);
            }
            result += '\n';
        }
        return result;
    }
}
