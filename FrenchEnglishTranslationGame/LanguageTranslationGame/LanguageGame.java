import java.util.HashMap;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
/**
 * Write a description of class LanguageGame here.
 *
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class LanguageGame
{
  private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
  private static HashMap<Integer, SentencePair> pairs = new HashMap<Integer, SentencePair>();
  private static String language;
  private static int goal;
  private static int correctCount;
   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {
     // sentences
     SentencePair pair1 = new SentencePair("What's your name?", "Comment vous appelez-vous?");
     SentencePair pair2 = new SentencePair("Pleased to meet you!", "Enchanté(e)!");
     SentencePair pair3 = new SentencePair("I'm from...", "Je viens de...");
     SentencePair pair4 = new SentencePair("I live in...", "J’habite à...");
     SentencePair pair5 = new SentencePair("What is your profession?", "Qu’est-ce que vous faites?");
     SentencePair pair6 = new SentencePair("What do you do in your free time?", "Qu’est-ce que vous aimez faire pendant votre temps libre?");
     SentencePair pair7 = new SentencePair("How’s the weather?", "Quel temps fait-il?");
     SentencePair pair8 = new SentencePair("Do you have siblings?", "Est-ce que vous avez des frères et sœurs?");
     SentencePair pair9 = new SentencePair("What's your favorite movie?", "Quel est ton/votre film préféré?");
     SentencePair pair10 = new SentencePair("Have you visited...?", "Est-ce que vous avez visité...?");

     SentencePair[] sentence = {pair1, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, pair10};

     for (int i = 0; i < english.length; i++)
     {
       pairs.put((i), sentence[i]);
     }

     // get information -> when to stop
     setInformation();

     // print random sentence
     printSentenceAndCheckAnswer();

     // display results
     displayResults();
   } // end of method main(String[] argument)


   /**
    * Returns a random sentence pair.
    *
    * @return a random sentence pair
    */
   public static SentencePair getRandomSentencePair()
   {
     Random rand = new Random();
     return pairs.get(rand.nextInt(pairs.size()));
   } // end of method getRandomSentencePair()

   /**
    * Sets player information.
    */
   public static void setInformation() throws IOException
   {
     goal = -1;
     boolean valid = false;
     while (!valid)
     {
       System.out.print("What language (english or french) would you like to translate from? ");
       switch (console.readLine())
       {
         case "english":
         case "English":
         case "eng":
         case "Eng":
           language = "english";
           valid = true;
           break;

         case "french":
         case "French":
         case "fre":
         case "Fre":
           language = "french";
           valid = true;
           break;

         default:
           System.out.println("It looks like you didn't pick a valid language. Please choose either french or english.");
           break;
       }
    }
     valid = false;
     while (!valid)
     {
       System.out.print("Would you like to set a goal? ");
       switch (console.readLine())
       {
         case "Yes":
         case "yes":
         case "Y":
         case "y":
           while (goal < 1)
           {
             System.out.print("What is your goal for number of correct sentences? ");
             try
             {
               goal = Integer.parseInt(console.readLine());
               if (goal < 1)
               {
                 throw new NumberFormatException("");
               }
             }
             catch (NumberFormatException exception)
             {
               System.out.println("It looks like you didn't give a valid number. Please choose a maximum integer above 0.");
             }
           }
           valid = true;
           break;

         case "No":
         case "no":
         case "N":
         case "n":
           System.out.println("There will be no maximum number of sentences.");
           valid = true;
           break;

         default:
           System.out.println("It looks like you didn't choose a valid response. Please choose either yes or no.");
       }
     }
   } // end of method setInformation()

   /**
    * Prints a random sentence.
    */
   public static void printSentenceAndCheckAnswer() throws IOException
   {
     correctCount = 0;
     String sentinelValue = "exit";
     String answer;
     SentencePair currentPair;
     do
     {
       // get a sentence pair that isn't correct
       do
       {
         currentPair = getRandomSentencePair();
       }
       while (currentPair.isCorrect());

       if (language.equals("english"))
       {
         System.out.println(currentPair.getEnglishSentence());
         correctCount++;
       }
       else
       {
         System.out.println(currentPair.getFrenchSentence());
         correctCount++;
       }

       // get answer and change correctness as needed
       System.out.print("Translation (\"exit\" to exit): ");
       answer = console.readLine();
       if (language.equals("english"))
       {
         if (answer.equals(currentPair.getFrenchSentence()))
         {
           currentPair.setCorrectness(true);
           System.out.print("correct!");
         }
       }
       else
       {
         if (answer.equals(currentPair.getEnglishSentence()))
         {
           currentPair.setCorrectness(true);
           System.out.print("correct!");
         }
       }
     }
     while (correctCount != goal && !(answer.equals(sentinelValue)));
   } // end of method printSentenceAndCheckAnswer()

   /**
    * Displays count of correct sentences.
    */
   public static void displayResults()
   {
     System.out.println("Number of sentences correct:" + correctCount);
   } // end of displayResults()
} // end of class LanguageGame
