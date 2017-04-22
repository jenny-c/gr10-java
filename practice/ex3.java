
/**
 * Write a description of class ex3 here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ex3
{
   // static fields
   private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
   
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {
      double[] marks = new double[100];
      
      //gather marks
      System.out.print("Please input marks here, input a negative number to end.");
      
      System.out.print("Mark: ");
      try
      {
          double mark = Double.parseDouble(console.readLine());
      }
      catch (NumberFormatException exception)
      {
           System.err.print("NumberFormatException: " + exception.getMessage());
           System.out.print("Please input a number.");
        }
    }
}