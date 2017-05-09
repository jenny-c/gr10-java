import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;

/**
 * Write a description of class LanguageGame here.
 *
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class LanguageGameGUI
{
  private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
  private static final String SENTINEL_VALUE = "exit";
  private static final int NUMBER_OF_SENTENCES = 10;

  private static final int FRAME_HEIGHT = 600;
  private static final String FRAME_TITLE = "Image Viewer";
  private static final int FRAME_WIDTH = 600;
  private static final String IMAGE_CREDIT = "https://www.iconfinder.com/icons/87928/translate_icon";
  private static final String IMAGE_SOURCE = "images/translate.jpeg";

  private HashMap<Integer, SentencePairGUI> pairs = new HashMap<Integer, SentencePairGUI>();
  private SentencePairGUI currentPair;
  private String answer;
  private int goal;
  private int correctCount;
  private String language;
  private Random randomLanguage = new Random();
  private int highScore;
  private int lowestScore;
  private int lastScore;
  private String inputString;
  private ImageComponent image1;

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
   public LanguageGameGUI() throws IOException
   {
     // images
     image1 = new ImageComponent(IMAGE_SOURCE);

     // sentences
     final String ENGLISH_FILE = "english.text";
     final String FRENCH_FILE = "french.text";
     final String RESULTS_FILE = "previousResults.text";

     BufferedReader englishFile = new BufferedReader(new FileReader(ENGLISH_FILE));
     BufferedReader frenchFile = new BufferedReader(new FileReader(FRENCH_FILE));
     BufferedReader resultsFile = new BufferedReader(new FileReader(RESULTS_FILE));

     // assign to hashmap
     for (int i = 0; i < NUMBER_OF_SENTENCES; i++)
     {
       String englishSentence = englishFile.readLine();
       String frenchSentence = frenchFile.readLine();
       pairs.put((i+1), new SentencePairGUI(englishSentence, frenchSentence));
     }

     // set scores
     highScore = Integer.parseInt(resultsFile.readLine());
     lowestScore = Integer.parseInt(resultsFile.readLine());
     lastScore = Integer.parseInt(resultsFile.readLine());

     // wrap up
     englishFile.close();
     frenchFile.close();
     resultsFile.close();

     // set default values
     goal = -1;
     correctCount = 0;
     language = "";
   }

   /**
    *
    */
   public void startGame() throws IOException
   {
     // get information -> when to stop
     setGoal();

     // show last score
     if (lastScore != 0)
     {
       System.out.println("Try to beat your last score: " + lastScore);
     }

     // print random sentence
     // check answer if right and change as necessary
     do
     {
       getRandomSentencePair();
       checkAnswer();
     }
     while (correctCount != goal && !(answer.equals(SENTINEL_VALUE)));

     // change file
     changeResultsFile();

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
   private void setGoal() throws IOException
   {
     goal = -1;
     boolean valid = false;
     while (!valid)
     {
       inputString = JOptionPane.showInputDialog("Would you like to set a goal? ");
       switch (inputString)
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
           JOptionPane.showMessageDialog(null, "There will be no maximum number of sentences.", "Thing", JOptionPane.INFORMATION_MESSAGE);
           valid = true;
           break;

         default:
           JOptionPane.showMessageDialog(null, "It looks like you didn't choose a valid response. Please choose either yes or no.", "Error", JOptionPane.ERROR_MESSAGE);
       }
     }
   } // end of method setGoal()

   /**
    * Checks the correctness of the user's answer.
    */
   private void checkAnswer() throws IOException
   {
     String currentSentence;

     // randomize language
     if (randomLanguage.nextInt(2) == 0)
     {
       currentSentence = currentPair.getEnglishSentence();
       language = "english";
     }
     else
     {
       currentSentence = currentPair.getFrenchSentence();
       language = "french";
     }
     answer = JOptionPane.showInputDialog("Translate (\"exit\" to exit): " + currentSentence);

     // check answer
     if (!answer.equals(SENTINEL_VALUE))
     {
       if (language.equals("english"))
       {
         if (answer.equals(currentPair.getFrenchSentence()))
         {
           currentPair.setCorrectness(true);
           JOptionPane.showMessageDialog(null, "Correct!");
           correctCount++;
         }
         else
         {
           JOptionPane.showMessageDialog(null, "Wrong..");
         }
       }
       else
       {
         if (answer.equals(currentPair.getEnglishSentence()))
         {
           currentPair.setCorrectness(true);
           JOptionPane.showMessageDialog(null, "Correct!");
           correctCount++;
         }
         else
         {
           JOptionPane.showMessageDialog(null, "Wrong..");
         }
       }
     }
   }

   /**
    * Displays count of correct sentences.
    */
   private void displayResults()
   {
     JOptionPane.showMessageDialog(image1, "\nNumber of sentences correct: " +
      correctCount + "\n        All time high score: " + highScore +
      "\n      All time lowest score: " + lowestScore);
   } // end of displayResults()

   private void changeResultsFile() throws IOException
   {
     final String RESULTS_FILE = "previousResults.text";

     PrintWriter outputFile = new PrintWriter(new FileWriter(RESULTS_FILE));

     if (correctCount > highScore)
     {
       highScore = correctCount;
     }
     if (correctCount < lowestScore)
     {
       lowestScore = correctCount;
     }
     lastScore = correctCount;

     outputFile.println(highScore);
     outputFile.println(lowestScore);
     outputFile.println(lastScore);

     outputFile.close();
   }

   /*
    * A component with a drawn image.
    */
   private class ImageComponent extends Component
   {
     // class fields
     private static final int NO_PROBLEMS_ENCOUNTERED = 0;
     private static final int PROBLEMS_ENCOUNTERED = -1;

     // instance fields
     private BufferedImage bufferedImage;
     private int status;

     /* constructors */

     public ImageComponent(String fileName)
     {
       bufferedImage = null;
       status = NO_PROBLEMS_ENCOUNTERED;
       try
       {
         bufferedImage = ImageIO.read(new File(fileName));
       }
       catch (IOException exception)
       {
         status = PROBLEMS_ENCOUNTERED;
       } // end of catch(IOException exception)
     } // end of constructor ImageComponent(String fileName)

     /* accessors */

     public int getStatus()
     {
       return status;
     } // end of method getStatus()



   }
} // end of class randomLanguageGame
