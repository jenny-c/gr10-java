
/**
 * Write a description of class J2 here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
import java.io.*;
public class D
{
   public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   public static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {
      spies();
   } // end of method main(String[] argument)
   
   public static void spies() throws IOException
   {
      int n = Integer.parseInt(console.readLine());
      String[] sentence = console.readLine().split("-");
      String[] newSentence = new String[sentence.length];
      String letter = "";
      int index = 0;
      int otherIndex = 0;

      for (String word : sentence)
      {
        char[] newWord = word.toCharArray();
        index = 0;
        for (char thing : newWord)
        {
          if ((alphabet.indexOf(thing) + n) >= 26)
          {
               newWord[index] = alphabet.charAt((alphabet.indexOf(thing) + n) - 26);
          }
          else 
          {
              newWord[index] = alphabet.charAt(alphabet.indexOf(thing) + n);
          }
          index++;

          

      }
      
      char[] w = word.toCharArray();
          int i = 1;
          char currentChar = 'a';
          while (i < w.length)
          {
            currentChar = newWord[i];
            newWord[i] = newWord[i-1];
            newWord[i-1] = currentChar;
            i = i + 2;
          }
          newSentence[otherIndex] = "";
          for (char element : newWord)
          {
            newSentence[otherIndex] = newSentence[otherIndex] + element;
          }
          otherIndex++;

   }
   for (String element : newSentence)
   {
       System.out.print(element + " ");
    }
}
} // end of class J2