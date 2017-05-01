import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Write a description of class OptionPane1 here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class OptionPane1
{
   private static final String OUTPUT_DIALOGUE_TITLE = "Results";
   private static final String ERROR_DIALOGUE_TITLE = "Error";
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {
      String integerParseError = "Oops, you didn't input a valid integer, try again.";
      
      // dialogue box
      String inputString = JOptionPane.showInputDialog("Input? ");
      String messageString = "Your input: " + inputString;
      JOptionPane.showMessageDialog(null, messageString, OUTPUT_DIALOGUE_TITLE, JOptionPane.INFORMATION_MESSAGE);
      
      // integer parsing
      boolean valid = false;
      int number = 0;
      while (!valid)
      {
          inputString = JOptionPane.showInputDialog("Integer? ");
          try 
          {
              number = Integer.parseInt(inputString);
              valid = true;
          }
          catch (NumberFormatException exception)
          {
              JOptionPane.showMessageDialog(null, integerParseError, ERROR_DIALOGUE_TITLE, JOptionPane.ERROR_MESSAGE);
          }
      }
      messageString = "Your integer: " + number;
      JOptionPane.showMessageDialog(null, messageString, OUTPUT_DIALOGUE_TITLE, JOptionPane.INFORMATION_MESSAGE);
      
      
      // Terminate input thread.
      System.exit(0);
   } // end of method main(String[] argument)

} // end of class OptionPane1