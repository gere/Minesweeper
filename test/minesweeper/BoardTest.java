/* Copyright (c) 2007-2017 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * TODO: Description
 */
public class BoardTest {
    
    
    // TODO: Testing strategy
    
    private static final Board randomBoard = new Board(10, 10);
    private static final String str = "4 3\n0 0 0 0\n0 0 1 0\n0 0 0 0\n";
    private static final ByteArrayInputStream inBytes = new ByteArrayInputStream(str.getBytes());
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(inBytes));
    
    
    
    public void testRandomPrint() {
        
        System.out.println(randomBoard.toString());        
        randomBoard.dig(1,2);
        System.out.println(randomBoard.toString());
    }
    
    @Test
    public void testInputBoard() throws IOException {
        Board inputBoard = new Board(in);
        System.out.println(inputBoard.toString());
        System.out.println(inputBoard.flag(2,1));
        System.out.println(inputBoard.dig(2,1));
        System.out.println(inputBoard.deflag(2,1));
        System.out.println(inputBoard.dig(2,1));
        System.out.println(inputBoard.dig(3,1));
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO: Tests
    
}
