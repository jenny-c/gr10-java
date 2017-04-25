import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
/**
 * Write a description of class LanguageGame here.
 *
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class LanguageGame
{
  private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
  private static final String SENTINEL_VALUE = "exit";
  private static final int NUMBER_OF_SENTENCES = 10;

  private  HashMap<Integer, SentencePair> pairs = new HashMap<Integer, SentencePair>();
  private SentencePair currentPair;
  private String answer;
  private String language;
  private int goal;
  private int correctCount;

   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument) throws IOException
   {

   } // end of method main(String[] argument)

   /**
    * Constructs a default language game.
    */
   public LanguageGame() throws IOException
   {
     // sentences
     final String ENGLISH_FILE = "english.text";
     final String FRENCH_FILE = "french.text";

     BufferedReader englishFile = new BufferedReader(new FileReader(ENGLISH_FILE));
     BufferedReader frenchFile = new BufferedReader(new FileReader(FRENCH_FILE));

     // assign to hashmap
     for (int i = 0; i < NUMBER_OF_SENTENCES; i++)
     {
       String englishSentence = englishFile.readLine();
       String frenchSentence = frenchFile.readLine();
       pairs.put((i+1), new SentencePair(englishSentence, frenchSentence));
     }

     // wrap up
     englishFile.close();
     frenchFile.close();
     
     // set default values
     goal = -1;
     language = "english";
     correctCount = 0;
   }

   /**
    *
    */
   public void startGame() throws IOException
   { 
     // get information -> when to stop
     setInformation();
     
     // print random sentence
     // check answer if right and change as necessary
     do
     {
       getRandomSentencePair();
       printSentence();
       checkAnswer();
     }
     while (correctCount != goal && !(answer.equals(SENTINEL_VALUE)));

     // display results
     displayResults();
   } // end of method startGame()

   /**
    * Returns a random sentence pair.
    *
    * @return a random sentence pair
    */
   private void getRandomSentencePair()
   {
     Random rand;
     do 
     {
       rand = new Random();
       currentPair = pairs.get(rand.nextInt(pairs.size())+1);
     }
     while (currentPair.isCorrect());
   } // end of method getRandomSentencePair()

   /**
    * Sets player information.
    */
   private void setInformation() throws IOException
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
   private void printSentence() throws IOException
   {
     {
       if (language.equals("english"))
       {
         System.out.println(currentPair.getEnglishSentence());
       }
       else
       {
         System.out.println(currentPair.getFrenchSentence());
       }
     }
   } // end of method printSentenceAndCheckAnswer()

   /**
    * Checks the correctness of the user's answer.
    */
   private void checkAnswer() throws IOException
   {
     // get answer and change correctness as needed
     System.out.print("Translation (\"exit\" to exit): ");
     answer = console.readLine();
     if (language.equals("english"))
     {
       if (answer.equals(currentPair.getFrenchSentence()))
       {
         currentPair.setCorrectness(true);
         System.out.println("correct!");
         correctCount++;
       }
     }
     else
     {
       if (answer.equals(currentPair.getEnglishSentence()))
       {
         currentPair.setCorrectness(true);
         System.out.println("correct!");
         correctCount++;
       }
     }
   }

   /**
    * Displays count of correct sentences.
    */
   private void displayResults()
   {
     System.out.println("Number of sentences correct:" + correctCount);
   } // end of displayResults()
} // end of class LanguageGame
