import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * This is Assignment 2. 
 *
 * @author Wang, Talia
 * @version 2017-03-20
 */
public class Assignment2
{
    // constants    
    private static final int MAX_MARKS = 100;
        
    // static fields
    private static BufferedReader console = 
    new BufferedReader(new InputStreamReader(System.in));
    
    // instance fields
    private static int[] mark = new int[MAX_MARKS];

    // methods
    
    /**
     * Gathers marks and displays them and their mean.
     * Rejects marks which are out of range.
     */
    public static void gatherAndDisplay() throws IOException
    {
        // local variables
        double sum = 0;
        int markCount = 0;
        int numberOfMarks = 0;
        int input = 0;
        boolean valid = false;
        
        while (markCount < MAX_MARKS)
        {
            while (!valid)
            {
                System.out.print((markCount + 1) + ": ");
                try
                {
                    input = Integer.parseInt(console.readLine());
                    valid = true;
                }
                catch (NumberFormatException exception)
                {
                    System.out.println("Please enter a valid mark.");
                } // end of try
            } // end of while (!valid)
            if (input >= 0 && input <= 100)
            {
                mark[markCount] = input;
                sum = sum + mark[markCount];
                markCount = markCount + 1;
            }
            else
            {
                numberOfMarks = markCount;
                markCount = MAX_MARKS;
            } // end of if (input >= 0...)
            valid = false;
        } // end of while (mark >= 0)

        // display marks  
        System.out.println("");
        for (int index = 0; index < numberOfMarks; index ++)
        {
            System.out.println((index + 1) + ": " + mark[index]);
        }
        
        // display mean of marks
        if (numberOfMarks > 0)
        {
            System.out.println("\nMean: " + (sum / numberOfMarks));
        }
        else 
        {
            System.out.println("No marks were entered.");
        } // end of if (markCount > 0)

    }
}