import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Write a description of class ex9 here.
 *
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */

public class ex9
{
   // static fields
   private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

   private static final int NUMBER_OF_STUDENTS = 5;
   private static final int NUMBER_OF_ASSIGNMENTS = 3;
   private static double mark;
   private static int assignmentCount;
   private static int studentCount;
   private static double[][] marks = new double[NUMBER_OF_STUDENTS][NUMBER_OF_ASSIGNMENTS];
   private static double[] markMeans = new double[NUMBER_OF_ASSIGNMENTS];

   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {
      while (studentCount < NUMBER_OF_STUDENTS)
      {
        assignmentCount = 0;
        System.out.println("\nMarks for student " + (studentCount+1) + ": ");

        // gather marks
        getMarks();

        studentCount++;
      } // end of for (int studentCount = 0; studentCount < NUMBER_OF_STUDENTS; studentCount++)

      calculateMeans();

      displayResults();

   } // end of method main(String[] argument)

   /**
    * Gathers marks from user.
    */
   private static void getMarks() throws IOException
   {
     while (assignmentCount < NUMBER_OF_ASSIGNMENTS)
     {
       String input;
       Boolean valid = false;
       while (!valid)
       {
         System.out.print("Assignment" + (assignmentCount+1) + ": ");
         try
         {
           mark = Double.parseDouble(console.readLine());
           if (mark <= 100 && mark >= 0)
           {
             valid = true;
           }
           else
           {
            System.out.println("Please input a number from 0-100");
           }
         }
         catch (NumberFormatException exception)
         {
           System.err.print("NumberFormatException: " + exception.getMessage());
           System.out.println("Please input a valid mark from 0-100.");
         }
       }
       marks[studentCount][assignmentCount] = mark;
       valid = false;
       assignmentCount++;
     }
   } // end of method getMarks()

   /**
    * Calculates means for each assignment.
    */
   private static void calculateMeans()
   {
     int count = 0;
     double total;
     for (int i = 0; i < NUMBER_OF_ASSIGNMENTS; i++)
     {
       total = 0;
       for (double[] n : marks)
       {

         total = total + n[count];
       } // end of for (double assignmentMarks : s)
       markMeans[count] = total / NUMBER_OF_STUDENTS;
       count++;
     } // end of for (double[] s : marks)
   } // end of method calculateMeans()

   /**
   * Displays marks and differences.
   */
   private static void displayResults()
   {
     int count = 0;
     int i = 0;
     for (double[] s : marks)
     {
       count++;
       i = 0;
       System.out.print("\nMarks for student " + count + ": [");
       for (double mark : s)
       {
         System.out.print(mark);
         i++;
         if (i < NUMBER_OF_ASSIGNMENTS)
         {
           System.out.print(" ");
         }
       } // end of for (double mark : s)
       System.out.print("]");
     } // end of for (double [] s : marks)
   } // end of method displayResults()

} // end of class ex9
