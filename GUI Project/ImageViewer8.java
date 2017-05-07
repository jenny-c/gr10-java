import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
/**
 * Write a description of class FramePractice here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class ImageViewer8 implements ActionListener
{
   // class constants
   private String LABEL2 = "Disappear!";
   private int DELAY = 4000;
    
   // instance fields
   private JFrame frame;
   private JLabel label;
   private JButton button;
   
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
   ImageViewer8()
   {
       frame = new JFrame("Image Viewer");
       
       label = new JLabel("My label text goes here");
       frame.add(label);
       
       frame.pack();
       frame.setVisible(true);
       
       button = new JButton(LABEL2);
       frame.add(button, BorderLayout.PAGE_END);
       button.addActionListener(this);
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
   
   
   /**
    * Sets the new label for this frame.
    * 
    * @param labelText new label for this frame
    */
   public void setLabel(String labelText)
   {
       if (labelText != null)
       {
           frame.remove(label);
       
           label = new JLabel(labelText);
           frame.add(label);
       
           frame.setVisible(true);
       }
   }
   
   public void actionPerformed(ActionEvent event)
   {
       Object source = event.getSource();
       
       if (source == button)
       {
           { 
               if (button.getText().equals(LABEL2))
               {
                   if (DELAY < 0) return;
                   
                   try
                   {
                       frame.setVisible(false);
                       Thread.sleep(DELAY);
                       frame.setVisible(true);
                   }
                   catch (InterruptedException exception)
                   {
                       // empty cuz we dont need it
                   }
               }
           }
       }
   }
} // end of class FramePractice