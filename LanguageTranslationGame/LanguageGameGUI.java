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
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.HashMap;
import java.util.Random;

/**
 * A game in which the player must successfully translate randomly-presentence sentences.
 *
 * @author Jenny Chen
 * @version 1.0 2017-05-26
 */
public class LanguageGameGUI
{
  // static fields
  private static final Color BACKGROUND_COLOUR = Color.WHITE;
  private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
  private static final String DEFAULT_IMAGE_CREDIT = "https://www.iconfinder.com/icons/87928/translate_icon";
  private static final String DEFAULT_IMAGE_SOURCE = "images/translate.png";
  private static final String ERROR_IMAGE_UNAVAILABLE = "Something went wrong.";
  private static final int FRAME_HEIGHT = 700;
  private static final String FRAME_TITLE = "Translation Game";
  private static final int FRAME_WIDTH = 1100;
  private static final String HOME_IMAGE_CREDIT = "https://www.iconfinder.com/icons/126572/home_house_icon";
  private static final String HOME_IMAGE_SOURCE = "images/home.png";
  private static final String MOVIE_IMAGE_CREDIT = "http://ideas.wikia.com/wiki/Category:Movies";
  private static final String MOVIE_IMAGE_SOURCE = "images/movie.jpeg";
  private static final int NUMBER_OF_SENTENCES = 10;
  private static final String SENTINEL_VALUE = "exit";

