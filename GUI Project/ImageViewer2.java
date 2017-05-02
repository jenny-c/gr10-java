import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Write a description of class FramePractice here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class ImageViewer2
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
   ImageViewer2()
   {
       frame = new JFrame("Image Viewer");
       
       JLabel label = new JLabel("My label text goes here");
       frame.add(label);
       
       frame.pack();
       frame.setVisible(true);
   }
   
   /**
    * Sets the visibility of this frame.
    * 
    * @param shouldBeVisible whether this frame should be visible
    */
   public void setVisibility(boolean shouldBeVisible)
   {
       frame.setVisible(shouldBeVisible);
   }
} // end of class FramePractice