/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mazebacktracking2;
import java.io.*;
import java.util.Random;
/**
 *
 * @author tony
 */
public class generator {
    
    String[][] maze;
    int r;
    int c;
    File file;
    boolean placedX;
    
    public generator(File path)
    {
        file = path;
        placedX = false;
    }
    
    public int flip()
    {
        Random random = new Random();
        int r = random.nextInt(2);
        return r;
    }
    
    public void gen(int min, int max)
    {
        Random randomR = new Random();
        r = randomR.nextInt(max - min + 1) + min;
        Random randomC = new Random();
        c = randomC.nextInt(max - min + 1) + min;
        maze = new String[r][c];

        fillBorder(r, c);
        fillGrid(r, c);
        placeX();
    }
    
    public void fillBorder(int r, int c)
    {
        for(int row = 0; row < r; row++)
        {
            maze[row][0] = "#";
            maze[row][c - 1] = "#";
        }
        for(int column = 1; column < c - 1; column++)
        {
            maze[0][column] = "#";
            maze[r - 1][column] = "#";
        }     
    }
    
    public void fillGrid(int dx, int dy)
    {
        for(int row = 1; row < dx - 1; row++)
        {
            for(int column = 1; column < dy - 1; column++)
            {
                if(flip() > 0)
                {
                    maze[row][column] = "#";
                }
                else
                {
                    maze[row][column] = " ";
                }
            }
        }
    }
    
    public void placeX()
    {
        for(int row = 0; row < r; row++)
        {
            for(int column = 0; column < c; column++)
            {
                if(!maze[row][column].equals("#") && !placedX)
                {
                    maze[row][column] = "X";
                    placedX = true;
                }
            }
        }
    }
    
    public void generateCSV()
    {
        try
        {
            gen(3, 12);
            FileWriter fw = new FileWriter(file);
            for(int row = 0; row < r; row++)
            {
                for(int column = 0; column < c; column++)
                {
                    fw.write(maze[row][column] + ",");
                }
                fw.write("\r\n");
            }
            fw.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}
