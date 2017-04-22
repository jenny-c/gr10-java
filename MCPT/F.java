
/**
 * Write a description of class J2 here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
import java.io.*;
public class F
{
   public static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {
      lock();
   } // end of method main(String[] argument)
   
   public static void lock() throws IOException
   {
       System.out.print("l");
       boolean goingUp = true;
       int currentNumber = 1;
       int numberOfSteps = 0;
       int index = 0;
       int otherIndex = 0;
       int dialMax = 0;
       int[] number;
       int comboNumber = 0;
       int currentRotation = 1;
       int total = 0;
       
       String[] input1 = console.readLine().split(" ");
       String[] input2 = console.readLine().split(" ");
       int[] intInput2 = new int[input2.length];
       

       dialMax = Integer.parseInt(input1[0]);
       comboNumber = Integer.parseInt(input1[1]);
       
       for (String thing : input2)
       {
           intInput2[otherIndex] = Integer.parseInt(thing);
           otherIndex++;
       }

       for (int i = 0; i < input2.length; i++)
       {
         
         if (i == (input2.length-1))
         {
             break;
          }
         if (goingUp)
         {
           if (intInput2[0] != 1)
           {
               total = total + (intInput2[i+1] - 1);
               i--;
            } else {
             
           total = total + (intInput2[i+1] - intInput2[i]);
        }
         }
         else
         {
           total = total + (dialMax - (intInput2[i+1] - intInput2[i]));
         }
         goingUp = !goingUp;
       }
       System.out.print(total);
   }
} // end of class J2