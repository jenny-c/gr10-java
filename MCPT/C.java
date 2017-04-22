
/**
 * Write a description of class J2 here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
import java.io.*;
public class C
{
   public static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {
      cookie();
   } // end of method main(String[] argument)
   
   public static void cookie() throws IOException
   {
     int total = 0;
     int money = Integer.parseInt(console.readLine());
     String[] egg = console.readLine().split(" ");
     String[] sugar = console.readLine().split(" ");
     String[] flour = console.readLine().split(" ");

     int eggTotal = Integer.parseInt(egg[0]) * Integer.parseInt(egg[1]);
     int sugarTotal = Integer.parseInt(sugar[0]) * Integer.parseInt(sugar[1]);
     int flourTotal = Integer.parseInt(flour[0]) * Integer.parseInt(flour[1]);
     total = eggTotal + sugarTotal + flourTotal;
     if (total <= money)
     {
       System.out.print("Monica can afford $" + total + ".");
     } else {
       System.out.print("Monica cannot afford $" + total + ".");
     }
   }
} // end of class J2