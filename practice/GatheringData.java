/**
 * Write a description of class GatheringData here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class GatheringData
{   
    // static fields
    private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    
    //instance fields
    
    /**
     * 
     */
    public static void main(String[] argument) throws IOException
    {   
        // get number of marks
        System.out.print("Number of marks: ");
        String input = console.readLine();
        int numberOfMarks = Integer.parseInt(input);
        int[] marks = new int[numberOfMarks];
        
        // gather marks
        for (int count = 0; count < marks.length; count++)
        {
            System.out.print("Mark " + (count + 1) + ": ");
            input = console.readLine();
            marks[count] = Integer.parseInt(input);
        }
        
        System.out.println();
        
        // display marks in descneding order
        for (int count = marks.length - 1; count > -1; count--)
        {
            System.out.println("Mark " + (count + 1) + ": " + marks[count]);
        }
    } // end of method main(String[] argument)
} // end of class GatheringData
