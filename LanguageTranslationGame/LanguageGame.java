import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * A game in which the player must successfully translate sentences.
 *
 * @author Jenny Chen
 * @version 1.0 2017-05-26
 */
public class LanguageGame
{
  // static fields
  private static final Color BACKGROUND_COLOUR = Color.WHITE;
  private static BufferedReader console =
    new BufferedReader(new InputStreamReader(System.in));
  private static final int DEFAULT_IMAGE_INDEX = 10;
  private static final String ERROR_IMAGE_UNAVAILABLE = "Something went wrong.";
  private static final String FILE_UNAVAILABLE = "File not found.";
  private static final int FRAME_HEIGHT = 700;
  private static final String FRAME_TITLE = "Translation Game";
  private static final int FRAME_WIDTH = 1100;
  private static final int NUMBER_OF_IMAGES = 11;
  private static final int NUMBER_OF_SENTENCES = 10;
  private static final String SENTINEL_VALUE = "exit";
  private static final int TEXT_FIELD_LENGTH = 20;

  // instance fields
  private ButtonListener actionListener;
  private String answer;
  private JPanel buttonPanel;
  private int correctCount;
  private JLabel credit;
  private SentencePair currentPair;
  private String currentSentence;
  private JFrame frame;
  private int goal;
  private int highScore;
  private JLabel highScoreLabel;
  private ImageComponent[] image;
  private String[] imageCredit;
  private String language;
  private ImageComponent lastImage;
  private int lastScore;
  private int lowestScore;
  private HashMap<Integer, SentencePair> pairs;
  private JLabel promptLabel;
  private JButton quitButton;
  private JLabel sentenceLabel;
  private int sentenceNumber;
  private JPanel sentencePanel;
  private GridLayout sentencePanelLayout;
  private JButton startButton;
  private JButton submitButton;
  private JTextField textField;
  private JLabel textFieldLabel;


  /**
   * Launches the game.
   *
   * @param argument not used
   */
  public static void main(String[] argument) throws IOException
  {
    LanguageGame game = new LanguageGame();
  } // end of method main(String[] argument)

  /* constructors */

  /**
   * Constructs a default language game.
   */
  public LanguageGame() throws IOException
  {
    // initialize instance fields
    actionListener = new ButtonListener();
    answer = "";
    correctCount = 0;
    goal = -1;
    language = "";
    sentencePanelLayout = new GridLayout(10, 2);

    loadImages();

    makeFrame();

    loadSentences();

    loadScores();
  } // end of constructor LanguageGame()

  /* private methods */

  /*
   * Updates the results for the next game.
   */
  private void changeResultsFile() throws IOException
  {
    final String RESULTS_FILE = "previousResults.text";
    PrintWriter outputFile = new PrintWriter(new FileWriter(RESULTS_FILE));

    // Update results as necessary.
    if (correctCount > highScore)
    {
      highScore = correctCount;
    } // end of if (correctCount > highScore)
    if (correctCount < lowestScore)
    {
      lowestScore = correctCount;
    } // end of if (correctCount < lowestScore)
    lastScore = correctCount;

    // Change results in file.
    outputFile.println(highScore);
    outputFile.println(lowestScore);
    outputFile.println(lastScore);

    // Wrap up.
    outputFile.close();
  } // end of method changeResultsFile()

  /*
   * Checks the correctness of the user's answer.
   */
  private void checkAnswer() throws IOException
  {
    SentencePair sentence;

    // Check answer.
    if (!answer.equals(SENTINEL_VALUE))
    {
      if (language.equals("english") &&
        answer.equals(currentPair.getFrenchSentence()) ||
        language.equals("french") &&
        answer.equals(currentPair.getEnglishSentence()))
      {
        currentPair.setCorrectness(true);
        JOptionPane.showMessageDialog(null, "Correct!");
        correctCount++;
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Wrong..");
      }
    } // end of if (!answer.equals(SENTINEL_VALUE))

    // Reset and update frame.
    textField.setText("");

    if (correctCount > highScore)
    {
      highScore = correctCount;
      highScoreLabel.setText("high score: " + highScore);
    } // end of if (correctCount > highScore)

    // Continue game if not over.
    runGame();
  } // end of method checkAnswer()

  /*
   * Displays count of correct sentences.
   */
  private void displayResults()
  {
    JOptionPane.showMessageDialog(null, "\nNumber of sentences correct: " +
      correctCount + "\n        All time high score: " + highScore +
      "\n      All time lowest score: " + lowestScore);
  } // end of displayResults()

  /*
   * Ends the game.
   */
  private void endGame()
  {
    try
    {
      changeResultsFile();

      displayResults();
    }
    catch (IOException exception)
    {
    } // end of try
  } // end of method endGame()

  /*
   * Returns a random sentence pair.
   */
  private void getRandomSentencePair()
  {
    Random rand = new Random();
    do
    {
      sentenceNumber = rand.nextInt(pairs.size()+1);
      currentPair = pairs.get(sentenceNumber);
    }
    while (currentPair.isCorrect());
  } // end of method getRandomSentencePair()

