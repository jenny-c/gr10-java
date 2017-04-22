
/**
 * Write a description of class WordSums here.
 * 
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class WordSums
{
  public static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument)
   {
      
   } // end of method main(String[] argument)

   public static String wordSum(String firstWord, String secondWord)
   {
     char[] word1 = firstWord.toCharArray();
     char[] word2 = secondWord.toCharArray();
     char[] newWord = new char[1000];
     char letter1 = '\u0000';
     char letter2 = '\u0000';
     int value1 = 0;
     int value2 = 0;
     int totalValue = 0;
     String word = "";
     int index = 0;

     // get letters while at least one is not empty
    while (index < word1.length || index < word2.length)
    {
      // get first letter
      if (index < word1.length)
      {
        letter1 = word1[index];
        value1 = alphabet.indexOf(letter1) + 1;
      }
      else 
      {
        value1 = 0;
      }

      // get second letter
      if (index < word2.length)
      {
        letter2 = word2[index];
        value2 = alphabet.indexOf(letter2) + 1;
      }
      else
      {
        value2 = 0;
      }

      totalValue = value1 + value2;
      if (totalValue > 26)
      {
        totalValue = totalValue - 26;
        System.out.println(totalValue);
      }
      System.out.println("total value is now: " + totalValue);
      newWord[index] = alphabet.charAt(totalValue-1);
      System.out.println(newWord[index]);
      index++;
    }
    
    for (int x = 0; x < index; x++)
    {
      word = word + newWord[x];
    }
    System.out.println(word);
    return word;
   }
} // end of class WordSums