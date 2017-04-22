
/**
 * Write a description of class TriangleTypes here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class TriangleTypes
{
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument)
   {
      triangleType(60, 60, 60);
   } // end of method main(String[] argument)

   public static String triangleType(int angle1, int angle2, int angle3)
   {
       String angleDescription = "";
       String sideDescription = "";

       if (angle1 == 90 || angle2 == 90 || angle3 == 90)
       {
         angleDescription = "right";
       }
       else if (angle1 > 90 || angle2 > 90 || angle3 > 90)
       {
         angleDescription = "obtuse";
       }
       else
       {
         angleDescription = "acute";
       }
       
       if (angle1 == angle2 && angle2 == angle3)
       {
         sideDescription = " equilateral";
       }
       else if (angle1 != angle2 && angle1 != angle3 && angle2 != angle3)
       {
         sideDescription = " scalene";
       }
       else
       {
         sideDescription = "isosceles";
       }
       System.out.println(angleDescription + sideDescription);
       return angleDescription + sideDescription;
  }
} // end of class TriangleTypes