  /*
   * Loads images into arrays.
   */
  private void loadImages() throws IOException
  {
    image = new ImageComponent[NUMBER_OF_IMAGES];
    imageCredit = new String[NUMBER_OF_IMAGES];

    final String IMAGE_FILE = "images.text";
    final String IMAGE_CREDIT_FILE = "imageCredits.text";

    BufferedReader imageFile = new BufferedReader(new FileReader(IMAGE_FILE));
    BufferedReader imageCreditFile =
      new BufferedReader(new FileReader(IMAGE_CREDIT_FILE));

    // Assign images and credits to arrays.
    for (int i = 0; i < NUMBER_OF_IMAGES; i++)
    {
      try
      {
        image[i] = new ImageComponent("images/" + imageFile.readLine());
        imageCredit[i] = imageCreditFile.readLine();
      }
      catch (Exception exception)
      {
        System.out.println(FILE_UNAVAILABLE);
      }
    } // end of for (int i = 0...)

    // Wrap up.
    imageFile.close();
    imageCreditFile.close();
  } // end of method loadImages()

  /*
   * Sets starting score values.
   */
  private void loadScores() throws IOException
  {
    final String RESULTS_FILE = "previousResults.text";

    BufferedReader resultsFile =
      new BufferedReader(new FileReader(RESULTS_FILE));

    // Set scores.
    try
    {
      highScore = Integer.parseInt(resultsFile.readLine());
      lowestScore = Integer.parseInt(resultsFile.readLine());
      lastScore = Integer.parseInt(resultsFile.readLine());
    }
    catch (Exception exception)
    {
      System.out.println(FILE_UNAVAILABLE);
    }

    // Wrap up.
    resultsFile.close();
  } // end of method loadScores()

  /*
   * Loads sentences into hashmap.
   */
  private void loadSentences() throws IOException
  {
    pairs = new HashMap<Integer, SentencePair>();

    final String ENGLISH_FILE = "english.text";
    final String FRENCH_FILE = "french.text";

    BufferedReader englishFile =
      new BufferedReader(new FileReader(ENGLISH_FILE));
    BufferedReader frenchFile =
      new BufferedReader(new FileReader(FRENCH_FILE));

    // Assign sentences to hashmap.
    for (int i = 0; i < NUMBER_OF_SENTENCES; i++)
    {
      try
      {
        String englishSentence = englishFile.readLine();
        String frenchSentence = frenchFile.readLine();
        pairs.put((i+1), new SentencePair(englishSentence, frenchSentence));
      }
      catch (Exception exception)
      {
        System.out.println(FILE_UNAVAILABLE);
      }
    } // end of for (int...)

    // Wrap up.
    englishFile.close();
    frenchFile.close();
  } // end of method loadSentences()

  /*
   * Constructs the panel for displaying sentences and receiving answers.
   */
  private void makeSentencePanel()
  {
    sentencePanel = new JPanel();
    sentencePanel.setLayout(sentencePanelLayout);

    promptLabel = new JLabel("Translate: ");
    sentencePanel.add(promptLabel);

    sentenceLabel = new JLabel("");
    sentencePanel.add(sentenceLabel);

    textFieldLabel = new JLabel("Answer (\"exit\" to exit): ");
    sentencePanel.add(textFieldLabel);

    textField = new JTextField(TEXT_FIELD_LENGTH);
    sentencePanel.add(textField);

    submitButton = new JButton("submit");
    submitButton.addActionListener(actionListener);
    sentencePanel.add(submitButton);

    highScoreLabel = new JLabel("high score: " + highScore);
    sentencePanel.add(highScoreLabel);
  } // end of method makeSentencePanel()

  /*
   * Constructs the button panel.
   */
  private void makeButtonPanel()
  {
    buttonPanel = new JPanel();
    buttonPanel.setBackground(BACKGROUND_COLOUR);

    startButton = new JButton("start game");
    startButton.addActionListener(actionListener);
    buttonPanel.add(startButton);

    quitButton = new JButton("quit");
    quitButton.addActionListener(actionListener);
    buttonPanel.add(quitButton);
  } // end of method makeButtonPanel()

  /*
   * Creates the application frame and its content.
   */
  private void makeFrame() throws IOException
  {
    frame = new JFrame(FRAME_TITLE);
    frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    frame.getContentPane().setBackground(BACKGROUND_COLOUR);

    // Show default image.
    frame.add(image[DEFAULT_IMAGE_INDEX], BorderLayout.CENTER);

    if (image[DEFAULT_IMAGE_INDEX].getStatus() == 0)
    {
      credit = new JLabel(imageCredit[DEFAULT_IMAGE_INDEX]);
    }
    else
    {
      credit = new JLabel(ERROR_IMAGE_UNAVAILABLE);
    } // end of if (image.getStatus() == 0)
    frame.add(credit, BorderLayout.PAGE_START);
    lastImage = image[DEFAULT_IMAGE_INDEX];

    // Create panels.
    makeButtonPanel();
    frame.add(buttonPanel, BorderLayout.PAGE_END);

    makeSentencePanel();
    frame.add(sentencePanel, BorderLayout.LINE_END);

    frame.pack();
    frame.setVisible(true);
  } // end of method makeFrame()

