/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazebacktracking2;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.*;
/**
 *
 * @author tony
 */
public class MazeBacktracking {
    
    private final int max = 999;
    private final int empty = 0;
    private final int invalid = -1;
    private File file;
    private String[][] result = null;
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> bestSolution = new Stack<>();
    
    private String[][] maze;
    public int columnsMaze;
    public int rowsMaze;
    
    public MazeBacktracking(File f)
    {
        file = f;
        columnsMaze = columns();
        rowsMaze = rows();
        initialize();
        System.out.println("Columns: " + columnsMaze);
        System.out.println("Rows: " + rowsMaze);
    }
    
    public int columns()
    {
        try
        {
            int columns = empty;
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
        return empty;
    }
    
    public int rows()
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
        return empty;
    }
    
    public void initialize()
    {
        try
        {
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            maze = new String[rowsMaze][columnsMaze];
            result = new String[rowsMaze][columnsMaze];
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
    
    public void printMaze()
    {
        for(int r = 0; r < rowsMaze; r++)
        {
            System.arraycopy(maze[r], 0, result[r], 0, columnsMaze);
        }
    }
    
    public void start(int x, int y)
    {
        bestSolution.push(max);

        if(backTrack(x, y, 0) != invalid)
        {
            if(maze[0][0] == null || result[0][0] == null)
            {
                System.out.println("no paths");
            }
            else
            {
                result[x][y] = "S";
                printSolution();
            }
        }
        else
        {
            System.out.println("error");
        }
    }

    public int backTrack(int x, int y, int count)
    {
        if(maze[x][y].equals("X"))
        {
            printMaze();
            bestSolution.push(count);
            return bestSolution.peek();
        }
        else if(maze[x][y].equals("*") || maze[x][y].equals("#"))
        {
            return max;
        }
        else if(count == bestSolution.peek())
        {
            return max;
        }
        else
        {
            maze[x][y] = "*";
            stack.push(max);
            
            List moves = new ArrayList();
            moves.add(backTrack(x, y + 1, count + 1));
            moves.add(backTrack(x - 1, y, count + 1));
            moves.add(backTrack(x, y - 1, count + 1));
            moves.add(backTrack(x + 1, y, count + 1));
            
            for(int i = 0; i < 4; i++)
            {
                if((Integer)moves.get(i) < stack.peek())
                {
                    stack.push((Integer)moves.get(i));
                }
            }    

            maze[x][y] = " ";
            return max;
        }    
    }
 
    public void printSolution()
    {
        for(int r = 0; r < rowsMaze; r++)
        {
            for(int c = 0; c < columnsMaze; c++)
            {
                System.out.print(result[r][c] + "  ");
            }
            System.out.println("\r\n");
        }
        System.out.println("The shortest path is: " + bestSolution.peek());
    }
}
