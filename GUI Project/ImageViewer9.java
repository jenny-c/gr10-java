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
public class ImageViewer9 implements ActionListener
{
   // class constants
   private String DISAPPEAR_LABEL = "Disappear!";
   private String QUIT_LABEL = "Quit";
   private int DELAY = 4000;
    
   // instance fields
   private JFrame frame;
   private JLabel label;
   private JButton button;
   
   private JButton quitButton;
  
   
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
   ImageViewer9()
   {
       frame = new JFrame("Image Viewer");
       
       label = new JLabel("My label text goes here");
       frame.add(label, BorderLayout.PAGE_START);
       
       frame.pack();
       frame.setVisible(true);
       
       button = new JButton(DISAPPEAR_LABEL);
       frame.add(button, BorderLayout.LINE_END);
       button.addActionListener(this);
       
       quitButton = new JButton(QUIT_LABEL);
       frame.add(quitButton, BorderLayout.CENTER);
       quitButton.addActionListener(this);

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
               if (button.getText().equals(DISAPPEAR_LABEL))
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
       else if (source == quitButton)
       {
           System.exit(0);
        }
   }
} // end of class FramePractice