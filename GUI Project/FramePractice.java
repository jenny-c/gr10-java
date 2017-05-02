import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Write a description of class FramePractice here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class FramePractice
{
   // instance fields
   private JFrame frame;
   
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument)
   {
      
   } // end of method main(String[] argument)
   
   /**
    * Constructs a frame.
    */
   FramePractice()
   {
       frame = new JFrame("Image Viewer");
       
       JLabel label = new JLabel("My label text goes here");
       frame.add(label);
       
       frame.pack();
       frame.setVisible(true);
   }
} // end of class FramePractice