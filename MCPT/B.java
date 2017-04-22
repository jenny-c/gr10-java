
/**
 * Write a description of class J2 here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
import java.io.*;
public class B
{
   public static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {
      grade();
   } // end of method main(String[] argument)
   
   public static void grade() throws IOException
   {
     int N = Integer.parseInt(console.readLine());
     String[] thing = (console.readLine()).split(" ");
     int total = 0;
     for (String element : thing)
     {
       total = total + Integer.parseInt(element);
     }

     System.out.print((N*4) - total + "");
   }
} // end of class J2