  // instance fields
  private String answer;
  private ButtonListener actionListener = new ButtonListener();
  private JPanel buttonPanel;
  private int correctCount;
  private SentencePairGUI currentPair;
  private String currentSentence;
  private JFrame frame;
  private int goal;
  private int highScore;
  private JLabel highScoreLabel;
  private ImageComponent homeImage;
  private ImageComponent image;
  private JLabel imageCredit;
  private String inputString;
  private String language;
  private ImageComponent lastImage;
  private int lastScore;
  private int lowestScore;
  private ImageComponent movieImage;
  private HashMap<Integer, SentencePairGUI> pairs = new HashMap<Integer, SentencePairGUI>();
  private JLabel promptLabel;
  private JButton quitButton;
  private JPanel sentencePanel;
  private GridLayout sentencePanelLayout = new GridLayout(10, 2);
  private JLabel sentenceLabel;
  private int sentenceNumber;
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
    LanguageGameGUI game = new LanguageGameGUI();
  } // end of method main(String[] argument)

  /**
   * Constructs a default language game.
   */
  public LanguageGameGUI() throws IOException
  {
    // construct frame
    makeFrame();

    // initialize images
    homeImage = new ImageComponent(HOME_IMAGE_SOURCE);
    movieImage = new ImageComponent(MOVIE_IMAGE_SOURCE);

    // initialze sentences
    final String ENGLISH_FILE = "english.text";
    final String FRENCH_FILE = "french.text";
    final String RESULTS_FILE = "previousResults.text";

    BufferedReader englishFile = new BufferedReader(new FileReader(ENGLISH_FILE));
    BufferedReader frenchFile = new BufferedReader(new FileReader(FRENCH_FILE));
    BufferedReader resultsFile = new BufferedReader(new FileReader(RESULTS_FILE));

    // assign sentences to hashmap
    for (int i = 0; i < NUMBER_OF_SENTENCES; i++)
    {
      String englishSentence = englishFile.readLine();
      String frenchSentence = frenchFile.readLine();
      pairs.put((i+1), new SentencePairGUI(englishSentence, frenchSentence));
    } // end of for (int...)

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
  } // end of constructor LanguageGameGUI()

  /*
   * Starts a new game.
   */
  private void startGame() throws IOException
  {
    // get goal if wanted by user
    setGoal();

    // show last score
    if (lastScore != 0)
    {
      JOptionPane.showMessageDialog(null, "Try to beat your last score: " + lastScore);
    } // end of if (lastScore != 0)

    // print random sentence
    // check answer if right and count as necessary
    getRandomSentencePair();
    randomizeLanguage();
  } // end of method startGame()

  /*
   * Constructs the button panel for starting and quitting the game.
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

    textField = new JTextField(20);
    sentencePanel.add(textField);

    submitButton = new JButton("submit");
    submitButton.addActionListener(actionListener);
    sentencePanel.add(submitButton);

    highScoreLabel = new JLabel("high score: " + highScore);
    sentencePanel.add(highScoreLabel);
  } // end of method makeSentencePanel()

  /*
   * Creates the application frame and its content.
   */
  private void makeFrame() throws IOException
  {
    frame = new JFrame(FRAME_TITLE);
    frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    frame.getContentPane().setBackground(BACKGROUND_COLOUR);

    image = new ImageComponent(DEFAULT_IMAGE_SOURCE);
    frame.add(image, BorderLayout.CENTER);

    if (image.getStatus() == 0)
    {
      imageCredit = new JLabel(DEFAULT_IMAGE_CREDIT);
    }
    else
    {
      imageCredit = new JLabel(ERROR_IMAGE_UNAVAILABLE);
    } // end of if (image.getStatus() == 0)
    frame.add(imageCredit, BorderLayout.PAGE_START);
    lastImage = image;

    makeButtonPanel();
    frame.add(buttonPanel, BorderLayout.PAGE_END);

    makeSentencePanel();
    frame.add(sentencePanel, BorderLayout.LINE_END);

    frame.pack();
    frame.setVisible(true);
  } // end of method makeFrame()

  /*
   * Returns a random sentence pair.
   */
  private void getRandomSentencePair()
  {
    Random rand = new Random();;
    do
    {
      sentenceNumber = rand.nextInt(pairs.size()+1);
      currentPair = pairs.get(sentenceNumber);
    }
    while (currentPair.isCorrect());
  } // end of method getRandomSentencePair()

  /*
   * Sets player information.
   */
  private void setGoal() throws IOException
  {
    goal = -1;
    boolean valid = false;

    // sets goal if requested
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
            inputString = JOptionPane.showInputDialog(null, "What is your goal for number of correct sentences? ");
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
              JOptionPane.showMessageDialog(null, "It looks like you didn't give a valid number. Please choose a maximum integer above 0.");
            } // end of try
          } // end of while (goal < 1)
          valid = true;
          break;

        case "No":
        case "no":
        case "N":
        case "n":
          JOptionPane.showMessageDialog(null, "There will be no maximum number of sentences.", "Maximum number", JOptionPane.INFORMATION_MESSAGE);
          valid = true;
          break;

        default:
          JOptionPane.showMessageDialog(null, "It looks like you didn't choose a valid response. Please choose either yes or no.", "Error", JOptionPane.ERROR_MESSAGE);
      } // end of switch(inputString)
    } // end of while(!valid)
  } // end of method setGoal()

  /*
   * Checks the correctness of the user's answer.
   */
  private void checkAnswer() throws IOException
  {
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
    } // end of if (!answer.equals(SENTINEL_VALUE))

    // reset and update frame
    textField.setText("");

    if (correctCount > highScore)
    {
      highScore = correctCount;
      highScoreLabel.setText("high score: " + highScore);
    } // end of if (correctCount > highScore)

    if (correctCount != goal && !(answer.equals(SENTINEL_VALUE)))
    {
      getRandomSentencePair();
      randomizeLanguage();
    }
    else
    {
      endGame();
    }// end of if (correctCount != goal..)
  } // end of method checkAnswer()

  private void randomizeLanguage()
  {
    Random randomLanguage = new Random();

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
    } // end of if (randomLanguage.nextInt(2) == 0)

    // show current sentences
    sentenceLabel.setText(currentSentence);

    updateImage();
  } // end of method randomizeLanguage

  private void updateImage()
  {
    // change image to correspond
    switch (sentenceNumber)
    {
      case 4:
        frame.remove(lastImage);
        frame.add(homeImage, BorderLayout.CENTER);

        imageCredit.setText(HOME_IMAGE_CREDIT);

        lastImage = homeImage;

      case 9:
        frame.remove(lastImage);
        frame.add(movieImage, BorderLayout.CENTER);

        imageCredit.setText(MOVIE_IMAGE_CREDIT);

        lastImage = movieImage;

      default:
        frame.remove(lastImage);
        frame.add(image, BorderLayout.CENTER);

        imageCredit.setText(DEFAULT_IMAGE_CREDIT);
    } // end of switch (sentenceNumber)
  }

  private void endGame()
  {
    try
    {
      // update results
      changeResultsFile();

      // display results to user
      displayResults();
    }
    catch (IOException exception)
    {
    } // end of try
  } // end of method endGame()

  /*
   * Displays count of correct sentences.
   */
  private void displayResults()
  {
    JOptionPane.showMessageDialog(null, "\nNumber of sentences correct: " +
      correctCount + "\n        All time high score: " + highScore +
      "\n      All time lowest score: " + lowestScore);
  } // end of displayResults()

  private void changeResultsFile() throws IOException
  {
    final String RESULTS_FILE = "previousResults.text";

    PrintWriter outputFile = new PrintWriter(new FileWriter(RESULTS_FILE));

    // update results as necessary
    if (correctCount > highScore)
    {
      highScore = correctCount;
    } // end of if (correctCount > highScore)
    if (correctCount < lowestScore)
    {
      lowestScore = correctCount;
    } // end of if (correctCount < lowestScore)
    lastScore = correctCount;

    // change results in file
    outputFile.println(highScore);
    outputFile.println(lowestScore);
    outputFile.println(lastScore);

    outputFile.close();
  } // end of method changeResultsFile()

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

    /*
     * Returns the status of this image component
     */
    public int getStatus()
    {
      return status;
    } // end of method getStatus()

    /*
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
     *
     */
    public void paint(Graphics graphicsContext)
    {
      graphicsContext.drawImage(bufferedImage, 0, 0, null);
    } // end of method paint(Graphics graphicsContext)

  } // end of class ImageComponent

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
} // end of class LanguageGameGUI
