/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mazebacktracking;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author tony
 */
public class MazeBacktracking {
    
    public static String[][] maze;
    public static String[][] intersections;
    public static int columns;
    public static int rows;
    public static int smallestPath;
    public static int right = 0;
    public static int left = 0;
    public static int down = 0;
    public static int up = 0;
    public static boolean intersection = false;
    //#'s are walls
    //spaces are open paths
    //X is the end
    //S is the start
    //*'s are used paths

    public static void initialize(File file)
    {
        try
        {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            columns = columns(file);
            rows = rows(file);
            intersections = new String[rows][columns];
            
            maze = new String[rows][columns];
            int c = 0;
            while(sc.hasNextLine())
            {
                String[] thisLine = sc.nextLine().split(",");
                for(int i = 0; i < thisLine.length; i++)
                {
                    maze[c][i] = thisLine[i];
                }
                c++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public static int columns(File file)
    {
        try
        {
            int columns = 0;
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            String[] c = sc.nextLine().split(",");
            for(int i = 0; i < c.length; i++)
            {
                columns++;
            }
            return columns;
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    public static int rows(File file)
    {
        try
        {
            int rows = 0;
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            while(sc.hasNextLine())
            {
                rows++;
                sc.nextLine();
            }
            return rows;
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return 0;
    }
    
    public static void start(int x, int y)
    {
        if(backTrack(x, y))
        {
            maze[x][y] = "S";
        }
        else
        {
            System.out.println("Error");
        }
    }
    
    public static boolean backTrack(int x, int y)
    {
        if(maze[x][y].equals("*") || maze[x][y].equals("#"))
        {
            return false;
        }
        if(maze[x][y].equals("X"))
        {
            return true;
        }
        maze[x][y] = "*";
        boolean b;
        b = backTrack(x, y + 1);
        if(b)
        {
            return true;
        }
        b = backTrack(x - 1, y);
        if(b)
        {
            return true;
        }
        b = backTrack(x, y - 1);
        if(b)
        {
            return true;
        }
        b = backTrack(x + 1, y);
        if(b)
        {
            return true;
        }
        maze[x][y] = " ";
        return false;
    }
    
    public static boolean backTrack2(int x, int y)
    {
        if(maze[x][y].equals("*") || maze[x][y].equals("#"))
        {
            return false;
        }
        if(maze[x][y].equals("X"))
        {
            return true;
        }
        maze[x][y] = "*";
        boolean b;
        b = backTrack(x, y + 1);
        if(b)
        {
            return true;
        }
        b = backTrack(x - 1, y);
        if(b)
        {
            return true;
        }
        b = backTrack(x, y - 1);
        if(b)
        {
            return true;
        }
        b = backTrack(x + 1, y);
        if(b)
        {
            return true;
        }
        maze[x][y] = " ";
        return false;
    }
    
    public static void findIntersections()
    {
        int count = 0;
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                if(maze[r][c].equals(" "))
                {
                    if(maze[r][c + 1].equals(" "))
                        count++;
                    if(maze[r][c - 1].equals(" "))
                        count++;
                    if(maze[r + 1][c].equals(" "))
                        count++;
                    if(maze[r - 1][c].equals(" "))
                        count++;
                }
                if(count >= 3)
                {
                    intersections[r][c] = "I";
                }
                count = 0;
            }
        }
    }
    
    public static boolean ifAtIntersection(int x, int y)
    {
        if(intersections[x][y].equals("I"))
            return true;
        else
            return false;
    }
    
    public static void printSolution()
    {
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                System.out.print(maze[r][c] + "  ");
            }
            System.out.println("\r\n");
        }
        System.out.println(path());
    }
    
    public static int path()
    {
        int path = 0;
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                if(maze[r][c].equals("*"))
                {
                    path++;
                }
            }
        }
        return path;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File file = new File("MazeTest.txt");
        initialize(file);
        start(1, 1);
        printSolution();
    }
}
