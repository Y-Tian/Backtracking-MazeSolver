/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazebacktracking2;

import java.io.File;

/**
 *
 * @author tony
 */
public class Main {
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File file = new File("MazeTestGen.txt");
        MazeBacktracking maze = new MazeBacktracking(file);
        Long start = System.currentTimeMillis();
        maze.start(6, 5);
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
        
        File path = new File("MazeTestGen.txt");
        generator gen = new generator(path);
        gen.generateCSV();
    }   
}
