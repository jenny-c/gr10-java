import java.util.HashMap;
/**
 * Write a description of class SentencePair here.
 *
 * @author GivenName FamilyName
 * @version 1.0 yyyy-mm-dd
 */
public class SentencePairGUI
{
  // instance fields
  private String englishSentence;
  private String frenchSentence;
  private boolean correct;
  private static int count = 0;

   /**
    * Write a description of method main here.
    *
    * @param argument not used
    */
   public static void main(String[] argument)
   {

   } // end of method main(String[] argument)


   // constructors
   /**
    * Constructs a pair of sentences with specified values.
    */
    public SentencePairGUI(String englishSentence, String frenchSentence)
    {
      this.englishSentence = englishSentence;
      this.frenchSentence = frenchSentence;
      correct = false;
    }


    // accessors
    /**
     * Returns a string representation of this sentence pair.
     *
     * @return a string representation of this sentence pair
    */
    public String toString()
    {
      return
      getClass().getName()
      + "["
      + "english sentence: " + englishSentence
      + "french sentence: " + frenchSentence
      + "correct: " + correct
      + "]";
    }

    /**
     * Returns the englishSentence value of this sentence pair.
     *
     * @return the englishSentence value of this sentence pair
    */
    public String getEnglishSentence()
    {
      return englishSentence;
    }

    /**
     * Returns the frenchSentence value of this sentence pair.
     *
     * @return the frenchSentence value of this sentence pair
    */
    public String getFrenchSentence()
    {
     return frenchSentence;
    }

    /**
     * Returns the correct value of this sentence pair.
     *
     * @return the correct value of this sentence pair
    */
    public boolean isCorrect()
    {
      return correct;
    }


    // mutators
    /**
     * Sets the englishSentence value of this sentence pair.
     *
     * @param englishSentence english sentence in pair
    */
    public void setEnglishSentence(String englishSentence)
    {
      this.englishSentence = englishSentence;
    }

    /**
     * Sets the frenchSentence value of this sentence pair.
     *
     * @param frenchSentence french sentence in pair
    */
    public void setFrenchSentence(String frenchSentence)
    {
      this.frenchSentence = frenchSentence;
    }

    /**
     * Sets the correct value of this sentence pair.
     *
     * @param correct correctness of answer
    */
    public void setCorrectness(boolean correct)
    {
      this.correct = correct;
    }
} // end of class SentencePair