  /*
   * Selects a random language.
   */
  private void randomizeLanguage()
  {
    Random randomLanguage = new Random();

    // Randomize language.
    if (randomLanguage.nextInt(2) == 0)
    {
      currentSentence = currentPair.getEnglishSentence();
      language = "english";
    }
    else
    {
      currentSentence = currentPair.getFrenchSentence();
      language = "french";
    } // end of if (randomLanguage.nextInt(2) == 0)

    // Update sentence.
    sentenceLabel.setText(currentSentence);

    updateImage();
  } // end of method randomizeLanguage

  /*
   * Continues the game.
   */
  private void runGame()
  {
    // Continue game.
    if (correctCount != goal && !(answer.equals(SENTINEL_VALUE)))
    {
      getRandomSentencePair();
      randomizeLanguage();
    }
    // Game is over.
    else
    {
      endGame();
    }// end of if (correctCount != goal..)
  }

  /*
   * Sets player goal
   */
  private void setGoal() throws IOException
  {
    boolean valid = false;

    // Sets goal if requested.
    while (!valid)
    {
      String inputString =
        JOptionPane.showInputDialog("Would you like to set a goal? ");

      switch (inputString)
      {
        case "Yes":
        case "yes":
        case "Y":
        case "y":
          while (goal < 1)
          {
            inputString = JOptionPane.showInputDialog(null,
              "What is your goal for number of correct sentences? ");
            try
            {
              goal = Integer.parseInt(inputString);
              if (goal < 1)
              {
                throw new NumberFormatException("");
              } // end of if (goal < 1)
            }
            catch (NumberFormatException exception)
            {
              JOptionPane.showMessageDialog(null,
                "It looks like you didn't give a valid number." +
                " Please choose a maximum integer above 0.");
            } // end of try
          } // end of while (goal < 1)
          valid = true;
          break;

        case "No":
        case "no":
        case "N":
        case "n":
          JOptionPane.showMessageDialog(null,
            "There will be no maximum number of sentences.",
            "Maximum number", JOptionPane.INFORMATION_MESSAGE);
          valid = true;
          break;

        default:
          JOptionPane.showMessageDialog(null,
          "It looks like you didn't choose a valid response." +
          " Please choose either yes or no.", "Error",
          JOptionPane.ERROR_MESSAGE);
      } // end of switch(inputString)
    } // end of while(!valid)
  } // end of method setGoal()

  /*
   * Starts a new game.
   */
  private void startGame() throws IOException
  {
    setGoal();

    // Show last score.
    if (lastScore != 0)
    {
      JOptionPane.showMessageDialog(null,
        "Try to beat your last score: " + lastScore);
    } // end of if (lastScore != 0)

    runGame();
  } // end of method startGame()

  /*
   * Updates the image to match with the sentences.
   */
  private void updateImage()
  {
    // Change image to correspond with sentence.
    frame.remove(lastImage);
    int imageIndex = sentenceNumber-1;
    frame.add(image[imageIndex], BorderLayout.CENTER);

    credit.setText(imageCredit[imageIndex]);

    lastImage = image[imageIndex];
  } // end of method updateImage()

  /* classes */

  private class ButtonListener implements ActionListener
  {
    /*
     * Responds to button events.
     */
    public void actionPerformed(ActionEvent event)
    {
    Object source = event.getSource();

      if (source == startButton)
      {
        try
        {
          startGame();
        }
        catch (IOException exception)
        {
        } // end of try
      }
      else if (source == quitButton)
      {
        System.exit(0);
      }
      else if (source == submitButton)
      {
        answer = textField.getText();
        try
        {
          checkAnswer();
        }
        catch (IOException exception)
        {
        }
      } // end of if (source == startButton)
    } // end of method actionPerformed(ActionEvent event)
  } // end of class ButtonListener

  /*
   * A component with a drawn image.
   */
  private class ImageComponent extends Component
  {
    // static fields
    private static final int NO_PROBLEMS_ENCOUNTERED = 0;
    private static final int PROBLEMS_ENCOUNTERED = -1;

    // instance fields
    private BufferedImage bufferedImage;
    private int status;

    /* constructors */

    /**
     * Creates a component with a drawn image.
     */
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
      } // end of catch (IOException exception)
    } // end of constructor ImageComponent(String fileName)

    /* accessors */

    /**
     * Returns the status of this image component
     *
     * @return a int representing the status of this image compoenent
     */
    public int getStatus()
    {
      return status;
    } // end of method getStatus()

    /**
     * Returns a string representation of this component.
     *
     * @return a string representing this component
     */
    public String toString()
    {
      return
        getClass().getName()
        + "["
        + "buffered image: " + bufferedImage
        + ", status: " + status
        + "]";
    } // end of method toString()

    /* mutators */

    /*
     * Called when the contents of the component should be painted.
     */
    public void paint(Graphics graphicsContext)
    {
      graphicsContext.drawImage(bufferedImage, 0, 0, null);
    } gend of method paint(Graphics graphicsContext)
  } // end of class ImageComponent
} // end of class LanguageGame
