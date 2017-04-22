
/**
 * Write a description of class J1 here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
import java.io.*;
public class A
{
   public static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {
      laps();
   } // end of method main(String[] argument)
   
   public static String laps() throws IOException
   {
      String[] number = new String[2];
      String input = console.readLine();
      String output = "";
      int total = 0;

      number = input.split(" ");

      int L = Integer.parseInt(number[0]);
      int M = Integer.parseInt(number[1]);
      int max = M;

      while (L < M)
      {
        M = M - L;
        total = total + L;
        output = output + total + ", ";
      }
      output = output + max;

      System.out.print(output);
      return output;
      
   }
} // end of class J1