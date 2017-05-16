import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Write a description of class CheckBox2 here.
 * 
 * @author Jenny Chen
 * @version 1.0 yyyy-mm-dd
 */
public class CheckBox2 implements ItemListener
{
   // class constants
   private static final int FRAME_HEIGHT = 600;
   private static final int FRAME_WIDTH = 800;
   private static final String FRAME_TITLE = "Thing";
   private static final String CHECK_BOX_TEXT = "Check me";
   private static final String DEFAULT_LABEL_TEXT = "Hi";
   private static final String NEW_LABEL_TEXT = "Bye";
   private static final Color BACKGROUND_COLOUR = Color.WHITE;
   private static final Color NEW_BACKGROUND_COLOUR = Color.RED;
   
   //instance fields
   private JCheckBox checkBox;
   private JLabel label;
   private JFrame frame;
   
   
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument)
   {
      CheckBox2 myCheckBox = new CheckBox2();
   } // end of method main(String[] argument)

   public CheckBox2()
   {
       makeFrame();
   }
   
   private void makeFrame()
   {
       frame = new JFrame(FRAME_TITLE);
       frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
       frame.getContentPane().setBackground(BACKGROUND_COLOUR);
       
       checkBox = new JCheckBox(CHECK_BOX_TEXT);
       checkBox.addItemListener(this);
       frame.add(checkBox, BorderLayout.PAGE_START);
       
       label = new JLabel(DEFAULT_LABEL_TEXT);
       frame.add(label, BorderLayout.PAGE_END);
       
       frame.pack();
       frame.setVisible(true);
   } // end of method makeFrame()
   
   public void itemStateChanged(ItemEvent event)
   {
       if (event.getSource() instanceof JCheckBox)
       {
           if (event.getSource().equals(checkBox))
           {
               if (checkBox.isSelected())
               {
                   frame.getContentPane().setBackground(NEW_BACKGROUND_COLOUR);
                   // changes check box text and text colour
                   checkBox.setText("wow");
                   checkBox.setForeground(Color.BLUE);
               }
               else 
               {
                   frame.getContentPane().setBackground(BACKGROUND_COLOUR);
                   checkBox.setText(CHECK_BOX_TEXT);
               }
           }
       }
   }
} // end of class CheckBox